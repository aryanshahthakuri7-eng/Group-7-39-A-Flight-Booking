package view;

import controller.LoginController;
import controller.NavigationController;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 * View class representing the User Login interface.
 * Built using NetBeans Form Designer components to maintain visual editing compatibility.
 */
public class login extends javax.swing.JFrame {

    private final LoginController loginController;

    public login() {
        initComponents();
        loginController = new LoginController();
        
        // Custom premium stylings matching mockup exactly
        getContentPane().setBackground(new java.awt.Color(15, 30, 54)); // Matching dark blue background
        pnlMain.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlMain.setLayout(null);
        
        // 1. Header listeners
        lblBackHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBackHome.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(login.this, "Returning to Home page...", "Home", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // 2. Email Input placeholder behaviour
        txtEmail.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtEmail.getText().equals("Email")) {
                    txtEmail.setText("");
                    txtEmail.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtEmail.getText().trim().isEmpty()) {
                    txtEmail.setText("Email");
                    txtEmail.setForeground(new java.awt.Color(160, 174, 192));
                }
            }
        });
        
        // 3. Password Input placeholder behaviour
        txtPassword.setEchoChar((char) 0);
        txtPassword.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String pass = new String(txtPassword.getPassword());
                if (pass.equals("Password")) {
                    txtPassword.setText("");
                    txtPassword.setEchoChar('•');
                    txtPassword.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String pass = new String(txtPassword.getPassword());
                if (pass.trim().isEmpty()) {
                    txtPassword.setText("Password");
                    txtPassword.setEchoChar((char) 0);
                    txtPassword.setForeground(new java.awt.Color(160, 174, 192));
                }
            }
        });
        
        // 4. Clickable Forgot Password Link
        lblForgot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login.this.dispose();
                new controller.PasswordRecoveryController(new view.passwordrecovery()).activate();
            }
        });
        
        // 5. Styled LOGIN button
        btnLogin.setContentAreaFilled(true);
        btnLogin.setBackground(new java.awt.Color(245, 130, 32)); // Mockup Orange
        btnLogin.setForeground(java.awt.Color.WHITE);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        // 6. Styled Google Sign-In Button
        btnGoogle.setText("<html><font color='#4285F4'><b>G</b></font>&nbsp;&nbsp;&nbsp;Login with Google</html>");
        btnGoogle.setBackground(java.awt.Color.WHITE);
        btnGoogle.setForeground(new java.awt.Color(51, 51, 51));
        btnGoogle.setBorder(BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnGoogle.setFocusPainted(false);
        btnGoogle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoogle.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Google Sign-In is currently in sandbox/testing mode.\nPlease login using your email and password.", "Google Login", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // 7. Sign Up Link
        lblSignUp.setText("<html>Don't have an account? <font color='#1A73E8'><b>Sign Up</b></font></html>");
        lblSignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login.this.dispose();
                NavigationController.goToSignUp(login.this);
            }
        });

        // Verify database connection on startup in a background thread
        new Thread(() -> {
            try {
                database.DatabaseConnection.getConnection();
            } catch (Exception e) {
                java.awt.EventQueue.invokeLater(() -> {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Warning: Could not connect to local MySQL database.\n" +
                        "Please ensure MySQL is running (password: rahul1234).\n" +
                        "Details: " + e.getMessage(), 
                        "Database Offline", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                });
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblBackHome = new javax.swing.JLabel();
        lblHeaderLogo = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        pnlEmail = new javax.swing.JPanel();
        lblEmailIcon = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        pnlPassword = new javax.swing.JPanel();
        lblPasswordIcon = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblForgot = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        lblOr = new javax.swing.JLabel();
        sepLeft = new javax.swing.JSeparator();
        sepRight = new javax.swing.JSeparator();
        btnGoogle = new javax.swing.JButton();
        lblSignUp = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        pnlBg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - Login");
        setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(null);

        pnlHeader.setBackground(new java.awt.Color(240, 242, 245));
        pnlHeader.setLayout(null);

        lblBackHome.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblBackHome.setForeground(new java.awt.Color(102, 102, 102));
        lblBackHome.setText("⌂ BACK TO HOME");
        pnlHeader.add(lblBackHome);
        lblBackHome.setBounds(15, 10, 150, 15);

        lblHeaderLogo.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblHeaderLogo.setForeground(new java.awt.Color(117, 140, 179));
        lblHeaderLogo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHeaderLogo.setText("YATRA AIR SEWA");
        pnlHeader.add(lblHeaderLogo);
        lblHeaderLogo.setBounds(235, 10, 150, 15);

        pnlMain.add(pnlHeader);
        pnlHeader.setBounds(0, 0, 400, 35);

        lblLogo.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(15, 37, 55));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setText("▼  YATRA AIR SEWA");
        pnlMain.add(lblLogo);
        lblLogo.setBounds(20, 55, 360, 30);

        lblTagline.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblTagline.setForeground(new java.awt.Color(113, 128, 150));
        lblTagline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTagline.setText("Welcome Back!");
        pnlMain.add(lblTagline);
        lblTagline.setBounds(20, 85, 360, 20);

        pnlEmail.setBackground(new java.awt.Color(255, 255, 255));
        pnlEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        pnlEmail.setLayout(null);

        lblEmailIcon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEmailIcon.setForeground(new java.awt.Color(160, 174, 192));
        lblEmailIcon.setText("✉");
        pnlEmail.add(lblEmailIcon);
        lblEmailIcon.setBounds(12, 8, 20, 24);

        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(160, 174, 192));
        txtEmail.setText("Email");
        txtEmail.setBorder(null);
        pnlEmail.add(txtEmail);
        txtEmail.setBounds(35, 5, 275, 30);

        pnlMain.add(pnlEmail);
        pnlEmail.setBounds(40, 130, 320, 40);

        pnlPassword.setBackground(new java.awt.Color(255, 255, 255));
        pnlPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        pnlPassword.setLayout(null);

        lblPasswordIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPasswordIcon.setForeground(new java.awt.Color(160, 174, 192));
        lblPasswordIcon.setText("🔒");
        pnlPassword.add(lblPasswordIcon);
        lblPasswordIcon.setBounds(12, 8, 20, 24);

        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(160, 174, 192));
        txtPassword.setText("Password");
        txtPassword.setBorder(null);
        pnlPassword.add(txtPassword);
        txtPassword.setBounds(35, 5, 275, 30);

        pnlMain.add(pnlPassword);
        pnlPassword.setBounds(40, 185, 320, 40);

        lblForgot.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblForgot.setForeground(new java.awt.Color(26, 115, 232));
        lblForgot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblForgot.setText("Forgot Password?");
        pnlMain.add(lblForgot);
        lblForgot.setBounds(250, 230, 110, 20);

        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(this::btnLoginActionPerformed);
        pnlMain.add(btnLogin);
        btnLogin.setBounds(40, 260, 320, 40);

        lblOr.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblOr.setForeground(new java.awt.Color(160, 174, 192));
        lblOr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOr.setText("or");
        pnlMain.add(lblOr);
        lblOr.setBounds(190, 310, 20, 20);
        pnlMain.add(sepLeft);
        sepLeft.setBounds(40, 320, 140, 2);
        pnlMain.add(sepRight);
        sepRight.setBounds(220, 320, 140, 2);

        btnGoogle.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnGoogle.setText("Login with Google");
        pnlMain.add(btnGoogle);
        btnGoogle.setBounds(40, 345, 320, 40);

        lblSignUp.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblSignUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSignUp.setText("Don't have an account? Sign Up");
        pnlMain.add(lblSignUp);
        lblSignUp.setBounds(40, 405, 320, 20);

        lblError.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 59, 48));
        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblError);
        lblError.setBounds(40, 435, 320, 20);

        getContentPane().add(pnlMain);
        pnlMain.setBounds(50, 50, 400, 480);

        pnlBg.setBackground(new java.awt.Color(20, 28, 35));
        pnlBg.setLayout(null);
        getContentPane().add(pnlBg);
        pnlBg.setBounds(0, 0, 500, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        
        lblError.setText("");
        if (email.equals("Email") || password.equals("Password") || email.isEmpty() || password.isEmpty()) {
            lblError.setText("Please enter both email and password.");
            return;
        }
        
        if (loginController.login(email, password)) {
            NavigationController.goToDashboard(this);
        } else {
            lblError.setText("Invalid email address or password.");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoogle;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblBackHome;
    private javax.swing.JLabel lblEmailIcon;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblForgot;
    private javax.swing.JLabel lblHeaderLogo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblOr;
    private javax.swing.JLabel lblPasswordIcon;
    private javax.swing.JLabel lblSignUp;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JPanel pnlEmail;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlPassword;
    private javax.swing.JSeparator sepLeft;
    private javax.swing.JSeparator sepRight;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
