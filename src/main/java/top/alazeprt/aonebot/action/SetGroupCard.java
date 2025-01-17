package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class SetGroupCard extends PostAction {
    private final long groupId;
    private final long userId;
    private final String card;

    public SetGroupCard(long groupId, long userId, String card) {
        this.groupId = groupId;
        this.userId = userId;
        this.card = card;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "set_group_card");
        map.put("params", MapUtil.of("group_id", groupId, "user_id", userId, "card", card));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
