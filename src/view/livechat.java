package view;

import controller.SupportController;
import model.SupportMessage;
import java.util.ArrayList;

/**
 * View class representing the Live Support Chat interface.
 * Integrates with NetBeans GUI Builder components.
 */
public class livechat extends javax.swing.JFrame {

    private final SupportController supportController;

    public livechat() {
        initComponents();
        supportController = new SupportController();

        getContentPane().setBackground(new java.awt.Color(20, 28, 35));
        btnSend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        styleTextField(txtMessageInput);

        // Load chat history
        loadChatHistory();

        // Wire Action Listeners
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtMessageInput.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnSendActionPerformed(null);
                }
            }
        });
    }

    private void styleTextField(javax.swing.JTextField txt) {
        txt.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void loadChatHistory() {
        ArrayList<SupportMessage> history = supportController.getLiveChatHistory();
        StringBuilder sb = new StringBuilder();
        
        if (history.isEmpty()) {
            sb.append("[AGENT]\n");
            sb.append("Hello! Welcome to Yatra Air Sewa Live Support. How can we help you with your flight details today?\n\n");
        } else {
            for (SupportMessage m : history) {
                sb.append("[").append(m.getSender()).append("]\n");
                sb.append(m.getMessage()).append("\n\n");
            }
        }
        
        txtChatHistory.setText(sb.toString());
        txtChatHistory.setCaretPosition(txtChatHistory.getDocument().getLength());
    }

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {
        String msg = txtMessageInput.getText().trim();
        if (msg.isEmpty()) {
            return;
        }

        boolean success = supportController.sendLiveChatMessage(msg);
        if (success) {
            txtMessageInput.setText("");
            loadChatHistory();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHeader = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChatHistory = new javax.swing.JTextArea();
        txtMessageInput = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        pnlBg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Yatra Air Sewa - Live Support Chat");
        setPreferredSize(new java.awt.Dimension(450, 600));
        getContentPane().setLayout(null);

        lblHeader.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(255, 255, 255));
        lblHeader.setText("YATRAAIR Support Chat");
        getContentPane().add(lblHeader);
        lblHeader.setBounds(20, 15, 300, 25);

        lblStatus.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(16, 185, 129));
        lblStatus.setText("● Online Now");
        getContentPane().add(lblStatus);
        lblStatus.setBounds(20, 40, 100, 15);

        txtChatHistory.setColumns(20);
        txtChatHistory.setRows(5);
        txtChatHistory.setEditable(false);
        txtChatHistory.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jScrollPane1.setViewportView(txtChatHistory);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 70, 410, 420);
        getContentPane().add(txtMessageInput);
        txtMessageInput.setBounds(20, 505, 300, 40);

        btnSend.setBackground(new java.awt.Color(255, 122, 26));
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnSend.setText("Send");
        getContentPane().add(btnSend);
        btnSend.setBounds(330, 505, 100, 40);

        pnlBg.setBackground(new java.awt.Color(20, 28, 35));
        getContentPane().add(pnlBg);
        pnlBg.setBounds(0, 0, 450, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtChatHistory;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JTextField txtMessageInput;
    // End of variables declaration//GEN-END:variables
}
