package model;

import java.sql.Timestamp;

/**
 * Booking Model - holds all data related to a single flight booking.
 * Merged version for compatibility with all DAO/Controller paths.
 */
public class Booking {
    
    // HEAD conventions
    private String fromCode;
    private String toCode;
    private String flightNo;
    private String date;
    private String time;
    private String seat;

    // rahul conventions
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
    
    // For HEAD branch compatibility
    public Booking(String fromCode, String toCode, String flightNo, String date, String time, String status, String seat, String passengerName, double amount) {
        this.fromCode = fromCode;
        this.fromCity = fromCode;
        this.toCode = toCode;
        this.toCity = toCode;
        this.flightNo = flightNo;
        this.flightCode = flightNo;
        this.date = date;
        this.departureDate = date;
        this.time = time;
        this.departureTime = time;
        this.status = status;
        this.seat = seat;
        this.seatNumber = seat;
        this.passengerName = passengerName;
        this.amount = amount;
    }
    
    // For rahul branch compatibility
    public Booking(String bookingId, int userId, int flightId, String flightCode, String route, 
                   String fromCity, String toCity,
                   String departureDate, String departureTime, String arrivalTime,
                   String seatNumber, String passengerName, double amount, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.flightId = flightId;
        this.flightCode = flightCode;
        this.flightNo = flightCode;
        this.route = route;
        this.fromCity = fromCity;
        this.fromCode = fromCity;
        this.toCity = toCity;
        this.toCode = toCity;
        this.departureDate = departureDate;
        this.date = departureDate;
        this.departureTime = departureTime;
        this.time = departureTime;
        this.arrivalTime = arrivalTime;
        this.seatNumber = seatNumber;
        this.seat = seatNumber;
        this.passengerName = passengerName;
        this.amount = amount;
        this.status = status;
    }
    
    // ======================== GETTERS & SETTERS ========================
    
    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
        this.fromCity = fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
        this.toCity = toCode;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
        this.flightCode = flightNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        this.departureDate = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        this.departureTime = time;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
        this.seatNumber = seat;
    }

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
        this.flightNo = flightCode;
    }
    
    public String getRoute() {
        if (route != null && route.contains("(") && route.contains(")")) {
            try {
                String src = extractAirportCode(fromCity);
                String dest = extractAirportCode(toCity);
                if (src != null && dest != null) {
                    return src + " → " + dest;
                }
            } catch (Exception e) {
                // fallback
            }
        }
        return route;
    }

    private String extractAirportCode(String cityAndCode) {
        if (cityAndCode == null) return null;
        int start = cityAndCode.indexOf("(");
        int end = cityAndCode.indexOf(")");
        if (start != -1 && end != -1 && end > start + 1) {
            return cityAndCode.substring(start + 1, end).trim();
        }
        return cityAndCode.trim();
    }
    
    public void setRoute(String route) {
        this.route = route;
    }
    
    public String getFromCity() {
        return fromCity;
    }
    
    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
        this.fromCode = fromCity;
    }
    
    public String getToCity() {
        return toCity;
    }
    
    public void setToCity(String toCity) {
        this.toCity = toCity;
        this.toCode = toCity;
    }
    
    public String getDepartureDate() {
        return departureDate;
    }
    
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
        this.date = departureDate;
    }
    
    public String getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
        this.time = departureTime;
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
        this.seat = seatNumber;
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
        String info = flightCode + " • " + departureDate + " • " + departureTime;
        String arr = (arrivalTime == null || arrivalTime.isEmpty()) ? calculateArrivalTime(departureTime) : arrivalTime;
        if (arr != null && !arr.isEmpty()) {
            info += " - " + arr;
        }
        return info;
    }

    private String calculateArrivalTime(String depTime) {
        try {
            if (depTime == null || depTime.trim().isEmpty()) return "";
            depTime = depTime.trim().toUpperCase();
            boolean isPm = depTime.endsWith("PM");
            boolean isAm = depTime.endsWith("AM");
            String timePart = depTime;
            if (isPm || isAm) {
                timePart = depTime.substring(0, depTime.length() - 2).trim();
            }
            String[] parts = timePart.split(":");
            int hour = Integer.parseInt(parts[0]);
            int min = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
            
            int arrHour = hour + 1;
            String suffix = "";
            if (isAm || isPm) {
                if (hour == 11) {
                    suffix = isAm ? "PM" : "AM";
                } else if (hour == 12) {
                    arrHour = 1;
                    suffix = isPm ? "PM" : "AM";
                } else {
                    suffix = isAm ? "AM" : "PM";
                }
            }
            if (arrHour > 12) {
                arrHour -= 12;
            }
            return String.format("%02d:%02d%s", arrHour, min, suffix);
        } catch (Exception e) {
            return "";
        }
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
