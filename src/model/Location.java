package model;

/**
 * Model representing a Location / Airport.
 */
public class Location {

    private int locationId;
    private String cityName;
    private String airportName;
    private String cityCode;

    public Location() {
    }

    public Location(int locationId, String cityName, String airportName, String cityCode) {
        this.locationId = locationId;
        this.cityName = cityName;
        this.airportName = airportName;
        this.cityCode = cityCode;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Standard display string for combo box elements.
     * Example: "Kathmandu (KTM)"
     */
    public String getFormattedDisplay() {
        return cityName + " (" + cityCode + ")";
    }

    @Override
    public String toString() {
        return getFormattedDisplay();
    }
}
