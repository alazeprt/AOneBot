package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.result.Group;
import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class GetGroupInfo extends GetAction<Group> {
    private final long groupId;
    private final boolean noCache;

    public GetGroupInfo(long groupId) {
        this.groupId = groupId;
        this.noCache = false;
    }

    public GetGroupInfo(long groupId, boolean noCache) {
        this.groupId = groupId;
        this.noCache = noCache;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "get_group_info");
        map.put("params", MapUtil.of("group_id", groupId, "no_cache", noCache));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }

    @Override
    public Class<Group> getClazz() {
        return Group.class;
    }
}
