package top.alazeprt.aonebot.event.notice;

import top.alazeprt.aonebot.event.Event;

public class FriendAddEvent extends Event {
    private final long time;
    private final long selfId;
    private final long userId;

    public FriendAddEvent(long time, long selfId, long userId) {
        this.time = time;
        this.selfId = selfId;
        this.userId = userId;
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
}
