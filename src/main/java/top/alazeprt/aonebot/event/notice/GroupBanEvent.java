package top.alazeprt.aonebot.event.notice;

import top.alazeprt.aonebot.event.Event;

public class GroupBanEvent extends Event {
    private final long time;
    private final long selfId;
    private final long userId;
    private final long groupId;
    private final long operatorId;
    private final long duration;
    private final BanType type;

    public GroupBanEvent(long time, long selfId, long userId, long groupId, long operatorId, long duration, BanType type) {
        this.time = time;
        this.selfId = selfId;
        this.userId = userId;
        this.groupId = groupId;
        this.operatorId = operatorId;
        this.duration = duration;
        this.type = type;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public long getTime() {
        return time;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getUserId() {
        return userId;
    }

    public long getSelfId() {
        return selfId;
    }

    public BanType getType() {
        return type;
    }

    public long getDuration() {
        return duration;
    }
}
