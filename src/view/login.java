package view;

import controller.LoginController;
import controller.NavigationController;
import javax.swing.BorderFactory;

/**
 * View class representing the User Login interface.
 * Built using NetBeans Form Designer components to maintain visual editing compatibility.
 */
public class login extends javax.swing.JFrame {

    private final LoginController loginController;

    public login() {
        initComponents();
        loginController = new LoginController();
        
        // Custom premium stylings
        getContentPane().setBackground(new java.awt.Color(20, 28, 35));
        pnlMain.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        
        styleTextField(txtEmail);
        styleTextField(txtPassword);
        
        // Clickable Forgot Password Link
        javax.swing.JLabel lblForgot = new javax.swing.JLabel("Forgot Password?");
        lblForgot.setFont(new java.awt.Font("SansSerif", 1, 11));
        lblForgot.setForeground(new java.awt.Color(255, 122, 26)); // Orange Accent
        lblForgot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblForgot.setBounds(250, 220, 110, 20);
        lblForgot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login.this.dispose();
                new controller.PasswordRecoveryController(new view.passwordrecovery()).activate();
            }
        });
        pnlMain.add(lblForgot);
        
        btnLogin.setContentAreaFilled(true);
        btnLogin.setBackground(new java.awt.Color(255, 122, 26)); // Orange Accent
        btnLogin.setForeground(java.awt.Color.WHITE);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());
        
        lblError.setText("");
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
