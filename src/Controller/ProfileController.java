package controller;

import dao.UserDAO;
import model.User;
import model.SessionManager;

/**
 * Controller to handle profile management operations.
 */
public class ProfileController {

    private final UserDAO userDAO;

    public ProfileController() {
        this.userDAO = new UserDAO();
    }

    /**
     * Gets profile details of currently logged-in user.
     */
    public User getLoggedInUserProfile() {
        User sessionUser = SessionManager.getCurrentUser();
        return userDAO.getById(sessionUser.getUserId());
    }

    /**
     * Saves edits to user profile.
     */
    public boolean updateProfile(String fullName, String email, String phone) {
        if (fullName == null || fullName.trim().isEmpty() ||
            email == null || email.trim().isEmpty()) {
            return false;
        }

        User user = SessionManager.getCurrentUser();
        user.setFullName(fullName.trim());
        user.setEmail(email.trim());
        user.setPhone(phone != null ? phone.trim() : "");

        boolean success = userDAO.update(user);
        if (success) {
            // Update session cache
            SessionManager.setCurrentUser(user);
        }
        return success;
    }
}
