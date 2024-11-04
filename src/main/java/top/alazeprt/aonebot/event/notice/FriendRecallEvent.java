package top.alazeprt.aonebot.event.notice;

import top.alazeprt.aonebot.event.Event;

public class FriendRecallEvent extends Event {
    private final long time;
    private final long selfId;
    private final long userId;
    private final long messageId;

    public FriendRecallEvent(long time, long selfId, long userId, long messageId) {
        this.time = time;
        this.selfId = selfId;
        this.userId = userId;
        this.messageId = messageId;
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

    public long getMessageId() {
        return messageId;
    }
}
