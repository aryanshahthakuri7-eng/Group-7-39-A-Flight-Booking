package model;

import java.sql.Timestamp;

/**
 * Model representing a single Message in support tickets or live chat.
 */
public class SupportMessage {

    private int messageId;
    private Integer ticketId; // null for live chat
    private int userId;
    private String message;
    private String sender; // 'USER' or 'AGENT'
    private Timestamp createdAt;

    public SupportMessage() {
    }

    public SupportMessage(int messageId, Integer ticketId, int userId, String message, String sender, Timestamp createdAt) {
        this.messageId = messageId;
        this.ticketId = ticketId;
        this.userId = userId;
        this.message = message;
        this.sender = sender;
        this.createdAt = createdAt;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SupportMessage{" + "messageId=" + messageId + ", sender=" + sender + ", message=" + message + '}';
    }
}
