package top.alazeprt.aonebot.client;

import top.alazeprt.aonebot.action.Action;
import top.alazeprt.aonebot.action.GetAction;
import top.alazeprt.aonebot.event.Listener;

import java.util.function.Consumer;

public interface BotClient {
    void action(Action action);
    <T> void action(GetAction<T> action, Consumer<T> consumer);
    void registerEvent(Listener listener);
}
