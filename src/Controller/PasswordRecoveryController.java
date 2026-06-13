package controller;

import dao.PasswordRecoveryDAO;
import dao.UserDAO;
import model.PasswordRecovery;
import model.User;
import view.passwordrecovery;
import javax.swing.JOptionPane;
import java.sql.Timestamp;
import java.util.Random;

/**
 * Controller class to handle all password recovery events and business logic.
 * Keeps the view clean and separated from business/database logic.
 */
public class PasswordRecoveryController {

    private final passwordrecovery view;
    private final PasswordRecoveryDAO recoveryDAO;
    private final UserDAO userDAO;

    public PasswordRecoveryController(passwordrecovery view) {
        this.view = view;
        this.recoveryDAO = new PasswordRecoveryDAO();
        this.userDAO = new UserDAO();
        initController();
    }

    /**
     * Activates and makes the view visible on the EDT.
     */
    public void activate() {
        java.awt.EventQueue.invokeLater(() -> {
            view.setVisible(true);
        });
    }

    /**
     * Binds action listeners to view components using clean getter accessors.
     */
    private void initController() {
        view.getBtnSendOTP().addActionListener(e -> handleSendOTP());
        view.getBtnVerifyOTP().addActionListener(e -> handleVerifyOTP());
        view.getBtnResetPassword().addActionListener(e -> handleResetPassword());
        view.getBtnBack().addActionListener(e -> handleBack());
    }

    /**
     * Checks if email is registered, generates OTP, saves to DB, and simulates sending.
     */
    private void handleSendOTP() {
        String email = view.getTxtEmail().getText().trim();
        view.getLblError().setText("");

        if (email.isEmpty()) {
            showError("Please enter your email address first.");
            return;
        }

        // Verify user exists in database
        User user = userDAO.getByEmail(email);
        if (user == null) {
            showError("This email address is not registered in our system.");
            return;
        }

        // Clean up old recoveries for this email
        recoveryDAO.delete(email);

        // Generate 6-digit OTP code
        String otp = String.format("%06d", new Random().nextInt(990000) + 10000);

        // OTP expires in 5 minutes
        Timestamp expiry = new Timestamp(System.currentTimeMillis() + (5 * 60 * 1000));

        // Insert into database
        PasswordRecovery recovery = new PasswordRecovery(email, otp, expiry, false);
        if (recoveryDAO.insert(recovery)) {
            JOptionPane.showMessageDialog(view,
                "A verification OTP code has been generated.\n" +
                "For development/testing purposes, your OTP is: " + otp,
                "OTP Sent Successfully", JOptionPane.INFORMATION_MESSAGE);
            view.getLblError().setForeground(new java.awt.Color(16, 185, 129)); // Green
            view.getLblError().setText("OTP sent! Enter it below to verify.");
        } else {
            showError("Failed to generate OTP. Please try again later.");
        }
    }

    /**
     * Verifies the entered OTP against the database and marks it verified.
     */
    private void handleVerifyOTP() {
        String email = view.getTxtEmail().getText().trim();
        String enteredOtp = view.getTxtOTP().getText().trim();
        view.getLblError().setText("");

        if (email.isEmpty() || enteredOtp.isEmpty()) {
            showError("Please enter both email and OTP code.");
            return;
        }

        // Validate that the verification OTP code matches the database record
        PasswordRecovery recovery = recoveryDAO.getLatestByEmail(email);
        if (recovery == null || !recovery.getOtp().equals(enteredOtp)) {
            showError("Invalid verification code. Please check and try again.");
            return;
        }

        // Validate that the code has not expired (5-minute expiration period)
        if (recovery.getExpiryTime().getTime() < System.currentTimeMillis()) {
            showError("The OTP code has expired. Please request a new one.");
            return;
        }

        // Mark verified in database
        recovery.setVerified(true);
        if (recoveryDAO.update(recovery)) {
            view.getLblError().setForeground(new java.awt.Color(16, 185, 129)); // Green
            view.getLblError().setText("OTP verified! You can now reset your password.");
            JOptionPane.showMessageDialog(view,
                "OTP verified successfully!\nYou can now set your new password below.",
                "OTP Verified", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showError("Verification failed. Please try again.");
        }
    }

    /**
     * Resets the user's password in the database if the OTP state is verified.
     */
    private void handleResetPassword() {
        String email = view.getTxtEmail().getText().trim();
        String newPassword = new String(view.getTxtNewPassword().getPassword());
        String confirmPassword = new String(view.getTxtConfirmPassword().getPassword());
        view.getLblError().setText("");

        if (email.isEmpty()) {
            showError("Please enter your email address.");
            return;
        }

        // Verify OTP is marked verified in the database
        PasswordRecovery recovery = recoveryDAO.getLatestByEmail(email);
        if (recovery == null || !recovery.isVerified()) {
            showError("Please enter and verify the OTP code first.");
            return;
        }

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showError("Please fill in both password fields.");
            return;
        }

        if (newPassword.length() < 6) {
            showError("Password must be at least 6 characters long.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showError("Passwords do not match. Please verify.");
            return;
        }

        // Update user password
        User user = userDAO.getByEmail(email);
        if (user != null) {
            user.setPassword(newPassword);
            if (userDAO.update(user)) {
                // Delete verification request after successful reset
                recoveryDAO.delete(email);
                
                JOptionPane.showMessageDialog(view,
                    "Your password has been reset successfully!\n" +
                    "Please sign in with your new password.",
                    "Password Reset Successful", JOptionPane.INFORMATION_MESSAGE);
                
                // Go back to login screen
                handleBack();
            } else {
                showError("Failed to update password. Database error occurred.");
            }
        } else {
            showError("User account not found.");
        }
    }

    /**
     * Closes the recovery frame and returns to login.
     */
    private void handleBack() {
        view.dispose();
        NavigationController.goToLogin(null);
    }

    private void showError(String message) {
        view.getLblError().setForeground(new java.awt.Color(255, 59, 48)); // Red
        view.getLblError().setText(message);
    }
}
