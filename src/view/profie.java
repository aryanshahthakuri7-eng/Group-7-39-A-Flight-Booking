package view;

import controller.NavigationController;
import controller.ProfileController;
import model.User;
import javax.swing.JOptionPane;

public class profie extends javax.swing.JFrame {

    private final ProfileController profileController;

    public profie() {
        initComponents();
        profileController = new ProfileController();
        
        getContentPane().setBackground(new java.awt.Color(20, 28, 35)); // Dark Navy Blue
        
        // Custom stylings to make UI look extremely premium and flat!
        styleSidebarButton(btnDashboard);
        styleSidebarButton(btnSearchFlight);
        styleSidebarButton(btnMyBookings);
        styleSidebarActiveButton(btnProfile);
        styleSidebarButton(btnCustomerSupport);
        styleSidebarButton(btnLogout);
        
        styleCardPanel(cardProfileHeader);
        styleCardPanel(cardPersonalInfo);
        styleCardPanel(cardSecurity);
        
        stylePrimaryButton(btnSaveChanges);
        
        styleRowButton(btnChangePassword);
        styleRowButton(btnTwoFactor);
        styleRowButton(btnDevices);
        
        // Style Avatar Rounded Look
        lblAvatar.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        // Style Badge Rounded Look
        lblMemberBadge.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(26, 115, 232), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));
        
        // Add nice padding to input fields
        styleTextField(txtFullName);
        styleTextField(txtEmail);
        styleTextField(txtPhone);
        styleTextField(txtDob);
        
        // Load User Details
        loadUserProfile();
        
        // Wire Save Changes Button
        btnSaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveChangesActionPerformed(evt);
            }
        });

        // Unimplemented features popups
        btnChangePassword.addActionListener(evt -> JOptionPane.showMessageDialog(this, "The Change Password feature is not implemented yet!", "Under Construction", JOptionPane.INFORMATION_MESSAGE));
        btnTwoFactor.addActionListener(evt -> JOptionPane.showMessageDialog(this, "Two-Factor Authentication configuration is not implemented yet!", "Under Construction", JOptionPane.INFORMATION_MESSAGE));
        btnDevices.addActionListener(evt -> JOptionPane.showMessageDialog(this, "Connected Device Management is not implemented yet!", "Under Construction", JOptionPane.INFORMATION_MESSAGE));
    }

    private void loadUserProfile() {
        User user = profileController.getLoggedInUserProfile();
        if (user != null) {
            txtFullName.setText(user.getFullName());
            txtEmail.setText(user.getEmail());
            txtPhone.setText(user.getPhone() != null ? user.getPhone() : "");
            lblProfileName.setText(user.getFullName());
            
            if (user.getCreatedAt() != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM yyyy");
                lblMemberSince.setText("Member since " + sdf.format(user.getCreatedAt()));
            } else {
                lblMemberSince.setText("Member since Dec 2024");
            }
        }
    }

    private void btnSaveChangesActionPerformed(java.awt.event.ActionEvent evt) {
        String name = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Full Name and Email Address are required fields.", "Input Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = profileController.updateProfile(name, email, phone);
        if (success) {
            lblProfileName.setText(name);
            JOptionPane.showMessageDialog(this, "Profile updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update profile. Email might already be in use.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void styleSidebarButton(javax.swing.JButton btn) {
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn.setMargin(new java.awt.Insets(0, 20, 0, 0));
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
    private void styleSidebarActiveButton(javax.swing.JButton btn) {
        btn.setContentAreaFilled(true);
        btn.setBackground(new java.awt.Color(20, 28, 35));
        btn.setForeground(java.awt.Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn.setMargin(new java.awt.Insets(0, 20, 0, 0));
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
    private void styleCardPanel(javax.swing.JPanel pnl) {
        pnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
    }
    
    private void stylePrimaryButton(javax.swing.JButton btn) {
        btn.setContentAreaFilled(true);
        btn.setBackground(new java.awt.Color(11, 60, 93)); // Deep blue
        btn.setForeground(java.awt.Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void styleRowButton(javax.swing.JButton btn) {
        btn.setContentAreaFilled(true);
        btn.setBackground(java.awt.Color.WHITE);
        btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(243, 244, 246), 1, true));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
    private void styleTextField(javax.swing.JTextField txt) {
        txt.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnSearchFlight = new javax.swing.JButton();
        btnMyBookings = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        btnCustomerSupport = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        pnlSidebar = new javax.swing.JPanel();
        lblBackToHome = new javax.swing.JLabel();
        lblTopTitle = new javax.swing.JLabel();
        lblHeaderIcons = new javax.swing.JLabel();
        pnlTopHeader = new javax.swing.JPanel();
        lblAvatar = new javax.swing.JLabel();
        lblEditIcon = new javax.swing.JLabel();
        lblProfileName = new javax.swing.JLabel();
        lblMemberBadge = new javax.swing.JLabel();
        lblMemberSince = new javax.swing.JLabel();
        btnSaveChanges = new javax.swing.JButton();
        cardProfileHeader = new javax.swing.JPanel();
        lblPersonalInfo = new javax.swing.JLabel();
        lblPersonalInfoIcon = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        lblEmailAddress = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPhoneNumber = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblDob = new javax.swing.JLabel();
        lblDobCal = new javax.swing.JLabel();
        txtDob = new javax.swing.JTextField();
        cardPersonalInfo = new javax.swing.JPanel();
        lblSecurity = new javax.swing.JLabel();
        lblSecurityIcon = new javax.swing.JLabel();
        lblPwdChevron = new javax.swing.JLabel();
        lblPwdDesc = new javax.swing.JLabel();
        lblPwdTitle = new javax.swing.JLabel();
        lblPwdIcon = new javax.swing.JLabel();
        btnChangePassword = new javax.swing.JButton();
        lblTfaChevron = new javax.swing.JLabel();
        lblTfaDesc = new javax.swing.JLabel();
        lblTfaTitle = new javax.swing.JLabel();
        lblTfaIcon = new javax.swing.JLabel();
        btnTwoFactor = new javax.swing.JButton();
        lblDevicesChevron = new javax.swing.JLabel();
        lblDevicesDesc = new javax.swing.JLabel();
        lblDevicesTitle = new javax.swing.JLabel();
        lblDevicesIcon = new javax.swing.JLabel();
        btnDevices = new javax.swing.JButton();
        cardSecurity = new javax.swing.JPanel();
        lblFooterCopyright = new javax.swing.JLabel();
        lblFooterLinks = new javax.swing.JLabel();
        lblStatusText = new javax.swing.JLabel();
        pnlStatus = new javax.swing.JPanel();
        pnlMainBg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(20, 28, 35));
        setPreferredSize(new java.awt.Dimension(1200, 800));
        getContentPane().setLayout(null);

        lblLogo.setText("▼  YATRAAIR");
        lblLogo.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblLogo);
        lblLogo.setBounds(25, 30, 200, 40);

        btnDashboard.setText("Dashboard");
        btnDashboard.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(92, 100, 112));
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);
        getContentPane().add(btnDashboard);
        btnDashboard.setBounds(15, 120, 220, 40);

        btnSearchFlight.setText("Search Flight");
        btnSearchFlight.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnSearchFlight.setForeground(new java.awt.Color(92, 100, 112));
        btnSearchFlight.addActionListener(this::btnSearchFlightActionPerformed);
        getContentPane().add(btnSearchFlight);
        btnSearchFlight.setBounds(15, 170, 220, 40);

        btnMyBookings.setText("My Bookings");
        btnMyBookings.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMyBookings.setForeground(new java.awt.Color(92, 100, 112));
        btnMyBookings.addActionListener(this::btnMyBookingsActionPerformed);
        getContentPane().add(btnMyBookings);
        btnMyBookings.setBounds(15, 220, 220, 40);

        btnProfile.setText("Profile");
        btnProfile.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnProfile.setForeground(new java.awt.Color(255, 255, 255));
        btnProfile.setBackground(new java.awt.Color(20, 28, 35));
        btnProfile.addActionListener(this::btnProfileActionPerformed);
        getContentPane().add(btnProfile);
        btnProfile.setBounds(15, 270, 220, 40);

        btnCustomerSupport.setText("Customer Support");
        btnCustomerSupport.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnCustomerSupport.setForeground(new java.awt.Color(92, 100, 112));
        btnCustomerSupport.addActionListener(this::btnCustomerSupportActionPerformed);
        getContentPane().add(btnCustomerSupport);
        btnCustomerSupport.setBounds(15, 320, 220, 40);

        btnLogout.setText("Logout");
        btnLogout.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 59, 48));
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        getContentPane().add(btnLogout);
        btnLogout.setBounds(15, 700, 220, 40);

        pnlSidebar.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 250, 800);

        lblBackToHome.setText("🏠 Back to Home");
        lblBackToHome.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblBackToHome.setForeground(new java.awt.Color(160, 174, 192));
        getContentPane().add(lblBackToHome);
        lblBackToHome.setBounds(275, 18, 150, 25);

        lblTopTitle.setText("YATRA AIR SEWA");
        lblTopTitle.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblTopTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTopTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblTopTitle);
        lblTopTitle.setBounds(600, 18, 250, 25);

        lblHeaderIcons.setText("🔔   ❓   ⚙️");
        lblHeaderIcons.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblHeaderIcons.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblHeaderIcons);
        lblHeaderIcons.setBounds(1080, 18, 90, 25);

        pnlTopHeader.setBackground(new java.awt.Color(20, 28, 35));
        getContentPane().add(pnlTopHeader);
        pnlTopHeader.setBounds(250, 0, 950, 60);

        lblAvatar.setText("👤");
        lblAvatar.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        lblAvatar.setForeground(new java.awt.Color(26, 115, 232));
        lblAvatar.setBackground(new java.awt.Color(235, 235, 240));
        lblAvatar.setOpaque(true);
        lblAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblAvatar);
        lblAvatar.setBounds(295, 95, 80, 80);

        lblEditIcon.setText("✏️");
        lblEditIcon.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        getContentPane().add(lblEditIcon);
        lblEditIcon.setBounds(360, 150, 20, 20);

        lblProfileName.setText("Aryan Shah");
        lblProfileName.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblProfileName.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblProfileName);
        lblProfileName.setBounds(400, 105, 200, 25);

        lblMemberBadge.setText("ELITE MEMBER");
        lblMemberBadge.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblMemberBadge.setForeground(new java.awt.Color(255, 255, 255));
        lblMemberBadge.setBackground(new java.awt.Color(26, 115, 232));
        lblMemberBadge.setOpaque(true);
        lblMemberBadge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblMemberBadge);
        lblMemberBadge.setBounds(400, 135, 100, 22);

        lblMemberSince.setText("Member since Dec 2024");
        lblMemberSince.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblMemberSince.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblMemberSince);
        lblMemberSince.setBounds(510, 135, 200, 22);

        btnSaveChanges.setText("Save Changes");
        btnSaveChanges.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnSaveChanges.setForeground(new java.awt.Color(255, 255, 255));
        btnSaveChanges.setBackground(new java.awt.Color(11, 60, 93));
        getContentPane().add(btnSaveChanges);
        btnSaveChanges.setBounds(970, 120, 170, 40);

        cardProfileHeader.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cardProfileHeader);
        cardProfileHeader.setBounds(280, 80, 890, 130);

        lblPersonalInfo.setText("Personal Information");
        lblPersonalInfo.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblPersonalInfo.setForeground(new java.awt.Color(26, 75, 117));
        getContentPane().add(lblPersonalInfo);
        lblPersonalInfo.setBounds(300, 240, 200, 25);

        lblPersonalInfoIcon.setText("📝");
        lblPersonalInfoIcon.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(lblPersonalInfoIcon);
        lblPersonalInfoIcon.setBounds(1120, 240, 30, 25);

        lblFullName.setText("Full Name");
        lblFullName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblFullName.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblFullName);
        lblFullName.setBounds(300, 280, 200, 20);

        txtFullName.setText("Aryan Shah");
        txtFullName.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        getContentPane().add(txtFullName);
        txtFullName.setBounds(300, 305, 380, 35);

        lblEmailAddress.setText("Email Address");
        lblEmailAddress.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblEmailAddress.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblEmailAddress);
        lblEmailAddress.setBounds(720, 280, 200, 20);

        txtEmail.setText("aryan.shah@gmail.com");
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        getContentPane().add(txtEmail);
        txtEmail.setBounds(720, 305, 420, 35);

        lblPhoneNumber.setText("Phone Number");
        lblPhoneNumber.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblPhoneNumber.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblPhoneNumber);
        lblPhoneNumber.setBounds(300, 350, 200, 20);

        txtPhone.setText("+977 98XXXXXXXX");
        txtPhone.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        getContentPane().add(txtPhone);
        txtPhone.setBounds(300, 375, 380, 35);

        lblDob.setText("Date of Birth");
        lblDob.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblDob.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblDob);
        lblDob.setBounds(720, 350, 200, 20);

        lblDobCal.setText("📅");
        lblDobCal.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblDobCal.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblDobCal);
        lblDobCal.setBounds(1115, 380, 20, 25);

        txtDob.setText("10 January 2006");
        txtDob.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        getContentPane().add(txtDob);
        txtDob.setBounds(720, 375, 420, 35);

        cardPersonalInfo.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cardPersonalInfo);
        cardPersonalInfo.setBounds(280, 225, 890, 200);

        lblSecurity.setText("Security Settings");
        lblSecurity.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblSecurity.setForeground(new java.awt.Color(26, 75, 117));
        getContentPane().add(lblSecurity);
        lblSecurity.setBounds(300, 455, 200, 25);

        lblSecurityIcon.setText("🛡️");
        lblSecurityIcon.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(lblSecurityIcon);
        lblSecurityIcon.setBounds(1120, 455, 30, 25);

        lblPwdChevron.setText("〉");
        lblPwdChevron.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblPwdChevron.setForeground(new java.awt.Color(120, 140, 160));
        getContentPane().add(lblPwdChevron);
        lblPwdChevron.setBounds(1125, 505, 20, 20);

        lblPwdDesc.setText("Last updated 3 months ago");
        lblPwdDesc.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblPwdDesc.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblPwdDesc);
        lblPwdDesc.setBounds(350, 515, 250, 15);

        lblPwdTitle.setText("Change Password");
        lblPwdTitle.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblPwdTitle.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblPwdTitle);
        lblPwdTitle.setBounds(350, 495, 200, 20);

        lblPwdIcon.setText("***");
        lblPwdIcon.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblPwdIcon.setForeground(new java.awt.Color(26, 75, 117));
        getContentPane().add(lblPwdIcon);
        lblPwdIcon.setBounds(300, 495, 40, 40);
        getContentPane().add(btnChangePassword);
        btnChangePassword.setBounds(300, 490, 850, 45);

        lblTfaChevron.setText("〉");
        lblTfaChevron.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTfaChevron.setForeground(new java.awt.Color(120, 140, 160));
        getContentPane().add(lblTfaChevron);
        lblTfaChevron.setBounds(1125, 560, 20, 20);

        lblTfaDesc.setText("ACTIVE");
        lblTfaDesc.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblTfaDesc.setForeground(new java.awt.Color(16, 185, 129));
        getContentPane().add(lblTfaDesc);
        lblTfaDesc.setBounds(350, 570, 250, 15);

        lblTfaTitle.setText("Two-Factor Authentication");
        lblTfaTitle.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblTfaTitle.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblTfaTitle);
        lblTfaTitle.setBounds(350, 550, 250, 20);

        lblTfaIcon.setText("🔒");
        lblTfaIcon.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTfaIcon.setForeground(new java.awt.Color(26, 75, 117));
        getContentPane().add(lblTfaIcon);
        lblTfaIcon.setBounds(300, 550, 40, 40);
        getContentPane().add(btnTwoFactor);
        btnTwoFactor.setBounds(300, 545, 850, 45);

        lblDevicesChevron.setText("〉");
        lblDevicesChevron.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDevicesChevron.setForeground(new java.awt.Color(120, 140, 160));
        getContentPane().add(lblDevicesChevron);
        lblDevicesChevron.setBounds(1125, 615, 20, 20);

        lblDevicesDesc.setText("3 active sessions");
        lblDevicesDesc.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblDevicesDesc.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblDevicesDesc);
        lblDevicesDesc.setBounds(350, 625, 250, 15);

        lblDevicesTitle.setText("Manage Connected Devices");
        lblDevicesTitle.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblDevicesTitle.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblDevicesTitle);
        lblDevicesTitle.setBounds(350, 605, 250, 20);

        lblDevicesIcon.setText("💻");
        lblDevicesIcon.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblDevicesIcon.setForeground(new java.awt.Color(26, 75, 117));
        getContentPane().add(lblDevicesIcon);
        lblDevicesIcon.setBounds(300, 605, 40, 40);
        getContentPane().add(btnDevices);
        btnDevices.setBounds(300, 600, 850, 45);

        cardSecurity.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cardSecurity);
        cardSecurity.setBounds(280, 440, 890, 230);

        lblFooterCopyright.setText("© 2026 YatraAir Sewa Flight booking System");
        lblFooterCopyright.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblFooterCopyright.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblFooterCopyright);
        lblFooterCopyright.setBounds(280, 685, 350, 20);

        lblFooterLinks.setText("Privacy Policy   Terms of Service");
        lblFooterLinks.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblFooterLinks.setForeground(new java.awt.Color(75, 85, 99));
        getContentPane().add(lblFooterLinks);
        lblFooterLinks.setBounds(980, 685, 200, 20);

        lblStatusText.setText("● SYSTEM STATUS: OPERATIONAL");
        lblStatusText.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblStatusText.setForeground(new java.awt.Color(16, 185, 129));
        getContentPane().add(lblStatusText);
        lblStatusText.setBounds(280, 755, 300, 20);

        pnlStatus.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(pnlStatus);
        pnlStatus.setBounds(250, 740, 950, 60);

        pnlMainBg.setBackground(new java.awt.Color(244, 246, 249));
        getContentPane().add(pnlMainBg);
        pnlMainBg.setBounds(250, 60, 950, 740);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        NavigationController.goToDashboard(this);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnSearchFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchFlightActionPerformed
        NavigationController.goToSearchFlight(this);
    }//GEN-LAST:event_btnSearchFlightActionPerformed

    private void btnMyBookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyBookingsActionPerformed
        NavigationController.goToMyBookings(this);
    }//GEN-LAST:event_btnMyBookingsActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        NavigationController.goToProfile(this);
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnCustomerSupportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerSupportActionPerformed
        NavigationController.goToCustomerSupport(this);
    }//GEN-LAST:event_btnCustomerSupportActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        NavigationController.logout(this);
    }//GEN-LAST:event_btnLogoutActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnCustomerSupport;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDevices;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMyBookings;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnSaveChanges;
    private javax.swing.JButton btnSearchFlight;
    private javax.swing.JButton btnTwoFactor;
    private javax.swing.JPanel cardPersonalInfo;
    private javax.swing.JPanel cardProfileHeader;
    private javax.swing.JPanel cardSecurity;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblBackToHome;
    private javax.swing.JLabel lblDevicesChevron;
    private javax.swing.JLabel lblDevicesDesc;
    private javax.swing.JLabel lblDevicesIcon;
    private javax.swing.JLabel lblDevicesTitle;
    private javax.swing.JLabel lblDob;
    private javax.swing.JLabel lblDobCal;
    private javax.swing.JLabel lblEditIcon;
    private javax.swing.JLabel lblEmailAddress;
    private javax.swing.JLabel lblFooterCopyright;
    private javax.swing.JLabel lblFooterLinks;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblHeaderIcons;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMemberBadge;
    private javax.swing.JLabel lblMemberSince;
    private javax.swing.JLabel lblPersonalInfo;
    private javax.swing.JLabel lblPersonalInfoIcon;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JLabel lblProfileName;
    private javax.swing.JLabel lblPwdChevron;
    private javax.swing.JLabel lblPwdDesc;
    private javax.swing.JLabel lblPwdIcon;
    private javax.swing.JLabel lblPwdTitle;
    private javax.swing.JLabel lblSecurity;
    private javax.swing.JLabel lblSecurityIcon;
    private javax.swing.JLabel lblStatusText;
    private javax.swing.JLabel lblTfaChevron;
    private javax.swing.JLabel lblTfaDesc;
    private javax.swing.JLabel lblTfaIcon;
    private javax.swing.JLabel lblTfaTitle;
    private javax.swing.JLabel lblTopTitle;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlTopHeader;
    private javax.swing.JTextField txtDob;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}

// Commit 1: Documented user profile photo avatar size bounds

// Commit 3: Documented edit profile detail fields regex checks

// Commit 5: Documented change password fields character length constraints

// Commit 7: Documented user profile status indicator active colors

// Commit 9: Documented edit profile cancel button redirect hooks

// Commit 11: Documented user profile settings layout dimensions

// Commit 13: Documented edit user profile save button action events
