package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.entity.City;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;
import com.epam.autobasematsiuk.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class changes a bid
 */
public class ChangeBidCommand implements ActionCommand {

    public static final String REQUEST_PARAMETER_FROM_CITY = "fromCity";
    public static final String REQUEST_PARAMETER_TO_CITY = "toCity";
    public static final String REQUEST_PARAMETER_VALUE_SHIPMENT = "value";
    public static final String REQUEST_PARAMETER_WEIGHT_SHIPMENT = "weight";
    public static final String REQUEST_PARAMETER_DATE = "date";
    public static final String REQUEST_ATTRIBUTE_NAME_FROM_CITY = "nameFromCity";
    public static final String REQUEST_ATTRIBUTE_NAME_TO_CITY = "nameToCity";
    public static final String SESSION_ATTRIBUTE_ID_CLIENT = "idUser";
    public static final String REQUEST_ATTRIBUTE_CHANGED_BID = "changedBid";
    public static final String REQUEST_ATTRIBUTE_WRONG_CITY = "wrongCity";
    public static final String REQUEST_ATTRIBUTE_INCORRECT_DATE = "incorrectDate";
    public static final String ID_BID = "idBid";
    public static final int STATUS_NEW_BID = 0;
    public static final String FLAG_PROTECTION_F5_CANCELED_BID = "canceledBid";
    public static final String REQUEST_ATTRIBUTE_NEW_BIDS = "newBids";

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
        int idBid = Integer.parseInt(request.getParameter(ID_BID));
        String nameFromCity = request.getParameter(REQUEST_PARAMETER_FROM_CITY);
        String nameToCity = request.getParameter(REQUEST_PARAMETER_TO_CITY);
        String value = request.getParameter(REQUEST_PARAMETER_VALUE_SHIPMENT);
        String weight = request.getParameter(REQUEST_PARAMETER_WEIGHT_SHIPMENT);
        String date = request.getParameter(REQUEST_PARAMETER_DATE);
        int valueShipment = Integer.parseInt(value);
        int weightShipment = Integer.parseInt(weight);
        ServiceBid serviceBid = new ServiceBid();
        try {
            int idClient = (int) session.getAttribute(SESSION_ATTRIBUTE_ID_CLIENT);
            if (session.getAttribute(FLAG_PROTECTION_F5_CANCELED_BID) != null) {
                setClientAttributes(request, idClient);
                return configurationManager.getProperty("path.page.listBids");
            }
            Validator validator = new Validator();
            request.setAttribute(REQUEST_ATTRIBUTE_NAME_FROM_CITY, nameFromCity);
            request.setAttribute(REQUEST_ATTRIBUTE_NAME_TO_CITY, nameToCity);
            request.setAttribute(REQUEST_PARAMETER_VALUE_SHIPMENT, value);
            request.setAttribute(REQUEST_PARAMETER_WEIGHT_SHIPMENT, weight);
            request.setAttribute(REQUEST_PARAMETER_DATE, date);
            request.setAttribute(ID_BID, idBid);
            if (nameFromCity.equals(nameToCity)) {
                request.setAttribute(REQUEST_ATTRIBUTE_WRONG_CITY, "addBid.wrongCity");
                return configurationManager.getProperty("path.page.changeBid");
            }
            if (!validator.checkDate(date)) {
                request.setAttribute(REQUEST_ATTRIBUTE_INCORRECT_DATE, "addBid.incorrectDate");
                return configurationManager.getProperty("path.page.changeBid");
            }
            City fromCity = serviceBid.findCityByName(nameFromCity);
            City toCity = serviceBid.findCityByName(nameToCity);
            serviceBid.deleteBid(idBid);
            serviceBid.addBid(fromCity, toCity, valueShipment, weightShipment, date, idClient);
            request.setAttribute(REQUEST_ATTRIBUTE_CHANGED_BID, "addBid.changedBid");
            setClientAttributes(request, idClient);
            session.setAttribute(FLAG_PROTECTION_F5_CANCELED_BID, "flagCanceledBid");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.listBids");
    }

    /**
     * The method sets attributes for client.
     *
     * @param request  is the request
     * @param idClient is the id of client
     * @throws ServiceException
     */
    private void setClientAttributes(HttpServletRequest request, int idClient) throws ServiceException {
        ServiceBid serviceBid = new ServiceBid();
        List<Bid> listBidClient = serviceBid.findListBidsByIdAndStatusClient(idClient, STATUS_NEW_BID);
        request.setAttribute(REQUEST_ATTRIBUTE_NEW_BIDS, listBidClient);
    }
}
