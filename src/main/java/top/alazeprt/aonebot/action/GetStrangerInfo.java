package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.result.Stranger;
import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class GetStrangerInfo extends GetAction<Stranger> {
    private final long userId;
    private final boolean noCache;

    public GetStrangerInfo(long userId, boolean noCache) {
        this.userId = userId;
        this.noCache = noCache;
    }

    public GetStrangerInfo(long userId) {
        this.userId = userId;
        this.noCache = false;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "get_stranger_info");
        map.put("params", MapUtil.of("user_id", userId, "no_cache", noCache));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }

    @Override
    public Class<Stranger> getClazz() {
        return Stranger.class;
    }
}
