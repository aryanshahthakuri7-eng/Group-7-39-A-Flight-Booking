package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.Border;
import model.User;

public class SeatSelectionPanel extends JPanel {
    private final Dashboard dashboard;
    private final String flightNo;
    private final String time;
    private final String fromCode;
    private final String toCode;
    private final String date;
    private final int passengerCount;
    private final double basePrice;
    
    // UI components
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtCitizenship;
    private JLabel lblSelectedSeatsText;
    private JLabel lblTotalCostVal;
    
    // Seat selection tracking
    private final Set<String> selectedSeats = new HashSet<>();
    private final Set<String> occupiedSeats = new HashSet<>();
    private final List<JButton> seatButtons = new ArrayList<>();

    public SeatSelectionPanel(Dashboard dashboard, String flightNo, String time, String from, String to, String date, int passengers, double price) {
        this.dashboard = dashboard;
        this.flightNo = flightNo;
        this.time = time;
        this.fromCode = from;
        this.toCode = to;
        this.date = date;
        this.passengerCount = passengers;
        this.basePrice = price;

        // Simulate some occupied seats
        occupiedSeats.add("1B");
        occupiedSeats.add("2D");
        occupiedSeats.add("3F");
        occupiedSeats.add("4A");
        occupiedSeats.add("5C");

        initComponents();
        populateUserInfo();
    }

    private void populateUserInfo() {
        User user = dashboard.getCurrentUser();
        if (user != null) {
            txtName.setText(user.getFullname());
            txtEmail.setText(user.getEmail());
            txtPhone.setText(user.getPhone());
        }
    }

    private void initComponents() {
        setBackground(new Color(248, 249, 255));
        setLayout(null);
        setSize(780, 630);

        // Back Button
        JLabel lblBack = new JLabel("← Back to Flights");
        lblBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBack.setForeground(new Color(245, 130, 32));
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBack.setBounds(30, 15, 150, 20);
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard.switchPanel(new SearchResultsPanel(dashboard, fromCode, toCode, date, passengerCount));
            }
        });
        add(lblBack);

        // Header Title
        JLabel lblTitle = new JLabel("Passenger Details & Seat Map");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(8, 22, 42));
        lblTitle.setBounds(30, 40, 400, 25);
        add(lblTitle);

        // Flight Info Summary banner
        JPanel infoBanner = new JPanel();
        infoBanner.setBackground(new Color(8, 22, 42)); // Navy background
        infoBanner.setLayout(null);
        infoBanner.setBounds(30, 75, 720, 45);
        
        JLabel lblBannerDetails = new JLabel(flightNo + "  •  " + fromCode + " → " + toCode + "  •  " + date + "  •  " + time);
        lblBannerDetails.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBannerDetails.setForeground(Color.WHITE);
        lblBannerDetails.setBounds(15, 10, 690, 25);
        infoBanner.add(lblBannerDetails);
        add(infoBanner);

        // Left Column: Passenger Information Form
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240)),
                " PASSENGER INFORMATION ",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 11),
                new Color(8, 22, 42)
        ));
        formPanel.setLayout(null);
        formPanel.setBounds(30, 135, 340, 280);

        JLabel lblName = new JLabel("Full Name");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblName.setForeground(new Color(113, 128, 150));
        lblName.setBounds(20, 25, 150, 15);
        formPanel.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(20, 45, 300, 32);
        formPanel.add(txtName);

        JLabel lblEmail = new JLabel("Email Address");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEmail.setForeground(new Color(113, 128, 150));
        lblEmail.setBounds(20, 85, 150, 15);
        formPanel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(20, 105, 300, 32);
        formPanel.add(txtEmail);

        JLabel lblPhone = new JLabel("Phone Number");
        lblPhone.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblPhone.setForeground(new Color(113, 128, 150));
        lblPhone.setBounds(20, 145, 150, 15);
        formPanel.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(20, 165, 300, 32);
        formPanel.add(txtPhone);

        JLabel lblCitizenship = new JLabel("Citizenship / Passport ID");
        lblCitizenship.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCitizenship.setForeground(new Color(113, 128, 150));
        lblCitizenship.setBounds(20, 205, 200, 15);
        formPanel.add(lblCitizenship);

        txtCitizenship = new JTextField();
        txtCitizenship.setBounds(20, 225, 300, 32);
        formPanel.add(txtCitizenship);

        add(formPanel);

        // Right Column: Seat Selection Grid Map
        JPanel seatPanel = new JPanel();
        seatPanel.setBackground(Color.WHITE);
        seatPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240)),
                " SELECT SEATS (Select " + passengerCount + ") ",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 11),
                new Color(8, 22, 42)
        ));
        seatPanel.setLayout(null);
        seatPanel.setBounds(390, 135, 360, 280);

        // Columns labels A B C [Aisle] D E F
        String[] cols = {"A", "B", "C", "D", "E", "F"};
        int xPos = 25;
        for (int col = 0; col < cols.length; col++) {
            JLabel lblCol = new JLabel(cols[col]);
            lblCol.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblCol.setHorizontalAlignment(JLabel.CENTER);
            lblCol.setForeground(new Color(113, 128, 150));
            
            // Account for aisle between C and D
            int colX = xPos + (col >= 3 ? col * 45 + 30 : col * 45);
            lblCol.setBounds(colX, 25, 35, 20);
            seatPanel.add(lblCol);
        }

        // Seat Grid rows 1 to 5
        int yPos = 50;
        for (int row = 1; row <= 5; row++) {
            JLabel lblRow = new JLabel(String.valueOf(row));
            lblRow.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblRow.setForeground(new Color(113, 128, 150));
            lblRow.setBounds(5, yPos + 5, 20, 25);
            seatPanel.add(lblRow);

            for (int col = 0; col < cols.length; col++) {
                String seatCode = row + cols[col];
                JButton btnSeat = new JButton(cols[col]);
                btnSeat.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                
                int colX = xPos + (col >= 3 ? col * 45 + 30 : col * 45);
                btnSeat.setBounds(colX, yPos, 35, 35);
                btnSeat.setFocusPainted(false);
                btnSeat.setCursor(new Cursor(Cursor.HAND_CURSOR));

                // Color configuration
                if (occupiedSeats.contains(seatCode)) {
                    btnSeat.setText("X");
                    btnSeat.setBackground(new Color(226, 232, 240)); // Gray
                    btnSeat.setForeground(new Color(148, 163, 184));
                    btnSeat.setEnabled(false);
                } else {
                    btnSeat.setBackground(Color.WHITE);
                    btnSeat.setForeground(new Color(8, 22, 42));
                    btnSeat.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 224)));
                    btnSeat.addActionListener(e -> toggleSeat(seatCode, btnSeat));
                }

                seatPanel.add(btnSeat);
                seatButtons.add(btnSeat);
            }
            yPos += 42;
        }
        add(seatPanel);

        // Summary details panel at bottom
        JPanel summaryPanel = new JPanel();
        summaryPanel.setBackground(Color.WHITE);
        summaryPanel.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        summaryPanel.setLayout(null);
        summaryPanel.setBounds(30, 435, 720, 160);

        JLabel lblSeatsSelected = new JLabel("Seats Selected:");
        lblSeatsSelected.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSeatsSelected.setForeground(new Color(8, 22, 42));
        lblSeatsSelected.setBounds(25, 25, 120, 20);
        summaryPanel.add(lblSeatsSelected);

        lblSelectedSeatsText = new JLabel("None (Select " + passengerCount + " seat(s))");
        lblSelectedSeatsText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSelectedSeatsText.setForeground(new Color(245, 130, 32));
        lblSelectedSeatsText.setBounds(150, 25, 300, 20);
        summaryPanel.add(lblSelectedSeatsText);

        // Price summary
        JLabel lblTotalCost = new JLabel("Grand Total:");
        lblTotalCost.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTotalCost.setForeground(new Color(113, 128, 150));
        lblTotalCost.setBounds(25, 60, 100, 20);
        summaryPanel.add(lblTotalCost);

        lblTotalCostVal = new JLabel("NPR 0");
        lblTotalCostVal.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTotalCostVal.setForeground(new Color(8, 22, 42));
        lblTotalCostVal.setBounds(150, 55, 200, 30);
        summaryPanel.add(lblTotalCostVal);

        JButton btnCheckout = new JButton("Proceed to Payment");
        btnCheckout.setBackground(new Color(245, 130, 32));
        btnCheckout.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCheckout.setForeground(Color.WHITE);
        btnCheckout.setBorderPainted(false);
        btnCheckout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCheckout.setBounds(500, 50, 190, 45);
        btnCheckout.addActionListener(e -> proceedToCheckout());
        summaryPanel.add(btnCheckout);

        add(summaryPanel);
    }

    private void toggleSeat(String seatCode, JButton btn) {
        if (selectedSeats.contains(seatCode)) {
            selectedSeats.remove(seatCode);
            btn.setBackground(Color.WHITE);
            btn.setForeground(new Color(8, 22, 42));
            btn.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 224)));
        } else {
            // Check if limit is reached
            if (selectedSeats.size() >= passengerCount) {
                JOptionPane.showMessageDialog(this, "You have already selected " + passengerCount + " seat(s). Deselect one to select a new one.", "Seat Selection Limit", JOptionPane.WARNING_MESSAGE);
                return;
            }
            selectedSeats.add(seatCode);
            btn.setBackground(new Color(0, 102, 204)); // Selected Blue
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createLineBorder(new Color(0, 77, 153)));
        }
        updateSummary();
    }

    private void updateSummary() {
        if (selectedSeats.isEmpty()) {
            lblSelectedSeatsText.setText("None (Select " + passengerCount + " seat(s))");
            lblTotalCostVal.setText("NPR 0");
        } else {
            List<String> sortedList = new ArrayList<>(selectedSeats);
            java.util.Collections.sort(sortedList);
            lblSelectedSeatsText.setText(String.join(", ", sortedList) + " (" + selectedSeats.size() + "/" + passengerCount + ")");
            
            double total = basePrice;
            lblTotalCostVal.setText("NPR " + String.format("%,.0f", total));
        }
    }

    private void proceedToCheckout() {
        // Validations
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();
        String citizenship = txtCitizenship.getText().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || citizenship.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all Passenger details.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (selectedSeats.size() < passengerCount) {
            JOptionPane.showMessageDialog(this, "Please select exactly " + passengerCount + " seat(s) on the seat map.", "Seat Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validation of email format
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Navigate to payment
        List<String> seats = new ArrayList<>(selectedSeats);
        java.util.Collections.sort(seats);
        String seatString = String.join(", ", seats);

        dashboard.switchPanel(new PaymentPanel(dashboard, flightNo, time, fromCode, toCode, date, passengerCount, basePrice, seatString, name, email, phone, citizenship));
    }
}
