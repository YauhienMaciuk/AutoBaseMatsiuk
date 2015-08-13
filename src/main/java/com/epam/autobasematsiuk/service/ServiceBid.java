package com.epam.autobasematsiuk.service;

import com.epam.autobasematsiuk.database.dao.BidDAO;
import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.entity.City;
import com.epam.autobasematsiuk.exception.DAOException;
import com.epam.autobasematsiuk.exception.ServiceException;

import java.sql.Date;
import java.util.List;

/**
 * The class ServiceBid performs work over BidDAO.
 */
public class ServiceBid {

    private BidDAO bidDAO;

    /**
     * Instantiates a new ServiceBid.
     */
    public ServiceBid() {
        bidDAO = new BidDAO();
    }

    /**
     * The method searches the list of cities
     *
     * @return the list of cities
     * @throws ServiceException
     */
    public List<City> receiveListCities() throws ServiceException {
        List<City> listCity = null;
        try {
            listCity = bidDAO.receiveListCities();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listCity;
    }

    /**
     * The method searches the list of bids by status
     *
     * @param statusBid is the required status of bid
     * @return the list of bids
     * @throws ServiceException
     */
    public List<Bid> findListBidsByStatus(int statusBid) throws ServiceException {
        List<Bid> listBid = null;
        try {
            listBid = bidDAO.findListBidsByStatus(statusBid);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listBid;
    }

    /**
     * The method searches the bid for driver
     *
     * @param idDriver is id of driver
     * @return the bid of driver
     * @throws ServiceException
     */
    public Bid findBidByIdDriver(int idDriver) throws ServiceException {
        Bid bidForDriver = null;
        try {
            bidForDriver = bidDAO.findBidByIdDriver(idDriver);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return bidForDriver;
    }

    /**
     * The method searches list of bids by id and status of client
     *
     * @param idClient  is the id of client
     * @param statusBid is the status of bid
     * @return the list of bid of client
     * @throws ServiceException
     */
    public List<Bid> findListBidsByIdAndStatusClient(int idClient, int statusBid) throws ServiceException {
        List<Bid> listBidClient = null;
        try {
            listBidClient = bidDAO.findListBidsByIdAndStatusClient(idClient, statusBid);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listBidClient;
    }

    /**
     * The method searches the city by the name
     *
     * @param name is name of the city
     * @return the city
     * @throws ServiceException
     */
    public City findCityByName(String name) throws ServiceException {
        City city = null;
        try {
            city = bidDAO.findCityByName(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return city;
    }

    /**
     * The method adds the new bid
     *
     * @param fromCity       is from the city
     * @param toCity         is to the city
     * @param valueShipment  is the value of the shipment
     * @param weightShipment is the weight of shipment
     * @param date           is the date of the new bid
     * @param idClient       is the id of client
     * @return true is added
     * @throws ServiceException
     */
    public boolean addBid(City fromCity, City toCity, int valueShipment, int weightShipment,
                          String date, int idClient) throws ServiceException {
        Date serviceDate = Date.valueOf(date);
        Bid bid = new Bid(fromCity, toCity, valueShipment, weightShipment, serviceDate, idClient);
        boolean flag = false;
        try {
            flag = bidDAO.addBid(bid);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    /**
     * The method changes the status of the bid
     *
     * @param statusBid is the new status of bid
     * @param idBid     is the id of bid
     * @return true if changed
     * @throws ServiceException
     */
    public boolean changeStatusBid(int statusBid, int idBid) throws ServiceException {
        boolean flag = false;
        try {
            flag = bidDAO.changeStatusBid(statusBid, idBid);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    /**
     * The method deletes the bid
     *
     * @param idBid is the id bid
     * @return true if deleted
     * @throws ServiceException
     */
    public boolean deleteBid(int idBid) throws ServiceException {
        boolean flag = false;
        try {
            flag = bidDAO.deleteBid(idBid);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    /**
     * The method finds the bid by id
     *
     * @param idBid is the id of the bid
     * @return the bid
     * @throws ServiceException
     */
    public Bid findBidById(int idBid) throws ServiceException {
        Bid bid = new Bid();
        try {
            bid = bidDAO.findBidById(idBid);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return bid;
    }

}
