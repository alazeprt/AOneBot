package top.alazeprt.aonebot.event.notice;

import top.alazeprt.aonebot.event.Event;

public class GroupUploadEvent extends Event {
    private final long time;
    private final long selfId;
    private final long userId;
    private final long groupId;
    private final String fileId;
    private final String fileName;
    private final long fileSize;


    public GroupUploadEvent(long time, long selfId, long userId, long groupId, String fileId, String fileName, long fileSize) {
        this.time = time;
        this.selfId = selfId;
        this.userId = userId;
        this.groupId = groupId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getUserId() {
        return userId;
    }

    public long getSelfId() {
        return selfId;
    }

    public long getTime() {
        return time;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }
}
