package view;

import controller.TicketController;
import model.Booking;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * View class representing the flight boarding pass receipt window.
 */
public class TicketWindow extends javax.swing.JFrame {

    private final TicketController ticketController;
    private final Booking booking;

    public TicketWindow(Booking booking) {
        this.ticketController = new TicketController();
        this.booking = booking;

        initComponents();
        getContentPane().setBackground(new java.awt.Color(20, 28, 35)); // Navy Blue
        
        // Custom stylings
        pnlTicketCard.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        pnlSeparator.setBackground(new java.awt.Color(241, 245, 249));
        
        btnPrint.setContentAreaFilled(true);
        btnPrint.setBackground(new java.awt.Color(255, 122, 26)); // Orange Accent
        btnPrint.setForeground(java.awt.Color.WHITE);
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnDownload.setContentAreaFilled(true);
        btnDownload.setBackground(new java.awt.Color(34, 197, 94)); // Green
        btnDownload.setForeground(java.awt.Color.WHITE);
        btnDownload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnClose.setContentAreaFilled(true);
        btnClose.setBackground(java.awt.Color.WHITE);
        btnClose.setForeground(new java.awt.Color(92, 100, 112));
        btnClose.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(226, 232, 240), 1, true));
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblStatusVal.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            new javax.swing.border.LineBorder(new java.awt.Color(230, 244, 234), 1, true),
            javax.swing.BorderFactory.createEmptyBorder(2, 6, 2, 6)
        ));

        populateTicketData();
    }

    private void populateTicketData() {
        if (booking != null) {
            lblBookingIdVal.setText(booking.getBookingId());
            lblPassengerVal.setText(booking.getPassengerName());
            lblFlightVal.setText(booking.getFlightCode());
            lblRouteVal.setText(booking.getRoute());
            lblDateVal.setText(booking.getDepartureDate());
            lblTimeVal.setText(booking.getDepartureTime());
            lblSeatVal.setText(booking.getSeatNumber());
            lblAmountVal.setText(booking.getFormattedAmount());
            lblStatusVal.setText(booking.getStatus());
            
            if ("CANCELLED".equalsIgnoreCase(booking.getStatus())) {
                lblStatusVal.setForeground(new java.awt.Color(239, 68, 68)); // Red
                lblStatusVal.setBackground(new java.awt.Color(254, 242, 242));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTicketCard = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        lblSubHeader = new javax.swing.JLabel();
        pnlSeparator = new javax.swing.JPanel();
        lblBookingIdLabel = new javax.swing.JLabel();
        lblBookingIdVal = new javax.swing.JLabel();
        lblPassengerLabel = new javax.swing.JLabel();
        lblPassengerVal = new javax.swing.JLabel();
        lblFlightLabel = new javax.swing.JLabel();
        lblFlightVal = new javax.swing.JLabel();
        lblRouteLabel = new javax.swing.JLabel();
        lblRouteVal = new javax.swing.JLabel();
        lblDateLabel = new javax.swing.JLabel();
        lblDateVal = new javax.swing.JLabel();
        lblTimeLabel = new javax.swing.JLabel();
        lblTimeVal = new javax.swing.JLabel();
        lblSeatLabel = new javax.swing.JLabel();
        lblSeatVal = new javax.swing.JLabel();
        lblAmountLabel = new javax.swing.JLabel();
        lblAmountVal = new javax.swing.JLabel();
        lblStatusLabel = new javax.swing.JLabel();
        lblStatusVal = new javax.swing.JLabel();
        lblQrPlaceholder = new javax.swing.JLabel();
        btnPrint = new javax.swing.JButton();
        btnDownload = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        pnlBg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Yatra Air Sewa - Boarding Pass");
        setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().setLayout(null);

        pnlTicketCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlTicketCard.setLayout(null);

        lblHeader.setText("YATRA AIR SEWA");
        lblHeader.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(20, 28, 35));
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlTicketCard.add(lblHeader);
        lblHeader.setBounds(20, 15, 390, 30);

        lblSubHeader.setText("Official Flight Boarding Ticket");
        lblSubHeader.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblSubHeader.setForeground(new java.awt.Color(113, 128, 150));
        lblSubHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlTicketCard.add(lblSubHeader);
        lblSubHeader.setBounds(20, 45, 390, 15);

        pnlSeparator.setBackground(new java.awt.Color(235, 235, 240));
        pnlTicketCard.add(pnlSeparator);
        pnlSeparator.setBounds(20, 70, 390, 2);

        lblBookingIdLabel.setText("BOOKING ID");
        lblBookingIdLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblBookingIdLabel.setForeground(new java.awt.Color(120, 140, 160));
        pnlTicketCard.add(lblBookingIdLabel);
        lblBookingIdLabel.setBounds(30, 90, 100, 15);

        lblBookingIdVal.setText("BK000");
        lblBookingIdVal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblBookingIdVal.setForeground(new java.awt.Color(20, 28, 35));
        pnlTicketCard.add(lblBookingIdVal);
        lblBookingIdVal.setBounds(30, 105, 100, 20);

        lblPassengerLabel.setText("PASSENGER NAME");
        lblPassengerLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblPassengerLabel.setForeground(new java.awt.Color(120, 140, 160));
        pnlTicketCard.add(lblPassengerLabel);
        lblPassengerLabel.setBounds(150, 90, 150, 15);

        lblPassengerVal.setText("Passenger");
        lblPassengerVal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblPassengerVal.setForeground(new java.awt.Color(20, 28, 35));
        pnlTicketCard.add(lblPassengerVal);
        lblPassengerVal.setBounds(150, 105, 150, 20);

        lblFlightLabel.setText("FLIGHT");
        lblFlightLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblFlightLabel.setForeground(new java.awt.Color(120, 140, 160));
        pnlTicketCard.add(lblFlightLabel);
        lblFlightLabel.setBounds(320, 90, 80, 15);

        lblFlightVal.setText("YS000");
        lblFlightVal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblFlightVal.setForeground(new java.awt.Color(20, 28, 35));
        pnlTicketCard.add(lblFlightVal);
        lblFlightVal.setBounds(320, 105, 80, 20);

        lblRouteLabel.setText("ROUTE");
        lblRouteLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblRouteLabel.setForeground(new java.awt.Color(120, 140, 160));
        pnlTicketCard.add(lblRouteLabel);
        lblRouteLabel.setBounds(30, 145, 200, 15);

        lblRouteVal.setText("KTM → PKR");
        lblRouteVal.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblRouteVal.setForeground(new java.awt.Color(20, 28, 35));
        pnlTicketCard.add(lblRouteVal);
        lblRouteVal.setBounds(30, 160, 260, 25);

        lblDateLabel.setText("DEPART DATE");
        lblDateLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblDateLabel.setForeground(new java.awt.Color(120, 140, 160));
        pnlTicketCard.add(lblDateLabel);
        lblDateLabel.setBounds(30, 200, 100, 15);

        lblDateVal.setText("28 APR 2026");
        lblDateVal.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDateVal.setForeground(new java.awt.Color(20, 28, 35));
        pnlTicketCard.add(lblDateVal);
        lblDateVal.setBounds(30, 215, 110, 20);

        lblTimeLabel.setText("DEPART TIME");
        lblTimeLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblTimeLabel.setForeground(new java.awt.Color(120, 140, 160));
        pnlTicketCard.add(lblTimeLabel);
        lblTimeLabel.setBounds(150, 200, 100, 15);

        lblTimeVal.setText("10:00 AM");
        lblTimeVal.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblTimeVal.setForeground(new java.awt.Color(20, 28, 35));
        pnlTicketCard.add(lblTimeVal);
        lblTimeVal.setBounds(150, 215, 100, 20);

        lblSeatLabel.setText("SEAT NUMBER");
        lblSeatLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblSeatLabel.setForeground(new java.awt.Color(120, 140, 160));
        pnlTicketCard.add(lblSeatLabel);
        lblSeatLabel.setBounds(270, 200, 100, 15);

        lblSeatVal.setText("A12");
        lblSeatVal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSeatVal.setForeground(new java.awt.Color(20, 28, 35));
        pnlTicketCard.add(lblSeatVal);
        lblSeatVal.setBounds(270, 215, 100, 20);

        lblAmountLabel.setText("AMOUNT");
        lblAmountLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblAmountLabel.setForeground(new java.awt.Color(120, 140, 160));
        pnlTicketCard.add(lblAmountLabel);
        lblAmountLabel.setBounds(30, 255, 100, 15);

        lblAmountVal.setText("NPR 5,000");
        lblAmountVal.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblAmountVal.setForeground(new java.awt.Color(20, 28, 35));
        pnlTicketCard.add(lblAmountVal);
        lblAmountVal.setBounds(30, 270, 110, 20);

        lblStatusLabel.setText("TICKET STATUS");
        lblStatusLabel.setFont(new java.awt.Font("SansSerif", 1, 9)); // NOI18N
        lblStatusLabel.setForeground(new java.awt.Color(120, 140, 160));
        pnlTicketCard.add(lblStatusLabel);
        lblStatusLabel.setBounds(150, 255, 100, 15);

        lblStatusVal.setText("CONFIRMED");
        lblStatusVal.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblStatusVal.setForeground(new java.awt.Color(16, 185, 129));
        lblStatusVal.setBackground(new java.awt.Color(235, 255, 235));
        lblStatusVal.setOpaque(true);
        lblStatusVal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlTicketCard.add(lblStatusVal);
        lblStatusVal.setBounds(150, 270, 100, 20);

        lblQrPlaceholder.setText("[QR CODE PLACEHOLDER]");
        lblQrPlaceholder.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lblQrPlaceholder.setForeground(new java.awt.Color(113, 128, 150));
        lblQrPlaceholder.setBackground(new java.awt.Color(247, 247, 250));
        lblQrPlaceholder.setOpaque(true);
        lblQrPlaceholder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlTicketCard.add(lblQrPlaceholder);
        lblQrPlaceholder.setBounds(30, 310, 370, 100);

        getContentPane().add(pnlTicketCard);
        pnlTicketCard.setBounds(35, 30, 430, 430);

        btnPrint.setText("Print Ticket");
        btnPrint.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setBackground(new java.awt.Color(255, 115, 21));
        btnPrint.addActionListener(this::btnPrintActionPerformed);
        getContentPane().add(btnPrint);
        btnPrint.setBounds(35, 480, 130, 40);

        btnDownload.setText("Download PDF");
        btnDownload.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnDownload.setForeground(new java.awt.Color(255, 255, 255));
        btnDownload.setBackground(new java.awt.Color(16, 181, 94));
        btnDownload.addActionListener(this::btnDownloadActionPerformed);
        getContentPane().add(btnDownload);
        btnDownload.setBounds(185, 480, 130, 40);

        btnClose.setText("Close");
        btnClose.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnClose.setForeground(new java.awt.Color(92, 100, 112));
        btnClose.addActionListener(this::btnCloseActionPerformed);
        getContentPane().add(btnClose);
        btnClose.setBounds(335, 480, 130, 40);

        pnlBg.setBackground(new java.awt.Color(20, 28, 35));
        getContentPane().add(pnlBg);
        pnlBg.setBounds(0, 0, 500, 600);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        boolean success = ticketController.printTicket(booking);
        if (success) {
            JOptionPane.showMessageDialog(this, "Boarding pass print stream sent successfully.", "Print Status", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Boarding Pass");
        fileChooser.setSelectedFile(new File(booking.getBookingId() + "_boarding_pass.txt"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            boolean success = ticketController.downloadTicket(booking, fileToSave);
            if (success) {
                JOptionPane.showMessageDialog(this, "Boarding pass saved to: " + fileToSave.getAbsolutePath(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDownloadActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose(); // Close only this window
    }//GEN-LAST:event_btnCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel lblAmountLabel;
    private javax.swing.JLabel lblAmountVal;
    private javax.swing.JLabel lblBookingIdLabel;
    private javax.swing.JLabel lblBookingIdVal;
    private javax.swing.JLabel lblDateLabel;
    private javax.swing.JLabel lblDateVal;
    private javax.swing.JLabel lblFlightLabel;
    private javax.swing.JLabel lblFlightVal;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblPassengerLabel;
    private javax.swing.JLabel lblPassengerVal;
    private javax.swing.JLabel lblQrPlaceholder;
    private javax.swing.JLabel lblRouteLabel;
    private javax.swing.JLabel lblRouteVal;
    private javax.swing.JLabel lblSeatLabel;
    private javax.swing.JLabel lblSeatVal;
    private javax.swing.JLabel lblStatusLabel;
    private javax.swing.JLabel lblStatusVal;
    private javax.swing.JLabel lblSubHeader;
    private javax.swing.JLabel lblTimeLabel;
    private javax.swing.JLabel lblTimeVal;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JPanel pnlSeparator;
    private javax.swing.JPanel pnlTicketCard;
    // End of variables declaration//GEN-END:variables
}
