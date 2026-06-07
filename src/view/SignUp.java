package view;

import Controller.UserController;
import javax.swing.JOptionPane;

public class SignUp extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SignUp.class.getName());

    /**
     * Creates new form SignUp
     */
    public SignUp() {
        initComponents();
        setupCustomListeners();
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
        jPasswordFieldPassword.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String pass = new String(jPasswordFieldPassword.getPassword());
                if (pass.equals("Password")) {
                    jPasswordFieldPassword.setText("");
                    jPasswordFieldPassword.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String pass = new String(jPasswordFieldPassword.getPassword());
                if (pass.trim().isEmpty()) {
                    jPasswordFieldPassword.setText("Password");
                    jPasswordFieldPassword.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        // Placeholder focus listener for Confirm Password
        jPasswordFieldConfirmPassword.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String pass = new String(jPasswordFieldConfirmPassword.getPassword());
                if (pass.equals("Confirm Password")) {
                    jPasswordFieldConfirmPassword.setText("");
                    jPasswordFieldConfirmPassword.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String pass = new String(jPasswordFieldConfirmPassword.getPassword());
                if (pass.trim().isEmpty()) {
                    jPasswordFieldConfirmPassword.setText("Confirm Password");
                    jPasswordFieldConfirmPassword.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        // Navigation link
        jLabelLoginLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelLoginLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new SignIn().setVisible(true);
                dispose();
            }
        });

        jLabelBackHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBackHome.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(SignUp.this, "Returning to Home page...", "Home", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        jPanelCard = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jLabelBackHome = new javax.swing.JLabel();
        jLabelHeaderLogo = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelMainTitle = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Flight Booking System - Sign Up");
        getContentPane().setLayout(null);

        // Frame Background: Deep Navy Blue (#0F2537)
        getContentPane().setBackground(new java.awt.Color(15, 37, 55));

        // White Card Panel
        jPanelCard.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCard.setLayout(null);

        // Header Panel inside Card
        jPanelHeader.setBackground(new java.awt.Color(240, 242, 245));
        jPanelHeader.setLayout(null);

        jLabelBackHome.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelBackHome.setForeground(new java.awt.Color(102, 102, 102));
        jLabelBackHome.setText("⌂ BACK TO HOME");
        jPanelHeader.add(jLabelBackHome);
        jLabelBackHome.setBounds(10, 10, 120, 13);

        jLabelHeaderLogo.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelHeaderLogo.setForeground(new java.awt.Color(117, 140, 179));
        jLabelHeaderLogo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelHeaderLogo.setText("YATRA AIR SEWA");
        jPanelHeader.add(jLabelHeaderLogo);
        jLabelHeaderLogo.setBounds(210, 10, 120, 13);

        jPanelCard.add(jPanelHeader);
        jPanelHeader.setBounds(0, 0, 340, 35);

        // Stylized logo (Triangle)
        jLabelLogo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelLogo.setForeground(new java.awt.Color(15, 37, 55));
        jLabelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogo.setText("▼");
        jPanelCard.add(jLabelLogo);
        jLabelLogo.setBounds(155, 42, 30, 30);

        // Main Title
        jLabelMainTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelMainTitle.setForeground(new java.awt.Color(15, 37, 55));
        jLabelMainTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMainTitle.setText("YATRA AIR SEWA");
        jPanelCard.add(jLabelMainTitle);
        jLabelMainTitle.setBounds(20, 72, 300, 25);

        // Subtitle
        jLabelSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabelSubtitle.setForeground(new java.awt.Color(102, 102, 102));
        jLabelSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSubtitle.setText("Create Your Account");
        jPanelCard.add(jLabelSubtitle);
        jLabelSubtitle.setBounds(20, 98, 300, 16);

        // Full Name Container Panel
        jPanelFullName.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFullName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanelFullName.setLayout(null);

        jLabelFullNameIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelFullNameIcon.setForeground(new java.awt.Color(153, 153, 153));
        jLabelFullNameIcon.setText("  👤 ");
        jPanelFullName.add(jLabelFullNameIcon);
        jLabelFullNameIcon.setBounds(5, 5, 25, 26);

        jTextFieldFullName.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldFullName.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldFullName.setText("Full Name");
        jTextFieldFullName.setBorder(null);
        jPanelFullName.add(jTextFieldFullName);
        jTextFieldFullName.setBounds(35, 5, 240, 26);

        jPanelCard.add(jPanelFullName);
        jPanelFullName.setBounds(30, 125, 280, 36);

        // Email Container Panel
        jPanelEmail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanelEmail.setLayout(null);

        jLabelEmailIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelEmailIcon.setForeground(new java.awt.Color(153, 153, 153));
        jLabelEmailIcon.setText("  ✉ ");
        jPanelEmail.add(jLabelEmailIcon);
        jLabelEmailIcon.setBounds(5, 5, 25, 26);

        jTextFieldEmail.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldEmail.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldEmail.setText("Email");
        jTextFieldEmail.setBorder(null);
        jPanelEmail.add(jTextFieldEmail);
        jTextFieldEmail.setBounds(35, 5, 240, 26);

        jPanelCard.add(jPanelEmail);
        jPanelEmail.setBounds(30, 170, 280, 36);

        // Phone Container Panel
        jPanelPhone.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPhone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanelPhone.setLayout(null);

        jLabelPhoneIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelPhoneIcon.setForeground(new java.awt.Color(153, 153, 153));
        jLabelPhoneIcon.setText("  📞 ");
        jPanelPhone.add(jLabelPhoneIcon);
        jLabelPhoneIcon.setBounds(5, 5, 25, 26);

        jTextFieldPhone.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldPhone.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldPhone.setText("Phone Number");
        jTextFieldPhone.setBorder(null);
        jPanelPhone.add(jTextFieldPhone);
        jTextFieldPhone.setBounds(35, 5, 240, 26);

        jPanelCard.add(jPanelPhone);
        jPanelPhone.setBounds(30, 215, 280, 36);

        // Password Container Panel
        jPanelPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanelPassword.setLayout(null);

        jLabelPasswordIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelPasswordIcon.setForeground(new java.awt.Color(153, 153, 153));
        jLabelPasswordIcon.setText("  🔒 ");
        jPanelPassword.add(jLabelPasswordIcon);
        jLabelPasswordIcon.setBounds(5, 5, 25, 26);

        jPasswordFieldPassword.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jPasswordFieldPassword.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordFieldPassword.setText("Password");
        jPasswordFieldPassword.setBorder(null);
        jPanelPassword.add(jPasswordFieldPassword);
        jPasswordFieldPassword.setBounds(35, 5, 240, 26);

        jPanelCard.add(jPanelPassword);
        jPanelPassword.setBounds(30, 260, 280, 36);

        // Confirm Password Container Panel
        jPanelConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanelConfirmPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanelConfirmPassword.setLayout(null);

        jLabelConfirmPasswordIcon.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelConfirmPasswordIcon.setForeground(new java.awt.Color(153, 153, 153));
        jLabelConfirmPasswordIcon.setText("  🛡 ");
        jPanelConfirmPassword.add(jLabelConfirmPasswordIcon);
        jLabelConfirmPasswordIcon.setBounds(5, 5, 25, 26);

        jPasswordFieldConfirmPassword.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jPasswordFieldConfirmPassword.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordFieldConfirmPassword.setText("Confirm Password");
        jPasswordFieldConfirmPassword.setBorder(null);
        jPanelConfirmPassword.add(jPasswordFieldConfirmPassword);
        jPasswordFieldConfirmPassword.setBounds(35, 5, 240, 26);

        jPanelCard.add(jPanelConfirmPassword);
        jPanelConfirmPassword.setBounds(30, 305, 280, 36);

        // Orange Sign Up Button
        jButton1.setBackground(new java.awt.Color(245, 130, 32));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SIGN UP");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanelCard.add(jButton1);
        jButton1.setBounds(30, 360, 280, 38);

        // Already have an account Link Label
        jLabelLoginLink.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabelLoginLink.setForeground(new java.awt.Color(51, 51, 51));
        jLabelLoginLink.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLoginLink.setText("Already have an account? Login");
        jPanelCard.add(jLabelLoginLink);
        jLabelLoginLink.setBounds(30, 420, 280, 15);

        getContentPane().add(jPanelCard);
        jPanelCard.setBounds(230, 25, 340, 475);

        // Set dimensions & center
        setPreferredSize(new java.awt.Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String fullname = jTextFieldFullName.getText();
        String email = jTextFieldEmail.getText();
        String phone = jTextFieldPhone.getText();
        String password = new String(jPasswordFieldPassword.getPassword());
        String confirmPassword = new String(jPasswordFieldConfirmPassword.getPassword());

        if (fullname.equals("Full Name") || email.equals("Email") || phone.equals("Phone Number") || password.equals("Password") || confirmPassword.equals("Confirm Password")) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UserController controller = new UserController();
        String result = controller.signUp(fullname, email, phone, password);

        if ("success".equals(result)) {
            JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new SignIn().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, result, "Registration Error", JOptionPane.ERROR_MESSAGE);
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
        java.awt.EventQueue.invokeLater(() -> new SignUp().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelBackHome;
    private javax.swing.JLabel jLabelEmailIcon;
    private javax.swing.JLabel jLabelFullNameIcon;
    private javax.swing.JLabel jLabelHeaderLogo;
    private javax.swing.JLabel jLabelLoginLink;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelMainTitle;
    private javax.swing.JLabel jLabelPhoneIcon;
    private javax.swing.JLabel jLabelPasswordIcon;
    private javax.swing.JLabel jLabelConfirmPasswordIcon;
    private javax.swing.JLabel jLabelSubtitle;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPanel jPanelEmail;
    private javax.swing.JPanel jPanelFullName;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelPhone;
    private javax.swing.JPanel jPanelPassword;
    private javax.swing.JPanel jPanelConfirmPassword;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JPasswordField jPasswordFieldConfirmPassword;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFullName;
    private javax.swing.JTextField jTextFieldPhone;
    // End of variables declaration
}
