package com.epam.autobasematsiuk.database.dao;

import com.epam.autobasematsiuk.entity.Auto;
import com.epam.autobasematsiuk.entity.User;
import com.epam.autobasematsiuk.exception.DAOException;

import java.util.List;

/**
 * The Class AbstractAutoFlightDAO. Base abstract class for the class AutoFlightDAO.
 */
public abstract class AbstractAutoFlightDAO extends AbstractDAO {

    /**
     * The method finds list of the auto for auto flight
     *
     * @param valueShipment  is value of the shipment
     * @param weightShipment is weight of the shipment
     * @return list of the auto for auto flight
     * @throws DAOException
     */
    public abstract List<Auto> findListAutoForAutoFlight(int valueShipment, int weightShipment) throws DAOException;

    /**
     * The method finds list of auto by condition
     *
     * @param condition is the condition auto
     * @return list of auto by condition
     * @throws DAOException
     */
    public abstract List<Auto> findListAutoByCondition(boolean condition) throws DAOException;

    /**
     * The method changes working condition of the auto
     *
     * @param workingConditionAuto is the new working condition of the auto
     * @param idAuto               is the id of the auto
     * @return true if changed
     * @throws DAOException
     */
    public abstract boolean changeWorkingConditionAuto(boolean workingConditionAuto, int idAuto) throws DAOException;

    /**
     * The method changes status of driver
     *
     * @param idDriver is the status of driver
     * @param status   is the new status
     * @return true if changed
     * @throws DAOException
     */
    public abstract boolean changeStatusDriver(int idDriver, boolean status) throws DAOException;

    /**
     * The method changes status of the auto
     *
     * @param idAuto is the id of auto
     * @param status is the new status
     * @return true if changed
     * @throws DAOException
     */
    public abstract boolean changeStatusAuto(int idAuto, boolean status) throws DAOException;

    /**
     * The method deletes auto flight
     *
     * @param idBid is the id of the bid
     * @return true if deleted
     * @throws DAOException
     */
    public abstract boolean deleteAutoFlight(int idBid) throws DAOException;

    /**
     * The method finds drivers by status
     *
     * @return list of drivers by status
     * @throws DAOException
     */
    public abstract List<User> findListDriversByStatus(boolean status) throws DAOException;

    /**
     * The method adds a new auto flight
     *
     * @param idBid    is the id of the bid
     * @param idDriver is the id of driver
     * @param idAuto   is the id of the auto
     * @return true if added
     * @throws DAOException
     */
    public abstract boolean addAutoFlight(int idBid, int idDriver, int idAuto) throws DAOException;

    /**
     * The method finds id of the auto by the id of the auto flight
     *
     * @param idAutoFlight is the id of the auto flight
     * @return id of the auto
     * @throws DAOException
     */
    public abstract int findIdAutoByIdAutoFlight(int idAutoFlight) throws DAOException;

    /**
     * The method finds list auto from auto flights
     *
     * @return list auto from auto flight
     * @throws DAOException
     */
    public abstract List<Auto> findListAutoFromAutoFlights() throws DAOException;

    /**
     * The method finds id driver by id of the auto flight
     *
     * @param idAutoFlight is the id of the auto flight
     * @return id driver
     * @throws DAOException
     */
    public abstract int findIdDriverByIdAutoFlight(int idAutoFlight) throws DAOException;
}
