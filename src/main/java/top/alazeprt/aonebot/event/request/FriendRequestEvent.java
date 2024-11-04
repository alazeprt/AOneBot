package top.alazeprt.aonebot.event.request;

import top.alazeprt.aonebot.event.Event;

public class FriendRequestEvent extends Event {
    private final long time;
    private final long selfId;
    private final long userId;
    private final String comment;
    private final String flag;

    public FriendRequestEvent(long time, long selfId, long userId, String comment, String flag) {
        this.time = time;
        this.selfId = selfId;
        this.userId = userId;
        this.comment = comment;
        this.flag = flag;
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
}
