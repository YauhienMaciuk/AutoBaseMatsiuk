package com.epam.autobasematsiuk.entity;

import java.util.Date;

/**
 * The class Bid.
 */
public class Bid extends AbstractEntity {

    private static final long serialVersionUID = -911042834327045039L;
    private int id;
    private City fromCity;
    private City toCity;
    private int valueShipment;
    private int weightShipment;
    private Date dateService;
    private int status;
    private int idClient;

    /**
     * Instantiates a new bid.
     */
    public Bid() {
    }

    /**
     * Instantiates a new bid.
     *
     * @param fromCity       is the city from where are taking the shipment
     * @param toCity         is the city of destination
     * @param valueShipment  is the value of the shipment
     * @param weightShipment is the weight of the shipment
     * @param dateService    is the data of service
     * @param idClient       is the id of client
     */
    public Bid(City fromCity, City toCity, int valueShipment,
               int weightShipment, Date dateService, int idClient) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.valueShipment = valueShipment;
        this.weightShipment = weightShipment;
        this.dateService = dateService;
        this.idClient = idClient;
    }

    /**
     * Gets the id of the bid.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id for the bid.
     *
     * @param id is the id
     */
    public void setId(int id) {
        this.id = id;
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
     * @param fromCity
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
     * Gets value of the shipment
     *
     * @return value of the shipment
     */
    public int getValueShipment() {
        return valueShipment;
    }

    /**
     * Sets value of the shipment.
     *
     * @param valueShipment is the value of the shipment
     */
    public void setValueShipment(int valueShipment) {
        this.valueShipment = valueShipment;
    }

    /**
     * Gets weight of the shipment.
     *
     * @return weightShipment
     */
    public int getWeightShipment() {
        return weightShipment;
    }

    /**
     * Sets weight of the shipment.
     *
     * @param weightShipment is the weight of the shipment
     */
    public void setWeightShipment(int weightShipment) {
        this.weightShipment = weightShipment;
    }

    /**
     * Gets data of the service.
     *
     * @return data
     */
    public Date getDateService() {
        return dateService;
    }

    /**
     * Sets data of the service.
     *
     * @param dateService is the data of the service
     */
    public void setDateService(Date dateService) {
        this.dateService = dateService;
    }

    /**
     * Gets status of the bid.
     *
     * @return status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status for the bid.
     *
     * @param status is the status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the id of client.
     *
     * @return id of the client
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Sets id for client.
     *
     * @param idClient is the id of client
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

        Bid bid = (Bid) o;

        if (id != bid.id) return false;
        if (idClient != bid.idClient) return false;
        if (status != bid.status) return false;
        if (valueShipment != bid.valueShipment) return false;
        if (weightShipment != bid.weightShipment) return false;
        if (!dateService.equals(bid.dateService)) return false;
        if (!fromCity.equals(bid.fromCity)) return false;
        if (!toCity.equals(bid.toCity)) return false;

        return true;
    }

    /**
     * The method hashCode from java.lang.Object
     *
     * @return hash code of the object Bid
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fromCity.hashCode();
        result = 31 * result + toCity.hashCode();
        result = 31 * result + valueShipment;
        result = 31 * result + weightShipment;
        result = 31 * result + dateService.hashCode();
        result = 31 * result + status;
        result = 31 * result + idClient;
        return result;
    }

    /**
     * The method toString from java.lang.Object
     *
     * @return String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("Bid [").append("id = ").append(getId()).append(", from ").
                append(getFromCity()).append(" to ").append(getToCity()).append(", valueShipment = ").
                append(getValueShipment()).append(", weightShipment = ").append(getWeightShipment()).
                append(", data = ").append(getDateService()).append(", status = ").
                append(getStatus()).append(", idClient = ").append(getIdClient()).
                append("]").toString();
    }
}
