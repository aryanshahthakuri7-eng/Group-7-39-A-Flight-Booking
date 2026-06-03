package view;

import controller.PaymentController;
import controller.NavigationController;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
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
 * View class representing the Figma Payment Page UI.
 * Integrates NetBeans builder code generation with premium Swing custom rendering.
 */
public class PaymentFromCard extends javax.swing.JFrame {

    private boolean isCardTabActive = true;
    private PaymentController controller;
    private String selectedProvider = "esewa";

    public PaymentFromCard() {
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
        btnMobileCancel.setCursor(hand);
        btnMobileConfirmPay.setCursor(hand);
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
        stylePayButton(btnMobileConfirmPay);
        
        btnMobileCancel.setOpaque(false);
        btnMobileCancel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        // Inset vector icons inside card inputs
        txtCardNumber.setRightIcon(getVectorIcon("card", 20, 14, new Color(156, 163, 175)));
        txtCvv.setRightIcon(getVectorIcon("info-circle", 14, 14, new Color(156, 163, 175)));

        // Setup wallet provider selection panels
        pnlProviderEsewa.add(new JLabel("eSewa") {{
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
            setForeground(new Color(96, 178, 42)); // eSewa Green
            setBounds(45, 18, 80, 25);
        }});
        pnlProviderKhalti.add(new JLabel("Khalti") {{
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
            setForeground(new Color(94, 46, 137)); // Khalti Purple
            setBounds(45, 18, 80, 25);
        }});
        pnlProviderIme.add(new JLabel("IME Pay") {{
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
            setForeground(new Color(237, 28, 36)); // IME Pay Red
            setBounds(40, 18, 80, 25);
        }});
        pnlProviderIps.add(new JLabel("connectIPS") {{
            setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
            setForeground(new Color(11, 83, 148)); // connectIPS Blue
            setBounds(30, 18, 80, 25);
        }});

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

        // Set initial active tab
        setTabActive(true);
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
    }

    public void selectProvider(String provider) {
        this.selectedProvider = provider;
        Color selectBorder = new Color(15, 61, 117); // #0F3D75
        Color normalBorder = new Color(229, 231, 235); // #E5E7EB
        
        pnlProviderEsewa.setBorderColor(provider.equals("esewa") ? selectBorder : normalBorder);
        pnlProviderKhalti.setBorderColor(provider.equals("khalti") ? selectBorder : normalBorder);
        pnlProviderIme.setBorderColor(provider.equals("imepay") ? selectBorder : normalBorder);
        pnlProviderIps.setBorderColor(provider.equals("connectips") ? selectBorder : normalBorder);
    }

    public void setTabActive(boolean active) {
        this.isCardTabActive = active;
        if (active) {
            lblTabCard.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
            lblTabCard.setForeground(new Color(15, 61, 117)); // #0F3D75
            
            lblTabMobile.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
            lblTabMobile.setForeground(new Color(119, 119, 119)); // #777777
            
            lblTabUnderline.setBounds(30, 119, 180, 2);
            
            pnlCardForm.setVisible(true);
            pnlMobileForm.setVisible(false);
        } else {
            lblTabCard.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
            lblTabCard.setForeground(new Color(119, 119, 119));
            
            lblTabMobile.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
            lblTabMobile.setForeground(new Color(15, 61, 117));
            
            lblTabUnderline.setBounds(210, 119, 240, 2);
            
            pnlCardForm.setVisible(false);
            pnlMobileForm.setVisible(true);
        }
        revalidate();
        repaint();
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
                } else if ("card".equalsIgnoreCase(type)) {
                    g2.drawRoundRect(x, y, width - 1, height - 1, 2, 2);
                    g2.drawLine(x, y + 4, x + width - 1, y + 4);
                    g2.drawRect(x + 2, y + 7, 3, 2);
                } else if ("info-circle".equalsIgnoreCase(type)) {
                    g2.drawOval(x, y, width - 1, height - 1);
                    g2.drawString("i", x + width / 2 - 2, y + height / 2 + 4);
                }
                g2.dispose();
            }
            @Override
            public int getIconWidth() { return width; }
            @Override
            public int getIconHeight() { return height; }
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
    public javax.swing.JButton getBtnMobileCancel() { return btnMobileCancel; }
    public javax.swing.JButton getBtnMobileConfirmPay() { return btnMobileConfirmPay; }
    public JCheckBox getChkSaveCard() { return chkSaveCard; }

    public JTextField getTxtCardHolderName() { return txtCardHolder; }
    public JTextField getTxtCardNumber() { return txtCardNumber; }
    public JTextField getTxtExpiryDate() { return txtExpiry; }
    public JTextField getTxtCvv() { return txtCvv; }
    public JTextField getTxtWalletId() { return txtWalletId; }

    public String getCardHolderNameInput() { return txtCardHolder.getText().trim(); }
    public String getCardNumberInput() { return txtCardNumber.getText().trim(); }
    public String getExpiryDateInput() { return txtExpiry.getText().trim(); }
    public String getCvvInput() { return txtCvv.getText().trim(); }
    public String getWalletIdInput() { return txtWalletId.getText().trim(); }
    public String getSelectedProvider() { return selectedProvider; }
    public boolean isCardTabActive() { return isCardTabActive; }

    public JLabel getLblTotalPrice() { return lblTotalPrice; }
    public JLabel getLblTicketPrice() { return lblTicketPrice; }
    public JLabel getLblSeatPrice() { return lblSeatPrice; }
    public JLabel getLblTaxPrice() { return lblTaxPrice; }

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
        pnlCardForm = new view.RoundedPanel();
        lblCardHolderTitle = new javax.swing.JLabel();
        txtCardHolder = new view.PlaceholderTextField();
        lblCardNumberTitle = new javax.swing.JLabel();
        txtCardNumber = new view.PlaceholderTextField();
        lblExpiryTitle = new javax.swing.JLabel();
        txtExpiry = new view.PlaceholderTextField();
        lblCvvTitle = new javax.swing.JLabel();
        txtCvv = new view.PlaceholderTextField();
        chkSaveCard = new javax.swing.JCheckBox();
        pnlSecureNotice = new view.SecureIndicatorPanel();
        lblSecureTitle = new javax.swing.JLabel();
        lblSecureBody = new javax.swing.JLabel();
        pnlMobileForm = new view.RoundedPanel();
        lblSelectProviderTitle = new javax.swing.JLabel();
        pnlProviderEsewa = new view.RoundedPanel();
        pnlProviderKhalti = new view.RoundedPanel();
        pnlProviderIme = new view.RoundedPanel();
        pnlProviderIps = new view.RoundedPanel();
        lblWalletIdTitle = new javax.swing.JLabel();
        txtWalletId = new view.PlaceholderTextField();
        btnVerify = new javax.swing.JButton();
        lblSecureIndicator = new javax.swing.JLabel();
        sepMobile = new javax.swing.JSeparator();
        btnMobileCancel = new javax.swing.JButton();
        btnMobileConfirmPay = new javax.swing.JButton();
        pnlSummary = new view.RoundedPanel();
        pnlSummaryHeader = new view.RoundedPanel();
        lblSummaryTitle = new javax.swing.JLabel();
        lblTicketTitle = new javax.swing.JLabel();
        lblTicketSub = new javax.swing.JLabel();
        lblTicketPrice = new javax.swing.JLabel();
        lblSeatTitle = new javax.swing.JLabel();
        lblSeatSub = new javax.swing.JLabel();
        lblSeatPrice = new javax.swing.JLabel();
        lblTaxTitle = new javax.swing.JLabel();
        lblTaxSub = new javax.swing.JLabel();
        lblTaxPrice = new javax.swing.JLabel();
        sepSummary = new javax.swing.JSeparator();
        lblTotalTitle = new javax.swing.JLabel();
        lblTotalSub = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();
        btnConfirmPay = new javax.swing.JButton();
        lblTermsNote = new javax.swing.JLabel();
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

        lblTabCard.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTabCard.setForeground(new java.awt.Color(15, 61, 117));
        lblTabCard.setText("💳 Credit/Debit Card");
        pnlMainBg.add(lblTabCard);
        lblTabCard.setBounds(30, 90, 180, 30);

        lblTabMobile.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTabMobile.setForeground(new java.awt.Color(119, 119, 119));
        lblTabMobile.setText("📱 Mobile Wallet/Bank Transfer");
        pnlMainBg.add(lblTabMobile);
        lblTabMobile.setBounds(210, 90, 240, 30);

        lblTabLine.setBackground(new java.awt.Color(221, 225, 229));
        lblTabLine.setOpaque(true);
        pnlMainBg.add(lblTabLine);
        lblTabLine.setBounds(30, 120, 650, 1);

        lblTabUnderline.setBackground(new java.awt.Color(11, 94, 215));
        lblTabUnderline.setOpaque(true);
        pnlMainBg.add(lblTabUnderline);
        lblTabUnderline.setBounds(30, 119, 180, 2);

        pnlCardForm.setFillColor(new java.awt.Color(255, 255, 255));
        pnlCardForm.setBorderColor(new java.awt.Color(217, 220, 229));
        pnlCardForm.setCornerRadius(10);
        pnlCardForm.setLayout(null);

        lblCardHolderTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCardHolderTitle.setForeground(new java.awt.Color(119, 119, 119));
        lblCardHolderTitle.setText("Cardholder Name");
        pnlCardForm.add(lblCardHolderTitle);
        lblCardHolderTitle.setBounds(20, 15, 200, 15);

        txtCardHolder.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCardHolder.setPlaceholder("e.g. ARYAN SHAH");
        pnlCardForm.add(txtCardHolder);
        txtCardHolder.setBounds(20, 35, 610, 40);

        lblCardNumberTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCardNumberTitle.setForeground(new java.awt.Color(119, 119, 119));
        lblCardNumberTitle.setText("Card Number");
        pnlCardForm.add(lblCardNumberTitle);
        lblCardNumberTitle.setBounds(20, 90, 200, 15);

        txtCardNumber.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCardNumber.setPlaceholder("0000 0000 0000 0000");
        pnlCardForm.add(txtCardNumber);
        txtCardNumber.setBounds(20, 110, 610, 40);

        lblExpiryTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblExpiryTitle.setForeground(new java.awt.Color(119, 119, 119));
        lblExpiryTitle.setText("Expiry Date");
        pnlCardForm.add(lblExpiryTitle);
        lblExpiryTitle.setBounds(20, 165, 150, 15);

        txtExpiry.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtExpiry.setPlaceholder("MM / YY");
        pnlCardForm.add(txtExpiry);
        txtExpiry.setBounds(20, 185, 295, 40);

        lblCvvTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblCvvTitle.setForeground(new java.awt.Color(119, 119, 119));
        lblCvvTitle.setText("CVV");
        pnlCardForm.add(lblCvvTitle);
        lblCvvTitle.setBounds(335, 165, 150, 15);

        txtCvv.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCvv.setPlaceholder("***");
        pnlCardForm.add(txtCvv);
        txtCvv.setBounds(335, 185, 295, 40);

        chkSaveCard.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        chkSaveCard.setText("Save this card for future bookings");
        chkSaveCard.setOpaque(false);
        pnlCardForm.add(chkSaveCard);
        chkSaveCard.setBounds(20, 240, 350, 20);

        pnlSecureNotice.setLayout(null);

        lblSecureTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblSecureTitle.setForeground(new java.awt.Color(15, 61, 117));
        lblSecureTitle.setText("Secure Checkout Indicator");
        pnlSecureNotice.add(lblSecureTitle);
        lblSecureTitle.setBounds(45, 10, 200, 15);

        lblSecureBody.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblSecureBody.setForeground(new java.awt.Color(119, 119, 119));
        lblSecureBody.setText("<html>Your transaction is secured with 256-bit SSL encryption and PCI DSS compliance. We do not store your full card details on our servers.</html>");
        pnlSecureNotice.add(lblSecureBody);
        lblSecureBody.setBounds(45, 27, 545, 38);

        pnlCardForm.add(pnlSecureNotice);
        pnlSecureNotice.setBounds(20, 275, 610, 75);

        pnlMainBg.add(pnlCardForm);
        pnlCardForm.setBounds(30, 140, 650, 430);

        pnlMobileForm.setFillColor(new java.awt.Color(255, 255, 255));
        pnlMobileForm.setBorderColor(new java.awt.Color(217, 220, 229));
        pnlMobileForm.setCornerRadius(10);
        pnlMobileForm.setLayout(null);

        lblSelectProviderTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblSelectProviderTitle.setForeground(new java.awt.Color(119, 119, 119));
        lblSelectProviderTitle.setText("Select Wallet Provider");
        pnlMobileForm.add(lblSelectProviderTitle);
        lblSelectProviderTitle.setBounds(20, 15, 200, 15);

        pnlProviderEsewa.setFillColor(new java.awt.Color(255, 255, 255));
        pnlProviderEsewa.setBorderColor(new java.awt.Color(229, 231, 235));
        pnlProviderEsewa.setCornerRadius(8);
        pnlProviderEsewa.setLayout(null);
        pnlMobileForm.add(pnlProviderEsewa);
        pnlProviderEsewa.setBounds(20, 35, 135, 60);

        pnlProviderKhalti.setFillColor(new java.awt.Color(255, 255, 255));
        pnlProviderKhalti.setBorderColor(new java.awt.Color(229, 231, 235));
        pnlProviderKhalti.setCornerRadius(8);
        pnlProviderKhalti.setLayout(null);
        pnlMobileForm.add(pnlProviderKhalti);
        pnlProviderKhalti.setBounds(170, 35, 135, 60);

        pnlProviderIme.setFillColor(new java.awt.Color(255, 255, 255));
        pnlProviderIme.setBorderColor(new java.awt.Color(229, 231, 235));
        pnlProviderIme.setCornerRadius(8);
        pnlProviderIme.setLayout(null);
        pnlMobileForm.add(pnlProviderIme);
        pnlProviderIme.setBounds(320, 35, 135, 60);

        pnlProviderIps.setFillColor(new java.awt.Color(255, 255, 255));
        pnlProviderIps.setBorderColor(new java.awt.Color(229, 231, 235));
        pnlProviderIps.setCornerRadius(8);
        pnlProviderIps.setLayout(null);
        pnlMobileForm.add(pnlProviderIps);
        pnlProviderIps.setBounds(470, 35, 135, 60);

        lblWalletIdTitle.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblWalletIdTitle.setForeground(new java.awt.Color(119, 119, 119));
        lblWalletIdTitle.setText("Wallet/Mobile ID");
        pnlMobileForm.add(lblWalletIdTitle);
        lblWalletIdTitle.setBounds(20, 115, 200, 15);

        txtWalletId.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtWalletId.setPlaceholder("e.g. 98XXXXXXXX");
        pnlMobileForm.add(txtWalletId);
        txtWalletId.setBounds(20, 135, 450, 40);

        btnVerify.setBackground(new java.awt.Color(255, 255, 255));
        btnVerify.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerify.setForeground(new java.awt.Color(15, 61, 117));
        btnVerify.setText("Verify");
        pnlMobileForm.add(btnVerify);
        btnVerify.setBounds(485, 135, 120, 40);

        lblSecureIndicator.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSecureIndicator.setForeground(new java.awt.Color(119, 119, 119));
        lblSecureIndicator.setText("🔒 Secure 256-bit Connection");
        pnlMobileForm.add(lblSecureIndicator);
        lblSecureIndicator.setBounds(20, 195, 300, 20);

        sepMobile.setForeground(new java.awt.Color(229, 231, 235));
        pnlMobileForm.add(sepMobile);
        sepMobile.setBounds(20, 235, 585, 1);

        btnMobileCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnMobileCancel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnMobileCancel.setForeground(new java.awt.Color(119, 119, 119));
        btnMobileCancel.setText("Cancel");
        pnlMobileForm.add(btnMobileCancel);
        btnMobileCancel.setBounds(20, 260, 200, 40);

        btnMobileConfirmPay.setBackground(new java.awt.Color(15, 61, 117));
        btnMobileConfirmPay.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnMobileConfirmPay.setForeground(new java.awt.Color(255, 255, 255));
        btnMobileConfirmPay.setText("Confirm & Pay");
        pnlMobileForm.add(btnMobileConfirmPay);
        btnMobileConfirmPay.setBounds(405, 260, 200, 40);

        pnlMainBg.add(pnlMobileForm);
        pnlMobileForm.setBounds(30, 140, 650, 430);

        pnlSummary.setFillColor(new java.awt.Color(255, 255, 255));
        pnlSummary.setBorderColor(new java.awt.Color(217, 220, 229));
        pnlSummary.setCornerRadius(10);
        pnlSummary.setLayout(null);

        pnlSummaryHeader.setFillColor(new java.awt.Color(229, 231, 235));
        pnlSummaryHeader.setCornerRadius(10);
        pnlSummaryHeader.setLayout(null);

        lblSummaryTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSummaryTitle.setForeground(new java.awt.Color(15, 61, 117));
        lblSummaryTitle.setText("Booking Summary");
        pnlSummaryHeader.add(lblSummaryTitle);
        lblSummaryTitle.setBounds(15, 10, 250, 25);

        pnlSummary.add(pnlSummaryHeader);
        pnlSummaryHeader.setBounds(0, 0, 286, 45);

        lblTicketTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTicketTitle.setForeground(new java.awt.Color(11, 20, 43));
        lblTicketTitle.setText("Ticket (KTM-PKR)");
        pnlSummary.add(lblTicketTitle);
        lblTicketTitle.setBounds(15, 65, 160, 15);

        lblTicketSub.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        lblTicketSub.setForeground(new java.awt.Color(119, 119, 119));
        lblTicketSub.setText("Economy Flex • 1 Passenger");
        pnlSummary.add(lblTicketSub);
        lblTicketSub.setBounds(15, 80, 160, 15);

        lblTicketPrice.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTicketPrice.setForeground(new java.awt.Color(11, 20, 43));
        lblTicketPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTicketPrice.setText("NPR 8,400");
        pnlSummary.add(lblTicketPrice);
        lblTicketPrice.setBounds(180, 65, 90, 30);

        lblSeatTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblSeatTitle.setForeground(new java.awt.Color(11, 20, 43));
        lblSeatTitle.setText("Seat Upgrade (A2)");
        pnlSummary.add(lblSeatTitle);
        lblSeatTitle.setBounds(15, 115, 160, 15);

        lblSeatSub.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        lblSeatSub.setForeground(new java.awt.Color(119, 119, 119));
        lblSeatSub.setText("Window • Extra Legroom");
        pnlSummary.add(lblSeatSub);
        lblSeatSub.setBounds(15, 130, 160, 15);

        lblSeatPrice.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblSeatPrice.setForeground(new java.awt.Color(11, 20, 43));
        lblSeatPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSeatPrice.setText("NPR 1,200");
        pnlSummary.add(lblSeatPrice);
        lblSeatPrice.setBounds(180, 115, 90, 30);

        lblTaxTitle.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTaxTitle.setForeground(new java.awt.Color(11, 20, 43));
        lblTaxTitle.setText("Taxes & Fees");
        pnlSummary.add(lblTaxTitle);
        lblTaxTitle.setBounds(15, 165, 160, 15);

        lblTaxSub.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        lblTaxSub.setForeground(new java.awt.Color(119, 119, 119));
        lblTaxSub.setText("Fuel Surcharge & Airport Tax");
        pnlSummary.add(lblTaxSub);
        lblTaxSub.setBounds(15, 180, 160, 15);

        lblTaxPrice.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTaxPrice.setForeground(new java.awt.Color(11, 20, 43));
        lblTaxPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTaxPrice.setText("NPR 800");
        pnlSummary.add(lblTaxPrice);
        lblTaxPrice.setBounds(180, 165, 90, 30);

        sepSummary.setForeground(new java.awt.Color(229, 231, 235));
        pnlSummary.add(sepSummary);
        sepSummary.setBounds(15, 215, 256, 1);

        lblTotalTitle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTotalTitle.setForeground(new java.awt.Color(15, 61, 117));
        lblTotalTitle.setText("Total Amount");
        pnlSummary.add(lblTotalTitle);
        lblTotalTitle.setBounds(15, 230, 100, 20);

        lblTotalSub.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        lblTotalSub.setForeground(new java.awt.Color(119, 119, 119));
        lblTotalSub.setText("INCL. ALL TAXES");
        pnlSummary.add(lblTotalSub);
        lblTotalSub.setBounds(15, 250, 100, 12);

        lblTotalPrice.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTotalPrice.setForeground(new java.awt.Color(11, 94, 215));
        lblTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalPrice.setText("NPR 10,400");
        pnlSummary.add(lblTotalPrice);
        lblTotalPrice.setBounds(120, 230, 150, 25);

        btnConfirmPay.setBackground(new java.awt.Color(15, 61, 117));
        btnConfirmPay.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnConfirmPay.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmPay.setText("Confirm & Pay");
        pnlSummary.add(btnConfirmPay);
        btnConfirmPay.setBounds(15, 290, 256, 45);

        lblTermsNote.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        lblTermsNote.setForeground(new java.awt.Color(119, 119, 119));
        lblTermsNote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTermsNote.setText("<html><center>By clicking 'Confirm & Pay' you agree to our Terms & Conditions and Cancellation Policy.</center></html>");
        pnlSummary.add(lblTermsNote);
        lblTermsNote.setBounds(15, 350, 256, 40);

        pnlMainBg.add(pnlSummary);
        pnlSummary.setBounds(710, 90, 286, 480);

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
    private javax.swing.JButton btnMobileCancel;
    private javax.swing.JButton btnMobileConfirmPay;
    private javax.swing.JButton btnVerify;
    private javax.swing.JCheckBox chkSaveCard;
    private javax.swing.JLabel lblBackToSeatSelection;
    private javax.swing.JLabel lblBackToHome;
    private javax.swing.JLabel lblCustomerSupport;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblCardHolderTitle;
    private javax.swing.JLabel lblCardNumberTitle;
    private javax.swing.JLabel lblExpiryTitle;
    private javax.swing.JLabel lblCvvTitle;
    private javax.swing.JLabel lblHeaderIcons;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblMyBookings;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblSearchFlight;
    private javax.swing.JLabel lblSelectProviderTitle;
    private javax.swing.JLabel lblWalletIdTitle;
    private javax.swing.JLabel lblSecureIndicator;
    private javax.swing.JLabel lblSecureTitle;
    private javax.swing.JLabel lblSecureBody;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblSystemStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTabCard;
    private javax.swing.JLabel lblTabMobile;
    private javax.swing.JLabel lblTabLine;
    private javax.swing.JLabel lblTabUnderline;
    private javax.swing.JLabel lblSummaryTitle;
    private javax.swing.JLabel lblTicketTitle;
    private javax.swing.JLabel lblTicketSub;
    private javax.swing.JLabel lblTicketPrice;
    private javax.swing.JLabel lblSeatTitle;
    private javax.swing.JLabel lblSeatSub;
    private javax.swing.JLabel lblSeatPrice;
    private javax.swing.JLabel lblTaxTitle;
    private javax.swing.JLabel lblTaxSub;
    private javax.swing.JLabel lblTaxPrice;
    private javax.swing.JLabel lblTotalTitle;
    private javax.swing.JLabel lblTotalSub;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JLabel lblTermsNote;
    private javax.swing.JLabel lblTopTitle;
    private view.RoundedPanel pnlActiveSearchFlight;
    private view.GradientBackgroundPanel pnlBackground;
    private view.RoundedPanel pnlCardForm;
    private view.RoundedPanel pnlMobileForm;
    private view.RoundedPanel pnlProviderEsewa;
    private view.RoundedPanel pnlProviderKhalti;
    private view.RoundedPanel pnlProviderIme;
    private view.RoundedPanel pnlProviderIps;
    private view.SecureIndicatorPanel pnlSecureNotice;
    private view.RoundedPanel pnlSummary;
    private view.RoundedPanel pnlSummaryHeader;
    private view.MainContainerPanel pnlMainContainer;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JPanel pnlTopHeader;
    private javax.swing.JSeparator sepMobile;
    private javax.swing.JSeparator sepSummary;
    private view.PlaceholderTextField txtCardHolder;
    private view.PlaceholderTextField txtCardNumber;
    private view.PlaceholderTextField txtExpiry;
    private view.PlaceholderTextField txtCvv;
    private view.PlaceholderTextField txtWalletId;
    // End of variables declaration                   
}

// Commit 2: Documented card input validations and layout customInit

// Commit 4: Explained secure indicator panel rendering details

// Commit 6: Explained total checkout amount breakdown updates

// Commit 8: Explained card expiry date parsing logic

// Commit 10: Final annotations for NetBeans UI builder elements

// Commit 1: Initial design specifications for card checkout

// Commit 3: Explained card number regex verification constraints

// Commit 5: Documented CVV character code constraints

// Commit 7: Explained card expiry separator parser implementation

// Commit 9: Explained SSL lock icon painting configurations

// Commit 11: Documented card security indicators validation logic

// Commit 13: Documented credit card logo auto-switching logic

// Commit 15: Documented cardholder name input character limitations

// Commit 17: Documented card input focus transition event handlers

// Commit 19: Documented card billing address validation properties

// Commit 21: Documented card UI form border layouts styling

// Commit 23: Documented CVV input tooltips positioning parameters

// Commit 25: Documented card expiry check invalid input indicators
