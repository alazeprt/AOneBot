package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class SetGroupWholeBan extends PostAction {
    private final long groupId;
    private final boolean enable;

    public SetGroupWholeBan(long groupId) {
        this.groupId = groupId;
        this.enable = true;
    }

    public SetGroupWholeBan(long groupId, boolean enable) {
        this.groupId = groupId;
        this.enable = enable;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "set_group_whole_ban");
        map.put("params", MapUtil.of("group_id", groupId, "enable", enable));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
