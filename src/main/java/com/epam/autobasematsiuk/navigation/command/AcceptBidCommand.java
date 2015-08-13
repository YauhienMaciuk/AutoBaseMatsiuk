package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Auto;
import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.entity.User;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceAutoFlight;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class AcceptBidCommand. Dispatcher accepts a new bid from client.
 */
public class AcceptBidCommand implements ActionCommand {

    public static final String PARAM_VALUE_SHIPMENT = "value";
    public static final String PARAM_WEIGHT_SHIPMENT = "weight";
    public static final String REQUEST_ATTRIBUTE_LIST_AUTO = "listAuto";
    public static final String REQUEST_PARAMETER_ID_BID = "idBid";
    public static final String REQUEST_ATTRIBUTE_LIST_DRIVERS = "listDrivers";
    public static final String REQUEST_ATTRIBUTE_NOT_AVAILABLE_AUTO = "notAvailableAuto";
    public static final String REQUEST_ATTRIBUTE_NEW_BIDS = "newBids";
    public static final String FLAG_PROTECTION_F5_ADD_AUTO_FLIGHT = "addAutoFlight";
    public static final int STATUS_NEW_BID = 0;
    public static final boolean DRIVER_STATUS_FREE = false;

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
        if (session.getAttribute(FLAG_PROTECTION_F5_ADD_AUTO_FLIGHT) != null) {
            session.removeAttribute(FLAG_PROTECTION_F5_ADD_AUTO_FLIGHT);
        }
        int valueShipment = Integer.parseInt(request.getParameter(PARAM_VALUE_SHIPMENT));
        int weightShipment = Integer.parseInt(request.getParameter(PARAM_WEIGHT_SHIPMENT));
        ServiceAutoFlight serviceAutoFlight = new ServiceAutoFlight();
        ServiceBid serviceBid = new ServiceBid();
        try {
            List<Auto> listAuto = serviceAutoFlight.findListAutoForAutoFlight(valueShipment, weightShipment);
            if (listAuto.isEmpty()) {
                List<Bid> listNewBids = serviceBid.findListBidsByStatus(STATUS_NEW_BID);
                request.setAttribute(REQUEST_ATTRIBUTE_NEW_BIDS, listNewBids);
                request.setAttribute(REQUEST_ATTRIBUTE_NOT_AVAILABLE_AUTO, "autoFlight.notAvailableAuto");
                return configurationManager.getProperty("path.page.bids");
            }
            request.setAttribute(REQUEST_ATTRIBUTE_LIST_AUTO, listAuto);
            List<User> listAvailableDrivers = serviceAutoFlight.findListDriversByStatus(DRIVER_STATUS_FREE);
            request.setAttribute(REQUEST_ATTRIBUTE_LIST_DRIVERS, listAvailableDrivers);
            int idBid = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_ID_BID));
            request.setAttribute(REQUEST_PARAMETER_ID_BID, idBid);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.createAutoFlight");
    }
}
