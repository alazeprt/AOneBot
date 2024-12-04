package top.alazeprt.aonebot.util;

import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static top.alazeprt.aonebot.BotClient.gson;
import static top.alazeprt.aonebot.event.notice.AdminChangeType.SET;
import static top.alazeprt.aonebot.event.notice.AdminChangeType.UNSET;
import static top.alazeprt.aonebot.event.notice.BanType.BAN;
import static top.alazeprt.aonebot.event.notice.BanType.LIFT_BAN;
import static top.alazeprt.aonebot.event.notice.MemberDecreaseType.KICK;
import static top.alazeprt.aonebot.event.notice.MemberDecreaseType.LEAVE;
import static top.alazeprt.aonebot.event.notice.MemberIncreaseType.APPROVE;
import static top.alazeprt.aonebot.event.notice.MemberIncreaseType.INVITE;
import static top.alazeprt.aonebot.event.request.GroupRequestType.ADD;

public class BotWSClient extends WebSocketClient {

    public Map<String, ConsumerWithType<?>> consumerMap = new HashMap<>();
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
        if (jsonObject.get("post_type") == null) {

        } else if (jsonObject.get("post_type").getAsString().equals("meta_event")) {
            long time = jsonObject.get("time").getAsLong();
            switch (jsonObject.get("meta_event_type").getAsString()) {
                case "heartbeat":
                    JsonObject status = jsonObject.getAsJsonObject("status");
                    postEvent(new HeartbeatEvent(time,
                            status.get("online").getAsBoolean(),
                            status.get("good").getAsBoolean()));
                    break;
                case "lifecycle":
                    postEvent(new LifecycleEvent(time, LifecycleType.valueOf(jsonObject.get("sub_type").getAsString().toUpperCase())));
                    break;
            }
        } else if (jsonObject.get("post_type").getAsString().equals("message")) {
            long time = jsonObject.get("time").getAsLong();
            switch (jsonObject.get("message_type").getAsString()) {
                case "private":
                    postEvent(new PrivateMessageEvent(time,
                        PrivateMessageType.valueOf(jsonObject.get("sub_type").getAsString().toUpperCase()),
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("message_id").getAsLong(),
                        jsonObject.getAsJsonArray("message"),
                        jsonObject.get("raw_message").getAsString(),
                        jsonObject.get("font").getAsInt(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("sender").getAsJsonObject().get("nickname").getAsString()));
                    break;
                case "group":
                    postEvent(new GroupMessageEvent(time,
                        GroupMessageType.valueOf(jsonObject.get("sub_type").getAsString().toUpperCase()),
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("message_id").getAsLong(),
                        jsonObject.get("group_id").getAsLong(),
                        jsonObject.get("anonymous") == null ? -1L : (jsonObject.get("anonymous").isJsonNull() ? -1L : jsonObject.get("anonymous").getAsJsonObject().get("id").getAsLong()),
                        jsonObject.get("anonymous") == null ? null : (jsonObject.get("anonymous").isJsonNull() ? null : jsonObject.getAsJsonObject("anonymous").get("name").getAsString()),
                        jsonObject.getAsJsonArray("message"),
                        jsonObject.get("raw_message").getAsString(),
                        jsonObject.get("font").getAsInt(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("sender").getAsJsonObject().get("nickname").getAsString()));
                    break;
            }
        } else if (jsonObject.get("post_type").getAsString().equals("notice")) {
            long time = jsonObject.get("time").getAsLong();
            switch (jsonObject.get("notice_type").getAsString()) {
                case "group_upload":
                    postEvent(new GroupUploadEvent(time,
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("group_id").getAsLong(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("file").getAsJsonObject().get("id").getAsString(),
                        jsonObject.get("file").getAsJsonObject().get("name").getAsString(),
                        jsonObject.get("file").getAsJsonObject().get("size").getAsLong()));
                    break;
                case "group_admin":
                    postEvent(new GroupAdminEvent(time,
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("group_id").getAsLong(),
                        jsonObject.get("sub_type").getAsString().equals("set") ? SET : UNSET));
                    break;
                case "group_decrease":
                    postEvent(new GroupMemberDecreaseEvent(time,
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("group_id").getAsLong(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("operator_id").getAsLong(),
                        jsonObject.get("sub_type").getAsString().equals("leave") ? LEAVE : KICK));
                    break;
                case "group_increase":
                    postEvent(new GroupMemberIncreaseEvent(time,
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("group_id").getAsLong(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("operator_id").getAsLong(),
                        jsonObject.get("sub_type").getAsString().equals("invite") ? INVITE : APPROVE));
                    break;
                case "group_ban":
                    postEvent(new GroupBanEvent(time,
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("group_id").getAsLong(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("operator_id").getAsLong(),
                        jsonObject.get("duration").getAsLong(),
                        jsonObject.get("sub_type").getAsString().equals("ban") ? BAN : LIFT_BAN));
                    break;
                case "group_recall":
                    postEvent(new GroupRecallEvent(time,
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("group_id").getAsLong(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("message_id").getAsLong(),
                        jsonObject.get("operator_id").getAsLong()));
                    break;
                case "friend_recall":
                    postEvent(new FriendRecallEvent(time,
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("message_id").getAsLong()));
                    break;
                case "poke":
                    postEvent(new PokeEvent(time,
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("group_id").getAsLong(),
                        jsonObject.get("target_id").getAsLong()));
                break;
            }
        } else if (jsonObject.get("post_type").getAsString().equals("request")) {
            long time = jsonObject.get("time").getAsLong();
            switch (jsonObject.get("request_type").getAsString()) {
                case "friend":
                    postEvent(new FriendRequestEvent(time,
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("comment").isJsonNull() ? "" : jsonObject.get("comment").getAsString(),
                        jsonObject.get("flag").getAsString()));
                    break;
                case "group":
                    postEvent(new GroupRequestEvent(time,
                        jsonObject.get("self_id").getAsLong(),
                        jsonObject.get("user_id").getAsLong(),
                        jsonObject.get("group_id").getAsLong(),
                        jsonObject.get("comment").isJsonNull() ? "" : jsonObject.get("comment").getAsString(),
                        jsonObject.get("flag").getAsString(),
                        jsonObject.get("sub_type").getAsString().equals("add") ? ADD : GroupRequestType.INVITE));
                    break;
            }
        }
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

    private void postEvent(Event event) {
        for (Listener clazz : eventClassList) {
            for (Method method : clazz.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(SubscribeBotEvent.class)) {
                    if (method.getParameters().length != 1) continue;
                    if (!event.getClass().getTypeName().equals(method.getParameters()[0].getParameterizedType().getTypeName())) continue;
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
