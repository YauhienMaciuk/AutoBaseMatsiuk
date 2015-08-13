package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class wants to add a new bid
 */
public class WantAddBidCommand implements ActionCommand {

    public static final String PARAM_FROM_CITY = "fromCity";
    public static final String PARAM_TO_CITY = "toCity";
    public static final String PARAM_VALUE_SHIPMENT = "value";
    public static final String PARAM_WEIGHT_SHIPMENT = "weight";
    public static final String PARAM_DATA = "date";
    public static final String REQUEST_ATTRIBUTE_WRONG_CITY = "wrongCity";
    public static final String REQUEST_ATTRIBUTE_INCORRECT_DATE = "incorrectDate";
    public static final String REQUEST_ATTRIBUTE_NAME_FROM_CITY = "nameFromCity";
    public static final String REQUEST_ATTRIBUTE_NAME_TO_CITY = "nameToCity";
    public static final String FLAG_PROTECTION_F5_ADDED_BID = "addedBid";
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
        if (session.getAttribute(FLAG_PROTECTION_F5_ADDED_BID) != null) {
            session.removeAttribute(FLAG_PROTECTION_F5_ADDED_BID);
        }
        String nameFromCity = request.getParameter(PARAM_FROM_CITY);
        String nameToCity = request.getParameter(PARAM_TO_CITY);
        String value = request.getParameter(PARAM_VALUE_SHIPMENT);
        String weight = request.getParameter(PARAM_WEIGHT_SHIPMENT);
        String date = request.getParameter(PARAM_DATA);
        Validator validator = new Validator();
        request.setAttribute(REQUEST_ATTRIBUTE_NAME_FROM_CITY, nameFromCity);
        request.setAttribute(REQUEST_ATTRIBUTE_NAME_TO_CITY, nameToCity);
        request.setAttribute(PARAM_VALUE_SHIPMENT, value);
        request.setAttribute(PARAM_WEIGHT_SHIPMENT, weight);
        request.setAttribute(PARAM_DATA, date);
        if (nameFromCity.equals(nameToCity)) {
            request.setAttribute(REQUEST_ATTRIBUTE_WRONG_CITY, "addBid.wrongCity");
            return configurationManager.getProperty("path.page.addBid");
        }
        if (!validator.checkDate(date)) {
            request.setAttribute(REQUEST_ATTRIBUTE_INCORRECT_DATE, "addBid.incorrectDate");
            return configurationManager.getProperty("path.page.addBid");
        }
        return configurationManager.getProperty("path.page.confirmBid");
    }
}
