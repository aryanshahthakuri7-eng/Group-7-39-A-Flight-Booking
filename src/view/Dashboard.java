package view;

import controller.NavigationController;
import controller.DashboardController;
import model.Booking;
import model.StatCard;
import java.util.ArrayList;

/**
 * Main application dashboard showing user booking statistics, recent tickets,
 * and quick flight search access. Fully database-driven.
 */
public class Dashboard extends javax.swing.JFrame {

    private DashboardController dashController;

    public Dashboard() {
        dashController = new DashboardController();
        
        initComponents();
        getContentPane().setBackground(new java.awt.Color(20, 28, 35)); // Dark Navy Blue
        
        // Custom stylings
        styleSidebarButton(btnDashboard);
        styleSidebarButton(btnSearchFlight);
        styleSidebarButton(btnMyBookings);
        styleSidebarButton(btnCheckIn);
        styleSidebarButton(btnProfile);
        styleSidebarButton(btnCustomerSupport);
        styleSidebarButton(btnLogout);
        
        styleSidebarActiveButton(btnDashboard);
        
        styleCardPanel(pnlQuickSearch);
        styleCardPanel(cardUpcoming);
        styleCardPanel(cardCancelled);
        styleCardPanel(cardTotalSpent);
        styleCardPanel(cardLoyalty);
        styleCardPanel(cardUpcomingBookings);
        styleCardPanel(pnlTicketBox);
        styleCardPanel(cardQuickActions);
        
        styleRowButton(btnActionSearch);
        styleRowButton(btnActionBookings);
        styleRowButton(btnActionProfile);
        styleRowButton(btnActionLogout);
        
        styleComboBox(cmbFrom);
        styleComboBox(cmbTo);
        styleComboBox(cmbPassengers);
        // styleTextField(txtDepartDate); // Removed since it's a JDateChooser now
        
        btnSearchFlights.setContentAreaFilled(true);
        btnSearchFlights.setBackground(new java.awt.Color(255, 122, 26)); // Orange Accent
        btnSearchFlights.setForeground(java.awt.Color.WHITE);
        btnSearchFlights.setBorderPainted(false);
        btnSearchFlights.setFocusPainted(false);
        btnSearchFlights.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        styleStatIcon(lblUpcomingIcon, new java.awt.Color(235, 248, 255), new java.awt.Color(26, 115, 232));
        styleStatIcon(lblCancelledIcon, new java.awt.Color(254, 242, 242), new java.awt.Color(239, 68, 68));
        styleStatIcon(lblSpentIcon, new java.awt.Color(240, 253, 244), new java.awt.Color(34, 197, 94));
        styleStatIcon(lblLoyaltyIcon, new java.awt.Color(255, 247, 237), new java.awt.Color(249, 115, 22));
        
        lblConfirmedBadge.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(230, 244, 234), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));
        lblTicketLogo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        
        populateDashboardData();
    }
    
    private void populateDashboardData() {
        dashController.refreshData(); // Sync with MySQL
        
        // --- Populate ComboBoxes from String[] Arrays ---
        cmbFrom.setModel(new javax.swing.DefaultComboBoxModel<>(dashController.getFromLocations()));
        cmbTo.setModel(new javax.swing.DefaultComboBoxModel<>(dashController.getToLocations()));
        cmbPassengers.setModel(new javax.swing.DefaultComboBoxModel<>(dashController.getPassengerOptions()));
        
        // --- Populate Welcome Message ---
        lblWelcomeUser.setText(dashController.getWelcomeMessage());
        lblWelcomeSub.setText(dashController.getWelcomeSubtitle());
        
        // --- Populate Stat Cards from ArrayList<StatCard> ---
        ArrayList<StatCard> stats = dashController.getStatCards();
        if (stats.size() > 0) {
            StatCard upcomingStat = stats.get(0);
            lblUpcomingIcon.setText(upcomingStat.getIcon());
            lblUpcomingVal.setText(upcomingStat.getValue());
            lblUpcomingLabel.setText(upcomingStat.getLabel());
            lblUpcomingSub.setText(upcomingStat.getSubtitle());
        }
        if (stats.size() > 1) {
            StatCard cancelledStat = stats.get(1);
            lblCancelledIcon.setText(cancelledStat.getIcon());
            lblCancelledVal.setText(cancelledStat.getValue());
            lblCancelledLabel.setText(cancelledStat.getLabel());
            lblCancelledSub.setText(cancelledStat.getSubtitle());
        }
        if (stats.size() > 2) {
            StatCard spentStat = stats.get(2);
            lblSpentIcon.setText(spentStat.getIcon());
            lblSpentVal.setText(spentStat.getValue());
            lblSpentLabel.setText(spentStat.getLabel());
            lblSpentSub.setText(spentStat.getSubtitle());
        }
        if (stats.size() > 3) {
            StatCard loyaltyStat = stats.get(3);
            lblLoyaltyIcon.setText(loyaltyStat.getIcon());
            lblLoyaltyVal.setText(loyaltyStat.getValue());
            lblLoyaltyLabel.setText(loyaltyStat.getLabel());
            lblLoyaltySub.setText(loyaltyStat.getSubtitle());
        }
        
        // --- Populate Ticket Box from database Booking ---
        Booking firstBooking = dashController.getFirstUpcomingBooking();
        if (firstBooking != null) {
            lblRoute.setText(firstBooking.getRoute());
            lblFlightInfo.setText(firstBooking.getFormattedFlightInfo());
            lblConfirmedBadge.setText(firstBooking.getStatus());
            lblConfirmedBadge.setVisible(true);
            lblSeatVal.setText(firstBooking.getSeatNumber());
            lblPassengerVal.setText(firstBooking.getPassengerName());
            lblAmountVal.setText(firstBooking.getFormattedAmount());
            pnlTicketBox.setVisible(true);
        } else {
            lblRoute.setText("No upcoming trips");
            lblFlightInfo.setText("Search and book a flight to begin your journey.");
            lblConfirmedBadge.setVisible(false);
            lblSeatVal.setText("-");
            lblPassengerVal.setText("-");
            lblAmountVal.setText("-");
        }
        
        ArrayList<Booking> upcoming = dashController.getUpcomingBookings();
        if (upcoming.size() > 1) {
            lblNoMoreBookings.setText("+" + (upcoming.size() - 1) + " more upcoming bookings");
        } else {
            lblNoMoreBookings.setText("No more upcoming bookings");
        }
        
        // --- Populate Recent Tickets Section (replacing Quick Actions) ---
        lblQuickActionsTitle.setText("Latest Bookings");
        ArrayList<Booking> allBookings = dashController.getAllBookings();
        
        if (allBookings.size() > 0) {
            Booking b1 = allBookings.get(0);
            btnActionSearch.setText("🎟️ " + b1.getBookingId() + " - " + b1.getRoute() + " (" + b1.getStatus() + ")");
            btnActionSearch.setVisible(true);
        } else {
            btnActionSearch.setText("No bookings found. Click to Search Flights.");
            btnActionSearch.setVisible(true);
        }
        
        if (allBookings.size() > 1) {
            Booking b2 = allBookings.get(1);
            btnActionBookings.setText("🎟️ " + b2.getBookingId() + " - " + b2.getRoute() + " (" + b2.getStatus() + ")");
            btnActionBookings.setVisible(true);
        } else {
            btnActionBookings.setVisible(false);
        }
        
        if (allBookings.size() > 2) {
            Booking b3 = allBookings.get(2);
            btnActionProfile.setText("🎟️ " + b3.getBookingId() + " - " + b3.getRoute() + " (" + b3.getStatus() + ")");
            btnActionProfile.setVisible(true);
        } else {
            btnActionProfile.setVisible(false);
        }
        
        btnActionLogout.setText("💼 View All Bookings                               〉");
        btnActionLogout.setForeground(new java.awt.Color(20, 28, 35));
        
        lblStatusText.setText(dashController.getSystemStatus());
        if (dashController.isDatabaseConnected()) {
            lblStatusText.setForeground(new java.awt.Color(16, 185, 129)); // Green Online
        } else {
            lblStatusText.setForeground(new java.awt.Color(239, 68, 68)); // Red Offline
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

    private void styleRowButton(javax.swing.JButton btn) {
        btn.setContentAreaFilled(true);
        btn.setBackground(java.awt.Color.WHITE);
        btn.setForeground(new java.awt.Color(20, 28, 35));
        btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(243, 244, 246), 1, true));
        btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn.setMargin(new java.awt.Insets(0, 15, 0, 15));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
    private void styleTextField(javax.swing.JTextField txt) {
        txt.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleComboBox(javax.swing.JComboBox<?> cmb) {
        cmb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
    }

    private void styleStatIcon(javax.swing.JLabel lbl, java.awt.Color bg, java.awt.Color fg) {
        lbl.setBackground(bg);
        lbl.setForeground(fg);
        lbl.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(bg, 1, true),
            javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2)
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
        lblWelcomeUser = new javax.swing.JLabel();
        lblWelcomeSub = new javax.swing.JLabel();
        lblFrom = new javax.swing.JLabel();
        cmbFrom = new javax.swing.JComboBox();
        lblTo = new javax.swing.JLabel();
        cmbTo = new javax.swing.JComboBox();
        lblDepartDate = new javax.swing.JLabel();
        txtDepartDate = new com.toedter.calendar.JDateChooser();
        lblPassengers = new javax.swing.JLabel();
        cmbPassengers = new javax.swing.JComboBox();
        btnSearchFlights = new javax.swing.JButton();
        pnlQuickSearch = new javax.swing.JPanel();
        lblUpcomingIcon = new javax.swing.JLabel();
        lblUpcomingVal = new javax.swing.JLabel();
        lblUpcomingLabel = new javax.swing.JLabel();
        lblUpcomingSub = new javax.swing.JLabel();
        cardUpcoming = new javax.swing.JPanel();
        lblCancelledIcon = new javax.swing.JLabel();
        lblCancelledVal = new javax.swing.JLabel();
        lblCancelledLabel = new javax.swing.JLabel();
        lblCancelledSub = new javax.swing.JLabel();
        cardCancelled = new javax.swing.JPanel();
        lblSpentIcon = new javax.swing.JLabel();
        lblSpentVal = new javax.swing.JLabel();
        lblSpentLabel = new javax.swing.JLabel();
        lblSpentSub = new javax.swing.JLabel();
        cardTotalSpent = new javax.swing.JPanel();
        lblLoyaltyIcon = new javax.swing.JLabel();
        lblLoyaltyVal = new javax.swing.JLabel();
        lblLoyaltyLabel = new javax.swing.JLabel();
        lblLoyaltySub = new javax.swing.JLabel();
        cardLoyalty = new javax.swing.JPanel();
        lblUpcomingTitle = new javax.swing.JLabel();
        lblViewAll = new javax.swing.JLabel();
        lblTicketLogo = new javax.swing.JLabel();
        lblRoute = new javax.swing.JLabel();
        lblFlightInfo = new javax.swing.JLabel();
        lblConfirmedBadge = new javax.swing.JLabel();
        pnlTicketLine = new javax.swing.JPanel();
        lblSeatIcon = new javax.swing.JLabel();
        lblSeatLabel = new javax.swing.JLabel();
        lblSeatVal = new javax.swing.JLabel();
        lblPassengerIcon = new javax.swing.JLabel();
        lblPassengerLabel = new javax.swing.JLabel();
        lblPassengerVal = new javax.swing.JLabel();
        lblAmountIcon = new javax.swing.JLabel();
        lblAmountLabel = new javax.swing.JLabel();
        lblAmountVal = new javax.swing.JLabel();
        pnlTicketBox = new javax.swing.JPanel();
        lblNoMoreBookings = new javax.swing.JLabel();
        cardUpcomingBookings = new javax.swing.JPanel();
        lblQuickActionsTitle = new javax.swing.JLabel();
        btnActionSearch = new javax.swing.JButton();
        btnActionBookings = new javax.swing.JButton();
        btnActionProfile = new javax.swing.JButton();
        btnActionLogout = new javax.swing.JButton();
        cardQuickActions = new javax.swing.JPanel();
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
        btnDashboard.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setBackground(new java.awt.Color(20, 28, 35));
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

        btnCheckIn.setText("Check-in");
        btnCheckIn.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnCheckIn.setForeground(new java.awt.Color(92, 100, 112));
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

        lblWelcomeUser.setText("Welcome User,");
        lblWelcomeUser.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        lblWelcomeUser.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblWelcomeUser);
        lblWelcomeUser.setBounds(280, 80, 250, 30);

        lblWelcomeSub.setText("Review your flight schedules and upcoming travels.");
        lblWelcomeSub.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblWelcomeSub.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblWelcomeSub);
        lblWelcomeSub.setBounds(280, 110, 400, 20);

        lblFrom.setText("FROM");
        lblFrom.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblFrom.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblFrom);
        lblFrom.setBounds(300, 150, 100, 15);

        cmbFrom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "", "" }));
        cmbFrom.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        getContentPane().add(cmbFrom);
        cmbFrom.setBounds(300, 170, 170, 35);

        lblTo.setText("TO");
        lblTo.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblTo.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblTo);
        lblTo.setBounds(485, 150, 100, 15);

        cmbTo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "", "" }));
        cmbTo.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        getContentPane().add(cmbTo);
        cmbTo.setBounds(485, 170, 170, 35);

        lblDepartDate.setText("DEPART DATE");
        lblDepartDate.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblDepartDate.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblDepartDate);
        lblDepartDate.setBounds(670, 150, 100, 15);

        txtDepartDate.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        // txtDepartDate.setText("DD/MM/YYYY");
        getContentPane().add(txtDepartDate);
        txtDepartDate.setBounds(670, 170, 120, 35);

        lblPassengers.setText("PASSENGERS");
        lblPassengers.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblPassengers.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblPassengers);
        lblPassengers.setBounds(805, 150, 100, 15);

        cmbPassengers.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        cmbPassengers.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "", "" }));
        getContentPane().add(cmbPassengers);
        cmbPassengers.setBounds(805, 170, 130, 35);

        btnSearchFlights.setText("Search flights");
        btnSearchFlights.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnSearchFlights.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchFlights.setBackground(new java.awt.Color(255, 115, 21));
        btnSearchFlights.addActionListener(this::btnSearchFlightsActionPerformed);
        getContentPane().add(btnSearchFlights);
        btnSearchFlights.setBounds(950, 170, 200, 35);

        pnlQuickSearch.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(pnlQuickSearch);
        pnlQuickSearch.setBounds(280, 140, 890, 80);

        lblUpcomingIcon.setText("⚠️");
        lblUpcomingIcon.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblUpcomingIcon.setBackground(new java.awt.Color(235, 235, 255));
        lblUpcomingIcon.setForeground(new java.awt.Color(26, 115, 232));
        lblUpcomingIcon.setOpaque(true);
        lblUpcomingIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblUpcomingIcon);
        lblUpcomingIcon.setBounds(300, 260, 35, 35);

        lblUpcomingVal.setText("2");
        lblUpcomingVal.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblUpcomingVal.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblUpcomingVal);
        lblUpcomingVal.setBounds(350, 260, 40, 35);

        lblUpcomingLabel.setText("UPCOMING TRIPS");
        lblUpcomingLabel.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblUpcomingLabel.setForeground(new java.awt.Color(120, 140, 160));
        getContentPane().add(lblUpcomingLabel);
        lblUpcomingLabel.setBounds(350, 295, 130, 15);

        lblUpcomingSub.setText("View your next booking");
        lblUpcomingSub.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblUpcomingSub.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblUpcomingSub);
        lblUpcomingSub.setBounds(350, 315, 130, 15);

        cardUpcoming.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cardUpcoming);
        cardUpcoming.setBounds(280, 245, 210, 100);

        lblCancelledIcon.setText("🚫");
        lblCancelledIcon.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblCancelledIcon.setBackground(new java.awt.Color(255, 235, 235));
        lblCancelledIcon.setForeground(new java.awt.Color(255, 59, 48));
        lblCancelledIcon.setOpaque(true);
        lblCancelledIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblCancelledIcon);
        lblCancelledIcon.setBounds(527, 260, 35, 35);

        lblCancelledVal.setText("1");
        lblCancelledVal.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblCancelledVal.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblCancelledVal);
        lblCancelledVal.setBounds(577, 260, 40, 35);

        lblCancelledLabel.setText("CANCELLED TRIPS");
        lblCancelledLabel.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblCancelledLabel.setForeground(new java.awt.Color(120, 140, 160));
        getContentPane().add(lblCancelledLabel);
        lblCancelledLabel.setBounds(577, 295, 130, 15);

        lblCancelledSub.setText("View cancelled bookings");
        lblCancelledSub.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblCancelledSub.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblCancelledSub);
        lblCancelledSub.setBounds(577, 315, 130, 15);

        cardCancelled.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cardCancelled);
        cardCancelled.setBounds(507, 245, 210, 100);

        lblSpentIcon.setText("🎫");
        lblSpentIcon.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblSpentIcon.setBackground(new java.awt.Color(235, 255, 235));
        lblSpentIcon.setForeground(new java.awt.Color(16, 185, 129));
        lblSpentIcon.setOpaque(true);
        lblSpentIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblSpentIcon);
        lblSpentIcon.setBounds(754, 260, 35, 35);

        lblSpentVal.setText("NPR 12,500");
        lblSpentVal.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblSpentVal.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblSpentVal);
        lblSpentVal.setBounds(804, 260, 130, 35);

        lblSpentLabel.setText("TOTAL SPENT");
        lblSpentLabel.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblSpentLabel.setForeground(new java.awt.Color(120, 140, 160));
        getContentPane().add(lblSpentLabel);
        lblSpentLabel.setBounds(804, 295, 130, 15);

        lblSpentSub.setText("View your transactions");
        lblSpentSub.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblSpentSub.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblSpentSub);
        lblSpentSub.setBounds(804, 315, 130, 15);

        cardTotalSpent.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cardTotalSpent);
        cardTotalSpent.setBounds(734, 245, 210, 100);

        lblLoyaltyIcon.setText("✨");
        lblLoyaltyIcon.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblLoyaltyIcon.setBackground(new java.awt.Color(255, 244, 235));
        lblLoyaltyIcon.setForeground(new java.awt.Color(255, 115, 21));
        lblLoyaltyIcon.setOpaque(true);
        lblLoyaltyIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblLoyaltyIcon);
        lblLoyaltyIcon.setBounds(980, 260, 35, 35);

        lblLoyaltyVal.setText("120");
        lblLoyaltyVal.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblLoyaltyVal.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblLoyaltyVal);
        lblLoyaltyVal.setBounds(1030, 260, 60, 35);

        lblLoyaltyLabel.setText("LOYALTY POINTS");
        lblLoyaltyLabel.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblLoyaltyLabel.setForeground(new java.awt.Color(120, 140, 160));
        getContentPane().add(lblLoyaltyLabel);
        lblLoyaltyLabel.setBounds(1030, 295, 130, 15);

        lblLoyaltySub.setText("Visit reward center");
        lblLoyaltySub.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblLoyaltySub.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblLoyaltySub);
        lblLoyaltySub.setBounds(1030, 315, 130, 15);

        cardLoyalty.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cardLoyalty);
        cardLoyalty.setBounds(960, 245, 210, 100);

        lblUpcomingTitle.setText("Upcoming Bookings");
        lblUpcomingTitle.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblUpcomingTitle.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblUpcomingTitle);
        lblUpcomingTitle.setBounds(300, 380, 200, 25);

        lblViewAll.setText("View All");
        lblViewAll.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblViewAll.setForeground(new java.awt.Color(255, 115, 21));
        getContentPane().add(lblViewAll);
        lblViewAll.setBounds(750, 380, 50, 20);

        lblTicketLogo.setText("▼");
        lblTicketLogo.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblTicketLogo.setForeground(new java.awt.Color(20, 28, 35));
        lblTicketLogo.setBackground(new java.awt.Color(244, 246, 249));
        lblTicketLogo.setOpaque(true);
        lblTicketLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblTicketLogo);
        lblTicketLogo.setBounds(320, 430, 50, 50);

        lblRoute.setText("KTM → PKR");
        lblRoute.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblRoute.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblRoute);
        lblRoute.setBounds(380, 430, 150, 25);

        lblFlightInfo.setText("YS101 ● 28 APR 2026 ● 10:00AM - 11:00AM");
        lblFlightInfo.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblFlightInfo.setForeground(new java.awt.Color(113, 128, 150));
        getContentPane().add(lblFlightInfo);
        lblFlightInfo.setBounds(380, 455, 280, 20);

        lblConfirmedBadge.setText("CONFIRMED");
        lblConfirmedBadge.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        lblConfirmedBadge.setForeground(new java.awt.Color(16, 185, 129));
        lblConfirmedBadge.setBackground(new java.awt.Color(235, 255, 235));
        lblConfirmedBadge.setOpaque(true);
        lblConfirmedBadge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblConfirmedBadge);
        lblConfirmedBadge.setBounds(690, 430, 90, 22);

        pnlTicketLine.setBackground(new java.awt.Color(246, 244, 243));
        getContentPane().add(pnlTicketLine);
        pnlTicketLine.setBounds(320, 495, 460, 1);

        lblSeatIcon.setText("🎫");
        lblSeatIcon.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(lblSeatIcon);
        lblSeatIcon.setBounds(320, 515, 20, 20);

        lblSeatLabel.setText("SEAT");
        lblSeatLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblSeatLabel.setForeground(new java.awt.Color(120, 140, 160));
        getContentPane().add(lblSeatLabel);
        lblSeatLabel.setBounds(350, 510, 50, 15);

        lblSeatVal.setText("A2");
        lblSeatVal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblSeatVal.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblSeatVal);
        lblSeatVal.setBounds(350, 525, 50, 20);

        lblPassengerIcon.setText("👤");
        lblPassengerIcon.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(lblPassengerIcon);
        lblPassengerIcon.setBounds(450, 515, 20, 20);

        lblPassengerLabel.setText("PASSENGER");
        lblPassengerLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblPassengerLabel.setForeground(new java.awt.Color(120, 140, 160));
        getContentPane().add(lblPassengerLabel);
        lblPassengerLabel.setBounds(480, 510, 80, 15);

        lblPassengerVal.setText("User Name");
        lblPassengerVal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblPassengerVal.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblPassengerVal);
        lblPassengerVal.setBounds(480, 525, 100, 20);

        lblAmountIcon.setText("💵");
        lblAmountIcon.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        getContentPane().add(lblAmountIcon);
        lblAmountIcon.setBounds(610, 515, 20, 20);

        lblAmountLabel.setText("AMOUNT");
        lblAmountLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblAmountLabel.setForeground(new java.awt.Color(120, 140, 160));
        getContentPane().add(lblAmountLabel);
        lblAmountLabel.setBounds(640, 510, 80, 15);

        lblAmountVal.setText("NPR 5,000");
        lblAmountVal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblAmountVal.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblAmountVal);
        lblAmountVal.setBounds(640, 525, 100, 20);

        pnlTicketBox.setBackground(new java.awt.Color(248, 250, 252));
        getContentPane().add(pnlTicketBox);
        pnlTicketBox.setBounds(300, 410, 500, 240);

        lblNoMoreBookings.setText("No more upcoming bookings");
        lblNoMoreBookings.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblNoMoreBookings.setForeground(new java.awt.Color(113, 128, 150));
        lblNoMoreBookings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblNoMoreBookings);
        lblNoMoreBookings.setBounds(300, 670, 500, 20);

        cardUpcomingBookings.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cardUpcomingBookings);
        cardUpcomingBookings.setBounds(280, 365, 540, 355);

        lblQuickActionsTitle.setText("Quick Actions");
        lblQuickActionsTitle.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        lblQuickActionsTitle.setForeground(new java.awt.Color(20, 28, 35));
        getContentPane().add(lblQuickActionsTitle);
        lblQuickActionsTitle.setBounds(860, 380, 200, 25);

        btnActionSearch.setText("🔍   Search Flights                                                        〉");
        btnActionSearch.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnActionSearch.setForeground(new java.awt.Color(20, 28, 35));
        btnActionSearch.addActionListener(this::btnActionSearchActionPerformed);
        getContentPane().add(btnActionSearch);
        btnActionSearch.setBounds(860, 420, 290, 45);

        btnActionBookings.setText("💼   My Bookings                                                           〉");
        btnActionBookings.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnActionBookings.setForeground(new java.awt.Color(20, 28, 35));
        btnActionBookings.addActionListener(this::btnActionBookingsActionPerformed);
        getContentPane().add(btnActionBookings);
        btnActionBookings.setBounds(860, 475, 290, 45);

        btnActionProfile.setText("⚙️   Profile Settings                                                       〉");
        btnActionProfile.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnActionProfile.setForeground(new java.awt.Color(20, 28, 35));
        btnActionProfile.addActionListener(this::btnActionProfileActionPerformed);
        getContentPane().add(btnActionProfile);
        btnActionProfile.setBounds(860, 530, 290, 45);

        btnActionLogout.setText("🚪   Logout                                                                   〉");
        btnActionLogout.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnActionLogout.setForeground(new java.awt.Color(255, 59, 48));
        btnActionLogout.addActionListener(this::btnActionLogoutActionPerformed);
        getContentPane().add(btnActionLogout);
        btnActionLogout.setBounds(860, 585, 290, 45);

        cardQuickActions.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(cardQuickActions);
        cardQuickActions.setBounds(840, 365, 330, 355);

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

    private void btnSearchFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchFlightsActionPerformed
        NavigationController.goToSearchFlight(this);
    }//GEN-LAST:event_btnSearchFlightsActionPerformed

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInActionPerformed
        NavigationController.goToCheckIn(this);
    }//GEN-LAST:event_btnCheckInActionPerformed

    private void btnActionSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionSearchActionPerformed
        ArrayList<Booking> list = dashController.getAllBookings();
        if (list.size() > 0) {
            NavigationController.openTicketWindow(list.get(0));
        } else {
            NavigationController.goToSearchFlight(this);
        }
    }//GEN-LAST:event_btnActionSearchActionPerformed

    private void btnActionBookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionBookingsActionPerformed
        ArrayList<Booking> list = dashController.getAllBookings();
        if (list.size() > 1) {
            NavigationController.openTicketWindow(list.get(1));
        }
    }//GEN-LAST:event_btnActionBookingsActionPerformed

    private void btnActionProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionProfileActionPerformed
        ArrayList<Booking> list = dashController.getAllBookings();
        if (list.size() > 2) {
            NavigationController.openTicketWindow(list.get(2));
        }
    }//GEN-LAST:event_btnActionProfileActionPerformed

    private void btnActionLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionLogoutActionPerformed
        NavigationController.goToMyBookings(this);
    }//GEN-LAST:event_btnActionLogoutActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActionBookings;
    private javax.swing.JButton btnActionLogout;
    private javax.swing.JButton btnActionProfile;
    private javax.swing.JButton btnActionSearch;
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JButton btnCustomerSupport;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMyBookings;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnSearchFlight;
    private javax.swing.JButton btnSearchFlights;
    private javax.swing.JPanel cardCancelled;
    private javax.swing.JPanel cardLoyalty;
    private javax.swing.JPanel cardQuickActions;
    private javax.swing.JPanel cardTotalSpent;
    private javax.swing.JPanel cardUpcoming;
    private javax.swing.JPanel cardUpcomingBookings;
    private javax.swing.JComboBox cmbFrom;
    private javax.swing.JComboBox cmbPassengers;
    private javax.swing.JComboBox cmbTo;
    private javax.swing.JLabel lblAmountIcon;
    private javax.swing.JLabel lblAmountLabel;
    private javax.swing.JLabel lblAmountVal;
    private javax.swing.JLabel lblBackToHome;
    private javax.swing.JLabel lblCancelledIcon;
    private javax.swing.JLabel lblCancelledLabel;
    private javax.swing.JLabel lblCancelledSub;
    private javax.swing.JLabel lblCancelledVal;
    private javax.swing.JLabel lblConfirmedBadge;
    private javax.swing.JLabel lblDepartDate;
    private javax.swing.JLabel lblFlightInfo;
    private javax.swing.JLabel lblFrom;
    private javax.swing.JLabel lblHeaderIcons;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLoyaltyIcon;
    private javax.swing.JLabel lblLoyaltyLabel;
    private javax.swing.JLabel lblLoyaltySub;
    private javax.swing.JLabel lblLoyaltyVal;
    private javax.swing.JLabel lblNoMoreBookings;
    private javax.swing.JLabel lblPassengerIcon;
    private javax.swing.JLabel lblPassengerLabel;
    private javax.swing.JLabel lblPassengerVal;
    private javax.swing.JLabel lblPassengers;
    private javax.swing.JLabel lblQuickActionsTitle;
    private javax.swing.JLabel lblRoute;
    private javax.swing.JLabel lblSeatIcon;
    private javax.swing.JLabel lblSeatLabel;
    private javax.swing.JLabel lblSeatVal;
    private javax.swing.JLabel lblSpentIcon;
    private javax.swing.JLabel lblSpentLabel;
    private javax.swing.JLabel lblSpentSub;
    private javax.swing.JLabel lblSpentVal;
    private javax.swing.JLabel lblStatusText;
    private javax.swing.JLabel lblTicketLogo;
    private javax.swing.JLabel lblTo;
    private javax.swing.JLabel lblTopTitle;
    private javax.swing.JLabel lblUpcomingIcon;
    private javax.swing.JLabel lblUpcomingLabel;
    private javax.swing.JLabel lblUpcomingSub;
    private javax.swing.JLabel lblUpcomingTitle;
    private javax.swing.JLabel lblUpcomingVal;
    private javax.swing.JLabel lblViewAll;
    private javax.swing.JLabel lblWelcomeSub;
    private javax.swing.JLabel lblWelcomeUser;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlQuickSearch;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlTicketBox;
    private javax.swing.JPanel pnlTicketLine;
    private javax.swing.JPanel pnlTopHeader;
    private com.toedter.calendar.JDateChooser txtDepartDate;
    // End of variables declaration//GEN-END:variables
}
