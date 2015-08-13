package com.epam.autobasematsiuk.entity;

/**
 * The class Auto.
 */
public class Auto extends AbstractEntity {

    private static final long serialVersionUID = 7980256012317725096L;
    private int id;
    private String mark;
    private int valueSpace;
    private int bearingCapacity;
    private boolean workingCondition;
    private boolean status;

    /**
     * Instantiates a new auto.
     */
    public Auto() {
    }

    /**
     * Instantiates a new auto.
     *
     * @param id               is the id
     * @param mark             is the mark
     * @param valueSpace       is the value of space
     * @param bearingCapacity  is the bearing capacity
     * @param workingCondition is the working condition
     */
    public Auto(int id, String mark, int valueSpace, int bearingCapacity, boolean workingCondition) {
        this.id = id;
        this.mark = mark;
        this.valueSpace = valueSpace;
        this.bearingCapacity = bearingCapacity;
        this.workingCondition = workingCondition;
    }

    /**
     * Gets the id of the auto.
     *
     * @return id of the auto
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id for the auto.
     *
     * @param id is the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the mark of the auto
     *
     * @return mark of the auto
     */
    public String getMark() {
        return mark;
    }

    /**
     * Sets mark for the auto.
     *
     * @param mark is the mark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * Gets value of space of the auto.
     *
     * @return value of space
     */
    public int getValueSpace() {
        return valueSpace;
    }

    /**
     * Sets value of space for the auto.
     *
     * @param valueSpace is the value of space
     */
    public void setValueSpace(int valueSpace) {
        this.valueSpace = valueSpace;
    }

    /**
     * Gets bearing capacity of the auto.
     *
     * @return bearing capacity
     */
    public int getBearingCapacity() {
        return bearingCapacity;
    }

    /**
     * Sets bearing capacity for the auto.
     *
     * @param bearingCapacity is the bearing capacity
     */
    public void setBearingCapacity(int bearingCapacity) {
        this.bearingCapacity = bearingCapacity;
    }

    /**
     * Gets the working condition of the auto.
     *
     * @return false if auto has good condition
     */
    public boolean isWorkingCondition() {
        return workingCondition;
    }

    /**
     * Sets working condition for the auto
     *
     * @param workingCondition is the working condition
     */
    public void setWorkingCondition(boolean workingCondition) {
        this.workingCondition = workingCondition;
    }

    /**
     * Gets the status of the auto.
     *
     * @return false if auto is free.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets status for the auto.
     *
     * @param status is the status
     */
    public void setStatus(boolean status) {
        this.status = status;
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

        Auto auto = (Auto) o;

        if (bearingCapacity != auto.bearingCapacity) return false;
        if (id != auto.id) return false;
        if (status != auto.status) return false;
        if (valueSpace != auto.valueSpace) return false;
        if (workingCondition != auto.workingCondition) return false;
        if (!mark.equals(auto.mark)) return false;

        return true;
    }

    /**
     * The method hashCode from java.lang.Object
     *
     * @return hash code of the object Auto
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mark.hashCode();
        result = 31 * result + valueSpace;
        result = 31 * result + bearingCapacity;
        result = 31 * result + (workingCondition ? 1 : 0);
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    /**
     * The method toString from java.lang.Object
     *
     * @return String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("Auto [").append("idAuto = ").append(getId()).
                append(", mark = ").append(getMark()).append(", valueSpace = ").
                append(getValueSpace()).append(", BearingCapacity = ").
                append(getBearingCapacity()).append(", workingCondition = ").
                append(isWorkingCondition()).append(", status").append(isStatus()).
                append("]").toString();
    }
}
