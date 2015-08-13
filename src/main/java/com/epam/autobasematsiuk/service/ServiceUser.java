package com.epam.autobasematsiuk.service;

import com.epam.autobasematsiuk.database.dao.UserDAO;
import com.epam.autobasematsiuk.encoder.Coder;
import com.epam.autobasematsiuk.entity.Role;
import com.epam.autobasematsiuk.entity.User;
import com.epam.autobasematsiuk.exception.DAOException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.validation.Validator;

/**
 * The class ServiceUser perform work over UserDAO.
 */
public class ServiceUser {

    private UserDAO userDAO;

    /**
     * Instantiates a new ServiceUser.
     */
    public ServiceUser() {
        userDAO = new UserDAO();
    }

    /**
     * The method adds a new client to the database
     *
     * @param login     is login of new client
     * @param password  is password of new client
     * @param firstName is first name of new client
     * @param lastName  is last name of new client
     * @return true if added
     * @throws ServiceException
     */
    public boolean addClient(String login, String password, String firstName, String lastName)
            throws ServiceException {
        Validator validator = new Validator();
        Coder coder = new Coder();
        boolean flag = validator.validateNewClient(firstName, lastName, login, password);
        if (!flag) {
            return false;
        }
        String hashLogin = null;
        try {
            hashLogin = coder.doHash(login);
            String hashPassword = coder.doHash(password);
            User user = new User(0, hashLogin, hashPassword, firstName, lastName, Role.CLIENT);
            flag = userDAO.addClient(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    /**
     * The method finds of user
     *
     * @param login    is login of user
     * @param password is password of user
     * @return found user
     * @throws ServiceException
     */
    public User findUser(String login, String password) throws ServiceException {
        Coder coder = new Coder();
        String hashLogin = null;
        User user = null;
        try {
            hashLogin = coder.doHash(login);
            String hashPassword = coder.doHash(password);
            user = userDAO.findUser(hashLogin, hashPassword);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    /**
     * The method checks the login of the new user
     *
     * @param login is the verifiable login
     * @return true if the login is free
     * @throws ServiceException
     */
    public boolean checkLogin(String login) throws ServiceException {
        Coder coder = new Coder();
        String hashLogin = null;
        boolean flag = false;
        try {
            hashLogin = coder.doHash(login);
            flag = userDAO.checkLogin(hashLogin);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
