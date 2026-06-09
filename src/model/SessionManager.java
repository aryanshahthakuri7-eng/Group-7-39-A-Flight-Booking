package model;

/**
 * SessionManager stores the currently logged-in user session.
 * Used for authentication context and loading user-specific data.
 */
public class SessionManager {

    private static User currentUser = null;

    /**
     * Set the current logged-in user.
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Get the currently logged-in user.
     * Defaults to Gaurav Chandra (user_id=1) if session is empty to prevent crashes on direct view loads.
     */
    public static User getCurrentUser() {
        if (currentUser == null) {
            User defaultUser = new User();
            defaultUser.setUserId(1);
            defaultUser.setFullName("Gaurav Chandra");
            defaultUser.setEmail("gaurav.chandra@gmail.com");
            defaultUser.setPassword("password123");
            defaultUser.setPhone("+977 9812345678");
            currentUser = defaultUser;
        }
        return currentUser;
    }

    /**
     * Clears user session on logout.
     */
    public static void clearSession() {
        currentUser = null;
    }
    
    /**
     * Check if a user is logged in.
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
