package top.alazeprt.aonebot.result;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class GroupList {
    public static List<Group> fromJson(JsonObject jsonObject) {
        List<Group> list = new ArrayList<>();
        for (JsonElement element : jsonObject.getAsJsonArray("data").asList()) {
            JsonObject elementObject = element.getAsJsonObject();
            JsonObject newObject = new JsonObject();
            newObject.add("data", elementObject);
            list.add(Group.fromJson(newObject));
        }
        return list;
    }
}
