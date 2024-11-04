package top.alazeprt.aonebot.action;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class CleanCache extends GetAction {
    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "delete_msg");
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
