package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class SendLike extends PostAction{
    private final long userId;
    private final int times;

    public SendLike(long userId, int times) {
        this.userId = userId;
        this.times = times;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "send_like");
        map.put("params", MapUtil.of("user_id", userId, "times", times));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
