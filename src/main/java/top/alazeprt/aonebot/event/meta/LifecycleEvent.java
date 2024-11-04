package top.alazeprt.aonebot.event.meta;

import top.alazeprt.aonebot.event.Event;

public class LifecycleEvent extends Event {
    private final long time;
    private final LifecycleType type;

    public LifecycleEvent(long time, LifecycleType type) {
        this.time = time;
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public LifecycleType getType() {
        return type;
    }
}
