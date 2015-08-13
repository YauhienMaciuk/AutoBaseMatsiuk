package com.epam.autobasematsiuk.database.dao;

import com.epam.autobasematsiuk.database.pool.ConnectionPool;
import com.epam.autobasematsiuk.entity.Role;
import com.epam.autobasematsiuk.entity.User;
import com.epam.autobasematsiuk.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The class UserDAO
 */
public class UserDAO extends AbstractUserDAO {

    public static final String ADD_CLIENT = "INSERT INTO user (login, password, first_name, last_name, id_role) VALUES (?, ?, ?, ?, 3)";
    public static final String CHECK_LOGIN = "SELECT login FROM user WHERE login = ?";
    public static final String FIND_ROLE = "SELECT role FROM role WHERE id_role = (SELECT id_role FROM user WHERE login = ? AND password = ?)";
    public static final String FIND_USER_BY_LOGIN = "SELECT id, login, password, first_name, last_name FROM user WHERE login = ? AND password = ?";

    /**
     * The method adds new client
     *
     * @param user is the new client
     * @return true if added
     * @throws DAOException
     */
    @Override
    public boolean addClient(User user) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(ADD_CLIENT);
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFirstName());
                preparedStatement.setString(4, user.getLastName());
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                throw new DAOException("Exception in method UserDAO.addClient", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return flag;
    }

    /**
     * The method checks the login
     *
     * @param login is the login
     * @return true if that login exists in database
     * @throws DAOException
     */
    @Override
    public boolean checkLogin(String login) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(CHECK_LOGIN);
                preparedStatement.setString(1, login);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    flag = true;
                }
            } catch (SQLException e) {
                throw new DAOException("Exception in method UserDAO.checkLogin", e);
            } finally {
                close(preparedStatement);
                pool.putConnection(connection);
            }
        }
        return flag;
    }

    /**
     * The method finds user
     *
     * @param login    is the login
     * @param password is the password
     * @return user
     * @throws DAOException
     */
    @Override
    public User findUser(String login, String password) throws DAOException {
        ConnectionPool pool = null;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatementRole = null;
        PreparedStatement preparedStatementUser = null;
        User user = new User();
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        if (connection != null) {
            try {
                preparedStatementRole = connection.prepareStatement(FIND_ROLE);
                preparedStatementRole.setString(1, login);
                preparedStatementRole.setString(2, password);
                resultSet = preparedStatementRole.executeQuery();
                Role role = specifyUserRole(resultSet);
                close(preparedStatementRole);

                preparedStatementUser = connection.prepareStatement(FIND_USER_BY_LOGIN);
                preparedStatementUser.setString(1, login);
                preparedStatementUser.setString(2, password);
                resultSet = preparedStatementUser.executeQuery();
                user = createUser(resultSet, role);
            } catch (SQLException e) {
                throw new DAOException("Exception in method UserDAO.findUser", e);
            } finally {
                close(preparedStatementUser);
                pool.putConnection(connection);
            }
        }
        return user;
    }

    /**
     * The method specifies the role of user
     *
     * @param resultSet is the resultSet
     * @return the role
     * @throws SQLException
     */
    private Role specifyUserRole(ResultSet resultSet) throws SQLException {
        Role role = null;
        while (resultSet.next()) {
            role = Role.valueOf(resultSet.getString(1).toUpperCase());
        }
        return role;
    }

    /**
     * The method creates user
     *
     * @param resultSet is the resultSet
     * @param role      is the role
     * @return user
     * @throws SQLException
     */
    private User createUser(ResultSet resultSet, Role role) throws SQLException {
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setFirstName(resultSet.getString(4));
            user.setLastName(resultSet.getString(5));
            user.setRole(role);
        }
        return user;
    }
}
