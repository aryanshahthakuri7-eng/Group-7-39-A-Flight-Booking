package view;

import model.Booking;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class Dashboard extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Dashboard.class.getName());

    // Dynamic list to store current bookings
    private final List<Booking> bookingsList = new ArrayList<>();

    // String arrays to populate dropdown selections
    private final String[] regions = {
        "Select Region",
        "KTM - Kathmandu",
        "PKR - Pokhara",
        "BHR - Bhairahawa",
        "BIR - Biratnagar"
    };

    private final String[] destinations = {
        "Select Destination",
        "PKR - Pokhara",
        "KTM - Kathmandu",
        "BHR - Bhairahawa",
        "BIR - Biratnagar"
    };

    private final String[] passengerOptions = {
        "1 Passenger",
        "2 Passengers",
        "3 Passengers",
        "4 Passengers"
    };

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        getContentPane().setPreferredSize(new java.awt.Dimension(1000, 700));
        pack();
        setLocationRelativeTo(null);
        populateComboBoxes();
        populateDefaultBookings();
        setupCustomListeners();
        renderBookings();
        updateStats();
    }

    private void populateComboBoxes() {
        jComboBoxFrom.setModel(new DefaultComboBoxModel<>(regions));
        jComboBoxTo.setModel(new DefaultComboBoxModel<>(destinations));
        jComboBoxPassengers.setModel(new DefaultComboBoxModel<>(passengerOptions));
    }

    private void populateDefaultBookings() {
        // Populate one default confirmed booking from array list on load
        bookingsList.add(new Booking(
            "KTM",
            "PKR",
            "YS101",
            "28 APR 2026",
            "10:00AM - 11:00AM",
            "CONFIRMED",
            "A2",
            "User Name",
            5000.0
        ));
    }

    private void setupCustomListeners() {
        // Sidebar navigation hover and click listeners
        setHandCursorAndHover(jLabelNavSearch);
        setHandCursorAndHover(jLabelNavBookings);
        setHandCursorAndHover(jLabelNavCheckIn);
        setHandCursorAndHover(jLabelNavProfile);
        setHandCursorAndHover(jLabelNavSupport);
        setHandCursorAndHover(jLabelNavLogout);
        setHandCursorAndHover(jLabelViewAll);

        // Quick Actions navigation
        setHandCursorAndHover(jLabelActionSearch);
        setHandCursorAndHover(jLabelActionBookings);
        setHandCursorAndHover(jLabelActionProfile);
        setHandCursorAndHover(jLabelActionLogout);

        // Sidebar Logout Action
        jLabelNavLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                performLogout();
            }
        });

        // Quick Actions Logout Action
        jLabelActionLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                performLogout();
            }
        });

        // Other Nav clicks
        jLabelNavBookings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(Dashboard.this, "Displaying all your bookings. Currently you have: " + bookingsList.size() + " active trip(s).", "My Bookings", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jLabelActionBookings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(Dashboard.this, "Displaying all your bookings. Currently you have: " + bookingsList.size() + " active trip(s).", "My Bookings", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        jLabelNavProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(Dashboard.this, "Navigating to Profile Settings.", "Profile", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jLabelActionProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(Dashboard.this, "Navigating to Profile Settings.", "Profile", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        jLabelNavSupport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(Dashboard.this, "Opening customer support chat channel.", "Customer Support", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Depart date focus simulation
        jTextFieldDepartDate.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (jTextFieldDepartDate.getText().equals("DD/MM/YYYY")) {
                    jTextFieldDepartDate.setText("");
                    jTextFieldDepartDate.setForeground(new java.awt.Color(51, 51, 51));
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (jTextFieldDepartDate.getText().trim().isEmpty()) {
                    jTextFieldDepartDate.setText("DD/MM/YYYY");
                    jTextFieldDepartDate.setForeground(new java.awt.Color(153, 153, 153));
                }
            }
        });
    }

    private void setHandCursorAndHover(javax.swing.JLabel label) {
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // If it is logout, keep bold or regular, let's restore
                if (label == jLabelNavLogout) {
                    label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
                } else {
                    label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
                }
            }
        });
    }

    private void performLogout() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            new SignIn().setVisible(true);
            dispose();
        }
    }

    private void renderBookings() {
        jPanelBookingsList.removeAll();
        int yOffset = 0;

        if (bookingsList.isEmpty()) {
            javax.swing.JLabel noBookings = new javax.swing.JLabel("No more upcoming bookings");
            noBookings.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 12));
            noBookings.setForeground(new java.awt.Color(153, 153, 153));
            noBookings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jPanelBookingsList.add(noBookings);
            noBookings.setBounds(0, 50, 410, 30);
        } else {
            // Populate bookings dynamically using list loop
            for (Booking b : bookingsList) {
                javax.swing.JPanel card = createBookingCard(b);
                jPanelBookingsList.add(card);
                card.setBounds(0, yOffset, 410, 110);
                yOffset += 120;
            }
        }
        jPanelBookingsList.revalidate();
        jPanelBookingsList.repaint();
    }

    private javax.swing.JPanel createBookingCard(Booking b) {
        javax.swing.JPanel card = new javax.swing.JPanel();
        card.setBackground(new java.awt.Color(255, 255, 255));
        card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 240)));
        card.setLayout(null);

        // Icon Label (Triangle logo placeholder)
        javax.swing.JLabel lblIcon = new javax.swing.JLabel("▼");
        lblIcon.setFont(new java.awt.Font("Segoe UI", 1, 16));
        lblIcon.setForeground(new java.awt.Color(15, 37, 55));
        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card.add(lblIcon);
        lblIcon.setBounds(10, 12, 30, 30);

        // Routing
        javax.swing.JLabel lblRoute = new javax.swing.JLabel(b.getFromCode() + " → " + b.getToCode());
        lblRoute.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblRoute.setForeground(new java.awt.Color(15, 37, 55));
        card.add(lblRoute);
        lblRoute.setBounds(50, 10, 150, 20);

        // Flight details
        javax.swing.JLabel lblDetails = new javax.swing.JLabel(b.getFlightNo() + " • " + b.getDate() + " • " + b.getTime());
        lblDetails.setFont(new java.awt.Font("Segoe UI", 0, 10));
        lblDetails.setForeground(new java.awt.Color(153, 153, 153));
        card.add(lblDetails);
        lblDetails.setBounds(50, 30, 280, 14);

        // Status Badge
        javax.swing.JLabel lblStatus = new javax.swing.JLabel(b.getStatus());
        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 8));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setOpaque(true);
        if (b.getStatus().equals("CONFIRMED")) {
            lblStatus.setBackground(new java.awt.Color(220, 245, 225));
            lblStatus.setForeground(new java.awt.Color(40, 160, 80));
        } else {
            lblStatus.setBackground(new java.awt.Color(255, 225, 225));
            lblStatus.setForeground(new java.awt.Color(220, 50, 50));
        }
        card.add(lblStatus);
        lblStatus.setBounds(310, 12, 85, 18);

        // Seat
        javax.swing.JLabel lblSeat = new javax.swing.JLabel("💺 SEAT: " + b.getSeat());
        lblSeat.setFont(new java.awt.Font("Segoe UI", 0, 10));
        lblSeat.setForeground(new java.awt.Color(102, 102, 102));
        card.add(lblSeat);
        lblSeat.setBounds(15, 75, 100, 15);

        // Passenger
        javax.swing.JLabel lblPassenger = new javax.swing.JLabel("👤 PASSENGER: " + b.getPassengerName());
        lblPassenger.setFont(new java.awt.Font("Segoe UI", 0, 10));
        lblPassenger.setForeground(new java.awt.Color(102, 102, 102));
        card.add(lblPassenger);
        lblPassenger.setBounds(120, 75, 150, 15);

        // Amount
        javax.swing.JLabel lblAmount = new javax.swing.JLabel("💵 AMOUNT: NPR " + String.format("%,.0f", b.getAmount()));
        lblAmount.setFont(new java.awt.Font("Segoe UI", 1, 10));
        lblAmount.setForeground(new java.awt.Color(15, 37, 55));
        card.add(lblAmount);
        lblAmount.setBounds(280, 75, 125, 15);

        return card;
    }

    private void updateStats() {
        int upcoming = bookingsList.size();
        int cancelled = 1; // Simulated statistic
        double totalSpent = 12500 + (upcoming - 1) * 5000;
        int loyaltyPoints = 120 + (upcoming - 1) * 20;

        jLabelStatUpcomingVal.setText(String.valueOf(upcoming));
        jLabelStatCancelledVal.setText(String.valueOf(cancelled));
        jLabelStatSpentVal.setText("NPR " + String.format("%,.0f", totalSpent));
        jLabelStatLoyaltyVal.setText(String.valueOf(loyaltyPoints));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        jPanelSidebar = new javax.swing.JPanel();
        jLabelNavIcon = new javax.swing.JLabel();
        jLabelNavTitle = new javax.swing.JLabel();
        jPanelNavDashboard = new javax.swing.JPanel();
        jLabelNavDashboardText = new javax.swing.JLabel();
        jLabelNavSearch = new javax.swing.JLabel();
        jLabelNavBookings = new javax.swing.JLabel();
        jLabelNavCheckIn = new javax.swing.JLabel();
        jLabelNavProfile = new javax.swing.JLabel();
        jLabelNavSupport = new javax.swing.JLabel();
        jLabelNavLogout = new javax.swing.JLabel();
        jLabelStatusDot = new javax.swing.JLabel();
        jPanelContent = new javax.swing.JPanel();
        jLabelGreeting = new javax.swing.JLabel();
        jLabelSubGreeting = new javax.swing.JLabel();
        jPanelSearch = new javax.swing.JPanel();
        jLabelFrom = new javax.swing.JLabel();
        jComboBoxFrom = new javax.swing.JComboBox<>();
        jLabelTo = new javax.swing.JLabel();
        jComboBoxTo = new javax.swing.JComboBox<>();
        jLabelDepartDate = new javax.swing.JLabel();
        jTextFieldDepartDate = new javax.swing.JTextField();
        jLabelPassengers = new javax.swing.JLabel();
        jComboBoxPassengers = new javax.swing.JComboBox<>();
        jButtonSearch = new javax.swing.JButton();
        jPanelStatUpcoming = new javax.swing.JPanel();
        jLabelStatUpcomingIcon = new javax.swing.JLabel();
        jLabelStatUpcomingVal = new javax.swing.JLabel();
        jLabelStatUpcomingTitle = new javax.swing.JLabel();
        jLabelStatUpcomingDesc = new javax.swing.JLabel();
        jPanelStatCancelled = new javax.swing.JPanel();
        jLabelStatCancelledIcon = new javax.swing.JLabel();
        jLabelStatCancelledVal = new javax.swing.JLabel();
        jLabelStatCancelledTitle = new javax.swing.JLabel();
        jLabelStatCancelledDesc = new javax.swing.JLabel();
        jPanelStatSpent = new javax.swing.JPanel();
        jLabelStatSpentIcon = new javax.swing.JLabel();
        jLabelStatSpentVal = new javax.swing.JLabel();
        jLabelStatSpentTitle = new javax.swing.JLabel();
        jLabelStatSpentDesc = new javax.swing.JLabel();
        jPanelStatLoyalty = new javax.swing.JPanel();
        jLabelStatLoyaltyIcon = new javax.swing.JLabel();
        jLabelStatLoyaltyVal = new javax.swing.JLabel();
        jLabelStatLoyaltyTitle = new javax.swing.JLabel();
        jLabelStatLoyaltyDesc = new javax.swing.JLabel();
        jPanelUpcomingBookings = new javax.swing.JPanel();
        jLabelBookingsHeader = new javax.swing.JLabel();
        jLabelViewAll = new javax.swing.JLabel();
        jPanelBookingsList = new javax.swing.JPanel();
        jPanelQuickActions = new javax.swing.JPanel();
        jLabelActionsHeader = new javax.swing.JLabel();
        jLabelActionSearch = new javax.swing.JLabel();
        jLabelActionBookings = new javax.swing.JLabel();
        jLabelActionProfile = new javax.swing.JLabel();
        jLabelActionLogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("YATRA AIR SEWA - Dashboard");
        getContentPane().setLayout(null);

        // Sidebar Pane
        jPanelSidebar.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSidebar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(217, 223, 230)));
        jPanelSidebar.setLayout(null);

        jLabelNavIcon.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabelNavIcon.setForeground(new java.awt.Color(15, 37, 55));
        jLabelNavIcon.setText("▼");
        jPanelSidebar.add(jLabelNavIcon);
        jLabelNavIcon.setBounds(20, 30, 20, 30);

        jLabelNavTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabelNavTitle.setForeground(new java.awt.Color(15, 37, 55));
        jLabelNavTitle.setText("YATRAAIR");
        jPanelSidebar.add(jLabelNavTitle);
        jLabelNavTitle.setBounds(45, 30, 150, 30);

        // Dashboard Navigation Item (Active Panel)
        jPanelNavDashboard.setBackground(new java.awt.Color(15, 37, 55));
        jPanelNavDashboard.setLayout(null);

        jLabelNavDashboardText.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelNavDashboardText.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNavDashboardText.setText("  ⊞   Dashboard");
        jPanelNavDashboard.add(jLabelNavDashboardText);
        jLabelNavDashboardText.setBounds(10, 5, 170, 26);

        jPanelSidebar.add(jPanelNavDashboard);
        jPanelNavDashboard.setBounds(15, 90, 190, 36);

        jLabelNavSearch.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelNavSearch.setForeground(new java.awt.Color(51, 51, 51));
        jLabelNavSearch.setText("  🔍   Search Flight");
        jPanelSidebar.add(jLabelNavSearch);
        jLabelNavSearch.setBounds(25, 140, 170, 30);

        jLabelNavBookings.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelNavBookings.setForeground(new java.awt.Color(51, 51, 51));
        jLabelNavBookings.setText("  📋   My Bookings");
        jPanelSidebar.add(jLabelNavBookings);
        jLabelNavBookings.setBounds(25, 180, 170, 30);

        jLabelNavCheckIn.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelNavCheckIn.setForeground(new java.awt.Color(51, 51, 51));
        jLabelNavCheckIn.setText("  🎫   Check-in");
        jPanelSidebar.add(jLabelNavCheckIn);
        jLabelNavCheckIn.setBounds(25, 220, 170, 30);

        jLabelNavProfile.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelNavProfile.setForeground(new java.awt.Color(51, 51, 51));
        jLabelNavProfile.setText("  👤   Profile");
        jPanelSidebar.add(jLabelNavProfile);
        jLabelNavProfile.setBounds(25, 260, 170, 30);

        jLabelNavSupport.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelNavSupport.setForeground(new java.awt.Color(51, 51, 51));
        jLabelNavSupport.setText("  💬   Customer Support");
        jPanelSidebar.add(jLabelNavSupport);
        jLabelNavSupport.setBounds(25, 300, 170, 30);

        jLabelNavLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelNavLogout.setForeground(new java.awt.Color(204, 51, 51));
        jLabelNavLogout.setText("  ↳   Logout");
        jPanelSidebar.add(jLabelNavLogout);
        jLabelNavLogout.setBounds(25, 610, 170, 30);

        jLabelStatusDot.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabelStatusDot.setForeground(new java.awt.Color(51, 179, 51));
        jLabelStatusDot.setText("  ●   SYSTEM STATUS: OPERATIONAL");
        jPanelSidebar.add(jLabelStatusDot);
        jLabelStatusDot.setBounds(15, 650, 190, 20);

        getContentPane().add(jPanelSidebar);
        jPanelSidebar.setBounds(0, 0, 220, 700);

        // Content Area Panel
        jPanelContent.setBackground(new java.awt.Color(245, 247, 250));
        jPanelContent.setLayout(null);

        jLabelGreeting.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabelGreeting.setForeground(new java.awt.Color(15, 37, 55));
        jLabelGreeting.setText("Welcome User,");
        jPanelContent.add(jLabelGreeting);
        jLabelGreeting.setBounds(30, 30, 400, 27);

        jLabelSubGreeting.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelSubGreeting.setForeground(new java.awt.Color(128, 128, 128));
        jLabelSubGreeting.setText("Review your flight schedules and upcoming travels.");
        jPanelContent.add(jLabelSubGreeting);
        jLabelSubGreeting.setBounds(30, 55, 500, 16);

        // Flight Search Panel
        jPanelSearch.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 229, 236)));
        jPanelSearch.setLayout(null);

        jLabelFrom.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelFrom.setForeground(new java.awt.Color(153, 153, 153));
        jLabelFrom.setText("FROM");
        jPanelSearch.add(jLabelFrom);
        jLabelFrom.setBounds(15, 10, 80, 13);
        jPanelSearch.add(jComboBoxFrom);
        jComboBoxFrom.setBounds(15, 30, 160, 30);

        jLabelTo.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelTo.setForeground(new java.awt.Color(153, 153, 153));
        jLabelTo.setText("TO");
        jPanelSearch.add(jLabelTo);
        jLabelTo.setBounds(190, 10, 80, 13);
        jPanelSearch.add(jComboBoxTo);
        jComboBoxTo.setBounds(190, 30, 160, 30);

        jLabelDepartDate.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelDepartDate.setForeground(new java.awt.Color(153, 153, 153));
        jLabelDepartDate.setText("DEPART DATE");
        jPanelSearch.add(jLabelDepartDate);
        jLabelDepartDate.setBounds(365, 10, 100, 13);

        jTextFieldDepartDate.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldDepartDate.setText("DD/MM/YYYY");
        jPanelSearch.add(jTextFieldDepartDate);
        jTextFieldDepartDate.setBounds(365, 30, 110, 30);

        jLabelPassengers.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelPassengers.setForeground(new java.awt.Color(153, 153, 153));
        jLabelPassengers.setText("PASSENGERS");
        jPanelSearch.add(jLabelPassengers);
        jLabelPassengers.setBounds(490, 10, 100, 13);
        jPanelSearch.add(jComboBoxPassengers);
        jComboBoxPassengers.setBounds(490, 30, 105, 30);

        jButtonSearch.setBackground(new java.awt.Color(245, 130, 32));
        jButtonSearch.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jButtonSearch.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSearch.setText("Search Flights");
        jButtonSearch.setBorderPainted(false);
        jButtonSearch.addActionListener(this::jButtonSearchActionPerformed);
        jPanelSearch.add(jButtonSearch);
        jButtonSearch.setBounds(605, 26, 105, 36);

        jPanelContent.add(jPanelSearch);
        jPanelSearch.setBounds(30, 90, 720, 80);

        // Upcoming trips Stat Card
        jPanelStatUpcoming.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStatUpcoming.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 229, 236)));
        jPanelStatUpcoming.setLayout(null);

        jLabelStatUpcomingIcon.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabelStatUpcomingIcon.setForeground(new java.awt.Color(0, 102, 204));
        jLabelStatUpcomingIcon.setText("  ▲");
        jPanelStatUpcoming.add(jLabelStatUpcomingIcon);
        jLabelStatUpcomingIcon.setBounds(10, 20, 30, 30);

        jLabelStatUpcomingVal.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelStatUpcomingVal.setForeground(new java.awt.Color(51, 51, 51));
        jLabelStatUpcomingVal.setText("2");
        jPanelStatUpcoming.add(jLabelStatUpcomingVal);
        jLabelStatUpcomingVal.setBounds(50, 10, 80, 30);

        jLabelStatUpcomingTitle.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelStatUpcomingTitle.setForeground(new java.awt.Color(153, 153, 153));
        jLabelStatUpcomingTitle.setText("UPCOMING TRIPS");
        jPanelStatUpcoming.add(jLabelStatUpcomingTitle);
        jLabelStatUpcomingTitle.setBounds(50, 42, 105, 13);

        jLabelStatUpcomingDesc.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabelStatUpcomingDesc.setForeground(new java.awt.Color(153, 153, 153));
        jLabelStatUpcomingDesc.setText("View your next booking");
        jPanelStatUpcoming.add(jLabelStatUpcomingDesc);
        jLabelStatUpcomingDesc.setBounds(50, 60, 105, 11);

        jPanelContent.add(jPanelStatUpcoming);
        jPanelStatUpcoming.setBounds(30, 185, 165, 100);

        // Cancelled trips Stat Card
        jPanelStatCancelled.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStatCancelled.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 229, 236)));
        jPanelStatCancelled.setLayout(null);

        jLabelStatCancelledIcon.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabelStatCancelledIcon.setForeground(new java.awt.Color(204, 51, 51));
        jLabelStatCancelledIcon.setText("  ⟲");
        jPanelStatCancelled.add(jLabelStatCancelledIcon);
        jLabelStatCancelledIcon.setBounds(10, 20, 30, 30);

        jLabelStatCancelledVal.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelStatCancelledVal.setForeground(new java.awt.Color(51, 51, 51));
        jLabelStatCancelledVal.setText("1");
        jPanelStatCancelled.add(jLabelStatCancelledVal);
        jLabelStatCancelledVal.setBounds(50, 10, 80, 30);

        jLabelStatCancelledTitle.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelStatCancelledTitle.setForeground(new java.awt.Color(153, 153, 153));
        jLabelStatCancelledTitle.setText("CANCELLED TRIPS");
        jPanelStatCancelled.add(jLabelStatCancelledTitle);
        jLabelStatCancelledTitle.setBounds(50, 42, 105, 13);

        jLabelStatCancelledDesc.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabelStatCancelledDesc.setForeground(new java.awt.Color(153, 153, 153));
        jLabelStatCancelledDesc.setText("View cancelled bookings");
        jPanelStatCancelled.add(jLabelStatCancelledDesc);
        jLabelStatCancelledDesc.setBounds(50, 60, 105, 11);

        jPanelContent.add(jPanelStatCancelled);
        jPanelStatCancelled.setBounds(215, 185, 165, 100);

        // Total Spent Stat Card
        jPanelStatSpent.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStatSpent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 229, 236)));
        jPanelStatSpent.setLayout(null);

        jLabelStatSpentIcon.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabelStatSpentIcon.setForeground(new java.awt.Color(51, 179, 51));
        jLabelStatSpentIcon.setText("  💵");
        jPanelStatSpent.add(jLabelStatSpentIcon);
        jLabelStatSpentIcon.setBounds(10, 20, 30, 30);

        jLabelStatSpentVal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelStatSpentVal.setForeground(new java.awt.Color(51, 51, 51));
        jLabelStatSpentVal.setText("NPR 12,500");
        jPanelStatSpent.add(jLabelStatSpentVal);
        jLabelStatSpentVal.setBounds(50, 14, 110, 25);

        jLabelStatSpentTitle.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelStatSpentTitle.setForeground(new java.awt.Color(153, 153, 153));
        jLabelStatSpentTitle.setText("TOTAL SPENT");
        jPanelStatSpent.add(jLabelStatSpentTitle);
        jLabelStatSpentTitle.setBounds(50, 42, 105, 13);

        jLabelStatSpentDesc.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabelStatSpentDesc.setForeground(new java.awt.Color(153, 153, 153));
        jLabelStatSpentDesc.setText("View your transactions");
        jPanelStatSpent.add(jLabelStatSpentDesc);
        jLabelStatSpentDesc.setBounds(50, 60, 105, 11);

        jPanelContent.add(jPanelStatSpent);
        jPanelStatSpent.setBounds(400, 185, 165, 100);

        // Loyalty Points Stat Card
        jPanelStatLoyalty.setBackground(new java.awt.Color(255, 255, 255));
        jPanelStatLoyalty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 229, 236)));
        jPanelStatLoyalty.setLayout(null);

        jLabelStatLoyaltyIcon.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabelStatLoyaltyIcon.setForeground(new java.awt.Color(245, 130, 32));
        jLabelStatLoyaltyIcon.setText("  ★");
        jPanelStatLoyalty.add(jLabelStatLoyaltyIcon);
        jLabelStatLoyaltyIcon.setBounds(10, 20, 30, 30);

        jLabelStatLoyaltyVal.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelStatLoyaltyVal.setForeground(new java.awt.Color(51, 51, 51));
        jLabelStatLoyaltyVal.setText("120");
        jPanelStatLoyalty.add(jLabelStatLoyaltyVal);
        jLabelStatLoyaltyVal.setBounds(50, 10, 80, 30);

        jLabelStatLoyaltyTitle.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        jLabelStatLoyaltyTitle.setForeground(new java.awt.Color(153, 153, 153));
        jLabelStatLoyaltyTitle.setText("LOYALTY POINTS");
        jPanelStatLoyalty.add(jLabelStatLoyaltyTitle);
        jLabelStatLoyaltyTitle.setBounds(50, 42, 105, 13);

        jLabelStatLoyaltyDesc.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabelStatLoyaltyDesc.setForeground(new java.awt.Color(153, 153, 153));
        jLabelStatLoyaltyDesc.setText("Visit reward center");
        jPanelStatLoyalty.add(jLabelStatLoyaltyDesc);
        jLabelStatLoyaltyDesc.setBounds(50, 60, 105, 11);

        jPanelContent.add(jPanelStatLoyalty);
        jPanelStatLoyalty.setBounds(585, 185, 165, 100);

        // Upcoming Bookings Section Card
        jPanelUpcomingBookings.setBackground(new java.awt.Color(255, 255, 255));
        jPanelUpcomingBookings.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 229, 236)));
        jPanelUpcomingBookings.setLayout(null);

        jLabelBookingsHeader.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelBookingsHeader.setForeground(new java.awt.Color(15, 37, 55));
        jLabelBookingsHeader.setText("Upcoming Bookings");
        jPanelUpcomingBookings.add(jLabelBookingsHeader);
        jLabelBookingsHeader.setBounds(20, 15, 150, 18);

        jLabelViewAll.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabelViewAll.setForeground(new java.awt.Color(245, 130, 32));
        jLabelViewAll.setText("View All");
        jPanelUpcomingBookings.add(jLabelViewAll);
        jLabelViewAll.setBounds(380, 15, 50, 14);

        // Inner Booking List Panel where Booking Cards are dynamically rendered
        jPanelBookingsList.setOpaque(false);
        jPanelBookingsList.setLayout(null);
        jPanelUpcomingBookings.add(jPanelBookingsList);
        jPanelBookingsList.setBounds(20, 45, 410, 270);

        jPanelContent.add(jPanelUpcomingBookings);
        jPanelUpcomingBookings.setBounds(30, 305, 450, 340);

        // Quick Actions Section Card
        jPanelQuickActions.setBackground(new java.awt.Color(255, 255, 255));
        jPanelQuickActions.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(222, 229, 236)));
        jPanelQuickActions.setLayout(null);

        jLabelActionsHeader.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabelActionsHeader.setForeground(new java.awt.Color(15, 37, 55));
        jLabelActionsHeader.setText("Quick Actions");
        jPanelQuickActions.add(jLabelActionsHeader);
        jLabelActionsHeader.setBounds(20, 15, 150, 18);

        jLabelActionSearch.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelActionSearch.setForeground(new java.awt.Color(51, 51, 51));
        jLabelActionSearch.setText("  🔍   Search Flights                          >");
        jPanelQuickActions.add(jLabelActionSearch);
        jLabelActionSearch.setBounds(20, 60, 210, 30);

        jLabelActionBookings.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelActionBookings.setForeground(new java.awt.Color(51, 51, 51));
        jLabelActionBookings.setText("  📋   My Bookings                             >");
        jPanelQuickActions.add(jLabelActionBookings);
        jLabelActionBookings.setBounds(20, 110, 210, 30);

        jLabelActionProfile.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelActionProfile.setForeground(new java.awt.Color(51, 51, 51));
        jLabelActionProfile.setText("  ⚙   Profile Settings                            >");
        jPanelQuickActions.add(jLabelActionProfile);
        jLabelActionProfile.setBounds(20, 160, 210, 30);

        jLabelActionLogout.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabelActionLogout.setForeground(new java.awt.Color(204, 51, 51));
        jLabelActionLogout.setText("  ↳   Logout                                        >");
        jPanelQuickActions.add(jLabelActionLogout);
        jLabelActionLogout.setBounds(20, 210, 210, 30);

        jPanelContent.add(jPanelQuickActions);
        jPanelQuickActions.setBounds(500, 305, 250, 340);

        getContentPane().add(jPanelContent);
        jPanelContent.setBounds(220, 0, 780, 700);

        // Set dimensions & center
        getContentPane().setPreferredSize(new java.awt.Dimension(1000, 700));
        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {
        String fromCode = (String) jComboBoxFrom.getSelectedItem();
        String toCode = (String) jComboBoxTo.getSelectedItem();
        String departDate = jTextFieldDepartDate.getText();
        String passengers = (String) jComboBoxPassengers.getSelectedItem();

        if (fromCode.equals("Select Region") || toCode.equals("Select Destination")) {
            JOptionPane.showMessageDialog(this, "Please select both origin and destination.", "Search Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fromCode.equals(toCode)) {
            JOptionPane.showMessageDialog(this, "Origin and destination cannot be the same.", "Search Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (departDate.trim().isEmpty() || departDate.equals("DD/MM/YYYY")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid departure date.", "Search Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Dynamically add a booking card to the ArrayList!
        String fromCodeShort = fromCode.substring(0, 3);
        String toCodeShort = toCode.substring(0, 3);

        Booking newBooking = new Booking(
            fromCodeShort,
            toCodeShort,
            "YS" + (100 + bookingsList.size() + 1),
            departDate,
            "10:00AM - 11:00AM",
            "CONFIRMED",
            "A" + (bookingsList.size() + 3),
            "User Name",
            5000.0
        );

        // Add to our list structure
        bookingsList.add(newBooking);
        
        // Re-render and update UI stats cards dynamically
        renderBookings();
        updateStats();

        JOptionPane.showMessageDialog(this, "Flight booked successfully!\n" + fromCodeShort + " to " + toCodeShort + " added to your upcoming bookings.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton jButton1; // Required for compilation mapping if generated by code
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox<String> jComboBoxFrom;
    private javax.swing.JComboBox<String> jComboBoxPassengers;
    private javax.swing.JComboBox<String> jComboBoxTo;
    private javax.swing.JLabel jLabelActionBookings;
    private javax.swing.JLabel jLabelActionLogout;
    private javax.swing.JLabel jLabelActionProfile;
    private javax.swing.JLabel jLabelActionSearch;
    private javax.swing.JLabel jLabelActionsHeader;
    private javax.swing.JLabel jLabelBackHome; // Needed to map header mapping
    private javax.swing.JLabel jLabelBookingsHeader;
    private javax.swing.JLabel jLabelDepartDate;
    private javax.swing.JLabel jLabelFrom;
    private javax.swing.JLabel jLabelGreeting;
    private javax.swing.JLabel jLabelHeaderLogo; // Needed to map header mapping
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelMainTitle;
    private javax.swing.JLabel jLabelNavBookings;
    private javax.swing.JLabel jLabelNavCheckIn;
    private javax.swing.JLabel jLabelNavDashboardText;
    private javax.swing.JLabel jLabelNavIcon;
    private javax.swing.JLabel jLabelNavLogout;
    private javax.swing.JLabel jLabelNavProfile;
    private javax.swing.JLabel jLabelNavSearch;
    private javax.swing.JLabel jLabelNavSupport;
    private javax.swing.JLabel jLabelNavTitle;
    private javax.swing.JLabel jLabelOr;
    private javax.swing.JLabel jLabelPassengers;
    private javax.swing.JLabel jLabelStatCancelledDesc;
    private javax.swing.JLabel jLabelStatCancelledIcon;
    private javax.swing.JLabel jLabelStatCancelledTitle;
    private javax.swing.JLabel jLabelStatCancelledVal;
    private javax.swing.JLabel jLabelStatLoyaltyDesc;
    private javax.swing.JLabel jLabelStatLoyaltyIcon;
    private javax.swing.JLabel jLabelStatLoyaltyTitle;
    private javax.swing.JLabel jLabelStatLoyaltyVal;
    private javax.swing.JLabel jLabelStatSpentDesc;
    private javax.swing.JLabel jLabelStatSpentIcon;
    private javax.swing.JLabel jLabelStatSpentTitle;
    private javax.swing.JLabel jLabelStatSpentVal;
    private javax.swing.JLabel jLabelStatUpcomingDesc;
    private javax.swing.JLabel jLabelStatUpcomingIcon;
    private javax.swing.JLabel jLabelStatUpcomingTitle;
    private javax.swing.JLabel jLabelStatUpcomingVal;
    private javax.swing.JLabel jLabelStatusDot;
    private javax.swing.JLabel jLabelSubGreeting;
    private javax.swing.JLabel jLabelTo;
    private javax.swing.JLabel jLabelViewAll;
    private javax.swing.JPanel jPanelBookingsList;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelNavDashboard;
    private javax.swing.JPanel jPanelQuickActions;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelSidebar;
    private javax.swing.JPanel jPanelStatCancelled;
    private javax.swing.JPanel jPanelStatLoyalty;
    private javax.swing.JPanel jPanelStatSpent;
    private javax.swing.JPanel jPanelStatUpcoming;
    private javax.swing.JPanel jPanelUpcomingBookings;
    private javax.swing.JTextField jTextFieldDepartDate;
    // End of variables declaration
}
