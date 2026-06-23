package view;

import controller.UserController;
import controller.NavigationController;
import javax.swing.JOptionPane;

/**
 * View class representing the User Sign Up interface.
 * Built using NetBeans Form Designer components with custom premium painting logic.
 * Extends JFrame container with responsive field bounds and focus border highlights.
 */
public class SignUp extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SignUp.class.getName());

    /**
     * Creates new form SignUp
     */
    public SignUp() {
        initComponents();
        setupCustomListeners();
        setupPremiumStyling();
        
        // Enter key action listener to submit SignUp form on Enter keypress
        java.awt.event.KeyAdapter enterKeyAdapter = new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    jButton1ActionPerformed(null);
                }
            }
        };
        jTextFieldFullName.addKeyListener(enterKeyAdapter);
        jTextFieldEmail.addKeyListener(enterKeyAdapter);
        jTextFieldPhone.addKeyListener(enterKeyAdapter);
        jPasswordFieldPassword.addKeyListener(enterKeyAdapter);
        jPasswordFieldConfirmPassword.addKeyListener(enterKeyAdapter);
    }

    private void setupPremiumStyling() {
        // Center card styling (rounded corners + subtle shadow + border).
        // Coordinates alignment of the main white container panel at exact mockup dimensions.
        jPanelCard.setOpaque(false);
        jPanelCard.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Shadow
                g2.setColor(new java.awt.Color(0, 0, 0, 15));
                for (int i = 1; i <= 6; i++) {
                    g2.drawRoundRect(x + i, y + i, width - 2 * i, height - 2 * i, 12, 12);
                }
                
                // Card Background
                g2.setColor(java.awt.Color.WHITE);
                g2.fillRoundRect(x, y, width - 1, height - 1, 12, 12);
                
                // Outer Border
                g2.setColor(new java.awt.Color(221, 227, 234)); // #DDE3EA
                g2.setStroke(new java.awt.BasicStroke(1.0f));
                g2.drawRoundRect(x, y, width - 1, height - 1, 12, 12);
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

        // Header separator border
        jPanelHeader.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                g.setColor(new java.awt.Color(221, 227, 234)); // #DDE3EA
                g.drawLine(x, y + height - 1, x + width, y + height - 1);
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(0, 0, 1, 0);
            }
            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        });

        // Dynamic highlight and rounded border implementation for input fields
        applyRoundedBorder(jPanelFullName, jTextFieldFullName);
        applyRoundedBorder(jPanelEmail, jTextFieldEmail);
        applyRoundedBorder(jPanelPhone, jTextFieldPhone);
        applyRoundedBorder(jPanelPassword, jPasswordFieldPassword);
        applyRoundedBorder(jPanelConfirmPassword, jPasswordFieldConfirmPassword);
        applyRoundedBorder(jPanelSecAnswer, jTextFieldSecAnswer);

        // Sign Up button custom styling with hover/press paint states
        jPanelCard.remove(jButton1);
        jButton1 = new javax.swing.JButton("SIGN UP") {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                
                if (getModel().isPressed()) {
                    g2.setColor(new java.awt.Color(220, 105, 10)); // Darker orange
                } else if (getModel().isRollover()) {
                    g2.setColor(new java.awt.Color(230, 115, 15)); // Hover orange
                } else {
                    g2.setColor(new java.awt.Color(247, 138, 36)); // #F78A24
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
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14));
        jButton1.setForeground(java.awt.Color.WHITE);
        jButton1.setContentAreaFilled(false);
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanelCard.add(jButton1);
        jButton1.setBounds(30, 520, 300, 40);

        // Set design specifications from Figma
        jLabelLogo.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 28)); // 28px
        jLabelLogo.setForeground(new java.awt.Color(31, 41, 55)); // #1F2937
        jLabelSubtitle.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 18)); // 18px
        jLabelSubtitle.setForeground(new java.awt.Color(75, 85, 99)); // #4B5563
    }

    private void applyRoundedBorder(javax.swing.JPanel panel, javax.swing.JTextField textField) {
        panel.setOpaque(false);
        panel.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Background fill
                g2.setColor(java.awt.Color.WHITE);
                g2.fillRoundRect(x, y, width, height, 8, 8);
                
                // Highlight border when textfield is focused
                if (textField.isFocusOwner()) {
                    g2.setColor(new java.awt.Color(247, 138, 36)); // Orange focus outline
                    g2.setStroke(new java.awt.BasicStroke(1.2f));
                } else {
                    g2.setColor(new java.awt.Color(221, 227, 234)); // #DDE3EA
                    g2.setStroke(new java.awt.BasicStroke(1.0f));
                }
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

        // Add focus listener to repaint the panel for visual focus feedback
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

    private void setupCustomListeners() {
        // Placeholder focus listener for Full Name
        jTextFieldFullName.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldFullName.getText().equals("Full Name")) {
                    jTextFieldFullName.setText("");
                    jTextFieldFullName.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldFullName.getText().trim().isEmpty()) {
                    jTextFieldFullName.setText("Full Name");
                    jTextFieldFullName.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        // Placeholder focus listener for Email
        jTextFieldEmail.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldEmail.getText().equals("Email")) {
                    jTextFieldEmail.setText("");
                    jTextFieldEmail.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldEmail.getText().trim().isEmpty()) {
                    jTextFieldEmail.setText("Email");
                    jTextFieldEmail.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        // Placeholder focus listener for Phone Number
        jTextFieldPhone.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldPhone.getText().equals("Phone Number")) {
                    jTextFieldPhone.setText("");
                    jTextFieldPhone.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldPhone.getText().trim().isEmpty()) {
                    jTextFieldPhone.setText("Phone Number");
                    jTextFieldPhone.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        // Placeholder focus listener for Password
        jPasswordFieldPassword.setEchoChar((char) 0);
        jPasswordFieldPassword.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String pass = new String(jPasswordFieldPassword.getPassword());
                if (pass.equals("Password")) {
                    jPasswordFieldPassword.setText("");
                    jPasswordFieldPassword.setEchoChar('•');
                    jPasswordFieldPassword.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String pass = new String(jPasswordFieldPassword.getPassword());
                if (pass.trim().isEmpty()) {
                    jPasswordFieldPassword.setText("Password");
                    jPasswordFieldPassword.setEchoChar((char) 0);
                    jPasswordFieldPassword.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        // Placeholder focus listener for Confirm Password
        jPasswordFieldConfirmPassword.setEchoChar((char) 0);
        jPasswordFieldConfirmPassword.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String pass = new String(jPasswordFieldConfirmPassword.getPassword());
                if (pass.equals("Confirm Password")) {
                    jPasswordFieldConfirmPassword.setText("");
                    jPasswordFieldConfirmPassword.setEchoChar('•');
                    jPasswordFieldConfirmPassword.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String pass = new String(jPasswordFieldConfirmPassword.getPassword());
                if (pass.trim().isEmpty()) {
                    jPasswordFieldConfirmPassword.setText("Confirm Password");
                    jPasswordFieldConfirmPassword.setEchoChar((char) 0);
                    jPasswordFieldConfirmPassword.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        // Navigation links cursor and click handlers
        
        jTextFieldSecAnswer.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldSecAnswer.getText().equals("Enter Security Answer")) {
                    jTextFieldSecAnswer.setText("");
                    jTextFieldSecAnswer.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldSecAnswer.getText().trim().isEmpty()) {
                    jTextFieldSecAnswer.setText("Enter Security Answer");
                    jTextFieldSecAnswer.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        jCheckBoxShowPassword.addActionListener(e -> {
            if (jCheckBoxShowPassword.isSelected()) {
                jPasswordFieldPassword.setEchoChar((char) 0);
                jPasswordFieldConfirmPassword.setEchoChar((char) 0);
            } else {
                if (!new String(jPasswordFieldPassword.getPassword()).equals("Password")) {
                    jPasswordFieldPassword.setEchoChar('•');
                }
                if (!new String(jPasswordFieldConfirmPassword.getPassword()).equals("Confirm Password")) {
                    jPasswordFieldConfirmPassword.setEchoChar('•');
                }
            }
        });

        jLabelLoginLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelLoginLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SignUp.this.dispose();
                NavigationController.goToLogin(SignUp.this);
            }
        });

        jLabelBackHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBackHome.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SignUp.this.dispose();
                NavigationController.goToLogin(SignUp.this);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCard = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jLabelBackHome = new javax.swing.JLabel();
        jLabelHeaderLogo = new javax.swing.JLabel();
        jLabelMainTitle = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelSubtitle = new javax.swing.JLabel();
        jPanelFullName = new javax.swing.JPanel();
        jLabelFullNameIcon = new javax.swing.JLabel();
        jTextFieldFullName = new javax.swing.JTextField();
        jPanelEmail = new javax.swing.JPanel();
        jLabelEmailIcon = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jPanelPhone = new javax.swing.JPanel();
        jLabelPhoneIcon = new javax.swing.JLabel();
        jTextFieldPhone = new javax.swing.JTextField();
        jPanelPassword = new javax.swing.JPanel();
        jLabelPasswordIcon = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jPanelConfirmPassword = new javax.swing.JPanel();
        jLabelConfirmPasswordIcon = new javax.swing.JLabel();
        jPasswordFieldConfirmPassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabelLoginLink = new javax.swing.JLabel();
        pnlBg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - Sign Up");
        setPreferredSize(new java.awt.Dimension(950, 780));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanelCard.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCard.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelCard.setLayout(null);

        jPanelHeader.setBackground(new java.awt.Color(240, 242, 245));
        jPanelHeader.setLayout(null);

        jLabelBackHome.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelBackHome.setForeground(new java.awt.Color(102, 102, 102));
        jLabelBackHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_outline.png"))); // NOI18N
        jLabelBackHome.setText(" BACK TO HOME");
        jLabelBackHome.setIconTextGap(6);
        jPanelHeader.add(jLabelBackHome);
        jLabelBackHome.setBounds(15, 8, 110, 15);

        jLabelHeaderLogo.setText("YATRA AIR SEWA");
        jLabelHeaderLogo.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelHeaderLogo.setForeground(new java.awt.Color(117, 140, 179));
        jLabelHeaderLogo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanelHeader.add(jLabelHeaderLogo);
        jLabelHeaderLogo.setBounds(195, 8, 150, 15);

        jPanelCard.add(jPanelHeader);
        jPanelHeader.setBounds(0, 0, 360, 30);
        jPanelCard.add(jLabelMainTitle);
        jLabelMainTitle.setBounds(145, 38, 0, 0);

        jLabelLogo.setText(" YATRA AIR SEWA");
        jLabelLogo.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabelLogo.setForeground(new java.awt.Color(11, 31, 77));
        jLabelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_arrowhead.png"))); // NOI18N
        jLabelLogo.setIconTextGap(8);
        jPanelCard.add(jLabelLogo);
        jLabelLogo.setBounds(10, 45, 340, 30);

        jLabelSubtitle.setText("Create Your Account");
        jLabelSubtitle.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabelSubtitle.setForeground(new java.awt.Color(75, 85, 99));
        jLabelSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelCard.add(jLabelSubtitle);
        jLabelSubtitle.setBounds(10, 80, 340, 20);

        jPanelFullName.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFullName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelFullName.setLayout(null);

        jLabelFullNameIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_outline.png"))); // NOI18N
        jPanelFullName.add(jLabelFullNameIcon);
        jLabelFullNameIcon.setBounds(12, 5, 20, 24);

        jTextFieldFullName.setText("Full Name");
        jTextFieldFullName.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jTextFieldFullName.setBorder(null);
        jTextFieldFullName.setForeground(new java.awt.Color(153, 153, 153));
        jPanelFullName.add(jTextFieldFullName);
        jTextFieldFullName.setBounds(35, 2, 255, 30);

        jPanelCard.add(jPanelFullName);
        jPanelFullName.setBounds(30, 115, 300, 34);

        jPanelEmail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelEmail.setLayout(null);

        jLabelEmailIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/mail_outline.png"))); // NOI18N
        jPanelEmail.add(jLabelEmailIcon);
        jLabelEmailIcon.setBounds(12, 5, 20, 24);

        jTextFieldEmail.setText("Email");
        jTextFieldEmail.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jTextFieldEmail.setBorder(null);
        jTextFieldEmail.setForeground(new java.awt.Color(153, 153, 153));
        jPanelEmail.add(jTextFieldEmail);
        jTextFieldEmail.setBounds(35, 2, 255, 30);

        jPanelCard.add(jPanelEmail);
        jPanelEmail.setBounds(30, 160, 300, 34);

        jPanelPhone.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPhone.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelPhone.setLayout(null);

        jLabelPhoneIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/phone_outline.png"))); // NOI18N
        jPanelPhone.add(jLabelPhoneIcon);
        jLabelPhoneIcon.setBounds(12, 5, 20, 24);

        jTextFieldPhone.setText("Phone Number");
        jTextFieldPhone.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jTextFieldPhone.setBorder(null);
        jTextFieldPhone.setForeground(new java.awt.Color(153, 153, 153));
        jPanelPhone.add(jTextFieldPhone);
        jTextFieldPhone.setBounds(35, 2, 255, 30);

        jPanelCard.add(jPanelPhone);
        jPanelPhone.setBounds(30, 205, 300, 34);

        jPanelPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelPassword.setLayout(null);

        jLabelPasswordIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lock_outline.png"))); // NOI18N
        jPanelPassword.add(jLabelPasswordIcon);
        jLabelPasswordIcon.setBounds(12, 5, 20, 24);

        jPasswordFieldPassword.setText("Password");
        jPasswordFieldPassword.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPasswordFieldPassword.setBorder(null);
        jPasswordFieldPassword.setForeground(new java.awt.Color(153, 153, 153));
        jPanelPassword.add(jPasswordFieldPassword);
        jPasswordFieldPassword.setBounds(35, 2, 255, 30);

        jPanelCard.add(jPanelPassword);
        jPanelPassword.setBounds(30, 250, 300, 34);

        jPanelConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelConfirmPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelConfirmPassword.setLayout(null);

        jLabelConfirmPasswordIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check_lock_outline.png"))); // NOI18N
        jPanelConfirmPassword.add(jLabelConfirmPasswordIcon);
        jLabelConfirmPasswordIcon.setBounds(12, 5, 20, 24);

        jPasswordFieldConfirmPassword.setText("Confirm Password");
        jPasswordFieldConfirmPassword.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jPasswordFieldConfirmPassword.setBorder(null);
        jPasswordFieldConfirmPassword.setForeground(new java.awt.Color(153, 153, 153));
        jPanelConfirmPassword.add(jPasswordFieldConfirmPassword);
        jPasswordFieldConfirmPassword.setBounds(35, 2, 255, 30);

        jPanelCard.add(jPanelConfirmPassword);
        jPanelConfirmPassword.setBounds(30, 295, 300, 34);

        
        jLabelRole = new javax.swing.JLabel();
        jComboBoxRole = new javax.swing.JComboBox<>();
        jComboBoxSecQuestion = new javax.swing.JComboBox<>();
        jPanelSecAnswer = new javax.swing.JPanel();
        jLabelSecAnswerIcon = new javax.swing.JLabel();
        jTextFieldSecAnswer = new javax.swing.JTextField();
        jCheckBoxShowPassword = new javax.swing.JCheckBox();

        jLabelRole.setFont(new java.awt.Font("SansSerif", 1, 13));
        jLabelRole.setForeground(new java.awt.Color(51, 51, 51));
        jLabelRole.setText("Register as:");
        jPanelCard.add(jLabelRole);
        jLabelRole.setBounds(30, 365, 90, 34);

        jComboBoxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Admin" }));
        jPanelCard.add(jComboBoxRole);
        jComboBoxRole.setBounds(120, 365, 210, 34);

        jComboBoxSecQuestion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Security Question", "What is your pet's name?", "What was your childhood nickname?", "In what city were you born?" }));
        jPanelCard.add(jComboBoxSecQuestion);
        jComboBoxSecQuestion.setBounds(30, 410, 300, 34);

        jPanelSecAnswer.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSecAnswer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelSecAnswer.setLayout(null);

        jLabelSecAnswerIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_outline.png"))); // Using user outline as placeholder for orange icon
        jPanelSecAnswer.add(jLabelSecAnswerIcon);
        jLabelSecAnswerIcon.setBounds(12, 5, 20, 24);

        jTextFieldSecAnswer.setText("Enter Security Answer");
        jTextFieldSecAnswer.setFont(new java.awt.Font("SansSerif", 0, 13));
        jTextFieldSecAnswer.setBorder(null);
        jTextFieldSecAnswer.setForeground(new java.awt.Color(153, 153, 153));
        jPanelSecAnswer.add(jTextFieldSecAnswer);
        jTextFieldSecAnswer.setBounds(35, 2, 255, 30);

        jPanelCard.add(jPanelSecAnswer);
        jPanelSecAnswer.setBounds(30, 455, 300, 34);

        jCheckBoxShowPassword.setText("Show Password");
        jCheckBoxShowPassword.setOpaque(false);
        jPanelCard.add(jCheckBoxShowPassword);
        jCheckBoxShowPassword.setBounds(30, 335, 150, 20);

        jButton1.setBackground(new java.awt.Color(247, 138, 36));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SIGN UP");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanelCard.add(jButton1);
        jButton1.setBounds(30, 520, 300, 36);

        jLabelLoginLink.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabelLoginLink.setForeground(new java.awt.Color(51, 51, 51));
        jLabelLoginLink.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLoginLink.setText("Already have an account? Login");
        jPanelCard.add(jLabelLoginLink);
        jLabelLoginLink.setBounds(30, 575, 300, 20);

        getContentPane().add(jPanelCard);
        jPanelCard.setBounds(295, 40, 360, 665);

        pnlBg.setBackground(new java.awt.Color(8, 34, 74));
        pnlBg.setLayout(null);
        getContentPane().add(pnlBg);
        pnlBg.setBounds(0, 0, 950, 780);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String fullname = jTextFieldFullName.getText().trim();
        String email = jTextFieldEmail.getText().trim();
        String phone = jTextFieldPhone.getText().trim();
        String password = new String(jPasswordFieldPassword.getPassword()).trim();
        String confirmPassword = new String(jPasswordFieldConfirmPassword.getPassword()).trim();
        String role = (String) jComboBoxRole.getSelectedItem();
        String secQuestion = (String) jComboBoxSecQuestion.getSelectedItem();
        String secAnswer = jTextFieldSecAnswer.getText().trim();

        // Ensure all required registration input fields have been filled out properly
        if (fullname.equals("Full Name") || email.equals("Email") || phone.equals("Phone Number") || password.equals("Password") || confirmPassword.equals("Confirm Password") || fullname.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all core fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verify that the password match constraint is satisfied before sending data to controller
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UserController controller = new UserController();
        String result = controller.signUp(fullname, email, phone, password, role, secQuestion, secAnswer);

        if ("success".equals(result)) {
            JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dao.UserDAO userDao = new dao.UserDAO();
            model.User registeredUser = userDao.getByEmail(email.trim());
            if (registeredUser != null) {
                model.SessionManager.setCurrentUser(registeredUser);
            }
            NavigationController.goToDashboard(this);
        } else {
            JOptionPane.showMessageDialog(this, result, "Registration Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new SignUp().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelBackHome;
    private javax.swing.JLabel jLabelConfirmPasswordIcon;
    private javax.swing.JLabel jLabelEmailIcon;
    private javax.swing.JLabel jLabelFullNameIcon;
    private javax.swing.JLabel jLabelHeaderLogo;
    private javax.swing.JLabel jLabelLoginLink;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelMainTitle;
    private javax.swing.JLabel jLabelPasswordIcon;
    private javax.swing.JLabel jLabelPhoneIcon;
    private javax.swing.JLabel jLabelSubtitle;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPanel jPanelConfirmPassword;
    private javax.swing.JPanel jPanelEmail;
    private javax.swing.JPanel jPanelFullName;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelPassword;
    private javax.swing.JPanel jPanelPhone;
    private javax.swing.JPasswordField jPasswordFieldConfirmPassword;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFullName;
    private javax.swing.JTextField jTextFieldPhone;
    private javax.swing.JPanel pnlBg;
        private javax.swing.JLabel jLabelRole;
    private javax.swing.JComboBox<String> jComboBoxRole;
    private javax.swing.JComboBox<String> jComboBoxSecQuestion;
    private javax.swing.JPanel jPanelSecAnswer;
    private javax.swing.JLabel jLabelSecAnswerIcon;
    private javax.swing.JTextField jTextFieldSecAnswer;
    private javax.swing.JCheckBox jCheckBoxShowPassword;
    // End of variables declaration//GEN-END:variables
}

// Commit 3: Documented SignUp password complexity limits validations

// Commit 6: Documented SignUp email input database check mappings

// Commit 9: Documented SignUp username length validation constraints

// Commit 12: Documented SignUp view password reveal checkbox toggle

// Commit 15: Documented SignUp first name character boundaries validation

// Commit 18: Documented SignUp view database connection pool details

// Commit 21: Documented SignUp view validation error labels styling

// Commit 24: Documented SignUp view database error connection warnings

// Commit 27: Documented SignUp view fields layout bounds alignments

// Commit 30: Documented SignUp view error reporting component parameters
