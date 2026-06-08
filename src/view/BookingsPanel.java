package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Booking;

public class BookingsPanel extends JPanel {
    private final Dashboard dashboard;
    private JPanel cardsContainer;

    public BookingsPanel(Dashboard dashboard) {
        this.dashboard = dashboard;
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(248, 249, 255));
        setLayout(null);
        setSize(780, 630);

        // Back Button
        JLabel lblBack = new JLabel("← Back to Dashboard");
        lblBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBack.setForeground(new Color(245, 130, 32));
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBack.setBounds(30, 20, 150, 20);
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard.switchPanel(null); // Go to main dashboard homepage
            }
        });
        add(lblBack);

        // Header Title
        JLabel lblTitle = new JLabel("My Bookings");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(8, 22, 42));
        lblTitle.setBounds(30, 50, 300, 30);
        add(lblTitle);

        // Info subtitle
        JLabel lblSub = new JLabel("Manage your upcoming and completed flight bookings.");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(113, 128, 150));
        lblSub.setBounds(30, 80, 500, 20);
        add(lblSub);

        // Container panel to hold bookings dynamically with a ScrollPane
        cardsContainer = new JPanel();
        cardsContainer.setBackground(new Color(248, 249, 255));
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(30, 120, 720, 480);
        add(scrollPane);

        renderBookings();
    }

    public void renderBookings() {
        cardsContainer.removeAll();
        List<Booking> list = dashboard.getBookingsList();

        if (list.isEmpty()) {
            JPanel emptyPanel = new JPanel();
            emptyPanel.setBackground(Color.WHITE);
            emptyPanel.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
            emptyPanel.setLayout(new BorderLayout());
            emptyPanel.setPreferredSize(new Dimension(700, 150));
            emptyPanel.setMaximumSize(new Dimension(700, 150));

            JLabel lblEmpty = new JLabel("<html><center>No bookings found.<br>Search and book flights to view them here.</center></html>");
            lblEmpty.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            lblEmpty.setForeground(Color.GRAY);
            lblEmpty.setHorizontalAlignment(JLabel.CENTER);
            emptyPanel.add(lblEmpty, BorderLayout.CENTER);
            cardsContainer.add(emptyPanel);
        } else {
            for (Booking b : list) {
                JPanel card = createBookingCard(b);
                cardsContainer.add(card);
                cardsContainer.add(Box.createRigidArea(new Dimension(0, 15))); // spacing
            }
        }
        
        cardsContainer.revalidate();
        cardsContainer.repaint();
    }

    private JPanel createBookingCard(Booking b) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        card.setLayout(null);
        card.setPreferredSize(new Dimension(700, 125));
        card.setMinimumSize(new Dimension(700, 125));
        card.setMaximumSize(new Dimension(700, 125));

        // Left section: Carrier Logo
        JPanel logoBox = new JPanel();
        logoBox.setBackground(new Color(248, 249, 255));
        logoBox.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        logoBox.setLayout(null);
        logoBox.setBounds(15, 20, 45, 45);

        JLabel logoIcon = new JLabel("▼");
        logoIcon.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logoIcon.setForeground(new Color(8, 22, 42));
        logoIcon.setHorizontalAlignment(JLabel.CENTER);
        logoIcon.setBounds(0, 0, 45, 45);
        logoBox.add(logoIcon);
        card.add(logoBox);

        // Flight Number
        JLabel lblFlightNo = new JLabel(b.getFlightNo());
        lblFlightNo.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblFlightNo.setForeground(new Color(113, 128, 150));
        lblFlightNo.setHorizontalAlignment(JLabel.CENTER);
        lblFlightNo.setBounds(15, 70, 45, 15);
        card.add(lblFlightNo);

        // Routing
        JLabel lblRoute = new JLabel(b.getFromCode() + " ➔ " + b.getToCode());
        lblRoute.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblRoute.setForeground(new Color(8, 22, 42));
        lblRoute.setBounds(80, 20, 200, 22);
        card.add(lblRoute);

        // Flight Schedule Details
        JLabel lblDetails = new JLabel(b.getDate() + "  •  " + b.getTime());
        lblDetails.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblDetails.setForeground(new Color(113, 128, 150));
        lblDetails.setBounds(80, 45, 250, 15);
        card.add(lblDetails);

        // Seat Info
        JLabel lblSeat = new JLabel("🎟  SEAT: " + b.getSeat());
        lblSeat.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblSeat.setForeground(new Color(113, 128, 150));
        lblSeat.setBounds(80, 85, 120, 15);
        card.add(lblSeat);

        // Passenger Name
        JLabel lblPassenger = new JLabel("👤  PASSENGER: " + b.getPassengerName());
        lblPassenger.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblPassenger.setForeground(new Color(113, 128, 150));
        lblPassenger.setBounds(210, 85, 200, 15);
        card.add(lblPassenger);

        // Status Badge
        JLabel lblStatus = new JLabel(b.getStatus());
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 9));
        lblStatus.setHorizontalAlignment(JLabel.CENTER);
        lblStatus.setOpaque(true);
        if (b.getStatus().equals("CONFIRMED")) {
            lblStatus.setBackground(new Color(222, 245, 226)); // Soft light green
            lblStatus.setForeground(new Color(40, 160, 80));
        } else {
            lblStatus.setBackground(new Color(255, 225, 225)); // Soft light red
            lblStatus.setForeground(new Color(220, 50, 50));
        }
        lblStatus.setBounds(430, 20, 90, 22);
        card.add(lblStatus);

        // Price info
        JLabel lblPrice = new JLabel("NPR " + String.format("%,.0f", b.getAmount()));
        lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPrice.setForeground(new Color(8, 22, 42));
        lblPrice.setBounds(430, 47, 95, 20);
        card.add(lblPrice);

        // Separator vertical
        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        sep.setForeground(new Color(226, 232, 240));
        sep.setBounds(540, 15, 2, 95);
        card.add(sep);

        // Action Buttons
        JButton btnPrint = new JButton("Receipt");
        btnPrint.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btnPrint.setBackground(new Color(235, 235, 240));
        btnPrint.setForeground(new Color(8, 22, 42));
        btnPrint.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPrint.setFocusPainted(false);
        btnPrint.setBounds(560, 20, 120, 35);
        btnPrint.addActionListener(e -> printReceipt(b));
        card.add(btnPrint);

        JButton btnCancel = new JButton("Cancel Ticket");
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        btnCancel.setBackground(new Color(255, 225, 225));
        btnCancel.setForeground(new Color(220, 50, 50));
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.setFocusPainted(false);
        btnCancel.setBounds(560, 65, 120, 35);
        
        if (!b.getStatus().equals("CONFIRMED")) {
            btnCancel.setEnabled(false);
            btnCancel.setBackground(new Color(241, 245, 249));
            btnCancel.setForeground(Color.GRAY);
        }

        btnCancel.addActionListener(e -> cancelBooking(b));
        card.add(btnCancel);

        return card;
    }

    private void printReceipt(Booking b) {
        String receipt = "-------------------------------------------\n" +
                         "          YATRA AIR SEWA TICKET RECEIPT    \n" +
                         "-------------------------------------------\n" +
                         "Flight Number   : " + b.getFlightNo() + "\n" +
                         "Route           : " + b.getFromCode() + " -> " + b.getToCode() + "\n" +
                         "Date & Time     : " + b.getDate() + " " + b.getTime() + "\n" +
                         "Seat Code       : " + b.getSeat() + "\n" +
                         "Passenger Name  : " + b.getPassengerName() + "\n" +
                         "Total Paid      : NPR " + String.format("%,.2f", b.getAmount()) + "\n" +
                         "Status          : " + b.getStatus() + "\n" +
                         "-------------------------------------------\n" +
                         "   Thank you for flying with Yatra Air!     \n" +
                         "-------------------------------------------";
        JOptionPane.showMessageDialog(this, new JTextArea(receipt), "Receipt Print Preview", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cancelBooking(Booking b) {
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to cancel your flight " + b.getFlightNo() + " from " + b.getFromCode() + " to " + b.getToCode() + "?", 
                "Cancel Flight Booking", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
                
        if (confirm == JOptionPane.YES_OPTION) {
            b.setStatus("CANCELLED");
            
            // Re-render and update UI stats
            renderBookings();
            dashboard.renderBookingsList(); // Render home bookings list
            dashboard.updateStats(); // Update stats cards (cancelled increments, spent/loyalty updates if needed)
            
            JOptionPane.showMessageDialog(this, "Flight cancellation successful! Refund of NPR " + String.format("%,.0f", b.getAmount() * 0.90) + " (90%) will be credited to your payment method.", "Refund Initiated", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
