package com.example.chatapp;

public class msgModelclass {
    private String message;
    private String senderid;
    private String messageId;
    private boolean isImage;
    private long timeStamp;

    public msgModelclass() {
    }

    public msgModelclass(String message, String senderid, long timeStamp, boolean isImage) {
        this.message = message;
        this.senderid = senderid;
        this.timeStamp = timeStamp;
        this.isImage = isImage;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean isImage) {
        this.isImage = isImage;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
