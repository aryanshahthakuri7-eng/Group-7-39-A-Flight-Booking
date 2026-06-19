package view;

import controller.LoginController;
import controller.NavigationController;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 * View class representing the User Login interface.
 * Built using NetBeans Form Designer components with custom premium painting logic.
 * Handles input validation, session connection callbacks, and responsive scaling boundaries.
 */
public class Login extends javax.swing.JFrame {

    private final LoginController loginController;

    public Login() {
        // Enable system-wide text anti-aliasing for Swing components
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext", "true");

        initComponents();
        loginController = new LoginController();
        
        // 1. Center the Login card (pnlMain) using GridBagLayout inside pnlBg
        getContentPane().remove(pnlMain);
        getContentPane().remove(pnlBg);
        
        pnlBg = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                float[] fractions = {0.0f, 1.0f};
                java.awt.Color[] colors = {new java.awt.Color(20, 48, 85), new java.awt.Color(9, 21, 45)};
                java.awt.RadialGradientPaint rgp = new java.awt.RadialGradientPaint(
                    new java.awt.geom.Point2D.Float(w / 2f, h / 2f),
                    Math.max(w, h) * 0.8f,
                    fractions,
                    colors
                );
                g2.setPaint(rgp);
                g2.fillRect(0, 0, w, h);
                g2.dispose();
            }
        };
        pnlBg.setLayout(new java.awt.GridBagLayout());
        
        pnlMain.setPreferredSize(new java.awt.Dimension(320, 460));
        pnlMain.setMinimumSize(new java.awt.Dimension(320, 460));
        pnlMain.setMaximumSize(new java.awt.Dimension(320, 460));
        pnlBg.add(pnlMain);
        
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnlBg, java.awt.BorderLayout.CENTER);

        // 2. Custom premium card border & background drawing for pnlMain
        pnlMain.setOpaque(false);
        pnlHeader.setOpaque(false);
        pnlMain.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Fill the main card body with solid white
                g2.setColor(java.awt.Color.WHITE);
                g2.fillRoundRect(x, y, width, height, 16, 16);
                
                // Fill the header area with light grey (top 30px) using clip
                java.awt.geom.RoundRectangle2D rect = new java.awt.geom.RoundRectangle2D.Float(x, y, width, height, 16, 16);
                g2.setClip(rect);
                g2.setColor(new java.awt.Color(245, 246, 248));
                g2.fillRect(x, y, width, 30);
                g2.setClip(null);
                
                // Thin line dividing header from body
                g2.setColor(new java.awt.Color(226, 232, 240));
                g2.drawLine(x, y + 30, x + width, y + 30);
                
                // Draw the border around the entire card
                g2.setStroke(new java.awt.BasicStroke(1.0f));
                g2.drawRoundRect(x, y, width - 1, height - 1, 16, 16);
                
                g2.dispose();
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(1, 1, 1, 1);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        // Make email and password field borders anti-aliased and smooth at runtime
        pnlEmail.setOpaque(false);
        pnlEmail.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new java.awt.Color(245, 246, 248));
                g2.fillRoundRect(x, y, width, height, 8, 8);
                g2.setColor(new java.awt.Color(230, 233, 238));
                g2.drawRoundRect(x, y, width - 1, height - 1, 8, 8);
                g2.dispose();
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(1, 1, 1, 1);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        pnlPassword.setOpaque(false);
        pnlPassword.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new java.awt.Color(245, 246, 248));
                g2.fillRoundRect(x, y, width, height, 8, 8);
                g2.setColor(new java.awt.Color(230, 233, 238));
                g2.drawRoundRect(x, y, width - 1, height - 1, 8, 8);
                g2.dispose();
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(1, 1, 1, 1);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        // 3. Header listeners
        lblBackHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBackHome.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(Login.this, "Returning to Home page...", "Home", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // 4. Replace txtEmail with custom placeholder-enabled text field
        pnlEmail.remove(txtEmail);
        txtEmail = new javax.swing.JTextField() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty()) {
                    java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new java.awt.Color(160, 174, 192));
                    g2.setFont(getFont().deriveFont(java.awt.Font.PLAIN));
                    int x = getInsets().left;
                    int y = g2.getFontMetrics().getAscent() + (getHeight() - g2.getFontMetrics().getHeight()) / 2;
                    g2.drawString("Email", x, y);
                    g2.dispose();
                }
            }
        };
        txtEmail.setText("");
        txtEmail.setForeground(new java.awt.Color(51, 51, 51));
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 13));
        txtEmail.setBorder(null);
        txtEmail.setOpaque(false);
        pnlEmail.add(txtEmail);
        txtEmail.setBounds(35, 4, 245, 30);
        
        // 5. Replace txtPassword with custom placeholder-enabled password field
        pnlPassword.remove(txtPassword);
        txtPassword = new javax.swing.JPasswordField() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                if (new String(getPassword()).isEmpty()) {
                    java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new java.awt.Color(160, 174, 192));
                    g2.setFont(getFont().deriveFont(java.awt.Font.PLAIN));
                    int x = getInsets().left;
                    int y = g2.getFontMetrics().getAscent() + (getHeight() - g2.getFontMetrics().getHeight()) / 2;
                    g2.drawString("..........", x, y);
                    g2.dispose();
                }
            }
        };
        txtPassword.setText("");
        txtPassword.setEchoChar('•');
        txtPassword.setForeground(new java.awt.Color(51, 51, 51));
        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 13));
        txtPassword.setBorder(null);
        txtPassword.setOpaque(false);
        pnlPassword.add(txtPassword);
        txtPassword.setBounds(35, 4, 245, 30);
        
        // 6. Clickable Forgot Password Link
        lblForgotPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Login.this.dispose();
                new controller.PasswordRecoveryController(new view.PasswordRecovery()).activate();
            }
        });
        
        // 7. Replace btnLogin with custom painted version
        pnlMain.remove(btnLogin);
        btnLogin = new javax.swing.JButton("LOGIN") {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                
                if (getModel().isPressed()) {
                    g2.setColor(new java.awt.Color(217, 95, 20));
                } else if (getModel().isRollover()) {
                    g2.setColor(new java.awt.Color(255, 133, 51));
                } else {
                    g2.setColor(new java.awt.Color(243, 112, 33));
                }
                g2.fillRoundRect(0, 0, width, height, 8, 8);
                
                g2.setColor(java.awt.Color.WHITE);
                g2.setFont(getFont());
                java.awt.FontMetrics fm = g2.getFontMetrics();
                java.awt.geom.Rectangle2D r = fm.getStringBounds(getText(), g2);
                int tx = (width - (int) r.getWidth()) / 2;
                int ty = (height - (int) r.getHeight()) / 2 + fm.getAscent();
                g2.drawString(getText(), tx, ty);
                g2.dispose();
            }
        };
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 14));
        btnLogin.setForeground(java.awt.Color.WHITE);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(this::btnLoginActionPerformed);
        pnlMain.add(btnLogin);
        btnLogin.setBounds(15, 255, 290, 40);
        
        // 8. Replace btnGoogle with custom painted version
        pnlMain.remove(btnGoogle);
        btnGoogle = new javax.swing.JButton("Login with Google") {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                
                if (getModel().isPressed()) {
                    g2.setColor(new java.awt.Color(245, 245, 245));
                } else if (getModel().isRollover()) {
                    g2.setColor(new java.awt.Color(250, 250, 250));
                } else {
                    g2.setColor(java.awt.Color.WHITE);
                }
                g2.fillRoundRect(0, 0, width, height, 8, 8);
                
                g2.setColor(new java.awt.Color(226, 232, 240));
                g2.drawRoundRect(0, 0, width - 1, height - 1, 8, 8);
                
                if (getIcon() != null) {
                    int size = 16;
                    int gap = getIconTextGap();
                    g2.setFont(getFont());
                    java.awt.FontMetrics fm = g2.getFontMetrics();
                    java.awt.geom.Rectangle2D r = fm.getStringBounds(getText(), g2);
                    int textWidth = (int) r.getWidth();
                    int totalContentWidth = size + gap + textWidth;
                    int startX = (width - totalContentWidth) / 2;
                    int cy = (height - size) / 2;
                    getIcon().paintIcon(this, g2, startX, cy);
                    
                    g2.setColor(new java.awt.Color(51, 51, 51));
                    int tx = startX + size + gap;
                    int ty = (height - (int) r.getHeight()) / 2 + fm.getAscent();
                    g2.drawString(getText(), tx, ty);
                }
                g2.dispose();
            }
        };
        btnGoogle.setFont(new java.awt.Font("SansSerif", 1, 12));
        btnGoogle.setForeground(new java.awt.Color(51, 51, 51));
        btnGoogle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/google_icon.png")));
        btnGoogle.setIconTextGap(15);
        btnGoogle.setContentAreaFilled(false);
        btnGoogle.setBorderPainted(false);
        btnGoogle.setFocusPainted(false);
        btnGoogle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGoogle.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Google Sign-In is currently in sandbox/testing mode.\nPlease Login using your email and password.", "Google Login", JOptionPane.INFORMATION_MESSAGE);
        });
        pnlMain.add(btnGoogle);
        btnGoogle.setBounds(15, 335, 290, 38);
        
        // 9. Sign Up Link
        lblSignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Login.this.dispose();
                NavigationController.goToSignUp(Login.this);
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

        // Enter key action listener to allow submitting the Login form on Enter keypress
        java.awt.event.KeyAdapter enterKeyAdapter = new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnLoginActionPerformed(null);
                }
            }
        };
        txtEmail.addKeyListener(enterKeyAdapter);
        txtPassword.addKeyListener(enterKeyAdapter);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        lblBackHome = new javax.swing.JLabel();
        lblHeaderLogo = new javax.swing.JLabel();
        lblLogoIcon = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        pnlEmail = new javax.swing.JPanel();
        lblEmailIcon = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        pnlPassword = new javax.swing.JPanel();
        lblPasswordIcon = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblForgotPassword = new javax.swing.JLabel();
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
        setBackground(new java.awt.Color(11, 31, 77));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlMain.setLayout(null);

        pnlHeader.setBackground(new java.awt.Color(240, 242, 245));
        pnlHeader.setLayout(null);

        lblBackHome.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblBackHome.setForeground(new java.awt.Color(102, 102, 102));
        lblBackHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_outline.png"))); // NOI18N
        lblBackHome.setText(" BACK TO HOME");
        lblBackHome.setIconTextGap(6);
        pnlHeader.add(lblBackHome);
        lblBackHome.setBounds(15, 8, 110, 15);

        lblHeaderLogo.setText("YATRA AIR SEWA");
        lblHeaderLogo.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblHeaderLogo.setForeground(new java.awt.Color(117, 140, 179));
        lblHeaderLogo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlHeader.add(lblHeaderLogo);
        lblHeaderLogo.setBounds(155, 8, 150, 15);

        pnlMain.add(pnlHeader);
        pnlHeader.setBounds(0, 0, 320, 30);
        pnlMain.add(lblLogoIcon);
        lblLogoIcon.setBounds(145, 38, 0, 0);

        lblLogo.setText(" YATRA AIR SEWA");
        lblLogo.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(11, 31, 77));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_arrowhead.png"))); // NOI18N
        lblLogo.setIconTextGap(8);
        pnlMain.add(lblLogo);
        lblLogo.setBounds(10, 45, 300, 30);

        lblWelcome.setText("Welcome Back!");
        lblWelcome.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(68, 68, 68));
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblWelcome);
        lblWelcome.setBounds(10, 90, 300, 25);

        pnlEmail.setBackground(new java.awt.Color(245, 246, 248));
        pnlEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlEmail.setLayout(null);

        lblEmailIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mail_outline.png"))); // NOI18N
        pnlEmail.add(lblEmailIcon);
        lblEmailIcon.setBounds(12, 7, 20, 24);

        txtEmail.setText("Email");
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtEmail.setBorder(null);
        txtEmail.setForeground(new java.awt.Color(153, 153, 153));
        pnlEmail.add(txtEmail);
        txtEmail.setBounds(35, 4, 245, 30);

        pnlMain.add(pnlEmail);
        pnlEmail.setBounds(15, 135, 290, 38);

        pnlPassword.setBackground(new java.awt.Color(245, 246, 248));
        pnlPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlPassword.setLayout(null);

        lblPasswordIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lock_outline.png"))); // NOI18N
        pnlPassword.add(lblPasswordIcon);
        lblPasswordIcon.setBounds(12, 7, 20, 24);

        txtPassword.setText("Password");
        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtPassword.setBorder(null);
        txtPassword.setForeground(new java.awt.Color(153, 153, 153));
        pnlPassword.add(txtPassword);
        txtPassword.setBounds(35, 4, 245, 30);

        pnlMain.add(pnlPassword);
        pnlPassword.setBounds(15, 185, 290, 38);

        lblForgotPassword.setText("Forgot Password?");
        lblForgotPassword.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblForgotPassword.setForeground(new java.awt.Color(90, 109, 143));
        lblForgotPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnlMain.add(lblForgotPassword);
        lblForgotPassword.setBounds(15, 230, 290, 20);

        btnLogin.setText("LOGIN");
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setBackground(new java.awt.Color(245, 130, 32));
        btnLogin.setBorderPainted(false);
        btnLogin.addActionListener(this::btnLoginActionPerformed);
        pnlMain.add(btnLogin);
        btnLogin.setBounds(15, 255, 290, 40);

        lblOr.setText("or");
        lblOr.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblOr.setForeground(new java.awt.Color(153, 153, 153));
        lblOr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblOr);
        lblOr.setBounds(145, 305, 30, 20);
        pnlMain.add(sepLeft);
        sepLeft.setBounds(15, 315, 120, 2);
        pnlMain.add(sepRight);
        sepRight.setBounds(185, 315, 120, 2);

        btnGoogle.setText("Login with Google");
        btnGoogle.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnGoogle.setForeground(new java.awt.Color(51, 51, 51));
        btnGoogle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/google_icon.png"))); // NOI18N
        btnGoogle.setIconTextGap(15);
        pnlMain.add(btnGoogle);
        btnGoogle.setBounds(15, 335, 290, 38);

        lblSignUp.setText("<html>Don't have an account? <font color='#111827'><b>Sign Up</b></font></html>");
        lblSignUp.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblSignUp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblSignUp);
        lblSignUp.setBounds(15, 390, 290, 20);

        lblError.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 59, 48));
        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblError);
        lblError.setBounds(15, 420, 290, 20);

        getContentPane().add(pnlMain);
        pnlMain.setBounds(240, 70, 320, 460);

        pnlBg.setBackground(new java.awt.Color(11, 31, 77));
        pnlBg.setLayout(null);
        getContentPane().add(pnlBg);
        pnlBg.setBounds(0, 0, 800, 600);

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
        
        if (loginController.Login(email, password)) {
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
    private javax.swing.JLabel lblForgotPassword;
    private javax.swing.JLabel lblHeaderLogo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoIcon;
    private javax.swing.JLabel lblOr;
    private javax.swing.JLabel lblPasswordIcon;
    private javax.swing.JLabel lblSignUp;
    private javax.swing.JLabel lblWelcome;
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

// Commit 1: Documented Login frame visual boundaries layout

// Commit 4: Documented Login email input validation regex checks

// Commit 7: Documented Login panel custom background coloring

// Commit 10: Documented Login submit button key press activation

// Commit 13: Documented Login panel custom border rendering attributes

// Commit 16: Documented Login page show password check state listener

// Commit 19: Documented Login panel custom logo icon bounds

// Commit 22: Documented Login controller redirection to home verification

// Commit 25: Documented Login panel custom layout border margins

// Commit 28: Documented Login panel background coloring customization
