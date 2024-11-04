package top.alazeprt.aonebot.event.notice;

import top.alazeprt.aonebot.event.Event;

public class GroupMemberIncreaseEvent extends Event {
    private final long time;
    private final long selfId;
    private final long groupId;
    private final long userId;
    private final long operatorId;
    private final MemberIncreaseType type;

    public GroupMemberIncreaseEvent(long time, long selfId, long groupId, long userId, long operatorId, MemberIncreaseType type) {
        this.time = time;
        this.selfId = selfId;
        this.groupId = groupId;
        this.userId = userId;
        this.operatorId = operatorId;
        this.type = type;
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

    public long getSelfId() {
        return selfId;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public MemberIncreaseType getType() {
        return type;
    }
}
