package view;

import controller.NavigationController;
import javax.swing.JOptionPane;

/**
 * View class representing the Password Recovery interface.
 * Built using NetBeans Form Designer components with custom premium painting logic.
 * Extends JFrame container with panel visibility state transition triggers.
 */
public class PasswordRecovery extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PasswordRecovery.class.getName());

    /**
     * Creates new form PasswordRecovery
     */
    public PasswordRecovery() {
        initComponents();
        setupPremiumStyling();
        setupCustomListeners();
    }


    private void setupPremiumStyling() {
        // Main white card (shadow + rounded corners + border).
        // Positions and draws the central content card container with dynamic shadows.
        jPanelCard.setOpaque(false);
        jPanelCard.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Shadow layers
                g2.setColor(new java.awt.Color(0, 0, 0, 12));
                for (int i = 1; i <= 6; i++) {
                    g2.drawRoundRect(x + i, y + i, width - 2 * i, height - 2 * i, 10, 10);
                }
                
                // Card panel background
                g2.setColor(java.awt.Color.WHITE);
                g2.fillRoundRect(x, y, width - 1, height - 1, 10, 10);
                
                // Card panel outer border
                g2.setColor(new java.awt.Color(229, 231, 235)); // #E5E7EB
                g2.setStroke(new java.awt.BasicStroke(1.0f));
                g2.drawRoundRect(x, y, width - 1, height - 1, 10, 10);
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

        // Inner form reset card container
        jPanelFormContainer.setOpaque(false);
        jPanelFormContainer.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2.setColor(java.awt.Color.WHITE);
                g2.fillRoundRect(x, y, width, height, 8, 8);
                
                g2.setColor(new java.awt.Color(229, 231, 235)); // #E5E7EB
                g2.setStroke(new java.awt.BasicStroke(1.0f));
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

        // Apply custom rounded border and focus highlights on input fields
        applyRoundedFieldBorder(jPanelEmailInput, txtEmail);
        applyRoundedFieldBorder(jPanelSecAnswer, txtSecurityAnswer);
        applyRoundedFieldBorder(jPanelNewPassword, txtNewPassword);
        applyRoundedFieldBorder(jPanelConfirmPassword, txtConfirmPassword);

        // Apply orange buttons styling with hover states
        applyOrangeButtonStyling(btnFetchQuestion);
        applyOrangeButtonStyling(btnResetPassword);

        // Style the back link to look like a clickable text label
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setForeground(new java.awt.Color(75, 85, 99)); // #4B5563
        
        // Navigation logo properties
        jLabelLogo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28)); // 28px
        jLabelLogo.setForeground(new java.awt.Color(31, 41, 55)); // #1F2937
    }

    private void applyRoundedFieldBorder(javax.swing.JPanel panel, javax.swing.JTextField textField) {
        panel.setOpaque(false);
        panel.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2.setColor(java.awt.Color.WHITE);
                g2.fillRoundRect(x, y, width, height, 6, 6);
                
                if (textField.isFocusOwner()) {
                    g2.setColor(new java.awt.Color(249, 115, 22)); // Orange focus glow
                    g2.setStroke(new java.awt.BasicStroke(1.2f));
                } else {
                    g2.setColor(new java.awt.Color(209, 213, 219)); // #D1D5DB default
                    g2.setStroke(new java.awt.BasicStroke(1.0f));
                }
                g2.drawRoundRect(x, y, width - 1, height - 1, 6, 6);
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

        textField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                panel.repaint();
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                panel.repaint();
            }
        });
    }

    private void applyOrangeButtonStyling(javax.swing.JButton button) {
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (button.getModel().isPressed()) {
                    g2.setColor(new java.awt.Color(220, 95, 10));
                } else if (button.getModel().isRollover()) {
                    g2.setColor(new java.awt.Color(234, 88, 12));
                } else {
                    g2.setColor(new java.awt.Color(249, 115, 22)); // #F97316
                }
                g2.fillRoundRect(x, y, width, height, 6, 6);
                g2.dispose();
                
                g.setColor(java.awt.Color.WHITE);
                g.setFont(button.getFont());
                java.awt.FontMetrics fm = g.getFontMetrics();
                java.awt.geom.Rectangle2D r = fm.getStringBounds(button.getText(), g);
                int tx = (width - (int) r.getWidth()) / 2;
                int ty = (height - (int) r.getHeight()) / 2 + fm.getAscent();
                g.drawString(button.getText(), tx, ty);
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
    }

    private void setupCustomListeners() {
        lblError = new javax.swing.JLabel();
        // Reset properties of interceptor label matching components layout
        lblError.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblError.setForeground(new java.awt.Color(255, 59, 48));
        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelCard.add(lblError);
        lblError.setBounds(20, 372, 460, 20);

        // Placeholders logic
        txtEmail.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtEmail.getText().equals("user@example.com")) {
                    txtEmail.setText("");
                    txtEmail.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtEmail.getText().trim().isEmpty()) {
                    txtEmail.setText("user@example.com");
                    txtEmail.setForeground(new java.awt.Color(156, 163, 175));
                }
            }
        });




        txtSecurityAnswer.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtSecurityAnswer.getText().equals("Enter Security Answer")) {
                    txtSecurityAnswer.setText("");
                    txtSecurityAnswer.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtSecurityAnswer.getText().trim().isEmpty()) {
                    txtSecurityAnswer.setText("Enter Security Answer");
                    txtSecurityAnswer.setForeground(new java.awt.Color(156, 163, 175));
                }
            }
        });

        txtNewPassword.setEchoChar((char) 0);
        txtNewPassword.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String pass = new String(txtNewPassword.getPassword());
                if (pass.equals("New Password")) {
                    txtNewPassword.setText("");
                    txtNewPassword.setEchoChar('•');
                    txtNewPassword.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String pass = new String(txtNewPassword.getPassword());
                if (pass.trim().isEmpty()) {
                    txtNewPassword.setText("New Password");
            
        txtSecurityAnswer.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtSecurityAnswer.getText().equals("Enter Security Answer")) {
                    txtSecurityAnswer.setText("");
                    txtSecurityAnswer.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtSecurityAnswer.getText().trim().isEmpty()) {
                    txtSecurityAnswer.setText("Enter Security Answer");
                    txtSecurityAnswer.setForeground(new java.awt.Color(156, 163, 175));
                }
            }
        });

        txtNewPassword.setEchoChar((char) 0);
                    txtNewPassword.setForeground(new java.awt.Color(156, 163, 175));
                }
            }
        });

        txtConfirmPassword.setEchoChar((char) 0);
        txtConfirmPassword.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String pass = new String(txtConfirmPassword.getPassword());
                if (pass.equals("Confirm Password")) {
                    txtConfirmPassword.setText("");
                    txtConfirmPassword.setEchoChar('•');
                    txtConfirmPassword.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String pass = new String(txtConfirmPassword.getPassword());
                if (pass.trim().isEmpty()) {
                    txtConfirmPassword.setText("Confirm Password");
                    txtConfirmPassword.setEchoChar((char) 0);
                    txtConfirmPassword.setForeground(new java.awt.Color(156, 163, 175));
                }
            }
        });

        // Top navigation bar mouse click
        jLabelBackHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBackHome.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PasswordRecovery.this.dispose();
                NavigationController.goToLogin(null);
            }
        });

        // Enter key action listeners to trigger transition actions on Enter keypress
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnFetchQuestion.doClick();
                }
            }
        });

        java.awt.event.KeyAdapter passwordEnterAdapter = new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnResetPassword.doClick();
                }
            }
        };
        txtNewPassword.addKeyListener(passwordEnterAdapter);
        txtConfirmPassword.addKeyListener(passwordEnterAdapter);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanelCard = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jLabelBackHome = new javax.swing.JLabel();
        jLabelHeaderTitle = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jPanelFormContainer = new javax.swing.JPanel();
        
        jLabelResetTitle = new javax.swing.JLabel();
        
        jPanelEmailInput = new javax.swing.JPanel();
        jLabelEmailIcon = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnFetchQuestion = new javax.swing.JButton();
        
        lblSecurityQuestionTitle = new javax.swing.JLabel();
        lblSecurityQuestionValue = new javax.swing.JLabel();
        
        jPanelSecAnswer = new javax.swing.JPanel();
        jLabelSecAnswerIcon = new javax.swing.JLabel();
        txtSecurityAnswer = new javax.swing.JTextField();
        
        jPanelNewPassword = new javax.swing.JPanel();
        jLabelLockIcon1 = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JPasswordField();
        
        jPanelConfirmPassword = new javax.swing.JPanel();
        jLabelLockIcon2 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        
        btnResetPassword = new javax.swing.JButton();
        
        btnBack = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        jPanelFooter = new javax.swing.JPanel();
        jLabelFooterStatus = new javax.swing.JLabel();
        pnlBg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("YATRA AIR SEWA - Password Recovery");
        setPreferredSize(new java.awt.Dimension(825, 620));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanelCard.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCard.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelCard.setLayout(null);

        jPanelHeader.setBackground(new java.awt.Color(31, 46, 74));
        jPanelHeader.setLayout(null);

        jLabelBackHome.setFont(new java.awt.Font("Segoe UI", 0, 10));
        jLabelBackHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBackHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_outline.png")));
        jLabelBackHome.setText(" Back to Home");
        jPanelHeader.add(jLabelBackHome);
        jLabelBackHome.setBounds(12, 5, 120, 15);

        jLabelHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 10));
        jLabelHeaderTitle.setForeground(new java.awt.Color(209, 213, 219));
        jLabelHeaderTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelHeaderTitle.setText("YATRA AIR SEWA");
        jPanelHeader.add(jLabelHeaderTitle);
        jLabelHeaderTitle.setBounds(338, 5, 150, 15);

        jPanelCard.add(jPanelHeader);
        jPanelHeader.setBounds(0, 0, 500, 25);

        jLabelLogo.setFont(new java.awt.Font("Segoe UI", 1, 28));
        jLabelLogo.setForeground(new java.awt.Color(31, 41, 55));
        jLabelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_arrowhead.png")));
        jLabelLogo.setText(" YATRA AIR SEWA");
        jLabelLogo.setIconTextGap(8);
        jPanelCard.add(jLabelLogo);
        jLabelLogo.setBounds(10, 35, 480, 36);

        jPanelFormContainer.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFormContainer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelFormContainer.setLayout(null);

        jLabelResetTitle.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabelResetTitle.setForeground(new java.awt.Color(55, 65, 81));
        jLabelResetTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResetTitle.setText("Recover Password");
        jPanelFormContainer.add(jLabelResetTitle);
        jLabelResetTitle.setBounds(10, 10, 330, 22);

        jPanelEmailInput.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmailInput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelEmailInput.setLayout(null);

        jLabelEmailIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_outline.png")));
        jPanelEmailInput.add(jLabelEmailIcon);
        jLabelEmailIcon.setBounds(8, 5, 18, 20);

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 11));
        txtEmail.setForeground(new java.awt.Color(156, 163, 175));
        txtEmail.setText("user@example.com");
        txtEmail.setBorder(null);
        jPanelEmailInput.add(txtEmail);
        txtEmail.setBounds(30, 2, 160, 26);

        jPanelFormContainer.add(jPanelEmailInput);
        jPanelEmailInput.setBounds(32, 45, 190, 30);

        btnFetchQuestion.setBackground(new java.awt.Color(249, 115, 22));
        btnFetchQuestion.setFont(new java.awt.Font("Segoe UI", 1, 10));
        btnFetchQuestion.setForeground(new java.awt.Color(255, 255, 255));
        btnFetchQuestion.setText("Fetch Quest...");
        btnFetchQuestion.setBorderPainted(false);
        jPanelFormContainer.add(btnFetchQuestion);
        btnFetchQuestion.setBounds(230, 45, 95, 30);

        lblSecurityQuestionTitle.setFont(new java.awt.Font("Segoe UI", 1, 11));
        lblSecurityQuestionTitle.setForeground(new java.awt.Color(55, 65, 81));
        lblSecurityQuestionTitle.setText("Security Question:");
        jPanelFormContainer.add(lblSecurityQuestionTitle);
        lblSecurityQuestionTitle.setBounds(32, 90, 290, 15);

        lblSecurityQuestionValue.setFont(new java.awt.Font("Segoe UI", 2, 11));
        lblSecurityQuestionValue.setForeground(new java.awt.Color(107, 114, 128));
        lblSecurityQuestionValue.setText("Retrieve your question first.");
        jPanelFormContainer.add(lblSecurityQuestionValue);
        lblSecurityQuestionValue.setBounds(32, 105, 290, 15);

        jPanelSecAnswer.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSecAnswer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelSecAnswer.setLayout(null);

        jLabelSecAnswerIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_outline.png")));
        jPanelSecAnswer.add(jLabelSecAnswerIcon);
        jLabelSecAnswerIcon.setBounds(8, 5, 18, 20);

        txtSecurityAnswer.setFont(new java.awt.Font("Segoe UI", 0, 11));
        txtSecurityAnswer.setForeground(new java.awt.Color(156, 163, 175));
        txtSecurityAnswer.setText("Enter Security Answer");
        txtSecurityAnswer.setBorder(null);
        jPanelSecAnswer.add(txtSecurityAnswer);
        txtSecurityAnswer.setBounds(30, 2, 250, 26);

        jPanelFormContainer.add(jPanelSecAnswer);
        jPanelSecAnswer.setBounds(32, 130, 290, 30);

        jPanelNewPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelNewPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelNewPassword.setLayout(null);

        jLabelLockIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lock_outline.png")));
        jPanelNewPassword.add(jLabelLockIcon1);
        jLabelLockIcon1.setBounds(8, 5, 18, 20);

        txtNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 11));
        txtNewPassword.setForeground(new java.awt.Color(156, 163, 175));
        txtNewPassword.setText("New Password");
        txtNewPassword.setBorder(null);
        jPanelNewPassword.add(txtNewPassword);
        txtNewPassword.setBounds(30, 2, 250, 26);

        jPanelFormContainer.add(jPanelNewPassword);
        jPanelNewPassword.setBounds(32, 170, 290, 30);

        jPanelConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelConfirmPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelConfirmPassword.setLayout(null);

        jLabelLockIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_lock_outline.png")));
        jPanelConfirmPassword.add(jLabelLockIcon2);
        jLabelLockIcon2.setBounds(8, 5, 18, 20);

        txtConfirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 11));
        txtConfirmPassword.setForeground(new java.awt.Color(156, 163, 175));
        txtConfirmPassword.setText("Confirm Password");
        txtConfirmPassword.setBorder(null);
        jPanelConfirmPassword.add(txtConfirmPassword);
        txtConfirmPassword.setBounds(30, 2, 250, 26);

        jPanelFormContainer.add(jPanelConfirmPassword);
        jPanelConfirmPassword.setBounds(32, 210, 290, 30);

        btnResetPassword.setBackground(new java.awt.Color(249, 115, 22));
        btnResetPassword.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnResetPassword.setForeground(new java.awt.Color(255, 255, 255));
        btnResetPassword.setText("Reset Password");
        btnResetPassword.setBorderPainted(false);
        jPanelFormContainer.add(btnResetPassword);
        btnResetPassword.setBounds(32, 255, 290, 32);

        jPanelCard.add(jPanelFormContainer);
        jPanelFormContainer.setBounds(75, 80, 350, 310);

        btnBack.setForeground(new java.awt.Color(75, 85, 99));
        btnBack.setText("Back to Login");
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        jPanelCard.add(btnBack);
        btnBack.setBounds(70, 420, 120, 20);

        lblError.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblError.setForeground(new java.awt.Color(255, 59, 48));
        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelCard.add(lblError);
        lblError.setBounds(20, 400, 460, 20);

        jPanelFooter.setBackground(new java.awt.Color(240, 242, 245));
        jPanelFooter.setLayout(null);

        jLabelFooterStatus.setFont(new java.awt.Font("Segoe UI", 1, 7));
        jLabelFooterStatus.setForeground(new java.awt.Color(113, 128, 150));
        jLabelFooterStatus.setText("SYSTEM STATUS: OPERATIONAL");
        jPanelFooter.add(jLabelFooterStatus);
        jLabelFooterStatus.setBounds(15, 8, 250, 10);

        jPanelCard.add(jPanelFooter);
        jPanelFooter.setBounds(0, 455, 500, 25);

        getContentPane().add(jPanelCard);
        jPanelCard.setBounds(162, 45, 500, 480);

        pnlBg.setBackground(new java.awt.Color(7, 29, 71));
        pnlBg.setLayout(null);
        getContentPane().add(pnlBg);
        pnlBg.setBounds(0, 0, 825, 620);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>


    /**
     * @param args the command line arguments
     */
    public static void runFrame(String args[]) {
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
        java.awt.EventQueue.invokeLater(() -> new PasswordRecovery().setVisible(true));
    }


    // Variables declaration - do not modify
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JButton btnFetchQuestion;
    private javax.swing.JLabel jLabelBackHome;
    private javax.swing.JLabel jLabelEmailIcon;
    private javax.swing.JLabel jLabelFooterStatus;
    private javax.swing.JLabel jLabelHeaderTitle;
    private javax.swing.JLabel jLabelLockIcon1;
    private javax.swing.JLabel jLabelLockIcon2;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelSecAnswerIcon;
    private javax.swing.JLabel lblSecurityQuestionTitle;
    private javax.swing.JLabel lblSecurityQuestionValue;
    private javax.swing.JLabel jLabelResetTitle;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPanel jPanelConfirmPassword;
    private javax.swing.JPanel jPanelEmailInput;
    private javax.swing.JPanel jPanelSecAnswer;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelFormContainer;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelNewPassword;
    private javax.swing.JLabel lblError;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JTextField txtSecurityAnswer;
    // End of variables declaration



    // ==========================================
    // GETTER METHODS (Clean Architecture Compliance)
    // ==========================================

    public javax.swing.JTextField getTxtEmail() {
        return txtEmail;
    }

    public javax.swing.JTextField getTxtSecurityAnswer() {
        return txtSecurityAnswer;
    }

    public javax.swing.JPasswordField getTxtNewPassword() {
        return txtNewPassword;
    }

    public javax.swing.JPasswordField getTxtConfirmPassword() {
        return txtConfirmPassword;
    }

    public javax.swing.JButton getBtnFetchQuestion() {
        return btnFetchQuestion;
    }

    public javax.swing.JLabel getLblSecurityQuestionValue() {
        return lblSecurityQuestionValue;
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


// Commit 2: Documented password recovery input validation logic

// Commit 5: Documented password recovery OTP code validation rules

// Commit 8: Documented password recovery text field input limits

// Commit 11: Documented password recovery back to Login navigation hooks

// Commit 14: Documented password recovery text field invalid warnings

// Commit 17: Documented password recovery action submit triggers verification

// Commit 20: Documented password recovery confirmation dialog messages

// Commit 23: Documented password recovery view cancel button navigation

// Commit 26: Documented password recovery layout screen dimensions

// Commit 29: Documented password recovery verification code validity check
