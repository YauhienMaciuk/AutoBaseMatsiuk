package com.epam.autobasematsiuk.database.dao;

import com.epam.autobasematsiuk.database.pool.ConnectionPool;
import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.entity.City;
import com.epam.autobasematsiuk.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The class BidDAO
 */
public class BidDAO extends AbstractBidDAO {

    public static final String CANCEL_BID = "UPDATE bid SET status = ? WHERE id_bid = ?";
    public static final String ADD_BID = "INSERT INTO bid (from_city, to_city, value_shipment, weight_shipment, date_service, id_client) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String FIND_BID_FOR_DRIVER = "SELECT auto_flight.id_order, bid.value_shipment, bid.weight_shipment, bid.date_service, bid.status, bid.id_client FROM auto_flight LEFT JOIN bid ON auto_flight.id_order = bid.id_bid WHERE auto_flight.id_driver = ? AND bid.status = 2";
    public static final String FIND_FROM_CITY_BY_AUTO_FLIGHT = "SELECT city.id, city.name_city FROM auto_flight LEFT JOIN bid ON auto_flight.id_order = bid.id_bid LEFT JOIN city ON bid.from_city=city.id WHERE auto_flight.id_driver = ? AND bid.status = 2";
    public static final String FIND_TO_CITY_BY_AUTO_FLIGHT = "SELECT city.id, city.name_city FROM auto_flight LEFT JOIN bid ON auto_flight.id_order = bid.id_bid LEFT JOIN city ON bid.to_city=city.id WHERE auto_flight.id_driver = ? AND bid.status = 2";
    public static final String FIND_FROM_CITY_BY_ID_CLIENT = "SELECT city.id, city.name_city FROM bid left join city on bid.from_city = city.id WHERE bid.id_client = ? AND bid.status = ? ORDER BY bid.id_bid";
    public static final String FIND_TO_CITY_BY_ID_CLIENT = "SELECT city.id, city.name_city FROM bid left join city on bid.to_city = city.id WHERE bid.id_client = ? AND bid.status = ? ORDER BY bid.id_bid";
    public static final String FIND_LIST_BIDS_BY_ID_CLIENT = "SELECT bid.id_bid, bid.value_shipment, bid.weight_shipment, bid.date_service, bid.status, bid.id_client FROM bid WHERE id_client = ? AND bid.status = ?";
    public static final String FIND_FROM_CITY_BY_STATUS_BID = "SELECT city.id, city.name_city FROM bid LEFT JOIN city ON bid.from_city = city.id WHERE bid.status = ? ORDER BY bid.id_bid";
    public static final String FIND_TO_CITY_BY_STATUS_BID = "SELECT city.id, city.name_city FROM bid LEFT JOIN city ON bid.to_city = city.id WHERE bid.status = ? ORDER BY bid.id_bid";
    public static final String FIND_LIST_BIDS_BY_STATUS = "SELECT bid.id_bid, bid.value_shipment, bid.weight_shipment, bid.date_service, bid.status, bid.id_client FROM bid WHERE bid.status = ?";
    public static final String RECEIVE_LIST_CITIES = "SELECT * FROM city";
    public static final String DELETE_BID = "DELETE FROM bid WHERE id_bid = ?";
    public static final String FIND_CITY_BY_NAME = "SELECT * FROM city WHERE name_city = ?";
    public static final String FIND_BID_BY_ID = "SELECT bid.id_bid, bid.value_shipment, bid.weight_shipment, bid.date_service, bid.status, bid.id_client FROM bid WHERE id_bid = ?";
    public static final String FIND_FROM_CITY_BY_ID_BID = "SELECT city.* FROM bid LEFT JOIN city ON bid.from_city = city.id WHERE bid.id_bid = ?";
    public static final String FIND_TO_CITY_BY_ID_BID = "SELECT city.* FROM bid LEFT JOIN city ON bid.to_city = city.id WHERE bid.id_bid = ?";

    /**
     * The method adds a new bid
     *
     * @param bid is the bid
     * @return true if added
     * @throws DAOException
     */
    @Override
    public boolean addBid(Bid bid) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(ADD_BID);
                preparedStatement.setInt(1, bid.getFromCity().getId());
                preparedStatement.setInt(2, bid.getToCity().getId());
                preparedStatement.setInt(3, bid.getValueShipment());
                preparedStatement.setInt(4, bid.getWeightShipment());
                preparedStatement.setDate(5, (Date) bid.getDateService());
                preparedStatement.setInt(6, bid.getIdClient());
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                throw new DAOException("Exception in method BidDAO.addBid", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return flag;
    }

    /**
     * The method changes status of the bid
     *
     * @param statusBid is the new status of the bid
     * @param idBid     is the id of bid
     * @return true if changed
     * @throws DAOException
     */
    @Override
    public boolean changeStatusBid(int statusBid, int idBid) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(CANCEL_BID);
                preparedStatement.setInt(1, statusBid);
                preparedStatement.setInt(2, idBid);
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                throw new DAOException("Exception in method BidDAO.changeStatusBid", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return flag;
    }

    /**
     * The method finds the bid by id of driver
     *
     * @param idDriver is the id of driver
     * @return the bid
     * @throws DAOException
     */
    @Override
    public Bid findBidByIdDriver(int idDriver) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatementFromCity = null;
        PreparedStatement preparedStatementToCity = null;
        PreparedStatement preparedStatementBid = null;
        ResultSet resultSet = null;
        City fromCity = new City();
        City toCity = new City();
        Bid bid = new Bid();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatementFromCity = connection.prepareStatement(FIND_FROM_CITY_BY_AUTO_FLIGHT);
                preparedStatementFromCity.setInt(1, idDriver);
                resultSet = preparedStatementFromCity.executeQuery();
                while (resultSet.next()) {
                    fromCity = createCity(resultSet);
                }
                close(preparedStatementFromCity);

                preparedStatementToCity = connection.prepareStatement(FIND_TO_CITY_BY_AUTO_FLIGHT);
                preparedStatementToCity.setInt(1, idDriver);
                resultSet = preparedStatementToCity.executeQuery();
                while (resultSet.next()) {
                    toCity = createCity(resultSet);
                }
                close(preparedStatementToCity);

                preparedStatementBid = connection.prepareStatement(FIND_BID_FOR_DRIVER);
                preparedStatementBid.setInt(1, idDriver);
                resultSet = preparedStatementBid.executeQuery();
                while (resultSet.next()) {
                    bid = createBid(resultSet, fromCity, toCity);
                }
            } catch (SQLException e) {
                throw new DAOException("SQLException in method BidDAO.findBidByIdDriver", e);
            } finally {
                close(preparedStatementBid);
                pool.putConnection(connection);
            }
        }
        return bid;
    }

    /**
     * The method creates of the city
     *
     * @param resultSet is the resultSet
     * @return the city
     * @throws SQLException
     */
    private City createCity(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt(1));
        city.setNameCity(resultSet.getString(2));
        return city;
    }

    /**
     * The method creates of the bid
     *
     * @param resultSet is the resultSet
     * @param fromCity  is the city from
     * @param toCity    is the city to
     * @return the bid
     * @throws SQLException
     */
    private Bid createBid(ResultSet resultSet, City fromCity,
                          City toCity) throws SQLException {
        Bid bid = new Bid();
        bid.setId(resultSet.getInt(1));
        bid.setValueShipment(resultSet.getInt(2));
        bid.setWeightShipment(resultSet.getInt(3));
        bid.setDateService(resultSet.getDate(4));
        bid.setFromCity(fromCity);
        bid.setToCity(toCity);
        bid.setStatus(resultSet.getInt(5));
        bid.setIdClient(resultSet.getInt(6));
        return bid;
    }

    /**
     * The method finds list of the bids by id and by status of client
     *
     * @param idClient  is the id of client
     * @param statusBid is the status of client
     * @return list of the bids by id and status of client
     * @throws DAOException
     */
    public List<Bid> findListBidsByIdAndStatusClient(int idClient, int statusBid) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatementFromCity = null;
        PreparedStatement preparedStatementToCity = null;
        PreparedStatement preparedStatementBid = null;
        ResultSet resultSet = null;
        LinkedList<City> listFromCity = new LinkedList<>();
        LinkedList<City> listToCity = new LinkedList<>();
        List<Bid> listBid = new ArrayList<>();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatementFromCity = connection.prepareStatement(FIND_FROM_CITY_BY_ID_CLIENT);
                preparedStatementFromCity.setInt(1, idClient);
                preparedStatementFromCity.setInt(2, statusBid);
                resultSet = preparedStatementFromCity.executeQuery();
                while (resultSet.next()) {
                    City city = createCity(resultSet);
                    listFromCity.add(city);
                }
                close(preparedStatementFromCity);

                preparedStatementToCity = connection.prepareStatement(FIND_TO_CITY_BY_ID_CLIENT);
                preparedStatementToCity.setInt(1, idClient);
                preparedStatementToCity.setInt(2, statusBid);
                resultSet = preparedStatementToCity.executeQuery();
                while (resultSet.next()) {
                    City city = createCity(resultSet);
                    listToCity.add(city);
                }
                close(preparedStatementToCity);

                preparedStatementBid = connection.prepareStatement(FIND_LIST_BIDS_BY_ID_CLIENT);
                preparedStatementBid.setInt(1, idClient);
                preparedStatementBid.setInt(2, statusBid);
                resultSet = preparedStatementBid.executeQuery();
                while (resultSet.next()) {
                    City fromCity = listFromCity.poll();
                    City toCity = listToCity.poll();
                    Bid bid = createBid(resultSet, fromCity, toCity);
                    listBid.add(bid);
                }
            } catch (SQLException e) {
                throw new DAOException("SQLException in method BidDAO.findListBidsByIdAndStatusClient", e);
            } finally {
                close(preparedStatementBid);
                pool.putConnection(connection);
            }
        }
        return listBid;
    }

    /**
     * The method finds list the bids by the status
     *
     * @param statusBid is the status of the bid
     * @return list the bids by the status
     * @throws DAOException
     */
    @Override
    public List<Bid> findListBidsByStatus(int statusBid) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatementFromCity = null;
        PreparedStatement preparedStatementToCity = null;
        PreparedStatement preparedStatementBid = null;
        ResultSet resultSet = null;
        LinkedList<City> listFromCity = new LinkedList<>();
        LinkedList<City> listToCity = new LinkedList<>();
        List<Bid> listBid = new ArrayList<>();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatementFromCity = connection.prepareStatement(FIND_FROM_CITY_BY_STATUS_BID);
                preparedStatementFromCity.setInt(1, statusBid);
                resultSet = preparedStatementFromCity.executeQuery();
                while (resultSet.next()) {
                    City city = createCity(resultSet);
                    listFromCity.add(city);
                }
                close(preparedStatementFromCity);

                preparedStatementToCity = connection.prepareStatement(FIND_TO_CITY_BY_STATUS_BID);
                preparedStatementToCity.setInt(1, statusBid);
                resultSet = preparedStatementToCity.executeQuery();
                while (resultSet.next()) {
                    City city = createCity(resultSet);
                    listToCity.add(city);
                }
                close(preparedStatementToCity);

                preparedStatementBid = connection.prepareStatement(FIND_LIST_BIDS_BY_STATUS);
                preparedStatementBid.setInt(1, statusBid);
                resultSet = preparedStatementBid.executeQuery();
                while (resultSet.next()) {
                    City fromCity = listFromCity.poll();
                    City toCity = listToCity.poll();
                    Bid bid = createBid(resultSet, fromCity, toCity);
                    listBid.add(bid);
                }
            } catch (SQLException e) {
                throw new DAOException("SQLException in method BidDAO.findListBidsByStatus", e);
            } finally {
                close(preparedStatementBid);
                pool.putConnection(connection);
            }
        }
        return listBid;
    }

    /**
     * The method searches list of cities
     *
     * @return list of cities
     * @throws DAOException
     */
    @Override
    public List<City> receiveListCities() throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<City> listCities = new ArrayList<>();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(RECEIVE_LIST_CITIES);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    City city = createCity(resultSet);
                    listCities.add(city);
                }
            } catch (SQLException e) {
                throw new DAOException("SQLException in method BidDAO.receiveListCities", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return listCities;
    }

    /**
     * The method deletes of the bid
     *
     * @param idBid is the id of the bid
     * @return true if deleted
     * @throws DAOException
     */
    @Override
    public boolean deleteBid(int idBid) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(DELETE_BID);
                preparedStatement.setInt(1, idBid);
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                throw new DAOException("SQLException in method BidDAO.deleteBid", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return flag;
    }

    /**
     * The method finds the city by name
     *
     * @param name is name of the city
     * @return the city
     * @throws DAOException
     */
    @Override
    public City findCityByName(String name) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        City city = new City();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(FIND_CITY_BY_NAME);
                preparedStatement.setString(1, name);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    city = createCity(resultSet);
                }
            } catch (SQLException e) {
                throw new DAOException("SQLException in method BidDAO.findCityByName", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return city;
    }

    /**
     * The method finds bid by id
     *
     * @param idBid is the id of bid
     * @return the bid
     * @throws DAOException
     */
    @Override
    public Bid findBidById(int idBid) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatementFromCity = null;
        PreparedStatement preparedStatementToCity = null;
        PreparedStatement preparedStatementBid = null;
        ResultSet resultSet = null;
        City fromCity = new City();
        City toCity = new City();
        Bid bid = new Bid();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatementFromCity = connection.prepareStatement(FIND_FROM_CITY_BY_ID_BID);
                preparedStatementFromCity.setInt(1, idBid);
                resultSet = preparedStatementFromCity.executeQuery();
                while (resultSet.next()) {
                    fromCity = createCity(resultSet);
                }
                close(preparedStatementFromCity);

                preparedStatementToCity = connection.prepareStatement(FIND_TO_CITY_BY_ID_BID);
                preparedStatementToCity.setInt(1, idBid);
                resultSet = preparedStatementToCity.executeQuery();
                while (resultSet.next()) {
                    toCity = createCity(resultSet);
                }
                close(preparedStatementToCity);

                preparedStatementBid = connection.prepareStatement(FIND_BID_BY_ID);
                preparedStatementBid.setInt(1, idBid);
                resultSet = preparedStatementBid.executeQuery();
                while (resultSet.next()) {
                    bid = createBid(resultSet, fromCity, toCity);
                }
            } catch (SQLException e) {
                throw new DAOException("SQLException in method BidDAO.findBidById", e);
            } finally {
                close(preparedStatementBid);
                pool.putConnection(connection);
            }
        }
        return bid;
    }
}
