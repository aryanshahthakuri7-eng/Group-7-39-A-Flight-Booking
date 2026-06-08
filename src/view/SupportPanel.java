package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class SupportPanel extends JPanel {
    private final Dashboard dashboard;
    
    // Chat components
    private JTextArea txtChatLog;
    private JTextField txtChatInput;
    private JComboBox<String> cmbCategory;
    private JTextField txtSubject;
    private JTextArea txtDescription;

    public SupportPanel(Dashboard dashboard) {
        this.dashboard = dashboard;
        initComponents();
        appendBotMessage("Hello! Welcome to Yatra Air Sewa Support. How can we help you today? Type 'baggage', 'refund', or 'change' for automated assistance.");
    }

    private void initComponents() {
        setBackground(new Color(248, 249, 255));
        setLayout(null);
        setSize(780, 630);

        // Back Button
        JLabel lblBack = new JLabel("← Back to Dashboard");
        lblBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBack.setForeground(new Color(245, 130, 32));
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBack.setBounds(30, 20, 150, 20);
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard.switchPanel(null); // Go to main dashboard homepage
            }
        });
        add(lblBack);

        // Header Title
        JLabel lblTitle = new JLabel("Customer Support");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(8, 22, 42));
        lblTitle.setBounds(30, 50, 300, 30);
        add(lblTitle);

        // Subtitle
        JLabel lblSub = new JLabel("Create support tickets or chat with our automated virtual agent.");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(113, 128, 150));
        lblSub.setBounds(30, 80, 500, 20);
        add(lblSub);

        // Left Panel: Create Support Ticket
        JPanel ticketPanel = new JPanel();
        ticketPanel.setBackground(Color.WHITE);
        ticketPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240)),
                " CREATE SUPPORT TICKET ",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 11),
                new Color(8, 22, 42)
        ));
        ticketPanel.setLayout(null);
        ticketPanel.setBounds(30, 120, 340, 470);

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCategory.setForeground(new Color(113, 128, 150));
        lblCategory.setBounds(20, 30, 150, 15);
        ticketPanel.add(lblCategory);

        String[] categories = {"Select Category", "Booking Issue", "Refund Status", "Baggage Claim", "General Inquiry"};
        cmbCategory = new JComboBox<>(categories);
        cmbCategory.setBounds(20, 50, 300, 32);
        ticketPanel.add(cmbCategory);

        JLabel lblSubject = new JLabel("Subject");
        lblSubject.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblSubject.setForeground(new Color(113, 128, 150));
        lblSubject.setBounds(20, 100, 150, 15);
        ticketPanel.add(lblSubject);

        txtSubject = new JTextField();
        txtSubject.setBounds(20, 120, 300, 32);
        ticketPanel.add(txtSubject);

        JLabel lblDesc = new JLabel("Detailed Description");
        lblDesc.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDesc.setForeground(new Color(113, 128, 150));
        lblDesc.setBounds(20, 170, 200, 15);
        ticketPanel.add(lblDesc);

        txtDescription = new JTextArea();
        txtDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        
        JScrollPane descScroll = new JScrollPane(txtDescription);
        descScroll.setBorder(BorderFactory.createLineBorder(new Color(206, 212, 218)));
        descScroll.setBounds(20, 190, 300, 140);
        ticketPanel.add(descScroll);

        JButton btnSubmit = new JButton("Submit Ticket");
        btnSubmit.setBackground(new Color(245, 130, 32)); // Orange
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBorderPainted(false);
        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSubmit.setBounds(20, 355, 300, 42);
        btnSubmit.addActionListener(e -> submitSupportTicket());
        ticketPanel.add(btnSubmit);

        // Emergency phone info
        JLabel lblHotline = new JLabel("📞 Toll-Free Hotline: 1660-01-77777");
        lblHotline.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblHotline.setForeground(new Color(8, 22, 42));
        lblHotline.setHorizontalAlignment(JLabel.CENTER);
        lblHotline.setBounds(20, 425, 300, 20);
        ticketPanel.add(lblHotline);

        add(ticketPanel);

        // Right Panel: Live Support Chat Simulation
        JPanel chatPanel = new JPanel();
        chatPanel.setBackground(Color.WHITE);
        chatPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240)),
                " LIVE CHAT ASSISTANT ",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 11),
                new Color(8, 22, 42)
        ));
        chatPanel.setLayout(null);
        chatPanel.setBounds(390, 120, 360, 470);

        // Chat Log Area
        txtChatLog = new JTextArea();
        txtChatLog.setEditable(false);
        txtChatLog.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtChatLog.setLineWrap(true);
        txtChatLog.setWrapStyleWord(true);
        txtChatLog.setBackground(new Color(250, 250, 252));

        JScrollPane chatScroll = new JScrollPane(txtChatLog);
        chatScroll.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        chatScroll.setBounds(15, 25, 330, 375);
        chatPanel.add(chatScroll);

        // Chat input textfield
        txtChatInput = new JTextField();
        txtChatInput.setBounds(15, 415, 245, 35);
        txtChatInput.addActionListener(e -> sendChatMessage());
        chatPanel.add(txtChatInput);

        // Send Button
        JButton btnSend = new JButton("Send");
        btnSend.setBackground(new Color(8, 22, 42)); // Navy
        btnSend.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnSend.setForeground(Color.WHITE);
        btnSend.setBorderPainted(false);
        btnSend.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSend.setBounds(265, 415, 80, 35);
        btnSend.addActionListener(e -> sendChatMessage());
        chatPanel.add(btnSend);

        add(chatPanel);
    }

    private void submitSupportTicket() {
        String category = (String) cmbCategory.getSelectedItem();
        String subject = txtSubject.getText().trim();
        String desc = txtDescription.getText().trim();

        if (category.equals("Select Category") || subject.isEmpty() || desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all ticket details.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Generate dynamic ticket ID
        int ticketNo = 1000 + (int)(Math.random() * 9000);
        String ticketId = "YAS-TK-" + ticketNo;

        JOptionPane.showMessageDialog(this, 
                "Support Ticket " + ticketId + " has been created successfully!\n" +
                "Our customer support desk will contact you via email (" +
                (dashboard.getCurrentUser() != null ? dashboard.getCurrentUser().getEmail() : "your registered email") +
                ") in 2 hours.", 
                "Ticket Submitted", 
                JOptionPane.INFORMATION_MESSAGE);

        // Clear ticket fields
        cmbCategory.setSelectedIndex(0);
        txtSubject.setText("");
        txtDescription.setText("");
    }

    private void sendChatMessage() {
        String userMsg = txtChatInput.getText().trim();
        if (userMsg.isEmpty()) return;

        appendUserMessage(userMsg);
        txtChatInput.setText("");

        // Simulate reply delay using a small Swing Timer
        Timer timer = new Timer(500, e -> {
            String lowercaseMsg = userMsg.toLowerCase();
            if (lowercaseMsg.contains("baggage") || lowercaseMsg.contains("luggage")) {
                appendBotMessage("Yatra Air allows 20kg check-in baggage and 7kg cabin hand carry baggage for all flights. Excess weight is charged at NPR 150 per kg.");
            } else if (lowercaseMsg.contains("refund") || lowercaseMsg.contains("cancel")) {
                appendBotMessage("Cancellations made 24 hours prior to departure receive a 90% refund. Cancellations inside 24 hours receive 50%. You can cancel bookings directly from your 'My Bookings' tab.");
            } else if (lowercaseMsg.contains("change") || lowercaseMsg.contains("reschedule")) {
                appendBotMessage("To change your flight, please raise a Support Ticket with your new preferred date or contact our hotline directly.");
            } else if (lowercaseMsg.contains("hello") || lowercaseMsg.contains("hi")) {
                appendBotMessage("Hi there! How can I assist you with your flight details today?");
            } else {
                appendBotMessage("Thank you for your inquiry. A live support agent will join this chat in a few moments. You can also dial our toll-free hotline: 1660-01-77777.");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void appendUserMessage(String msg) {
        txtChatLog.append("You: " + msg + "\n\n");
        txtChatLog.setCaretPosition(txtChatLog.getDocument().getLength());
    }

    private void appendBotMessage(String msg) {
        txtChatLog.append("Yatra Bot: " + msg + "\n\n");
        txtChatLog.setCaretPosition(txtChatLog.getDocument().getLength());
    }
}
