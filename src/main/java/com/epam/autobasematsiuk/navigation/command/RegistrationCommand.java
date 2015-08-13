package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.City;
import com.epam.autobasematsiuk.entity.User;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;
import com.epam.autobasematsiuk.service.ServiceUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class RegistrationCommand for registration a new client and
 * adds this client to the session.
 */
public class RegistrationCommand implements ActionCommand {

    public static final String PARAM_CLIENT_FIST_NAME = "firstName";
    public static final String PARAM_CLIENT_LAST_NAME = "lastName";
    public static final String PARAM_CLIENT_LOGIN = "login";
    public static final String PARAM_CLIENT_PASSWORD = "password";
    public static final String REQUEST_ATTRIBUTE_ERROR_REGISTRATION = "errorRegistration";
    public static final String SESSION_ATTRIBUTE_LIST_CITIES = "listCities";
    public static final String SESSION_ATTRIBUTE_ID_CLIENT = "idUser";
    public static final String PARAM_CONFIRM_PASSWORD = "confirmPassword";
    public static final String REQUEST_ATTRIBUTE_INCORRECT_PASSWORD = "incorrectConfirmPass";
    public static final String REQUEST_ATTRIBUTE_USER_ALREADY_EXISTS = "userAlreadyExists";
    public static final int STATUS_NEW_BID = 0;

    /**
     * The method execute.
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
            if (session.getAttribute(SESSION_ATTRIBUTE_ID_CLIENT) != null) {
                int idClient = (int) session.getAttribute(SESSION_ATTRIBUTE_ID_CLIENT);
                setClientAttributes(request, idClient);
            } else {
                String firstName = request.getParameter(PARAM_CLIENT_FIST_NAME);
                String lastName = request.getParameter(PARAM_CLIENT_LAST_NAME);
                String login = request.getParameter(PARAM_CLIENT_LOGIN);
                String password = request.getParameter(PARAM_CLIENT_PASSWORD);
                String confirmPassword = request.getParameter(PARAM_CONFIRM_PASSWORD);
                request.setAttribute(PARAM_CLIENT_FIST_NAME, firstName);
                request.setAttribute(PARAM_CLIENT_LAST_NAME, lastName);
                request.setAttribute(PARAM_CLIENT_LOGIN, login);
                if (!password.equals(confirmPassword)) {
                    request.setAttribute(REQUEST_ATTRIBUTE_INCORRECT_PASSWORD, "option.incorrectPass");
                    return configurationManager.getProperty("path.page.registration");
                }
                ServiceUser serviceUser = new ServiceUser();
                boolean checkLog = serviceUser.checkLogin(login);
                if (!checkLog) {
                    request.setAttribute(REQUEST_ATTRIBUTE_USER_ALREADY_EXISTS, "registration.userAlreadyExists");
                    return configurationManager.getProperty("path.page.registration");
                }
                boolean flag = serviceUser.addClient(login, password, firstName, lastName);
                if (!flag) {
                    request.setAttribute(REQUEST_ATTRIBUTE_ERROR_REGISTRATION, "registration.error");
                    return configurationManager.getProperty("path.page.registration");
                }
                User client = serviceUser.findUser(login, password);
                setClientAttributes(request, client.getId());
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.addBid");
    }

    /**
     * The method sets attributes for client
     *
     * @param request  is the request
     * @param idClient is the id of client
     * @throws ServiceException
     */
    private void setClientAttributes(HttpServletRequest request, int idClient) throws ServiceException {
        HttpSession session = request.getSession();
        ServiceBid serviceBid = new ServiceBid();
        List<City> listCities = serviceBid.receiveListCities();
        session.setAttribute(SESSION_ATTRIBUTE_LIST_CITIES, listCities);
        session.setAttribute(SESSION_ATTRIBUTE_ID_CLIENT, idClient);
    }
}
