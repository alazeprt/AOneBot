package top.alazeprt.aonebot.result;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class GroupMemberList {
    public static List<GroupMember> fromJson(JsonObject jsonObject) {
        List<GroupMember> list = new ArrayList<>();
        for (JsonElement element : jsonObject.getAsJsonArray("data").asList()) {
            JsonObject elementObject = element.getAsJsonObject();
            JsonObject newObject = new JsonObject();
            newObject.add("data", elementObject);
            list.add(GroupMember.fromJson(newObject));
        }
        return list;
    }
}
