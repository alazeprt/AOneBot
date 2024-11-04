package top.alazeprt.aonebot.event.request;

import top.alazeprt.aonebot.event.Event;

public class GroupRequestEvent extends Event {
    private final long time;
    private final long selfId;
    private final long userId;
    private final long groupId;
    private final String comment;
    private final String flag;
    private final GroupRequestType subType;

    public GroupRequestEvent(long time, long selfId, long userId, long groupId, String comment, String flag, GroupRequestType subType) {
        this.time = time;
        this.selfId = selfId;
        this.userId = userId;
        this.groupId = groupId;
        this.comment = comment;
        this.flag = flag;
        this.subType = subType;
    }

    public long getTime() {
        return time;
    }

    public long getSelfId() {
        return selfId;
    }

    public long getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public String getFlag() {
        return flag;
    }

    public long getGroupId() {
        return groupId;
    }

    public GroupRequestType getSubType() {
        return subType;
    }
}
