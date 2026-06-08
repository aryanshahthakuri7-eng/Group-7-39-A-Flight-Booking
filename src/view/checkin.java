package view;

import controller.TicketController;
import controller.NavigationController;
import dao.BookingDAO;
import model.Booking;
import model.SessionManager;
import utils.PDFGenerator;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * View class representing the flight Check-In and Boarding Pass generation screen.
 */
public class checkin extends javax.swing.JFrame {

    private final TicketController ticketController;
    private final BookingDAO bookingDAO;
    private Booking activeBooking = null;

    public checkin() {
        this.ticketController = new TicketController();
        this.bookingDAO = new BookingDAO();

        initComponents();
        getContentPane().setBackground(new java.awt.Color(20, 28, 35)); // Navy

        // Sidebar custom styles
        styleSidebarButton(btnDashboard);
        styleSidebarButton(btnSearchFlight);
        styleSidebarButton(btnMyBookings);
        styleSidebarButton(btnCheckIn);
        styleSidebarButton(btnProfile);
        styleSidebarButton(btnCustomerSupport);
        styleSidebarButton(btnLogout);
        styleSidebarActiveButton(btnCheckIn);

        styleCardPanel(pnlSearchCard);
        styleCardPanel(pnlPassCard);
        
        styleTextField(txtBookingId);

        btnVerify.setContentAreaFilled(true);
        btnVerify.setBackground(new java.awt.Color(26, 115, 232)); // Blue
        btnVerify.setForeground(java.awt.Color.WHITE);
        btnVerify.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnGenerate.setContentAreaFilled(true);
        btnGenerate.setBackground(new java.awt.Color(255, 122, 26)); // Orange
        btnGenerate.setForeground(java.awt.Color.WHITE);
        btnGenerate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnDownloadPass.setContentAreaFilled(true);
        btnDownloadPass.setBackground(new java.awt.Color(34, 197, 94)); // Green
        btnDownloadPass.setForeground(java.awt.Color.WHITE);
        btnDownloadPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        pnlPassCard.setVisible(false);
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

    private void styleTextField(javax.swing.JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnSearchFlight = new javax.swing.JButton();
        btnMyBookings = new javax.swing.JButton();
        btnCheckIn = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        btnCustomerSupport = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        pnlSidebar = new javax.swing.JPanel();
        lblBackToHome = new javax.swing.JLabel();
        lblTopTitle = new javax.swing.JLabel();
        lblHeaderIcons = new javax.swing.JLabel();
        pnlTopHeader = new javax.swing.JPanel();
        pnlMainBg = new javax.swing.JPanel();
        pnlSearchCard = new javax.swing.JPanel();
        lblBookingId = new javax.swing.JLabel();
        txtBookingId = new javax.swing.JTextField();
        btnVerify = new javax.swing.JButton();
        pnlPassCard = new javax.swing.JPanel();
        lblPassHeader = new javax.swing.JLabel();
        lblPassStatus = new javax.swing.JLabel();
        lblPName = new javax.swing.JLabel();
        lblPFlight = new javax.swing.JLabel();
        lblPRoute = new javax.swing.JLabel();
        lblPSeat = new javax.swing.JLabel();
        lblPDate = new javax.swing.JLabel();
        lblBarcode = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JButton();
        btnDownloadPass = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - Check-In");
        setPreferredSize(new java.awt.Dimension(1200, 800));
        getContentPane().setLayout(null);

        lblLogo.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(20, 28, 35));
        lblLogo.setText("▼  YATRAAIR");
        getContentPane().add(lblLogo);
        lblLogo.setBounds(25, 30, 200, 40);

        btnDashboard.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(92, 100, 112));
        btnDashboard.setText("Dashboard");
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);
        getContentPane().add(btnDashboard);
        btnDashboard.setBounds(15, 120, 220, 40);

        btnSearchFlight.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnSearchFlight.setForeground(new java.awt.Color(92, 100, 112));
        btnSearchFlight.setText("Search & Book");
        btnSearchFlight.addActionListener(this::btnSearchFlightActionPerformed);
        getContentPane().add(btnSearchFlight);
        btnSearchFlight.setBounds(15, 170, 220, 40);

        btnMyBookings.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMyBookings.setForeground(new java.awt.Color(92, 100, 112));
        btnMyBookings.setText("My Bookings");
        btnMyBookings.addActionListener(this::btnMyBookingsActionPerformed);
        getContentPane().add(btnMyBookings);
        btnMyBookings.setBounds(15, 220, 220, 40);

        btnCheckIn.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCheckIn.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckIn.setBackground(new java.awt.Color(20, 28, 35));
        btnCheckIn.setText("Check-in");
        btnCheckIn.addActionListener(this::btnCheckInActionPerformed);
        getContentPane().add(btnCheckIn);
        btnCheckIn.setBounds(15, 270, 220, 40);

        btnProfile.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnProfile.setForeground(new java.awt.Color(92, 100, 112));
        btnProfile.setText("Profile");
        btnProfile.addActionListener(this::btnProfileActionPerformed);
        getContentPane().add(btnProfile);
        btnProfile.setBounds(15, 320, 220, 40);

        btnCustomerSupport.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnCustomerSupport.setForeground(new java.awt.Color(92, 100, 112));
        btnCustomerSupport.setText("Customer Support");
        btnCustomerSupport.addActionListener(this::btnCustomerSupportActionPerformed);
        getContentPane().add(btnCustomerSupport);
        btnCustomerSupport.setBounds(15, 370, 220, 40);

        btnLogout.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 59, 48));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        getContentPane().add(btnLogout);
        btnLogout.setBounds(15, 700, 220, 40);

        pnlSidebar.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 250, 800);

        lblBackToHome.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblBackToHome.setForeground(new java.awt.Color(160, 174, 192));
        lblBackToHome.setText("🏠 Back to Home");
        getContentPane().add(lblBackToHome);
        lblBackToHome.setBounds(275, 18, 150, 25);

        lblTopTitle.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblTopTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTopTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTopTitle.setText("YATRA AIR SEWA");
        getContentPane().add(lblTopTitle);
        lblTopTitle.setBounds(600, 18, 250, 25);

        lblHeaderIcons.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblHeaderIcons.setForeground(new java.awt.Color(255, 255, 255));
        lblHeaderIcons.setText("🔔   ❓   ⚙️");
        getContentPane().add(lblHeaderIcons);
        lblHeaderIcons.setBounds(1080, 18, 90, 25);

        pnlTopHeader.setBackground(new java.awt.Color(20, 28, 35));
        getContentPane().add(pnlTopHeader);
        pnlTopHeader.setBounds(250, 0, 950, 60);

        pnlMainBg.setBackground(new java.awt.Color(244, 246, 249));
        pnlMainBg.setLayout(null);

        pnlSearchCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlSearchCard.setLayout(null);

        lblBookingId.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblBookingId.setForeground(new java.awt.Color(113, 128, 150));
        lblBookingId.setText("ENTER BOOKING ID FOR CHECK-IN");
        pnlSearchCard.add(lblBookingId);
        lblBookingId.setBounds(20, 20, 300, 15);

        txtBookingId.setText("BK001");
        pnlSearchCard.add(txtBookingId);
        txtBookingId.setBounds(20, 40, 350, 35);

        btnVerify.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnVerify.setForeground(new java.awt.Color(255, 255, 255));
        btnVerify.setText("Verify Ticket & Check-In");
        btnVerify.addActionListener(this::btnVerifyActionPerformed);
        pnlSearchCard.add(btnVerify);
        btnVerify.setBounds(400, 40, 250, 35);

        pnlMainBg.add(pnlSearchCard);
        pnlSearchCard.setBounds(30, 30, 890, 110);

        pnlPassCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlPassCard.setLayout(null);

        lblPassHeader.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblPassHeader.setForeground(new java.awt.Color(20, 28, 35));
        lblPassHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassHeader.setText("YATRA AIR SEWA BOARDING PASS");
        pnlPassCard.add(lblPassHeader);
        lblPassHeader.setBounds(20, 20, 460, 30);

        lblPassStatus.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblPassStatus.setForeground(new java.awt.Color(255, 59, 48));
        lblPassStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassStatus.setText("STATUS: PENDING CHECK-IN");
        pnlPassCard.add(lblPassStatus);
        lblPassStatus.setBounds(20, 60, 460, 25);

        lblPName.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblPName.setForeground(new java.awt.Color(20, 28, 35));
        lblPName.setText("Passenger: -");
        pnlPassCard.add(lblPName);
        lblPName.setBounds(40, 110, 420, 25);

        lblPFlight.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblPFlight.setForeground(new java.awt.Color(113, 128, 150));
        lblPFlight.setText("Flight Code: -");
        pnlPassCard.add(lblPFlight);
        lblPFlight.setBounds(40, 145, 420, 25);

        lblPRoute.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblPRoute.setForeground(new java.awt.Color(113, 128, 150));
        lblPRoute.setText("Route: -");
        pnlPassCard.add(lblPRoute);
        lblPRoute.setBounds(40, 180, 420, 25);

        lblPSeat.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblPSeat.setForeground(new java.awt.Color(20, 28, 35));
        lblPSeat.setText("Seat Number: -");
        pnlPassCard.add(lblPSeat);
        lblPSeat.setBounds(40, 215, 420, 25);

        lblPDate.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblPDate.setForeground(new java.awt.Color(113, 128, 150));
        lblPDate.setText("Date/Time: -");
        pnlPassCard.add(lblPDate);
        lblPDate.setBounds(40, 250, 420, 25);

        lblBarcode.setFont(new java.awt.Font("SansSerif", 0, 9)); // NOI18N
        lblBarcode.setForeground(new java.awt.Color(160, 174, 192));
        lblBarcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBarcode.setText("||||||||||||||||||||||||||||||||||||||||||||||||| BOARDING PASS CODE |||||||||||||||||||||||||||||||||||||||||||||||||");
        pnlPassCard.add(lblBarcode);
        lblBarcode.setBounds(20, 300, 460, 40);

        btnGenerate.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnGenerate.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerate.setText("Generate Boarding Pass");
        btnGenerate.addActionListener(this::btnGenerateActionPerformed);
        pnlPassCard.add(btnGenerate);
        btnGenerate.setBounds(40, 370, 420, 50);

        btnDownloadPass.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnDownloadPass.setForeground(new java.awt.Color(255, 255, 255));
        btnDownloadPass.setText("Download Boarding Pass");
        btnDownloadPass.addActionListener(this::btnDownloadPassActionPerformed);
        pnlPassCard.add(btnDownloadPass);
        btnDownloadPass.setBounds(40, 440, 420, 45);

        pnlMainBg.add(pnlPassCard);
        pnlPassCard.setBounds(30, 160, 500, 520);

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

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInActionPerformed
        NavigationController.goToCheckIn(this);
    }//GEN-LAST:event_btnCheckInActionPerformed

    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
        String bookingId = txtBookingId.getText().trim();
        if (bookingId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Booking ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        activeBooking = ticketController.getTicketDetails(bookingId);
        
        if (activeBooking == null) {
            pnlPassCard.setVisible(false);
            JOptionPane.showMessageDialog(this, "Booking ID not found in database.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (activeBooking.getUserId() != SessionManager.getCurrentUser().getUserId()) {
            pnlPassCard.setVisible(false);
            JOptionPane.showMessageDialog(this, "This booking does not belong to the currently logged in user.", "Access Denied", JOptionPane.WARNING_MESSAGE);
            return;
        }

        pnlPassCard.setVisible(true);
        lblPName.setText("Passenger: " + activeBooking.getPassengerName());
        lblPFlight.setText("Flight Code: " + activeBooking.getFlightCode());
        lblPRoute.setText("Route: " + activeBooking.getRoute());
        lblPSeat.setText("Seat Number: " + activeBooking.getSeatNumber());
        lblPDate.setText("Date/Time: " + activeBooking.getDepartureDate() + " @ " + activeBooking.getDepartureTime());

        updateStatusUI();
    }//GEN-LAST:event_btnVerifyActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        if (activeBooking == null) return;

        if ("CANCELLED".equalsIgnoreCase(activeBooking.getStatus())) {
            JOptionPane.showMessageDialog(this, "Cannot check-in to a cancelled booking.", "Cancelled Booking", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean success = bookingDAO.updateBookingStatus(activeBooking.getBookingId(), "CHECKED-IN");
        if (success) {
            activeBooking.setStatus("CHECKED-IN");
            JOptionPane.showMessageDialog(this, "Check-in successful! Boarding pass generated.", "Checked In", JOptionPane.INFORMATION_MESSAGE);
            updateStatusUI();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update status in database.", "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void btnDownloadPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadPassActionPerformed
        if (activeBooking == null) return;
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Boarding Pass");
        fileChooser.setSelectedFile(new File(activeBooking.getBookingId() + "_boarding_pass.txt"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            boolean success = ticketController.downloadTicket(activeBooking, fileToSave);
            if (success) {
                JOptionPane.showMessageDialog(this, "Boarding pass downloaded to: " + fileToSave.getAbsolutePath(), "Download Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDownloadPassActionPerformed

    private void updateStatusUI() {
        if (activeBooking == null) return;
        
        lblPassStatus.setText("STATUS: " + activeBooking.getStatus().toUpperCase());
        if ("CHECKED-IN".equalsIgnoreCase(activeBooking.getStatus())) {
            lblPassStatus.setForeground(new java.awt.Color(34, 197, 94)); // Green
            btnGenerate.setEnabled(false);
            btnGenerate.setText("Check-In Complete");
            btnDownloadPass.setEnabled(true);
        } else if ("CANCELLED".equalsIgnoreCase(activeBooking.getStatus())) {
            lblPassStatus.setForeground(new java.awt.Color(239, 68, 68)); // Red
            btnGenerate.setEnabled(false);
            btnGenerate.setText("Cancelled");
            btnDownloadPass.setEnabled(false);
        } else {
            lblPassStatus.setForeground(new java.awt.Color(249, 115, 22)); // Orange (CONFIRMED)
            btnGenerate.setEnabled(true);
            btnGenerate.setText("Generate Boarding Pass");
            btnDownloadPass.setEnabled(false);
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCustomerSupport;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDownloadPass;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMyBookings;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnSearchFlight;
    private javax.swing.JButton btnVerify;
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JLabel lblBackToHome;
    private javax.swing.JLabel lblBarcode;
    private javax.swing.JLabel lblBookingId;
    private javax.swing.JLabel lblHeaderIcons;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPDate;
    private javax.swing.JLabel lblPFlight;
    private javax.swing.JLabel lblPName;
    private javax.swing.JLabel lblPRoute;
    private javax.swing.JLabel lblPSeat;
    private javax.swing.JLabel lblPassHeader;
    private javax.swing.JLabel lblPassStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTopTitle;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlPassCard;
    private javax.swing.JPanel pnlSearchCard;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTopHeader;
    private javax.swing.JTextField txtBookingId;
    // End of variables declaration//GEN-END:variables
}
