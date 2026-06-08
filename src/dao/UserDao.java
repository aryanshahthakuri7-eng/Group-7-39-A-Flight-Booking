package dao;

import model.User;

public interface UserDao {
    boolean createUser(User user);
    User getUserByEmail(String email);
    boolean updatePassword(String email, String newPassword);
    boolean updateUser(User user);
}
