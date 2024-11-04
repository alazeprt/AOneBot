package top.alazeprt.aonebot.result;

import com.google.gson.JsonObject;

public class Status {
    private final boolean online;
    private final boolean good;

    public Status(boolean online, boolean good) {
        this.online = online;
        this.good = good;
    }

    public boolean isGood() {
        return good;
    }

    public boolean isOnline() {
        return online;
    }

    public static Status fromJson(JsonObject jsonObject) {
        jsonObject = jsonObject.get("data").getAsJsonObject();
        boolean good = jsonObject.get("good").getAsBoolean();
        boolean online = jsonObject.get("online").getAsBoolean();
        return new Status(online, good);
    }
}
