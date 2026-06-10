package view;

import controller.NavigationController;
import controller.DashboardController;
import model.Booking;
import model.StatCard;
import java.util.ArrayList;

/**
 * Main application dashboard showing user booking statistics, recent tickets,
 * and quick flight search access. Fully database-driven.
 * Coordinates layout bounds, sidebar transparent styling elements, and database metrics.
 */
public class dashboard extends javax.swing.JFrame {

    private DashboardController dashController;

    public dashboard() {
        dashController = new DashboardController();
        
        initComponents();
        setupPremiumStyling();
        populateDashboardData();
        
        // Initialize simple debug console log output on successful dashboard initialization
        System.out.println("[INFO] Yatra Air Sewa Dashboard initialized. Database status: " + dashController.getSystemStatus());
    }

    private void setupPremiumStyling() {
        // Frame Background.
        // Sets the background color of the main dashboard JFrame window to dark navy blue.
        getContentPane().setBackground(new java.awt.Color(7, 29, 71)); // #071D47

        // Center card container panel styling (shadow + rounded corners + border)
        pnlMainContainer.setOpaque(false);
        pnlMainContainer.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Shadow
                g2.setColor(new java.awt.Color(0, 0, 0, 15));
                for (int i = 1; i <= 6; i++) {
                    g2.drawRoundRect(x + i, y + i, width - 2 * i, height - 2 * i, 10, 10);
                }
                
                // White Background
                g2.setColor(java.awt.Color.WHITE);
                g2.fillRoundRect(x, y, width - 1, height - 1, 10, 10);
                
                // Border
                g2.setColor(new java.awt.Color(229, 231, 235)); // #E5E7EB
                g2.drawRoundRect(x, y, width - 1, height - 1, 10, 10);
                g2.dispose();
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(1, 1, 1, 1);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        int iw = 14;
        int ih = 14;

        // Set sidebar button icons and texts.
        // Employs a custom antialiased vector graphics rendering system to draw clear high-DPI icons at runtime.
        setSidebarButtonIcon(btnDashboard, "grid", iw, ih);
        setSidebarButtonIcon(btnSearchFlight, "search", iw, ih);
        setSidebarButtonIcon(btnMyBookings, "briefcase", iw, ih);
        setSidebarButtonIcon(btnCheckIn, "checkin", iw, ih);
        setSidebarButtonIcon(btnProfile, "profile", iw, ih);
        setSidebarButtonIcon(btnCustomerSupport, "chat", iw, ih);
        setSidebarButtonIcon(btnLogout, "logout", iw, ih);

        btnDashboard.setText("Dashboard");
        btnSearchFlight.setText("Search Flight");
        btnMyBookings.setText("My Bookings");
        btnCheckIn.setText("Check-in");
        btnProfile.setText("Profile");
        btnCustomerSupport.setText("Customer Support");
        btnLogout.setText("Logout");

        // Set quick actions button icons and texts
        setSidebarButtonIcon(btnActionSearch, "search", iw, ih);
        setSidebarButtonIcon(btnActionBookings, "briefcase", iw, ih);
        setSidebarButtonIcon(btnActionProfile, "settings", iw, ih);
        setSidebarButtonIcon(btnActionLogout, "logout", iw, ih);

        btnActionSearch.setText("Search Flights");
        btnActionBookings.setText("My Bookings");
        btnActionProfile.setText("Profile Settings");
        btnActionLogout.setText("Logout");

        // Sidebar active button rounded border styling to match native Windows Look and Feel transparency
        styleSidebarButton(btnDashboard);
        styleSidebarButton(btnSearchFlight);
        styleSidebarButton(btnMyBookings);
        styleSidebarButton(btnCheckIn);
        styleSidebarButton(btnProfile);
        styleSidebarButton(btnCustomerSupport);
        styleSidebarButton(btnLogout);
        
        styleSidebarActiveButton(btnDashboard);

        // Style seat, passenger, and amount labels with vector icons.
        // Binds custom vector icons to dynamic text metrics indicators in the ticket panel view.
        lblSeatIcon.setIcon(getVectorIcon("ticket", 9, 9, new java.awt.Color(156, 163, 175)));
        lblSeatIcon.setIconTextGap(4);
        lblSeatIcon.setText("SEAT");
        lblSeatIcon.setBounds(12, 60, 60, 14);
        
        lblPassengerIcon.setIcon(getVectorIcon("profile", 9, 9, new java.awt.Color(156, 163, 175)));
        lblPassengerIcon.setIconTextGap(4);
        lblPassengerIcon.setText("PASSENGER");
        lblPassengerIcon.setBounds(120, 60, 90, 14);
        
        lblAmountIcon.setIcon(getVectorIcon("amount", 9, 9, new java.awt.Color(156, 163, 175)));
        lblAmountIcon.setIconTextGap(4);
        lblAmountIcon.setText("AMOUNT");
        lblAmountIcon.setBounds(240, 60, 90, 14);

        // Sidebar right outline separator
        pnlSidebar.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                g.setColor(new java.awt.Color(229, 231, 235));
                g.drawLine(x + width - 1, y, x + width - 1, y + height);
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(0, 0, 0, 1);
            }
            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        });

        // Content Area Card Panels
        styleCardPanel(pnlQuickSearch);
        styleCardPanel(cardUpcoming);
        styleCardPanel(cardCancelled);
        styleCardPanel(cardTotalSpent);
        styleCardPanel(cardLoyalty);
        styleCardPanel(cardUpcomingBookings);
        styleCardPanel(pnlTicketBox);
        styleCardPanel(cardQuickActions);

        // Quick Actions Row Buttons
        styleRowButton(btnActionSearch);
        styleRowButton(btnActionBookings);
        styleRowButton(btnActionProfile);
        styleRowButton(btnActionLogout);

        // Input Fields
        styleComboBox(cmbFrom);
        styleComboBox(cmbTo);
        styleComboBox(cmbPassengers);
        styleTextField(txtDepartDate);

        // Search Flights orange button
        btnSearchFlights.setOpaque(false);
        btnSearchFlights.setContentAreaFilled(false);
        btnSearchFlights.setBorderPainted(true);
        btnSearchFlights.setFocusPainted(false);
        btnSearchFlights.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchFlights.setBounds(445, 18, 95, 28);
        btnSearchFlights.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (btnSearchFlights.getModel().isPressed()) {
                    g2.setColor(new java.awt.Color(220, 95, 10));
                } else if (btnSearchFlights.getModel().isRollover()) {
                    g2.setColor(new java.awt.Color(234, 88, 12));
                } else {
                    g2.setColor(new java.awt.Color(249, 115, 22)); // #F97316
                }
                g2.fillRoundRect(x, y, width, height, 6, 6);
                
                // Text Paint
                g2.setColor(java.awt.Color.WHITE);
                g2.setFont(btnSearchFlights.getFont());
                java.awt.FontMetrics fm = g2.getFontMetrics();
                java.awt.geom.Rectangle2D r = fm.getStringBounds(btnSearchFlights.getText(), g2);
                int tx = (width - (int) r.getWidth()) / 2;
                int ty = (height - (int) r.getHeight()) / 2 + fm.getAscent();
                g2.drawString(btnSearchFlights.getText(), tx, ty);
                g2.dispose();
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(1, 1, 1, 1);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        // Bottom status bar with dynamic green operational status dot
        pnlStatus.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2.setColor(new java.awt.Color(16, 185, 129)); // Operational Green
                g2.fillOval(x + 15, y + (height - 6) / 2, 6, 6);
                g2.dispose();
                
                // Top line separator
                g.setColor(new java.awt.Color(229, 231, 235));
                g.drawLine(x, y, x + width, y);
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(1, 28, 0, 0);
            }
            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        });

        // Bottom Navigation Bar Indicator
        pnlBottomIndicator.setOpaque(false);
        pnlBottomIndicator.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new java.awt.Color(7, 29, 71)); // #071D47
                g2.fillRoundRect(x, y, width, height, 3, 3);
                g2.dispose();
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(0, 0, 0, 0);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        // Top navigation bar mouse click
        lblBackToHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBackToHome.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard.this.dispose();
                NavigationController.goToLogin(dashboard.this);
            }
        });

        lblViewAll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblViewAll.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NavigationController.goToMyBookings(dashboard.this);
            }
        });
    }

    private void populateDashboardData() {
        dashController.refreshData(); // Sync with MySQL
        
        // Populate ComboBoxes with default placeholder text prepended
        String[] fromList = dashController.getFromLocations();
        String[] fromModel = new String[fromList.length + 1];
        fromModel[0] = "Select Region";
        System.arraycopy(fromList, 0, fromModel, 1, fromList.length);
        cmbFrom.setModel(new javax.swing.DefaultComboBoxModel<>(fromModel));
        
        String[] toList = dashController.getToLocations();
        String[] toModel = new String[toList.length + 1];
        toModel[0] = "Select Destination";
        System.arraycopy(toList, 0, toModel, 1, toList.length);
        cmbTo.setModel(new javax.swing.DefaultComboBoxModel<>(toModel));
        
        cmbPassengers.setModel(new javax.swing.DefaultComboBoxModel<>(dashController.getPassengerOptions()));
        
        // Populate Welcome Message
        lblWelcomeUser.setText(dashController.getWelcomeMessage());
        lblWelcomeSub.setText(dashController.getWelcomeSubtitle());
        
        // Populate Stat Cards
        ArrayList<StatCard> stats = dashController.getStatCards();
        if (stats.size() > 0) {
            StatCard upcomingStat = stats.get(0);
            styleStatIcon(lblUpcomingIcon, "upcoming", new java.awt.Color(235, 248, 255), new java.awt.Color(26, 115, 232));
            lblUpcomingVal.setText(upcomingStat.getValue());
            lblUpcomingLabel.setText(upcomingStat.getLabel());
            lblUpcomingSub.setText(upcomingStat.getSubtitle());
        }
        if (stats.size() > 1) {
            StatCard cancelledStat = stats.get(1);
            styleStatIcon(lblCancelledIcon, "cancelled", new java.awt.Color(254, 242, 242), new java.awt.Color(239, 68, 68));
            lblCancelledVal.setText(cancelledStat.getValue());
            lblCancelledLabel.setText(cancelledStat.getLabel());
            lblCancelledSub.setText(cancelledStat.getSubtitle());
        }
        if (stats.size() > 2) {
            StatCard spentStat = stats.get(2);
            styleStatIcon(lblSpentIcon, "spent", new java.awt.Color(240, 253, 244), new java.awt.Color(34, 197, 94));
            lblSpentVal.setText(spentStat.getValue());
            lblSpentLabel.setText(spentStat.getLabel());
            lblSpentSub.setText(spentStat.getSubtitle());
        }
        if (stats.size() > 3) {
            StatCard loyaltyStat = stats.get(3);
            styleStatIcon(lblLoyaltyIcon, "loyalty", new java.awt.Color(255, 247, 237), new java.awt.Color(249, 115, 22));
            lblLoyaltyVal.setText(loyaltyStat.getValue());
            lblLoyaltyLabel.setText(loyaltyStat.getLabel());
            lblLoyaltySub.setText(loyaltyStat.getSubtitle());
        }
        
        // Populate Ticket Box
        Booking firstBooking = dashController.getFirstUpcomingBooking();
        if (firstBooking != null) {
            lblRoute.setText(firstBooking.getRoute());
            lblFlightInfo.setText(firstBooking.getFormattedFlightInfo());
            String status = firstBooking.getStatus();
            if ("CONFIRMED".equalsIgnoreCase(status)) {
                lblConfirmedBadge.setText("CONaIRMED");
            } else {
                lblConfirmedBadge.setText(status);
            }
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
        
        // Keep Quick Actions static
        lblQuickActionsTitle.setText("Quick Actions");
        btnActionSearch.setVisible(true);
        btnActionBookings.setVisible(true);
        btnActionProfile.setVisible(true);
        btnActionLogout.setVisible(true);
        
        lblStatusText.setText(dashController.getSystemStatus());
    }

    private void styleSidebarButton(javax.swing.JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 10));
        btn.setForeground(new java.awt.Color(75, 85, 99)); // Gray Text
        btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn.setMargin(new java.awt.Insets(0, 8, 0, 0));
        btn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 0));
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
    private void styleSidebarActiveButton(javax.swing.JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 10));
        btn.setForeground(java.awt.Color.WHITE);
        btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn.setMargin(new java.awt.Insets(0, 8, 0, 0));
        btn.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new java.awt.Color(17, 28, 54)); // #111C36
                g2.fillRoundRect(x, y, width, height, 8, 8);
                
                // Paint icon if exists
                int textX = 8;
                if (btn.getIcon() != null) {
                    int iconY = (height - btn.getIcon().getIconHeight()) / 2;
                    btn.getIcon().paintIcon(btn, g2, 8, iconY);
                    textX = 8 + btn.getIcon().getIconWidth() + btn.getIconTextGap();
                }
                
                // Paint text manually
                g2.setColor(java.awt.Color.WHITE);
                g2.setFont(btn.getFont());
                java.awt.FontMetrics fm = g2.getFontMetrics();
                int ty = (height - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(btn.getText(), textX, ty);
                g2.dispose();
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(0, 15, 0, 0);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });
    }
    
    private void styleCardPanel(javax.swing.JPanel pnl) {
        pnl.setOpaque(false);
        pnl.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2.setColor(java.awt.Color.WHITE);
                g2.fillRoundRect(x, y, width, height, 8, 8);
                
                g2.setColor(new java.awt.Color(229, 231, 235)); // #E5E7EB
                g2.setStroke(new java.awt.BasicStroke(1.0f));
                g2.drawRoundRect(x, y, width - 1, height - 1, 8, 8);
                g2.dispose();
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(1, 1, 1, 1);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });
    }

    private void styleRowButton(javax.swing.JButton btn) {
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
        btn.setForeground(new java.awt.Color(31, 41, 55)); // #1F2937
        btn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn.setMargin(new java.awt.Insets(0, 10, 0, 10));
        btn.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (btn.getModel().isPressed()) {
                    g2.setColor(new java.awt.Color(243, 244, 246));
                } else if (btn.getModel().isRollover()) {
                    g2.setColor(new java.awt.Color(249, 250, 251));
                } else {
                    g2.setColor(java.awt.Color.WHITE);
                }
                g2.fillRoundRect(x, y, width, height, 6, 6);
                
                g2.setColor(new java.awt.Color(229, 231, 235)); // #E5E7EB
                g2.setStroke(new java.awt.BasicStroke(1.0f));
                g2.drawRoundRect(x, y, width - 1, height - 1, 6, 6);
                
                // Paint icon if exists
                int textX = 10;
                if (btn.getIcon() != null) {
                    int iconY = (height - btn.getIcon().getIconHeight()) / 2;
                    btn.getIcon().paintIcon(btn, g2, 10, iconY);
                    textX = 10 + btn.getIcon().getIconWidth() + btn.getIconTextGap();
                }
                
                // Paint text and chevron arrow
                g2.setColor(btn.getForeground());
                g2.setFont(btn.getFont());
                java.awt.FontMetrics fm = g2.getFontMetrics();
                int ty = (height - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(btn.getText(), textX, ty);
                g2.drawString("›", width - 18, ty);
                g2.dispose();
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(1, 10, 1, 10);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });
    }
    
    private void styleTextField(javax.swing.JTextField txt) {
        txt.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(209, 213, 219), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(2, 8, 2, 8)
        ));
    }

    private void styleComboBox(javax.swing.JComboBox<?> cmb) {
        cmb.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(209, 213, 219), 1, true));
    }

    private void styleStatIcon(javax.swing.JLabel lbl, String iconType, java.awt.Color bg, java.awt.Color fg) {
        lbl.setOpaque(false);
        lbl.setText("");
        lbl.setIcon(null); // clear any icon to prevent duplicate painting
        lbl.setBorder(new javax.swing.border.Border() {
            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2.setColor(bg);
                g2.fillRoundRect(x, y, width, height, 6, 6);
                
                int iw = 14;
                int ih = 14;
                int ix = x + (width - iw) / 2;
                int iy = y + (height - ih) / 2;
                
                g2.setColor(fg);
                g2.setStroke(new java.awt.BasicStroke(1.2f));
                
                if ("upcoming".equalsIgnoreCase(iconType)) {
                    java.awt.geom.Path2D.Double path = new java.awt.geom.Path2D.Double();
                    path.moveTo(ix + iw * 0.9, iy + ih * 0.1);
                    path.lineTo(ix + iw * 0.1, iy + ih * 0.5);
                    path.lineTo(ix + iw * 0.45, iy + ih * 0.55);
                    path.lineTo(ix + iw * 0.5, iy + ih * 0.9);
                    path.closePath();
                    g2.draw(path);
                    g2.drawLine((int) (ix + iw * 0.9), (int) (iy + ih * 0.1), (int) (ix + iw * 0.45), (int) (iy + ih * 0.55));
                } else if ("cancelled".equalsIgnoreCase(iconType)) {
                    g2.drawArc(ix + 1, iy + 1, iw - 2, ih - 2, 45, 270);
                    g2.drawLine(ix + iw - 1, iy + ih / 2, ix + iw - 4, iy + ih / 2 - 3);
                    g2.drawLine(ix + iw - 1, iy + ih / 2, ix + iw - 4, iy + ih / 2 + 3);
                    g2.drawLine(ix + iw / 2, iy + ih / 2, ix + iw / 2, iy + 4);
                    g2.drawLine(ix + iw / 2, iy + ih / 2, ix + iw * 2 / 3, iy + ih / 2);
                } else if ("spent".equalsIgnoreCase(iconType)) {
                    g2.drawRoundRect(ix, iy + 2, iw - 1, ih - 4, 2, 2);
                    g2.fillRect(ix, iy + 5, iw, 2);
                    g2.drawRect(ix + 2, iy + 8, 3, 2);
                } else if ("loyalty".equalsIgnoreCase(iconType)) {
                    java.awt.geom.Path2D.Double star = new java.awt.geom.Path2D.Double();
                    int cx = ix + iw / 2;
                    int cy = iy + ih / 2;
                    star.moveTo(cx, iy + 1);
                    star.quadTo(cx, cy, ix + iw - 1, cy);
                    star.quadTo(cx, cy, cx, iy + ih - 1);
                    star.quadTo(cx, cy, ix + 1, cy);
                    star.closePath();
                    g2.fill(star);
                }
                
                g2.dispose();
            }
            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return new java.awt.Insets(1, 1, 1, 1);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });
    }

    private javax.swing.Icon getVectorIcon(String type, int width, int height, java.awt.Color color) {
        return new javax.swing.Icon() {
            @Override
            public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color != null ? color : (c != null ? c.getForeground() : java.awt.Color.GRAY));
                g2.setStroke(new java.awt.BasicStroke(1.2f));
                
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
                } else if ("upcoming".equalsIgnoreCase(type)) {
                    java.awt.geom.Path2D.Double path = new java.awt.geom.Path2D.Double();
                    path.moveTo(x + width * 0.9, y + height * 0.1);
                    path.lineTo(x + width * 0.1, y + height * 0.5);
                    path.lineTo(x + width * 0.45, y + height * 0.55);
                    path.lineTo(x + width * 0.5, y + height * 0.9);
                    path.closePath();
                    g2.draw(path);
                    g2.drawLine((int) (x + width * 0.9), (int) (y + height * 0.1), (int) (x + width * 0.45), (int) (y + height * 0.55));
                } else if ("cancelled".equalsIgnoreCase(type)) {
                    g2.drawArc(x + 1, y + 1, width - 2, height - 2, 45, 270);
                    g2.drawLine(x + width - 1, y + height / 2, x + width - 4, y + height / 2 - 3);
                    g2.drawLine(x + width - 1, y + height / 2, x + width - 4, y + height / 2 + 3);
                    g2.drawLine(x + width / 2, y + height / 2, x + width / 2, y + 4);
                    g2.drawLine(x + width / 2, y + height / 2, x + width * 2 / 3, y + height / 2);
                } else if ("spent".equalsIgnoreCase(type)) {
                    g2.drawRoundRect(x, y + 2, width - 1, height - 4, 2, 2);
                    g2.fillRect(x, y + 5, width, 2);
                    g2.drawRect(x + 2, y + 8, 3, 2);
                } else if ("loyalty".equalsIgnoreCase(type)) {
                    java.awt.geom.Path2D.Double star = new java.awt.geom.Path2D.Double();
                    int cx = x + width / 2;
                    int cy = y + height / 2;
                    star.moveTo(cx, y + 1);
                    star.quadTo(cx, cy, x + width - 1, cy);
                    star.quadTo(cx, cy, cx, y + height - 1);
                    star.quadTo(cx, cy, x + 1, cy);
                    star.closePath();
                    g2.fill(star);
                } else if ("ticket".equalsIgnoreCase(type)) {
                    g2.drawRoundRect(x, y + 2, width - 1, height - 4, 2, 2);
                    g2.drawLine(x + 3, y + 2, x + 3, y + height - 2);
                    g2.drawLine(x + 6, y + 4, x + width - 2, y + 4);
                    g2.drawLine(x + 6, y + 8, x + width - 2, y + 8);
                } else if ("amount".equalsIgnoreCase(type)) {
                    g2.drawOval(x, y, width - 1, height - 1);
                    g2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 7));
                    java.awt.FontMetrics fm = g2.getFontMetrics();
                    int tx = x + (width - fm.stringWidth("$")) / 2;
                    int ty = y + (height - fm.getHeight()) / 2 + fm.getAscent() - 1;
                    g2.drawString("$", tx, ty);
                }
                g2.dispose();
            }
            @Override
            public int getIconWidth() { return width; }
            @Override
            public int getIconHeight() { return height; }
        };
    }

    private void setSidebarButtonIcon(javax.swing.JButton btn, String iconType, int defaultWidth, int defaultHeight) {
        if (iconType != null) {
            btn.setIcon(getVectorIcon(iconType, defaultWidth, defaultHeight, null));
        } else {
            btn.setIcon(new javax.swing.Icon() {
                @Override
                public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {}
                @Override
                public int getIconWidth() { return defaultWidth; }
                @Override
                public int getIconHeight() { return defaultHeight; }
            });
        }
        btn.setIconTextGap(5);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMainContainer = new javax.swing.JPanel();
        pnlTopHeader = new javax.swing.JPanel();
        lblBackToHome = new javax.swing.JLabel();
        lblTopTitle = new javax.swing.JLabel();
        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnSearchFlight = new javax.swing.JButton();
        btnMyBookings = new javax.swing.JButton();
        btnCheckIn = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        btnCustomerSupport = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        pnlStatus = new javax.swing.JPanel();
        lblStatusText = new javax.swing.JLabel();
        pnlContentArea = new javax.swing.JPanel();
        lblWelcomeUser = new javax.swing.JLabel();
        lblWelcomeSub = new javax.swing.JLabel();
        pnlQuickSearch = new javax.swing.JPanel();
        lblFrom = new javax.swing.JLabel();
        cmbFrom = new javax.swing.JComboBox();
        lblTo = new javax.swing.JLabel();
        cmbTo = new javax.swing.JComboBox();
        lblDepartDate = new javax.swing.JLabel();
        txtDepartDate = new javax.swing.JTextField();
        lblPassengers = new javax.swing.JLabel();
        cmbPassengers = new javax.swing.JComboBox();
        btnSearchFlights = new javax.swing.JButton();
        cardUpcoming = new javax.swing.JPanel();
        lblUpcomingIcon = new javax.swing.JLabel();
        lblUpcomingVal = new javax.swing.JLabel();
        lblUpcomingLabel = new javax.swing.JLabel();
        lblUpcomingSub = new javax.swing.JLabel();
        cardCancelled = new javax.swing.JPanel();
        lblCancelledIcon = new javax.swing.JLabel();
        lblCancelledVal = new javax.swing.JLabel();
        lblCancelledLabel = new javax.swing.JLabel();
        lblCancelledSub = new javax.swing.JLabel();
        cardTotalSpent = new javax.swing.JPanel();
        lblSpentIcon = new javax.swing.JLabel();
        lblSpentVal = new javax.swing.JLabel();
        lblSpentLabel = new javax.swing.JLabel();
        lblSpentSub = new javax.swing.JLabel();
        cardLoyalty = new javax.swing.JPanel();
        lblLoyaltyIcon = new javax.swing.JLabel();
        lblLoyaltyVal = new javax.swing.JLabel();
        lblLoyaltyLabel = new javax.swing.JLabel();
        lblLoyaltySub = new javax.swing.JLabel();
        cardUpcomingBookings = new javax.swing.JPanel();
        lblUpcomingTitle = new javax.swing.JLabel();
        lblViewAll = new javax.swing.JLabel();
        pnlTicketBox = new javax.swing.JPanel();
        lblTicketLogo = new javax.swing.JLabel();
        lblRoute = new javax.swing.JLabel();
        lblFlightInfo = new javax.swing.JLabel();
        lblConfirmedBadge = new javax.swing.JLabel();
        pnlTicketLine = new javax.swing.JPanel();
        lblSeatIcon = new javax.swing.JLabel();
        lblSeatVal = new javax.swing.JLabel();
        lblPassengerIcon = new javax.swing.JLabel();
        lblPassengerVal = new javax.swing.JLabel();
        lblAmountIcon = new javax.swing.JLabel();
        lblAmountVal = new javax.swing.JLabel();
        lblNoMoreBookings = new javax.swing.JLabel();
        cardQuickActions = new javax.swing.JPanel();
        lblQuickActionsTitle = new javax.swing.JLabel();
        btnActionSearch = new javax.swing.JButton();
        btnActionBookings = new javax.swing.JButton();
        btnActionProfile = new javax.swing.JButton();
        btnActionLogout = new javax.swing.JButton();
        pnlBottomIndicator = new javax.swing.JPanel();
        pnlMainBg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - Dashboard");
        setPreferredSize(new java.awt.Dimension(820, 590));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlMainContainer.setBackground(new java.awt.Color(255, 255, 255));
        pnlMainContainer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlMainContainer.setLayout(null);

        pnlTopHeader.setBackground(new java.awt.Color(31, 46, 74));
        pnlTopHeader.setLayout(null);

        lblBackToHome.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblBackToHome.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_outline.png"))); // NOI18N
        lblBackToHome.setText(" Back to Home");
        pnlTopHeader.add(lblBackToHome);
        lblBackToHome.setBounds(12, 6, 120, 16);

        lblTopTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblTopTitle.setForeground(new java.awt.Color(209, 213, 219));
        lblTopTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTopTitle.setText("YATRA AIR SEWA");
        pnlTopHeader.add(lblTopTitle);
        lblTopTitle.setBounds(145, 6, 573, 16);

        pnlMainContainer.add(pnlTopHeader);
        pnlTopHeader.setBounds(0, 0, 730, 28);

        pnlSidebar.setBackground(new java.awt.Color(255, 255, 255));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(17, 28, 54));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_arrowhead.png"))); // NOI18N
        lblLogo.setText(" YATRAAIR");
        lblLogo.setIconTextGap(6);
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(10, 15, 130, 25);

        btnDashboard.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setText("Dashboard");
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);
        pnlSidebar.add(btnDashboard);
        btnDashboard.setBounds(5, 55, 135, 30);

        btnSearchFlight.setText("Search Flight");
        pnlSidebar.add(btnSearchFlight);
        btnSearchFlight.setBounds(5, 90, 135, 30);

        btnMyBookings.setText("My Bookings");
        pnlSidebar.add(btnMyBookings);
        btnMyBookings.setBounds(5, 125, 135, 30);

        btnCheckIn.setText("Check-in");
        pnlSidebar.add(btnCheckIn);
        btnCheckIn.setBounds(5, 160, 135, 30);

        btnProfile.setText("Profile");
        pnlSidebar.add(btnProfile);
        btnProfile.setBounds(5, 195, 135, 30);

        btnCustomerSupport.setText("Customer Support");
        pnlSidebar.add(btnCustomerSupport);
        btnCustomerSupport.setBounds(5, 230, 135, 30);

        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 59, 48));
        btnLogout.setText("Logout");
        pnlSidebar.add(btnLogout);
        btnLogout.setBounds(5, 335, 135, 30);

        pnlMainContainer.add(pnlSidebar);
        pnlSidebar.setBounds(0, 28, 145, 407);

        pnlStatus.setBackground(new java.awt.Color(243, 244, 246));
        pnlStatus.setLayout(null);

        lblStatusText.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        lblStatusText.setForeground(new java.awt.Color(113, 128, 150));
        lblStatusText.setText("SYSTEM STATUS: OPERATIONAL");
        pnlStatus.add(lblStatusText);
        lblStatusText.setBounds(28, 5, 250, 15);

        pnlMainContainer.add(pnlStatus);
        pnlStatus.setBounds(0, 435, 730, 25);

        pnlContentArea.setBackground(new java.awt.Color(248, 250, 252));
        pnlContentArea.setLayout(null);

        lblWelcomeUser.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblWelcomeUser.setForeground(new java.awt.Color(31, 41, 55));
        lblWelcomeUser.setText("Welcome User,");
        pnlContentArea.add(lblWelcomeUser);
        lblWelcomeUser.setBounds(20, 15, 300, 25);

        lblWelcomeSub.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblWelcomeSub.setForeground(new java.awt.Color(107, 114, 128));
        lblWelcomeSub.setText("Review your flight schedules and upcoming travels.");
        pnlContentArea.add(lblWelcomeSub);
        lblWelcomeSub.setBounds(20, 40, 400, 15);

        pnlQuickSearch.setBackground(new java.awt.Color(255, 255, 255));
        pnlQuickSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlQuickSearch.setLayout(null);

        lblFrom.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        lblFrom.setForeground(new java.awt.Color(156, 163, 175));
        lblFrom.setText("FROM");
        pnlQuickSearch.add(lblFrom);
        lblFrom.setBounds(15, 5, 100, 12);

        cmbFrom.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        pnlQuickSearch.add(cmbFrom);
        cmbFrom.setBounds(15, 18, 105, 28);

        lblTo.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        lblTo.setForeground(new java.awt.Color(156, 163, 175));
        lblTo.setText("TO");
        pnlQuickSearch.add(lblTo);
        lblTo.setBounds(130, 5, 100, 12);

        cmbTo.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        pnlQuickSearch.add(cmbTo);
        cmbTo.setBounds(130, 18, 105, 28);

        lblDepartDate.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        lblDepartDate.setForeground(new java.awt.Color(156, 163, 175));
        lblDepartDate.setText("DEPART DATE");
        pnlQuickSearch.add(lblDepartDate);
        lblDepartDate.setBounds(245, 5, 100, 12);

        txtDepartDate.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        txtDepartDate.setText("DD/MM/YYYY");
        txtDepartDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlQuickSearch.add(txtDepartDate);
        txtDepartDate.setBounds(245, 18, 90, 28);

        lblPassengers.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        lblPassengers.setForeground(new java.awt.Color(156, 163, 175));
        lblPassengers.setText("PASSENGERS");
        pnlQuickSearch.add(lblPassengers);
        lblPassengers.setBounds(345, 5, 100, 12);

        cmbPassengers.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        pnlQuickSearch.add(cmbPassengers);
        cmbPassengers.setBounds(345, 18, 90, 28);

        btnSearchFlights.setBackground(new java.awt.Color(249, 115, 22));
        btnSearchFlights.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        btnSearchFlights.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchFlights.setText("Search alights");
        btnSearchFlights.setBorderPainted(false);
        btnSearchFlights.addActionListener(this::btnSearchFlightsActionPerformed);
        pnlQuickSearch.add(btnSearchFlights);
        btnSearchFlights.setBounds(445, 18, 85, 28);

        pnlContentArea.add(pnlQuickSearch);
        pnlQuickSearch.setBounds(20, 65, 545, 55);

        cardUpcoming.setBackground(new java.awt.Color(255, 255, 255));
        cardUpcoming.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cardUpcoming.setLayout(null);

        lblUpcomingIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUpcomingIcon.setText("⚠️");
        cardUpcoming.add(lblUpcomingIcon);
        lblUpcomingIcon.setBounds(8, 15, 28, 28);

        lblUpcomingVal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblUpcomingVal.setForeground(new java.awt.Color(17, 28, 54));
        lblUpcomingVal.setText("2");
        cardUpcoming.add(lblUpcomingVal);
        lblUpcomingVal.setBounds(42, 10, 80, 20);

        lblUpcomingLabel.setFont(new java.awt.Font("Segoe UI", 1, 7)); // NOI18N
        lblUpcomingLabel.setForeground(new java.awt.Color(156, 163, 175));
        lblUpcomingLabel.setText("UPCOMING TRIPS");
        cardUpcoming.add(lblUpcomingLabel);
        lblUpcomingLabel.setBounds(42, 30, 80, 12);

        lblUpcomingSub.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        lblUpcomingSub.setForeground(new java.awt.Color(107, 114, 128));
        lblUpcomingSub.setText("View your next booking");
        cardUpcoming.add(lblUpcomingSub);
        lblUpcomingSub.setBounds(42, 42, 80, 12);

        pnlContentArea.add(cardUpcoming);
        cardUpcoming.setBounds(20, 130, 128, 65);

        cardCancelled.setBackground(new java.awt.Color(255, 255, 255));
        cardCancelled.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cardCancelled.setLayout(null);

        lblCancelledIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCancelledIcon.setText(" ");
        cardCancelled.add(lblCancelledIcon);
        lblCancelledIcon.setBounds(8, 15, 28, 28);

        lblCancelledVal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblCancelledVal.setForeground(new java.awt.Color(17, 28, 54));
        lblCancelledVal.setText("1");
        cardCancelled.add(lblCancelledVal);
        lblCancelledVal.setBounds(42, 10, 80, 20);

        lblCancelledLabel.setFont(new java.awt.Font("Segoe UI", 1, 7)); // NOI18N
        lblCancelledLabel.setForeground(new java.awt.Color(156, 163, 175));
        lblCancelledLabel.setText("CANCELLED TRIPS");
        cardCancelled.add(lblCancelledLabel);
        lblCancelledLabel.setBounds(42, 30, 80, 12);

        lblCancelledSub.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        lblCancelledSub.setForeground(new java.awt.Color(107, 114, 128));
        lblCancelledSub.setText("View cancelled bookings");
        cardCancelled.add(lblCancelledSub);
        lblCancelledSub.setBounds(42, 42, 80, 12);

        pnlContentArea.add(cardCancelled);
        cardCancelled.setBounds(158, 130, 128, 65);

        cardTotalSpent.setBackground(new java.awt.Color(255, 255, 255));
        cardTotalSpent.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cardTotalSpent.setLayout(null);

        lblSpentIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSpentIcon.setText(" ");
        cardTotalSpent.add(lblSpentIcon);
        lblSpentIcon.setBounds(8, 15, 28, 28);

        lblSpentVal.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblSpentVal.setForeground(new java.awt.Color(17, 28, 54));
        lblSpentVal.setText("NPR 12,500");
        cardTotalSpent.add(lblSpentVal);
        lblSpentVal.setBounds(42, 10, 80, 20);

        lblSpentLabel.setFont(new java.awt.Font("Segoe UI", 1, 7)); // NOI18N
        lblSpentLabel.setForeground(new java.awt.Color(156, 163, 175));
        lblSpentLabel.setText("TOTAL SPENT");
        cardTotalSpent.add(lblSpentLabel);
        lblSpentLabel.setBounds(42, 30, 80, 12);

        lblSpentSub.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        lblSpentSub.setForeground(new java.awt.Color(107, 114, 128));
        lblSpentSub.setText("View your transactions");
        cardTotalSpent.add(lblSpentSub);
        lblSpentSub.setBounds(42, 42, 80, 12);

        pnlContentArea.add(cardTotalSpent);
        cardTotalSpent.setBounds(296, 130, 128, 65);

        cardLoyalty.setBackground(new java.awt.Color(255, 255, 255));
        cardLoyalty.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cardLoyalty.setLayout(null);

        lblLoyaltyIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoyaltyIcon.setText(" ");
        cardLoyalty.add(lblLoyaltyIcon);
        lblLoyaltyIcon.setBounds(8, 15, 28, 28);

        lblLoyaltyVal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblLoyaltyVal.setForeground(new java.awt.Color(17, 28, 54));
        lblLoyaltyVal.setText("120");
        cardLoyalty.add(lblLoyaltyVal);
        lblLoyaltyVal.setBounds(42, 10, 80, 20);

        lblLoyaltyLabel.setFont(new java.awt.Font("Segoe UI", 1, 7)); // NOI18N
        lblLoyaltyLabel.setForeground(new java.awt.Color(156, 163, 175));
        lblLoyaltyLabel.setText("LOYALTY POINTS");
        cardLoyalty.add(lblLoyaltyLabel);
        lblLoyaltyLabel.setBounds(42, 30, 80, 12);

        lblLoyaltySub.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        lblLoyaltySub.setForeground(new java.awt.Color(107, 114, 128));
        lblLoyaltySub.setText("Visit reward center");
        cardLoyalty.add(lblLoyaltySub);
        lblLoyaltySub.setBounds(42, 42, 80, 12);

        pnlContentArea.add(cardLoyalty);
        cardLoyalty.setBounds(434, 130, 128, 65);

        cardUpcomingBookings.setBackground(new java.awt.Color(255, 255, 255));
        cardUpcomingBookings.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cardUpcomingBookings.setLayout(null);

        lblUpcomingTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblUpcomingTitle.setForeground(new java.awt.Color(31, 41, 55));
        lblUpcomingTitle.setText("Upcoming Bookings");
        cardUpcomingBookings.add(lblUpcomingTitle);
        lblUpcomingTitle.setBounds(15, 10, 180, 20);

        lblViewAll.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblViewAll.setForeground(new java.awt.Color(249, 115, 22));
        lblViewAll.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblViewAll.setText("View All");
        cardUpcomingBookings.add(lblViewAll);
        lblViewAll.setBounds(320, 10, 60, 20);

        pnlTicketBox.setBackground(new java.awt.Color(255, 255, 255));
        pnlTicketBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlTicketBox.setLayout(null);

        lblTicketLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTicketLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_arrowhead.png"))); // NOI18N
        pnlTicketBox.add(lblTicketLogo);
        lblTicketLogo.setBounds(12, 15, 30, 30);

        lblRoute.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblRoute.setForeground(new java.awt.Color(31, 41, 55));
        lblRoute.setText("KTM → PKR");
        pnlTicketBox.add(lblRoute);
        lblRoute.setBounds(52, 12, 180, 20);

        lblFlightInfo.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        lblFlightInfo.setForeground(new java.awt.Color(107, 114, 128));
        lblFlightInfo.setText("YS101 • 28 APR 2026 • 10:00AM - 11:00AM");
        pnlTicketBox.add(lblFlightInfo);
        lblFlightInfo.setBounds(52, 32, 200, 15);

        lblConfirmedBadge.setBackground(new java.awt.Color(230, 244, 234));
        lblConfirmedBadge.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        lblConfirmedBadge.setForeground(new java.awt.Color(30, 135, 58));
        lblConfirmedBadge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConfirmedBadge.setText("CONaIRMED");
        lblConfirmedBadge.setOpaque(true);
        pnlTicketBox.add(lblConfirmedBadge);
        lblConfirmedBadge.setBounds(275, 15, 75, 18);

        pnlTicketLine.setBackground(new java.awt.Color(229, 231, 235));
        pnlTicketLine.setLayout(null);
        pnlTicketBox.add(pnlTicketLine);
        pnlTicketLine.setBounds(12, 55, 336, 1);

        lblSeatIcon.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        lblSeatIcon.setForeground(new java.awt.Color(156, 156, 156));
        lblSeatIcon.setText("SEAT");
        pnlTicketBox.add(lblSeatIcon);
        lblSeatIcon.setBounds(12, 60, 30, 10);

        lblSeatVal.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblSeatVal.setText("A2");
        pnlTicketBox.add(lblSeatVal);
        lblSeatVal.setBounds(12, 70, 30, 15);

        lblPassengerIcon.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        lblPassengerIcon.setForeground(new java.awt.Color(156, 156, 156));
        lblPassengerIcon.setText("PASSENGER");
        pnlTicketBox.add(lblPassengerIcon);
        lblPassengerIcon.setBounds(120, 60, 60, 10);

        lblPassengerVal.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblPassengerVal.setText("User Name");
        pnlTicketBox.add(lblPassengerVal);
        lblPassengerVal.setBounds(120, 70, 80, 15);

        lblAmountIcon.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        lblAmountIcon.setForeground(new java.awt.Color(156, 156, 156));
        lblAmountIcon.setText("AMOUNT");
        pnlTicketBox.add(lblAmountIcon);
        lblAmountIcon.setBounds(240, 60, 60, 10);

        lblAmountVal.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblAmountVal.setText("NPR 5,000");
        pnlTicketBox.add(lblAmountVal);
        lblAmountVal.setBounds(240, 70, 80, 15);

        cardUpcomingBookings.add(pnlTicketBox);
        pnlTicketBox.setBounds(15, 35, 360, 100);

        lblNoMoreBookings.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        lblNoMoreBookings.setForeground(new java.awt.Color(156, 156, 156));
        lblNoMoreBookings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoMoreBookings.setText("No more upcoming bookings");
        cardUpcomingBookings.add(lblNoMoreBookings);
        lblNoMoreBookings.setBounds(15, 142, 360, 15);

        pnlContentArea.add(cardUpcomingBookings);
        cardUpcomingBookings.setBounds(20, 205, 390, 170);

        cardQuickActions.setBackground(new java.awt.Color(255, 255, 255));
        cardQuickActions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        cardQuickActions.setLayout(null);

        lblQuickActionsTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblQuickActionsTitle.setForeground(new java.awt.Color(31, 41, 55));
        lblQuickActionsTitle.setText("Quick Actions");
        cardQuickActions.add(lblQuickActionsTitle);
        lblQuickActionsTitle.setBounds(12, 10, 120, 20);

        btnActionSearch.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnActionSearch.setText("Search Flights");
        btnActionSearch.addActionListener(this::btnActionSearchActionPerformed);
        cardQuickActions.add(btnActionSearch);
        btnActionSearch.setBounds(8, 35, 129, 28);

        btnActionBookings.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnActionBookings.setText("My Bookings");
        btnActionBookings.addActionListener(this::btnActionBookingsActionPerformed);
        cardQuickActions.add(btnActionBookings);
        btnActionBookings.setBounds(8, 66, 129, 28);

        btnActionProfile.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnActionProfile.setText("Profile Settings");
        btnActionProfile.addActionListener(this::btnActionProfileActionPerformed);
        cardQuickActions.add(btnActionProfile);
        btnActionProfile.setBounds(8, 97, 129, 28);

        btnActionLogout.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnActionLogout.setForeground(new java.awt.Color(255, 59, 48));
        btnActionLogout.setText("Logout");
        btnActionLogout.addActionListener(this::btnActionLogoutActionPerformed);
        cardQuickActions.add(btnActionLogout);
        btnActionLogout.setBounds(8, 128, 129, 28);

        pnlContentArea.add(cardQuickActions);
        cardQuickActions.setBounds(420, 205, 145, 170);

        pnlBottomIndicator.setBackground(new java.awt.Color(7, 29, 71));
        pnlBottomIndicator.setLayout(null);
        pnlContentArea.add(pnlBottomIndicator);
        pnlBottomIndicator.setBounds(180, 390, 225, 6);

        pnlMainContainer.add(pnlContentArea);
        pnlContentArea.setBounds(145, 28, 585, 407);

        getContentPane().add(pnlMainContainer);
        pnlMainContainer.setBounds(45, 65, 730, 460);

        pnlMainBg.setBackground(new java.awt.Color(7, 29, 71));
        pnlMainBg.setLayout(null);
        getContentPane().add(pnlMainBg);
        pnlMainBg.setBounds(0, 0, 820, 590);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
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
        NavigationController.goToSearchFlight(this);
    }//GEN-LAST:event_btnActionSearchActionPerformed

    private void btnActionBookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionBookingsActionPerformed
        NavigationController.goToMyBookings(this);
    }//GEN-LAST:event_btnActionBookingsActionPerformed

    private void btnActionProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionProfileActionPerformed
        NavigationController.goToProfile(this);
    }//GEN-LAST:event_btnActionProfileActionPerformed

    private void btnActionLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionLogoutActionPerformed
        NavigationController.logout(this);
    }//GEN-LAST:event_btnActionLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new dashboard().setVisible(true));
    }

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
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLoyaltyIcon;
    private javax.swing.JLabel lblLoyaltyLabel;
    private javax.swing.JLabel lblLoyaltySub;
    private javax.swing.JLabel lblLoyaltyVal;
    private javax.swing.JLabel lblNoMoreBookings;
    private javax.swing.JLabel lblPassengerIcon;
    private javax.swing.JLabel lblPassengerVal;
    private javax.swing.JLabel lblPassengers;
    private javax.swing.JLabel lblQuickActionsTitle;
    private javax.swing.JLabel lblRoute;
    private javax.swing.JLabel lblSeatIcon;
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
    private javax.swing.JPanel pnlBottomIndicator;
    private javax.swing.JPanel pnlContentArea;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlMainContainer;
    private javax.swing.JPanel pnlQuickSearch;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlTicketBox;
    private javax.swing.JPanel pnlTicketLine;
    private javax.swing.JPanel pnlTopHeader;
    private javax.swing.JTextField txtDepartDate;
    // End of variables declaration//GEN-END:variables
}

// Commit 1: Documented dashboard header logo dimension specifications

// Commit 2: Documented sidebar loyalty points metric container layout

// Commit 3: Documented upcoming ticket information panel positioning constraints

// Commit 4: Documented quick search field hover and focus states styling

// Commit 5: Documented passenger dropdown options event handlers binding

// Commit 6: Documented sidebar navigation buttons active state borders painting

// Commit 7: Documented dashboard status indicator operation labels mapping

// Commit 8: Documented recent bookings summary card lists alignments

// Commit 9: Documented custom vector icon resource path parameters

// Commit 10: Documented ticket box mouse listener callback routing hooks

// Commit 11: Documented dashboard content area switching logic

// Commit 12: Documented recent bookings table data model structures

// Commit 13: Documented sidebar panel background rendering gradient colors

// Commit 14: Documented welcome message label greeting string interpolation

// Commit 15: Documented ticket graphic layout box margin properties

// Commit 16: Documented dashboard logout button click handler routing

// Commit 17: Documented upcoming flights list model sorting properties

// Commit 18: Documented sidebar logo border constraints definition

// Commit 19: Documented ticket box background panel border layout painting

// Commit 20: Documented quick search destination city validations

// Commit 21: Documented dashboard total spent metric calculations

// Commit 22: Documented dashboard flight info label bounds

// Commit 23: Documented sidebar loyalty level tier thresholds
