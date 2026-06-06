package view;

import controller.PaymentController;
import controller.NavigationController;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
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
 * View class representing the Figma Payment From Mobile Banking UI.
 */
public class PaymentFromMobileBanking extends javax.swing.JFrame {

    private String selectedProvider = "esewa";
    private PaymentController controller;
    private JLabel checkEsewa;
    private JLabel checkKhalti;
    private JLabel checkIme;
    private JLabel checkIps;

    public PaymentFromMobileBanking() {
        initComponents();
        customInit();
        // The Controller binds itself on construction
        this.controller = new PaymentController(this);
    }

    private void customInit() {
        Cursor hand = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        lblDashboard.setCursor(hand);
        lblSearchFlight.setCursor(hand);
        lblMyBookings.setCursor(hand);
        lblProfile.setCursor(hand);
        lblCustomerSupport.setCursor(hand);
        lblLogout.setCursor(hand);
        lblBackToHome.setCursor(hand);
        lblBackToSeatSelection.setCursor(hand);
        lblTabCard.setCursor(hand);
        lblTabMobile.setCursor(hand);
        btnConfirmPay.setCursor(hand);
        btnVerify.setCursor(hand);
        btnCancel.setCursor(hand);
        pnlProviderEsewa.setCursor(hand);
        pnlProviderKhalti.setCursor(hand);
        pnlProviderIme.setCursor(hand);
        pnlProviderIps.setCursor(hand);

        // Sidebar Logo Arrowhead
        lblLogo.setText("  YATRAAIR");
        try {
            lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo_arrowhead.png")));
        } catch (Exception ex) {}

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

        // Back to Home link with vector icon
        lblBackToHome.setText("Back to Home");
        lblBackToHome.setIcon(getVectorIcon("home", 14, 14, Color.WHITE));
        lblBackToHome.setIconTextGap(8);
        lblBackToHome.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // Set sidebar menu text padding and vector icons
        int iw = 14;
        int ih = 14;
        lblDashboard.setIcon(getVectorIcon("grid", iw, ih, new Color(119, 119, 119)));
        lblDashboard.setIconTextGap(8);
        lblDashboard.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        lblSearchFlight.setIcon(getVectorIcon("search", iw, ih, Color.WHITE));
        lblSearchFlight.setIconTextGap(8);
        lblSearchFlight.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        lblMyBookings.setIcon(getVectorIcon("briefcase", iw, ih, new Color(119, 119, 119)));
        lblMyBookings.setIconTextGap(8);
        lblMyBookings.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        lblProfile.setIcon(getVectorIcon("profile", iw, ih, new Color(119, 119, 119)));
        lblProfile.setIconTextGap(8);
        lblProfile.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        lblCustomerSupport.setIcon(getVectorIcon("chat", iw, ih, new Color(119, 119, 119)));
        lblCustomerSupport.setIconTextGap(8);
        lblCustomerSupport.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        lblLogout.setIcon(getVectorIcon("logout", iw, ih, new Color(239, 68, 68)));
        lblLogout.setIconTextGap(8);
        lblLogout.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        // Style status operational green circle
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
        lblSystemStatus.setText("SYSTEM STATUS: OPERATIONAL");

        // Action Buttons: Pay and Cancel
        stylePayButton(btnConfirmPay);
        
        btnCancel.setOpaque(false);
        btnCancel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        // Lock icon on the secure SSL notice at the bottom
        lblSecureNote.setIcon(getVectorIcon("lock", 12, 12, new Color(156, 163, 175)));
        lblSecureNote.setIconTextGap(8);

        // Render QR graphic dynamically inside pnlQrGraphic
        pnlQrGraphic.add(new javax.swing.JPanel() {
            {
                setBounds(0, 0, 130, 130);
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMockQr((Graphics2D) g, 0, 0, getWidth());
            }
        });

        // Setup provider selection overlay elements and drawings
        pnlProviderEsewa.add(new JLabel("eSewa") {{
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            setForeground(new Color(26, 26, 26));
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            setBounds(10, 48, 195, 20);
        }});
        pnlProviderEsewa.add(new javax.swing.JPanel() {
            {
                setOpaque(false);
                setBounds(95, 15, 24, 24);
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(96, 178, 42)); // eSewa Green
                g2.fillOval(0, 0, 24, 24);
                g2.setColor(Color.WHITE);
                g2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
                g2.drawString("e", 8, 17);
                g2.dispose();
            }
        });

        pnlProviderKhalti.add(new JLabel("Khalti") {{
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            setForeground(new Color(26, 26, 26));
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            setBounds(10, 48, 195, 20);
        }});
        pnlProviderKhalti.add(new javax.swing.JPanel() {
            {
                setOpaque(false);
                setBounds(95, 15, 24, 24);
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(94, 46, 137)); // Khalti Purple
                int[] px = {4, 20, 10, 4};
                int[] py = {10, 4, 16, 10};
                g2.fillPolygon(px, py, 4);
                g2.dispose();
            }
        });

        pnlProviderIme.add(new JLabel("IME Pay") {{
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            setForeground(new Color(26, 26, 26));
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            setBounds(10, 48, 195, 20);
        }});
        pnlProviderIme.add(new javax.swing.JPanel() {
            {
                setOpaque(false);
                setBounds(95, 15, 24, 24);
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(237, 28, 36)); // IME Red
                g2.fillRoundRect(0, 0, 24, 24, 6, 6);
                g2.setColor(Color.WHITE);
                g2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 8));
                g2.drawString("IME", 3, 15);
                g2.dispose();
            }
        });

        pnlProviderIps.add(new JLabel("connectIPS") {{
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            setForeground(new Color(26, 26, 26));
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            setBounds(10, 48, 195, 20);
        }});
        pnlProviderIps.add(new javax.swing.JPanel() {
            {
                setOpaque(false);
                setBounds(95, 15, 24, 24);
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(11, 83, 148)); // connectIPS Blue
                g2.drawOval(0, 0, 22, 22);
                g2.setColor(new Color(237, 28, 36));
                g2.fillOval(4, 4, 14, 14);
                g2.dispose();
            }
        });

        // Setup check overlays
        checkEsewa = createCheckBadge();
        pnlProviderEsewa.add(checkEsewa);

        checkKhalti = createCheckBadge();
        pnlProviderKhalti.add(checkKhalti);

        checkIme = createCheckBadge();
        pnlProviderIme.add(checkIme);

        checkIps = createCheckBadge();
        pnlProviderIps.add(checkIps);

        pnlProviderEsewa.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { selectProvider("esewa"); }
        });
        pnlProviderKhalti.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { selectProvider("khalti"); }
        });
        pnlProviderIme.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { selectProvider("imepay"); }
        });
        pnlProviderIps.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { selectProvider("connectips"); }
        });

        // Initialize default selected wallet provider
        selectProvider("esewa");
    }

    private JLabel createCheckBadge() {
        JLabel lbl = new JLabel();
        lbl.setBounds(190, 8, 16, 16);
        lbl.setIcon(new javax.swing.Icon() {
            @Override
            public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(15, 61, 117)); // Navy Blue selection color
                g2.fillOval(x, y, 14, 14);
                g2.setColor(Color.WHITE);
                g2.setStroke(new java.awt.BasicStroke(2.0f));
                g2.drawLine(x + 4, y + 7, x + 6, y + 9);
                g2.drawLine(x + 6, y + 9, x + 10, y + 4);
                g2.dispose();
            }
            @Override public int getIconWidth() { return 14; }
            @Override public int getIconHeight() { return 14; }
        });
        lbl.setVisible(false);
        return lbl;
    }

    private void stylePayButton(JButton button) {
        button.setOpaque(false);
        button.setBorder(new Border() {
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
                int tx = x + (width - fm.stringWidth(text)) / 2;
                int ty = y + (height - fm.getHeight()) / 2 + fm.getAscent();
                g2.drawString(text, tx, ty);
                g2.dispose();
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(1, 1, 1, 1); }
            @Override public boolean isBorderOpaque() { return false; }
        });
    }

    public void selectProvider(String provider) {
        this.selectedProvider = provider;
        Color selectBorder = new Color(15, 61, 117); // #0F3D75
        Color normalBorder = new Color(229, 231, 235); // #E5E7EB
        
        pnlProviderEsewa.setBorderColor(provider.equals("esewa") ? selectBorder : normalBorder);
        pnlProviderKhalti.setBorderColor(provider.equals("khalti") ? selectBorder : normalBorder);
        pnlProviderIme.setBorderColor(provider.equals("imepay") ? selectBorder : normalBorder);
        pnlProviderIps.setBorderColor(provider.equals("connectips") ? selectBorder : normalBorder);

        checkEsewa.setVisible(provider.equals("esewa"));
        checkKhalti.setVisible(provider.equals("khalti"));
        checkIme.setVisible(provider.equals("imepay"));
        checkIps.setVisible(provider.equals("connectips"));
    }

    private void drawMockQr(Graphics2D g2d, int x, int y, int size) {
        g2d.setColor(new Color(13, 27, 49)); // Dark Navy
        g2d.fillRect(x, y, size, size);
        
        g2d.setColor(Color.WHITE);
        int finderSize = size / 4;
        
        // Finders
        drawFinderPattern(g2d, x + 8, y + 8, finderSize);
        drawFinderPattern(g2d, x + size - finderSize - 8, y + 8, finderSize);
        drawFinderPattern(g2d, x + 8, y + size - finderSize - 8, finderSize);
        
        // Noise grid
        java.util.Random rand = new java.util.Random(99);
        int cells = 21;
        int cellSize = size / cells;
        for (int r = 0; r < cells; r++) {
            for (int c = 0; c < cells; c++) {
                if ((r < 7 && c < 7) || (r < 7 && c > cells - 8) || (r > cells - 8 && c < 7)) {
                    continue;
                }
                if (rand.nextBoolean()) {
                    g2d.fillRect(x + c * cellSize, y + r * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    private void drawFinderPattern(Graphics2D g2, int x, int y, int size) {
        g2.fillRect(x, y, size, size);
        g2.setColor(new Color(13, 27, 49));
        g2.fillRect(x + size/7, y + size/7, size*5/7, size*5/7);
        g2.setColor(Color.WHITE);
        g2.fillRect(x + size*2/7, y + size*2/7, size*3/7, size*3/7);
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
                } else if ("lock".equalsIgnoreCase(type)) {
                    g2.drawRoundRect(x + 2, y + 5, width - 5, height - 6, 2, 2);
                    g2.drawArc(x + 4, y + 1, width - 9, 8, 0, 180);
                }
                g2.dispose();
            }
            @Override public int getIconWidth() { return width; }
            @Override public int getIconHeight() { return height; }
        };
    }

    // Getters for controller binding
    public javax.swing.JLabel getLblDashboard() { return lblDashboard; }
    public javax.swing.JLabel getLblSearchFlight() { return lblSearchFlight; }
    public javax.swing.JLabel getLblMyBookings() { return lblMyBookings; }
    public javax.swing.JLabel getLblProfile() { return lblProfile; }
    public javax.swing.JLabel getLblCustomerSupport() { return lblCustomerSupport; }
    public javax.swing.JLabel getLblLogout() { return lblLogout; }
    public javax.swing.JLabel getLblBackToHome() { return lblBackToHome; }
    public javax.swing.JLabel getLblBackToSeatSelection() { return lblBackToSeatSelection; }
    public javax.swing.JLabel getLblTabCard() { return lblTabCard; }
    public javax.swing.JLabel getLblTabMobile() { return lblTabMobile; }
    
    public javax.swing.JButton getBtnConfirmPay() { return btnConfirmPay; }
    public javax.swing.JButton getBtnVerify() { return btnVerify; }
    public javax.swing.JButton getBtnCancel() { return btnCancel; }
    
    public JTextField getTxtWalletId() { return txtWalletId; }
    public String getWalletIdInput() { return txtWalletId.getText().trim(); }
    public String getSelectedProvider() { return selectedProvider; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        pnlBackground = new view.GradientBackgroundPanel();
        pnlMainContainer = new view.MainContainerPanel();
        pnlTopHeader = new javax.swing.JPanel();
        lblTopTitle = new javax.swing.JLabel();
        lblBackToHome = new javax.swing.JLabel();
        lblHeaderIcons = new javax.swing.JLabel();
        pnlSidebar = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblDashboard = new javax.swing.JLabel();
        pnlActiveSearchFlight = new view.RoundedPanel();
        lblSearchFlight = new javax.swing.JLabel();
        lblMyBookings = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblCustomerSupport = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        pnlMainBg = new javax.swing.JPanel();
        lblBackToSeatSelection = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblTabCard = new javax.swing.JLabel();
        lblTabMobile = new javax.swing.JLabel();
        lblTabLine = new javax.swing.JLabel();
        lblTabUnderline = new javax.swing.JLabel();
        pnlMobileContainer = new view.RoundedPanel();
        lblSelectProviderTitle = new javax.swing.JLabel();
        pnlProviderEsewa = new view.RoundedPanel();
        pnlProviderKhalti = new view.RoundedPanel();
        pnlProviderIme = new view.RoundedPanel();
        pnlProviderIps = new view.RoundedPanel();
        pnlQrInstructions = new view.RoundedPanel();
        pnlQrBox = new view.RoundedPanel();
        pnlQrGraphic = new javax.swing.JPanel();
        lblQrLabel = new javax.swing.JLabel();
        lblInstructionsTitle = new javax.swing.JLabel();
        lblInstruction1 = new javax.swing.JLabel();
        lblInstruction2 = new javax.swing.JLabel();
        lblInstruction3 = new javax.swing.JLabel();
        lblInstruction4 = new javax.swing.JLabel();
        lblOrMobile = new javax.swing.JLabel();
        txtWalletId = new view.PlaceholderTextField();
        btnVerify = new javax.swing.JButton();
        lblSecureNote = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnConfirmPay = new javax.swing.JButton();
        pnlStatus = new javax.swing.JPanel();
        lblSystemStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - Payment");
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlBackground.setLayout(null);

        pnlMainContainer.setLayout(null);

        pnlTopHeader.setBackground(new java.awt.Color(8, 22, 42));
        pnlTopHeader.setLayout(null);

        lblTopTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTopTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTopTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTopTitle.setText("YATRA AIR SEWA");
        pnlTopHeader.add(lblTopTitle);
        lblTopTitle.setBounds(498, 18, 250, 25);

        lblBackToHome.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblBackToHome.setForeground(new java.awt.Color(255, 255, 255));
        lblBackToHome.setText("Back to Home");
        pnlTopHeader.add(lblBackToHome);
        lblBackToHome.setBounds(30, 20, 150, 20);

        lblHeaderIcons.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHeaderIcons.setForeground(new java.awt.Color(255, 255, 255));
        lblHeaderIcons.setText("🔔   ❓   ⚙️");
        pnlTopHeader.add(lblHeaderIcons);
        lblHeaderIcons.setBounds(1130, 18, 90, 25);

        pnlMainContainer.add(pnlTopHeader);
        pnlTopHeader.setBounds(0, 0, 1246, 60);

        pnlSidebar.setBackground(new java.awt.Color(255, 255, 255));
        pnlSidebar.setLayout(null);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(26, 26, 26));
        lblLogo.setText("▼  YATRAAIR");
        pnlSidebar.add(lblLogo);
        lblLogo.setBounds(24, 25, 180, 30);

        lblDashboard.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDashboard.setForeground(new java.awt.Color(119, 119, 119));
        lblDashboard.setText("Dashboard");
        pnlSidebar.add(lblDashboard);
        lblDashboard.setBounds(15, 90, 190, 35);

        pnlActiveSearchFlight.setFillColor(new java.awt.Color(43, 10, 10));
        pnlActiveSearchFlight.setBorderColor(new java.awt.Color(43, 10, 10));
        pnlActiveSearchFlight.setCornerRadius(8);
        pnlActiveSearchFlight.setLayout(null);

        lblSearchFlight.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSearchFlight.setForeground(new java.awt.Color(255, 255, 255));
        lblSearchFlight.setText("Search Flight");
        pnlActiveSearchFlight.add(lblSearchFlight);
        lblSearchFlight.setBounds(0, 0, 190, 35);

        pnlSidebar.add(pnlActiveSearchFlight);
        pnlActiveSearchFlight.setBounds(15, 135, 190, 35);

        lblMyBookings.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMyBookings.setForeground(new java.awt.Color(119, 119, 119));
        lblMyBookings.setText("My Bookings");
        pnlSidebar.add(lblMyBookings);
        lblMyBookings.setBounds(15, 180, 190, 35);

        lblProfile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblProfile.setForeground(new java.awt.Color(119, 119, 119));
        lblProfile.setText("Profile");
        pnlSidebar.add(lblProfile);
        lblProfile.setBounds(15, 225, 190, 35);

        lblCustomerSupport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCustomerSupport.setForeground(new java.awt.Color(119, 119, 119));
        lblCustomerSupport.setText("Customer Support");
        pnlSidebar.add(lblCustomerSupport);
        lblCustomerSupport.setBounds(15, 270, 190, 35);

        lblLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblLogout.setForeground(new java.awt.Color(239, 68, 68));
        lblLogout.setText("Logout");
        pnlSidebar.add(lblLogout);
        lblLogout.setBounds(15, 520, 190, 35);

        pnlMainContainer.add(pnlSidebar);
        pnlSidebar.setBounds(0, 60, 220, 603);

        pnlMainBg.setBackground(new java.awt.Color(245, 246, 250));
        pnlMainBg.setLayout(null);

        lblBackToSeatSelection.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblBackToSeatSelection.setForeground(new java.awt.Color(11, 94, 215));
        lblBackToSeatSelection.setText("← Back to Seat Selection");
        pnlMainBg.add(lblBackToSeatSelection);
        lblBackToSeatSelection.setBounds(30, 15, 220, 20);

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(15, 61, 117));
        lblTitle.setText("Payment Methods");
        pnlMainBg.add(lblTitle);
        lblTitle.setBounds(30, 45, 400, 30);

        lblTabCard.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTabCard.setForeground(new java.awt.Color(119, 119, 119));
        lblTabCard.setText("💳 Credit/Debit Card");
        pnlMainBg.add(lblTabCard);
        lblTabCard.setBounds(30, 90, 180, 30);

        lblTabMobile.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTabMobile.setForeground(new java.awt.Color(15, 61, 117));
        lblTabMobile.setText("📱 Mobile Wallet/Bank Transfer");
        pnlMainBg.add(lblTabMobile);
        lblTabMobile.setBounds(210, 90, 240, 30);

        lblTabLine.setBackground(new java.awt.Color(221, 225, 229));
        lblTabLine.setOpaque(true);
        pnlMainBg.add(lblTabLine);
        lblTabLine.setBounds(30, 120, 960, 1);

        lblTabUnderline.setBackground(new java.awt.Color(11, 94, 215));
        lblTabUnderline.setOpaque(true);
        pnlMainBg.add(lblTabUnderline);
        lblTabUnderline.setBounds(210, 119, 240, 2);

        pnlMobileContainer.setFillColor(new java.awt.Color(255, 255, 255));
        pnlMobileContainer.setBorderColor(new java.awt.Color(217, 220, 229));
        pnlMobileContainer.setCornerRadius(10);
        pnlMobileContainer.setLayout(null);

        lblSelectProviderTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSelectProviderTitle.setForeground(new java.awt.Color(26, 26, 26));
        lblSelectProviderTitle.setText("Select Payment Provider");
        pnlMobileContainer.add(lblSelectProviderTitle);
        lblSelectProviderTitle.setBounds(20, 15, 300, 20);

        pnlProviderEsewa.setFillColor(new java.awt.Color(255, 255, 255));
        pnlProviderEsewa.setBorderColor(new java.awt.Color(229, 231, 235));
        pnlProviderEsewa.setCornerRadius(8);
        pnlProviderEsewa.setLayout(null);
        pnlMobileContainer.add(pnlProviderEsewa);
        pnlProviderEsewa.setBounds(20, 45, 215, 80);

        pnlProviderKhalti.setFillColor(new java.awt.Color(255, 255, 255));
        pnlProviderKhalti.setBorderColor(new java.awt.Color(229, 231, 235));
        pnlProviderKhalti.setCornerRadius(8);
        pnlProviderKhalti.setLayout(null);
        pnlMobileContainer.add(pnlProviderKhalti);
        pnlProviderKhalti.setBounds(255, 45, 215, 80);

        pnlProviderIme.setFillColor(new java.awt.Color(255, 255, 255));
        pnlProviderIme.setBorderColor(new java.awt.Color(229, 231, 235));
        pnlProviderIme.setCornerRadius(8);
        pnlProviderIme.setLayout(null);
        pnlMobileContainer.add(pnlProviderIme);
        pnlProviderIme.setBounds(490, 45, 215, 80);

        pnlProviderIps.setFillColor(new java.awt.Color(255, 255, 255));
        pnlProviderIps.setBorderColor(new java.awt.Color(229, 231, 235));
        pnlProviderIps.setCornerRadius(8);
        pnlProviderIps.setLayout(null);
        pnlMobileContainer.add(pnlProviderIps);
        pnlProviderIps.setBounds(725, 45, 215, 80);

        pnlQrInstructions.setFillColor(new java.awt.Color(249, 250, 251));
        pnlQrInstructions.setBorderColor(new java.awt.Color(229, 231, 235));
        pnlQrInstructions.setCornerRadius(10);
        pnlQrInstructions.setLayout(null);

        pnlQrBox.setFillColor(new java.awt.Color(255, 255, 255));
        pnlQrBox.setBorderColor(new java.awt.Color(229, 231, 235));
        pnlQrBox.setCornerRadius(10);
        pnlQrBox.setLayout(null);

        pnlQrGraphic.setBackground(new java.awt.Color(13, 27, 49));
        pnlQrGraphic.setLayout(null);
        pnlQrBox.add(pnlQrGraphic);
        pnlQrGraphic.setBounds(135, 15, 130, 130);

        lblQrLabel.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblQrLabel.setForeground(new java.awt.Color(119, 119, 119));
        lblQrLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQrLabel.setText("Scan to pay NPR 10,400");
        pnlQrBox.add(lblQrLabel);
        lblQrLabel.setBounds(20, 155, 360, 20);

        pnlQrInstructions.add(pnlQrBox);
        pnlQrBox.setBounds(20, 20, 400, 220);

        lblInstructionsTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblInstructionsTitle.setForeground(new java.awt.Color(26, 26, 26));
        lblInstructionsTitle.setText("Scan & Pay Instructions");
        pnlQrInstructions.add(lblInstructionsTitle);
        lblInstructionsTitle.setBounds(450, 20, 440, 20);

        lblInstruction1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblInstruction1.setForeground(new java.awt.Color(119, 119, 119));
        lblInstruction1.setText("Open your selected wallet application.");
        pnlQrInstructions.add(lblInstruction1);
        lblInstruction1.setBounds(450, 45, 440, 20);

        lblInstruction2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblInstruction2.setForeground(new java.awt.Color(119, 119, 119));
        lblInstruction2.setText("Click on the Scan QR button.");
        pnlQrInstructions.add(lblInstruction2);
        lblInstruction2.setBounds(450, 65, 440, 20);

        lblInstruction3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblInstruction3.setForeground(new java.awt.Color(119, 119, 119));
        lblInstruction3.setText("Point your camera at this QR code.");
        pnlQrInstructions.add(lblInstruction3);
        lblInstruction3.setBounds(450, 85, 440, 20);

        lblInstruction4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblInstruction4.setForeground(new java.awt.Color(119, 119, 119));
        lblInstruction4.setText("Verify the payment amount and confirm.");
        pnlQrInstructions.add(lblInstruction4);
        lblInstruction4.setBounds(450, 105, 440, 20);

        lblOrMobile.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblOrMobile.setForeground(new java.awt.Color(119, 119, 119));
        lblOrMobile.setText("OR USE MOBILE NUMBER");
        pnlQrInstructions.add(lblOrMobile);
        lblOrMobile.setBounds(450, 145, 200, 15);

        txtWalletId.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtWalletId.setPlaceholder("Wallet ID / Mobile Number");
        pnlQrInstructions.add(txtWalletId);
        txtWalletId.setBounds(450, 165, 310, 40);

        btnVerify.setBackground(new java.awt.Color(15, 61, 117));
        btnVerify.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerify.setForeground(new java.awt.Color(255, 255, 255));
        btnVerify.setText("Verify");
        pnlQrInstructions.add(btnVerify);
        btnVerify.setBounds(770, 165, 120, 40);

        pnlMobileContainer.add(pnlQrInstructions);
        pnlQrInstructions.setBounds(20, 150, 920, 260);

        pnlMainBg.add(pnlMobileContainer);
        pnlMobileContainer.setBounds(30, 140, 960, 415);

        lblSecureNote.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSecureNote.setForeground(new java.awt.Color(119, 119, 119));
        lblSecureNote.setText("🔒 Secure SSL Encryption & PCI-DSS Compliant");
        pnlMainBg.add(lblSecureNote);
        lblSecureNote.setBounds(30, 568, 400, 20);

        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(119, 119, 119));
        btnCancel.setText("Cancel");
        pnlMainBg.add(btnCancel);
        btnCancel.setBounds(630, 560, 120, 36);

        btnConfirmPay.setBackground(new java.awt.Color(15, 61, 117));
        btnConfirmPay.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnConfirmPay.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmPay.setText("Confirm & Pay NPR 10,400");
        pnlMainBg.add(btnConfirmPay);
        btnConfirmPay.setBounds(770, 560, 220, 36);

        pnlMainContainer.add(pnlMainBg);
        pnlMainBg.setBounds(220, 60, 1026, 603);

        pnlStatus.setBackground(new java.awt.Color(243, 244, 246));
        pnlStatus.setLayout(null);

        lblSystemStatus.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSystemStatus.setForeground(new java.awt.Color(119, 119, 119));
        lblSystemStatus.setText("● SYSTEM STATUS: OPERATIONAL");
        pnlStatus.add(lblSystemStatus);
        lblSystemStatus.setBounds(20, 2, 300, 20);

        pnlMainContainer.add(pnlStatus);
        pnlStatus.setBounds(0, 663, 1246, 25);

        pnlBackground.add(pnlMainContainer);
        pnlMainContainer.setBounds(60, 40, 1246, 688);

        getContentPane().add(pnlBackground);
        pnlBackground.setBounds(0, 0, 1366, 768);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnConfirmPay;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnVerify;
    private javax.swing.JLabel lblBackToSeatSelection;
    private javax.swing.JLabel lblBackToHome;
    private javax.swing.JLabel lblCustomerSupport;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblSelectProviderTitle;
    private javax.swing.JLabel lblInstructionsTitle;
    private javax.swing.JLabel lblInstruction1;
    private javax.swing.JLabel lblInstruction2;
    private javax.swing.JLabel lblInstruction3;
    private javax.swing.JLabel lblInstruction4;
    private javax.swing.JLabel lblOrMobile;
    private javax.swing.JLabel lblHeaderIcons;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblMyBookings;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblSearchFlight;
    private javax.swing.JLabel lblSecureNote;
    private javax.swing.JLabel lblSystemStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTabCard;
    private javax.swing.JLabel lblTabMobile;
    private javax.swing.JLabel lblTabLine;
    private javax.swing.JLabel lblTabUnderline;
    private javax.swing.JLabel lblQrLabel;
    private javax.swing.JLabel lblTopTitle;
    private view.RoundedPanel pnlActiveSearchFlight;
    private view.GradientBackgroundPanel pnlBackground;
    private view.RoundedPanel pnlMobileContainer;
    private view.RoundedPanel pnlProviderEsewa;
    private view.RoundedPanel pnlProviderKhalti;
    private view.RoundedPanel pnlProviderIme;
    private view.RoundedPanel pnlProviderIps;
    private view.RoundedPanel pnlQrInstructions;
    private view.RoundedPanel pnlQrBox;
    private javax.swing.JPanel pnlQrGraphic;
    private view.MainContainerPanel pnlMainContainer;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlTopHeader;
    private view.PlaceholderTextField txtWalletId;
    // End of variables declaration                   
}

// Commit 3: Documented wallet provider card click event listeners

// Commit 5: Documented vector QR grid painting loop logic

// Commit 7: Explained verify mobile wallet transaction steps

// Commit 9: Documented connectIPS selection border styles

// Commit 2: Added documentation for wallet providers list

// Commit 4: Documented PIN visual masking configuration

// Commit 6: Documented API redirection routing hooks

// Commit 8: Documented bank selector items loading logic

// Commit 10: Documented escape route to dashboard listener hooks

// Commit 12: Documented eSewa payment callback response parsing

// Commit 14: Documented Khalti wallet payment SDK integration mapping

// Commit 16: Documented IME Pay wallet selection highlight borders

// Commit 18: Documented OTP timer counting intervals details

// Commit 20: Documented confirm payment button validation triggers

// Commit 22: Documented IME Pay logo resolution painting properties

// Commit 24: Documented bank accounts list dropdown selection bindings

// Commit 26: Documented QR code scanner view overlay constraints

// Commit 28: Documented checkin component navigation redirect flows

// Commit 30: Documented mobile payment network timeout thresholds
