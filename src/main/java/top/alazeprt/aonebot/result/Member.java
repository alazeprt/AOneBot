package top.alazeprt.aonebot.result;

public class Member {
    private final long userId;
    private final String nickname;
    private final String sex;
    private final int age;
    private final String area;
    private final boolean unfriendly;


    protected Member(long userId, String nickname, String sex, int age, String area, boolean unfriendly) {
        this.userId = userId;
        this.nickname = nickname;
        this.sex = sex;
        this.age = age;
        this.area = area;
        this.unfriendly = unfriendly;
    }

    public int getAge() {
        return age;
    }

    public boolean isUnfriendly() {
        return unfriendly;
    }

    public long getUserId() {
        return userId;
    }

    public String getArea() {
        return area;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSex() {
        return sex;
    }
}
