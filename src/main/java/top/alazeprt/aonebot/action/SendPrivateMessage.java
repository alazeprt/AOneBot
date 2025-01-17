package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class SendPrivateMessage extends PostAction {
    private final long userId;
    private final String message;
    private final boolean autoEscape;

    public SendPrivateMessage(long userId, String message) {
        this.userId = userId;
        this.message = message;
        this.autoEscape = false;
    }

    public SendPrivateMessage(long userId, String message, boolean autoEscape) {
        this.userId = userId;
        this.message = message;
        this.autoEscape = autoEscape;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "send_private_msg");
        map.put("params", MapUtil.of("user_id", userId, "message", message, "auto_escape", autoEscape));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
