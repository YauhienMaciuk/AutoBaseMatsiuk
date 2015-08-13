package com.epam.autobasematsiuk.database.dao;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.entity.City;
import com.epam.autobasematsiuk.exception.DAOException;

import java.util.List;

/**
 * The Class AbstractBidDAO. Base abstract class for the class BidDAO.
 */
public abstract class AbstractBidDAO extends AbstractDAO {

    /**
     * The method adds a new bid
     *
     * @param bid is the bid
     * @return true if added
     * @throws DAOException
     */
    public abstract boolean addBid(Bid bid) throws DAOException;

    /**
     * The method changes status of the bid
     *
     * @param statusBid is the new status of the bid
     * @param idBid     is the id bid
     * @return true if changed
     * @throws DAOException
     */
    public abstract boolean changeStatusBid(int statusBid, int idBid) throws DAOException;

    /**
     * The method finds the bid by id of driver
     *
     * @param idDriver is the id of driver
     * @return the bid
     * @throws DAOException
     */
    public abstract Bid findBidByIdDriver(int idDriver) throws DAOException;

    /**
     * The method finds list of the bids by id of client and by status bid
     *
     * @param idClient  is the id of client
     * @param statusBid is the status of client
     * @return list of the bids by id of client
     * @throws DAOException
     */
    public abstract List<Bid> findListBidsByIdAndStatusClient(int idClient, int statusBid) throws DAOException;

    /**
     * The method finds list the bids by the status
     *
     * @param statusBid is the status bid
     * @return list the bids by the status bid
     * @throws DAOException
     */
    public abstract List<Bid> findListBidsByStatus(int statusBid) throws DAOException;

    /**
     * The method searches list of cities
     *
     * @return list of cities
     * @throws DAOException
     */
    public abstract List<City> receiveListCities() throws DAOException;

    /**
     * The method deletes the bid
     *
     * @param idBid is the id of the bid
     * @return true if deleted
     * @throws DAOException
     */
    public abstract boolean deleteBid(int idBid) throws DAOException;

    /**
     * The method finds the city by name
     *
     * @param name is name of the city
     * @return the city
     * @throws DAOException
     */
    public abstract City findCityByName(String name) throws DAOException;

    /**
     * The method finds bid by id
     *
     * @param idBid is the id of bid
     * @return the bid
     * @throws DAOException
     */
    public abstract Bid findBidById(int idBid) throws DAOException;
}
