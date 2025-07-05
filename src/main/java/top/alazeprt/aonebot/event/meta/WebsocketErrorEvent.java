package top.alazeprt.aonebot.event.meta;

import top.alazeprt.aonebot.event.Event;

public class WebsocketErrorEvent extends Event {
    private final long time;
    private final Exception ex;

    public WebsocketErrorEvent(Exception ex) {
        this.time = System.currentTimeMillis();
        this.ex = ex;
    }

    public Exception getException() {
        return ex;
    }

    public long getTime() {
        return time;
    }
}
