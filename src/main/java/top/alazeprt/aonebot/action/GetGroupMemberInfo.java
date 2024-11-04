package top.alazeprt.aonebot.action;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class GetGroupMemberInfo extends GetAction {
    private final long groupId;
    private final long userId;
    private final boolean noCache;

    public GetGroupMemberInfo(long groupId, long userId, boolean noCache) {
        this.groupId = groupId;
        this.userId = userId;
        this.noCache = noCache;
    }

    public GetGroupMemberInfo(long groupId, long userId) {
        this.groupId = groupId;
        this.userId = userId;
        this.noCache = false;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "get_group_member_info");
        map.put("params", Map.of("group_id", groupId, "user_id", userId, "no_cache", noCache));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
