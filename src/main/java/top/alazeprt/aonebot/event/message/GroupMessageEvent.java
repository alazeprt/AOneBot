package top.alazeprt.aonebot.event.message;

import com.google.gson.JsonArray;
import top.alazeprt.aonebot.event.Event;

public class GroupMessageEvent extends Event {
    private final long time;
    private final GroupMessageType type;
    private final long selfId;
    private final long messageId;
    private final long groupId;
    private final long anonymousId;
    private final String anonymousName;
    private final JsonArray jsonMessage;
    private final String message;
    private final int font;
    private final long senderId;
    private final String senderNickName;

    public GroupMessageEvent(long time, GroupMessageType type, long selfId, long messageId, long groupId, long anonymousId, String anonymousName, JsonArray jsonMessage, String message, int font, long senderId, String senderNickName) {
        this.time = time;
        this.type = type;
        this.selfId = selfId;
        this.messageId = messageId;
        this.groupId = groupId;
        this.anonymousId = anonymousId;
        this.anonymousName = anonymousName;
        this.jsonMessage = jsonMessage;
        this.message = message;
        this.font = font;
        this.senderId = senderId;
        this.senderNickName = senderNickName;
    }

    public String getSenderNickName() {
        return senderNickName;
    }

    public long getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }

    public long getMessageId() {
        return messageId;
    }

    public long getTime() {
        return time;
    }

    public long getSelfId() {
        return selfId;
    }

    public int getFont() {
        return font;
    }

    public long getGroupId() {
        return groupId;
    }

    public GroupMessageType getType() {
        return type;
    }

    public JsonArray getJsonMessage() {
        return jsonMessage;
    }

    public String getAnonymousName() {
        return anonymousName;
    }

    public long getAnonymousId() {
        return anonymousId;
    }
}
