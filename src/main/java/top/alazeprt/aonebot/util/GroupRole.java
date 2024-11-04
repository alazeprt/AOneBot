package top.alazeprt.aonebot.util;

public enum GroupRole {
    OWNER,
    ADMIN,
    MEMBER;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static GroupRole fromName(String name) {
        switch (name.toUpperCase()) {
            case "OWNER":
                return OWNER;
            case "ADMIN":
            case "ADMINISTRATOR":
                return ADMIN;
            case "MEMBER":
            case "DEFAULT":
                return MEMBER;
            default:
                return null;
        }
    }
}
