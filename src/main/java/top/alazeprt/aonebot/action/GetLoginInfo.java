package top.alazeprt.aonebot.action;

import top.alazeprt.aonebot.result.LoginInfo;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class GetLoginInfo extends GetAction<LoginInfo> {
    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "delete_msg");
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }

    @Override
    public Class<LoginInfo> getClazz() {
        return LoginInfo.class;
    }
}
