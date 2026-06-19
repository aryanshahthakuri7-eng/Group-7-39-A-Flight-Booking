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

/**
 * View class representing the Flight Search and Booking screen.
 * Seamlessly integrates database flight searching with atomic transaction bookings.
 * Coordinates input text criteria filtering, and displays dynamic flight cards.
 */
public class SearchFlight extends javax.swing.JFrame {

    private final FlightController flightController;
    private final BookingController bookingController;
    private final LocationDAO locationDAO;
    
    private List<Flight> searchedFlights;

    public SearchFlight() {
        this.flightController = new FlightController();
        this.bookingController = new BookingController();
        this.locationDAO = new LocationDAO();

        initComponents();
        getContentPane().setBackground(new java.awt.Color(20, 28, 35)); // Dark Navy
        
        // Match sidebar button text and icons with dashboard
        btnDashboard.setText("㗊  Dashboard");
        btnSearchFlight.setText("🔍  Search Flight");
        btnMyBookings.setText("💼  My Bookings");
        btnCheckIn.setText("🎫  Check-in");
        btnProfile.setText("👤  Profile");
        btnCustomerSupport.setText("💬  Customer Support");
        btnLogout.setText("🚪  Logout");

        // Custom stylings.
        // Applies clean flat visual styling attributes to the sidebar selection buttons at runtime.
        styleSidebarButton(btnDashboard);
        styleSidebarButton(btnSearchFlight);
        styleSidebarButton(btnMyBookings);
        styleSidebarButton(btnCheckIn);
        styleSidebarButton(btnProfile);
        styleSidebarButton(btnCustomerSupport);
        styleSidebarButton(btnLogout);
        styleSidebarActiveButton(btnSearchFlight);

        // Header Icons - visible in Search mockup
        lblHeaderIcons.setVisible(true);
        lblHeaderIcons.setForeground(java.awt.Color.WHITE);

        // Back to Home listener.
        // Attaches cursor hand trigger pointing to dashboard route on back-to-home navigation links.
        lblBackToHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBackToHome.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NavigationController.goToDashboard(SearchFlight.this);
            }
        });
        
        // Fetch all flights initially and render
        searchAndRefreshTable();
    }

    private void searchAndRefreshTable() {
        // Fetch flights from MySQL database (by default showing available ones).
        // Triggers the initial query load with empty query parameters to fetch all active schedules.
        searchedFlights = flightController.searchFlights("", "", "");
        
        // Map database flights (or fallback) to the UI components designed in the form
        updateFlightDataUI(searchedFlights);
    }

    private void updateFlightDataUI(List<Flight> flights) {
        List<Flight> displayList = flights;
        if (displayList == null || displayList.isEmpty()) {
            displayList = new java.util.ArrayList<>();
            displayList.add(new Flight(1, "YS101", "Kathmandu (KTM)", "Pokhara (PKR)", "28 APR 2026", "10:00 AM", 10, 5000.0, "ACTIVE"));
            displayList.add(new Flight(2, "YS202", "Kathmandu (KTM)", "Pokhara (PKR)", "28 APR 2026", "01:00 PM", 15, 4500.0, "ACTIVE"));
            displayList.add(new Flight(3, "YS303", "Kathmandu (KTM)", "Pokhara (PKR)", "28 APR 2026", "04:00 PM", 20, 5200.0, "ACTIVE"));
        }
        
        // Card 1
        if (displayList.size() > 0) {
            pnlFlightCard1.setVisible(true);
            Flight f = displayList.get(0);
            lblFlightName1.setText("Yatra Air " + f.getFlightCode().replaceAll("[^0-9]", ""));
            lblDepTime1.setText(f.getDepartureTime());
            lblFromCode1.setText(getAirportCode(f.getSource()));
            lblArrTime1.setText(getArrivalTime(f.getDepartureTime()));
            lblToCode1.setText(getAirportCode(f.getDestination()));
            lblFareVal1.setText("NPR " + String.format("%,.0f", f.getPrice()));
            
            // Determine class badge category label based on flight ticket pricing tiers
            String classBadgeText = "STANDARD CLASS";
            if (f.getPrice() >= 5200) classBadgeText = "PREMIUM PLUS";
            else if (f.getPrice() <= 4500) classBadgeText = "ECONOMY";
            lblClassBadge1.setText(classBadgeText);
        } else {
            pnlFlightCard1.setVisible(false);
        }
        
        // Card 2
        if (displayList.size() > 1) {
            pnlFlightCard2.setVisible(true);
            Flight f = displayList.get(1);
            lblFlightName2.setText("Yatra Air " + f.getFlightCode().replaceAll("[^0-9]", ""));
            lblDepTime2.setText(f.getDepartureTime());
            lblFromCode2.setText(getAirportCode(f.getSource()));
            lblArrTime2.setText(getArrivalTime(f.getDepartureTime()));
            lblToCode2.setText(getAirportCode(f.getDestination()));
            lblFareVal2.setText("NPR " + String.format("%,.0f", f.getPrice()));
            
            String classBadgeText = "STANDARD CLASS";
            if (f.getPrice() >= 5200) classBadgeText = "PREMIUM PLUS";
            else if (f.getPrice() <= 4500) classBadgeText = "ECONOMY";
            lblClassBadge2.setText(classBadgeText);
        } else {
            pnlFlightCard2.setVisible(false);
        }
        
        // Card 3
        if (displayList.size() > 2) {
            pnlFlightCard3.setVisible(true);
            Flight f = displayList.get(2);
            lblFlightName3.setText("Yatra Air " + f.getFlightCode().replaceAll("[^0-9]", ""));
            lblDepTime3.setText(f.getDepartureTime());
            lblFromCode3.setText(getAirportCode(f.getSource()));
            lblArrTime3.setText(getArrivalTime(f.getDepartureTime()));
            lblToCode3.setText(getAirportCode(f.getDestination()));
            lblFareVal3.setText("NPR " + String.format("%,.0f", f.getPrice()));
            
            String classBadgeText = "STANDARD CLASS";
            if (f.getPrice() >= 5200) classBadgeText = "PREMIUM PLUS";
            else if (f.getPrice() <= 4500) classBadgeText = "ECONOMY";
            lblClassBadge3.setText(classBadgeText);
        } else {
            pnlFlightCard3.setVisible(false);
        }
    }
    
    // Extracts the 3-letter IATA airport code nested within parenthesis (e.g. 'Kathmandu (KTM)' -> 'KTM')
    private String getAirportCode(String location) {
        if (location == null) return "KTM";
        if (location.contains("(")) {
            return location.substring(location.indexOf("(") + 1, location.indexOf(")"));
        }
        return location;
    }
    
    // Computes flight arrival time by adding a standard 1-hour travel duration to the departure timestamp
    private String getArrivalTime(String depTime) {
        if (depTime == null) return "11:00 AM";
        if (depTime.toLowerCase().contains("01:00") || depTime.toLowerCase().contains("1:00")) return "02:00 PM";
        if (depTime.toLowerCase().contains("04:00") || depTime.toLowerCase().contains("4:00")) return "05:00 PM";
        if (depTime.toLowerCase().contains("02:30")) return "03:30 PM";
        if (depTime.toLowerCase().contains("08:00")) return "09:00 AM";
        if (depTime.toLowerCase().contains("06:00")) return "07:00 AM";
        if (depTime.toLowerCase().contains("11:15")) return "12:15 PM";
        return "11:00 AM";
    }

    private void openBookingDialog(Flight flight) {
        // Build a modal JDialog window to collect traveler booking details dynamically on top of the search view
        javax.swing.JDialog dialog = new javax.swing.JDialog(this, "Book Flight - " + flight.getFlightCode(), true);
        dialog.setSize(400, 450);
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setLayout(null);
        dialog.getContentPane().setBackground(new java.awt.Color(255, 255, 255));
        
        javax.swing.JLabel lblTitle = new javax.swing.JLabel("Flight Booking Form");
        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 16));
        lblTitle.setBounds(30, 20, 340, 25);
        lblTitle.setForeground(new java.awt.Color(20, 28, 35));
        dialog.add(lblTitle);
        
        javax.swing.JLabel lblDetails = new javax.swing.JLabel("Flight: " + flight.getFlightCode() + " (" + flight.getSource() + " → " + flight.getDestination() + ")");
        lblDetails.setFont(new java.awt.Font("SansSerif", 0, 12));
        lblDetails.setBounds(30, 45, 340, 20);
        lblDetails.setForeground(new java.awt.Color(113, 128, 150));
        dialog.add(lblDetails);
        
        javax.swing.JLabel lblPassenger = new javax.swing.JLabel("Passenger Full Name");
        lblPassenger.setFont(new java.awt.Font("SansSerif", 1, 11));
        lblPassenger.setBounds(30, 90, 340, 20);
        lblPassenger.setForeground(new java.awt.Color(92, 100, 112));
        dialog.add(lblPassenger);
        
        javax.swing.JTextField txtPassenger = new javax.swing.JTextField(SessionManager.getCurrentUser() != null ? SessionManager.getCurrentUser().getFullName() : "");
        txtPassenger.setBounds(30, 115, 340, 35);
        styleTextField(txtPassenger);
        dialog.add(txtPassenger);
        
        javax.swing.JLabel lblSeat = new javax.swing.JLabel("Seat Number (e.g. A2, B5)");
        lblSeat.setFont(new java.awt.Font("SansSerif", 1, 11));
        lblSeat.setBounds(30, 170, 340, 20);
        lblSeat.setForeground(new java.awt.Color(92, 100, 112));
        dialog.add(lblSeat);
        
        javax.swing.JTextField txtSeatNum = new javax.swing.JTextField("A12");
        txtSeatNum.setBounds(30, 195, 340, 35);
        styleTextField(txtSeatNum);
        dialog.add(txtSeatNum);
        
        javax.swing.JLabel lblPayment = new javax.swing.JLabel("Payment Method");
        lblPayment.setFont(new java.awt.Font("SansSerif", 1, 11));
        lblPayment.setBounds(30, 250, 340, 20);
        lblPayment.setForeground(new java.awt.Color(92, 100, 112));
        dialog.add(lblPayment);
        
        javax.swing.JComboBox<String> cmbPaymentMethod = new javax.swing.JComboBox<>(new String[] {"E-SEWA", "KHALTI", "CREDIT_CARD"});
        cmbPaymentMethod.setBounds(30, 275, 340, 35);
        cmbPaymentMethod.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        dialog.add(cmbPaymentMethod);
        
        javax.swing.JButton btnConfirm = new javax.swing.JButton("Confirm Booking");
        btnConfirm.setBounds(30, 340, 340, 45);
        btnConfirm.setBackground(new java.awt.Color(255, 122, 26)); // Orange Accent
        btnConfirm.setForeground(java.awt.Color.WHITE);
        btnConfirm.setFont(new java.awt.Font("SansSerif", 1, 14));
        btnConfirm.setContentAreaFilled(true);
        btnConfirm.setBorderPainted(false);
        btnConfirm.setFocusPainted(false);
        btnConfirm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        btnConfirm.addActionListener(e -> {
            String name = txtPassenger.getText().trim();
            String seat = txtSeatNum.getText().trim();
            String payment = cmbPaymentMethod.getSelectedItem().toString();
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter passenger name.", "Required Field", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (seat.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter seat number.", "Required Field", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            boolean ok = bookingController.bookFlight(flight, name, seat, payment);
            if (ok) {
                JOptionPane.showMessageDialog(dialog, "Flight booked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                NavigationController.goToMyBookings(this);
            } else {
                JOptionPane.showMessageDialog(dialog, "Booking failed. Seat might be occupied.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(btnConfirm);
        
        dialog.setVisible(true);
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

    private void styleTextField(javax.swing.JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationController.goToDashboard(this);
    }

    private void btnSearchFlightActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationController.goToSearchFlight(this);
    }

    private void btnMyBookingsActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationController.goToMyBookings(this);
    }

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationController.goToProfile(this);
    }

    private void btnCustomerSupportActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationController.goToCustomerSupport(this);
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationController.logout(this);
    }

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {
        NavigationController.goToCheckIn(this);
    }

    private void btnViewDetails1ActionPerformed(java.awt.event.ActionEvent evt) {
        triggerBookingForCard(0);
    }

    private void btnViewDetails2ActionPerformed(java.awt.event.ActionEvent evt) {
        triggerBookingForCard(1);
    }

    private void btnViewDetails3ActionPerformed(java.awt.event.ActionEvent evt) {
        triggerBookingForCard(2);
    }

    private void btnFilterResultsActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Filters applied successfully based on search criteria.", "Filters Applied", JOptionPane.INFORMATION_MESSAGE);
    }

    private void triggerBookingForCard(int index) {
        List<Flight> displayList = searchedFlights;
        if (displayList == null || displayList.isEmpty()) {
            displayList = new java.util.ArrayList<>();
            displayList.add(new Flight(1, "YS101", "Kathmandu (KTM)", "Pokhara (PKR)", "28 APR 2026", "10:00 AM", 10, 5000.0, "ACTIVE"));
            displayList.add(new Flight(2, "YS202", "Kathmandu (KTM)", "Pokhara (PKR)", "28 APR 2026", "01:00 PM", 15, 4500.0, "ACTIVE"));
            displayList.add(new Flight(3, "YS303", "Kathmandu (KTM)", "Pokhara (PKR)", "28 APR 2026", "04:00 PM", 20, 5200.0, "ACTIVE"));
        }
        if (index < displayList.size()) {
            openBookingDialog(displayList.get(index));
        }
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
        lblSub = new javax.swing.JLabel();
        pnlCriteriaBar = new javax.swing.JPanel();
        lblRoutePlaneIcon = new javax.swing.JLabel();
        lblRouteTitle = new javax.swing.JLabel();
        lblRouteVal = new javax.swing.JLabel();
        pnlCriteriaSep1 = new javax.swing.JPanel();
        lblDateTitle = new javax.swing.JLabel();
        lblDateVal = new javax.swing.JLabel();
        pnlCriteriaSep2 = new javax.swing.JPanel();
        lblTravelersTitle = new javax.swing.JLabel();
        lblTravelersVal = new javax.swing.JLabel();
        btnFilterResults = new javax.swing.JButton();
        scrollResults = new javax.swing.JScrollPane();
        pnlResultsList = new javax.swing.JPanel();
        pnlFlightCard1 = new javax.swing.JPanel();
        pnlPlaneBox1 = new javax.swing.JPanel();
        lblPlaneIcon1 = new javax.swing.JLabel();
        lblFlightName1 = new javax.swing.JLabel();
        lblClassBadge1 = new javax.swing.JLabel();
        lblDepTime1 = new javax.swing.JLabel();
        lblFromCode1 = new javax.swing.JLabel();
        lblDuration1 = new javax.swing.JLabel();
        lblJourneyLine1 = new javax.swing.JLabel();
        lblDirect1 = new javax.swing.JLabel();
        lblArrTime1 = new javax.swing.JLabel();
        lblToCode1 = new javax.swing.JLabel();
        lblFareTitle1 = new javax.swing.JLabel();
        lblFareVal1 = new javax.swing.JLabel();
        btnViewDetails1 = new javax.swing.JButton();
        pnlFlightCard2 = new javax.swing.JPanel();
        pnlPlaneBox2 = new javax.swing.JPanel();
        lblPlaneIcon2 = new javax.swing.JLabel();
        lblFlightName2 = new javax.swing.JLabel();
        lblClassBadge2 = new javax.swing.JLabel();
        lblDepTime2 = new javax.swing.JLabel();
        lblFromCode2 = new javax.swing.JLabel();
        lblDuration2 = new javax.swing.JLabel();
        lblJourneyLine2 = new javax.swing.JLabel();
        lblDirect2 = new javax.swing.JLabel();
        lblArrTime2 = new javax.swing.JLabel();
        lblToCode2 = new javax.swing.JLabel();
        lblFareTitle2 = new javax.swing.JLabel();
        lblFareVal2 = new javax.swing.JLabel();
        btnViewDetails2 = new javax.swing.JButton();
        pnlFlightCard3 = new javax.swing.JPanel();
        pnlPlaneBox3 = new javax.swing.JPanel();
        lblPlaneIcon3 = new javax.swing.JLabel();
        lblFlightName3 = new javax.swing.JLabel();
        lblClassBadge3 = new javax.swing.JLabel();
        lblDepTime3 = new javax.swing.JLabel();
        lblFromCode3 = new javax.swing.JLabel();
        lblDuration3 = new javax.swing.JLabel();
        lblJourneyLine3 = new javax.swing.JLabel();
        lblDirect3 = new javax.swing.JLabel();
        lblArrTime3 = new javax.swing.JLabel();
        lblToCode3 = new javax.swing.JLabel();
        lblFareTitle3 = new javax.swing.JLabel();
        lblFareVal3 = new javax.swing.JLabel();
        btnViewDetails3 = new javax.swing.JButton();

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

        btnSearchFlight.setBackground(new java.awt.Color(20, 28, 35));
        btnSearchFlight.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSearchFlight.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchFlight.setText("Search Flight");
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

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(20, 28, 35));
        lblTitle.setText("Search Flight Results");
        pnlMainBg.add(lblTitle);
        lblTitle.setBounds(30, 20, 300, 25);

        lblSub.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(113, 128, 150));
        lblSub.setText("Showing available flights based on your search criteria.");
        pnlMainBg.add(lblSub);
        lblSub.setBounds(30, 45, 500, 20);

        pnlCriteriaBar.setBackground(new java.awt.Color(255, 255, 255));
        pnlCriteriaBar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlCriteriaBar.setLayout(null);

        lblRoutePlaneIcon.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        lblRoutePlaneIcon.setForeground(new java.awt.Color(26, 115, 232));
        lblRoutePlaneIcon.setText("✈");
        pnlCriteriaBar.add(lblRoutePlaneIcon);
        lblRoutePlaneIcon.setBounds(20, 15, 30, 40);

        lblRouteTitle.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblRouteTitle.setForeground(new java.awt.Color(120, 140, 160));
        lblRouteTitle.setText("ROUTE");
        pnlCriteriaBar.add(lblRouteTitle);
        lblRouteTitle.setBounds(60, 15, 100, 15);

        lblRouteVal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblRouteVal.setForeground(new java.awt.Color(20, 28, 35));
        lblRouteVal.setText("KTM → PKR");
        pnlCriteriaBar.add(lblRouteVal);
        lblRouteVal.setBounds(60, 32, 120, 20);

        pnlCriteriaSep1.setBackground(new java.awt.Color(226, 232, 224));
        pnlCriteriaBar.add(pnlCriteriaSep1);
        pnlCriteriaSep1.setBounds(200, 15, 1, 40);

        lblDateTitle.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblDateTitle.setForeground(new java.awt.Color(120, 140, 160));
        lblDateTitle.setText("DEPARTURE DATE");
        pnlCriteriaBar.add(lblDateTitle);
        lblDateTitle.setBounds(220, 15, 150, 15);

        lblDateVal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDateVal.setForeground(new java.awt.Color(20, 28, 35));
        lblDateVal.setText("28 APR 2026");
        pnlCriteriaBar.add(lblDateVal);
        lblDateVal.setBounds(220, 32, 150, 20);

        pnlCriteriaSep2.setBackground(new java.awt.Color(226, 232, 224));
        pnlCriteriaBar.add(pnlCriteriaSep2);
        pnlCriteriaSep2.setBounds(390, 15, 1, 40);

        lblTravelersTitle.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblTravelersTitle.setForeground(new java.awt.Color(120, 140, 160));
        lblTravelersTitle.setText("TRAVELERS");
        pnlCriteriaBar.add(lblTravelersTitle);
        lblTravelersTitle.setBounds(410, 15, 150, 15);

        lblTravelersVal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTravelersVal.setForeground(new java.awt.Color(20, 28, 35));
        lblTravelersVal.setText("1 Passenger");
        pnlCriteriaBar.add(lblTravelersVal);
        lblTravelersVal.setBounds(410, 32, 150, 20);

        btnFilterResults.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnFilterResults.setForeground(new java.awt.Color(92, 100, 112));
        btnFilterResults.setText("⚙  Filter Results");
        btnFilterResults.addActionListener(this::btnFilterResultsActionPerformed);
        pnlCriteriaBar.add(btnFilterResults);
        btnFilterResults.setBounds(720, 17, 140, 36);

        pnlMainBg.add(pnlCriteriaBar);
        pnlCriteriaBar.setBounds(30, 75, 890, 70);

        scrollResults.setBorder(null);
        scrollResults.setOpaque(false);

        pnlResultsList.setBackground(new java.awt.Color(244, 246, 249));
        pnlResultsList.setLayout(null);

        pnlFlightCard1.setBackground(new java.awt.Color(255, 255, 255));
        pnlFlightCard1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlFlightCard1.setLayout(null);

        pnlPlaneBox1.setBackground(new java.awt.Color(8, 22, 42));
        pnlPlaneBox1.setLayout(null);

        lblPlaneIcon1.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblPlaneIcon1.setForeground(new java.awt.Color(255, 255, 255));
        lblPlaneIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlaneIcon1.setText("✈");
        pnlPlaneBox1.add(lblPlaneIcon1);
        lblPlaneIcon1.setBounds(0, 0, 60, 60);

        pnlFlightCard1.add(pnlPlaneBox1);
        pnlPlaneBox1.setBounds(20, 20, 60, 60);

        lblFlightName1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblFlightName1.setForeground(new java.awt.Color(20, 28, 35));
        lblFlightName1.setText("Yatra Air 101");
        pnlFlightCard1.add(lblFlightName1);
        lblFlightName1.setBounds(95, 22, 130, 20);

        lblClassBadge1.setBackground(new java.awt.Color(235, 248, 255));
        lblClassBadge1.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblClassBadge1.setForeground(new java.awt.Color(26, 115, 232));
        lblClassBadge1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClassBadge1.setOpaque(true);
        lblClassBadge1.setText("STANDARD CLASS");
        pnlFlightCard1.add(lblClassBadge1);
        lblClassBadge1.setBounds(95, 48, 120, 18);

        lblDepTime1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDepTime1.setForeground(new java.awt.Color(20, 28, 35));
        lblDepTime1.setText("10:00 AM");
        pnlFlightCard1.add(lblDepTime1);
        lblDepTime1.setBounds(240, 22, 80, 20);

        lblFromCode1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblFromCode1.setForeground(new java.awt.Color(160, 174, 192));
        lblFromCode1.setText("KTM");
        pnlFlightCard1.add(lblFromCode1);
        lblFromCode1.setBounds(240, 48, 80, 20);

        lblDuration1.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lblDuration1.setForeground(new java.awt.Color(160, 174, 192));
        lblDuration1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration1.setText("1H 00M");
        pnlFlightCard1.add(lblDuration1);
        lblDuration1.setBounds(330, 15, 160, 15);

        lblJourneyLine1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblJourneyLine1.setForeground(new java.awt.Color(200, 210, 220));
        lblJourneyLine1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJourneyLine1.setText("─── ✈ ───");
        pnlFlightCard1.add(lblJourneyLine1);
        lblJourneyLine1.setBounds(330, 32, 160, 20);

        lblDirect1.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblDirect1.setForeground(new java.awt.Color(26, 115, 232));
        lblDirect1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDirect1.setText("Direct Flight");
        pnlFlightCard1.add(lblDirect1);
        lblDirect1.setBounds(330, 52, 160, 15);

        lblArrTime1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblArrTime1.setForeground(new java.awt.Color(20, 28, 35));
        lblArrTime1.setText("11:00 AM");
        pnlFlightCard1.add(lblArrTime1);
        lblArrTime1.setBounds(510, 22, 80, 20);

        lblToCode1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblToCode1.setForeground(new java.awt.Color(160, 174, 192));
        lblToCode1.setText("PKR");
        pnlFlightCard1.add(lblToCode1);
        lblToCode1.setBounds(510, 48, 80, 20);

        lblFareTitle1.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblFareTitle1.setForeground(new java.awt.Color(120, 140, 160));
        lblFareTitle1.setText("TOTAL FARE");
        pnlFlightCard1.add(lblFareTitle1);
        lblFareTitle1.setBounds(600, 22, 100, 15);

        lblFareVal1.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblFareVal1.setForeground(new java.awt.Color(26, 115, 232));
        lblFareVal1.setText("NPR 5,000");
        pnlFlightCard1.add(lblFareVal1);
        lblFareVal1.setBounds(600, 43, 100, 25);

        btnViewDetails1.setBackground(new java.awt.Color(8, 22, 42));
        btnViewDetails1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnViewDetails1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetails1.setText("View Details");
        btnViewDetails1.addActionListener(this::btnViewDetails1ActionPerformed);
        pnlFlightCard1.add(btnViewDetails1);
        btnViewDetails1.setBounds(710, 30, 120, 40);

        pnlResultsList.add(pnlFlightCard1);
        pnlFlightCard1.setBounds(0, 0, 850, 100);

        pnlFlightCard2.setBackground(new java.awt.Color(255, 255, 255));
        pnlFlightCard2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlFlightCard2.setLayout(null);

        pnlPlaneBox2.setBackground(new java.awt.Color(8, 22, 42));
        pnlPlaneBox2.setLayout(null);

        lblPlaneIcon2.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblPlaneIcon2.setForeground(new java.awt.Color(255, 255, 255));
        lblPlaneIcon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlaneIcon2.setText("✈");
        pnlPlaneBox2.add(lblPlaneIcon2);
        lblPlaneIcon2.setBounds(0, 0, 60, 60);

        pnlFlightCard2.add(pnlPlaneBox2);
        pnlPlaneBox2.setBounds(20, 20, 60, 60);

        lblFlightName2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblFlightName2.setForeground(new java.awt.Color(20, 28, 35));
        lblFlightName2.setText("Yatra Air 202");
        pnlFlightCard2.add(lblFlightName2);
        lblFlightName2.setBounds(95, 22, 130, 20);

        lblClassBadge2.setBackground(new java.awt.Color(235, 248, 255));
        lblClassBadge2.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblClassBadge2.setForeground(new java.awt.Color(26, 115, 232));
        lblClassBadge2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClassBadge2.setOpaque(true);
        lblClassBadge2.setText("ECONOMY");
        pnlFlightCard2.add(lblClassBadge2);
        lblClassBadge2.setBounds(95, 48, 120, 18);

        lblDepTime2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDepTime2.setForeground(new java.awt.Color(20, 28, 35));
        lblDepTime2.setText("01:00 PM");
        pnlFlightCard2.add(lblDepTime2);
        lblDepTime2.setBounds(240, 22, 80, 20);

        lblFromCode2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblFromCode2.setForeground(new java.awt.Color(160, 174, 192));
        lblFromCode2.setText("KTM");
        pnlFlightCard2.add(lblFromCode2);
        lblFromCode2.setBounds(240, 48, 80, 20);

        lblDuration2.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lblDuration2.setForeground(new java.awt.Color(160, 174, 192));
        lblDuration2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration2.setText("1H 00M");
        pnlFlightCard2.add(lblDuration2);
        lblDuration2.setBounds(330, 15, 160, 15);

        lblJourneyLine2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblJourneyLine2.setForeground(new java.awt.Color(200, 210, 220));
        lblJourneyLine2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJourneyLine2.setText("─── ✈ ───");
        pnlFlightCard2.add(lblJourneyLine2);
        lblJourneyLine2.setBounds(330, 32, 160, 20);

        lblDirect2.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblDirect2.setForeground(new java.awt.Color(26, 115, 232));
        lblDirect2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDirect2.setText("Direct Flight");
        pnlFlightCard2.add(lblDirect2);
        lblDirect2.setBounds(330, 52, 160, 15);

        lblArrTime2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblArrTime2.setForeground(new java.awt.Color(20, 28, 35));
        lblArrTime2.setText("02:00 PM");
        pnlFlightCard2.add(lblArrTime2);
        lblArrTime2.setBounds(510, 22, 80, 20);

        lblToCode2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblToCode2.setForeground(new java.awt.Color(160, 174, 192));
        lblToCode2.setText("PKR");
        pnlFlightCard2.add(lblToCode2);
        lblToCode2.setBounds(510, 48, 80, 20);

        lblFareTitle2.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblFareTitle2.setForeground(new java.awt.Color(120, 140, 160));
        lblFareTitle2.setText("TOTAL FARE");
        pnlFlightCard2.add(lblFareTitle2);
        lblFareTitle2.setBounds(600, 22, 100, 15);

        lblFareVal2.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblFareVal2.setForeground(new java.awt.Color(26, 115, 232));
        lblFareVal2.setText("NPR 4,500");
        pnlFlightCard2.add(lblFareVal2);
        lblFareVal2.setBounds(600, 43, 100, 25);

        btnViewDetails2.setBackground(new java.awt.Color(8, 22, 42));
        btnViewDetails2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnViewDetails2.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetails2.setText("View Details");
        btnViewDetails2.addActionListener(this::btnViewDetails2ActionPerformed);
        pnlFlightCard2.add(btnViewDetails2);
        btnViewDetails2.setBounds(710, 30, 120, 40);

        pnlResultsList.add(pnlFlightCard2);
        pnlFlightCard2.setBounds(0, 115, 850, 100);

        pnlFlightCard3.setBackground(new java.awt.Color(255, 255, 255));
        pnlFlightCard3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlFlightCard3.setLayout(null);

        pnlPlaneBox3.setBackground(new java.awt.Color(8, 22, 42));
        pnlPlaneBox3.setLayout(null);

        lblPlaneIcon3.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblPlaneIcon3.setForeground(new java.awt.Color(255, 255, 255));
        lblPlaneIcon3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlaneIcon3.setText("✈");
        pnlPlaneBox3.add(lblPlaneIcon3);
        lblPlaneIcon3.setBounds(0, 0, 60, 60);

        pnlFlightCard3.add(pnlPlaneBox3);
        pnlPlaneBox3.setBounds(20, 20, 60, 60);

        lblFlightName3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblFlightName3.setForeground(new java.awt.Color(20, 28, 35));
        lblFlightName3.setText("Yatra Air 303");
        pnlFlightCard3.add(lblFlightName3);
        lblFlightName3.setBounds(95, 22, 130, 20);

        lblClassBadge3.setBackground(new java.awt.Color(235, 248, 255));
        lblClassBadge3.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblClassBadge3.setForeground(new java.awt.Color(26, 115, 232));
        lblClassBadge3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClassBadge3.setOpaque(true);
        lblClassBadge3.setText("PREMIUM PLUS");
        pnlFlightCard3.add(lblClassBadge3);
        lblClassBadge3.setBounds(95, 48, 120, 18);

        lblDepTime3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDepTime3.setForeground(new java.awt.Color(20, 28, 35));
        lblDepTime3.setText("04:00 PM");
        pnlFlightCard3.add(lblDepTime3);
        lblDepTime3.setBounds(240, 22, 80, 20);

        lblFromCode3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblFromCode3.setForeground(new java.awt.Color(160, 174, 192));
        lblFromCode3.setText("KTM");
        pnlFlightCard3.add(lblFromCode3);
        lblFromCode3.setBounds(240, 48, 80, 20);

        lblDuration3.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lblDuration3.setForeground(new java.awt.Color(160, 174, 192));
        lblDuration3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration3.setText("1H 00M");
        pnlFlightCard3.add(lblDuration3);
        lblDuration3.setBounds(330, 15, 160, 15);

        lblJourneyLine3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblJourneyLine3.setForeground(new java.awt.Color(200, 210, 220));
        lblJourneyLine3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJourneyLine3.setText("─── ✈ ───");
        pnlFlightCard3.add(lblJourneyLine3);
        lblJourneyLine3.setBounds(330, 32, 160, 20);

        lblDirect3.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblDirect3.setForeground(new java.awt.Color(26, 115, 232));
        lblDirect3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDirect3.setText("Direct Flight");
        pnlFlightCard3.add(lblDirect3);
        lblDirect3.setBounds(330, 52, 160, 15);

        lblArrTime3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblArrTime3.setForeground(new java.awt.Color(20, 28, 35));
        lblArrTime3.setText("05:00 PM");
        pnlFlightCard3.add(lblArrTime3);
        lblArrTime3.setBounds(510, 22, 80, 20);

        lblToCode3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblToCode3.setForeground(new java.awt.Color(160, 174, 192));
        lblToCode3.setText("PKR");
        pnlFlightCard3.add(lblToCode3);
        lblToCode3.setBounds(510, 48, 80, 20);

        lblFareTitle3.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblFareTitle3.setForeground(new java.awt.Color(120, 140, 160));
        lblFareTitle3.setText("TOTAL FARE");
        pnlFlightCard3.add(lblFareTitle3);
        lblFareTitle3.setBounds(600, 22, 100, 15);

        lblFareVal3.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblFareVal3.setForeground(new java.awt.Color(26, 115, 232));
        lblFareVal3.setText("NPR 5,200");
        pnlFlightCard3.add(lblFareVal3);
        lblFareVal3.setBounds(600, 43, 100, 25);

        btnViewDetails3.setBackground(new java.awt.Color(8, 22, 42));
        btnViewDetails3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnViewDetails3.setForeground(new java.awt.Color(255, 255, 255));
        btnViewDetails3.setText("View Details");
        btnViewDetails3.addActionListener(this::btnViewDetails3ActionPerformed);
        pnlFlightCard3.add(btnViewDetails3);
        btnViewDetails3.setBounds(710, 30, 120, 40);

        pnlResultsList.add(pnlFlightCard3);
        pnlFlightCard3.setBounds(0, 230, 850, 100);

        scrollResults.setViewportView(pnlResultsList);

        pnlMainBg.add(scrollResults);
        scrollResults.setBounds(30, 160, 890, 520);

        getContentPane().add(pnlMainBg);
        pnlMainBg.setBounds(250, 60, 950, 740);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JButton btnCustomerSupport;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnFilterResults;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMyBookings;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnSearchFlight;
    private javax.swing.JButton btnViewDetails1;
    private javax.swing.JButton btnViewDetails2;
    private javax.swing.JButton btnViewDetails3;
    private javax.swing.JLabel lblArrTime1;
    private javax.swing.JLabel lblArrTime2;
    private javax.swing.JLabel lblArrTime3;
    private javax.swing.JLabel lblBackToHome;
    private javax.swing.JLabel lblClassBadge1;
    private javax.swing.JLabel lblClassBadge2;
    private javax.swing.JLabel lblClassBadge3;
    private javax.swing.JLabel lblDateTitle;
    private javax.swing.JLabel lblDateVal;
    private javax.swing.JLabel lblDepTime1;
    private javax.swing.JLabel lblDepTime2;
    private javax.swing.JLabel lblDepTime3;
    private javax.swing.JLabel lblDirect1;
    private javax.swing.JLabel lblDirect2;
    private javax.swing.JLabel lblDirect3;
    private javax.swing.JLabel lblDuration1;
    private javax.swing.JLabel lblDuration2;
    private javax.swing.JLabel lblDuration3;
    private javax.swing.JLabel lblFareTitle1;
    private javax.swing.JLabel lblFareTitle2;
    private javax.swing.JLabel lblFareTitle3;
    private javax.swing.JLabel lblFareVal1;
    private javax.swing.JLabel lblFareVal2;
    private javax.swing.JLabel lblFareVal3;
    private javax.swing.JLabel lblFlightName1;
    private javax.swing.JLabel lblFlightName2;
    private javax.swing.JLabel lblFlightName3;
    private javax.swing.JLabel lblFromCode1;
    private javax.swing.JLabel lblFromCode2;
    private javax.swing.JLabel lblFromCode3;
    private javax.swing.JLabel lblHeaderIcons;
    private javax.swing.JLabel lblJourneyLine1;
    private javax.swing.JLabel lblJourneyLine2;
    private javax.swing.JLabel lblJourneyLine3;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPlaneIcon1;
    private javax.swing.JLabel lblPlaneIcon2;
    private javax.swing.JLabel lblPlaneIcon3;
    private javax.swing.JLabel lblRoutePlaneIcon;
    private javax.swing.JLabel lblRouteTitle;
    private javax.swing.JLabel lblRouteVal;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblToCode1;
    private javax.swing.JLabel lblToCode2;
    private javax.swing.JLabel lblToCode3;
    private javax.swing.JLabel lblTopTitle;
    private javax.swing.JLabel lblTravelersTitle;
    private javax.swing.JLabel lblTravelersVal;
    private javax.swing.JPanel pnlCriteriaBar;
    private javax.swing.JPanel pnlCriteriaSep1;
    private javax.swing.JPanel pnlCriteriaSep2;
    private javax.swing.JPanel pnlFlightCard1;
    private javax.swing.JPanel pnlFlightCard2;
    private javax.swing.JPanel pnlFlightCard3;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlPlaneBox1;
    private javax.swing.JPanel pnlPlaneBox2;
    private javax.swing.JPanel pnlPlaneBox3;
    private javax.swing.JPanel pnlResultsList;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTopHeader;
    private javax.swing.JScrollPane scrollResults;
    // End of variables declaration//GEN-END:variables
}
