/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class SearchFlight {

    private final String flightName;
    private final String departureTime;
    private final String arrivalTime;
    private final String flightClass;
    private final String price;

    // Constructor
    public SearchFlight(
            String flightName,
            String departureTime,
            String arrivalTime,
            String flightClass,
            String price) {

        this.flightName = flightName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightClass = flightClass;
        this.price = price;
    }

    // Getter methods
    public String getFlightName() {
        return flightName;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public String getPrice() {
        return price;
    }
}