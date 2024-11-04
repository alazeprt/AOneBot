package top.alazeprt.aonebot.action;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class SendPrivateMessage extends PostAction {
    private final long groupId;
    private final String message;
    private final boolean autoEscape;

    public SendPrivateMessage(long groupId, String message) {
        this.groupId = groupId;
        this.message = message;
        this.autoEscape = false;
    }

    public SendPrivateMessage(long groupId, String message, boolean autoEscape) {
        this.groupId = groupId;
        this.message = message;
        this.autoEscape = autoEscape;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "send_private_msg");
        map.put("params", Map.of("user_id", groupId, "message", message, "auto_escape", autoEscape));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
