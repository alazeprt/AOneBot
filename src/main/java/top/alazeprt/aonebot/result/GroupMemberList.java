package top.alazeprt.aonebot.result;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class GroupMemberList extends ArrayList<GroupMember> {
    private GroupMemberList() {
        super();
    }

    public static GroupMemberList fromJson(JsonObject jsonObject) {
        GroupMemberList list = new GroupMemberList();
        if (jsonObject.get("data").isJsonNull()) return null;
        for (JsonElement element : jsonObject.getAsJsonArray("data")) {
            JsonObject elementObject = element.getAsJsonObject();
            JsonObject newObject = new JsonObject();
            newObject.add("data", elementObject);
            list.add(GroupMember.fromJson(newObject));
        }
        return list;
    }
}
