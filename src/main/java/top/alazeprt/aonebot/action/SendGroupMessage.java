package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class SendGroupMessage extends PostAction {
    private final long groupId;
    private final String message;
    private final boolean autoEscape;

    public SendGroupMessage(long groupId, String message) {
        this.groupId = groupId;
        this.message = message;
        this.autoEscape = false;
    }

    public SendGroupMessage(long groupId, String message, boolean autoEscape) {
        this.groupId = groupId;
        this.message = message;
        this.autoEscape = autoEscape;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "send_group_msg");
        map.put("params", MapUtil.of("group_id", groupId, "message", message, "auto_escape", autoEscape));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
