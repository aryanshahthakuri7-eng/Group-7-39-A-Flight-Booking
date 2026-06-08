package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.*;
import Controller.UserController;
import model.User;

public class ProfilePanel extends JPanel {
    private final Dashboard dashboard;
    private final UserController userController;

    // Form inputs
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;

    public ProfilePanel(Dashboard dashboard) {
        this.dashboard = dashboard;
        this.userController = new UserController();
        initComponents();
        loadUserProfile();
    }

    private void loadUserProfile() {
        User user = dashboard.getCurrentUser();
        if (user != null) {
            txtName.setText(user.getFullname());
            txtEmail.setText(user.getEmail());
            txtPhone.setText(user.getPhone());
            txtPassword.setText(user.getPassword());
            txtConfirmPassword.setText(user.getPassword());
        }
    }

    private void initComponents() {
        setBackground(new Color(248, 249, 255));
        setLayout(null);
        setSize(780, 630);

        // Back Button
        JLabel lblBack = new JLabel("← Back to Dashboard");
        lblBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBack.setForeground(new Color(245, 130, 32));
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBack.setBounds(30, 20, 150, 20);
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard.switchPanel(null); // Go to main dashboard homepage
            }
        });
        add(lblBack);

        // Header Title
        JLabel lblTitle = new JLabel("Profile Settings");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(8, 22, 42));
        lblTitle.setBounds(30, 50, 300, 30);
        add(lblTitle);

        // Subtitle
        JLabel lblSub = new JLabel("Update your personal account credentials and phone numbers.");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(113, 128, 150));
        lblSub.setBounds(30, 80, 500, 20);
        add(lblSub);

        // Profile Card Layout
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        card.setLayout(null);
        card.setBounds(30, 120, 720, 470);

        // Left section - Avatar / Status
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(248, 249, 255));
        leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(226, 232, 240)));
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 0, 220, 470);

        JLabel lblAvatar = new JLabel("👤");
        lblAvatar.setFont(new Font("Segoe UI", Font.PLAIN, 80));
        lblAvatar.setHorizontalAlignment(JLabel.CENTER);
        lblAvatar.setBounds(20, 50, 180, 100);
        leftPanel.add(lblAvatar);

        JLabel lblMember = new JLabel("YATRA ELITE MEMBER");
        lblMember.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblMember.setForeground(new Color(245, 130, 32)); // Orange
        lblMember.setHorizontalAlignment(JLabel.CENTER);
        lblMember.setBounds(10, 160, 200, 20);
        leftPanel.add(lblMember);

        JLabel lblMemberDesc = new JLabel("Enjoy 10% discounts on booking");
        lblMemberDesc.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        lblMemberDesc.setForeground(new Color(113, 128, 150));
        lblMemberDesc.setHorizontalAlignment(JLabel.CENTER);
        lblMemberDesc.setBounds(10, 180, 200, 15);
        leftPanel.add(lblMemberDesc);

        card.add(leftPanel);

        // Right section - Form inputs
        int xOffset = 250;
        
        JLabel lblName = new JLabel("Full Name");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblName.setForeground(new Color(113, 128, 150));
        lblName.setBounds(xOffset, 30, 200, 15);
        card.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(xOffset, 50, 430, 34);
        card.add(txtName);

        JLabel lblEmail = new JLabel("Email Address (Registered ID)");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEmail.setForeground(new Color(113, 128, 150));
        lblEmail.setBounds(xOffset, 100, 250, 15);
        card.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(xOffset, 120, 430, 34);
        txtEmail.setEditable(false);
        txtEmail.setBackground(new Color(241, 245, 249)); // Greyed out read only
        txtEmail.setForeground(Color.GRAY);
        card.add(txtEmail);

        JLabel lblPhone = new JLabel("Phone Number");
        lblPhone.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblPhone.setForeground(new Color(113, 128, 150));
        lblPhone.setBounds(xOffset, 170, 200, 15);
        card.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(xOffset, 190, 430, 34);
        card.add(txtPhone);

        JLabel lblPassword = new JLabel("New Password");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblPassword.setForeground(new Color(113, 128, 150));
        lblPassword.setBounds(xOffset, 240, 200, 15);
        card.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(xOffset, 260, 430, 34);
        card.add(txtPassword);

        JLabel lblConfirmPassword = new JLabel("Confirm New Password");
        lblConfirmPassword.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblConfirmPassword.setForeground(new Color(113, 128, 150));
        lblConfirmPassword.setBounds(xOffset, 310, 200, 15);
        card.add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(xOffset, 330, 430, 34);
        card.add(txtConfirmPassword);

        // Update profile button
        JButton btnSave = new JButton("Save Profile Updates");
        btnSave.setBackground(new Color(245, 130, 32)); // Orange
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSave.setForeground(Color.WHITE);
        btnSave.setBorderPainted(false);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setBounds(xOffset, 395, 430, 45);
        btnSave.addActionListener(e -> saveProfileChanges());
        card.add(btnSave);

        add(card);
    }

    private void saveProfileChanges() {
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (name.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all details.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = dashboard.getCurrentUser();
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Session invalid. Please login again.", "Session Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Set values and execute update
        user.setFullname(name);
        user.setPhone(phone);
        user.setPassword(password);

        String result = userController.updateProfile(user);

        if (result.equals("success")) {
            JOptionPane.showMessageDialog(this, "Profile settings updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Sync with parent layout
            dashboard.setCurrentUser(user);
            dashboard.updateGreeting();
        } else {
            JOptionPane.showMessageDialog(this, result, "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
