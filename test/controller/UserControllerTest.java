package controller;

import model.User;
import dao.UserDAO;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserControllerTest {

    public UserControllerTest() {
    }

    /**
     * Test of signUp method, of class UserController.
     */
    @Test
    public void testSignUp() {
        System.out.println("signUp");
        UserController instance = new UserController();
        UserDAO userDao = new UserDAO();
        
        // Clean up duplicate if left over
        String email = "signup_test@example.com";
        User existing = userDao.getByEmail(email);
        if (existing != null) {
            userDao.delete(existing.getUserId());
        }

        // 1. Success case
        String result = instance.signUp("Sign Up User", email, "+9779800000100", "password123");
        assertEquals("success", result);

        // 2. Duplicate email check
        assertEquals("Email is already registered.", instance.signUp("Name", email, "9812345678", "password123"));

        // Clean up
        existing = userDao.getByEmail(email);
        if (existing != null) {
            userDao.delete(existing.getUserId());
        }

        // 3. Validation failures
        assertEquals("Full Name cannot be empty.", instance.signUp("", email, "9812345678", "password123"));
        assertEquals("Email cannot be empty.", instance.signUp("Name", "", "9812345678", "password123"));
        assertEquals("Phone Number cannot be empty.", instance.signUp("Name", email, "", "password123"));
        assertEquals("Password cannot be empty.", instance.signUp("Name", email, "9812345678", ""));
        assertEquals("Password must be at least 6 characters long.", instance.signUp("Name", email, "9812345678", "123"));
        assertEquals("Invalid email address format.", instance.signUp("Name", "invalidemail", "9812345678", "password123"));
        assertEquals("Invalid phone number format.", instance.signUp("Name", email, "phone123", "password123"));
    }

    /**
     * Test of signIn method, of class UserController.
     */
    @Test
    public void testSignIn() {
        System.out.println("signIn");
        UserController instance = new UserController();
        UserDAO userDao = new UserDAO();
        
        // Ensure user exists
        String email = "signin_test@example.com";
        User existing = userDao.getByEmail(email);
        if (existing != null) {
            userDao.delete(existing.getUserId());
        }
        User testUser = new User("SignIn Test", email, "9800000001", "password123");
        userDao.insert(testUser);

        // 1. Success case
        User result = instance.signIn(email, "password123");
        assertNotNull(result);
        assertEquals("SignIn Test", result.getFullName());

        // 2. Failure case (wrong password)
        assertNull(instance.signIn(email, "wrongpass"));

        // 3. Failure case (non-existent email)
        assertNull(instance.signIn("nonexistent@example.com", "password123"));

        // Cleanup
        userDao.delete(testUser.getUserId());
    }

    /**
     * Test of generateResetCode method, of class UserController.
     */
    @Test
    public void testGenerateResetCode() {
        System.out.println("generateResetCode");
        UserController instance = new UserController();
        UserDAO userDao = new UserDAO();

        String email = "reset_test@example.com";
        User existing = userDao.getByEmail(email);
        if (existing != null) {
            userDao.delete(existing.getUserId());
        }
        User testUser = new User("Reset Test", email, "9800000002", "password123");
        userDao.insert(testUser);

        // 1. Valid registered email
        String otp = instance.generateResetCode(email);
        assertNotNull(otp);
        assertEquals(6, otp.length());
        assertTrue(otp.matches("^[0-9]{6}$"));

        // 2. Unregistered email
        assertNull(instance.generateResetCode("nonexistent@example.com"));

        // 3. Invalid format
        assertNull(instance.generateResetCode("invalid_email"));

        // Cleanup
        userDao.delete(testUser.getUserId());
    }

    /**
     * Test of recoverPassword method, of class UserController.
     */
    @Test
    public void testRecoverPassword() {
        System.out.println("recoverPassword");
        UserController instance = new UserController();
        UserDAO userDao = new UserDAO();

        String email = "recover_test@example.com";
        User existing = userDao.getByEmail(email);
        if (existing != null) {
            userDao.delete(existing.getUserId());
        }
        User testUser = new User("Recover Test", email, "9800000003", "password123");
        userDao.insert(testUser);

        // 1. Success recovery
        boolean result = instance.recoverPassword(email, "newpassword123");
        assertTrue(result);

        // Verify updated password in database
        User updatedUser = userDao.getById(testUser.getUserId());
        assertEquals("newpassword123", updatedUser.getPassword());

        // 2. Failure: too short password
        assertFalse(instance.recoverPassword(email, "123"));

        // 3. Failure: unregistered email
        assertFalse(instance.recoverPassword("nonexistent@example.com", "newpassword123"));

        // Cleanup
        userDao.delete(testUser.getUserId());
    }

    /**
     * Test of updateProfile method, of class UserController.
     */
    @Test
    public void testUpdateProfile() {
        System.out.println("updateProfile");
        UserController instance = new UserController();
        UserDAO userDao = new UserDAO();

        String email = "updateprofile_test@example.com";
        User existing = userDao.getByEmail(email);
        if (existing != null) {
            userDao.delete(existing.getUserId());
        }
        User testUser = new User("Original Name", email, "9800000004", "password123");
        userDao.insert(testUser);

        // 1. Valid update
        testUser.setFullName("New Name");
        testUser.setPhone("+977 9800000004");
        testUser.setPassword("newpassword");
        String result = instance.updateProfile(testUser);
        assertEquals("success", result);

        // Verify in DB
        User updated = userDao.getById(testUser.getUserId());
        assertEquals("New Name", updated.getFullName());
        assertEquals("+977 9800000004", updated.getPhone());
        assertEquals("newpassword", updated.getPassword());

        // 2. Validation failures
        testUser.setFullName("");
        assertEquals("Full Name cannot be empty.", instance.updateProfile(testUser));
        testUser.setFullName("New Name");

        testUser.setPhone("");
        assertEquals("Phone Number cannot be empty.", instance.updateProfile(testUser));
        testUser.setPhone("New Name"); // invalid format
        assertEquals("Invalid phone number format.", instance.updateProfile(testUser));
        testUser.setPhone("+977 9800000004");

        testUser.setPassword("");
        assertEquals("Password cannot be empty.", instance.updateProfile(testUser));
        testUser.setPassword("123");
        assertEquals("Password must be at least 6 characters long.", instance.updateProfile(testUser));

        // Cleanup
        userDao.delete(testUser.getUserId());
    }
}
