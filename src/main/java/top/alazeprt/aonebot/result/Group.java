package top.alazeprt.aonebot.result;

import com.google.gson.JsonObject;

public class Group {
    private final long groupId;
    private final String groupName;
    private final int memberCount;
    private final int maxMemberCount;

    private Group(long groupId, String groupName, int memberCount, int maxMemberCount) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.memberCount = memberCount;
        this.maxMemberCount = maxMemberCount;
    }

    public long getGroupId() {
        return groupId;
    }

    public int getMaxMemberCount() {
        return maxMemberCount;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public static Group fromJson(JsonObject jsonObject) {
        jsonObject = jsonObject.get("data").getAsJsonObject();
        long groupId = jsonObject.get("group_id").getAsLong();
        String groupName = jsonObject.get("group_name").getAsString();
        int memberCount = jsonObject.get("member_count").getAsInt();
        int maxMemberCount = jsonObject.get("max_member_count").getAsInt();
        return new Group(groupId, groupName, memberCount, maxMemberCount);
    }
}
