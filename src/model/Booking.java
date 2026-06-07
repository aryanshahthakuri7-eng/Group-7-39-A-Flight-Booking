package model;

public class Booking {
    private String fromCode;
    private String toCode;
    private String flightNo;
    private String date;
    private String time;
    private String status; // e.g. "CONFIRMED", "CANCELLED"
    private String seat;
    private String passengerName;
    private double amount;

    public Booking() {
    }

    public Booking(String fromCode, String toCode, String flightNo, String date, String time, String status, String seat, String passengerName, double amount) {
        this.fromCode = fromCode;
        this.toCode = toCode;
        this.flightNo = flightNo;
        this.date = date;
        this.time = time;
        this.status = status;
        this.seat = seat;
        this.passengerName = passengerName;
        this.amount = amount;
    }

    // Getters and Setters
    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
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
}
