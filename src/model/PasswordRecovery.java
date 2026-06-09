package model;

import java.sql.Timestamp;

/**
 * Model class representing a Password Recovery request and OTP verification state.
 */
public class PasswordRecovery {
    private int recoveryId;
    private String email;
    private String otp;
    private Timestamp expiryTime;
    private boolean isVerified;

    // Default constructor
    public PasswordRecovery() {
    }

    // Parametrized constructor
    public PasswordRecovery(String email, String otp, Timestamp expiryTime, boolean isVerified) {
        this.email = email;
        this.otp = otp;
        this.expiryTime = expiryTime;
        this.isVerified = isVerified;
    }

    // Getters and Setters
    public int getRecoveryId() {
        return recoveryId;
    }

    public void setRecoveryId(int recoveryId) {
        this.recoveryId = recoveryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Timestamp getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Timestamp expiryTime) {
        this.expiryTime = expiryTime;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
