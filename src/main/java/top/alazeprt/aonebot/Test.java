package top.alazeprt.aonebot;

import top.alazeprt.aonebot.action.GetGroupMemberInfo;
import top.alazeprt.aonebot.client.websocket.WebsocketBotClient;
import top.alazeprt.aonebot.event.Listener;
import top.alazeprt.aonebot.event.SubscribeBotEvent;
import top.alazeprt.aonebot.event.message.GroupMessageEvent;
import top.alazeprt.aonebot.result.GroupMember;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.function.Consumer;

public class Test implements Listener {
    public static void main(String[] args) throws URISyntaxException {
        WebsocketBotClient client = new WebsocketBotClient("localhost", 3001, "abcdef");
        client.connect();
        client.registerEvent(new Test());
        client.action(new GetGroupMemberInfo(721509831L, 431876428L), groupMember -> {
            System.out.println(groupMember.getCard());
        });
    }

    @SubscribeBotEvent
    public void onGroupMessage(GroupMessageEvent event) {
        System.out.println(event.getMessage());
    }
}
