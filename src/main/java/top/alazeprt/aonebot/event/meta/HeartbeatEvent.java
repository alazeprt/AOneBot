package top.alazeprt.aonebot.event.meta;

import top.alazeprt.aonebot.event.Event;

public class HeartbeatEvent extends Event {
    private final long time;
    private final boolean online;
    private final boolean good;

    public HeartbeatEvent(long time, boolean online, boolean good) {
        this.time = time;
        this.online = online;
        this.good = good;
    }

    public boolean isGood() {
        return good;
    }

    public boolean isOnline() {
        return online;
    }

    public long getTime() {
        return time;
    }
}
