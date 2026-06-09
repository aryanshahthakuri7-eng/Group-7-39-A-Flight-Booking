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
        
        // Hide default labels and hints
        lblEmail.setVisible(false);
        lblPassword.setVisible(false);
        lblHint.setVisible(false);
        
        // 1. Light Gray Header Panel inside Card
        javax.swing.JPanel pnlHeader = new javax.swing.JPanel(null);
        pnlHeader.setBackground(new java.awt.Color(240, 242, 245));
        pnlHeader.setBounds(0, 0, 400, 35);
        
        javax.swing.JLabel lblBackHome = new javax.swing.JLabel("🏠 BACK TO HOME");
        lblBackHome.setFont(new java.awt.Font("Segoe UI", 1, 9));
        lblBackHome.setForeground(new java.awt.Color(102, 102, 102));
        lblBackHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBackHome.setBounds(15, 10, 150, 15);
        lblBackHome.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(login.this, "Returning to Home page...", "Home", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        pnlHeader.add(lblBackHome);
        
        javax.swing.JLabel lblHeaderLogo = new javax.swing.JLabel("YATRA AIR SEWA");
        lblHeaderLogo.setFont(new java.awt.Font("Segoe UI", 1, 9));
        lblHeaderLogo.setForeground(new java.awt.Color(117, 140, 179));
        lblHeaderLogo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHeaderLogo.setBounds(235, 10, 150, 15);
        pnlHeader.add(lblHeaderLogo);
        
        pnlMain.add(pnlHeader);
        
        // 2. Logo & Welcome Title
        lblLogo.setText("▼  YATRA AIR SEWA");
        lblLogo.setFont(new java.awt.Font("SansSerif", 1, 20));
        lblLogo.setForeground(new java.awt.Color(15, 37, 55));
        lblLogo.setBounds(20, 55, 360, 30);
        
        lblTagline.setText("Welcome Back!");
        lblTagline.setFont(new java.awt.Font("SansSerif", 0, 14));
        lblTagline.setForeground(new java.awt.Color(113, 128, 150));
        lblTagline.setBounds(20, 85, 360, 20);
        
        // 3. Email Input wrapper
        javax.swing.JPanel pnlEmail = new javax.swing.JPanel(null);
        pnlEmail.setBackground(java.awt.Color.WHITE);
        pnlEmail.setBorder(BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlEmail.setBounds(40, 130, 320, 40);
        
        javax.swing.JLabel lblEmailIcon = new javax.swing.JLabel("✉");
        lblEmailIcon.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lblEmailIcon.setForeground(new java.awt.Color(160, 174, 192));
        lblEmailIcon.setBounds(12, 8, 20, 24);
        pnlEmail.add(lblEmailIcon);
        
        pnlMain.remove(txtEmail);
        txtEmail.setBorder(null);
        txtEmail.setBackground(java.awt.Color.WHITE);
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 13));
        txtEmail.setBounds(35, 5, 275, 30);
        txtEmail.setText("Email");
        txtEmail.setForeground(new java.awt.Color(160, 174, 192));
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
        pnlEmail.add(txtEmail);
        pnlMain.add(pnlEmail);
        
        // 4. Password Input wrapper
        javax.swing.JPanel pnlPassword = new javax.swing.JPanel(null);
        pnlPassword.setBackground(java.awt.Color.WHITE);
        pnlPassword.setBorder(BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlPassword.setBounds(40, 185, 320, 40);
        
        javax.swing.JLabel lblPasswordIcon = new javax.swing.JLabel("🔒");
        lblPasswordIcon.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblPasswordIcon.setForeground(new java.awt.Color(160, 174, 192));
        lblPasswordIcon.setBounds(12, 8, 20, 24);
        pnlPassword.add(lblPasswordIcon);
        
        pnlMain.remove(txtPassword);
        txtPassword.setBorder(null);
        txtPassword.setBackground(java.awt.Color.WHITE);
        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 13));
        txtPassword.setBounds(35, 5, 275, 30);
        txtPassword.setText("Password");
        txtPassword.setForeground(new java.awt.Color(160, 174, 192));
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
        pnlPassword.add(txtPassword);
        pnlMain.add(pnlPassword);
        
        // 5. Clickable Forgot Password Link
        javax.swing.JLabel lblForgot = new javax.swing.JLabel("Forgot Password?");
        lblForgot.setFont(new java.awt.Font("SansSerif", 1, 11));
        lblForgot.setForeground(new java.awt.Color(26, 115, 232)); // Accent Blue
        lblForgot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblForgot.setBounds(250, 230, 110, 20);
        lblForgot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login.this.dispose();
                new controller.PasswordRecoveryController(new view.passwordrecovery()).activate();
            }
        });
        pnlMain.add(lblForgot);
        
        // 6. Styled LOGIN button
        btnLogin.setText("LOGIN");
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 13));
        btnLogin.setContentAreaFilled(true);
        btnLogin.setBackground(new java.awt.Color(245, 130, 32)); // Mockup Orange
        btnLogin.setForeground(java.awt.Color.WHITE);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setBounds(40, 260, 320, 40);
        
        // 7. Divider lines
        javax.swing.JLabel lblOr = new javax.swing.JLabel("or");
        lblOr.setFont(new java.awt.Font("SansSerif", 0, 11));
        lblOr.setForeground(new java.awt.Color(160, 174, 192));
        lblOr.setBounds(190, 310, 20, 20);
        lblOr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblOr);

        javax.swing.JSeparator sepLeft = new javax.swing.JSeparator();
        sepLeft.setForeground(new java.awt.Color(226, 232, 240));
        sepLeft.setBounds(40, 320, 140, 2);
        pnlMain.add(sepLeft);

        javax.swing.JSeparator sepRight = new javax.swing.JSeparator();
        sepRight.setForeground(new java.awt.Color(226, 232, 240));
        sepRight.setBounds(220, 320, 140, 2);
        pnlMain.add(sepRight);
        
        // 8. Google Sign-In Button
        javax.swing.JButton btnGoogle = new javax.swing.JButton("<html><font color='#4285F4'><b>G</b></font>&nbsp;&nbsp;&nbsp;Login with Google</html>");
        btnGoogle.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnGoogle.setBackground(java.awt.Color.WHITE);
        btnGoogle.setForeground(new java.awt.Color(51, 51, 51));
        btnGoogle.setBorder(BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnGoogle.setFocusPainted(false);
        btnGoogle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoogle.setBounds(40, 345, 320, 40);
        btnGoogle.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Google Sign-In is currently in sandbox/testing mode.\nPlease login using your email and password.", "Google Login", JOptionPane.INFORMATION_MESSAGE);
        });
        pnlMain.add(btnGoogle);
        
        // 9. Sign Up Link
        javax.swing.JLabel lblSignUp = new javax.swing.JLabel("<html>Don't have an account? <font color='#1A73E8'><b>Sign Up</b></font></html>");
        lblSignUp.setFont(new java.awt.Font("SansSerif", 0, 11));
        lblSignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSignUp.setBounds(40, 405, 320, 20);
        lblSignUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login.this.dispose();
                NavigationController.goToSignUp(login.this);
            }
        });
        pnlMain.add(lblSignUp);
        
        // 10. Position Error label
        lblError.setBounds(40, 435, 320, 20);

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

    private void styleTextField(javax.swing.JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        lblHint = new javax.swing.JLabel();
        pnlBg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - Login");
        setBackground(new java.awt.Color(20, 28, 35));
        setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(null);

        lblLogo.setText("▼  YATRAAIR");
        lblLogo.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(20, 28, 35));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblLogo);
        lblLogo.setBounds(20, 40, 360, 40);

        lblTagline.setText("Yatra Air Sewa Flight Booking Portal");
        lblTagline.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblTagline.setForeground(new java.awt.Color(113, 128, 150));
        lblTagline.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblTagline);
        lblTagline.setBounds(20, 80, 360, 20);

        lblEmail.setText("Email Address");
        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(92, 100, 112));
        pnlMain.add(lblEmail);
        lblEmail.setBounds(40, 140, 320, 20);

        txtEmail.setText("gauravchandra@gmail.com");
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlMain.add(txtEmail);
        txtEmail.setBounds(40, 165, 320, 35);

        lblPassword.setText("Password");
        lblPassword.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(92, 100, 112));
        pnlMain.add(lblPassword);
        lblPassword.setBounds(40, 220, 320, 20);

        txtPassword.setText("password123");
        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlMain.add(txtPassword);
        txtPassword.setBounds(40, 245, 320, 35);

        btnLogin.setText("Sign In");
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setBackground(new java.awt.Color(255, 115, 21));
        btnLogin.addActionListener(this::btnLoginActionPerformed);
        pnlMain.add(btnLogin);
        btnLogin.setBounds(40, 320, 320, 40);

        lblError.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 59, 48));
        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblError);
        lblError.setBounds(40, 380, 320, 20);

        lblHint.setText("Hint: gaurav.chandra@gmail.com / password123");
        lblHint.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblHint.setForeground(new java.awt.Color(144, 160, 176));
        lblHint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblHint);
        lblHint.setBounds(20, 440, 360, 20);

        getContentPane().add(pnlMain);
        pnlMain.setBounds(50, 50, 400, 480);

        pnlBg.setBackground(new java.awt.Color(20, 28, 35));
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
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblHint;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
