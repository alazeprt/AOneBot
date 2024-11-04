package top.alazeprt.aonebot.event.notice;

import top.alazeprt.aonebot.event.Event;

public class GroupMemberDecreaseEvent extends Event {
    private final long time;
    private final long selfId;
    private final long groupId;
    private final long userId;
    private final long operatorId;
    private final MemberDecreaseType type;

    public GroupMemberDecreaseEvent(long time, long selfId, long groupId, long userId, long operatorId, MemberDecreaseType type) {
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

    public MemberDecreaseType getType() {
        return type;
    }
}
