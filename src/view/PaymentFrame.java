package view;

import controller.PaymentController;
import controller.NavigationController;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;

/**
 * View class representing the Figma Payment Page UI.
 * Integrates NetBeans builder code generation with premium Swing custom rendering.
 */
public class PaymentFrame extends javax.swing.JFrame {

    private boolean isCardTabActive = true;
    private PaymentController controller;

    // Field Placeholders
    private static final String HOLDER_PLACEHOLDER = "e.g. ARYAN SHAH";
    private static final String NUMBER_PLACEHOLDER = "0000 0000 0000 0000";
    private static final String EXPIRY_PLACEHOLDER = "MM / YY";
    private static final String CVV_PLACEHOLDER = "***";
    private static final String WALLET_PLACEHOLDER = "e.g. 98XXXXXXXX";

    public PaymentFrame() {
        initComponents();
        customInit();
        // The Controller binds itself on construction
        this.controller = new PaymentController(this);
    }

    private void customInit() {
        // Frame setup
        setSize(1366, 768);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 246, 250)); // #F5F6FA

        // Sidebar active item styling (#2B0A0A background with 8px radius)
        pnlSidebar.setOpaque(true);
        pnlSidebar.setBackground(Color.WHITE);
        pnlSidebar.setBorder(new Border() {
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Active item rounded background (behind Search Flight label)
                g2.setColor(new Color(43, 10, 10)); // #2B0A0A
                g2.fillRoundRect(15, 170, 210, 40, 8, 8);
                // Sidebar separator/border
                g2.setColor(new Color(217, 220, 229)); // #D9DCE5
                g2.drawLine(width - 1, 0, width - 1, height);
                g2.dispose();
            }
            @Override
            public Insets getBorderInsets(java.awt.Component c) {
                return new Insets(0, 0, 0, 1);
            }
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        // Set padding inside sidebar labels
        lblDashboard.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        lblSearchFlight.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        lblMyBookings.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        lblProfile.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        lblCustomerSupport.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        lblLogout.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        // Cursors for navigation labels
        Cursor hand = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        lblDashboard.setCursor(hand);
        lblSearchFlight.setCursor(hand);
        lblMyBookings.setCursor(hand);
        lblProfile.setCursor(hand);
        lblCustomerSupport.setCursor(hand);
        lblLogout.setCursor(hand);
        lblBackSeat.setCursor(hand);
        lblTabCard.setCursor(hand);
        lblTabMobile.setCursor(hand);

        // Sidebar click handlers
        lblDashboard.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToDashboard(PaymentFrame.this); }
        });
        lblSearchFlight.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToSearchFlight(PaymentFrame.this); }
        });
        lblMyBookings.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToMyBookings(PaymentFrame.this); }
        });
        lblProfile.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToProfile(PaymentFrame.this); }
        });
        lblCustomerSupport.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToCustomerSupport(PaymentFrame.this); }
        });
        lblLogout.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.logout(PaymentFrame.this); }
        });
        lblBackSeat.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToSearchFlight(PaymentFrame.this); }
        });

        // Top Header styling (Border #D9DCE5 at bottom)
        pnlTopHeader.setBorder(new Border() {
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(new Color(217, 220, 229)); // #D9DCE5
                g.drawLine(0, height - 1, width, height - 1);
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(0, 0, 1, 0); }
            @Override public boolean isBorderOpaque() { return true; }
        });

        // Main panels styling (Rounded corners, Border #D9DCE5)
        pnlPaymentMethods.setOpaque(false);
        pnlPaymentMethods.setBorder(new RoundedCardBorder(10, new Color(217, 220, 229), Color.WHITE));

        pnlBookingSummary.setOpaque(false);
        pnlBookingSummary.setBorder(new RoundedCardBorder(10, new Color(217, 220, 229), Color.WHITE));

        // Tab Line Underline Border
        pnlTabs.setBorder(new Border() {
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(15, 61, 117)); // #0F3D75
                if (isCardTabActive) {
                    g2.fillRect(10, height - 3, 160, 3);
                } else {
                    g2.fillRect(230, height - 3, 230, 3);
                }
                g2.dispose();
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(0, 0, 3, 0); }
            @Override public boolean isBorderOpaque() { return false; }
        });

        // Secure indicators styling
        pnlSecureBox.setOpaque(false);
        pnlSecureBox.setBorder(new RoundedCardBorder(8, new Color(217, 230, 245), new Color(247, 250, 253))); // #D9E6F5 / #F7FAFD

        // Input Fields rounded border and placeholder wiring
        styleTextField(txtCardHolderName, HOLDER_PLACEHOLDER);
        styleTextField(txtCardNumber, NUMBER_PLACEHOLDER);
        styleTextField(txtExpiryDate, EXPIRY_PLACEHOLDER);
        styleTextField(txtCvv, CVV_PLACEHOLDER);
        styleTextField(txtWalletId, WALLET_PLACEHOLDER);

        // Styling ComboBox
        cmbMobileWallet.setBorder(BorderFactory.createCompoundBorder(
            new RoundedFieldBorder(8, new Color(217, 220, 229)),
            BorderFactory.createEmptyBorder(2, 5, 2, 5)
        ));
        cmbMobileWallet.setBackground(Color.WHITE);

        // Confirm button custom style
        btnConfirmPay.setOpaque(false);
        btnConfirmPay.setCursor(hand);
        btnConfirmPay.setBorder(new Border() {
            private final Color hoverCol = new Color(10, 46, 89);  // #0A2E59
            private final Color normalCol = new Color(15, 61, 117); // #0F3D75
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (btnConfirmPay.getModel().isRollover()) {
                    g2.setColor(hoverCol);
                } else {
                    g2.setColor(normalCol);
                }
                g2.fillRoundRect(x, y, width, height, 8, 8);
                g2.dispose();
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(1, 1, 1, 1); }
            @Override public boolean isBorderOpaque() { return false; }
        });

        // Bottom center Progress indicator line rounded
        lblProgressLine.setBorder(new Border() {
            @Override
            public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(51, 51, 51)); // Dark Gray
                g2.fillRoundRect(x, y, width, height, height, height);
                g2.dispose();
            }
            @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(0, 0, 0, 0); }
            @Override public boolean isBorderOpaque() { return true; }
        });

        // Initialize state view (Card tab active)
        setTabActive(true);
    }

    private void styleTextField(final javax.swing.JTextField txt, final String placeholder) {
        txt.setForeground(Color.GRAY);
        txt.setBorder(BorderFactory.createCompoundBorder(
            new RoundedFieldBorder(8, new Color(217, 220, 229)),
            BorderFactory.createEmptyBorder(5, 12, 5, 40) // padding (large right padding to prevent icon overlap)
        ));
        
        txt.addFocusListener(new FocusListener() {
            private final Color focusColor = new Color(15, 61, 117); // #0F3D75
            private final Color normalColor = new Color(217, 220, 229); // #D9DCE5

            @Override
            public void focusGained(FocusEvent e) {
                txt.setBorder(BorderFactory.createCompoundBorder(
                    new RoundedFieldBorder(8, focusColor, 1.5f),
                    BorderFactory.createEmptyBorder(5, 12, 5, 40)
                ));
                if (txt.getText().equals(placeholder)) {
                    txt.setText("");
                    txt.setForeground(new Color(26, 26, 26)); // Dark Text
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                txt.setBorder(BorderFactory.createCompoundBorder(
                    new RoundedFieldBorder(8, normalColor),
                    BorderFactory.createEmptyBorder(5, 12, 5, 40)
                ));
                if (txt.getText().trim().isEmpty()) {
                    txt.setText(placeholder);
                    txt.setForeground(Color.GRAY);
                }
            }
        });
    }

    public void setTabActive(boolean cardActive) {
        this.isCardTabActive = cardActive;

        // Toggle text styles
        if (cardActive) {
            lblTabCard.setFont(new java.awt.Font("Segoe UI", 1, 14));
            lblTabCard.setForeground(new Color(15, 61, 117)); // #0F3D75
            lblTabMobile.setFont(new java.awt.Font("Segoe UI", 0, 14));
            lblTabMobile.setForeground(Color.GRAY);
        } else {
            lblTabCard.setFont(new java.awt.Font("Segoe UI", 0, 14));
            lblTabCard.setForeground(Color.GRAY);
            lblTabMobile.setFont(new java.awt.Font("Segoe UI", 1, 14));
            lblTabMobile.setForeground(new Color(15, 61, 117)); // #0F3D75
        }

        // Toggle visibility of card form fields
        lblCardHolderName.setVisible(cardActive);
        txtCardHolderName.setVisible(cardActive);
        lblCardNumber.setVisible(cardActive);
        txtCardNumber.setVisible(cardActive);
        lblCardIcon.setVisible(cardActive);
        lblExpiryDate.setVisible(cardActive);
        txtExpiryDate.setVisible(cardActive);
        lblCvv.setVisible(cardActive);
        txtCvv.setVisible(cardActive);
        lblInfoIcon.setVisible(cardActive);
        chkSaveCard.setVisible(cardActive);

        // Toggle visibility of mobile wallet components
        lblMobileWalletTitle.setVisible(!cardActive);
        cmbMobileWallet.setVisible(!cardActive);
        lblWalletId.setVisible(!cardActive);
        txtWalletId.setVisible(!cardActive);

        // Repaint components
        pnlTabs.repaint();
        pnlPaymentMethods.repaint();
    }

    // Input values helper (returning empty string if user left placeholder unmodified)
    public String getCardHolderNameInput() {
        String val = txtCardHolderName.getText().trim();
        return val.equals(HOLDER_PLACEHOLDER) ? "" : val;
    }

    public String getCardNumberInput() {
        String val = txtCardNumber.getText().trim();
        return val.equals(NUMBER_PLACEHOLDER) ? "" : val;
    }

    public String getExpiryDateInput() {
        String val = txtExpiryDate.getText().trim();
        return val.equals(EXPIRY_PLACEHOLDER) ? "" : val;
    }

    public String getCvvInput() {
        String val = txtCvv.getText().trim();
        return val.equals(CVV_PLACEHOLDER) ? "" : val;
    }

    public String getWalletIdInput() {
        String val = txtWalletId.getText().trim();
        return val.equals(WALLET_PLACEHOLDER) ? "" : val;
    }

    // Getters for controller binding
    public javax.swing.JTextField getTxtCardHolderName() { return txtCardHolderName; }
    public javax.swing.JTextField getTxtCardNumber() { return txtCardNumber; }
    public javax.swing.JTextField getTxtExpiryDate() { return txtExpiryDate; }
    public javax.swing.JTextField getTxtCvv() { return txtCvv; }
    public javax.swing.JCheckBox getChkSaveCard() { return chkSaveCard; }
    public javax.swing.JButton getBtnConfirmPay() { return btnConfirmPay; }
    public javax.swing.JLabel getLblBackSeat() { return lblBackSeat; }
    public javax.swing.JLabel getLblTabCard() { return lblTabCard; }
    public javax.swing.JLabel getLblTabMobile() { return lblTabMobile; }
    public javax.swing.JComboBox<String> getCmbMobileWallet() { return cmbMobileWallet; }
    public javax.swing.JTextField getTxtWalletId() { return txtWalletId; }
    public boolean isCardTabActive() { return isCardTabActive; }

    // Sidebar Getters
    public javax.swing.JLabel getLblDashboard() { return lblDashboard; }
    public javax.swing.JLabel getLblSearchFlight() { return lblSearchFlight; }
    public javax.swing.JLabel getLblMyBookings() { return lblMyBookings; }
    public javax.swing.JLabel getLblProfile() { return lblProfile; }
    public javax.swing.JLabel getLblCustomerSupport() { return lblCustomerSupport; }
    public javax.swing.JLabel getLblLogout() { return lblLogout; }

    // Custom Border Helpers
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
            // Paint filled rounded rect background
            g2.setColor(fillCol);
            g2.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
            // Draw rounded outline border
            g2.setColor(borderCol);
            g2.setStroke(new java.awt.BasicStroke(1.0f));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(radius / 2, radius / 2, radius / 2, radius / 2); }
        @Override public boolean isBorderOpaque() { return false; }
    }

    private static class RoundedFieldBorder implements Border {
        private final int radius;
        private final Color color;
        private final float strokeWidth;

        public RoundedFieldBorder(int radius, Color color) {
            this(radius, color, 1.0f);
        }

        public RoundedFieldBorder(int radius, Color color, float strokeWidth) {
            this.radius = radius;
            this.color = color;
            this.strokeWidth = strokeWidth;
        }

        @Override
        public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new java.awt.BasicStroke(strokeWidth));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override public Insets getBorderInsets(java.awt.Component c) { return new Insets(radius / 2, radius / 2, radius / 2, radius / 2); }
        @Override public boolean isBorderOpaque() { return false; }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        lblLogout = new javax.swing.JLabel();
        pnlTopHeader = new javax.swing.JPanel();
        lblBackSeat = new javax.swing.JLabel();
        lblTopTitle = new javax.swing.JLabel();
        lblTopIcons = new javax.swing.JLabel();
        pnlMainBg = new javax.swing.JPanel();
        pnlPaymentMethods = new javax.swing.JPanel();
        lblPaymentTitle = new javax.swing.JLabel();
        pnlTabs = new javax.swing.JPanel();
        lblTabCard = new javax.swing.JLabel();
        lblTabMobile = new javax.swing.JLabel();
        sepTabs = new javax.swing.JSeparator();
        lblCardHolderName = new javax.swing.JLabel();
        txtCardHolderName = new javax.swing.JTextField();
        lblCardNumber = new javax.swing.JLabel();
        txtCardNumber = new javax.swing.JTextField();
        lblCardIcon = new javax.swing.JLabel();
        lblExpiryDate = new javax.swing.JLabel();
        txtExpiryDate = new javax.swing.JTextField();
        lblCvv = new javax.swing.JLabel();
        txtCvv = new javax.swing.JTextField();
        lblInfoIcon = new javax.swing.JLabel();
        chkSaveCard = new javax.swing.JCheckBox();
        lblMobileWalletTitle = new javax.swing.JLabel();
        cmbMobileWallet = new javax.swing.JComboBox();
        lblWalletId = new javax.swing.JLabel();
        txtWalletId = new javax.swing.JTextField();
        pnlSecureBox = new javax.swing.JPanel();
        lblShieldIcon = new javax.swing.JLabel();
        lblSecureTitle = new javax.swing.JLabel();
        lblSecureDesc = new javax.swing.JLabel();
        pnlBookingSummary = new javax.swing.JPanel();
        lblSummaryTitle = new javax.swing.JLabel();
        lblTicketLabel = new javax.swing.JLabel();
        lblTicketValue = new javax.swing.JLabel();
        lblTicketDesc = new javax.swing.JLabel();
        lblSeatLabel = new javax.swing.JLabel();
        lblSeatValue = new javax.swing.JLabel();
        lblSeatDesc = new javax.swing.JLabel();
        lblTaxesLabel = new javax.swing.JLabel();
        lblTaxesValue = new javax.swing.JLabel();
        lblTaxesDesc = new javax.swing.JLabel();
        sepSummary = new javax.swing.JSeparator();
        lblTotalLabel = new javax.swing.JLabel();
        lblTotalValue = new javax.swing.JLabel();
        lblInclTaxes = new javax.swing.JLabel();
        btnConfirmPay = new javax.swing.JButton();
        lblTerms = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblProgressLine = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Yatra Air Sewa - Payment");
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

        lblLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblLogout.setForeground(new java.awt.Color(211, 47, 47));
        lblLogout.setText("🚪  Logout");
        pnlSidebar.add(lblLogout);
        lblLogout.setBounds(15, 650, 210, 40);

        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 240, 768);

        pnlTopHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlTopHeader.setLayout(null);

        lblBackSeat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBackSeat.setForeground(new java.awt.Color(0, 102, 204));
        lblBackSeat.setText("←  Back to Seat Selection");
        pnlTopHeader.add(lblBackSeat);
        lblBackSeat.setBounds(30, 25, 220, 20);

        lblTopTitle.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTopTitle.setForeground(new java.awt.Color(26, 26, 26));
        lblTopTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTopTitle.setText("YATRA AIR SEWA");
        pnlTopHeader.add(lblTopTitle);
        lblTopTitle.setBounds(438, 22, 250, 25);

        lblTopIcons.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTopIcons.setForeground(new java.awt.Color(51, 51, 51));
        lblTopIcons.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTopIcons.setText("🔔   ❓   ⚙️");
        pnlTopHeader.add(lblTopIcons);
        lblTopIcons.setBounds(980, 22, 110, 25);

        getContentPane().add(pnlTopHeader);
        pnlTopHeader.setBounds(240, 0, 1126, 70);

        pnlMainBg.setBackground(new java.awt.Color(245, 246, 250));
        pnlMainBg.setLayout(null);

        pnlPaymentMethods.setBackground(new java.awt.Color(255, 255, 255));
        pnlPaymentMethods.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlPaymentMethods.setLayout(null);

        lblPaymentTitle.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblPaymentTitle.setForeground(new java.awt.Color(15, 61, 117));
        lblPaymentTitle.setText("💳  Payment Methods");
        pnlPaymentMethods.add(lblPaymentTitle);
        lblPaymentTitle.setBounds(20, 20, 350, 40);

        pnlTabs.setBackground(new java.awt.Color(255, 255, 255));
        pnlTabs.setLayout(null);

        lblTabCard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTabCard.setForeground(new java.awt.Color(15, 61, 117));
        lblTabCard.setText("💳  Credit/Debit Card");
        pnlTabs.add(lblTabCard);
        lblTabCard.setBounds(10, 5, 200, 30);

        lblTabMobile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTabMobile.setForeground(new java.awt.Color(128, 128, 128));
        lblTabMobile.setText("📱  Mobile Wallet / Bank Transfer");
        pnlTabs.add(lblTabMobile);
        lblTabMobile.setBounds(230, 5, 280, 30);

        pnlPaymentMethods.add(pnlTabs);
        pnlTabs.setBounds(20, 70, 680, 40);
        pnlPaymentMethods.add(sepTabs);
        sepTabs.setBounds(20, 112, 680, 5);

        lblCardHolderName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCardHolderName.setForeground(new java.awt.Color(74, 85, 104));
        lblCardHolderName.setText("Cardholder Name");
        pnlPaymentMethods.add(lblCardHolderName);
        lblCardHolderName.setBounds(20, 130, 200, 20);

        txtCardHolderName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCardHolderName.setText("e.g. ARYAN SHAH");
        pnlPaymentMethods.add(txtCardHolderName);
        txtCardHolderName.setBounds(20, 155, 680, 45);

        lblCardNumber.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCardNumber.setForeground(new java.awt.Color(74, 85, 104));
        lblCardNumber.setText("Card Number");
        pnlPaymentMethods.add(lblCardNumber);
        lblCardNumber.setBounds(20, 215, 200, 20);

        txtCardNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCardNumber.setText("0000 0000 0000 0000");
        pnlPaymentMethods.add(txtCardNumber);
        txtCardNumber.setBounds(20, 240, 680, 45);

        lblCardIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCardIcon.setText("💳");
        pnlPaymentMethods.add(lblCardIcon);
        lblCardIcon.setBounds(665, 250, 24, 24);

        lblExpiryDate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblExpiryDate.setForeground(new java.awt.Color(74, 85, 104));
        lblExpiryDate.setText("Expiry Date");
        pnlPaymentMethods.add(lblExpiryDate);
        lblExpiryDate.setBounds(20, 300, 200, 20);

        txtExpiryDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtExpiryDate.setText("MM / YY");
        pnlPaymentMethods.add(txtExpiryDate);
        txtExpiryDate.setBounds(20, 325, 330, 45);

        lblCvv.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCvv.setForeground(new java.awt.Color(74, 85, 104));
        lblCvv.setText("CVV");
        pnlPaymentMethods.add(lblCvv);
        lblCvv.setBounds(370, 300, 200, 20);

        txtCvv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCvv.setText("***");
        pnlPaymentMethods.add(txtCvv);
        txtCvv.setBounds(370, 325, 330, 45);

        lblInfoIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfoIcon.setText("ⓘ");
        pnlPaymentMethods.add(lblInfoIcon);
        lblInfoIcon.setBounds(665, 335, 24, 24);

        chkSaveCard.setForeground(new java.awt.Color(51, 51, 51));
        chkSaveCard.setText("Save this card for future bookings");
        pnlPaymentMethods.add(chkSaveCard);
        chkSaveCard.setBounds(20, 385, 350, 25);

        lblMobileWalletTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblMobileWalletTitle.setForeground(new java.awt.Color(74, 85, 104));
        lblMobileWalletTitle.setText("Select Mobile Wallet or Bank");
        pnlPaymentMethods.add(lblMobileWalletTitle);
        lblMobileWalletTitle.setBounds(20, 130, 250, 20);

        cmbMobileWallet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlPaymentMethods.add(cmbMobileWallet);
        cmbMobileWallet.setBounds(20, 155, 680, 45);

        lblWalletId.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblWalletId.setForeground(new java.awt.Color(74, 85, 104));
        lblWalletId.setText("Wallet ID / Mobile Number / Account Number");
        pnlPaymentMethods.add(lblWalletId);
        lblWalletId.setBounds(20, 215, 350, 20);

        txtWalletId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtWalletId.setText("e.g. 98XXXXXXXX");
        pnlPaymentMethods.add(txtWalletId);
        txtWalletId.setBounds(20, 240, 680, 45);

        pnlSecureBox.setBackground(new java.awt.Color(247, 250, 253));
        pnlSecureBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlSecureBox.setLayout(null);

        lblShieldIcon.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblShieldIcon.setForeground(new java.awt.Color(15, 61, 117));
        lblShieldIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShieldIcon.setText("🛡️");
        pnlSecureBox.add(lblShieldIcon);
        lblShieldIcon.setBounds(15, 15, 30, 30);

        lblSecureTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSecureTitle.setForeground(new java.awt.Color(15, 61, 117));
        lblSecureTitle.setText("Secure Checkout Indicator");
        pnlSecureBox.add(lblSecureTitle);
        lblSecureTitle.setBounds(50, 15, 250, 20);

        lblSecureDesc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSecureDesc.setForeground(new java.awt.Color(74, 85, 104));
        lblSecureDesc.setText("<html>Your transaction is secured with 256-bit SSL encryption and PCI DSS compliance.<br>We do not store your full card details on our servers.</html>");
        pnlSecureBox.add(lblSecureDesc);
        lblSecureDesc.setBounds(50, 38, 610, 50);

        pnlPaymentMethods.add(pnlSecureBox);
        pnlSecureBox.setBounds(20, 440, 680, 100);

        pnlMainBg.add(pnlPaymentMethods);
        pnlPaymentMethods.setBounds(30, 40, 720, 570);

        pnlBookingSummary.setBackground(new java.awt.Color(255, 255, 255));
        pnlBookingSummary.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnlBookingSummary.setLayout(null);

        lblSummaryTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSummaryTitle.setForeground(new java.awt.Color(26, 26, 26));
        lblSummaryTitle.setText("📋  Booking Summary");
        pnlBookingSummary.add(lblSummaryTitle);
        lblSummaryTitle.setBounds(20, 20, 280, 30);

        lblTicketLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTicketLabel.setForeground(new java.awt.Color(51, 51, 51));
        lblTicketLabel.setText("Ticket (KTM-PKR)");
        pnlBookingSummary.add(lblTicketLabel);
        lblTicketLabel.setBounds(20, 70, 160, 20);

        lblTicketValue.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTicketValue.setForeground(new java.awt.Color(26, 26, 26));
        lblTicketValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTicketValue.setText("NPR 8,400");
        pnlBookingSummary.add(lblTicketValue);
        lblTicketValue.setBounds(180, 70, 120, 20);

        lblTicketDesc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblTicketDesc.setForeground(new java.awt.Color(119, 119, 119));
        lblTicketDesc.setText("Economy Flex • 1 Passenger");
        pnlBookingSummary.add(lblTicketDesc);
        lblTicketDesc.setBounds(20, 90, 280, 15);

        lblSeatLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSeatLabel.setForeground(new java.awt.Color(51, 51, 51));
        lblSeatLabel.setText("Seat Upgrade (A2)");
        pnlBookingSummary.add(lblSeatLabel);
        lblSeatLabel.setBounds(20, 125, 160, 20);

        lblSeatValue.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSeatValue.setForeground(new java.awt.Color(26, 26, 26));
        lblSeatValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSeatValue.setText("NPR 1,200");
        pnlBookingSummary.add(lblSeatValue);
        lblSeatValue.setBounds(180, 125, 120, 20);

        lblSeatDesc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblSeatDesc.setForeground(new java.awt.Color(119, 119, 119));
        lblSeatDesc.setText("Window + Extra Legroom");
        pnlBookingSummary.add(lblSeatDesc);
        lblSeatDesc.setBounds(20, 145, 280, 15);

        lblTaxesLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTaxesLabel.setForeground(new java.awt.Color(51, 51, 51));
        lblTaxesLabel.setText("Taxes & Fees");
        pnlBookingSummary.add(lblTaxesLabel);
        lblTaxesLabel.setBounds(20, 180, 160, 20);

        lblTaxesValue.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTaxesValue.setForeground(new java.awt.Color(26, 26, 26));
        lblTaxesValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTaxesValue.setText("NPR 800");
        pnlBookingSummary.add(lblTaxesValue);
        lblTaxesValue.setBounds(180, 180, 120, 20);

        lblTaxesDesc.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblTaxesDesc.setForeground(new java.awt.Color(119, 119, 119));
        lblTaxesDesc.setText("Fuel Surcharge & Airport Tax");
        pnlBookingSummary.add(lblTaxesDesc);
        lblTaxesDesc.setBounds(20, 200, 280, 15);
        pnlBookingSummary.add(sepSummary);
        sepSummary.setBounds(20, 235, 280, 5);

        lblTotalLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalLabel.setForeground(new java.awt.Color(51, 51, 51));
        lblTotalLabel.setText("Total Amount");
        pnlBookingSummary.add(lblTotalLabel);
        lblTotalLabel.setBounds(20, 255, 110, 30);

        lblTotalValue.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTotalValue.setForeground(new java.awt.Color(0, 102, 204));
        lblTotalValue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalValue.setText("NPR 10,400");
        pnlBookingSummary.add(lblTotalValue);
        lblTotalValue.setBounds(130, 250, 170, 35);

        lblInclTaxes.setFont(new java.awt.Font("Segoe UI", 1, 9)); // NOI18N
        lblInclTaxes.setForeground(new java.awt.Color(119, 119, 119));
        lblInclTaxes.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInclTaxes.setText("INCL. ALL TAXES");
        pnlBookingSummary.add(lblInclTaxes);
        lblInclTaxes.setBounds(180, 285, 120, 15);

        btnConfirmPay.setBackground(new java.awt.Color(15, 61, 117));
        btnConfirmPay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnConfirmPay.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmPay.setText("Confirm & Pay →");
        btnConfirmPay.setBorderPainted(false);
        btnConfirmPay.setFocusPainted(false);
        pnlBookingSummary.add(btnConfirmPay);
        btnConfirmPay.setBounds(20, 320, 280, 55);

        lblTerms.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblTerms.setForeground(new java.awt.Color(119, 119, 119));
        lblTerms.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTerms.setText("<html><center>By clicking 'Confirm & Pay' you agree to our <font color='#0066cc'><u>Terms & Conditions</u></font> and <font color='#0066cc'><u>Cancellation Policy</u></font>.</center></html>");
        pnlBookingSummary.add(lblTerms);
        lblTerms.setBounds(20, 390, 280, 45);

        pnlMainBg.add(pnlBookingSummary);
        pnlBookingSummary.setBounds(776, 40, 320, 570);

        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(119, 119, 119));
        lblStatus.setText("●  SYSTEM STATUS: OPERATIONAL");
        pnlMainBg.add(lblStatus);
        lblStatus.setBounds(30, 625, 250, 20);

        lblProgressLine.setBackground(new java.awt.Color(51, 51, 51));
        lblProgressLine.setOpaque(true);
        pnlMainBg.add(lblProgressLine);
        lblProgressLine.setBounds(413, 635, 300, 6);

        getContentPane().add(pnlMainBg);
        pnlMainBg.setBounds(240, 70, 1126, 698);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmPay;
    private javax.swing.JCheckBox chkSaveCard;
    private javax.swing.JComboBox cmbMobileWallet;
    private javax.swing.JLabel lblBackSeat;
    private javax.swing.JLabel lblCardHolderName;
    private javax.swing.JLabel lblCardIcon;
    private javax.swing.JLabel lblCardNumber;
    private javax.swing.JLabel lblCustomerSupport;
    private javax.swing.JLabel lblCvv;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblExpiryDate;
    private javax.swing.JLabel lblInclTaxes;
    private javax.swing.JLabel lblInfoIcon;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblMobileWalletTitle;
    private javax.swing.JLabel lblMyBookings;
    private javax.swing.JLabel lblPaymentTitle;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblProgressLine;
    private javax.swing.JLabel lblSearchFlight;
    private javax.swing.JLabel lblSeatDesc;
    private javax.swing.JLabel lblSeatLabel;
    private javax.swing.JLabel lblSeatValue;
    private javax.swing.JLabel lblSecureDesc;
    private javax.swing.JLabel lblSecureTitle;
    private javax.swing.JLabel lblShieldIcon;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSummaryTitle;
    private javax.swing.JLabel lblTabCard;
    private javax.swing.JLabel lblTabMobile;
    private javax.swing.JLabel lblTaxesDesc;
    private javax.swing.JLabel lblTaxesLabel;
    private javax.swing.JLabel lblTaxesValue;
    private javax.swing.JLabel lblTerms;
    private javax.swing.JLabel lblTicketDesc;
    private javax.swing.JLabel lblTicketLabel;
    private javax.swing.JLabel lblTicketValue;
    private javax.swing.JLabel lblTopIcons;
    private javax.swing.JLabel lblTopTitle;
    private javax.swing.JLabel lblTotalLabel;
    private javax.swing.JLabel lblTotalValue;
    private javax.swing.JLabel lblWalletId;
    private javax.swing.JPanel pnlBookingSummary;
    private javax.swing.JPanel pnlMainBg;
    private javax.swing.JPanel pnlPaymentMethods;
    private javax.swing.JPanel pnlSecureBox;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTabs;
    private javax.swing.JPanel pnlTopHeader;
    private javax.swing.JSeparator sepSummary;
    private javax.swing.JSeparator sepTabs;
    private javax.swing.JTextField txtCardHolderName;
    private javax.swing.JTextField txtCardNumber;
    private javax.swing.JTextField txtCvv;
    private javax.swing.JTextField txtExpiryDate;
    private javax.swing.JTextField txtWalletId;
    // End of variables declaration//GEN-END:variables
}
