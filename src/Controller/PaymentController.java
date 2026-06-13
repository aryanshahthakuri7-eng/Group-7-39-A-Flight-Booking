package controller;

import model.PaymentModel;
import view.PaymentFrame;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

/**
 * Controller linking PaymentModel and PaymentFrame.
 * Implements form validation, interactive tab toggling, and payment completion popups.
 */
public class PaymentController {

    private final PaymentFrame view;

    public PaymentController(PaymentFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Tab switching click handlers
        view.getLblTabCard().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.setTabActive(true);
            }
        });

        view.getLblTabMobile().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.setTabActive(false);
            }
        });

        // Confirm & Pay button action listener
        view.getBtnConfirmPay().addActionListener(e -> handlePayment());
    }

    private void handlePayment() {
        if (view.isCardTabActive()) {
            // Validate Credit/Debit Card Form
            String cardHolderName = view.getCardHolderNameInput();
            String cardNumber = view.getCardNumberInput();
            String expiryDate = view.getExpiryDateInput();
            String cvv = view.getCvvInput();
            boolean saveCard = view.getChkSaveCard().isSelected();

            if (cardHolderName.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter the cardholder name.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtCardHolderName().requestFocus();
                return;
            }

            if (!cardHolderName.matches("^[a-zA-Z\\s]{3,50}$")) {
                JOptionPane.showMessageDialog(view, "Cardholder name must contain only letters and be at least 3 characters long.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtCardHolderName().requestFocus();
                return;
            }

            // Strip spaces from card number
            String rawCardNo = cardNumber.replaceAll("\\s+", "");
            if (rawCardNo.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter your card number.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtCardNumber().requestFocus();
                return;
            }

            if (!rawCardNo.matches("^\\d{16}$")) {
                JOptionPane.showMessageDialog(view, "Card number must contain exactly 16 digits.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtCardNumber().requestFocus();
                return;
            }

            // Validate Expiry Date
            if (expiryDate.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter card expiry date (MM/YY).", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtExpiryDate().requestFocus();
                return;
            }

            if (!expiryDate.matches("^(0[1-9]|1[0-2])\\s*/\\s*\\d{2}$")) {
                JOptionPane.showMessageDialog(view, "Expiry date must be in MM/YY format.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtExpiryDate().requestFocus();
                return;
            }

            // Parse Month and Year (Assuming current system is in June 2026 based on metadata)
            String[] parts = expiryDate.split("/");
            int expMonth = Integer.parseInt(parts[0].trim());
            int expYear = Integer.parseInt(parts[1].trim()) + 2000; // e.g. "26" -> 2026

            int curMonth = 6;  // June
            int curYear = 2026; // 2026

            if (expYear < curYear || (expYear == curYear && expMonth < curMonth)) {
                JOptionPane.showMessageDialog(view, "The card has expired. Please use a valid card.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtExpiryDate().requestFocus();
                return;
            }

            // Validate CVV
            if (cvv.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter your CVV.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtCvv().requestFocus();
                return;
            }

            if (!cvv.matches("^\\d{3}$")) {
                JOptionPane.showMessageDialog(view, "CVV must be exactly 3 digits.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtCvv().requestFocus();
                return;
            }

            // All card details are valid
            PaymentModel model = new PaymentModel(cardHolderName, rawCardNo, expiryDate, cvv, saveCard);
            
            // Process payment transaction mock success
            String successMsg = String.format(
                "Payment of NPR 10,400 completed successfully!\nCardholder: %s\nCard: XXXX-XXXX-XXXX-%s\nSave for future: %s",
                model.getCardHolderName(),
                model.getCardNumber().substring(12),
                model.isSaveCard() ? "Yes" : "No"
            );
            JOptionPane.showMessageDialog(view, successMsg, "Payment Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Navigate back to Dashboard or Bookings
            NavigationController.goToDashboard(view);

        } else {
            // Validate Mobile Wallet / Bank Transfer Form
            String walletId = view.getWalletIdInput();
            String method = view.getCmbMobileWallet().getSelectedItem().toString();

            if (walletId.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter your mobile wallet number or account ID.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtWalletId().requestFocus();
                return;
            }

            if (!walletId.matches("^[a-zA-Z0-9]{8,25}$")) {
                JOptionPane.showMessageDialog(view, "Account/Wallet ID must be alphanumeric and between 8 to 25 characters long.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                view.getTxtWalletId().requestFocus();
                return;
            }

            // Process wallet payment success
            String successMsg = String.format(
                "Payment of NPR 10,400 completed successfully via %s!\nWallet/Account ID: %s",
                method,
                walletId
            );
            JOptionPane.showMessageDialog(view, successMsg, "Payment Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Navigate back to Dashboard
            NavigationController.goToDashboard(view);
        }
    }
}
