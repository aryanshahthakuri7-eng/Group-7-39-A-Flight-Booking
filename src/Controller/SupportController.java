package controller;

import dao.SupportDAO;
import model.SupportTicket;
import model.SupportMessage;
import model.SessionManager;
import model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller to handle customer support ticketing and live chat logic.
 */
public class SupportController {

    private final SupportDAO supportDAO;

    public SupportController() {
        this.supportDAO = new SupportDAO();
    }

    /**
     * Creates a new support ticket in the database.
     */
    public boolean createTicket(String subject, String message) {
        User user = SessionManager.getCurrentUser();
        if (user == null || subject == null || subject.trim().isEmpty() || message == null || message.trim().isEmpty()) {
            return false;
        }

        SupportTicket ticket = new SupportTicket();
        ticket.setUserId(user.getUserId());
        ticket.setSubject(subject.trim());
        ticket.setMessage(message.trim());
        ticket.setStatus("OPEN");

        return supportDAO.insertTicket(ticket);
    }

    /**
     * Retrieves all support tickets of the logged-in user.
     */
    public ArrayList<SupportTicket> getTickets() {
        User user = SessionManager.getCurrentUser();
        if (user == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(supportDAO.getTicketsByUserId(user.getUserId()));
    }

    /**
     * Sends a message in live chat and simulates an agent response.
     */
    public boolean sendLiveChatMessage(String text) {
        User user = SessionManager.getCurrentUser();
        if (user == null || text == null || text.trim().isEmpty()) {
            return false;
        }

        // 1. Save User Message to database
        SupportMessage userMsg = new SupportMessage();
        userMsg.setTicketId(null);
        userMsg.setUserId(user.getUserId());
        userMsg.setMessage(text.trim());
        userMsg.setSender("USER");
        boolean success = supportDAO.insertMessage(userMsg);

        if (success) {
            // 2. Generate and Save simulated Agent Response to database
            String agentReply = generateAgentReply(text);
            SupportMessage agentMsg = new SupportMessage();
            agentMsg.setTicketId(null);
            agentMsg.setUserId(user.getUserId());
            agentMsg.setMessage(agentReply);
            agentMsg.setSender("AGENT");
            supportDAO.insertMessage(agentMsg);
        }

        return success;
    }

    /**
     * Retrieves live chat history for the logged-in user.
     */
    public ArrayList<SupportMessage> getLiveChatHistory() {
        User user = SessionManager.getCurrentUser();
        if (user == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(supportDAO.getLiveChatMessagesByUserId(user.getUserId()));
    }

    /**
     * Sends a reply message on a specific support ticket.
     */
    public boolean sendTicketMessage(int ticketId, String text) {
        User user = SessionManager.getCurrentUser();
        if (user == null || text == null || text.trim().isEmpty()) {
            return false;
        }

        SupportMessage userReply = new SupportMessage();
        userReply.setTicketId(ticketId);
        userReply.setUserId(user.getUserId());
        userReply.setMessage(text.trim());
        userReply.setSender("USER");
        
        boolean success = supportDAO.insertMessage(userReply);
        
        if (success) {
            // Simulate agent response for ticket
            String agentReply = "We have received your update regarding this ticket. Our support team is reviewing the details and will update you shortly.";
            SupportMessage agentReplyMsg = new SupportMessage();
            agentReplyMsg.setTicketId(ticketId);
            agentReplyMsg.setUserId(user.getUserId());
            agentReplyMsg.setMessage(agentReply);
            agentReplyMsg.setSender("AGENT");
            supportDAO.insertMessage(agentReplyMsg);
        }
        
        return success;
    }

    /**
     * Fetches all message dialogue on a support ticket.
     */
    public ArrayList<SupportMessage> getTicketMessages(int ticketId) {
        return new ArrayList<>(supportDAO.getMessagesByTicketId(ticketId));
    }

    /**
     * Local intelligence helper to simulate support agent dialogue.
     */
    private String generateAgentReply(String userText) {
        String input = userText.toLowerCase();
        if (input.contains("baggage") || input.contains("luggage")) {
            return "For Yatra Air Sewa domestic flights, you are allowed up to 20kg checked baggage and 7kg cabin carry-on. Extra baggage can be purchased at the airport counter.";
        } else if (input.contains("cancel") || input.contains("refund")) {
            return "You can cancel any confirmed flight under 'My Bookings'. Refunds are processed automatically and credited back to your payment method (eSewa, Khalti, Card) in 3-5 business days.";
        } else if (input.contains("seat") || input.contains("change seat")) {
            return "Seats can be selected at the time of flight booking. To change your seat, please cancel your booking and rebook, or contact our support counter at the airport terminal.";
        } else if (input.contains("check-in") || input.contains("boarding pass") || input.contains("CheckIn")) {
            return "Online check-in opens 24 hours prior to departure. You can perform check-in and generate your boarding pass under the 'Check-in' tab in the main sidebar menu.";
        } else if (input.contains("hello") || input.contains("hi") || input.contains("hey")) {
            return "Hello! Thank you for choosing Yatra Air Sewa. How can I help you with your flight booking or ticket information today?";
        } else {
            return "Thank you for message. I have logged your query about: \"" + userText + "\". A support officer will review this and respond to you directly.";
        }
    }
}
