package top.alazeprt.aonebot.event.notice;

import top.alazeprt.aonebot.event.Event;

public class GroupRecallEvent extends Event {
    private final long time;
    private final long selfId;
    private final long groupId;
    private final long userId;
    private final long messageId;
    private final long operatorId;

    public GroupRecallEvent(long time, long selfId, long groupId, long userId, long messageId, long operatorId) {
        this.time = time;
        this.selfId = selfId;
        this.groupId = groupId;
        this.userId = userId;
        this.messageId = messageId;
        this.operatorId = operatorId;
    }

    public long getTime() {
        return time;
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

    public long getMessageId() {
        return messageId;
    }

    public long getOperatorId() {
        return operatorId;
    }
}
