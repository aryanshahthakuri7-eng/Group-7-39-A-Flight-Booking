package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;
import model.Booking;

public class ConfirmationPanel extends JPanel {
    private final Dashboard dashboard;
    private final Booking booking;
    private final String bookingId;

    public ConfirmationPanel(Dashboard dashboard, Booking booking, String bookingId) {
        this.dashboard = dashboard;
        this.booking = booking;
        this.bookingId = bookingId;

        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(248, 249, 255));
        setLayout(null);
        setSize(780, 630);

        // Success Card
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        card.setLayout(null);
        card.setBounds(140, 40, 500, 530);

        // Success Icon Circle
        JPanel iconCircle = new JPanel();
        iconCircle.setBackground(new Color(222, 245, 226)); // Soft light green
        iconCircle.setLayout(new java.awt.BorderLayout());
        iconCircle.setBounds(210, 30, 80, 80);
        
        // Circular borders
        iconCircle.setBorder(BorderFactory.createLineBorder(new Color(25, 127, 54), 1));
        
        JLabel lblCheck = new JLabel("✓");
        lblCheck.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblCheck.setForeground(new Color(25, 127, 54)); // Success Green
        lblCheck.setHorizontalAlignment(JLabel.CENTER);
        iconCircle.add(lblCheck, java.awt.BorderLayout.CENTER);
        card.add(iconCircle);

        // Success message
        JLabel lblSuccessMsg = new JLabel("Payment Successful!");
        lblSuccessMsg.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblSuccessMsg.setForeground(new Color(8, 22, 42)); // Navy
        lblSuccessMsg.setHorizontalAlignment(JLabel.CENTER);
        lblSuccessMsg.setBounds(50, 130, 400, 30);
        card.add(lblSuccessMsg);

        JLabel lblSubMsg = new JLabel("Your booking has been confirmed. Bon voyage!");
        lblSubMsg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubMsg.setForeground(new Color(113, 128, 150));
        lblSubMsg.setHorizontalAlignment(JLabel.CENTER);
        lblSubMsg.setBounds(50, 160, 400, 20);
        card.add(lblSubMsg);

        // Ticket Details Box
        JPanel ticketBox = new JPanel();
        ticketBox.setBackground(new Color(248, 249, 255));
        ticketBox.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        ticketBox.setLayout(null);
        ticketBox.setBounds(40, 200, 420, 240);

        // Booking ID
        JLabel lblBIdLabel = new JLabel("BOOKING ID:");
        lblBIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblBIdLabel.setForeground(new Color(113, 128, 150));
        lblBIdLabel.setBounds(20, 15, 100, 15);
        ticketBox.add(lblBIdLabel);

        JLabel lblBIdVal = new JLabel(bookingId);
        lblBIdVal.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBIdVal.setForeground(new Color(245, 130, 32)); // Orange
        lblBIdVal.setBounds(130, 15, 270, 15);
        ticketBox.add(lblBIdVal);

        // Route
        JLabel lblRouteLabel = new JLabel("ROUTE:");
        lblRouteLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblRouteLabel.setForeground(new Color(113, 128, 150));
        lblRouteLabel.setBounds(20, 45, 100, 15);
        ticketBox.add(lblRouteLabel);

        JLabel lblRouteVal = new JLabel(booking.getFromCode() + " ➔ " + booking.getToCode());
        lblRouteVal.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblRouteVal.setForeground(new Color(8, 22, 42));
        lblRouteVal.setBounds(130, 45, 270, 15);
        ticketBox.add(lblRouteVal);

        // Flight / Date
        JLabel lblDateLabel = new JLabel("FLIGHT / DATE:");
        lblDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblDateLabel.setForeground(new Color(113, 128, 150));
        lblDateLabel.setBounds(20, 75, 100, 15);
        ticketBox.add(lblDateLabel);

        JLabel lblDateVal = new JLabel(booking.getFlightNo() + "  •  " + booking.getDate() + "  •  " + booking.getTime());
        lblDateVal.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDateVal.setForeground(new Color(8, 22, 42));
        lblDateVal.setBounds(130, 75, 270, 15);
        ticketBox.add(lblDateVal);

        // Passenger Name
        JLabel lblNameLabel = new JLabel("PASSENGER:");
        lblNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblNameLabel.setForeground(new Color(113, 128, 150));
        lblNameLabel.setBounds(20, 105, 100, 15);
        ticketBox.add(lblNameLabel);

        JLabel lblNameVal = new JLabel(booking.getPassengerName());
        lblNameVal.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblNameVal.setForeground(new Color(8, 22, 42));
        lblNameVal.setBounds(130, 105, 270, 15);
        ticketBox.add(lblNameVal);

        // Seat
        JLabel lblSeatLabel = new JLabel("SEAT(S):");
        lblSeatLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblSeatLabel.setForeground(new Color(113, 128, 150));
        lblSeatLabel.setBounds(20, 135, 100, 15);
        ticketBox.add(lblSeatLabel);

        JLabel lblSeatVal = new JLabel("🎟  " + booking.getSeat());
        lblSeatVal.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSeatVal.setForeground(new Color(0, 102, 204));
        lblSeatVal.setBounds(130, 135, 270, 15);
        ticketBox.add(lblSeatVal);

        // Separator
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(226, 232, 240));
        sep.setBounds(20, 165, 380, 2);
        ticketBox.add(sep);

        // Total Spent
        JLabel lblPriceLabel = new JLabel("AMOUNT PAID:");
        lblPriceLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblPriceLabel.setForeground(new Color(113, 128, 150));
        lblPriceLabel.setBounds(20, 185, 100, 20);
        ticketBox.add(lblPriceLabel);

        JLabel lblPriceVal = new JLabel("NPR " + String.format("%,.0f", booking.getAmount()));
        lblPriceVal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPriceVal.setForeground(new Color(25, 127, 54)); // Green
        lblPriceVal.setBounds(130, 185, 270, 20);
        ticketBox.add(lblPriceVal);

        // Status
        JLabel lblStatusLabel = new JLabel("STATUS:");
        lblStatusLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblStatusLabel.setForeground(new Color(113, 128, 150));
        lblStatusLabel.setBounds(20, 212, 100, 15);
        ticketBox.add(lblStatusLabel);

        JLabel lblStatusVal = new JLabel("CONFIRMED");
        lblStatusVal.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblStatusVal.setForeground(new Color(25, 127, 54));
        lblStatusVal.setBounds(130, 212, 270, 15);
        ticketBox.add(lblStatusVal);

        card.add(ticketBox);

        // Done button
        JButton btnDone = new JButton("Go to Dashboard");
        btnDone.setBackground(new Color(8, 22, 42)); // Dark Navy
        btnDone.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnDone.setForeground(Color.WHITE);
        btnDone.setBorderPainted(false);
        btnDone.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDone.setBounds(40, 460, 420, 45);
        btnDone.addActionListener(e -> {
            dashboard.switchPanel(null); // Go to main dashboard homepage
        });
        card.add(btnDone);

        add(card);
    }
}
