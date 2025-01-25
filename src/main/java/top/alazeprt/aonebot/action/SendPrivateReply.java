package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class SendPrivateReply extends PostAction {
    private final long userId;
    private final String message;
    private final long replyFor;

    public SendPrivateReply(long userId, String message, long replyFor) {
        this.userId = userId;
        this.message = message;
        this.replyFor = replyFor;
    }

    @Override
    public String getData() {
        Map[] para = new Map[2];
        para[0] = MapUtil.of("type", "reply", "data", MapUtil.of("id", replyFor));
        para[1] = MapUtil.of("type", "text", "data", MapUtil.of("text", message));

        Map<String, Object> map = new HashMap<>();
        map.put("action", "send_private_msg");
        map.put("params", MapUtil.of("user_id", userId, "message", para));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
