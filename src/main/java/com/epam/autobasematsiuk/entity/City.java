package com.epam.autobasematsiuk.entity;

/**
 * The class City.
 */
public class City extends AbstractEntity {

    private static final long serialVersionUID = -3034654176128327326L;

    private int id;

    private String nameCity;

    /**
     * Instantiates a new city.
     */
    public City() {
    }

    /**
     * Instantiates a new city.
     *
     * @param nameCity is the name of the city
     */
    public City(String nameCity) {
        this.nameCity = nameCity;
    }

    /**
     * Gets the id of the city.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id for the city.
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the city.
     *
     * @return name of the city
     */
    public String getNameCity() {
        return nameCity;
    }

    /**
     * Sets name for the city.
     *
     * @param nameCity is name of the city.
     */
    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
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

        City city = (City) o;

        if (id != city.id) return false;
        if (!nameCity.equals(city.nameCity)) return false;

        return true;
    }

    /**
     * The method hashCode from java.lang.Object
     *
     * @return hash code of the object City
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nameCity.hashCode();
        return result;
    }

    /**
     * The method toString from java.lang.Object
     *
     * @return String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("City [").append("id = ").append(getId()).append(" name = ").
                append(getNameCity()).append("]").toString();
    }
}
