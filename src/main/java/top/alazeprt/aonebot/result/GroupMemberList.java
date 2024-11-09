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
        for (JsonElement element : jsonObject.getAsJsonArray("data").asList()) {
            JsonObject elementObject = element.getAsJsonObject();
            JsonObject newObject = new JsonObject();
            newObject.add("data", elementObject);
            list.add(GroupMember.fromJson(newObject));
        }
        return list;
    }
}
