package dao;

import model.User;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class UserDAOTest {

    private final UserDAO userDao = new UserDAO();

    @Test
    public void testInsertAndGet() {
        String uniqueSuffix = String.valueOf(System.currentTimeMillis());
        String email = "test_" + uniqueSuffix + "@example.com";
        User user = new User("Test User", email, "+9779800000000", "password123");

        // Insert
        boolean inserted = userDao.insert(user);
        assertTrue("User insertion should succeed", inserted);
        assertTrue("Inserted user ID should be generated and set", user.getUserId() > 0);

        // Get by ID
        User fetchedById = userDao.getById(user.getUserId());
        assertNotNull("Fetched user by ID should not be null", fetchedById);
        assertEquals("Test User", fetchedById.getFullName());
        assertEquals(email, fetchedById.getEmail());

        // Get by Email
        User fetchedByEmail = userDao.getByEmail(email);
        assertNotNull("Fetched user by email should not be null", fetchedByEmail);
        assertEquals(user.getUserId(), fetchedByEmail.getUserId());

        // Cleanup
        boolean deleted = userDao.delete(user.getUserId());
        assertTrue("User deletion for cleanup should succeed", deleted);
    }

    @Test
    public void testUpdate() {
        String uniqueSuffix = String.valueOf(System.currentTimeMillis());
        String email = "update_" + uniqueSuffix + "@example.com";
        User user = new User("Original Name", email, "+9779800000001", "originalpass");

        // Insert
        userDao.insert(user);

        // Modify & Update
        user.setFullName("Updated Name");
        user.setPhone("+9779800000002");
        user.setPassword("newsecurepass");
        boolean updated = userDao.update(user);
        assertTrue("User update should succeed", updated);

        // Verify changes
        User fetched = userDao.getById(user.getUserId());
        assertNotNull(fetched);
        assertEquals("Updated Name", fetched.getFullName());
        assertEquals("+9779800000002", fetched.getPhone());
        assertEquals("newsecurepass", fetched.getPassword());

        // Cleanup
        userDao.delete(user.getUserId());
    }

    @Test
    public void testGetByEmailAndPassword() {
        String uniqueSuffix = String.valueOf(System.currentTimeMillis());
        String email = "auth_" + uniqueSuffix + "@example.com";
        User user = new User("Auth User", email, "+9779800000003", "authpassword");

        // Insert
        userDao.insert(user);

        // Valid Auth
        User authenticated = userDao.getByEmailAndPassword(email, "authpassword");
        assertNotNull("Authentication should succeed with valid credentials", authenticated);
        assertEquals(user.getUserId(), authenticated.getUserId());

        // Invalid Auth (wrong password)
        User wrongPass = userDao.getByEmailAndPassword(email, "wrongpassword");
        assertNull("Authentication should fail with incorrect password", wrongPass);

        // Invalid Auth (non-existent email)
        User nonExistent = userDao.getByEmailAndPassword("nonexistent_" + uniqueSuffix + "@example.com", "authpassword");
        assertNull("Authentication should fail with non-existent email", nonExistent);

        // Cleanup
        userDao.delete(user.getUserId());
    }

    @Test
    public void testGetAll() {
        String uniqueSuffix = String.valueOf(System.currentTimeMillis());
        String email1 = "all1_" + uniqueSuffix + "@example.com";
        String email2 = "all2_" + uniqueSuffix + "@example.com";

        User u1 = new User("A Test User 1", email1, "+9779800000004", "pass1");
        User u2 = new User("B Test User 2", email2, "+9779800000005", "pass2");

        // Insert both
        userDao.insert(u1);
        userDao.insert(u2);

        // Get all
        List<User> users = userDao.getAll();
        assertNotNull(users);
        assertTrue("User list should contain at least the 2 newly added users", users.size() >= 2);

        // Verify presence
        boolean foundU1 = false;
        boolean foundU2 = false;
        for (User u : users) {
            if (u.getUserId() == u1.getUserId()) foundU1 = true;
            if (u.getUserId() == u2.getUserId()) foundU2 = true;
        }
        assertTrue("User 1 should be present in the list", foundU1);
        assertTrue("User 2 should be present in the list", foundU2);

        // Cleanup
        userDao.delete(u1.getUserId());
        userDao.delete(u2.getUserId());
    }
}
