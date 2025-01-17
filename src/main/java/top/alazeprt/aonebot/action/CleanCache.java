package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.Null;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class CleanCache extends PostAction {
    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "clean_cache");
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
