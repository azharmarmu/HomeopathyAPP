package com.example.azharuddin.homeopathyapp.model;

/**
 * Created by azharuddin on 12/3/18.
 */

public class ChatMessage {

    private String messageText;
    private UserType userType;
    private Status messageStatus;
    private long messageTime;

    public ChatMessage(String messageText, UserType userType, Status messageStatus, long messageTime) {
        this.messageText = messageText;
        this.userType = userType;
        this.messageStatus = messageStatus;
        this.messageTime = messageTime;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageText() {

        return messageText;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setMessageStatus(Status messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Status getMessageStatus() {
        return messageStatus;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public long getMessageTime() {
        return messageTime;
    }
}
