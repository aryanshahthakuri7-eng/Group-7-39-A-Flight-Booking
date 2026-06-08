package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.BorderFactory;

public class SearchResultsPanel extends JPanel {
    private final Dashboard dashboard;
    private final String fromCode;
    private final String toCode;
    private final String departDate;
    private final int passengerCount;

    public SearchResultsPanel(Dashboard dashboard, String from, String to, String date, int passengers) {
        this.dashboard = dashboard;
        this.fromCode = from.contains(" - ") ? from.split(" - ")[0] : from;
        this.toCode = to.contains(" - ") ? to.split(" - ")[0] : to;
        this.departDate = date;
        this.passengerCount = passengers;

        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(248, 249, 255)); // Light background
        setLayout(null);
        setSize(780, 630);

        // Back Button
        JLabel lblBack = new JLabel("← Back to Search");
        lblBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblBack.setForeground(new Color(245, 130, 32)); // Orange accent
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBack.setBounds(30, 20, 150, 20);
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard.switchPanel(null); // Go back to main dashboard
            }
        });
        add(lblBack);

        // Header Title
        JLabel lblTitle = new JLabel("Available Flights");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(8, 22, 42)); // Primary Navy
        lblTitle.setBounds(30, 50, 300, 30);
        add(lblTitle);

        // Search Info Summary
        JLabel lblSummary = new JLabel(fromCode + " to " + toCode + "  •  " + departDate + "  •  " + passengerCount + " Passenger(s)");
        lblSummary.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSummary.setForeground(new Color(113, 128, 150));
        lblSummary.setBounds(30, 80, 500, 20);
        add(lblSummary);

        // Create 3 dummy flight options
        int yOffset = 120;
        
        // Option 1: Morning
        addFlightCard("YS102", "07:30 AM", "08:30 AM", 4500.0 * passengerCount, yOffset);
        yOffset += 150;

        // Option 2: Afternoon
        addFlightCard("YS105", "01:15 PM", "02:15 PM", 5200.0 * passengerCount, yOffset);
        yOffset += 150;

        // Option 3: Evening
        addFlightCard("YS109", "06:45 PM", "07:45 PM", 4800.0 * passengerCount, yOffset);
    }

    private void addFlightCard(String flightNo, String depTime, String arrTime, double price, int y) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        card.setLayout(null);
        card.setBounds(30, y, 720, 130);

        // Left section: Carrier Logo & Flight Number
        JPanel logoBox = new JPanel();
        logoBox.setBackground(new Color(248, 249, 255));
        logoBox.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));
        logoBox.setLayout(null);
        logoBox.setBounds(20, 25, 50, 50);
        
        JLabel logoIcon = new JLabel("▼");
        logoIcon.setFont(new Font("Segoe UI", Font.BOLD, 18));
        logoIcon.setForeground(new Color(8, 22, 42));
        logoIcon.setHorizontalAlignment(JLabel.CENTER);
        logoIcon.setBounds(0, 0, 50, 50);
        logoBox.add(logoIcon);
        card.add(logoBox);

        JLabel lblFlightNo = new JLabel(flightNo);
        lblFlightNo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblFlightNo.setForeground(new Color(113, 128, 150));
        lblFlightNo.setHorizontalAlignment(JLabel.CENTER);
        lblFlightNo.setBounds(20, 80, 50, 20);
        card.add(lblFlightNo);

        // Middle section: Route and Schedule
        JLabel lblFromTime = new JLabel(depTime);
        lblFromTime.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblFromTime.setForeground(new Color(8, 22, 42));
        lblFromTime.setBounds(100, 35, 100, 25);
        card.add(lblFromTime);

        JLabel lblFromCity = new JLabel(fromCode);
        lblFromCity.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblFromCity.setForeground(new Color(113, 128, 150));
        lblFromCity.setBounds(100, 60, 100, 20);
        card.add(lblFromCity);

        // Arrow and Duration representation
        JLabel lblArrow = new JLabel("────────────────►");
        lblArrow.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblArrow.setForeground(new Color(203, 213, 224));
        lblArrow.setBounds(210, 38, 200, 20);
        card.add(lblArrow);

        JLabel lblDuration = new JLabel("1H 00M (Direct)");
        lblDuration.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblDuration.setForeground(new Color(113, 128, 150));
        lblDuration.setHorizontalAlignment(JLabel.CENTER);
        lblDuration.setBounds(210, 58, 200, 15);
        card.add(lblDuration);

        JLabel lblToTime = new JLabel(arrTime);
        lblToTime.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblToTime.setForeground(new Color(8, 22, 42));
        lblToTime.setBounds(420, 35, 100, 25);
        card.add(lblToTime);

        JLabel lblToCity = new JLabel(toCode);
        lblToCity.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblToCity.setForeground(new Color(113, 128, 150));
        lblToCity.setBounds(420, 60, 100, 20);
        card.add(lblToCity);

        // Vertical separator
        JSeparator sep = new JSeparator(JSeparator.VERTICAL);
        sep.setForeground(new Color(226, 232, 240));
        sep.setBounds(540, 15, 2, 100);
        card.add(sep);

        // Right section: Price & Action
        JLabel lblPriceTag = new JLabel("NPR " + String.format("%,.0f", price));
        lblPriceTag.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPriceTag.setForeground(new Color(8, 22, 42));
        lblPriceTag.setHorizontalAlignment(JLabel.CENTER);
        lblPriceTag.setBounds(550, 25, 160, 30);
        card.add(lblPriceTag);

        JButton btnSelect = new JButton("Select Flight");
        btnSelect.setBackground(new Color(245, 130, 32)); // Orange Button
        btnSelect.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnSelect.setForeground(Color.WHITE);
        btnSelect.setBorderPainted(false);
        btnSelect.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSelect.setBounds(565, 65, 130, 35);
        
        btnSelect.addActionListener(e -> {
            // Transition to seat selection panel
            dashboard.switchPanel(new SeatSelectionPanel(dashboard, flightNo, depTime + " - " + arrTime, fromCode, toCode, departDate, passengerCount, price));
        });
        
        card.add(btnSelect);

        add(card);
    }
}
