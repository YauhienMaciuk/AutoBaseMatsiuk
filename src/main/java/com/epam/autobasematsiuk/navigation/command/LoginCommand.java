package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.entity.Role;
import com.epam.autobasematsiuk.entity.User;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceAutoFlight;
import com.epam.autobasematsiuk.service.ServiceBid;
import com.epam.autobasematsiuk.service.ServiceUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class LoginCommand adds user to the session.
 */
public class LoginCommand implements ActionCommand {

    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD = "password";
    public static final String SESSION_ATTRIBUTE_NAME_ROLE = "role";
    public static final String REQUEST_ATTRIBUTE_NEW_BIDS = "newBids";
    public static final String REQUEST_ATTRIBUTE_BID_FOR_DRIVER = "bidForDriver";
    public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";
    public static final String REQUEST_ATTRIBUTE_INCORRECT_LOGIN_PASSWORD = "incorrectLogPass";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_NEW_BIDS = "emptyListNewBids";
    public static final String REQUEST_ATTRIBUTE_NOT_AUTO_FLIGHT = "notAutoFlight";
    public static final String REQUEST_PARAMETER_ID_AUTO = "idAuto";
    public static final int ID_DISPATCHER = 1;
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
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        String page = null;
        ServiceUser serviceUser = new ServiceUser();
        try {
            User user = serviceUser.findUser(login, password);
            Role role = user.getRole();
            if (role == null) {
                request.setAttribute(REQUEST_ATTRIBUTE_INCORRECT_LOGIN_PASSWORD, "login.incorrect");
                return configurationManager.getProperty("path.page.login");
            }
            request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_ROLE, role);
            switch (role) {
                case DISPATCHER:
                    setDispatcherAttributes(request);
                    page = configurationManager.getProperty("path.page.bids");
                    break;
                case DRIVER:
                    setDriverAttributes(request, user.getId());
                    page = configurationManager.getProperty("path.page.bidsDriver");
                    break;
                case CLIENT:
                    request.getSession().setAttribute(SESSION_ATTRIBUTE_ID_USER, user.getId());
                    page = configurationManager.getProperty("path.page.start");
                    break;
                default:
                    page = configurationManager.getProperty("path.page.login");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }

    /**
     * The method sets attributes for dispatcher.
     *
     * @param request ia the request
     * @throws ServiceException
     */
    private void setDispatcherAttributes(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        ServiceBid serviceBid = new ServiceBid();
        List<Bid> listNewBids = serviceBid.findListBidsByStatus(STATUS_NEW_BID);
        if (listNewBids.isEmpty()) {
            request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_NEW_BIDS, "bids.emptyListNewBids");
        } else {
            request.setAttribute(REQUEST_ATTRIBUTE_NEW_BIDS, listNewBids);
        }
        session.setAttribute(SESSION_ATTRIBUTE_ID_USER, ID_DISPATCHER);
    }

    /**
     * The method sets attributes for driver.
     *
     * @param request  is the request
     * @param idDriver is the id of driver
     * @throws ServiceException
     */
    private void setDriverAttributes(HttpServletRequest request, int idDriver) throws ServiceException {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_ATTRIBUTE_ID_USER, idDriver);
        ServiceBid serviceBid = new ServiceBid();
        Bid bidForDriver = serviceBid.findBidByIdDriver(idDriver);
        if (bidForDriver.getId() > 0) {
            ServiceAutoFlight serviceAutoFlight = new ServiceAutoFlight();
            int idAutoForAutoFlight = serviceAutoFlight.findIdAutoByIdAutoFlight(bidForDriver.getId());
            session.setAttribute(REQUEST_PARAMETER_ID_AUTO, idAutoForAutoFlight);
            session.setAttribute(REQUEST_ATTRIBUTE_BID_FOR_DRIVER, bidForDriver);
        } else {
            request.setAttribute(REQUEST_ATTRIBUTE_NOT_AUTO_FLIGHT, "bidsDriver.notAutoFlight");
        }
    }
}
