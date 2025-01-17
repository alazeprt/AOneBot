package top.alazeprt.aonebot.result;

import com.google.gson.JsonObject;
import top.alazeprt.aonebot.util.GroupRole;

public class GroupMember {
    private final Member member;
    private final long groupId;
    private final String card;
    private final long joinTime;
    private final long lastSentTime;
    private final String level;
    private final GroupRole role;
    private final String title;
    private final long titleExpireTime;
    private final boolean cardChangeable;

    GroupMember(long groupId, Member member, String card, long joinTime, long lastSentTime, String level, GroupRole role, String title, long titleExpireTime, boolean cardChangeable) {
        this.groupId = groupId;
        this.member = member;
        this.card = card;
        this.joinTime = joinTime;
        this.lastSentTime = lastSentTime;
        this.level = level;
        this.role = role;
        this.title = title;
        this.titleExpireTime = titleExpireTime;
        this.cardChangeable = cardChangeable;
    }

    public long getGroupId() {
        return groupId;
    }

    public String getCard() {
        return card;
    }

    public Member getMember() {
        return member;
    }

    public GroupRole getRole() {
        return role;
    }

    public boolean isCardChangeable() {
        return cardChangeable;
    }

    public long getJoinTime() {
        return joinTime;
    }

    public long getLastSentTime() {
        return lastSentTime;
    }

    public long getTitleExpireTime() {
        return titleExpireTime;
    }

    public String getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public static GroupMember fromJson(JsonObject jsonObject) {
        if (jsonObject.get("data").isJsonNull()) return null;
        jsonObject = jsonObject.get("data").getAsJsonObject();
        long groupId = jsonObject.get("group_id").getAsLong();
        long userId = jsonObject.get("user_id").getAsLong();
        String nickname = jsonObject.get("nickname").getAsString();
        String card = jsonObject.get("card").isJsonNull() ? nickname : jsonObject.get("card").getAsString();
        String sex = jsonObject.get("sex").getAsString();
        int age = jsonObject.get("age").getAsInt();
        String area = jsonObject.get("area").getAsString();
        long joinTime = jsonObject.get("join_time").getAsLong();
        long lastSentTime = jsonObject.get("last_sent_time").getAsLong();
        String level = jsonObject.get("level").getAsString();
        GroupRole role = GroupRole.fromName(jsonObject.get("role").getAsString());
        boolean unfriendly = jsonObject.get("unfriendly").getAsBoolean();
        String title = jsonObject.get("title").getAsString();
        long titleExpireTime = jsonObject.get("title_expire_time").getAsLong();
        boolean cardChangeable = jsonObject.get("card_changeable").getAsBoolean();
        Member member = new Member(userId, nickname, sex, age, area, unfriendly);
        return new GroupMember(groupId, member, card, joinTime, lastSentTime, level, role, title, titleExpireTime, cardChangeable);
    }
}
