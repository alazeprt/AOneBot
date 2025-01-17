package top.alazeprt.aonebot.client.websocket.reverse;

import com.google.gson.JsonObject;
import top.alazeprt.aonebot.action.Action;
import top.alazeprt.aonebot.action.GetAction;
import top.alazeprt.aonebot.client.BotClient;
import top.alazeprt.aonebot.client.MessageHandler;
import top.alazeprt.aonebot.event.Listener;
import top.alazeprt.aonebot.util.ConsumerWithType;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Consumer;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class WebsocketReverseBotClient implements BotClient {
    private final InetSocketAddress address;
    private WSServer server;
    private String accessToken;

    public WebsocketReverseBotClient(String address, int port) {
        this.address = new InetSocketAddress(address, port);
    }

    public WebsocketReverseBotClient(InetSocketAddress address) {
        this.address = address;
    }

    public WebsocketReverseBotClient(String address, int port, String accessToken) {
        this.address = new InetSocketAddress(address, port);
        this.accessToken = accessToken;
    }

    public WebsocketReverseBotClient(InetSocketAddress address, String accessToken) {
        this.address = address;
        this.accessToken = accessToken;
    }

    public void start() {
        server = new WSServer(address, accessToken);
        server.start();
        try {
           server.latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            server.stop();
            server = null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void send(String data) {
        if (server != null) {
            server.broadcast(data);
        } else {
            throw new IllegalStateException("Server not started");
        }
    }

    @Override
    public void action(Action action) {
        send(action.getData());
    }

    @Override
    public <T> void action(GetAction<T> action, Consumer<T> consumer) {
        String data = action.getData();
        ConsumerWithType<T> consumerWithType = new ConsumerWithType<>(action.getClazz(), consumer);
        server.consumerMap.put(gson.fromJson(data, JsonObject.class).get("echo").getAsString(), consumerWithType);
        send(data);
    }

    @Override
    public void registerEvent(Listener listener) {
        MessageHandler.eventClassList.add(listener);
    }

    public boolean isStarted() {
        return server != null;
    }

    public boolean hasClientConnected() {
        return !server.getConnections().isEmpty();
    }
}
