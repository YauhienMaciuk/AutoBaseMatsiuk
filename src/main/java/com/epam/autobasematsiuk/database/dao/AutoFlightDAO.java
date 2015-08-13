package com.epam.autobasematsiuk.database.dao;

import com.epam.autobasematsiuk.database.pool.ConnectionPool;
import com.epam.autobasematsiuk.entity.*;
import com.epam.autobasematsiuk.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class AutoFlightDAO
 */
public class AutoFlightDAO extends AbstractAutoFlightDAO {

    public static final String FIND_ALL_AUTO_FOR_AUTO_FLIGHT = "SELECT * FROM auto WHERE value_space >= ? && bearing_capacity >= ? && working_condition = false && status = false";
    public static final String FIND_LIST_AUTO_BY_WORKING_CONDITION = "SELECT * FROM auto WHERE status = FALSE AND working_condition = ?";
    public static final String CHANGE_WORKING_CONDITION_AUTO = "UPDATE auto SET working_condition = ? WHERE id_auto = ?";
    public static final String CHANGE_STATUS_DRIVER = "UPDATE user SET status = ? WHERE id = ?";
    public static final String CHANGE_STATUS_AUTO = "UPDATE auto SET status = ? WHERE id_auto = ?";
    public static final String DELETE_AUTO_FLIGHT = "DELETE FROM auto_flight WHERE id_order = ?";
    public static final String FIND_AVAILABLE_DRIVERS = "SELECT * FROM user WHERE id_role = 2 AND status = FALSE ";
    public static final String CREATE_AUTO_FLIGHT = "INSERT INTO auto_flight (id_order, id_driver, id_auto) VALUES (?, ?, ?)";
    public static final String FIND_ID_AUTO_BY_ID_AUTO_FLIGHT = "SELECT id_auto FROM auto_flight WHERE id_order = ?";
    public static final String FIND_LIST_AUTO_BY_AUTO_FLIGHT = "SELECT auto.* FROM auto_flight LEFT JOIN auto ON auto.id_auto = auto_flight.id_auto ORDER BY auto_flight.id_order";
    public static final String FIND_LIST_DRIVERS_FROM_AUTO_FLIGHTS = "SELECT user.* FROM user INNER JOIN auto_flight ON auto_flight.id_driver = user.id ORDER BY auto_flight.id_order";
    public static final String FIND_ID_DRIVER_BY_ID_AUTO_FLIGHT = "SELECT id_driver FROM auto_flight WHERE id_order = ?";

    /**
     * The method finds list of the auto for auto flight
     *
     * @param valueShipment  is the value of shipment
     * @param weightShipment is the weight of shipment
     * @return list of the auto for auto flight
     * @throws DAOException
     */
    @Override
    public List<Auto> findListAutoForAutoFlight(int valueShipment, int weightShipment)
            throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Auto> listAuto = new ArrayList<>();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(FIND_ALL_AUTO_FOR_AUTO_FLIGHT);
                preparedStatement.setInt(1, valueShipment);
                preparedStatement.setInt(2, weightShipment);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Auto auto = createAuto(resultSet);
                    listAuto.add(auto);
                }
            } catch (SQLException e) {
                throw new DAOException("SQLException in method AutoFlightDAO.findListAutoForAutoFlight", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return listAuto;
    }

    /**
     * The method creates auto
     *
     * @param resultSet is the resultSet
     * @return the auto
     * @throws SQLException
     */
    private Auto createAuto(ResultSet resultSet) throws SQLException {
        Auto auto = new Auto();
        auto.setId(resultSet.getInt(1));
        auto.setMark(resultSet.getString(2));
        auto.setValueSpace(resultSet.getInt(3));
        auto.setBearingCapacity(resultSet.getInt(4));
        auto.setWorkingCondition(resultSet.getBoolean(5));
        auto.setStatus(resultSet.getBoolean(6));
        return auto;
    }

    /**
     * The method finds list of auto by condition
     *
     * @param condition is the condition auto
     * @return list of auto by condition
     * @throws DAOException
     */
    @Override
    public List<Auto> findListAutoByCondition(boolean condition) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Auto> listAuto = new ArrayList<>();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(FIND_LIST_AUTO_BY_WORKING_CONDITION);
                preparedStatement.setBoolean(1, condition);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Auto auto = createAuto(resultSet);
                    listAuto.add(auto);
                }
            } catch (SQLException e) {
                throw new DAOException("SQLException in method AutoFlightDAO.findListAutoByCondition", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return listAuto;
    }

    /**
     * The method changes working condition the auto
     *
     * @param workingConditionAuto is the new working condition the auto
     * @param idAuto               is the id of the auto
     * @return true if changed
     * @throws DAOException
     */
    @Override
    public boolean changeWorkingConditionAuto(boolean workingConditionAuto, int idAuto)
            throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(CHANGE_WORKING_CONDITION_AUTO);
                preparedStatement.setBoolean(1, workingConditionAuto);
                preparedStatement.setInt(2, idAuto);
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                throw new DAOException("SQLException in method AutoFlightDAO.changeWorkingConditionAuto", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return flag;
    }

    /**
     * The method changes status of driver
     *
     * @param idDriver is the id of driver
     * @param status   is the new status
     * @return true if changed
     * @throws DAOException
     */
    @Override
    public boolean changeStatusDriver(int idDriver, boolean status)
            throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(CHANGE_STATUS_DRIVER);
                preparedStatement.setBoolean(1, status);
                preparedStatement.setInt(2, idDriver);
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                throw new DAOException("SQLException in method AutoFlightDAO.changeStatusDriver", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return flag;
    }

    /**
     * The method changes status of the auto
     *
     * @param idAuto is the id of auto
     * @param status is the new status
     * @return true if changed
     * @throws DAOException
     */
    @Override
    public boolean changeStatusAuto(int idAuto, boolean status) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(CHANGE_STATUS_AUTO);
                preparedStatement.setBoolean(1, status);
                preparedStatement.setInt(2, idAuto);
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                throw new DAOException("SQLException in method AutoFlightDAO.changeStatusAuto", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return flag;
    }

    /**
     * The method deletes auto flight
     *
     * @param idAutoFlight is the id of the auto flight
     * @return true if deleted
     * @throws DAOException
     */
    @Override
    public boolean deleteAutoFlight(int idAutoFlight) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(DELETE_AUTO_FLIGHT);
                preparedStatement.setInt(1, idAutoFlight);
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                throw new DAOException("SQLException in method AutoFlightDAO.deleteAutoFlight", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return flag;
    }

    /**
     * The method finds drivers by status
     *
     * @return list of drivers by status
     * @throws DAOException
     */
    @Override
    public List<User> findListDriversByStatus(boolean status) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> listDrivers = new ArrayList<>();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                if (status) {
                    preparedStatement = connection.prepareStatement(FIND_LIST_DRIVERS_FROM_AUTO_FLIGHTS);
                } else {
                    preparedStatement = connection.prepareStatement(FIND_AVAILABLE_DRIVERS);
                }
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User driver = createDriver(resultSet);
                    listDrivers.add(driver);
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in method UserDAO.findListDriversByStatus", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return listDrivers;
    }

    /**
     * The method adds a new auto flight
     *
     * @param idBid    is the id of the bid
     * @param idDriver is the id of driver
     * @param idAuto   is the id of the auto
     * @return true if added
     * @throws DAOException
     */
    @Override
    public boolean addAutoFlight(int idBid, int idDriver, int idAuto) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(CREATE_AUTO_FLIGHT);
                preparedStatement.setInt(1, idBid);
                preparedStatement.setInt(2, idDriver);
                preparedStatement.setInt(3, idAuto);
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                throw new DAOException("Exception in method AutoFlightDAO.addAutoFlight", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return flag;
    }

    /**
     * The method finds id of the auto by the id of the auto flight
     *
     * @param idAutoFlight is the id of the auto flight
     * @return id of the auto
     * @throws DAOException
     */
    @Override
    public int findIdAutoByIdAutoFlight(int idAutoFlight) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int idAuto = 0;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(FIND_ID_AUTO_BY_ID_AUTO_FLIGHT);
                preparedStatement.setInt(1, idAutoFlight);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                idAuto = resultSet.getInt(1);
            } catch (SQLException e) {
                throw new DAOException("Exception in method AutoFlightDAO.findAutoByIdAutoFlight", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return idAuto;
    }

    /**
     * The method finds list auto from auto flights
     *
     * @return list auto from auto flight
     * @throws DAOException
     */
    @Override
    public List<Auto> findListAutoFromAutoFlights() throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Auto> listAuto = new ArrayList<>();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(FIND_LIST_AUTO_BY_AUTO_FLIGHT);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Auto auto = createAuto(resultSet);
                    listAuto.add(auto);
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in method AutoFlightDAO.findListAutoFromAutoFlights", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return listAuto;
    }

    /**
     * The method finds id driver by id of the auto flight
     *
     * @param idAutoFlight is the id of the auto flight
     * @return id driver
     * @throws DAOException
     */
    @Override
    public int findIdDriverByIdAutoFlight(int idAutoFlight) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int idDriver = 0;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(FIND_ID_DRIVER_BY_ID_AUTO_FLIGHT);
                preparedStatement.setInt(1, idAutoFlight);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                idDriver = resultSet.getInt(1);
            } catch (SQLException e) {
                throw new DAOException("Exception in method AutoFlightDAO.findIdDriverByIdAutoFlight", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return idDriver;
    }

    /**
     * The method creates driver
     *
     * @param resultSet is the resultSet
     * @return driver
     * @throws SQLException
     */
    private User createDriver(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setLogin(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setFirstName(resultSet.getString(4));
        user.setLastName(resultSet.getString(5));
        user.setRole(Role.DRIVER);
        return user;
    }
}
