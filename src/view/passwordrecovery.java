package view;

import javax.swing.BorderFactory;

/**
 * View frame class for Password Recovery.
 * Clean UI design following the project guidelines. No business logic is included here.
 */
public class passwordrecovery extends javax.swing.JFrame {

    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JButton btnSendOTP;
    private javax.swing.JLabel lblOTP;
    private javax.swing.JTextField txtOTP;
    private javax.swing.JButton btnVerifyOTP;
    private javax.swing.JLabel lblNewPassword;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JLabel lblConfirmPassword;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel lblError;
    private javax.swing.JPanel pnlBg;

    public passwordrecovery() {
        initComponents();
        styleComponents();
    }

    private void initComponents() {
        pnlMain = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnSendOTP = new javax.swing.JButton();
        lblOTP = new javax.swing.JLabel();
        txtOTP = new javax.swing.JTextField();
        btnVerifyOTP = new javax.swing.JButton();
        lblNewPassword = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JPasswordField();
        lblConfirmPassword = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        btnResetPassword = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        pnlBg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - Password Recovery");
        setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(null);

        lblLogo.setFont(new java.awt.Font("SansSerif", 1, 28));
        lblLogo.setForeground(new java.awt.Color(20, 28, 35));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setText("▼  YATRAAIR");
        pnlMain.add(lblLogo);
        lblLogo.setBounds(20, 20, 360, 30);

        lblTagline.setFont(new java.awt.Font("SansSerif", 0, 13));
        lblTagline.setForeground(new java.awt.Color(113, 128, 150));
        lblTagline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTagline.setText("Reset your account password securely");
        pnlMain.add(lblTagline);
        lblTagline.setBounds(20, 50, 360, 20);

        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 11));
        lblEmail.setForeground(new java.awt.Color(92, 100, 112));
        lblEmail.setText("Email Address");
        pnlMain.add(lblEmail);
        lblEmail.setBounds(40, 80, 200, 20);

        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 13));
        pnlMain.add(txtEmail);
        txtEmail.setBounds(40, 105, 200, 35);

        btnSendOTP.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnSendOTP.setText("Send OTP");
        pnlMain.add(btnSendOTP);
        btnSendOTP.setBounds(250, 105, 110, 35);

        lblOTP.setFont(new java.awt.Font("SansSerif", 1, 11));
        lblOTP.setForeground(new java.awt.Color(92, 100, 112));
        lblOTP.setText("Verification OTP Code");
        pnlMain.add(lblOTP);
        lblOTP.setBounds(40, 150, 200, 20);

        txtOTP.setFont(new java.awt.Font("SansSerif", 0, 13));
        pnlMain.add(txtOTP);
        txtOTP.setBounds(40, 175, 200, 35);

        btnVerifyOTP.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnVerifyOTP.setText("Verify OTP");
        pnlMain.add(btnVerifyOTP);
        btnVerifyOTP.setBounds(250, 175, 110, 35);

        lblNewPassword.setFont(new java.awt.Font("SansSerif", 1, 11));
        lblNewPassword.setForeground(new java.awt.Color(92, 100, 112));
        lblNewPassword.setText("New Password");
        pnlMain.add(lblNewPassword);
        lblNewPassword.setBounds(40, 220, 320, 20);

        txtNewPassword.setFont(new java.awt.Font("SansSerif", 0, 13));
        pnlMain.add(txtNewPassword);
        txtNewPassword.setBounds(40, 245, 320, 35);

        lblConfirmPassword.setFont(new java.awt.Font("SansSerif", 1, 11));
        lblConfirmPassword.setForeground(new java.awt.Color(92, 100, 112));
        lblConfirmPassword.setText("Confirm New Password");
        pnlMain.add(lblConfirmPassword);
        lblConfirmPassword.setBounds(40, 290, 320, 20);

        txtConfirmPassword.setFont(new java.awt.Font("SansSerif", 0, 13));
        pnlMain.add(txtConfirmPassword);
        txtConfirmPassword.setBounds(40, 315, 320, 35);

        btnResetPassword.setFont(new java.awt.Font("SansSerif", 1, 14));
        btnResetPassword.setText("Reset Password");
        pnlMain.add(btnResetPassword);
        btnResetPassword.setBounds(40, 370, 320, 40);

        btnBack.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnBack.setText("← Back to Login");
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setForeground(new java.awt.Color(92, 100, 112));
        pnlMain.add(btnBack);
        btnBack.setBounds(40, 420, 320, 30);

        lblError.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblError.setForeground(new java.awt.Color(255, 59, 48));
        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblError);
        lblError.setBounds(20, 452, 360, 20);

        getContentPane().add(pnlMain);
        pnlMain.setBounds(50, 50, 400, 480);

        pnlBg.setBackground(new java.awt.Color(20, 28, 35));
        pnlBg.setLayout(null);
        getContentPane().add(pnlBg);
        pnlBg.setBounds(0, 0, 500, 600);

        pack();
        setLocationRelativeTo(null);
    }

    private void styleComponents() {
        getContentPane().setBackground(new java.awt.Color(20, 28, 35));
        pnlMain.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        
        styleTextField(txtEmail);
        styleTextField(txtOTP);
        styleTextField(txtNewPassword);
        styleTextField(txtConfirmPassword);
        
        styleActionButton(btnSendOTP, new java.awt.Color(26, 115, 232)); // Blue for OTP Send
        styleActionButton(btnVerifyOTP, new java.awt.Color(26, 115, 232)); // Blue for OTP Verify
        styleActionButton(btnResetPassword, new java.awt.Color(255, 122, 26)); // Orange for Reset
        
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void styleTextField(javax.swing.JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleActionButton(javax.swing.JButton btn, java.awt.Color bg) {
        btn.setContentAreaFilled(true);
        btn.setBackground(bg);
        btn.setForeground(java.awt.Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    // ==========================================
    // GETTER METHODS (Clean Architecture Compliance)
    // ==========================================

    public javax.swing.JTextField getTxtEmail() {
        return txtEmail;
    }

    public javax.swing.JTextField getTxtOTP() {
        return txtOTP;
    }

    public javax.swing.JPasswordField getTxtNewPassword() {
        return txtNewPassword;
    }

    public javax.swing.JPasswordField getTxtConfirmPassword() {
        return txtConfirmPassword;
    }

    public javax.swing.JButton getBtnSendOTP() {
        return btnSendOTP;
    }

    public javax.swing.JButton getBtnVerifyOTP() {
        return btnVerifyOTP;
    }

    public javax.swing.JButton getBtnResetPassword() {
        return btnResetPassword;
    }

    public javax.swing.JButton getBtnBack() {
        return btnBack;
    }

    public javax.swing.JLabel getLblError() {
        return lblError;
    }
}
