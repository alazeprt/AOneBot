package top.alazeprt.aonebot.event.notice;

import top.alazeprt.aonebot.event.Event;

public class PokeEvent extends Event {
    private final long time;
    private final long selfId;
    private final long userId;
    private final long groupId;
    private final long targetId;

    public PokeEvent(long time, long selfId, long userId, long groupId, long targetId) {
        this.time = time;
        this.selfId = selfId;
        this.userId = userId;
        this.groupId = groupId;
        this.targetId = targetId;
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

    public long getGroupId() {
        return groupId;
    }

    public long getTargetId() {
        return targetId;
    }
}
