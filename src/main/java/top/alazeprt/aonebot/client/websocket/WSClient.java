package top.alazeprt.aonebot.client.websocket;

import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import top.alazeprt.aonebot.client.MessageHandler;
import top.alazeprt.aonebot.util.ConsumerWithType;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

class WSClient extends WebSocketClient {

    public final Map<String, ConsumerWithType<?>> consumerMap = new HashMap<>();

    public CountDownLatch latch = new CountDownLatch(1);

    public WSClient(URI serverUri) {
        super(serverUri);
    }

    public WSClient(URI serverUri, Map<String, String> headers) {
        super(serverUri, headers);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        latch.countDown();
    }

    @Override
    public void onMessage(String s) {
        JsonObject jsonObject = gson.fromJson(s, JsonObject.class);
        MessageHandler.handle(jsonObject);
        Object toRemove = null;
        synchronized (consumerMap) {
            for (Map.Entry<String, ConsumerWithType<?>> entry : consumerMap.entrySet()) {
                if (jsonObject.get("echo") != null && entry.getKey().equals(jsonObject.get("echo").getAsString())) {
                    ConsumerWithType<?> consumerWithType = entry.getValue();
                    consumerWithType.accept(jsonObject);
                    toRemove = entry.getKey();
                }
            }
            if (toRemove != null) consumerMap.remove(toRemove);
       }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
    }

    @Override
    public void onError(Exception e) {
        if (!this.isOpen()) latch.countDown();
        e.printStackTrace();
    }

}
