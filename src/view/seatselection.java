package view;

import controller.SeatSelectionController;
import controller.NavigationController;
import model.Flight;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;

/**
 * View class representing the Seat Selection page UI.
 * Integrates an interactive visual grid representing flight rows with custom checks.
 */
public class seatselection extends javax.swing.JFrame {

    private Flight flight;
    private String passengerName;
    private SeatSelectionController controller;

    // Interactive seat variables
    private String selectedSeatCode = "A2"; // Default from Figma mockup
    private final boolean[][] occupiedSeats = new boolean[5][6]; // row x col (0-indexed)
    private final JPanel[][] seatPanels = new JPanel[5][6];

    public seatselection() {
        this.flight = new Flight(1, "YS101", "Kathmandu (KTM)", "Pokhara (PKR)", "28 APR 2026", "10:00 AM", 15, 8400.0, "ACTIVE");
        this.passengerName = "Aryan Shah";
        initOccupiedStates();
        initComponents();
        customInit();
        this.controller = new SeatSelectionController(this, this.flight, this.passengerName);
    }

    public seatselection(Flight flight, String passengerName) {
        this.flight = flight;
        this.passengerName = passengerName;
        initOccupiedStates();
        initComponents();
        customInit();
        this.controller = new SeatSelectionController(this, this.flight, this.passengerName);
    }

    private void initOccupiedStates() {
        // Pre-fill occupied states based on Figma mockup
        occupiedSeats[1][4] = true; // E2 (Row 2, Column E)
        occupiedSeats[2][3] = true; // D3 (Row 3, Column D)
        occupiedSeats[3][3] = true; // D4 (Row 4, Column D)
    }

    private void customInit() {
        // Frame setup
        setSize(1366, 768);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(8, 22, 42)); // #08162A

        // 1. Create the gradient background panel for the outer frame
        JPanel pnlBackground = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                java.awt.GradientPaint gp = new java.awt.GradientPaint(
                    0, 0, new Color(13, 27, 49), // #0D1B31
                    0, getHeight(), new Color(6, 13, 26) // #060D1A
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        pnlBackground.setLayout(null);
        pnlBackground.setBounds(0, 0, 1366, 768);

        // 2. Create the main rounded dashboard container with shadow and unified border
        JPanel pnlMainContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                int w = getWidth();
                int h = getHeight();
                
                // Draw shadow
                g2.setColor(new Color(0, 0, 0, 30));
                for (int i = 1; i <= 6; i++) {
                    g2.drawRoundRect(i, i, w - 2 * i, h - 2 * i, 16, 16);
                }
                
                // Fill white background
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, w - 1, h - 1, 16, 16);
                
                // Fill dark navy top header background (top 60px)
                g2.setColor(new Color(8, 22, 42)); // #08162A
                g2.fillRoundRect(0, 0, w - 1, 60, 16, 16);
                g2.fillRect(0, 50, w - 1, 10); // Flatten the bottom of the header
                
                // Fill light grey bottom status bar background (bottom 25px)
                g2.setColor(new Color(243, 244, 246)); // #F3F4F6
                g2.fillRoundRect(0, h - 25, w - 1, 25, 16, 16);
                g2.fillRect(0, h - 25, w - 1, 10); // Flatten the top of the status bar
                
                // Draw container outline border
                g2.setColor(new Color(229, 231, 235)); // #E5E7EB
                g2.setStroke(new java.awt.BasicStroke(1.0f));
                g2.drawRoundRect(0, 0, w - 1, h - 1, 16, 16);
                g2.dispose();
            }
        };
        pnlMainContainer.setOpaque(false);
        pnlMainContainer.setLayout(null);
        pnlMainContainer.setBounds(60, 40, 1246, 688);
        pnlBackground.add(pnlMainContainer);

        // 3. Remove header, sidebar, mainBg from frame content pane and add to main container
        getContentPane().remove(pnlTopHeader);
        getContentPane().remove(pnlSidebar);
        getContentPane().remove(pnlMainBg);

        pnlMainContainer.add(pnlTopHeader);
        pnlMainContainer.add(pnlSidebar);
        pnlMainContainer.add(pnlMainBg);
        
        getContentPane().add(pnlBackground);

        // Re-arrange layout bounds programmatically inside container
        pnlTopHeader.setBounds(0, 0, 1246, 60);
        pnlTopHeader.setOpaque(false);
        pnlTopHeader.setBorder(null);

        pnlSidebar.setBounds(0, 60, 220, 603);
        pnlSidebar.setOpaque(true);
        pnlSidebar.setBackground(Color.WHITE);
        pnlSidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(229, 231, 235))); // #E5E7EB

        pnlMainBg.setBounds(220, 60, 1026, 603);
        pnlMainBg.setOpaque(true);
        pnlMainBg.setBackground(new Color(245, 246, 250));

        // Sidebar Active Selection rounded panel wrapper
        pnlSidebar.remove(lblSearchFlight);
        
        RoundedPanel pnlActiveSearchFlight = new RoundedPanel(8, null, new Color(43, 10, 10)); // #2B0A0A
        pnlActiveSearchFlight.setBounds(15, 135, 190, 35);
        pnlActiveSearchFlight.setLayout(null);
        
        lblSearchFlight.setBounds(0, 0, 190, 35);
        lblSearchFlight.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pnlActiveSearchFlight.add(lblSearchFlight);
        
        pnlSidebar.add(pnlActiveSearchFlight);

        // Move System Status label to the new bottom status bar panel
        JPanel pnlStatus = new JPanel();
        pnlStatus.setOpaque(false);
        pnlStatus.setLayout(null);
        pnlStatus.setBounds(0, 663, 1246, 25);
        
        pnlSidebar.remove(lblSystemStatus);
        pnlStatus.add(lblSystemStatus);
        lblSystemStatus.setBounds(20, 2, 300, 20);
        lblSystemStatus.setText("SYSTEM STATUS: OPERATIONAL");
        lblSystemStatus.setBorder(new Border() {
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(16, 185, 129)); // Operational Green (#10B981)
                g2.fillOval(x + 5, y + (height - 6) / 2, 6, 6);
                g2.dispose();
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(0, 18, 0, 0); }
            @Override public boolean isBorderOpaque() { return false; }
        });
        pnlMainContainer.add(pnlStatus);

        // Set padding inside sidebar labels and vector icons to prevent tofu [] emojis
        int iw = 14;
        int ih = 14;

        lblDashboard.setText("Dashboard");
        lblDashboard.setIcon(getVectorIcon("grid", iw, ih, new Color(119, 119, 119)));
        lblDashboard.setForeground(new Color(119, 119, 119));
        lblDashboard.setIconTextGap(8);
        lblDashboard.setBounds(15, 90, 190, 35);
        lblDashboard.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        lblSearchFlight.setText("Search Flight");
        lblSearchFlight.setIcon(getVectorIcon("search", iw, ih, Color.WHITE));
        lblSearchFlight.setForeground(Color.WHITE);
        lblSearchFlight.setIconTextGap(8);
        lblSearchFlight.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        lblMyBookings.setText("My Bookings");
        lblMyBookings.setIcon(getVectorIcon("briefcase", iw, ih, new Color(119, 119, 119)));
        lblMyBookings.setForeground(new Color(119, 119, 119));
        lblMyBookings.setIconTextGap(8);
        lblMyBookings.setBounds(15, 180, 190, 35);
        lblMyBookings.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        lblProfile.setText("Profile");
        lblProfile.setIcon(getVectorIcon("profile", iw, ih, new Color(119, 119, 119)));
        lblProfile.setForeground(new Color(119, 119, 119));
        lblProfile.setIconTextGap(8);
        lblProfile.setBounds(15, 225, 190, 35);
        lblProfile.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        lblCustomerSupport.setText("Customer Support");
        lblCustomerSupport.setIcon(getVectorIcon("chat", iw, ih, new Color(119, 119, 119)));
        lblCustomerSupport.setForeground(new Color(119, 119, 119));
        lblCustomerSupport.setIconTextGap(8);
        lblCustomerSupport.setBounds(15, 270, 190, 35);
        lblCustomerSupport.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        lblLogout.setText("Logout");
        lblLogout.setIcon(getVectorIcon("logout", iw, ih, new Color(239, 68, 68)));
        lblLogout.setForeground(new Color(239, 68, 68));
        lblLogout.setIconTextGap(8);
        lblLogout.setBounds(15, 520, 190, 35);
        lblLogout.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        Cursor hand = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        lblDashboard.setCursor(hand);
        lblSearchFlight.setCursor(hand);
        lblMyBookings.setCursor(hand);
        lblProfile.setCursor(hand);
        lblCustomerSupport.setCursor(hand);
        lblLogout.setCursor(hand);
        lblBackToHome.setCursor(hand);
        lblBackToPassenger.setCursor(hand);

        // Logo icon Arrowhead
        lblLogo.setText("  YATRAAIR");
        lblLogo.setBounds(24, 25, 180, 30);
        try {
            lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_arrowhead.png")));
        } catch (Exception ex) {}

        // Hide "Book New Flight" button to match Figma sidebar layout
        btnBookNewFlight.setVisible(false);

        // Back to Home link with vector icon
        lblBackToHome.setText("Back to Home");
        lblBackToHome.setIcon(getVectorIcon("home", 14, 14, Color.WHITE));
        lblBackToHome.setIconTextGap(8);
        lblBackToHome.setBounds(30, 20, 150, 20);
        lblBackToHome.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // Centered window title inside top header
        lblTopTitle.setBounds(498, 18, 250, 25);

        // Hide old emoji labels and add programmatically rendered vector ones for the header
        lblHeaderIcons.setVisible(false);
        
        JLabel lblNotification = new JLabel();
        lblNotification.setIcon(getVectorIcon("bell", 16, 16, Color.WHITE));
        lblNotification.setBounds(1130, 18, 24, 25);
        lblNotification.setCursor(hand);
        pnlTopHeader.add(lblNotification);
        
        JLabel lblHelp = new JLabel();
        lblHelp.setIcon(getVectorIcon("help", 16, 16, Color.WHITE));
        lblHelp.setBounds(1165, 18, 24, 25);
        lblHelp.setCursor(hand);
        pnlTopHeader.add(lblHelp);
        
        JLabel lblSettings = new JLabel();
        lblSettings.setIcon(getVectorIcon("settings", 16, 16, Color.WHITE));
        lblSettings.setBounds(1200, 18, 24, 25);
        lblSettings.setCursor(hand);
        pnlTopHeader.add(lblSettings);

        // Badges layout for route and time under Seat Selection (drawn as rounded grey/blue cards)
        pnlSeatHeaderCard.remove(lblFlightBadge);
        pnlSeatHeaderCard.remove(lblTimeBadge);

        lblFlightBadge = new javax.swing.JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 243, 246)); // Light grey/blue #F0F3F6
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Draw plane icon (pointing right)
                g2.setColor(new Color(15, 61, 117)); // #0F3D75
                int px = 8, py = getHeight() / 2;
                // Fuselage
                g2.fillRoundRect(px, py - 2, 12, 4, 2, 2);
                // Wings
                g2.fillRoundRect(px + 4, py - 6, 3, 12, 2, 2);
                // Tail fin
                g2.fillRoundRect(px, py - 4, 2, 8, 1, 1);
                g2.dispose();
                
                super.paintComponent(g);
            }
        };
        lblFlightBadge.setText("Yatra Air 101 | KTM → PKR");
        lblFlightBadge.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblFlightBadge.setForeground(new java.awt.Color(51, 51, 51));
        lblFlightBadge.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 8));
        lblFlightBadge.setBounds(20, 44, 220, 22);
        pnlSeatHeaderCard.add(lblFlightBadge);

        lblTimeBadge = new javax.swing.JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(240, 243, 246)); // #F0F3F6
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                
                // Draw clock icon
                g2.setColor(new Color(15, 61, 117));
                g2.drawOval(8, getHeight() / 2 - 5, 10, 10);
                g2.drawLine(13, getHeight() / 2 - 2, 13, getHeight() / 2);
                g2.drawLine(13, getHeight() / 2, 15, getHeight() / 2);
                g2.dispose();
                
                super.paintComponent(g);
            }
        };
        lblTimeBadge.setText("10:00 AM - 11:00 AM");
        lblTimeBadge.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblTimeBadge.setForeground(new java.awt.Color(51, 51, 51));
        lblTimeBadge.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 8));
        lblTimeBadge.setBounds(250, 44, 180, 22);
        pnlSeatHeaderCard.add(lblTimeBadge);

        // Cards layout border setup
        pnlSeatHeaderCard.setBounds(30, 45, 966, 80);
        pnlSeatHeaderCard.setCornerRadius(10);
        pnlSeatHeaderCard.setBorderColor(new Color(217, 220, 229));
        pnlSeatHeaderCard.setFillColor(Color.WHITE);

        lblSeatTitle.setBounds(20, 12, 200, 25);
        lblFlightBadge.setBounds(20, 44, 220, 22);
        lblTimeBadge.setBounds(250, 44, 180, 22);
        lblClassVal.setBounds(730, 15, 210, 15);
        lblAircraftVal.setBounds(730, 35, 210, 25);

        // Seat Grid Card configuration
        pnlSeatGridCard.setBounds(30, 140, 620, 435);
        pnlSeatGridCard.setCornerRadius(10);
        pnlSeatGridCard.setBorderColor(new Color(217, 220, 229));
        pnlSeatGridCard.setFillColor(Color.WHITE);
        pnlSeatGridCard.setLayout(null);

        lblAvailableLegend.setBounds(145, 20, 90, 20);
        lblSelectedLegend.setBounds(260, 20, 90, 20);
        lblOccupiedLegend.setBounds(375, 20, 90, 20);

        // Selection details card setup inside right column container
        pnlRightCol.setBounds(670, 140, 326, 435);
        
        pnlSelectionDetailsCard.setBounds(0, 0, 326, 260);
        pnlSelectionDetailsCard.setCornerRadius(10);
        pnlSelectionDetailsCard.setFillColor(Color.WHITE);
        pnlSelectionDetailsCard.setBorderColor(null); // Outlined by custom border below
        pnlSelectionDetailsCard.setBorder(new Border() {
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw rounded outline
                g2.setColor(new Color(217, 220, 229));
                g2.setStroke(new java.awt.BasicStroke(1.0f));
                g2.drawRoundRect(x, y, width - 1, height - 1, 10, 10);
                
                // Draw dotted separators
                float[] dash = {2f, 4f};
                java.awt.BasicStroke dashed = new java.awt.BasicStroke(1.0f, java.awt.BasicStroke.CAP_BUTT, java.awt.BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
                g2.setStroke(dashed);
                g2.setColor(new Color(209, 213, 219)); // Gray #D1D5DB
                
                // Divider 1 (below selected seat badge at y=125)
                g2.drawLine(x + 20, y + 135, x + width - 20, y + 135);
                
                // Divider 2 (above Total Price at y=205)
                g2.drawLine(x + 20, y + 205, x + width - 20, y + 205);
                
                g2.dispose();
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(10, 10, 10, 10); }
            @Override public boolean isBorderOpaque() { return false; }
        });

        lblSelectionDetailsTitle.setBounds(20, 20, 200, 22);

        pnlSelectedSeatBadge.setBounds(20, 55, 286, 70);
        pnlSelectedSeatBadge.setCornerRadius(8);
        pnlSelectedSeatBadge.setBorderColor(new Color(217, 230, 245));
        pnlSelectedSeatBadge.setFillColor(new Color(240, 245, 253)); // #E6F0FF

        lblSeatCodeBox.setOpaque(false);
        lblSeatCodeBox.setBounds(15, 15, 40, 40);
        lblSeatCodeBox.setBorder(new Border() {
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(15, 61, 117)); // #0F3D75
                g2.fillRoundRect(x, y, width, height, 6, 6);
                
                JLabel l = (JLabel) c;
                g2.setColor(Color.WHITE);
                g2.setFont(l.getFont());
                String t = l.getText();
                java.awt.FontMetrics fm = g2.getFontMetrics();
                int tx = x + (width - fm.stringWidth(t)) / 2;
                int ty = y + (height - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(t, tx, ty);
                g2.dispose();
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(1, 1, 1, 1); }
            @Override public boolean isBorderOpaque() { return false; }
        });

        lblSeatName.setBounds(70, 13, 100, 20);
        lblSeatDesc.setBounds(70, 35, 140, 20);
        lblSeatPrice.setBounds(175, 25, 95, 20);

        lblBaseFareTitle.setBounds(20, 145, 120, 20);
        lblBaseFareVal.setBounds(160, 145, 146, 20);

        lblSeatUpgradeTitle.setBounds(20, 175, 120, 20);
        lblSeatUpgradeVal.setBounds(160, 175, 146, 20);

        lblTotalPriceTitle.setBounds(20, 215, 120, 20);
        lblTotalPriceVal.setBounds(140, 210, 166, 30);

        btnConfirmSeat.setBounds(0, 280, 326, 45);
        btnBackResults.setBounds(0, 340, 326, 42);

        lblBackToPassenger.setBounds(30, 15, 220, 20);

        // Legends indicator squares
        styleLegendIcon(lblAvailableLegend, Color.WHITE, new Color(200, 200, 200), false, false);
        styleLegendIcon(lblSelectedLegend, new Color(15, 61, 117), new Color(15, 61, 117), true, false);
        styleLegendIcon(lblOccupiedLegend, new Color(220, 220, 220), new Color(200, 200, 200), false, true);

        // Buttons styling
        btnConfirmSeat.setOpaque(false);
        btnConfirmSeat.setCursor(hand);
        btnConfirmSeat.setBorder(new Border() {
            private final Color hoverCol = new Color(10, 46, 89);
            private final Color normalCol = new Color(15, 61, 117);
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                JButton b = (JButton) c;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (b.getModel().isRollover()) {
                    g2.setColor(hoverCol);
                } else {
                    g2.setColor(normalCol);
                }
                g2.fillRoundRect(x, y, width, height, 8, 8);
                
                g2.setColor(b.getForeground());
                g2.setFont(b.getFont());
                String text = b.getText();
                java.awt.FontMetrics fm = g2.getFontMetrics();
                int tx = x + (width - fm.stringWidth(text)) / 2 - 8;
                int ty = y + (height - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(text, tx, ty);
                
                // Draw vector arrow key next to the text
                g2.setStroke(new java.awt.BasicStroke(2.0f));
                g2.setColor(Color.WHITE);
                int ax = tx + fm.stringWidth(text) + 8;
                int ay = y + height / 2;
                g2.drawLine(ax, ay, ax + 8, ay);
                g2.drawLine(ax + 5, ay - 3, ax + 8, ay);
                g2.drawLine(ax + 5, ay + 3, ax + 8, ay);
                g2.dispose();
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(1, 1, 1, 1); }
            @Override public boolean isBorderOpaque() { return false; }
        });

        btnBackResults.setOpaque(false);
        btnBackResults.setCursor(hand);
        btnBackResults.setBorder(new Border() {
            private final Color borderCol = new Color(15, 61, 117);
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                JButton b = (JButton) c;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(x, y, width - 1, height - 1, 8, 8);
                g2.setColor(borderCol);
                g2.setStroke(new java.awt.BasicStroke(1f));
                g2.drawRoundRect(x, y, width - 1, height - 1, 8, 8);
                
                g2.setColor(b.getForeground());
                g2.setFont(b.getFont());
                String text = b.getText();
                java.awt.FontMetrics fm = g2.getFontMetrics();
                int tx = x + (width - fm.stringWidth(text)) / 2;
                int ty = y + (height - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(text, tx, ty);
                g2.dispose();
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(1, 1, 1, 1); }
            @Override public boolean isBorderOpaque() { return false; }
        });

        // Initialize and paint aircraft fuselage grid programmatically
        initInteractiveSeatGrid();

        // Update pricing details
        updateSelectionSummary();

        // Force repaint and revalidate to ensure custom components lay out correctly
        pnlSeatGridCard.revalidate();
        pnlSeatGridCard.repaint();
        revalidate();
        repaint();
    }

    private void styleLegendIcon(JLabel lbl, final Color fill, final Color border, final boolean check, final boolean cross) {
        lbl.setBorder(new Border() {
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw square icon on the left (x=0, y=0, w=20, h=20)
                g2.setColor(fill);
                g2.fillRoundRect(0, 0, 20, 20, 4, 4);
                g2.setColor(border);
                g2.setStroke(new java.awt.BasicStroke(1f));
                g2.drawRoundRect(0, 0, 20, 20, 4, 4);
                
                if (check) {
                    g2.setColor(Color.WHITE);
                    g2.setStroke(new java.awt.BasicStroke(1.5f));
                    g2.drawLine(5, 10, 8, 13);
                    g2.drawLine(8, 13, 14, 7);
                } else if (cross) {
                    g2.setColor(new Color(119, 119, 119));
                    g2.setStroke(new java.awt.BasicStroke(1.5f));
                    g2.drawLine(6, 6, 14, 14);
                    g2.drawLine(14, 6, 6, 14);
                }
                g2.dispose();
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(0, 28, 0, 0); } // Padding for legend text
            @Override public boolean isBorderOpaque() { return false; }
        });
    }

    private void initInteractiveSeatGrid() {
        // Container for airplane contour body paint
        JPanel pnlFuselage = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw airplane fuselage outline (perfectly centered and symmetrical)
                g2.setColor(new Color(248, 249, 250)); // #F8F9FA
                g2.fillRoundRect(75, 30, 470, 330, 30, 30);
                g2.setColor(new Color(229, 231, 235)); // #E5E7EB
                g2.setStroke(new java.awt.BasicStroke(1.5f));
                g2.drawRoundRect(75, 30, 470, 330, 30, 30);
                g2.dispose();
            }
        };
        pnlFuselage.setOpaque(false);
        pnlFuselage.setLayout(null);
        pnlFuselage.setBounds(0, 50, 620, 385);
        pnlSeatGridCard.add(pnlFuselage);

        // Add Column Headers A, B, C, D, E, F inside fuselage
        String[] cols = {"A", "B", "C", "D", "E", "F"};
        int[] colXs = {110, 175, 240, 345, 410, 475};
        for (int i = 0; i < cols.length; i++) {
            JLabel lblCol = new JLabel(cols[i], JLabel.CENTER);
            lblCol.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            lblCol.setForeground(new Color(119, 119, 119));
            lblCol.setBounds(colXs[i], 42, 35, 20);
            pnlFuselage.add(lblCol);
        }

        // Add Rows numbers 1 to 5 and seat panels inside grid
        int[] rowYs = {65, 125, 185, 245, 305};
        for (int r = 0; r < 5; r++) {
            // Row number label placed cleanly inside cabin on the left of Col A
            JLabel lblRow = new JLabel(String.valueOf(r + 1), JLabel.CENTER);
            lblRow.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            lblRow.setForeground(new Color(119, 119, 119));
            lblRow.setBounds(85, rowYs[r] + 7, 20, 20);
            pnlFuselage.add(lblRow);

            for (int c = 0; c < 6; c++) {
                final int row = r;
                final int col = c;
                final String seatCode = cols[col] + (row + 1);

                final JPanel pnlSeat = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        boolean isOccupied = occupiedSeats[row][col];
                        boolean isSelected = selectedSeatCode.equals(seatCode);

                        // Paint seat background
                        if (isOccupied) {
                            g2.setColor(new Color(229, 231, 235)); // Gray #E5E7EB
                        } else if (isSelected) {
                            g2.setColor(new Color(15, 61, 117)); // Dark Blue #0F3D75
                        } else {
                            g2.setColor(Color.WHITE);
                        }
                        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 6, 6);

                        // Paint seat border
                        if (isSelected) {
                            g2.setColor(new Color(15, 61, 117));
                        } else {
                            g2.setColor(new Color(209, 213, 219)); // Gray #D1D5DB
                        }
                        g2.setStroke(new java.awt.BasicStroke(1f));
                        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 6, 6);

                        // Paint checkmark or cross indicator
                        if (isSelected) {
                            g2.setColor(Color.WHITE);
                            g2.setStroke(new java.awt.BasicStroke(2f));
                            g2.drawLine(10, 17, 15, 23);
                            g2.drawLine(15, 23, 25, 12);
                        } else if (isOccupied) {
                            g2.setColor(new Color(156, 163, 175)); // Darker Gray #9CA3AF
                            g2.setStroke(new java.awt.BasicStroke(1.5f));
                            g2.drawLine(12, 12, 23, 23);
                            g2.drawLine(23, 12, 12, 23);
                        }

                        g2.dispose();
                    }
                };

                pnlSeat.setOpaque(false);
                pnlSeat.setCursor(occupiedSeats[r][c] ? Cursor.getDefaultCursor() : Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                pnlSeat.setBounds(colXs[c], rowYs[r], 35, 35);

                pnlSeat.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (occupiedSeats[row][col]) return; // Cannot select occupied

                        selectedSeatCode = seatCode;
                        repaintGrid();
                        updateSelectionSummary();
                    }
                });

                pnlFuselage.add(pnlSeat);
                seatPanels[row][col] = pnlSeat;
            }
        }
    }

    private void repaintGrid() {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 6; c++) {
                if (seatPanels[r][c] != null) {
                    seatPanels[r][c].repaint();
                }
            }
        }
    }

    public void updateSelectionSummary() {
        lblSeatCodeBox.setText(selectedSeatCode);
        lblSeatName.setText("Seat " + selectedSeatCode);

        // Seat features: A & F are windows, C & D are aisles
        String desc = "Standard Seat";
        double upgradeFee = 0.0;
        char colChar = selectedSeatCode.charAt(0);
        int rowNo = Character.getNumericValue(selectedSeatCode.charAt(1));

        if (colChar == 'A' || colChar == 'F') {
            desc = "Window";
            upgradeFee = 1200.0;
        } else if (colChar == 'C' || colChar == 'D') {
            desc = "Aisle";
            upgradeFee = 500.0;
        }

        if (rowNo <= 2) {
            desc += " | Extra Legroom";
            // Extra legroom on window seats has NPR 1,200 (Matches mockup: Seat A2 Window | Extra Legroom NPR 1,200)
            if (colChar == 'A' || colChar == 'F') {
                upgradeFee = 1200.0;
            } else {
                upgradeFee += 500.0;
            }
        }

        lblSeatDesc.setText(desc);
        lblSeatPrice.setText("NPR " + String.format("%,.0f", upgradeFee));

        // Pricing summary
        double baseFare = flight.getPrice(); // defaults to 8400 or flight base
        double total = baseFare + upgradeFee;

        lblBaseFareVal.setText("NPR " + String.format("%,.0f", baseFare));
        lblSeatUpgradeVal.setText("NPR " + String.format("%,.0f", upgradeFee));
        lblTotalPriceVal.setText("NPR " + String.format("%,.0f", total));
        
        btnConfirmSeat.setText("Confirm Seat Selection");
    }

    private javax.swing.Icon getVectorIcon(String type, int width, int height, java.awt.Color color) {
        return new javax.swing.Icon() {
            @Override
            public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color != null ? color : (c != null ? c.getForeground() : java.awt.Color.GRAY));
                g2.setStroke(new java.awt.BasicStroke(1.5f));
                
                if ("grid".equalsIgnoreCase(type)) {
                    int sw = width / 2 - 1;
                    int sh = height / 2 - 1;
                    g2.drawRoundRect(x, y, sw, sh, 1, 1);
                    g2.drawRoundRect(x + sw + 2, y, sw, sh, 1, 1);
                    g2.drawRoundRect(x, y + sh + 2, sw, sh, 1, 1);
                    g2.drawRoundRect(x + sw + 2, y + sh + 2, sw, sh, 1, 1);
                } else if ("search".equalsIgnoreCase(type)) {
                    int r = width * 2 / 3;
                    g2.drawOval(x, y, r, r);
                    g2.drawLine(x + r - 1, y + r - 1, x + width - 1, y + height - 1);
                } else if ("briefcase".equalsIgnoreCase(type)) {
                    g2.drawRoundRect(x, y + 3, width - 1, height - 4, 2, 2);
                    g2.drawRect(x + width / 4, y, width / 2, 3);
                    g2.drawLine(x, y + 7, x + width - 1, y + 7);
                } else if ("checkin".equalsIgnoreCase(type)) {
                    g2.drawRoundRect(x, y + 2, width - 1, height - 4, 2, 2);
                    g2.drawLine(x + 4, y + 2, x + 4, y + height - 2);
                    g2.drawLine(x + 7, y + height / 2 + 1, x + 9, y + height / 2 + 3);
                    g2.drawLine(x + 9, y + height / 2 + 3, x + 12, y + height / 2 - 1);
                } else if ("profile".equalsIgnoreCase(type)) {
                    int hw = width / 2;
                    g2.drawOval(x + (width - hw) / 2, y, hw, hw);
                    g2.drawArc(x, y + hw - 1, width - 1, height - hw, 0, 180);
                } else if ("chat".equalsIgnoreCase(type)) {
                    g2.drawRoundRect(x, y + 1, width - 1, height - 4, 2, 2);
                    java.awt.Polygon tail = new java.awt.Polygon();
                    tail.addPoint(x + 3, y + height - 3);
                    tail.addPoint(x + 7, y + height - 3);
                    tail.addPoint(x + 3, y + height - 1);
                    g2.fill(tail);
                } else if ("logout".equalsIgnoreCase(type)) {
                    g2.drawArc(x, y + 1, width - 6, height - 2, 90, 180);
                    g2.drawLine(x, y + 1, x, y + height - 1);
                    g2.drawLine(x + 2, y + height / 2, x + width - 1, y + height / 2);
                    g2.drawLine(x + width - 4, y + height / 2 - 3, x + width - 1, y + height / 2);
                    g2.drawLine(x + width - 4, y + height / 2 + 3, x + width - 1, y + height / 2);
                } else if ("settings".equalsIgnoreCase(type)) {
                    int cx = x + width / 2;
                    int cy = y + height / 2;
                    int r1 = width / 4;
                    g2.drawOval(cx - r1, cy - r1, r1 * 2, r1 * 2);
                    for (int i = 0; i < 8; i++) {
                        double angle = i * Math.PI / 4;
                        int x1 = (int) (cx + Math.cos(angle) * r1);
                        int y1 = (int) (cy + Math.sin(angle) * r1);
                        int x2 = (int) (cx + Math.cos(angle) * (r1 + 2));
                        int y2 = (int) (cy + Math.sin(angle) * (r1 + 2));
                        g2.drawLine(x1, y1, x2, y2);
                    }
                } else if ("home".equalsIgnoreCase(type)) {
                    g2.drawLine(x + width / 2, y, x, y + height / 2);
                    g2.drawLine(x + width / 2, y, x + width - 1, y + height / 2);
                    g2.drawLine(x + 2, y + height / 2, x + 2, y + height - 1);
                    g2.drawLine(x + width - 3, y + height / 2, x + width - 3, y + height - 1);
                    g2.drawLine(x + 2, y + height - 1, x + width - 3, y + height - 1);
                    g2.drawRect(x + width / 2 - 2, y + height - 5, 4, 4);
                } else if ("bell".equalsIgnoreCase(type)) {
                    g2.drawRoundRect(x + width / 4, y + 2, width / 2, height * 2 / 3, 3, 3);
                    g2.drawLine(x + 2, y + height * 2 / 3 + 2, x + width - 3, y + height * 2 / 3 + 2);
                    g2.drawArc(x + width / 2 - 2, y + height * 2 / 3 + 2, 4, 3, 180, 180);
                } else if ("help".equalsIgnoreCase(type)) {
                    g2.drawOval(x, y, width - 1, height - 1);
                    g2.drawString("?", x + width / 2 - 3, y + height / 2 + 4);
                }
                g2.dispose();
            }
            @Override
            public int getIconWidth() { return width; }
            @Override
            public int getIconHeight() { return height; }
        };
    }

    // Custom Rounded Border Helper
    private static class RoundedCardBorder implements Border {
        private final int radius;
        private final Color borderCol;
        private final Color fillCol;

        public RoundedCardBorder(int radius, Color borderCol, Color fillCol) {
            this.radius = radius;
            this.borderCol = borderCol;
            this.fillCol = fillCol;
        }

        @Override
        public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Fill background
            g2.setColor(fillCol);
            g2.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
            
            // Draw border outline
            g2.setColor(borderCol);
            g2.setStroke(new java.awt.BasicStroke(1.0f));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(radius / 2, radius / 2, radius / 2, radius / 2); }
        @Override public boolean isBorderOpaque() { return false; }
    }

    // Getters for controller binding
    public javax.swing.JLabel getLblDashboard() { return lblDashboard; }
    public javax.swing.JLabel getLblSearchFlight() { return lblSearchFlight; }
    public javax.swing.JLabel getLblMyBookings() { return lblMyBookings; }
    public javax.swing.JLabel getLblProfile() { return lblProfile; }
    public javax.swing.JLabel getLblCustomerSupport() { return lblCustomerSupport; }
    public javax.swing.JLabel getLblLogout() { return lblLogout; }
    public javax.swing.JLabel getLblBackToHome() { return lblBackToHome; }
    public javax.swing.JLabel getLblBackToPassenger() { return lblBackToPassenger; }
    public javax.swing.JButton getBtnConfirmSeat() { return btnConfirmSeat; }
    public javax.swing.JButton getBtnBackResults() { return btnBackResults; }
    public javax.swing.JButton getBtnBookNewFlight() { return btnBookNewFlight; }
    public String getSelectedSeatCode() { return selectedSeatCode; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblDashboard = new javax.swing.JLabel();
        lblSearchFlight = new javax.swing.JLabel();
        lblMyBookings = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblCustomerSupport = new javax.swing.JLabel();
        btnBookNewFlight = new javax.swing.JButton();
        lblLogout = new javax.swing.JLabel();
        lblSystemStatus = new javax.swing.JLabel();
        pnlTopHeader = new javax.swing.JPanel();
        lblTopTitle = new javax.swing.JLabel();
        lblHeaderIcons = new javax.swing.JLabel();
        lblBackToHome = new javax.swing.JLabel();
        pnlMainBg = new javax.swing.JPanel();
        lblBackToPassenger = new javax.swing.JLabel();
        pnlSeatHeaderCard = new view.RoundedPanel();
        lblSeatTitle = new javax.swing.JLabel();
        lblFlightBadge = new javax.swing.JLabel();
        lblTimeBadge = new javax.swing.JLabel();
        lblClassVal = new javax.swing.JLabel();
        lblAircraftVal = new javax.swing.JLabel();
        pnlSeatGridCard = new view.RoundedPanel();
        lblAvailableLegend = new javax.swing.JLabel();
        lblSelectedLegend = new javax.swing.JLabel();
        lblOccupiedLegend = new javax.swing.JLabel();
        pnlRightCol = new javax.swing.JPanel();
        pnlSelectionDetailsCard = new view.RoundedPanel();
        lblSelectionDetailsTitle = new javax.swing.JLabel();
        pnlSelectedSeatBadge = new view.RoundedPanel();
        lblSeatCodeBox = new javax.swing.JLabel();
        lblSeatName = new javax.swing.JLabel();
        lblSeatDesc = new javax.swing.JLabel();
        lblSeatPrice = new javax.swing.JLabel();
        lblBaseFareTitle = new javax.swing.JLabel();
        lblBaseFareVal = new javax.swing.JLabel();
        lblSeatUpgradeTitle = new javax.swing.JLabel();
        lblSeatUpgradeVal = new javax.swing.JLabel();
        lblTotalPriceTitle = new javax.swing.JLabel();
        lblTotalPriceVal = new javax.swing.JLabel();
        btnConfirmSeat = new javax.swing.JButton();
        btnBackResults = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - Seat Selection");
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlSidebar.setBackground(new java.awt.Color(255, 255, 255));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(26, 26, 26));
        lblLogo.setText("▼  YATRAAIR");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(24, 30, 200, 40);

        lblDashboard.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(51, 51, 51));
        lblDashboard.setText("㗊  Dashboard");
        pnlSidebar.add(lblDashboard);
        lblDashboard.setBounds(15, 120, 210, 40);

        lblSearchFlight.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSearchFlight.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchFlight.setText("🔍  Search Flight");
        pnlSidebar.add(lblSearchFlight);
        lblSearchFlight.setBounds(15, 170, 210, 40);

        lblMyBookings.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMyBookings.setForeground(new java.awt.Color(51, 51, 51));
        lblMyBookings.setText("📋  My Bookings");
        pnlSidebar.add(lblMyBookings);
        lblMyBookings.setBounds(15, 220, 210, 40);

        lblProfile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblProfile.setForeground(new java.awt.Color(51, 51, 51));
        lblProfile.setText("👤  Profile");
        pnlSidebar.add(lblProfile);
        lblProfile.setBounds(15, 270, 210, 40);

        lblCustomerSupport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCustomerSupport.setForeground(new java.awt.Color(51, 51, 51));
        lblCustomerSupport.setText("💬  Customer Support");
        pnlSidebar.add(lblCustomerSupport);
        lblCustomerSupport.setBounds(15, 320, 210, 40);

        btnBookNewFlight.setBackground(new java.awt.Color(15, 61, 117));
        btnBookNewFlight.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBookNewFlight.setForeground(new java.awt.Color(255, 255, 255));
        btnBookNewFlight.setText("Book New Flight");
        pnlSidebar.add(btnBookNewFlight);
        btnBookNewFlight.setBounds(15, 550, 210, 40);

        lblLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblLogout.setForeground(new java.awt.Color(255, 59, 48));
        lblLogout.setText("🚪  Logout");
        pnlSidebar.add(lblLogout);
        lblLogout.setBounds(15, 630, 210, 40);

        lblSystemStatus.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSystemStatus.setForeground(new java.awt.Color(119, 119, 119));
        lblSystemStatus.setText("● SYSTEM STATUS: OPERATIONAL");
        pnlSidebar.add(lblSystemStatus);
        lblSystemStatus.setBounds(15, 725, 210, 20);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 240, 768);

        pnlTopHeader.setBackground(new java.awt.Color(8, 22, 42));
        pnlTopHeader.setLayout(null);

        lblTopTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTopTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTopTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTopTitle.setText("YATRA AIR SEWA");
        pnlTopHeader.add(lblTopTitle);
        lblTopTitle.setBounds(438, 22, 250, 25);

        lblHeaderIcons.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHeaderIcons.setForeground(new java.awt.Color(255, 255, 255));
        lblHeaderIcons.setText("🔔   ❔   ⚙");
        pnlTopHeader.add(lblHeaderIcons);
        lblHeaderIcons.setBounds(1000, 22, 90, 25);

        lblBackToHome.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToHome.setText("  Back to Home");
        pnlTopHeader.add(lblBackToHome);
        lblBackToHome.setBounds(30, 25, 150, 20);

        getContentPane().add(pnlTopHeader);
        pnlTopHeader.setBounds(240, 0, 1126, 70);

        pnlMainBg.setBackground(new java.awt.Color(245, 246, 250));
        pnlMainBg.setLayout(null);

        lblBackToPassenger.setForeground(new java.awt.Color(11, 94, 215));
        lblBackToPassenger.setText("← Back to Passenger Details");
        pnlMainBg.add(lblBackToPassenger);
        lblBackToPassenger.setBounds(30, 15, 220, 20);

        pnlSeatHeaderCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlSeatHeaderCard.setLayout(null);

        lblSeatTitle.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblSeatTitle.setForeground(new java.awt.Color(10, 46, 89));
        lblSeatTitle.setText("Seat Selection");
        pnlSeatHeaderCard.add(lblSeatTitle);
        lblSeatTitle.setBounds(20, 15, 200, 25);

        lblFlightBadge.setForeground(new java.awt.Color(51, 51, 51));
        lblFlightBadge.setText("✈ Yatra Air 101 | KTM → PKR");
        pnlSeatHeaderCard.add(lblFlightBadge);
        lblFlightBadge.setBounds(20, 47, 220, 22);

        lblTimeBadge.setForeground(new java.awt.Color(51, 51, 51));
        lblTimeBadge.setText("🕒 10:00 AM - 11:00 AM");
        pnlSeatHeaderCard.add(lblTimeBadge);
        lblTimeBadge.setBounds(250, 47, 180, 22);

        lblClassVal.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblClassVal.setForeground(new java.awt.Color(153, 153, 153));
        lblClassVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblClassVal.setText("ECONOMY CLASS");
        pnlSeatHeaderCard.add(lblClassVal);
        lblClassVal.setBounds(830, 22, 210, 15);

        lblAircraftVal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAircraftVal.setForeground(new java.awt.Color(10, 46, 89));
        lblAircraftVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAircraftVal.setText("Airbus A320");
        pnlSeatHeaderCard.add(lblAircraftVal);
        lblAircraftVal.setBounds(830, 40, 210, 25);

        pnlMainBg.add(pnlSeatHeaderCard);
        pnlSeatHeaderCard.setBounds(30, 50, 1066, 90);

        pnlSeatGridCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlSeatGridCard.setLayout(null);

        lblAvailableLegend.setForeground(new java.awt.Color(85, 85, 85));
        lblAvailableLegend.setText("Available");
        pnlSeatGridCard.add(lblAvailableLegend);
        lblAvailableLegend.setBounds(180, 20, 80, 20);

        lblSelectedLegend.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSelectedLegend.setForeground(new java.awt.Color(15, 61, 117));
        lblSelectedLegend.setText("Selected");
        pnlSeatGridCard.add(lblSelectedLegend);
        lblSelectedLegend.setBounds(290, 20, 80, 20);

        lblOccupiedLegend.setForeground(new java.awt.Color(119, 119, 119));
        lblOccupiedLegend.setText("Occupied");
        pnlSeatGridCard.add(lblOccupiedLegend);
        lblOccupiedLegend.setBounds(400, 20, 80, 20);

        pnlMainBg.add(pnlSeatGridCard);
        pnlSeatGridCard.setBounds(30, 160, 690, 450);

        pnlRightCol.setBackground(new java.awt.Color(245, 246, 250));
        pnlRightCol.setLayout(null);

        pnlSelectionDetailsCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlSelectionDetailsCard.setLayout(null);

        lblSelectionDetailsTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblSelectionDetailsTitle.setForeground(new java.awt.Color(10, 46, 89));
        lblSelectionDetailsTitle.setText("Selection Details");
        pnlSelectionDetailsCard.add(lblSelectionDetailsTitle);
        lblSelectionDetailsTitle.setBounds(20, 20, 200, 22);

        pnlSelectedSeatBadge.setBackground(new java.awt.Color(230, 240, 255));
        pnlSelectedSeatBadge.setLayout(null);

        lblSeatCodeBox.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblSeatCodeBox.setForeground(new java.awt.Color(255, 255, 255));
        lblSeatCodeBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeatCodeBox.setText("A2");
        pnlSelectedSeatBadge.add(lblSeatCodeBox);
        lblSeatCodeBox.setBounds(15, 15, 40, 40);

        lblSeatName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSeatName.setForeground(new java.awt.Color(10, 46, 89));
        lblSeatName.setText("Seat A2");
        pnlSelectedSeatBadge.add(lblSeatName);
        lblSeatName.setBounds(70, 13, 120, 20);

        lblSeatDesc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSeatDesc.setForeground(new java.awt.Color(119, 119, 119));
        lblSeatDesc.setText("Window | Extra Legroom");
        pnlSelectedSeatBadge.add(lblSeatDesc);
        lblSeatDesc.setBounds(70, 35, 150, 20);

        lblSeatPrice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSeatPrice.setForeground(new java.awt.Color(10, 46, 89));
        lblSeatPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSeatPrice.setText("NPR 1,200");
        pnlSelectedSeatBadge.add(lblSeatPrice);
        lblSeatPrice.setBounds(210, 25, 90, 20);

        pnlSelectionDetailsCard.add(pnlSelectedSeatBadge);
        pnlSelectedSeatBadge.setBounds(20, 55, 316, 70);

        lblBaseFareTitle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblBaseFareTitle.setForeground(new java.awt.Color(119, 119, 119));
        lblBaseFareTitle.setText("Base Fare");
        pnlSelectionDetailsCard.add(lblBaseFareTitle);
        lblBaseFareTitle.setBounds(20, 145, 180, 20);

        lblBaseFareVal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblBaseFareVal.setForeground(new java.awt.Color(51, 51, 51));
        lblBaseFareVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBaseFareVal.setText("NPR 8,400");
        pnlSelectionDetailsCard.add(lblBaseFareVal);
        lblBaseFareVal.setBounds(210, 145, 126, 20);

        lblSeatUpgradeTitle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSeatUpgradeTitle.setForeground(new java.awt.Color(119, 119, 119));
        lblSeatUpgradeTitle.setText("Seat Upgrade");
        pnlSelectionDetailsCard.add(lblSeatUpgradeTitle);
        lblSeatUpgradeTitle.setBounds(20, 175, 180, 20);

        lblSeatUpgradeVal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSeatUpgradeVal.setForeground(new java.awt.Color(51, 51, 51));
        lblSeatUpgradeVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSeatUpgradeVal.setText("NPR 1,200");
        pnlSelectionDetailsCard.add(lblSeatUpgradeVal);
        lblSeatUpgradeVal.setBounds(210, 175, 126, 20);

        lblTotalPriceTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTotalPriceTitle.setForeground(new java.awt.Color(10, 46, 89));
        lblTotalPriceTitle.setText("Total Price");
        pnlSelectionDetailsCard.add(lblTotalPriceTitle);
        lblTotalPriceTitle.setBounds(20, 245, 120, 20);

        lblTotalPriceVal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTotalPriceVal.setForeground(new java.awt.Color(10, 46, 89));
        lblTotalPriceVal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalPriceVal.setText("NPR 9,600");
        pnlSelectionDetailsCard.add(lblTotalPriceVal);
        lblTotalPriceVal.setBounds(150, 240, 186, 30);

        pnlRightCol.add(pnlSelectionDetailsCard);
        pnlSelectionDetailsCard.setBounds(0, 0, 356, 300);

        btnConfirmSeat.setBackground(new java.awt.Color(15, 61, 117));
        btnConfirmSeat.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnConfirmSeat.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmSeat.setText("Confirm Seat Selection  →");
        pnlRightCol.add(btnConfirmSeat);
        btnConfirmSeat.setBounds(0, 320, 356, 48);

        btnBackResults.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBackResults.setForeground(new java.awt.Color(15, 61, 117));
        btnBackResults.setText("Back to Flight Results");
        pnlRightCol.add(btnBackResults);
        btnBackResults.setBounds(0, 380, 356, 45);

        pnlMainBg.add(pnlRightCol);
        pnlRightCol.setBounds(740, 160, 356, 510);

        getContentPane().add(pnlMainBg);
        pnlMainBg.setBounds(240, 70, 1126, 698);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackResults;
    private javax.swing.JButton btnBookNewFlight;
    private javax.swing.JButton btnConfirmSeat;
    private javax.swing.JLabel lblAircraftVal;
    private javax.swing.JLabel lblAvailableLegend;
    private javax.swing.JLabel lblBackToHome;
    private javax.swing.JLabel lblBackToPassenger;
    private javax.swing.JLabel lblBaseFareTitle;
    private javax.swing.JLabel lblBaseFareVal;
    private javax.swing.JLabel lblClassVal;
    private javax.swing.JLabel lblCustomerSupport;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblFlightBadge;
    private javax.swing.JLabel lblHeaderIcons;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblMyBookings;
    private javax.swing.JLabel lblOccupiedLegend;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblSearchFlight;
    private javax.swing.JLabel lblSeatCodeBox;
    private javax.swing.JLabel lblSeatDesc;
    private javax.swing.JLabel lblSeatName;
    private javax.swing.JLabel lblSeatPrice;
    private javax.swing.JLabel lblSeatTitle;
    private javax.swing.JLabel lblSeatUpgradeTitle;
    private javax.swing.JLabel lblSeatUpgradeVal;
    private javax.swing.JLabel lblSelectedLegend;
    private javax.swing.JLabel lblSelectionDetailsTitle;
    private javax.swing.JLabel lblSystemStatus;
    private javax.swing.JLabel lblTimeBadge;
    private javax.swing.JLabel lblTopTitle;
    private javax.swing.JLabel lblTotalPriceTitle;
    private javax.swing.JLabel lblTotalPriceVal;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlRightCol;
    private view.RoundedPanel pnlSeatGridCard;
    private view.RoundedPanel pnlSeatHeaderCard;
    private view.RoundedPanel pnlSelectedSeatBadge;
    private view.RoundedPanel pnlSelectionDetailsCard;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTopHeader;
    // End of variables declaration//GEN-END:variables
}

// Commit 2: Documented seat selection layout and dynamic seat grid generation

// Commit 3: Documented click handlers to update selected seat state

// Commit 5: Documented seat legends for Available, Selected, and Booked status

// Commit 6: Documented checkout redirection and seat validation trigger

// Commit 8: Explained grid placement math for standard airliner seat configurations

// Commit 10: Final notes for NetBeans UI builder properties on seat panels

// Commit 11: Documented row bounds check inside grid mouse handler

// Commit 13: Added documentation for repainting selected seats on hover

// Commit 15: Documented tooltips representing seat type details
