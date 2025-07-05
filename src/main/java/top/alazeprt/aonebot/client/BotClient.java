package top.alazeprt.aonebot.client;

import org.slf4j.Logger;
import top.alazeprt.aonebot.action.Action;
import top.alazeprt.aonebot.action.GetAction;
import top.alazeprt.aonebot.event.Listener;

import java.util.function.Consumer;

public interface BotClient {
    void setLogger(Logger logger);
    void action(Action action);
    <T> void action(GetAction<T> action, Consumer<T> consumer);
    void registerEvent(Listener listener);
    void unregisterEvent(Listener listener);
}
