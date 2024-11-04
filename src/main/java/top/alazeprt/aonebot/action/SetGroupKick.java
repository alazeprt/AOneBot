package top.alazeprt.aonebot.action;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class SetGroupKick extends PostAction {
    private final long groupId;
    private final long userId;
    private final boolean rejectAddRequest;

    public SetGroupKick(long groupId, long userId) {
        this.groupId = groupId;
        this.userId = userId;
        this.rejectAddRequest = false;
    }

    public SetGroupKick(long groupId, long userId, boolean rejectAddRequest) {
        this.groupId = groupId;
        this.userId = userId;
        this.rejectAddRequest = rejectAddRequest;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "set_group_kick");
        map.put("params", Map.of("group_id", groupId, "user_id", userId, "reject_add_request", rejectAddRequest));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
