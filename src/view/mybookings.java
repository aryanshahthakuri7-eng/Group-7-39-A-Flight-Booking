package view;

import controller.BookingController;
import controller.TicketController;
import controller.NavigationController;
import model.Booking;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * View class representing the User Bookings list.
 * Built with professional JTable and dynamic CRUD actions.
 */
public class mybookings extends javax.swing.JFrame {

    private final BookingController bookingController;
    private final TicketController ticketController;
    private ArrayList<Booking> bookingsList;

    public mybookings() {
        this.bookingController = new BookingController();
        this.ticketController = new TicketController();

        initComponents();
        getContentPane().setBackground(new java.awt.Color(20, 28, 35)); // Navy Blue

        // Style side navigation buttons
        styleSidebarButton(btnDashboard);
        styleSidebarButton(btnSearchFlight);
        styleSidebarButton(btnMyBookings);
        styleSidebarButton(btnCheckIn);
        styleSidebarButton(btnProfile);
        styleSidebarButton(btnCustomerSupport);
        styleSidebarButton(btnLogout);
        styleSidebarActiveButton(btnMyBookings);

        // Buttons custom styling
        btnViewTicket.setContentAreaFilled(true);
        btnViewTicket.setBackground(new java.awt.Color(26, 115, 232)); // Blue
        btnViewTicket.setForeground(java.awt.Color.WHITE);
        btnViewTicket.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnCancel.setContentAreaFilled(true);
        btnCancel.setBackground(new java.awt.Color(239, 68, 68)); // Red
        btnCancel.setForeground(java.awt.Color.WHITE);
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnDownload.setContentAreaFilled(true);
        btnDownload.setBackground(new java.awt.Color(34, 197, 94)); // Green
        btnDownload.setForeground(java.awt.Color.WHITE);
        btnDownload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnPrint.setContentAreaFilled(true);
        btnPrint.setBackground(new java.awt.Color(249, 115, 22)); // Orange
        btnPrint.setForeground(java.awt.Color.WHITE);
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnRefresh.setContentAreaFilled(true);
        btnRefresh.setBackground(java.awt.Color.WHITE);
        btnRefresh.setForeground(new java.awt.Color(92, 100, 112));
        btnRefresh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        refreshBookingsTable();
    }

    /**
     * Loads logged-in user bookings from the database and populates the table.
     */
    private void refreshBookingsTable() {
        bookingsList = bookingController.getLoggedInUserBookings();
        DefaultTableModel model = (DefaultTableModel) tblBookings.getModel();
        model.setRowCount(0);

        for (Booking b : bookingsList) {
            model.addRow(new Object[]{
                b.getBookingId(),
                b.getFlightCode(),
                b.getFromCity(),
                b.getToCity(),
                b.getPassengerName(),
                b.getSeatNumber(),
                b.getDepartureDate(),
                b.getFormattedAmount(),
                b.getStatus()
            });
        }
    }

    private Booking getSelectedBooking() {
        int selectedRow = tblBookings.getSelectedRow();
        if (selectedRow == -1 || bookingsList == null || selectedRow >= bookingsList.size()) {
            return null;
        }
        return bookingsList.get(selectedRow);
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
        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBookings = new javax.swing.JTable();
        btnViewTicket = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnDownload = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - My Bookings");
        setPreferredSize(new java.awt.Dimension(1200, 800));
        getContentPane().setLayout(null);

        tblBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Flight Code", "From", "To", "Passenger", "Seat", "Date", "Amount", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBookings.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(tblBookings);

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

        btnSearchFlight.setText("Search & Book");
        btnSearchFlight.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnSearchFlight.setForeground(new java.awt.Color(92, 100, 112));
        btnSearchFlight.addActionListener(this::btnSearchFlightActionPerformed);
        getContentPane().add(btnSearchFlight);
        btnSearchFlight.setBounds(15, 170, 220, 40);

        btnMyBookings.setText("My Bookings");
        btnMyBookings.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnMyBookings.setForeground(new java.awt.Color(255, 255, 255));
        btnMyBookings.setBackground(new java.awt.Color(20, 28, 35));
        btnMyBookings.addActionListener(this::btnMyBookingsActionPerformed);
        getContentPane().add(btnMyBookings);
        btnMyBookings.setBounds(15, 220, 220, 40);

        btnCheckIn.setText("Check-in");
        btnCheckIn.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnCheckIn.setForeground(new java.awt.Color(92, 100, 112));
        btnCheckIn.addActionListener(this::btnCheckInActionPerformed);
        getContentPane().add(btnCheckIn);
        btnCheckIn.setBounds(15, 270, 220, 40);

        btnProfile.setText("Profile");
        btnProfile.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnProfile.setForeground(new java.awt.Color(92, 100, 112));
        btnProfile.addActionListener(this::btnProfileActionPerformed);
        getContentPane().add(btnProfile);
        btnProfile.setBounds(15, 320, 220, 40);

        btnCustomerSupport.setText("Customer Support");
        btnCustomerSupport.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnCustomerSupport.setForeground(new java.awt.Color(92, 100, 112));
        btnCustomerSupport.addActionListener(this::btnCustomerSupportActionPerformed);
        getContentPane().add(btnCustomerSupport);
        btnCustomerSupport.setBounds(15, 370, 220, 40);

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

        pnlMainBg.setBackground(new java.awt.Color(244, 246, 249));
        pnlMainBg.setLayout(null);

        lblTitle.setText("My Flight Bookings");
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(20, 28, 35));
        pnlMainBg.add(lblTitle);
        lblTitle.setBounds(30, 30, 300, 30);
        pnlMainBg.add(jScrollPane1);
        jScrollPane1.setBounds(30, 90, 890, 480);

        btnViewTicket.setText("View Ticket");
        btnViewTicket.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnViewTicket.setForeground(new java.awt.Color(255, 255, 255));
        btnViewTicket.setBackground(new java.awt.Color(232, 115, 26));
        btnViewTicket.addActionListener(this::btnViewTicketActionPerformed);
        pnlMainBg.add(btnViewTicket);
        btnViewTicket.setBounds(30, 590, 150, 45);

        btnCancel.setText("Cancel Booking");
        btnCancel.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setBackground(new java.awt.Color(255, 59, 48));
        btnCancel.addActionListener(this::btnCancelActionPerformed);
        pnlMainBg.add(btnCancel);
        btnCancel.setBounds(200, 590, 150, 45);

        btnDownload.setText("Download Ticket");
        btnDownload.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnDownload.setForeground(new java.awt.Color(255, 255, 255));
        btnDownload.setBackground(new java.awt.Color(16, 181, 94));
        btnDownload.addActionListener(this::btnDownloadActionPerformed);
        pnlMainBg.add(btnDownload);
        btnDownload.setBounds(370, 590, 150, 45);

        btnPrint.setText("Print Ticket");
        btnPrint.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setBackground(new java.awt.Color(255, 122, 21));
        btnPrint.addActionListener(this::btnPrintActionPerformed);
        pnlMainBg.add(btnPrint);
        btnPrint.setBounds(540, 590, 150, 45);

        btnRefresh.setText("Refresh");
        btnRefresh.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(92, 100, 112));
        btnRefresh.addActionListener(this::btnRefreshActionPerformed);
        pnlMainBg.add(btnRefresh);
        btnRefresh.setBounds(710, 590, 150, 45);

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

    private void btnViewTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewTicketActionPerformed
        Booking selected = getSelectedBooking();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a booking row from the table.", "No Booking Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        NavigationController.openTicketWindow(selected);
    }//GEN-LAST:event_btnViewTicketActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Booking selected = getSelectedBooking();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a booking to cancel.", "No Booking Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!"CONFIRMED".equalsIgnoreCase(selected.getStatus())) {
            JOptionPane.showMessageDialog(this, "Only confirmed bookings can be cancelled.", "Invalid Cancellation Request", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to cancel booking " + selected.getBookingId() + "? This action is irreversible.", 
            "Confirm Booking Cancellation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
        if (choice == JOptionPane.YES_OPTION) {
            boolean success = bookingController.cancelBooking(selected.getBookingId());
            if (success) {
                JOptionPane.showMessageDialog(this, "Booking cancelled successfully. Flight seat count has been restored.", "Cancellation Successful", JOptionPane.INFORMATION_MESSAGE);
                refreshBookingsTable();
            } else {
                JOptionPane.showMessageDialog(this, "Cancellation failed. Ensure database connections are active.", "Cancellation Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        Booking selected = getSelectedBooking();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a booking to download.", "No Booking Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Boarding Ticket File");
        fileChooser.setSelectedFile(new File(selected.getBookingId() + "_ticket.txt"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            boolean success = ticketController.downloadTicket(selected, fileToSave);
            if (success) {
                JOptionPane.showMessageDialog(this, "Ticket downloaded successfully to: " + fileToSave.getAbsolutePath(), "Download Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to write ticket file. Ensure directory permissions are set.", "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDownloadActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        Booking selected = getSelectedBooking();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select a booking to print.", "No Booking Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        boolean success = ticketController.printTicket(selected);
        if (success) {
            JOptionPane.showMessageDialog(this, "Ticket print command initiated. Check console log outputs for print stream verification.", "Print Successful", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to print. Booking details are empty.", "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refreshBookingsTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JButton btnCustomerSupport;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMyBookings;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearchFlight;
    private javax.swing.JButton btnViewTicket;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBookings;
    private javax.swing.JLabel lblBackToHome;
    private javax.swing.JLabel lblHeaderIcons;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTopTitle;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTopHeader;
    // End of variables declaration//GEN-END:variables
}
