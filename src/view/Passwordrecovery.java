/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Controller.UserController;
import javax.swing.JOptionPane;

public class Passwordrecovery extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Passwordrecovery.class.getName());

    /**
     * Creates new form Passwordrecovery
     */
    public Passwordrecovery() {
        initComponents();
        setupCustomListeners();
    }

    private void setupCustomListeners() {
        jLabelLoginLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelLoginLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new SignIn().setVisible(true);
                dispose();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        jPanelForm = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelNewPassword = new javax.swing.JLabel();
        jPasswordFieldNewPassword = new javax.swing.JPasswordField();
        jLabelConfirmPassword = new javax.swing.JLabel();
        jPasswordFieldConfirmPassword = new javax.swing.JPasswordField();
        jButtonReset = new javax.swing.JButton();
        jLabelLoginLink = new javax.swing.JLabel();
        jLabelBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Flight Booking System - Password Recovery");
        getContentPane().setLayout(null);

        // Form Panel
        jPanelForm.setBackground(new java.awt.Color(204, 255, 255));
        jPanelForm.setLayout(null);

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabelTitle.setText("Reset Password");
        jPanelForm.add(jLabelTitle);
        jLabelTitle.setBounds(80, 15, 160, 27);

        jLabelEmail.setText("Email:");
        jPanelForm.add(jLabelEmail);
        jLabelEmail.setBounds(20, 55, 80, 16);
        jPanelForm.add(jTextFieldEmail);
        jTextFieldEmail.setBounds(20, 75, 270, 26);

        jLabelNewPassword.setText("New Password:");
        jPanelForm.add(jLabelNewPassword);
        jLabelNewPassword.setBounds(20, 110, 100, 16);
        jPanelForm.add(jPasswordFieldNewPassword);
        jPasswordFieldNewPassword.setBounds(20, 130, 270, 26);

        jLabelConfirmPassword.setText("Confirm Password:");
        jPanelForm.add(jLabelConfirmPassword);
        jLabelConfirmPassword.setBounds(20, 165, 120, 16);
        jPanelForm.add(jPasswordFieldConfirmPassword);
        jPasswordFieldConfirmPassword.setBounds(20, 185, 270, 26);

        jButtonReset.setBackground(new java.awt.Color(50, 153, 229));
        jButtonReset.setForeground(new java.awt.Color(255, 255, 255));
        jButtonReset.setText("Reset Password");
        jButtonReset.addActionListener(this::jButtonResetActionPerformed);
        jPanelForm.add(jButtonReset);
        jButtonReset.setBounds(20, 230, 270, 30);

        jLabelLoginLink.setForeground(new java.awt.Color(0, 153, 153));
        jLabelLoginLink.setText("Back to Login");
        jPanelForm.add(jLabelLoginLink);
        jLabelLoginLink.setBounds(115, 280, 90, 16);

        getContentPane().add(jPanelForm);
        jPanelForm.setBounds(340, 30, 310, 330);

        // Fallback solid background color if image is not loaded
        getContentPane().setBackground(new java.awt.Color(0, 153, 153));

        // Try to load background image
        try {
            java.net.URL bgUrl = getClass().getResource("/vuew/sussssu.png");
            if (bgUrl != null) {
                jLabelBackground.setIcon(new javax.swing.ImageIcon(bgUrl));
            }
        } catch (Exception e) {
            // Keep solid color
        }
        getContentPane().add(jLabelBackground);
        jLabelBackground.setBounds(0, 0, 670, 410);

        // Set size & center
        setPreferredSize(new java.awt.Dimension(685, 450));
        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {
        String email = jTextFieldEmail.getText();
        String newPassword = new String(jPasswordFieldNewPassword.getPassword());
        String confirmPassword = new String(jPasswordFieldConfirmPassword.getPassword());

        if (email.trim().isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserController controller = new UserController();
        boolean success = controller.recoverPassword(email, newPassword);

        if (success) {
            JOptionPane.showMessageDialog(this, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new SignIn().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update password. Make sure the email is correct.", "Reset Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Passwordrecovery().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton jButtonReset;
    private javax.swing.JLabel jLabelBackground;
    private javax.swing.JLabel jLabelConfirmPassword;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelLoginLink;
    private javax.swing.JLabel jLabelNewPassword;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JPasswordField jPasswordFieldConfirmPassword;
    private javax.swing.JPasswordField jPasswordFieldNewPassword;
    private javax.swing.JTextField jTextFieldEmail;
    // End of variables declaration
}
