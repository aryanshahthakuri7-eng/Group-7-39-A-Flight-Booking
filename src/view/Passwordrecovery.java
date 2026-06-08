package view;

import Controller.UserController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Passwordrecovery extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Passwordrecovery.class.getName());

    // Transient OTP and User Email variables for session verification
    private String generatedOTP;
    private String userEmail;

    /**
     * Creates new form Passwordrecovery
     */
    public Passwordrecovery() {
        initComponents();
        setupCustomListeners();
        
        // Initial Step Configuration
        jPanelStep1.setVisible(true);
        jPanelStep2.setVisible(false);
    }

    private void setupCustomListeners() {
        // Navigation links hover and click listeners
        setHandCursorAndHover(jLabelBackHome);
        setHandCursorAndHover(jLabelBackLogin);
        
        jButtonSendReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonVerifyReset.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Click to navigate back to Home
        jLabelBackHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(Passwordrecovery.this, "Returning to Home page...", "Home", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Click to navigate back to Login
        jLabelBackLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                new SignIn().setVisible(true);
                dispose();
            }
        });

        // Setup Placeholders for all input fields
        setupPlaceholder(jTextFieldEmail, "user@example.com");
        setupPlaceholder(jTextFieldOTP, "Verification Code");
        setupPlaceholder(jPasswordFieldNew, "New Password");
        setupPlaceholder(jPasswordFieldConfirm, "Confirm Password");
    }

    private void setHandCursorAndHover(javax.swing.JLabel label) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setFont(new Font("Segoe UI", Font.BOLD, label.getFont().getSize()));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                label.setFont(new Font("Segoe UI", Font.PLAIN, label.getFont().getSize()));
            }
        });
    }

    private void setupPlaceholder(javax.swing.JTextField field, String placeholder) {
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String text = field.getText();
                if (text.equals(placeholder)) {
                    field.setText("");
                    field.setForeground(new Color(51, 51, 51));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = field.getText().trim();
                if (text.isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(new Color(153, 153, 153));
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCard = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jLabelBackHome = new javax.swing.JLabel();
        jLabelHeaderTitle = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelBrandTitle = new javax.swing.JLabel();
        jPanelFormContainer = new javax.swing.JPanel();
        jPanelStep1 = new javax.swing.JPanel();
        jLabelResetTitle = new javax.swing.JLabel();
        jLabelResetDesc = new javax.swing.JLabel();
        jLabelEmailLabel = new javax.swing.JLabel();
        jPanelEmailInput = new javax.swing.JPanel();
        jLabelEmailIcon = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jButtonSendReset = new javax.swing.JButton();
        jPanelStep2 = new javax.swing.JPanel();
        jLabelResetTitle2 = new javax.swing.JLabel();
        jLabelResetDesc2 = new javax.swing.JLabel();
        jPanelOTP = new javax.swing.JPanel();
        jLabelOTPIcon = new javax.swing.JLabel();
        jTextFieldOTP = new javax.swing.JTextField();
        jPanelNewPassword = new javax.swing.JPanel();
        jLabelLockIcon1 = new javax.swing.JLabel();
        jPasswordFieldNew = new javax.swing.JPasswordField();
        jPanelConfirmPassword = new javax.swing.JPanel();
        jLabelLockIcon2 = new javax.swing.JLabel();
        jPasswordFieldConfirm = new javax.swing.JPasswordField();
        jButtonVerifyReset = new javax.swing.JButton();
        jLabelBackLogin = new javax.swing.JLabel();
        jPanelFooter = new javax.swing.JPanel();
        jLabelFooterStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("YATRA AIR SEWA - Password Recovery");
        getContentPane().setLayout(null);

        // Frame Background
        getContentPane().setBackground(new java.awt.Color(15, 37, 55));

        jPanelCard.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCard.setLayout(null);

        jPanelHeader.setBackground(new java.awt.Color(30, 44, 59));
        jPanelHeader.setLayout(null);

        jLabelBackHome.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabelBackHome.setForeground(new java.awt.Color(204, 204, 204));
        jLabelBackHome.setText("⌂ Back to Home");
        jPanelHeader.add(jLabelBackHome);
        jLabelBackHome.setBounds(15, 10, 150, 15);

        jLabelHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelHeaderTitle.setForeground(new java.awt.Color(117, 140, 179));
        jLabelHeaderTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelHeaderTitle.setText("YATRA AIR SEWA");
        jPanelHeader.add(jLabelHeaderTitle);
        jLabelHeaderTitle.setBounds(430, 10, 135, 15);

        jPanelCard.add(jPanelHeader);
        jPanelHeader.setBounds(0, 0, 580, 35);

        jLabelLogo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelLogo.setForeground(new java.awt.Color(15, 37, 55));
        jLabelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogo.setText("▼");
        jPanelCard.add(jLabelLogo);
        jLabelLogo.setBounds(215, 70, 30, 30);

        jLabelBrandTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabelBrandTitle.setForeground(new java.awt.Color(15, 37, 55));
        jLabelBrandTitle.setText("YATRA AIR SEWA");
        jPanelCard.add(jLabelBrandTitle);
        jLabelBrandTitle.setBounds(250, 70, 180, 30);

        jPanelFormContainer.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFormContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        jPanelFormContainer.setLayout(null);

        jPanelStep1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStep1.setLayout(null);

        jLabelResetTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabelResetTitle.setForeground(new java.awt.Color(15, 37, 55));
        jLabelResetTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResetTitle.setText("Reset Your Password");
        jPanelStep1.add(jLabelResetTitle);
        jLabelResetTitle.setBounds(20, 20, 420, 25);

        jLabelResetDesc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabelResetDesc.setForeground(new java.awt.Color(113, 128, 150));
        jLabelResetDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResetDesc.setText("<html><body style='text-align: center;'>Enter your registered email address and we will send you<br>instructions to reset your password.</body></html>");
        jPanelStep1.add(jLabelResetDesc);
        jLabelResetDesc.setBounds(20, 50, 420, 40);

        jLabelEmailLabel.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelEmailLabel.setForeground(new java.awt.Color(160, 174, 192));
        jLabelEmailLabel.setText("EMAIL ADDRESS");
        jPanelStep1.add(jLabelEmailLabel);
        jLabelEmailLabel.setBounds(40, 105, 380, 15);

        jPanelEmailInput.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmailInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 224)));
        jPanelEmailInput.setLayout(null);

        jLabelEmailIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelEmailIcon.setForeground(new java.awt.Color(160, 174, 192));
        jLabelEmailIcon.setText("  ✉ ");
        jPanelEmailInput.add(jLabelEmailIcon);
        jLabelEmailIcon.setBounds(5, 6, 25, 26);

        jTextFieldEmail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldEmail.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldEmail.setText("user@example.com");
        jTextFieldEmail.setBorder(null);
        jPanelEmailInput.add(jTextFieldEmail);
        jTextFieldEmail.setBounds(35, 6, 335, 26);

        jPanelStep1.add(jPanelEmailInput);
        jPanelEmailInput.setBounds(40, 125, 380, 38);

        jButtonSendReset.setBackground(new java.awt.Color(245, 130, 32));
        jButtonSendReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSendReset.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSendReset.setText("Send Reset Link →");
        jButtonSendReset.setBorderPainted(false);
        jButtonSendReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendResetActionPerformed(evt);
            }
        });
        jPanelStep1.add(jButtonSendReset);
        jButtonSendReset.setBounds(40, 185, 380, 38);

        jPanelFormContainer.add(jPanelStep1);
        jPanelStep1.setBounds(0, 0, 460, 250);

        jPanelStep2.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStep2.setLayout(null);

        jLabelResetTitle2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabelResetTitle2.setForeground(new java.awt.Color(15, 37, 55));
        jLabelResetTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResetTitle2.setText("Verify Code & Reset Password");
        jPanelStep2.add(jLabelResetTitle2);
        jLabelResetTitle2.setBounds(20, 15, 420, 25);

        jLabelResetDesc2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabelResetDesc2.setForeground(new java.awt.Color(113, 128, 150));
        jLabelResetDesc2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResetDesc2.setText("Enter the 6-digit code and your new password.");
        jPanelStep2.add(jLabelResetDesc2);
        jLabelResetDesc2.setBounds(20, 40, 420, 15);

        jPanelOTP.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOTP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 224)));
        jPanelOTP.setLayout(null);

        jLabelOTPIcon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelOTPIcon.setForeground(new java.awt.Color(160, 174, 192));
        jLabelOTPIcon.setText("  🔑 ");
        jPanelOTP.add(jLabelOTPIcon);
        jLabelOTPIcon.setBounds(5, 5, 25, 26);

        jTextFieldOTP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldOTP.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldOTP.setText("Verification Code");
        jTextFieldOTP.setBorder(null);
        jPanelOTP.add(jTextFieldOTP);
        jTextFieldOTP.setBounds(35, 5, 335, 26);

        jPanelStep2.add(jPanelOTP);
        jPanelOTP.setBounds(40, 65, 380, 36);

        jPanelNewPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelNewPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 224)));
        jPanelNewPassword.setLayout(null);

        jLabelLockIcon1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelLockIcon1.setForeground(new java.awt.Color(160, 174, 192));
        jLabelLockIcon1.setText("  🔒 ");
        jPanelNewPassword.add(jLabelLockIcon1);
        jLabelLockIcon1.setBounds(5, 5, 25, 26);

        jPasswordFieldNew.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jPasswordFieldNew.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordFieldNew.setText("New Password");
        jPasswordFieldNew.setBorder(null);
        jPanelNewPassword.add(jPasswordFieldNew);
        jPasswordFieldNew.setBounds(35, 5, 335, 26);

        jPanelStep2.add(jPanelNewPassword);
        jPanelNewPassword.setBounds(40, 110, 380, 36);

        jPanelConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelConfirmPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 224)));
        jPanelConfirmPassword.setLayout(null);

        jLabelLockIcon2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelLockIcon2.setForeground(new java.awt.Color(160, 174, 192));
        jLabelLockIcon2.setText("  🔒 ");
        jPanelConfirmPassword.add(jLabelLockIcon2);
        jLabelLockIcon2.setBounds(5, 5, 25, 26);

        jPasswordFieldConfirm.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jPasswordFieldConfirm.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordFieldConfirm.setText("Confirm Password");
        jPasswordFieldConfirm.setBorder(null);
        jPanelConfirmPassword.add(jPasswordFieldConfirm);
        jPasswordFieldConfirm.setBounds(35, 5, 335, 26);

        jPanelStep2.add(jPanelConfirmPassword);
        jPanelConfirmPassword.setBounds(40, 155, 380, 36);

        jButtonVerifyReset.setBackground(new java.awt.Color(245, 130, 32));
        jButtonVerifyReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonVerifyReset.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVerifyReset.setText("Verify & Reset Password →");
        jButtonVerifyReset.setBorderPainted(false);
        jButtonVerifyReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerifyResetActionPerformed(evt);
            }
        });
        jPanelStep2.add(jButtonVerifyReset);
        jButtonVerifyReset.setBounds(40, 202, 380, 36);

        jPanelFormContainer.add(jPanelStep2);
        jPanelStep2.setBounds(0, 0, 460, 250);

        jPanelCard.add(jPanelFormContainer);
        jPanelFormContainer.setBounds(60, 120, 460, 250);

        jLabelBackLogin.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelBackLogin.setForeground(new java.awt.Color(74, 85, 104));
        jLabelBackLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBackLogin.setText("← Back to Login");
        jPanelCard.add(jLabelBackLogin);
        jLabelBackLogin.setBounds(60, 385, 460, 25);

        jPanelFooter.setBackground(new java.awt.Color(226, 232, 240));
        jPanelFooter.setLayout(null);

        jLabelFooterStatus.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelFooterStatus.setForeground(new java.awt.Color(113, 128, 150));
        jLabelFooterStatus.setText("SYSTEM STATUS: OPERATIONAL");
        jPanelFooter.add(jLabelFooterStatus);
        jLabelFooterStatus.setBounds(15, 10, 250, 15);

        jPanelCard.add(jPanelFooter);
        jPanelFooter.setBounds(0, 425, 580, 35);

        getContentPane().add(jPanelCard);
        jPanelCard.setBounds(110, 50, 580, 460);

        // Center on screen
        setPreferredSize(new java.awt.Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSendResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendResetActionPerformed
        String email = jTextFieldEmail.getText().trim();

        if (email.isEmpty() || email.equals("user@example.com")) {
            JOptionPane.showMessageDialog(this, "Please enter your registered email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserController controller = new UserController();
        String code = controller.generateResetCode(email);

        if (code != null) {
            this.generatedOTP = code;
            this.userEmail = email;
            
            // Show verification OTP dialog box (simulating mail server dispatch)
            JOptionPane.showMessageDialog(this, 
                "A verification code has been dispatched to your email.\n" +
                "For testing/evaluation, your code is: " + code, 
                "Verification Code Sent", 
                JOptionPane.INFORMATION_MESSAGE);

            // Swap forms dynamically
            jPanelStep1.setVisible(false);
            jPanelStep2.setVisible(true);
            jPanelFormContainer.revalidate();
            jPanelFormContainer.repaint();
        } else {
            JOptionPane.showMessageDialog(this, 
                "This email address is not registered in our system or is formatted incorrectly.", 
                "Account Not Found", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSendResetActionPerformed

    private void jButtonVerifyResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerifyResetActionPerformed
        String codeEntered = jTextFieldOTP.getText().trim();
        String newPassword = new String(jPasswordFieldNew.getPassword());
        String confirmPassword = new String(jPasswordFieldConfirm.getPassword());

        if (codeEntered.isEmpty() || codeEntered.equals("Verification Code") ||
            newPassword.isEmpty() || newPassword.equals("New Password") ||
            confirmPassword.isEmpty() || confirmPassword.equals("Confirm Password")) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!codeEntered.equals(generatedOTP)) {
            JOptionPane.showMessageDialog(this, "Invalid verification code. Please check and try again.", "Verification Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (newPassword.length() < 6) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserController controller = new UserController();
        boolean success = controller.recoverPassword(userEmail, newPassword);

        if (success) {
            JOptionPane.showMessageDialog(this, 
                "Password has been reset successfully!\nYour account is now updated.", 
                "Reset Success", 
                JOptionPane.INFORMATION_MESSAGE);
            new SignIn().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "An database connectivity error occurred while updating your password.", 
                "Reset Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonVerifyResetActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:initComponents
    private javax.swing.JButton jButtonSendReset;
    private javax.swing.JButton jButtonVerifyReset;
    private javax.swing.JLabel jLabelBackHome;
    private javax.swing.JLabel jLabelBackLogin;
    private javax.swing.JLabel jLabelBrandTitle;
    private javax.swing.JLabel jLabelEmailIcon;
    private javax.swing.JLabel jLabelEmailLabel;
    private javax.swing.JLabel jLabelFooterStatus;
    private javax.swing.JLabel jLabelHeaderTitle;
    private javax.swing.JLabel jLabelLockIcon1;
    private javax.swing.JLabel jLabelLockIcon2;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelOTPIcon;
    private javax.swing.JLabel jLabelResetDesc;
    private javax.swing.JLabel jLabelResetDesc2;
    private javax.swing.JLabel jLabelResetTitle;
    private javax.swing.JLabel jLabelResetTitle2;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPanel jPanelConfirmPassword;
    private javax.swing.JPanel jPanelEmailInput;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelFormContainer;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelNewPassword;
    private javax.swing.JPanel jPanelOTP;
    private javax.swing.JPanel jPanelStep1;
    private javax.swing.JPanel jPanelStep2;
    private javax.swing.JPasswordField jPasswordFieldConfirm;
    private javax.swing.JPasswordField jPasswordFieldNew;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldOTP;
    // End of variables declaration//GEN-END:initComponents
}
