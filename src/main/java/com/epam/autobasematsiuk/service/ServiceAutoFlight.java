package com.epam.autobasematsiuk.service;

import com.epam.autobasematsiuk.database.dao.AutoFlightDAO;
import com.epam.autobasematsiuk.entity.Auto;
import com.epam.autobasematsiuk.entity.User;
import com.epam.autobasematsiuk.exception.DAOException;
import com.epam.autobasematsiuk.exception.ServiceException;

import java.util.List;

/**
 * The class ServiceAutoFlight performs work over AutoFlightDAO.
 */
public class ServiceAutoFlight {

    private AutoFlightDAO autoFlightDAO;

    /**
     * Instantiates a new ServiceAutoFlight.
     */
    public ServiceAutoFlight() {
        autoFlightDAO = new AutoFlightDAO();
    }

    /**
     * The method searches the list of auto for the auto flight
     *
     * @param valueShipment  is volume of the shipment for transportation
     * @param weightShipment is weight of the shipment for transportation
     * @return list of auto for the auto flight
     * @throws ServiceException
     */
    public List<Auto> findListAutoForAutoFlight(int valueShipment, int weightShipment)
            throws ServiceException {
        List<Auto> listAuto = null;
        try {
            listAuto = autoFlightDAO.findListAutoForAutoFlight(valueShipment, weightShipment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listAuto;
    }

    /**
     * The method searches the list of drivers by status
     *
     * @return list of drivers by status
     * @throws ServiceException
     */
    public List<User> findListDriversByStatus(boolean status) throws ServiceException {
        List<User> listAvailableDrivers = null;
        try {
            listAvailableDrivers = autoFlightDAO.findListDriversByStatus(status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listAvailableDrivers;
    }

    /**
     * The method adds the auto flight to database.
     *
     * @param idBid    id of bid
     * @param idDriver id of driver
     * @param idAuto   id of auto
     * @return true if added
     * @throws ServiceException
     */
    public boolean addAutoFlight(int idBid, int idDriver, int idAuto) throws ServiceException {
        boolean flag = false;
        try {
            flag = autoFlightDAO.addAutoFlight(idBid, idDriver, idAuto);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    /**
     * The method changes the status of the driver
     *
     * @param idDriver id of driver
     * @param status   new status for driver
     * @return true if changed
     * @throws ServiceException
     */
    public boolean changeStatusDriver(int idDriver, boolean status) throws ServiceException {
        boolean flag = false;
        try {
            flag = autoFlightDAO.changeStatusDriver(idDriver, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    /**
     * The method changes the status of the auto
     *
     * @param idAuto id of auto
     * @param status new status for auto
     * @return true if changed
     * @throws ServiceException
     */
    public boolean changeStatusAuto(int idAuto, boolean status) throws ServiceException {
        boolean flag = false;
        try {
            flag = autoFlightDAO.changeStatusAuto(idAuto, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    /**
     * The method deletes the auto flight
     *
     * @param idBid is the id of bid
     * @return true if deleted
     * @throws ServiceException
     */
    public boolean deleteAutoFlight(int idBid) throws ServiceException {
        boolean flag = false;
        try {
            flag = autoFlightDAO.deleteAutoFlight(idBid);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    /**
     * The method finds id auto by the id of the auto flight
     *
     * @param idAutoFlight is the id of the auto flight
     * @return id of the auto
     * @throws ServiceException
     */
    public int findIdAutoByIdAutoFlight(int idAutoFlight) throws ServiceException {
        int idAuto = 0;
        try {
            idAuto = autoFlightDAO.findIdAutoByIdAutoFlight(idAutoFlight);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return idAuto;
    }

    /**
     * The method changes the working condition of the auto
     *
     * @param workingConditionAuto is the new working condition of the auto
     * @param idAuto               is the id of auto
     * @return true if changed
     * @throws ServiceException
     */
    public boolean changeWorkingConditionAuto(boolean workingConditionAuto, int idAuto)
            throws ServiceException {
        boolean flag = false;
        try {
            flag = autoFlightDAO.changeWorkingConditionAuto(workingConditionAuto, idAuto);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    /**
     * The method finds the list of auto by their working condition
     *
     * @param condition is working condition of the auto
     * @return list of auto
     * @throws ServiceException
     */
    public List<Auto> findListAutoByCondition(boolean condition) throws ServiceException {
        List<Auto> listAuto = null;
        try {
            listAuto = autoFlightDAO.findListAutoByCondition(condition);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listAuto;
    }

    /**
     * The method finds list of auto from auto flights
     *
     * @return list of auto
     * @throws ServiceException
     */
    public List<Auto> findListAutoFromAutoFlights() throws ServiceException {
        List<Auto> listAuto = null;
        try {
            listAuto = autoFlightDAO.findListAutoFromAutoFlights();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listAuto;
    }

    /**
     * The method finds id of driver by id of the auto flight
     *
     * @param idAutoFlight is the id of the auto flight
     * @return id of driver
     * @throws ServiceException
     */
    public int findIdDriverByIdAutoFlight(int idAutoFlight) throws ServiceException {
        int idDriver = 0;
        try {
            idDriver = autoFlightDAO.findIdDriverByIdAutoFlight(idAutoFlight);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return idDriver;
    }
}
