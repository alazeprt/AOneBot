package top.alazeprt.aonebot.action;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class SetFriendAddRequest extends PostAction {
    private final String flag;
    private final boolean approve;
    private final String remark;

    public SetFriendAddRequest(String flag, boolean approve, String remark) {
        this.flag = flag;
        this.approve = approve;
        this.remark = remark;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "set_friend_add_request");
        map.put("params", Map.of("flag", flag, "approve", approve, "remark", remark));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
