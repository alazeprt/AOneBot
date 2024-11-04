package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class SetGroupSpecialTitle extends PostAction {
    private final long groupId;
    private final long userId;
    private final String specialTitle;
    private final int duration = -1;

    public SetGroupSpecialTitle(long groupId, long userId, String specialTitle) {
        this.groupId = groupId;
        this.userId = userId;
        this.specialTitle = specialTitle;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "set_group_special_title");
        map.put("params", MapUtil.of("group_id", groupId, "user_id", userId, "special_title", specialTitle, "duration", duration));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
