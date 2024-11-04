package top.alazeprt.aonebot.util;

import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import top.alazeprt.aonebot.event.Event;
import top.alazeprt.aonebot.event.Listener;
import top.alazeprt.aonebot.event.message.GroupMessageEvent;
import top.alazeprt.aonebot.event.message.GroupMessageType;
import top.alazeprt.aonebot.event.message.PrivateMessageEvent;
import top.alazeprt.aonebot.event.message.PrivateMessageType;
import top.alazeprt.aonebot.event.meta.HeartbeatEvent;
import top.alazeprt.aonebot.event.SubscribeBotEvent;
import top.alazeprt.aonebot.event.meta.LifecycleEvent;
import top.alazeprt.aonebot.event.meta.LifecycleType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

import static top.alazeprt.aonebot.BotClient.gson;

public class BotWSClient extends WebSocketClient {

    public Map<String, Consumer<JsonObject>> consumerMap = new HashMap<>();
    public List<Listener> eventClassList = new ArrayList<>();

    public CountDownLatch latch = new CountDownLatch(1);

    public BotWSClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        latch.countDown();
    }

    @Override
    public void onMessage(String s) {
        JsonObject jsonObject = gson.fromJson(s, JsonObject.class);
        System.out.println(s);

        if (jsonObject.get("post_type") == null) {

        } else if (jsonObject.get("post_type").getAsString().equals("meta_event")) {
            long time = jsonObject.get("time").getAsLong();
            switch (jsonObject.get("meta_event_type").getAsString()) {
                case "heartbeat" -> {
                    JsonObject status = jsonObject.getAsJsonObject("status");
                    postEvent(new HeartbeatEvent(time,
                            status.get("online").getAsBoolean(),
                            status.get("good").getAsBoolean()));
                }
                case "lifecycle" -> {
                    postEvent(new LifecycleEvent(time, LifecycleType.valueOf(jsonObject.get("sub_type").getAsString().toUpperCase())));
                }
            }
        } else if (jsonObject.get("post_type").getAsString().equals("message")) {
            long time = jsonObject.get("time").getAsLong();
            switch (jsonObject.get("message_type").getAsString()) {
                case "private" -> postEvent(new PrivateMessageEvent(time,
                        PrivateMessageType.valueOf(jsonObject.get("sub_type").getAsString().toUpperCase()),
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("message_id").getAsLong(),
                        jsonObject.getAsJsonArray("message"),
                        jsonObject.get("raw_message").getAsString(),
                        jsonObject.get("font").getAsInt(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("sender").getAsJsonObject().get("nickname").getAsString()));
                case "group" -> postEvent(new GroupMessageEvent(time,
                        GroupMessageType.valueOf(jsonObject.get("sub_type").getAsString().toUpperCase()),
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("message_id").getAsLong(),
                        jsonObject.get("group_id").getAsLong(),
                        jsonObject.get("anonymous").isJsonNull() ? -1L : jsonObject.get("anonymous").getAsJsonObject().get("id").getAsLong(),
                        jsonObject.get("anonymous").isJsonNull() ? null : jsonObject.getAsJsonObject("anonymous").get("name").getAsString(),
                        jsonObject.getAsJsonArray("message"),
                        jsonObject.get("raw_message").getAsString(),
                        jsonObject.get("font").getAsInt(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("sender").getAsJsonObject().get("nickname").getAsString()));
            }
        }
        Object toRemove = null;
        for (Map.Entry<String, Consumer<JsonObject>> entry : consumerMap.entrySet()) {
            if (jsonObject.get("echo") != null && entry.getKey().equals(jsonObject.get("echo").getAsString())) {
                entry.getValue().accept(jsonObject);
                toRemove = entry.getKey();
            }
        }
        if (toRemove != null) consumerMap.remove(toRemove);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }

    private void postEvent(Event event) {
        for (Listener clazz : eventClassList) {
            for (Method method : clazz.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(SubscribeBotEvent.class)) {
                    if (method.getParameters().length != 1) return;
                    if (!event.getClass().getTypeName().equals(method.getParameters()[0].getParameterizedType().getTypeName())) return;
                    try {
                        method.invoke(clazz, event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
