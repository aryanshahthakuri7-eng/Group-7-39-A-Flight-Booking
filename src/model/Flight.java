package model;

/**
 * Model representing a Flight.
 */
public class Flight {

    private int flightId;
    private String flightCode;
    private String source;
    private String destination;
    private String departureDate;
    private String departureTime;
    private int availableSeats;
    private double price;
    private String status; // 'ACTIVE', 'CANCELLED'

    public Flight() {
    }

    public Flight(int flightId, String flightCode, String source, String destination, 
                  String departureDate, String departureTime, int availableSeats, double price, String status) {
        this.flightId = flightId;
        this.flightCode = flightCode;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
        this.price = price;
        this.status = status;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoute() {
        return source + " → " + destination;
    }

    @Override
    public String toString() {
        return "Flight{" + "flightId=" + flightId + ", flightCode=" + flightCode + ", route=" + getRoute() + '}';
    }
}
