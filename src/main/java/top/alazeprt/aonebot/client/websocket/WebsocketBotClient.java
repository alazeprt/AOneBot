package top.alazeprt.aonebot.client.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import top.alazeprt.aonebot.action.Action;
import top.alazeprt.aonebot.action.GetAction;
import top.alazeprt.aonebot.client.BotClient;
import top.alazeprt.aonebot.client.MessageHandler;
import top.alazeprt.aonebot.event.Listener;
import top.alazeprt.aonebot.util.ConsumerWithType;
import top.alazeprt.aonebot.util.MapUtil;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class WebsocketBotClient implements BotClient {
    private final URI uri;
    private WSClient client;
    private String accessToken;
    private Logger logger;
    private final MessageHandler messageHandler = new MessageHandler();

    public static final Gson gson = new Gson();

    public WebsocketBotClient(URI uri) {
        this.uri = uri;
    }

    public WebsocketBotClient(String address, int port) throws URISyntaxException {
        this.uri = new URI("ws://" + address + ":" + port);
    }

    public WebsocketBotClient(URI uri, String accessToken) {
        this.uri = uri;
        this.accessToken = accessToken;
    }

    public WebsocketBotClient(String address, int port, String accessToken) throws URISyntaxException {
        this.uri = new URI("ws://" + address + ":" + port);
        this.accessToken = accessToken;
    }

    public void connect() {
        if (logger != null) logger.info("Connecting to the websocket server at " + uri);
        if (accessToken == null) {
            client = new WSClient(uri, messageHandler);
        } else {
            client = new WSClient(uri, MapUtil.of("Authorization", "Bearer " + accessToken), messageHandler);
        }
        try {
            client.connect();
            Thread.sleep(10000);
            if (!isConnected()) throw new InterruptedException("Failed to connect to the websocket server after 10 seconds");
            client.latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void send(String data) {
        if (logger != null) logger.info("Sending data to the websocket server: " + data);
        if (client != null) {
            client.send(data);
        } else {
            throw new IllegalStateException("Client not connected");
        }
    }

    public void disconnect() {
        if (logger != null) logger.info("Disconnecting from the websocket server: " + uri);
        client.close();
        client = null;
    }

    @Override
    public void action(Action action) {
        send(action.getData());
    }

    @Override
    public <T> void action(GetAction<T> action, Consumer<T> consumer) {
        String data = action.getData();
        ConsumerWithType<T> consumerWithType = new ConsumerWithType<>(action.getClazz(), consumer);
        client.consumerMap.put(gson.fromJson(data, JsonObject.class).get("echo").getAsString(), consumerWithType);
        send(data);
    }

    @Override
    public void registerEvent(Listener listener) {
        messageHandler.eventClassList.add(listener);
    }

    @Override
    public void unregisterEvent(Listener listener) {
        messageHandler.eventClassList.remove(listener);
    }

    public boolean isConnected() {
        return client.isOpen();
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
        messageHandler.logger = logger;
    }

    public List<Listener> getEventList() {
        return messageHandler.eventClassList;
    }
}
