package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.result.GroupList;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class GetGroupList extends GetAction<GroupList> {
    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "get_group_list");
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }

    @Override
    public Class<GroupList> getClazz() {
        return GroupList.class;
    }
}
