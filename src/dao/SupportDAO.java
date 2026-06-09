package dao;

import database.DatabaseConnection;
import model.SupportTicket;
import model.SupportMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for support_tickets and support_messages tables.
 */
public class SupportDAO {

    public boolean insertTicket(SupportTicket ticket) {
        String sql = "INSERT INTO support_tickets (user_id, subject, message, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, ticket.getUserId());
            pstmt.setString(2, ticket.getSubject());
            pstmt.setString(3, ticket.getMessage());
            pstmt.setString(4, ticket.getStatus() != null ? ticket.getStatus() : "OPEN");

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        ticket.setTicketId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error inserting support ticket: " + e.getMessage());
        }
        return false;
    }

    public List<SupportTicket> getTicketsByUserId(int userId) {
        List<SupportTicket> list = new ArrayList<>();
        String sql = "SELECT * FROM support_tickets WHERE user_id = ? ORDER BY created_at DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SupportTicket ticket = new SupportTicket();
                    ticket.setTicketId(rs.getInt("ticket_id"));
                    ticket.setUserId(rs.getInt("user_id"));
                    ticket.setSubject(rs.getString("subject"));
                    ticket.setMessage(rs.getString("message"));
                    ticket.setStatus(rs.getString("status"));
                    ticket.setCreatedAt(rs.getTimestamp("created_at"));
                    list.add(ticket);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching tickets by user id: " + e.getMessage());
        }
        return list;
    }

    public boolean insertMessage(SupportMessage msg) {
        String sql = "INSERT INTO support_messages (ticket_id, user_id, message, sender) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (msg.getTicketId() != null) {
                pstmt.setInt(1, msg.getTicketId());
            } else {
                pstmt.setNull(1, Types.INTEGER);
            }
            pstmt.setInt(2, msg.getUserId());
            pstmt.setString(3, msg.getMessage());
            pstmt.setString(4, msg.getSender());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        msg.setMessageId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error inserting support message: " + e.getMessage());
        }
        return false;
    }

    public List<SupportMessage> getMessagesByTicketId(int ticketId) {
        List<SupportMessage> list = new ArrayList<>();
        String sql = "SELECT * FROM support_messages WHERE ticket_id = ? ORDER BY created_at ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ticketId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SupportMessage msg = new SupportMessage();
                    msg.setMessageId(rs.getInt("message_id"));
                    if (rs.getObject("ticket_id") != null) {
                        msg.setTicketId(rs.getInt("ticket_id"));
                    }
                    msg.setUserId(rs.getInt("user_id"));
                    msg.setMessage(rs.getString("message"));
                    msg.setSender(rs.getString("sender"));
                    msg.setCreatedAt(rs.getTimestamp("created_at"));
                    list.add(msg);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching messages by ticket id: " + e.getMessage());
        }
        return list;
    }

    public List<SupportMessage> getLiveChatMessagesByUserId(int userId) {
        List<SupportMessage> list = new ArrayList<>();
        String sql = "SELECT * FROM support_messages WHERE user_id = ? AND ticket_id IS NULL ORDER BY created_at ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    SupportMessage msg = new SupportMessage();
                    msg.setMessageId(rs.getInt("message_id"));
                    msg.setUserId(rs.getInt("user_id"));
                    msg.setMessage(rs.getString("message"));
                    msg.setSender(rs.getString("sender"));
                    msg.setCreatedAt(rs.getTimestamp("created_at"));
                    list.add(msg);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching live chat messages: " + e.getMessage());
        }
        return list;
    }
}
