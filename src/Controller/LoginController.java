package controller;

import dao.UserDAO;
import model.User;
import model.SessionManager;

/**
 * Controller to validate user login and establish a user session.
 */
public class LoginController {

    private final UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

    /**
     * Validates user credentials. If correct, starts a session and returns true.
     */
    public boolean login(String email, String password) {
        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            return false;
        }

        User user = userDAO.getByEmailAndPassword(email.trim(), password);
        if (user != null) {
            SessionManager.setCurrentUser(user);
            return true;
        }
        return false;
    }
}
