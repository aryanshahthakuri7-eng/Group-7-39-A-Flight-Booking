package view;

import controller.NavigationController;
import javax.swing.JOptionPane;

/**
 * View class representing the Password Recovery interface.
 * Built using NetBeans Form Designer components with custom premium painting logic.
 */
public class passwordrecovery extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(passwordrecovery.class.getName());

    /**
     * Creates new form passwordrecovery
     */
    public passwordrecovery() {
        initComponents();
        setupPremiumStyling();
        setupCustomListeners();
        showStep(1); // Default to email request step
    }

    private void showStep(int stepNum) {
        jPanelStep1.setVisible(stepNum == 1);
        jPanelStep2.setVisible(stepNum == 2);
        jPanelStep3.setVisible(stepNum == 3);
        jPanelFormContainer.repaint();
    }

    private void setupPremiumStyling() {
        // Main white card (shadow + rounded corners + border)
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
        applyRoundedFieldBorder(jPanelOTPInput, txtOTP);
        applyRoundedFieldBorder(jPanelNewPassword, txtNewPassword);
        applyRoundedFieldBorder(jPanelConfirmPassword, txtConfirmPassword);

        // Apply orange buttons styling with hover states
        applyOrangeButtonStyling(btnSendOTP);
        applyOrangeButtonStyling(btnVerifyOTP);
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
        // Intercept error notifications to automatically transition recovery steps
        lblError = new javax.swing.JLabel() {
            @Override
            public void setText(String text) {
                super.setText(text);
                if (text != null) {
                    if (text.contains("OTP sent!")) {
                        showStep(2);
                    } else if (text.contains("verified!")) {
                        showStep(3);
                    }
                }
            }
        };
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

        txtOTP.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtOTP.getText().equals("Verification Code")) {
                    txtOTP.setText("");
                    txtOTP.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtOTP.getText().trim().isEmpty()) {
                    txtOTP.setText("Verification Code");
                    txtOTP.setForeground(new java.awt.Color(156, 163, 175));
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
                passwordrecovery.this.dispose();
                NavigationController.goToLogin(null);
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
        jPanelFormContainer = new javax.swing.JPanel();
        jPanelStep1 = new javax.swing.JPanel();
        jLabelResetTitle = new javax.swing.JLabel();
        jLabelResetDesc = new javax.swing.JLabel();
        jLabelEmailLabel = new javax.swing.JLabel();
        jPanelEmailInput = new javax.swing.JPanel();
        jLabelEmailIcon = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnSendOTP = new javax.swing.JButton();
        jPanelStep2 = new javax.swing.JPanel();
        jLabelResetTitle2 = new javax.swing.JLabel();
        jLabelResetDesc2 = new javax.swing.JLabel();
        jLabelOTPLabel = new javax.swing.JLabel();
        jPanelOTPInput = new javax.swing.JPanel();
        jLabelOTPIcon = new javax.swing.JLabel();
        txtOTP = new javax.swing.JTextField();
        btnVerifyOTP = new javax.swing.JButton();
        jPanelStep3 = new javax.swing.JPanel();
        jLabelResetTitle3 = new javax.swing.JLabel();
        jLabelNewPasswordLabel = new javax.swing.JLabel();
        jPanelNewPassword = new javax.swing.JPanel();
        jLabelLockIcon1 = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JPasswordField();
        jLabelConfirmPasswordLabel = new javax.swing.JLabel();
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
        setPreferredSize(new java.awt.Dimension(825, 575));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanelCard.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCard.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelCard.setLayout(null);

        jPanelHeader.setBackground(new java.awt.Color(31, 46, 74));
        jPanelHeader.setLayout(null);

        jLabelBackHome.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabelBackHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBackHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_outline.png"))); // NOI18N
        jLabelBackHome.setText(" Back to Home");
        jPanelHeader.add(jLabelBackHome);
        jLabelBackHome.setBounds(12, 5, 120, 15);

        jLabelHeaderTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabelHeaderTitle.setForeground(new java.awt.Color(209, 213, 219));
        jLabelHeaderTitle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelHeaderTitle.setText("YATRA AIR SEWA");
        jPanelHeader.add(jLabelHeaderTitle);
        jLabelHeaderTitle.setBounds(338, 5, 150, 15);

        jPanelCard.add(jPanelHeader);
        jPanelHeader.setBounds(0, 0, 500, 25);

        jLabelLogo.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabelLogo.setForeground(new java.awt.Color(31, 41, 55));
        jLabelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_arrowhead.png"))); // NOI18N
        jLabelLogo.setText(" YATRA AIR SEWA");
        jLabelLogo.setIconTextGap(8);
        jPanelCard.add(jLabelLogo);
        jLabelLogo.setBounds(10, 45, 480, 36);

        jPanelFormContainer.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFormContainer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelFormContainer.setLayout(null);

        jPanelStep1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStep1.setLayout(null);

        jLabelResetTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelResetTitle.setForeground(new java.awt.Color(55, 65, 81));
        jLabelResetTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResetTitle.setText("Reset Your Password");
        jPanelStep1.add(jLabelResetTitle);
        jLabelResetTitle.setBounds(10, 15, 260, 22);

        jLabelResetDesc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabelResetDesc.setForeground(new java.awt.Color(107, 114, 128));
        jLabelResetDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResetDesc.setText("<html><body style='text-align: center;'>Enter your registered email address and we will send you instructions to reset your password.</body></html>");
        jPanelStep1.add(jLabelResetDesc);
        jLabelResetDesc.setBounds(20, 42, 240, 42);

        jLabelEmailLabel.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelEmailLabel.setForeground(new java.awt.Color(107, 114, 128));
        jLabelEmailLabel.setText("EMAIL ADDRESS");
        jPanelStep1.add(jLabelEmailLabel);
        jLabelEmailLabel.setBounds(32, 90, 216, 12);

        jPanelEmailInput.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmailInput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelEmailInput.setLayout(null);

        jLabelEmailIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mail_outline.png"))); // NOI18N
        jPanelEmailInput.add(jLabelEmailIcon);
        jLabelEmailIcon.setBounds(8, 5, 18, 20);

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(156, 163, 175));
        txtEmail.setText("user@example.com");
        txtEmail.setBorder(null);
        jPanelEmailInput.add(txtEmail);
        txtEmail.setBounds(30, 2, 178, 26);

        jPanelStep1.add(jPanelEmailInput);
        jPanelEmailInput.setBounds(32, 105, 216, 30);

        btnSendOTP.setBackground(new java.awt.Color(249, 115, 22));
        btnSendOTP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSendOTP.setForeground(new java.awt.Color(255, 255, 255));
        btnSendOTP.setText("Send Reset Link →");
        btnSendOTP.setBorderPainted(false);
        jPanelStep1.add(btnSendOTP);
        btnSendOTP.setBounds(32, 150, 216, 32);

        jPanelFormContainer.add(jPanelStep1);
        jPanelStep1.setBounds(0, 0, 280, 230);

        jPanelStep2.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStep2.setLayout(null);

        jLabelResetTitle2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelResetTitle2.setForeground(new java.awt.Color(55, 65, 81));
        jLabelResetTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResetTitle2.setText("Enter Verification Code");
        jPanelStep2.add(jLabelResetTitle2);
        jLabelResetTitle2.setBounds(10, 15, 260, 22);

        jLabelResetDesc2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabelResetDesc2.setForeground(new java.awt.Color(107, 114, 128));
        jLabelResetDesc2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResetDesc2.setText("<html><body style='text-align: center;'>Please enter the 6-digit verification code sent to your email.</body></html>");
        jPanelStep2.add(jLabelResetDesc2);
        jLabelResetDesc2.setBounds(20, 42, 240, 42);

        jLabelOTPLabel.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelOTPLabel.setForeground(new java.awt.Color(107, 114, 128));
        jLabelOTPLabel.setText("VERIFICATION CODE");
        jPanelStep2.add(jLabelOTPLabel);
        jLabelOTPLabel.setBounds(32, 90, 216, 12);

        jPanelOTPInput.setBackground(new java.awt.Color(255, 255, 255));
        jPanelOTPInput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelOTPInput.setLayout(null);

        jLabelOTPIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lock_outline.png"))); // NOI18N
        jPanelOTPInput.add(jLabelOTPIcon);
        jLabelOTPIcon.setBounds(8, 5, 18, 20);

        txtOTP.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtOTP.setForeground(new java.awt.Color(156, 163, 175));
        txtOTP.setText("Verification Code");
        txtOTP.setBorder(null);
        jPanelOTPInput.add(txtOTP);
        txtOTP.setBounds(30, 2, 178, 26);

        jPanelStep2.add(jPanelOTPInput);
        jPanelOTPInput.setBounds(32, 105, 216, 30);

        btnVerifyOTP.setBackground(new java.awt.Color(249, 115, 22));
        btnVerifyOTP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerifyOTP.setForeground(new java.awt.Color(255, 255, 255));
        btnVerifyOTP.setText("Verify Code →");
        btnVerifyOTP.setBorderPainted(false);
        jPanelStep2.add(btnVerifyOTP);
        btnVerifyOTP.setBounds(32, 150, 216, 32);

        jPanelFormContainer.add(jPanelStep2);
        jPanelStep2.setBounds(0, 0, 280, 230);

        jPanelStep3.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStep3.setLayout(null);

        jLabelResetTitle3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelResetTitle3.setForeground(new java.awt.Color(55, 65, 81));
        jLabelResetTitle3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelResetTitle3.setText("Set New Password");
        jPanelStep3.add(jLabelResetTitle3);
        jLabelResetTitle3.setBounds(10, 10, 260, 20);

        jLabelNewPasswordLabel.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelNewPasswordLabel.setForeground(new java.awt.Color(107, 114, 128));
        jLabelNewPasswordLabel.setText("NEW PASSWORD");
        jPanelStep3.add(jLabelNewPasswordLabel);
        jLabelNewPasswordLabel.setBounds(32, 38, 216, 12);

        jPanelNewPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelNewPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelNewPassword.setLayout(null);

        jLabelLockIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lock_outline.png"))); // NOI18N
        jPanelNewPassword.add(jLabelLockIcon1);
        jLabelLockIcon1.setBounds(8, 5, 18, 20);

        txtNewPassword.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtNewPassword.setForeground(new java.awt.Color(156, 163, 175));
        txtNewPassword.setText("New Password");
        txtNewPassword.setBorder(null);
        jPanelNewPassword.add(txtNewPassword);
        txtNewPassword.setBounds(30, 2, 178, 26);

        jPanelStep3.add(jPanelNewPassword);
        jPanelNewPassword.setBounds(32, 52, 216, 30);

        jLabelConfirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelConfirmPasswordLabel.setForeground(new java.awt.Color(107, 114, 128));
        jLabelConfirmPasswordLabel.setText("CONFIRM PASSWORD");
        jPanelStep3.add(jLabelConfirmPasswordLabel);
        jLabelConfirmPasswordLabel.setBounds(32, 90, 216, 12);

        jPanelConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelConfirmPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelConfirmPassword.setLayout(null);

        jLabelLockIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_lock_outline.png"))); // NOI18N
        jPanelConfirmPassword.add(jLabelLockIcon2);
        jLabelLockIcon2.setBounds(8, 5, 18, 20);

        txtConfirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtConfirmPassword.setForeground(new java.awt.Color(156, 163, 175));
        txtConfirmPassword.setText("Confirm Password");
        txtConfirmPassword.setBorder(null);
        jPanelConfirmPassword.add(txtConfirmPassword);
        txtConfirmPassword.setBounds(30, 2, 178, 26);

        jPanelStep3.add(jPanelConfirmPassword);
        jPanelConfirmPassword.setBounds(32, 104, 216, 30);

        btnResetPassword.setBackground(new java.awt.Color(249, 115, 22));
        btnResetPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnResetPassword.setForeground(new java.awt.Color(255, 255, 255));
        btnResetPassword.setText("Reset Password →");
        btnResetPassword.setBorderPainted(false);
        jPanelStep3.add(btnResetPassword);
        btnResetPassword.setBounds(32, 150, 216, 32);

        jPanelFormContainer.add(jPanelStep3);
        jPanelStep3.setBounds(0, 0, 280, 230);

        jPanelCard.add(jPanelFormContainer);
        jPanelFormContainer.setBounds(110, 100, 280, 230);

        btnBack.setForeground(new java.awt.Color(75, 85, 99));
        btnBack.setText("← Back to Login");
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        jPanelCard.add(btnBack);
        btnBack.setBounds(60, 340, 380, 30);

        lblError.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 59, 48));
        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelCard.add(lblError);
        lblError.setBounds(20, 372, 460, 20);

        jPanelFooter.setBackground(new java.awt.Color(240, 242, 245));
        jPanelFooter.setLayout(null);

        jLabelFooterStatus.setFont(new java.awt.Font("Segoe UI", 1, 7)); // NOI18N
        jLabelFooterStatus.setForeground(new java.awt.Color(113, 128, 150));
        jLabelFooterStatus.setText("SYSTEM STATUS: OPERATIONAL");
        jPanelFooter.add(jLabelFooterStatus);
        jLabelFooterStatus.setBounds(15, 8, 250, 10);

        jPanelCard.add(jPanelFooter);
        jPanelFooter.setBounds(0, 395, 500, 25);

        getContentPane().add(jPanelCard);
        jPanelCard.setBounds(162, 77, 500, 420);

        pnlBg.setBackground(new java.awt.Color(7, 29, 71));
        pnlBg.setLayout(null);
        getContentPane().add(pnlBg);
        pnlBg.setBounds(0, 0, 825, 575);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
        java.awt.EventQueue.invokeLater(() -> new passwordrecovery().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JButton btnSendOTP;
    private javax.swing.JButton btnVerifyOTP;
    private javax.swing.JLabel jLabelBackHome;
    private javax.swing.JLabel jLabelConfirmPasswordLabel;
    private javax.swing.JLabel jLabelEmailIcon;
    private javax.swing.JLabel jLabelEmailLabel;
    private javax.swing.JLabel jLabelFooterStatus;
    private javax.swing.JLabel jLabelHeaderTitle;
    private javax.swing.JLabel jLabelLockIcon1;
    private javax.swing.JLabel jLabelLockIcon2;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelNewPasswordLabel;
    private javax.swing.JLabel jLabelOTPIcon;
    private javax.swing.JLabel jLabelOTPLabel;
    private javax.swing.JLabel jLabelResetDesc;
    private javax.swing.JLabel jLabelResetDesc2;
    private javax.swing.JLabel jLabelResetTitle;
    private javax.swing.JLabel jLabelResetTitle2;
    private javax.swing.JLabel jLabelResetTitle3;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPanel jPanelConfirmPassword;
    private javax.swing.JPanel jPanelEmailInput;
    private javax.swing.JPanel jPanelFooter;
    private javax.swing.JPanel jPanelFormContainer;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelNewPassword;
    private javax.swing.JPanel jPanelOTPInput;
    private javax.swing.JPanel jPanelStep1;
    private javax.swing.JPanel jPanelStep2;
    private javax.swing.JPanel jPanelStep3;
    private javax.swing.JLabel lblError;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JTextField txtOTP;
    // End of variables declaration//GEN-END:variables

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
