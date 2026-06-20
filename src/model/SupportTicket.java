package model;

import java.sql.Timestamp;

/**
2:  * Model representing a Support Ticket.
3:  */
public class SupportTicket {

    private int ticketId;
    private int userId;
    private String subject;
    private String message;
    private String status;
    private Timestamp createdAt;

    public SupportTicket() {
    }

    public SupportTicket(int ticketId, int userId, String subject, String message, String status, Timestamp createdAt) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SupportTicket{" + "ticketId=" + ticketId + ", subject=" + subject + ", status=" + status + '}';
    }
}
