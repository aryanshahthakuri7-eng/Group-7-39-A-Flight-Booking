package view;

import controller.SupportController;
import model.SupportTicket;
import model.SupportMessage;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * View class representing the Support Tickets and messaging log.
 * Integrates with NetBeans GUI Builder components.
 */
public class supporttickets extends javax.swing.JFrame {

    private final SupportController supportController;
    private ArrayList<SupportTicket> ticketList;
    private SupportTicket selectedTicket = null;

    public supporttickets() {
        initComponents();
        supportController = new SupportController();
        
        getContentPane().setBackground(new java.awt.Color(20, 28, 35));
        
        // Dynamic styles
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSendReply.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        styleTextField(txtSubject);
        styleTextField(txtMessage);
        styleTextField(txtReply);

        // Load data
        refreshTicketsTable();

        // Wire JTable selection listener
        tblTickets.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int idx = tblTickets.getSelectedRow();
                    if (idx >= 0 && idx < ticketList.size()) {
                        selectedTicket = ticketList.get(idx);
                        loadTicketMessages(selectedTicket);
                    }
                }
            }
        });

        // Wire Actions
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnSendReply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendReplyActionPerformed(evt);
            }
        });
    }

    private void styleTextField(javax.swing.JTextField txt) {
        txt.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void refreshTicketsTable() {
        ticketList = supportController.getTickets();
        DefaultTableModel model = (DefaultTableModel) tblTickets.getModel();
        model.setRowCount(0);

        for (SupportTicket t : ticketList) {
            model.addRow(new Object[]{
                t.getTicketId(),
                t.getSubject(),
                t.getStatus(),
                t.getCreatedAt() != null ? t.getCreatedAt().toString() : "N/A"
            });
        }
        
        selectedTicket = null;
        lblSelectedTicket.setText("Select a ticket from the left list.");
        txtReplies.setText("");
    }

    private void loadTicketMessages(SupportTicket ticket) {
        lblSelectedTicket.setText("Ticket ID: " + ticket.getTicketId() + " - " + ticket.getSubject());
        
        ArrayList<SupportMessage> msgs = supportController.getTicketMessages(ticket.getTicketId());
        StringBuilder sb = new StringBuilder();
        
        // Show ticket original message
        sb.append("[USER (Ticket description)]\n");
        sb.append(ticket.getMessage()).append("\n\n");
        
        for (SupportMessage m : msgs) {
            sb.append("[").append(m.getSender()).append("]\n");
            sb.append(m.getMessage()).append("\n\n");
        }
        
        txtReplies.setText(sb.toString());
        txtReplies.setCaretPosition(txtReplies.getDocument().getLength());
    }

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {
        String subject = txtSubject.getText().trim();
        String desc = txtMessage.getText().trim();

        if (subject.isEmpty() || desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Subject and Description cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = supportController.createTicket(subject, desc);
        if (success) {
            JOptionPane.showMessageDialog(this, "Support Ticket submitted successfully. Our help desk has been notified.", "Ticket Submitted", JOptionPane.INFORMATION_MESSAGE);
            txtSubject.setText("");
            txtMessage.setText("");
            refreshTicketsTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to submit ticket. Check database server connection.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnSendReplyActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectedTicket == null) {
            JOptionPane.showMessageDialog(this, "Please select a ticket from the list to reply.", "No Ticket Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String replyText = txtReply.getText().trim();
        if (replyText.isEmpty()) {
            return;
        }

        boolean success = supportController.sendTicketMessage(selectedTicket.getTicketId(), replyText);
        if (success) {
            txtReply.setText("");
            loadTicketMessages(selectedTicket);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to send message.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHeader = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTickets = new javax.swing.JTable();
        pnlNewTicket = new javax.swing.JPanel();
        lblSubject = new javax.swing.JLabel();
        txtSubject = new javax.swing.JTextField();
        lblMessage = new javax.swing.JLabel();
        txtMessage = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        pnlTicketDetails = new javax.swing.JPanel();
        lblSelectedTicket = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtReplies = new javax.swing.JTextArea();
        txtReply = new javax.swing.JTextField();
        btnSendReply = new javax.swing.JButton();
        pnlBg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Yatra Air Sewa - Support Tickets");
        setPreferredSize(new java.awt.Dimension(900, 650));
        getContentPane().setLayout(null);

        lblHeader.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setText("Yatra Air Sewa - Support Ticket Center");
        getContentPane().add(lblHeader);
        lblHeader.setBounds(30, 20, 450, 30);

        tblTickets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Ticket ID", "Subject", "Status", "Created At"
            }
        ) {
            boolean[] editables = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return editables [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTickets);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 70, 400, 200);

        pnlNewTicket.setBackground(new java.awt.Color(255, 255, 255));
        pnlNewTicket.setBorder(javax.swing.BorderFactory.createTitledBorder("Submit a New Ticket"));
        pnlNewTicket.setLayout(null);

        lblSubject.setText("Subject:");
        pnlNewTicket.add(lblSubject);
        lblSubject.setBounds(20, 30, 80, 20);
        pnlNewTicket.add(txtSubject);
        txtSubject.setBounds(20, 55, 360, 30);

        lblMessage.setText("Description:");
        pnlNewTicket.add(lblMessage);
        lblMessage.setBounds(20, 95, 100, 20);
        pnlNewTicket.add(txtMessage);
        txtMessage.setBounds(20, 120, 360, 90);

        btnSubmit.setBackground(new java.awt.Color(11, 60, 93));
        btnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        btnSubmit.setText("Submit Ticket");
        pnlNewTicket.add(btnSubmit);
        btnSubmit.setBounds(20, 225, 360, 35);

        getContentPane().add(pnlNewTicket);
        pnlNewTicket.setBounds(30, 290, 400, 280);

        pnlTicketDetails.setBackground(new java.awt.Color(255, 255, 255));
        pnlTicketDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Ticket Conversation & Detail"));
        pnlTicketDetails.setLayout(null);

        lblSelectedTicket.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblSelectedTicket.setText("Select a ticket from the left list.");
        pnlTicketDetails.add(lblSelectedTicket);
        lblSelectedTicket.setBounds(20, 30, 370, 20);

        txtReplies.setEditable(false);
        txtReplies.setColumns(20);
        txtReplies.setRows(5);
        txtReplies.setWrapStyleWord(true);
        txtReplies.setLineWrap(true);
        jScrollPane2.setViewportView(txtReplies);

        pnlTicketDetails.add(jScrollPane2);
        jScrollPane2.setBounds(20, 60, 370, 340);
        pnlTicketDetails.add(txtReply);
        txtReply.setBounds(20, 415, 260, 35);

        btnSendReply.setBackground(new java.awt.Color(26, 115, 232));
        btnSendReply.setForeground(new java.awt.Color(255, 255, 255));
        btnSendReply.setText("Send");
        pnlTicketDetails.add(btnSendReply);
        btnSendReply.setBounds(290, 415, 100, 35);

        getContentPane().add(pnlTicketDetails);
        pnlTicketDetails.setBounds(460, 70, 410, 500);

        pnlBg.setBackground(new java.awt.Color(20, 28, 35));
        pnlBg.setLayout(null);
        getContentPane().add(pnlBg);
        pnlBg.setBounds(0, 0, 900, 650);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendReply;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblSelectedTicket;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JPanel pnlNewTicket;
    private javax.swing.JPanel pnlTicketDetails;
    private javax.swing.JTable tblTickets;
    private javax.swing.JTextField txtMessage;
    private javax.swing.JTextArea txtReplies;
    private javax.swing.JTextField txtReply;
    private javax.swing.JTextField txtSubject;
    // End of variables declaration//GEN-END:variables
}
