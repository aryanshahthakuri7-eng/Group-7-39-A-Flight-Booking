package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import model.Booking;

public class PaymentPanel extends JPanel {
    private final Dashboard dashboard;
    private final String flightNo;
    private final String time;
    private final String fromCode;
    private final String toCode;
    private final String date;
    private final int passengerCount;
    private final double grandTotal;
    private final String selectedSeats;
    private final String passName;
    private final String passEmail;
    private final String passPhone;
    private final String passCitizenship;

    // Tab buttons and form containers
    private JButton btnCardTab;
    private JButton btnWalletTab;
    private JPanel panelCardForm;
    private JPanel panelWalletForm;

    // Card Form Inputs
    private JTextField txtCardHolder;
    private JTextField txtCardNo;
    private JTextField txtCardExpiry;
    private JPasswordField txtCardCvv;

    // Wallet selection
    private String selectedWallet = "eSewa";
    private JLabel lblWalletLogo;
    
    // Switch state tracking
    private boolean isCardPayment = true;

    public PaymentPanel(Dashboard dashboard, String flightNo, String time, String from, String to, String date,
                        int passengers, double total, String seats, String name, String email, String phone, String citizenship) {
        this.dashboard = dashboard;
        this.flightNo = flightNo;
        this.time = time;
        this.fromCode = from;
        this.toCode = to;
        this.date = date;
        this.passengerCount = passengers;
        this.grandTotal = total;
        this.selectedSeats = seats;
        this.passName = name;
        this.passEmail = email;
        this.passPhone = phone;
        this.passCitizenship = citizenship;

        initComponents();
        showCardPaymentForm();
    }

    private void initComponents() {
        setBackground(new Color(248, 249, 255));
        setLayout(null);
        setSize(780, 630);

        // Back Button
        JLabel lblBack = new JLabel("← Back to Seats");
        lblBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBack.setForeground(new Color(245, 130, 32));
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBack.setBounds(30, 15, 150, 20);
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard.switchPanel(new SeatSelectionPanel(dashboard, flightNo, time, fromCode, toCode, date, passengerCount, grandTotal));
            }
        });
        add(lblBack);

        // Header Title
        JLabel lblTitle = new JLabel("Complete Your Payment");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(8, 22, 42));
        lblTitle.setBounds(30, 40, 400, 25);
        add(lblTitle);

        // Left Column: Payment Options (Tabs and forms)
        JPanel paymentWrapper = new JPanel();
        paymentWrapper.setBackground(Color.WHITE);
        paymentWrapper.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        paymentWrapper.setLayout(null);
        paymentWrapper.setBounds(30, 85, 420, 510);

        // Tabs
        btnCardTab = new JButton("💳  Credit/Debit Card");
        btnCardTab.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCardTab.setFocusPainted(false);
        btnCardTab.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCardTab.setBounds(20, 20, 185, 40);
        btnCardTab.addActionListener(e -> showCardPaymentForm());
        paymentWrapper.add(btnCardTab);

        btnWalletTab = new JButton("📱  Mobile Wallet (QR)");
        btnWalletTab.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnWalletTab.setFocusPainted(false);
        btnWalletTab.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnWalletTab.setBounds(215, 20, 185, 40);
        btnWalletTab.addActionListener(e -> showWalletPaymentForm());
        paymentWrapper.add(btnWalletTab);

        // --- Card Payment Form ---
        panelCardForm = new JPanel();
        panelCardForm.setBackground(Color.WHITE);
        panelCardForm.setLayout(null);
        panelCardForm.setBounds(20, 80, 380, 410);

        JLabel lblCardHolder = new JLabel("Card Holder Name");
        lblCardHolder.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCardHolder.setForeground(new Color(113, 128, 150));
        lblCardHolder.setBounds(0, 10, 200, 15);
        panelCardForm.add(lblCardHolder);

        txtCardHolder = new JTextField();
        txtCardHolder.setBounds(0, 30, 380, 34);
        panelCardForm.add(txtCardHolder);

        JLabel lblCardNo = new JLabel("Card Number");
        lblCardNo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCardNo.setForeground(new Color(113, 128, 150));
        lblCardNo.setBounds(0, 80, 200, 15);
        panelCardForm.add(lblCardNo);

        txtCardNo = new JTextField();
        txtCardNo.setBounds(0, 100, 380, 34);
        panelCardForm.add(txtCardNo);

        JLabel lblExpiry = new JLabel("Expiration Date");
        lblExpiry.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblExpiry.setForeground(new Color(113, 128, 150));
        lblExpiry.setBounds(0, 150, 150, 15);
        panelCardForm.add(lblExpiry);

        txtCardExpiry = new JTextField("MM/YY");
        txtCardExpiry.setForeground(Color.GRAY);
        txtCardExpiry.setBounds(0, 170, 175, 34);
        txtCardExpiry.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtCardExpiry.getText().equals("MM/YY")) {
                    txtCardExpiry.setText("");
                    txtCardExpiry.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtCardExpiry.getText().trim().isEmpty()) {
                    txtCardExpiry.setText("MM/YY");
                    txtCardExpiry.setForeground(Color.GRAY);
                }
            }
        });
        panelCardForm.add(txtCardExpiry);

        JLabel lblCvv = new JLabel("CVV / CVC");
        lblCvv.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCvv.setForeground(new Color(113, 128, 150));
        lblCvv.setBounds(205, 150, 150, 15);
        panelCardForm.add(lblCvv);

        txtCardCvv = new JPasswordField();
        txtCardCvv.setBounds(205, 170, 175, 34);
        panelCardForm.add(txtCardCvv);

        // Security label
        JLabel lblSecurity = new JLabel("🔒  Secure 256-bit SSL Encrypted Transaction");
        lblSecurity.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblSecurity.setForeground(new Color(113, 128, 150));
        lblSecurity.setHorizontalAlignment(JLabel.CENTER);
        lblSecurity.setBounds(0, 240, 380, 20);
        panelCardForm.add(lblSecurity);

        JButton btnPayCard = new JButton("Confirm & Pay NPR " + String.format("%,.0f", grandTotal));
        btnPayCard.setBackground(new Color(245, 130, 32));
        btnPayCard.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnPayCard.setForeground(Color.WHITE);
        btnPayCard.setBorderPainted(false);
        btnPayCard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPayCard.setBounds(0, 280, 380, 48);
        btnPayCard.addActionListener(e -> processCardPayment());
        panelCardForm.add(btnPayCard);

        paymentWrapper.add(panelCardForm);

        // --- Wallet Payment Form ---
        panelWalletForm = new JPanel();
        panelWalletForm.setBackground(Color.WHITE);
        panelWalletForm.setLayout(null);
        panelWalletForm.setBounds(20, 80, 380, 410);

        JLabel lblWalletSelect = new JLabel("Select Your Preferred Wallet");
        lblWalletSelect.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblWalletSelect.setForeground(new Color(113, 128, 150));
        lblWalletSelect.setBounds(0, 10, 300, 15);
        panelWalletForm.add(lblWalletSelect);

        // Grid selection buttons for eSewa, Khalti, IME Pay
        JButton btnEsewa = new JButton("eSewa");
        btnEsewa.setBounds(0, 30, 115, 35);
        btnEsewa.setFocusPainted(false);
        btnEsewa.setBackground(new Color(96, 189, 48)); // eSewa green
        btnEsewa.setForeground(Color.WHITE);
        btnEsewa.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnEsewa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEsewa.addActionListener(e -> selectWallet("eSewa", btnEsewa));
        panelWalletForm.add(btnEsewa);

        JButton btnKhalti = new JButton("Khalti");
        btnKhalti.setBounds(130, 30, 115, 35);
        btnKhalti.setFocusPainted(false);
        btnKhalti.setBackground(new Color(235, 235, 240));
        btnKhalti.setForeground(new Color(93, 23, 115)); // Khalti Purple
        btnKhalti.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnKhalti.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnKhalti.addActionListener(e -> selectWallet("Khalti", btnKhalti));
        panelWalletForm.add(btnKhalti);

        JButton btnIme = new JButton("IME Pay");
        btnIme.setBounds(260, 30, 120, 35);
        btnIme.setFocusPainted(false);
        btnIme.setBackground(new Color(235, 235, 240));
        btnIme.setForeground(new Color(216, 27, 96)); // IME Pay Red
        btnIme.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnIme.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIme.addActionListener(e -> selectWallet("IME Pay", btnIme));
        panelWalletForm.add(btnIme);

        // Simulated QR Code Frame Box
        JPanel qrFrame = new JPanel();
        qrFrame.setBackground(Color.WHITE);
        qrFrame.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        qrFrame.setLayout(new BorderLayout());
        qrFrame.setBounds(110, 90, 160, 160);

        // Draw a simulated QR Code
        lblWalletLogo = new JLabel("QR CODE");
        lblWalletLogo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblWalletLogo.setHorizontalAlignment(JLabel.CENTER);
        lblWalletLogo.setVerticalAlignment(JLabel.CENTER);
        lblWalletLogo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        qrFrame.add(lblWalletLogo, BorderLayout.CENTER);
        panelWalletForm.add(qrFrame);

        JLabel lblQrHint = new JLabel("Scan this QR using your " + selectedWallet + " app to pay");
        lblQrHint.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblQrHint.setForeground(new Color(113, 128, 150));
        lblQrHint.setHorizontalAlignment(JLabel.CENTER);
        lblQrHint.setBounds(0, 260, 380, 20);
        panelWalletForm.add(lblQrHint);

        JButton btnPayWallet = new JButton("Pay via Wallet");
        btnPayWallet.setBackground(new Color(245, 130, 32));
        btnPayWallet.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnPayWallet.setForeground(Color.WHITE);
        btnPayWallet.setBorderPainted(false);
        btnPayWallet.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPayWallet.setBounds(0, 290, 380, 48);
        btnPayWallet.addActionListener(e -> processWalletPayment());
        panelWalletForm.add(btnPayWallet);

        paymentWrapper.add(panelWalletForm);

        add(paymentWrapper);

        // Right Column: Summary Panel
        JPanel summaryPanel = new JPanel();
        summaryPanel.setBackground(Color.WHITE);
        summaryPanel.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        summaryPanel.setLayout(null);
        summaryPanel.setBounds(470, 85, 280, 510);

        JLabel lblSumTitle = new JLabel("BOOKING SUMMARY");
        lblSumTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblSumTitle.setForeground(new Color(113, 128, 150));
        lblSumTitle.setBounds(20, 20, 240, 15);
        summaryPanel.add(lblSumTitle);

        // Flight Info
        JLabel lblFlightLabel = new JLabel("Flight:");
        lblFlightLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblFlightLabel.setForeground(new Color(113, 128, 150));
        lblFlightLabel.setBounds(20, 50, 80, 15);
        summaryPanel.add(lblFlightLabel);

        JLabel lblFlightVal = new JLabel(flightNo + " (" + fromCode + " → " + toCode + ")");
        lblFlightVal.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblFlightVal.setForeground(new Color(8, 22, 42));
        lblFlightVal.setBounds(100, 50, 160, 15);
        summaryPanel.add(lblFlightVal);

        JLabel lblDateLabel = new JLabel("Date:");
        lblDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDateLabel.setForeground(new Color(113, 128, 150));
        lblDateLabel.setBounds(20, 75, 80, 15);
        summaryPanel.add(lblDateLabel);

        JLabel lblDateVal = new JLabel(date + " " + time.split(" - ")[0]);
        lblDateVal.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDateVal.setForeground(new Color(8, 22, 42));
        lblDateVal.setBounds(100, 75, 160, 15);
        summaryPanel.add(lblDateVal);

        JLabel lblSeatsLabel = new JLabel("Seat(s):");
        lblSeatsLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblSeatsLabel.setForeground(new Color(113, 128, 150));
        lblSeatsLabel.setBounds(20, 100, 80, 15);
        summaryPanel.add(lblSeatsLabel);

        JLabel lblSeatsVal = new JLabel(selectedSeats);
        lblSeatsVal.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblSeatsVal.setForeground(new Color(8, 22, 42));
        lblSeatsVal.setBounds(100, 100, 160, 15);
        summaryPanel.add(lblSeatsVal);

        JLabel lblPassLabel = new JLabel("Passenger:");
        lblPassLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblPassLabel.setForeground(new Color(113, 128, 150));
        lblPassLabel.setBounds(20, 125, 80, 15);
        summaryPanel.add(lblPassLabel);

        JLabel lblPassVal = new JLabel(passName);
        lblPassVal.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblPassVal.setForeground(new Color(8, 22, 42));
        lblPassVal.setBounds(100, 125, 160, 15);
        summaryPanel.add(lblPassVal);

        JSeparator sep1 = new JSeparator();
        sep1.setForeground(new Color(226, 232, 240));
        sep1.setBounds(20, 155, 240, 2);
        summaryPanel.add(sep1);

        // Price breakdown
        JLabel lblBaseLabel = new JLabel("Base Fare:");
        lblBaseLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblBaseLabel.setForeground(new Color(113, 128, 150));
        lblBaseLabel.setBounds(20, 175, 100, 15);
        summaryPanel.add(lblBaseLabel);

        double baseFare = grandTotal * 0.90;
        JLabel lblBaseVal = new JLabel("NPR " + String.format("%,.0f", baseFare));
        lblBaseVal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblBaseVal.setForeground(new Color(8, 22, 42));
        lblBaseVal.setHorizontalAlignment(JLabel.RIGHT);
        lblBaseVal.setBounds(140, 175, 120, 15);
        summaryPanel.add(lblBaseVal);

        JLabel lblTaxLabel = new JLabel("Taxes & Fees:");
        lblTaxLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTaxLabel.setForeground(new Color(113, 128, 150));
        lblTaxLabel.setBounds(20, 200, 100, 15);
        summaryPanel.add(lblTaxLabel);

        double tax = grandTotal * 0.10;
        JLabel lblTaxVal = new JLabel("NPR " + String.format("%,.0f", tax));
        lblTaxVal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTaxVal.setForeground(new Color(8, 22, 42));
        lblTaxVal.setHorizontalAlignment(JLabel.RIGHT);
        lblTaxVal.setBounds(140, 200, 120, 15);
        summaryPanel.add(lblTaxVal);

        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(226, 232, 240));
        sep2.setBounds(20, 230, 240, 2);
        summaryPanel.add(sep2);

        JLabel lblTotalLabel = new JLabel("Total Price:");
        lblTotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTotalLabel.setForeground(new Color(8, 22, 42));
        lblTotalLabel.setBounds(20, 245, 100, 20);
        summaryPanel.add(lblTotalLabel);

        JLabel lblTotalVal = new JLabel("NPR " + String.format("%,.0f", grandTotal));
        lblTotalVal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotalVal.setForeground(new Color(245, 130, 32)); // Orange
        lblTotalVal.setHorizontalAlignment(JLabel.RIGHT);
        lblTotalVal.setBounds(130, 245, 130, 20);
        summaryPanel.add(lblTotalVal);

        add(summaryPanel);
    }

    private void showCardPaymentForm() {
        isCardPayment = true;
        btnCardTab.setBackground(new Color(8, 22, 42)); // Dark Navy Active
        btnCardTab.setForeground(Color.WHITE);
        btnWalletTab.setBackground(new Color(235, 235, 240)); // Light Inactive
        btnWalletTab.setForeground(new Color(113, 128, 150));
        
        panelCardForm.setVisible(true);
        panelWalletForm.setVisible(false);
    }

    private void showWalletPaymentForm() {
        isCardPayment = false;
        btnWalletTab.setBackground(new Color(8, 22, 42)); // Dark Navy Active
        btnWalletTab.setForeground(Color.WHITE);
        btnCardTab.setBackground(new Color(235, 235, 240)); // Light Inactive
        btnCardTab.setForeground(new Color(113, 128, 150));
        
        panelCardForm.setVisible(false);
        panelWalletForm.setVisible(true);
        updateWalletQrLogo();
    }

    private void selectWallet(String walletName, JButton btn) {
        selectedWallet = walletName;
        // Reset colors of all wallet buttons
        for (Component c : panelWalletForm.getComponents()) {
            if (c instanceof JButton && !((JButton) c).getText().startsWith("Pay")) {
                JButton b = (JButton) c;
                if (b.getText().equals(walletName)) {
                    if (walletName.equals("eSewa")) {
                        b.setBackground(new Color(96, 189, 48));
                        b.setForeground(Color.WHITE);
                    } else if (walletName.equals("Khalti")) {
                        b.setBackground(new Color(93, 23, 115));
                        b.setForeground(Color.WHITE);
                    } else if (walletName.equals("IME Pay")) {
                        b.setBackground(new Color(216, 27, 96));
                        b.setForeground(Color.WHITE);
                    }
                } else {
                    b.setBackground(new Color(235, 235, 240));
                    if (b.getText().equals("eSewa")) b.setForeground(new Color(96, 189, 48));
                    else if (b.getText().equals("Khalti")) b.setForeground(new Color(93, 23, 115));
                    else if (b.getText().equals("IME Pay")) b.setForeground(new Color(216, 27, 96));
                }
            }
        }
        
        // Update label hint
        for (Component c : panelWalletForm.getComponents()) {
            if (c instanceof JLabel && ((JLabel) c).getText().contains("Scan")) {
                ((JLabel) c).setText("Scan this QR using your " + selectedWallet + " app to pay");
            }
        }
        updateWalletQrLogo();
    }

    private void updateWalletQrLogo() {
        lblWalletLogo.setText("<html><center><b>" + selectedWallet.toUpperCase() + "</b><br>PAYMENT QR<br>● ■ █ ■ ●<br>█ ░ ░ ░ █<br>● ■ █ ■ ●</center></html>");
        if (selectedWallet.equals("eSewa")) {
            lblWalletLogo.setForeground(new Color(96, 189, 48));
        } else if (selectedWallet.equals("Khalti")) {
            lblWalletLogo.setForeground(new Color(93, 23, 115));
        } else {
            lblWalletLogo.setForeground(new Color(216, 27, 96));
        }
    }

    private void processCardPayment() {
        String holder = txtCardHolder.getText().trim();
        String number = txtCardNo.getText().trim();
        String expiry = txtCardExpiry.getText().trim();
        String cvv = new String(txtCardCvv.getPassword()).trim();

        if (holder.isEmpty() || number.isEmpty() || expiry.isEmpty() || cvv.isEmpty() || expiry.equals("MM/YY")) {
            JOptionPane.showMessageDialog(this, "Please fill in all credit card details.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (number.length() < 12 || cvv.length() < 3) {
            JOptionPane.showMessageDialog(this, "Please enter valid credit card details.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        completeBooking();
    }

    private void processWalletPayment() {
        // Just directly proceed on scanning simulation
        completeBooking();
    }

    private void completeBooking() {
        // Appends the booking to the dynamic ArrayList<Booking> in memory
        Booking newBooking = new Booking(
                fromCode,
                toCode,
                flightNo,
                date,
                time,
                "CONFIRMED",
                selectedSeats,
                passName,
                grandTotal
        );
        
        // Add to dashboard list
        dashboard.getBookingsList().add(newBooking);
        dashboard.renderBookingsList(); // Render the main list in the dashboard if returning
        
        // Trigger payment success views
        String bookingId = "YAS-" + (1000000 + (int)(Math.random() * 9000000)) + "-B";
        
        // Transition to success panel
        dashboard.switchPanel(new ConfirmationPanel(dashboard, newBooking, bookingId));
    }
}
