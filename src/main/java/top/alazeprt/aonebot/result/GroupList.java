package top.alazeprt.aonebot.result;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class GroupList extends ArrayList<Group> {
    private GroupList() {
        super();
    }

    public static GroupList fromJson(JsonObject jsonObject) {
        GroupList list = new GroupList();
        if (!jsonObject.get("status").getAsString().equalsIgnoreCase("ok")) {
            return list;
        }
        for (JsonElement element : jsonObject.getAsJsonArray("data")) {
            JsonObject elementObject = element.getAsJsonObject();
            JsonObject newObject = new JsonObject();
            newObject.add("data", elementObject);
            list.add(Group.fromJson(newObject));
        }
        return list;
    }
}
