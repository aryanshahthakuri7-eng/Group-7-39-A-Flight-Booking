package controller;

import dao.UserDAO;
import model.User;
import javax.swing.JOptionPane;

/**
 * Controller class to handle all password recovery events and business logic.
 * Keeps the view clean and separated from business/database logic.
 */
public class PasswordRecoveryController {

    private final view.PasswordRecovery view;
    private final UserDAO userDAO;

    // Temporary storage for fetched user to verify answer
    private User pendingRecoveryUser = null;

    public PasswordRecoveryController(view.PasswordRecovery view) {
        this.view = view;
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
        view.getBtnFetchQuestion().addActionListener(e -> handleFetchQuestion());
        view.getBtnResetPassword().addActionListener(e -> handleResetPassword());
        view.getBtnBack().addActionListener(e -> handleBack());
    }

    /**
     * Checks if email is registered and retrieves the security question.
     */
    private void handleFetchQuestion() {
        String email = view.getTxtEmail().getText().trim();
        view.getLblError().setText("");

        if (email.isEmpty() || email.equals("user@example.com")) {
            showError("Please enter your email address first.");
            return;
        }

        // Verify user exists in database
        User user = userDAO.getByEmail(email);
        if (user == null) {
            showError("This email address is not registered in our system.");
            view.getLblSecurityQuestionValue().setText("Retrieve your question first.");
            pendingRecoveryUser = null;
            return;
        }

        pendingRecoveryUser = user;
        String question = user.getSecurityQuestion();
        
        if (question == null || question.trim().isEmpty() || question.equals("Security Question")) {
            showError("No security question set for this account. Contact Support.");
            view.getLblSecurityQuestionValue().setText("Retrieve your question first.");
            return;
        }

        view.getLblSecurityQuestionValue().setText(question);
        view.getLblError().setForeground(new java.awt.Color(16, 185, 129)); // Green
        view.getLblError().setText("Question fetched. Please enter the answer.");
    }

    /**
     * Verifies the answer and resets the user's password in the database.
     */
    private void handleResetPassword() {
        String email = view.getTxtEmail().getText().trim();
        String answer = view.getTxtSecurityAnswer().getText().trim();
        String newPassword = new String(view.getTxtNewPassword().getPassword());
        String confirmPassword = new String(view.getTxtConfirmPassword().getPassword());
        
        view.getLblError().setText("");

        if (pendingRecoveryUser == null || !pendingRecoveryUser.getEmail().equals(email)) {
            showError("Please fetch your security question first.");
            return;
        }

        if (answer.isEmpty() || answer.equals("Enter Security Answer")) {
            showError("Please enter your security answer.");
            return;
        }

        // Case insensitive match for security answer
        if (!pendingRecoveryUser.getSecurityAnswer().equalsIgnoreCase(answer)) {
            showError("Incorrect security answer.");
            return;
        }

        if (newPassword.isEmpty() || confirmPassword.isEmpty() || newPassword.equals("New Password")) {
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
        pendingRecoveryUser.setPassword(newPassword);
        if (userDAO.update(pendingRecoveryUser)) {
            JOptionPane.showMessageDialog(view,
                "Your password has been reset successfully!\n" +
                "Please sign in with your new password.",
                "Password Reset Successful", JOptionPane.INFORMATION_MESSAGE);
            
            // Go back to Login screen
            handleBack();
        } else {
            showError("Failed to update password. Database error occurred.");
        }
    }

    /**
     * Closes the recovery frame and returns to Login.
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
