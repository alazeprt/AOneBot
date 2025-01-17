package top.alazeprt.aonebot.client.websocket;

import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import top.alazeprt.aonebot.client.MessageHandler;
import top.alazeprt.aonebot.event.Event;
import top.alazeprt.aonebot.event.Listener;
import top.alazeprt.aonebot.event.SubscribeBotEvent;
import top.alazeprt.aonebot.event.message.GroupMessageEvent;
import top.alazeprt.aonebot.event.message.GroupMessageType;
import top.alazeprt.aonebot.event.message.PrivateMessageEvent;
import top.alazeprt.aonebot.event.message.PrivateMessageType;
import top.alazeprt.aonebot.event.meta.HeartbeatEvent;
import top.alazeprt.aonebot.event.meta.LifecycleEvent;
import top.alazeprt.aonebot.event.meta.LifecycleType;
import top.alazeprt.aonebot.event.notice.*;
import top.alazeprt.aonebot.event.request.FriendRequestEvent;
import top.alazeprt.aonebot.event.request.GroupRequestEvent;
import top.alazeprt.aonebot.event.request.GroupRequestType;
import top.alazeprt.aonebot.util.ConsumerWithType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;
import static top.alazeprt.aonebot.event.notice.AdminChangeType.SET;
import static top.alazeprt.aonebot.event.notice.AdminChangeType.UNSET;
import static top.alazeprt.aonebot.event.notice.BanType.BAN;
import static top.alazeprt.aonebot.event.notice.BanType.LIFT_BAN;
import static top.alazeprt.aonebot.event.notice.MemberDecreaseType.KICK;
import static top.alazeprt.aonebot.event.notice.MemberDecreaseType.LEAVE;
import static top.alazeprt.aonebot.event.notice.MemberIncreaseType.APPROVE;
import static top.alazeprt.aonebot.event.notice.MemberIncreaseType.INVITE;
import static top.alazeprt.aonebot.event.request.GroupRequestType.ADD;

class WSClient extends WebSocketClient {

    public Map<String, ConsumerWithType<?>> consumerMap = new HashMap<>();

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
        System.out.println(s);
        JsonObject jsonObject = gson.fromJson(s, JsonObject.class);
        MessageHandler.handle(jsonObject);
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
    public void onClose(int i, String s, boolean b) {
    }

    @Override
    public void onError(Exception e) {
        if (!this.isOpen()) latch.countDown();
        e.printStackTrace();
    }

}
