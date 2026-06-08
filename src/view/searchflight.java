package view;

import controller.FlightController;
import controller.BookingController;
import controller.NavigationController;
import dao.LocationDAO;
import model.Flight;
import model.SessionManager;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * View class representing the Flight Search and Booking screen.
 * Seamlessly integrates database flight searching with atomic transaction bookings.
 */
public class searchflight extends javax.swing.JFrame {

    private final FlightController flightController;
    private final BookingController bookingController;
    private final LocationDAO locationDAO;
    
    private List<Flight> searchedFlights;
    private Flight selectedFlight = null;

    public searchflight() {
        this.flightController = new FlightController();
        this.bookingController = new BookingController();
        this.locationDAO = new LocationDAO();

        initComponents();
        getContentPane().setBackground(new java.awt.Color(20, 28, 35)); // Dark Navy
        
        // Custom stylings
        styleSidebarButton(btnDashboard);
        styleSidebarButton(btnSearchFlight);
        styleSidebarButton(btnMyBookings);
        styleSidebarButton(btnCheckIn);
        styleSidebarButton(btnProfile);
        styleSidebarButton(btnCustomerSupport);
        styleSidebarButton(btnLogout);
        styleSidebarActiveButton(btnSearchFlight);

        styleCardPanel(pnlSearchCard);
        styleCardPanel(pnlBookingCard);
        
        styleComboBox(cmbFrom);
        styleComboBox(cmbTo);
        styleComboBox(cmbPayment);
        styleTextField(txtDepartDate);
        styleTextField(txtPassengerName);
        styleTextField(txtSeat);
        
        btnSearch.setContentAreaFilled(true);
        btnSearch.setBackground(new java.awt.Color(26, 115, 232));
        btnSearch.setForeground(java.awt.Color.WHITE);
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnBook.setContentAreaFilled(true);
        btnBook.setBackground(new java.awt.Color(255, 122, 26)); // Orange
        btnBook.setForeground(java.awt.Color.WHITE);
        btnBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Populate search dropdown locations from DB
        cmbFrom.setModel(new javax.swing.DefaultComboBoxModel<>(locationDAO.getFromLocationsArray()));
        cmbTo.setModel(new javax.swing.DefaultComboBoxModel<>(locationDAO.getToLocationsArray()));
        
        // Load logged-in user name as default passenger
        txtPassengerName.setText(SessionManager.getCurrentUser().getFullName());

        // Attach listener for table selection
        tblFlights.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblFlights.getSelectedRow() != -1) {
                int row = tblFlights.getSelectedRow();
                if (searchedFlights != null && row < searchedFlights.size()) {
                    selectedFlight = searchedFlights.get(row);
                    lblFlightDetails.setText("<html>Selected: <b>" + selectedFlight.getFlightCode() + "</b> (" + selectedFlight.getRoute() + ")<br>Price: NPR " + String.format("%,.0f", selectedFlight.getPrice()) + " | Seats left: " + selectedFlight.getAvailableSeats() + "</html>");
                }
            }
        });
        
        // Fetch all flights initially
        searchAndRefreshTable();
    }

    private void searchAndRefreshTable() {
        String from = cmbFrom.getSelectedItem() != null ? cmbFrom.getSelectedItem().toString() : "";
        String to = cmbTo.getSelectedItem() != null ? cmbTo.getSelectedItem().toString() : "";
        String date = txtDepartDate.getText();

        searchedFlights = flightController.searchFlights(from, to, date);
        DefaultTableModel model = (DefaultTableModel) tblFlights.getModel();
        model.setRowCount(0);

        for (Flight f : searchedFlights) {
            model.addRow(new Object[]{
                f.getFlightCode(),
                f.getSource(),
                f.getDestination(),
                f.getDepartureDate(),
                f.getDepartureTime(),
                "NPR " + String.format("%,.0f", f.getPrice())
            });
        }
        
        selectedFlight = null;
        lblFlightDetails.setText("Select a flight from search results...");
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

    private void styleComboBox(javax.swing.JComboBox<?> cmb) {
        cmb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
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
        lblFrom = new javax.swing.JLabel();
        cmbFrom = new javax.swing.JComboBox<>();
        lblTo = new javax.swing.JLabel();
        cmbTo = new javax.swing.JComboBox<>();
        lblDepartDate = new javax.swing.JLabel();
        txtDepartDate = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlBookingCard = new javax.swing.JPanel();
        lblBookingTitle = new javax.swing.JLabel();
        lblFlightDetails = new javax.swing.JLabel();
        lblPassengerName = new javax.swing.JLabel();
        txtPassengerName = new javax.swing.JTextField();
        lblSeat = new javax.swing.JLabel();
        txtSeat = new javax.swing.JTextField();
        lblPayment = new javax.swing.JLabel();
        cmbPayment = new javax.swing.JComboBox<>();
        btnBook = new javax.swing.JButton();
        lblBookingStatusMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - Search Flights");
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

        btnSearchFlight.setText("Search & Book");
        btnSearchFlight.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSearchFlight.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchFlight.setBackground(new java.awt.Color(20, 28, 35));
        btnSearchFlight.addActionListener(this::btnSearchFlightActionPerformed);
        getContentPane().add(btnSearchFlight);
        btnSearchFlight.setBounds(15, 170, 220, 40);

        btnMyBookings.setText("My Bookings");
        btnMyBookings.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMyBookings.setForeground(new java.awt.Color(92, 100, 112));
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

        pnlSearchCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlSearchCard.setLayout(null);

        lblFrom.setText("FROM");
        lblFrom.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblFrom.setForeground(new java.awt.Color(113, 128, 150));
        pnlSearchCard.add(lblFrom);
        lblFrom.setBounds(20, 20, 100, 15);

        pnlSearchCard.add(cmbFrom);
        cmbFrom.setBounds(20, 40, 200, 35);

        lblTo.setText("TO");
        lblTo.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblTo.setForeground(new java.awt.Color(113, 128, 150));
        pnlSearchCard.add(lblTo);
        lblTo.setBounds(240, 20, 100, 15);

        pnlSearchCard.add(cmbTo);
        cmbTo.setBounds(240, 40, 200, 35);

        lblDepartDate.setText("DEPART DATE");
        lblDepartDate.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblDepartDate.setForeground(new java.awt.Color(113, 128, 150));
        pnlSearchCard.add(lblDepartDate);
        lblDepartDate.setBounds(460, 20, 100, 15);

        txtDepartDate.setText("DD/MM/YYYY");
        pnlSearchCard.add(txtDepartDate);
        txtDepartDate.setBounds(460, 40, 150, 35);

        btnSearch.setText("Search Flights");
        btnSearch.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setBackground(new java.awt.Color(232, 115, 26));
        btnSearch.addActionListener(this::btnSearchActionPerformed);
        pnlSearchCard.add(btnSearch);
        btnSearch.setBounds(640, 40, 220, 35);

        pnlMainBg.add(pnlSearchCard);
        pnlSearchCard.setBounds(30, 30, 890, 110);
        pnlMainBg.add(jScrollPane1);
        jScrollPane1.setBounds(30, 160, 520, 520);

        pnlBookingCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlBookingCard.setLayout(null);

        lblBookingTitle.setText("Flight Booking Form");
        lblBookingTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblBookingTitle.setForeground(new java.awt.Color(20, 28, 35));
        lblBookingTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlBookingCard.add(lblBookingTitle);
        lblBookingTitle.setBounds(20, 20, 310, 30);

        lblFlightDetails.setText("Select a flight from search results...");
        lblFlightDetails.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblFlightDetails.setForeground(new java.awt.Color(113, 128, 150));
        lblFlightDetails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlBookingCard.add(lblFlightDetails);
        lblFlightDetails.setBounds(20, 60, 310, 40);

        lblPassengerName.setText("Passenger Full Name");
        lblPassengerName.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblPassengerName.setForeground(new java.awt.Color(92, 100, 112));
        pnlBookingCard.add(lblPassengerName);
        lblPassengerName.setBounds(30, 120, 290, 20);
        pnlBookingCard.add(txtPassengerName);
        txtPassengerName.setBounds(30, 145, 290, 35);

        lblSeat.setText("Seat Number (e.g. A1 - F30)");
        lblSeat.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblSeat.setForeground(new java.awt.Color(92, 100, 112));
        pnlBookingCard.add(lblSeat);
        lblSeat.setBounds(30, 200, 290, 20);

        txtSeat.setText("A12");
        pnlBookingCard.add(txtSeat);
        txtSeat.setBounds(30, 225, 290, 35);

        lblPayment.setText("Payment Method");
        lblPayment.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblPayment.setForeground(new java.awt.Color(92, 100, 112));
        pnlBookingCard.add(lblPayment);
        lblPayment.setBounds(30, 280, 290, 20);

        pnlBookingCard.add(cmbPayment);
        cmbPayment.setBounds(30, 305, 290, 35);

        btnBook.setText("Book Flight Now");
        btnBook.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnBook.setForeground(new java.awt.Color(255, 255, 255));
        btnBook.setBackground(new java.awt.Color(255, 122, 21));
        btnBook.addActionListener(this::btnBookActionPerformed);
        pnlBookingCard.add(btnBook);
        btnBook.setBounds(30, 390, 290, 45);

        lblBookingStatusMsg.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblBookingStatusMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlBookingCard.add(lblBookingStatusMsg);
        lblBookingStatusMsg.setBounds(30, 455, 290, 25);

        pnlMainBg.add(pnlBookingCard);
        pnlBookingCard.setBounds(570, 160, 350, 520);

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

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchAndRefreshTable();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        if (selectedFlight == null) {
            JOptionPane.showMessageDialog(this, "Please select a flight from the search results table first.", "Flight Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String passengerName = txtPassengerName.getText();
        String seatNumber = txtSeat.getText();
        String paymentMethod = cmbPayment.getSelectedItem() != null ? cmbPayment.getSelectedItem().toString() : "E-SEWA";

        if (passengerName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter passenger name.", "Passenger Name Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (seatNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid seat number (e.g. A12).", "Seat Number Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean success = bookingController.bookFlight(selectedFlight, passengerName, seatNumber, paymentMethod);
        if (success) {
            JOptionPane.showMessageDialog(this, "Flight booked successfully! Available seats have been updated and details saved to database.", "Booking Successful", JOptionPane.INFORMATION_MESSAGE);
            NavigationController.goToMyBookings(this);
        } else {
            JOptionPane.showMessageDialog(this, "Booking failed. Seat might be unavailable or flight ID incorrect.", "Booking Failed", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBookActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JButton btnCustomerSupport;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMyBookings;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchFlight;
    private javax.swing.JComboBox<String> cmbFrom;
    private javax.swing.JComboBox<String> cmbPayment;
    private javax.swing.JComboBox<String> cmbTo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackToHome;
    private javax.swing.JLabel lblBookingStatusMsg;
    private javax.swing.JLabel lblBookingTitle;
    private javax.swing.JLabel lblDepartDate;
    private javax.swing.JLabel lblFlightDetails;
    private javax.swing.JLabel lblFrom;
    private javax.swing.JLabel lblHeaderIcons;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPassengerName;
    private javax.swing.JLabel lblPayment;
    private javax.swing.JLabel lblSeat;
    private javax.swing.JLabel lblTo;
    private javax.swing.JLabel lblTopTitle;
    private javax.swing.JPanel pnlBookingCard;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlSearchCard;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTopHeader;
    private javax.swing.JTable tblFlights;
    private javax.swing.JTextField txtDepartDate;
    private javax.swing.JTextField txtPassengerName;
    private javax.swing.JTextField txtSeat;
    // End of variables declaration//GEN-END:variables
}
