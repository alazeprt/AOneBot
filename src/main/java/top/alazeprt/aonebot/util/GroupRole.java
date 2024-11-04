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
        return switch (name.toUpperCase()) {
            case "OWNER" -> OWNER;
            case "ADMIN", "ADMINISTRATOR" -> ADMIN;
            case "MEMBER", "DEFAULT" -> MEMBER;
            default -> null;
        };
    }
}
