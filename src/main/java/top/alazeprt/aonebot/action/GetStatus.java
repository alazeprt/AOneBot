package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.result.Status;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class GetStatus extends GetAction<Status> {
    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "get_status");
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }

    @Override
    public Class<Status> getClazz() {
        return Status.class;
    }
}
