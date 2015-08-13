package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.City;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class AddBidCommand creates a new bid
 */
public class AddBidCommand implements ActionCommand {

    public static final String REQUEST_PARAMETER_NAME_FROM_CITY = "nameFromCity";
    public static final String REQUEST_PARAMETER_NAME_TO_CITY = "nameToCity";
    public static final String REQUEST_PARAMETER_VALUE = "value";
    public static final String REQUEST_PARAMETER_WEIGHT = "weight";
    public static final String REQUEST_PARAMETER_DATE = "date";
    public static final String SESSION_ATTRIBUTE_ID_CLIENT = "idUser";
    public static final String REQUEST_ATTRIBUTE_BID_CREATED = "bidCreated";
    public static final String REQUEST_ATTRIBUTE_BID_NOT_CREATED = "bidNotCreated";
    public static final int STATUS_NEW_BID = 0;
    public static final String FLAG_PROTECTION_F5_ADDED_BID = "addedBid";

    /**
     * The method execute
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String nameFromCity = request.getParameter(REQUEST_PARAMETER_NAME_FROM_CITY);
        String nameToCity = request.getParameter(REQUEST_PARAMETER_NAME_TO_CITY);
        int valueShipment = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_VALUE));
        int weightShipment = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_WEIGHT));
        String date = request.getParameter(REQUEST_PARAMETER_DATE);
        int idClient = (int) session.getAttribute(SESSION_ATTRIBUTE_ID_CLIENT);
        ServiceBid serviceBid = new ServiceBid();
        try {
            if (session.getAttribute(FLAG_PROTECTION_F5_ADDED_BID) != null) {
                return configurationManager.getProperty("path.page.addBid");
            }
            City fromCity = serviceBid.findCityByName(nameFromCity);
            City toCity = serviceBid.findCityByName(nameToCity);
            boolean flag = serviceBid.addBid(fromCity, toCity, valueShipment, weightShipment, date, idClient);
            if (!flag) {
                request.setAttribute(REQUEST_ATTRIBUTE_BID_NOT_CREATED, "addBid.notCreated");
                return configurationManager.getProperty("path.page.addBid");
            } else {
                request.setAttribute(REQUEST_ATTRIBUTE_BID_CREATED, "addBid.created");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        session.setAttribute(FLAG_PROTECTION_F5_ADDED_BID, "addedBid");
        return configurationManager.getProperty("path.page.addBid");
    }
}
