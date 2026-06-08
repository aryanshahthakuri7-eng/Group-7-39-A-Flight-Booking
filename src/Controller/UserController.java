package Controller;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;

public class UserController {
    private final UserDao userDao;

    public UserController() {
        this.userDao = new UserDaoImpl();
    }

    /**
     * Attempts to register a new user.
     * Returns a message string: "success" or details about the failure.
     */
    public String signUp(String fullname, String email, String phone, String password) {
        // Basic Validations
        if (fullname == null || fullname.trim().isEmpty()) {
            return "Full Name cannot be empty.";
        }
        if (email == null || email.trim().isEmpty()) {
            return "Email cannot be empty.";
        }
        if (phone == null || phone.trim().isEmpty()) {
            return "Phone Number cannot be empty.";
        }
        if (password == null || password.trim().isEmpty()) {
            return "Password cannot be empty.";
        }
        if (password.length() < 6) {
            return "Password must be at least 6 characters long.";
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "Invalid email address format.";
        }
        if (!phone.matches("^[0-9+() -]+$")) {
            return "Invalid phone number format.";
        }

        // Check duplicate email
        if (userDao.getUserByEmail(email.trim()) != null) {
            return "Email is already registered.";
        }

        // Create and save user
        User user = new User(fullname.trim(), email.trim(), phone.trim(), password);
        boolean saved = userDao.createUser(user);
        
        return saved ? "success" : "Failed to save user in database. Please check connection.";
    }

    /**
     * Authenticates a user.
     * Returns the authenticated User object on success, or null on failure.
     */
    public User signIn(String email, String password) {
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return null;
        }

        User user = userDao.getUserByEmail(email.trim());
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    /**
     * Checks if email is registered and returns a generated 6-digit OTP code.
     * Returns null if email is not found or invalid.
     */
    public String generateResetCode(String email) {
        if (email == null || email.trim().isEmpty()) {
            return null;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return null;
        }
        
        User user = userDao.getUserByEmail(email.trim());
        if (user == null) {
            return null;
        }
        
        // Generate 6-digit OTP
        int otp = 100000 + (int)(Math.random() * 900000);
        return String.valueOf(otp);
    }

    /**
     * Resets password if email exists.
     * Returns true if reset succeeded, false otherwise.
     */
    public boolean recoverPassword(String email, String newPassword) {
        if (email == null || email.trim().isEmpty() || newPassword == null || newPassword.trim().isEmpty()) {
            return false;
        }
        if (newPassword.length() < 6) {
            return false;
        }

        User user = userDao.getUserByEmail(email.trim());
        if (user == null) {
            return false;
        }

        return userDao.updatePassword(email.trim(), newPassword);
    }

    /**
     * Updates user profile info in the database.
     * Returns a status string: "success" or error message.
     */
    public String updateProfile(User user) {
        if (user == null) {
            return "User session is invalid.";
        }
        if (user.getFullname() == null || user.getFullname().trim().isEmpty()) {
            return "Full Name cannot be empty.";
        }
        if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
            return "Phone Number cannot be empty.";
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return "Password cannot be empty.";
        }
        if (user.getPassword().length() < 6) {
            return "Password must be at least 6 characters long.";
        }
        if (!user.getPhone().matches("^[0-9+() -]+$")) {
            return "Invalid phone number format.";
        }

        boolean updated = userDao.updateUser(user);
        return updated ? "success" : "Failed to update profile in database.";
    }
}
