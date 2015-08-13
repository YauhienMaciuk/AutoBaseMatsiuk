package com.epam.autobasematsiuk.entity;

/**
 * The class AutoFlight.
 */
public class AutoFlight extends AbstractEntity {

    private static final long serialVersionUID = -3006547336838042493L;
    private int idAutoFlight;
    private City fromCity;
    private City toCity;
    private User driver;
    private Auto auto;

    /**
     * Instantiates a new auto flight.
     */
    public AutoFlight() {
    }

    /**
     * Instantiates a new auto flight.
     *
     * @param idAutoFlight is the id
     * @param fromCity     is the city from where are taking the shipment
     * @param toCity       is the city of destination
     * @param driver       is driver
     * @param auto         is the auto
     */
    public AutoFlight(int idAutoFlight, City fromCity, City toCity, User driver, Auto auto) {
        this.idAutoFlight = idAutoFlight;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.driver = driver;
        this.auto = auto;
    }

    /**
     * Gets the id of the auto flight
     *
     * @return id
     */
    public int getIdAutoFlight() {
        return idAutoFlight;
    }

    /**
     * Sets id for the auto flight.
     *
     * @param idAutoFlight is the id of the auto flight
     */
    public void setIdAutoFlight(int idAutoFlight) {
        this.idAutoFlight = idAutoFlight;
    }

    /**
     * Gets the city from where are taking the shipment.
     *
     * @return the city
     */
    public City getFromCity() {
        return fromCity;
    }

    /**
     * Sets city from where are taking the shipment.
     *
     * @param fromCity is the city
     */
    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    /**
     * Gets the city of destination.
     *
     * @return the city
     */
    public City getToCity() {
        return toCity;
    }

    /**
     * Sets city of destination.
     *
     * @param toCity is the city
     */
    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    /**
     * Gets driver.
     *
     * @return driver
     */
    public User getDriver() {
        return driver;
    }

    /**
     * The method assigns driver on a auto flight
     *
     * @param driver is driver of the auto flight
     */
    public void setDriver(User driver) {
        this.driver = driver;
    }

    /**
     * Gets the auto.
     *
     * @return auto
     */
    public Auto getAuto() {
        return auto;
    }

    /**
     * The method assigns auto on a flight
     *
     * @param auto is the auto of the auto flight
     */
    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    /**
     * The method equals from java.lang.Object
     *
     * @param o is the object
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoFlight that = (AutoFlight) o;

        if (idAutoFlight != that.idAutoFlight) return false;
        if (!auto.equals(that.auto)) return false;
        if (!driver.equals(that.driver)) return false;
        if (!fromCity.equals(that.fromCity)) return false;
        if (!toCity.equals(that.toCity)) return false;

        return true;
    }

    /**
     * The method hashCode from java.lang.Object
     *
     * @return hash code of the object AutoFlight
     */
    @Override
    public int hashCode() {
        int result = idAutoFlight;
        result = 31 * result + fromCity.hashCode();
        result = 31 * result + toCity.hashCode();
        result = 31 * result + driver.hashCode();
        result = 31 * result + auto.hashCode();
        return result;
    }

    /**
     * The method toString from java.lang.Object
     *
     * @return String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("AutoFlight [").append("idAutoFlight = ").append(getIdAutoFlight()).
                append(", fromCity = ").append(getFromCity()).append(", toCity = ").
                append(getToCity()).append(", driver = ").append(getDriver()).
                append(", auto = ").append(getAuto()).append("]").toString();
    }
}
