package top.alazeprt.aonebot;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import top.alazeprt.aonebot.action.Action;
import top.alazeprt.aonebot.action.GetAction;
import top.alazeprt.aonebot.event.Listener;
import top.alazeprt.aonebot.util.BotWSClient;
import top.alazeprt.aonebot.util.ConsumerWithType;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Consumer;

public class BotClient {
    private final URI uri;
    private BotWSClient client;
    private String accessToken;

    public static final Gson gson = new Gson();

    public BotClient(URI uri) {
        this.uri = uri;
    }

    public BotClient(String address, int port) throws URISyntaxException {
        this.uri = new URI("ws://" + address + ":" + port);
    }

    public BotClient(URI uri, String accessToken) {
        this.uri = uri;
        this.accessToken = accessToken;
    }

    public BotClient(String address, int port, String accessToken) throws URISyntaxException {
        this.uri = new URI("ws://" + address + ":" + port);
        this.accessToken = accessToken;
    }

    public void connect() {
        client = new BotWSClient(uri);
        client.connect();
        try {
            client.latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void send(String data) {
        if (client != null) {
            if (accessToken != null) client.addHeader("Authorization", "Bearer " + accessToken);
            client.send(data);
        } else {
            throw new IllegalStateException("Client not connected");
        }
    }

    public void disconnect() {
        client.close();
        client = null;
    }

    public void action(Action action) {
        send(action.getData());
    }

    public <T> void action(GetAction<T> action, Consumer<T> consumer) {
        String data = action.getData();
        ConsumerWithType<T> consumerWithType = new ConsumerWithType<>(action.getClazz(), consumer);
        client.consumerMap.put(gson.fromJson(data, JsonObject.class).get("echo").getAsString(), consumerWithType);
        send(data);
    }

    public void registerEvent(Listener listener) {
        client.eventClassList.add(listener);
    }

    public boolean isConnected() {
        return client.isOpen();
    }
}
