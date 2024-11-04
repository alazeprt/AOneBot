package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class SetGroupBan extends PostAction {
    private final long groupId;
    private final long userId;
    private final long duration;

    public SetGroupBan(long groupId, long userId, long duration) {
        this.groupId = groupId;
        this.userId = userId;
        this.duration = duration;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "set_group_ban");
        map.put("params", MapUtil.of("group_id", groupId, "user_id", userId, "duration", duration));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
