package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.util.GroupAddRequestType;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class SetGroupAddRequest extends PostAction {
    private final String flag;
    private final GroupAddRequestType type;
    private final boolean approve;
    private final String remark;

    public SetGroupAddRequest(String flag, GroupAddRequestType type, boolean approve, String remark) {
        this.flag = flag;
        this.type = type;
        this.approve = approve;
        this.remark = remark;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "set_group_add_request");
        map.put("params", Map.of("flag", flag, "approve", approve, "remark", remark, "type", type.toString().toLowerCase()));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
