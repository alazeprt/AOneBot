package top.alazeprt.aonebot.action;

import java.util.HashMap;
import java.util.Map;

import static top.alazeprt.aonebot.BotClient.gson;

public class GetRecord extends GetAction {
    private final String file;
    private final String outputFormat;

    public GetRecord(String file, String outputFormat) {
        this.file = file;
        this.outputFormat = outputFormat;
    }

    @Override
    public String getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("action", "get_record");
        map.put("params", Map.of("file", file, "output_format", outputFormat));
        map.put("echo", "aob_" + System.currentTimeMillis()%10000);
        return gson.toJson(map);
    }
}
