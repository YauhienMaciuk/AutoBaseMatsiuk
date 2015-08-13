package com.epam.autobasematsiuk.database.dao;

import com.epam.autobasematsiuk.entity.User;
import com.epam.autobasematsiuk.exception.DAOException;

/**
 * The Class AbstractUserDAO. Base abstract class for the class UserDAO.
 */
public abstract class AbstractUserDAO extends AbstractDAO {

    /**
     * The method adds new client
     *
     * @param user is the new client
     * @return true if added
     * @throws DAOException
     */
    public abstract boolean addClient(User user) throws DAOException;

    /**
     * The method checks the login
     *
     * @param login is the login
     * @return true if that login exists in database
     * @throws DAOException
     */
    public abstract boolean checkLogin(String login) throws DAOException;

    /**
     * The method finds user
     *
     * @param login    is the login
     * @param password is the password
     * @return user
     * @throws DAOException
     */
    public abstract User findUser(String login, String password) throws DAOException;

}
