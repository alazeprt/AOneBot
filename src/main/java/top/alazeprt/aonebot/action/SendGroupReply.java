package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.MapUtil;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.client.websocket.WebsocketBotClient.gson;

public class SendGroupReply extends PostAction {
    private final long groupId;
    private final String message;
    private final long replyFor;

    public SendGroupReply(long groupId, String message, long replyFor) {
        this.groupId = groupId;
        this.message = message;
        this.replyFor = replyFor;
    }

    @Override
    public String getData() {
        Map[] para = new Map[2];
        para[0] = MapUtil.of("type", "reply", "data", MapUtil.of("id", replyFor));
        para[1] = MapUtil.of("type", "text", "data", MapUtil.of("text", message));

        Map<String, Object> map = new HashMap<>();
        map.put("action", "send_group_msg");
        map.put("params", MapUtil.of("group_id", groupId, "message", para));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
