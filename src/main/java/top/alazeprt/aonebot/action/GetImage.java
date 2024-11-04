package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class GetImage extends GetAction {
    private final String file;

    public GetImage(String file) {
        this.file = file;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "get_image");
        map.put("params", MapUtil.of("file", file));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
