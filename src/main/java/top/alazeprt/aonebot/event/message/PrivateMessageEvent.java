package top.alazeprt.aonebot.event.message;

import com.google.gson.JsonArray;
import top.alazeprt.aonebot.event.Event;

public class PrivateMessageEvent extends Event {
    private final long time;
    private final PrivateMessageType type;
    private final long selfId;
    private final long messageId;
    private final JsonArray jsonMessage;
    private final String message;
    private final int font;
    private final long senderId;
    private final String senderNickName;

    public PrivateMessageEvent(long time, PrivateMessageType type, long selfId, long messageId, JsonArray jsonMessage, String message, int font, long senderId, String senderNickName) {
        this.time = time;
        this.type = type;
        this.selfId = selfId;
        this.messageId = messageId;
        this.jsonMessage = jsonMessage;
        this.message = message;
        this.font = font;
        this.senderId = senderId;
        this.senderNickName = senderNickName;
    }

    public long getTime() {
        return time;
    }

    public long getMessageId() {
        return messageId;
    }

    public PrivateMessageType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public int getFont() {
        return font;
    }

    public JsonArray getJsonMessage() {
        return jsonMessage;
    }

    public long getSelfId() {
        return selfId;
    }

    public long getSenderId() {
        return senderId;
    }

    public String getSenderNickName() {
        return senderNickName;
    }
}
