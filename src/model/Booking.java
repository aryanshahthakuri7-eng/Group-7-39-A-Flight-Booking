package model;

import java.sql.Timestamp;

/**
 * Booking Model - holds all data related to a single flight booking.
 */
public class Booking {
    
    private String bookingId;
    private int userId;
    private int flightId;
    private String flightCode;
    private String route;          // e.g. "KTM → PKR"
    private String fromCity;
    private String toCity;
    private String departureDate;
    private String departureTime;
    private String arrivalTime;    // Kept for backward compatibility
    private String seatNumber;
    private String passengerName;
    private double amount;
    private String status;         // "CONFIRMED", "CANCELLED", "PENDING"
    private Timestamp bookingDate;
    
    // Default constructor
    public Booking() {
        this.arrivalTime = "";
    }
    
    // Parameterized constructor
    public Booking(String bookingId, int userId, int flightId, String flightCode, String route, 
                   String fromCity, String toCity,
                   String departureDate, String departureTime, String arrivalTime,
                   String seatNumber, String passengerName, double amount, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.flightId = flightId;
        this.flightCode = flightCode;
        this.route = route;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seatNumber = seatNumber;
        this.passengerName = passengerName;
        this.amount = amount;
        this.status = status;
    }
    
    // ======================== GETTERS & SETTERS ========================
    
    public String getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
    
    public String getFlightCode() {
        return flightCode;
    }
    
    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }
    
    public String getRoute() {
        return route;
    }
    
    public void setRoute(String route) {
        this.route = route;
    }
    
    public String getFromCity() {
        return fromCity;
    }
    
    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }
    
    public String getToCity() {
        return toCity;
    }
    
    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
    
    public String getDepartureDate() {
        return departureDate;
    }
    
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
    
    public String getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    
    public String getArrivalTime() {
        return arrivalTime == null ? "" : arrivalTime;
    }
    
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    public String getSeatNumber() {
        return seatNumber;
    }
    
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    public String getPassengerName() {
        return passengerName;
    }
    
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    // ======================== UTILITY METHODS ========================
    
    /**
     * Returns a formatted flight info string for display.
     * Example: "YS101 ● 28 APR 2026 ● 10:00AM"
     */
    public String getFormattedFlightInfo() {
        String info = flightCode + " ● " + departureDate + " ● " + departureTime;
        if (arrivalTime != null && !arrivalTime.isEmpty()) {
            info += " - " + arrivalTime;
        }
        return info;
    }
    
    /**
     * Returns formatted amount with NPR prefix.
     * Example: "NPR 5,000"
     */
    public String getFormattedAmount() {
        return "NPR " + String.format("%,.0f", amount);
    }
    
    @Override
    public String toString() {
        return "Booking{" + "bookingId=" + bookingId + ", route=" + route + 
               ", status=" + status + ", amount=" + amount + '}';
    }
}
