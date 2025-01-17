package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.Null;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class CleanCache extends GetAction<Null> {
    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "delete_msg");
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }

    @Override
    public Class<Null> getClazz() {
        return null;
    }
}
