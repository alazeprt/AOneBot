package top.alazeprt.aonebot.result;

import com.google.gson.JsonObject;

public class Stranger {
    private final long userId;
    private final String nickname;
    private final String sex;
    private final int age;

    public Stranger(long userId, String nickname, String sex, int age) {
        this.userId = userId;
        this.nickname = nickname;
        this.sex = sex;
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public long getUserId() {
        return userId;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public static Stranger fromJson(JsonObject jsonObject) {
        if (jsonObject.get("data").isJsonNull()) return null;
        jsonObject = jsonObject.get("data").getAsJsonObject();
        long userId = jsonObject.get("user_id").getAsLong();
        String nickname = jsonObject.get("nickname").getAsString();
        String sex = jsonObject.get("sex").getAsString();
        int age = jsonObject.get("age").getAsInt();
        return new Stranger(userId, nickname, sex, age);
    }
}
