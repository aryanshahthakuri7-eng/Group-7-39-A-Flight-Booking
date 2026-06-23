package model;

import java.sql.Timestamp;

/**
 * Model representing a User account - merged version for compatibility with all DAO paths.
 */
public class User {

    // Dual-field mapping variables implemented to maintain backward compatibility 
    // between the different branch naming conventions (e.g. 'fullname' vs 'fullName')
    private int id;
    private int userId;
    private String fullname;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String role = "User";
    private String securityQuestion;
    private String securityAnswer;
    private Timestamp createdAt;

    public User() {
    }

    // For HEAD branch compatibility
    public User(String fullname, String email, String phone, String password) {
        this.fullname = fullname;
        this.fullName = fullname;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User(String fullname, String email, String phone, String password, String role, String securityQuestion, String securityAnswer) {
        this.fullname = fullname;
        this.fullName = fullname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public User(int id, String fullname, String email, String phone, String password) {
        this.id = id;
        this.userId = id;
        this.fullname = fullname;
        this.fullName = fullname;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }



    // Getters and Setters mapping both conventions
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.userId = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        this.id = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
        this.fullName = fullname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        this.fullname = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", fullName=" + fullName + ", email=" + email + '}';
    }
}
