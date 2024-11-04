package top.alazeprt.aonebot.event.notice;

import top.alazeprt.aonebot.event.Event;

public class GroupAdminEvent extends Event {
    private final long time;
    private final long selfId;
    private final long userId;
    private final long groupId;
    private final AdminChangeType type;

    public GroupAdminEvent(long time, long selfId, long userId, long groupId, AdminChangeType type) {
        this.time = time;
        this.selfId = selfId;
        this.userId = userId;
        this.groupId = groupId;
        this.type = type;
    }

    public long getSelfId() {
        return selfId;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getUserId() {
        return userId;
    }

    public long getTime() {
        return time;
    }

    public AdminChangeType getType() {
        return type;
    }
}
