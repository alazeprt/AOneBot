package top.alazeprt.aonebot.client.websocket.reverse;

import com.google.gson.JsonObject;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import top.alazeprt.aonebot.client.MessageHandler;
import top.alazeprt.aonebot.util.ConsumerWithType;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

class WSServer extends WebSocketServer {

    public Map<String, ConsumerWithType<?>> consumerMap = new ConcurrentHashMap<>();

    public CountDownLatch latch = new CountDownLatch(1);

    private String accessToken;
    private final MessageHandler messageHandler;
    private Logger logger;

    public WSServer(InetSocketAddress address, String accessToken, Logger logger, MessageHandler messageHandler) {
        super(address);
        this.accessToken = accessToken;
        this.logger = logger;
        this.messageHandler = messageHandler;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        latch.countDown();
        if (logger != null) logger.info("A websocket connection has been established with " + webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        JsonObject jsonObject = gson.fromJson(s, JsonObject.class);
        messageHandler.handle(jsonObject);
        Object toRemove = null;
        for (Map.Entry<String, ConsumerWithType<?>> entry : consumerMap.entrySet()) {
            if (jsonObject.get("echo") != null && entry.getKey().equals(jsonObject.get("echo").getAsString())) {
                ConsumerWithType<?> consumerWithType = entry.getValue();
                consumerWithType.accept(jsonObject);
                toRemove = entry.getKey();
            }
        }
        if (toRemove != null) consumerMap.remove(toRemove);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        if (this.getConnections().isEmpty()) latch.countDown();
        e.printStackTrace();
    }

    @Override
    public void onStart() {

    }

    @Override
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft, ClientHandshake request) throws InvalidDataException {
        String authHeader = request.getFieldValue("Authorization").substring(7);
        if (accessToken != null && !accessToken.equals(authHeader)) {
            throw new InvalidDataException(1002, "Invalid access token");
        } else {
            return super.onWebsocketHandshakeReceivedAsServer(conn, draft, request);
        }
    }
}
