package controller;

import dao.UserDAO;
import model.User;
import model.SessionManager;

/**
 * Controller to validate user Login and establish a user session.
 */
public class LoginController {

    private final UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

    /**
     * Validates user credentials. If correct, starts a session and returns true.
     */
    public boolean Login(String email, String password) {
        // Validate that fields are not empty or null before invoking the database query
        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            return false;
        }

        // Check if user exists with matching email and password in database
        User user = userDAO.getByEmailAndPassword(email.trim(), password);
        if (user != null) {
            // Establish global user session for application views
            SessionManager.setCurrentUser(user);
            return true;
        }
        return false;
    }
}
