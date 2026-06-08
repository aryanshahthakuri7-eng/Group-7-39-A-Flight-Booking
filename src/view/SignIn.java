package view;

import Controller.UserController;
import model.User;
import javax.swing.JOptionPane;

public class SignIn extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SignIn.class.getName());

    /**
     * Creates new form SignIn
     */
    public SignIn() {
        initComponents();
        setupCustomListeners();
    }

    private void setupCustomListeners() {
        // Focus listeners for Email placeholder
        jTextFieldUsername.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldUsername.getText().equals("Email")) {
                    jTextFieldUsername.setText("");
                    jTextFieldUsername.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldUsername.getText().trim().isEmpty()) {
                    jTextFieldUsername.setText("Email");
                    jTextFieldUsername.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });

        // Focus listeners for Password placeholder
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

        // Mouse listeners for navigation
        jLabelSignUpLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelSignUpLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new SignUp().setVisible(true);
                dispose();
            }
        });

        jLabelForgotPwdLink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelForgotPwdLink.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Passwordrecovery().setVisible(true);
                dispose();
            }
        });

        jLabelBackHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBackHome.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(SignIn.this, "Returning to Home page...", "Home", JOptionPane.INFORMATION_MESSAGE);
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
        jPanelEmail = new javax.swing.JPanel();
        jLabelEmailIcon = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jPanelPassword = new javax.swing.JPanel();
        jLabelPasswordIcon = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jLabelForgotPwdLink = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparatorLeft = new javax.swing.JSeparator();
        jLabelOr = new javax.swing.JLabel();
        jSeparatorRight = new javax.swing.JSeparator();
        jButtonGoogle = new javax.swing.JButton();
        jLabelSignUpLink = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Flight Booking System - Sign In");
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
        jLabelLogo.setBounds(155, 50, 30, 30);

        // Main Title
        jLabelMainTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelMainTitle.setForeground(new java.awt.Color(15, 37, 55));
        jLabelMainTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMainTitle.setText("YATRA AIR SEWA");
        jPanelCard.add(jLabelMainTitle);
        jLabelMainTitle.setBounds(20, 85, 300, 25);

        // Subtitle
        jLabelSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelSubtitle.setForeground(new java.awt.Color(102, 102, 102));
        jLabelSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSubtitle.setText("Welcome Back!");
        jPanelCard.add(jLabelSubtitle);
        jLabelSubtitle.setBounds(20, 115, 300, 20);

        // Email Container Panel
        jPanelEmail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanelEmail.setLayout(null);

        jLabelEmailIcon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelEmailIcon.setForeground(new java.awt.Color(153, 153, 153));
        jLabelEmailIcon.setText("  ✉ ");
        jPanelEmail.add(jLabelEmailIcon);
        jLabelEmailIcon.setBounds(5, 5, 25, 26);

        jTextFieldUsername.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTextFieldUsername.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldUsername.setText("Email");
        jTextFieldUsername.setBorder(null);
        jPanelEmail.add(jTextFieldUsername);
        jTextFieldUsername.setBounds(35, 5, 240, 26);

        jPanelCard.add(jPanelEmail);
        jPanelEmail.setBounds(30, 150, 280, 36);

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
        jPanelPassword.setBounds(30, 195, 280, 36);

        // Forgot Password
        jLabelForgotPwdLink.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabelForgotPwdLink.setForeground(new java.awt.Color(0, 102, 204));
        jLabelForgotPwdLink.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelForgotPwdLink.setText("Forgot Password?");
        jPanelCard.add(jLabelForgotPwdLink);
        jLabelForgotPwdLink.setBounds(190, 235, 120, 14);

        // Orange Login Button
        jButton1.setBackground(new java.awt.Color(245, 130, 32));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("LOGIN");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jPanelCard.add(jButton1);
        jButton1.setBounds(30, 260, 280, 38);

        // Left Separator
        jSeparatorLeft.setForeground(new java.awt.Color(224, 224, 224));
        jPanelCard.add(jSeparatorLeft);
        jSeparatorLeft.setBounds(30, 320, 115, 5);

        // "or" label
        jLabelOr.setFont(new java.awt.Font("Segoe UI", 2, 11)); // NOI18N
        jLabelOr.setForeground(new java.awt.Color(153, 153, 153));
        jLabelOr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOr.setText("or");
        jPanelCard.add(jLabelOr);
        jLabelOr.setBounds(155, 310, 30, 20);

        // Right Separator
        jSeparatorRight.setForeground(new java.awt.Color(224, 224, 224));
        jPanelCard.add(jSeparatorRight);
        jSeparatorRight.setBounds(195, 320, 115, 5);

        // Google Button
        jButtonGoogle.setBackground(new java.awt.Color(255, 255, 255));
        jButtonGoogle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButtonGoogle.setForeground(new java.awt.Color(51, 51, 51));
        jButtonGoogle.setText("G   Login with Google");
        jButtonGoogle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanelCard.add(jButtonGoogle);
        jButtonGoogle.setBounds(30, 350, 280, 38);

        // Sign Up Link Label
        jLabelSignUpLink.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabelSignUpLink.setForeground(new java.awt.Color(51, 51, 51));
        jLabelSignUpLink.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSignUpLink.setText("Don't have an account? Sign Up");
        jPanelCard.add(jLabelSignUpLink);
        jLabelSignUpLink.setBounds(30, 415, 280, 15);

        getContentPane().add(jPanelCard);
        jPanelCard.setBounds(230, 50, 340, 475);

        // Set dimensions & center
        setPreferredSize(new java.awt.Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String username = jTextFieldUsername.getText();
        String password = new String(jPasswordFieldPassword.getPassword());

        if (username.equals("Email") || password.equals("Password")) {
            JOptionPane.showMessageDialog(this, "Please enter your credentials.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UserController controller = new UserController();
        User user = controller.signIn(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Welcome " + user.getFullname() + "! Login Successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            new Dashboard(user).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password.", "Login Error", JOptionPane.ERROR_MESSAGE);
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
        java.awt.EventQueue.invokeLater(() -> new SignIn().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonGoogle;
    private javax.swing.JLabel jLabelBackHome;
    private javax.swing.JLabel jLabelEmailIcon;
    private javax.swing.JLabel jLabelForgotPwdLink;
    private javax.swing.JLabel jLabelHeaderLogo;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelMainTitle;
    private javax.swing.JLabel jLabelOr;
    private javax.swing.JLabel jLabelPasswordIcon;
    private javax.swing.JLabel jLabelSignUpLink;
    private javax.swing.JLabel jLabelSubtitle;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPanel jPanelEmail;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelPassword;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JSeparator jSeparatorLeft;
    private javax.swing.JSeparator jSeparatorRight;
    private javax.swing.JTextField jTextFieldUsername;
    // End of variables declaration
}
