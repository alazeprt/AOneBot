package top.alazeprt.aonebot.result;

import com.google.gson.JsonObject;

public class LoginInfo {
    private final long userId;
    private final String nickname;

    private LoginInfo(long userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    public static LoginInfo fromJson(JsonObject jsonObject) {
        jsonObject = jsonObject.get("data").getAsJsonObject();
        long userId = jsonObject.get("user_id").getAsLong();
        String nickname = jsonObject.get("nickname").getAsString();
        return new LoginInfo(userId, nickname);
    }

    public String getNickname() {
        return nickname;
    }

    public long getUserId() {
        return userId;
    }
}
