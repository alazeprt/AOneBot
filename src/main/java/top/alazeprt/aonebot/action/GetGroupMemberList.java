package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.result.GroupMemberList;
import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class GetGroupMemberList extends GetAction<GroupMemberList> {
    private final long groupId;

    public GetGroupMemberList(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "get_group_member_list");
        map.put("params", MapUtil.of("group_id", groupId));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }

    @Override
    public Class<GroupMemberList> getClazz() {
        return GroupMemberList.class;
    }
}
