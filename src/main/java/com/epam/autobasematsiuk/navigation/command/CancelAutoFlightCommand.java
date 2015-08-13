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
 * The class CancelAutoFlightCommand cancels the auto flight
 */
public class CancelAutoFlightCommand implements ActionCommand {

    public static final String SESSION_ATTRIBUTE_ID_BID = "idBid";
    public static final String REQUEST_ATTRIBUTE_LIST_ACCEPTED_BIDS = "listAcceptedBids";
    public static final String REQUEST_ATTRIBUTE_LIST_DRIVERS = "listDrivers";
    public static final String REQUEST_ATTRIBUTE_LIST_AUTO = "listAuto";
    public static final String REQUEST_ATTRIBUTE_IMPOSSIBLE_CANCEL_AUTO_FLIGHT = "impossibleCancelAutoFlight";
    public static final String REQUEST_ATTRIBUTE_SUCCESSFULLY_CANCEL = "successfullyCancel";
    public static final String FLAG_PROTECTION_F5_CANCELED_AUTO_FLIGHT = "canceledAutoFlight";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_ACCEPTED_BIDS = "emptyListAcceptedBids";
    public static final int STATUS_ACCEPTED_BID = 2;
    public static final int STATUS_NEW_BID = 0;
    public static final boolean DRIVER_STATUS_BUSY = true;
    public static final boolean DRIVER_STATUS_FREE = false;
    public static final boolean AUTO_STATUS_FREE = false;

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
        try {
            if (session.getAttribute(FLAG_PROTECTION_F5_CANCELED_AUTO_FLIGHT) != null) {
                setDispatcherAttributes(request);
                return configurationManager.getProperty("path.page.bids");
            }
            int idAutoFlight = (int) session.getAttribute(SESSION_ATTRIBUTE_ID_BID);
            ServiceAutoFlight serviceAutoFlight = new ServiceAutoFlight();
            ServiceBid serviceBid = new ServiceBid();
            int idAuto = serviceAutoFlight.findIdAutoByIdAutoFlight(idAutoFlight);
            int idDriver = serviceAutoFlight.findIdDriverByIdAutoFlight(idAutoFlight);
            boolean flag = serviceAutoFlight.deleteAutoFlight(idAutoFlight);
            if (!flag) {
                request.setAttribute(REQUEST_ATTRIBUTE_IMPOSSIBLE_CANCEL_AUTO_FLIGHT,
                        "bids.impossibleCancelAutoFlight");
                setDispatcherAttributes(request);
                return configurationManager.getProperty("path.page.bids");
            }
            serviceBid.changeStatusBid(STATUS_NEW_BID, idAutoFlight);
            serviceAutoFlight.changeStatusAuto(idAuto, AUTO_STATUS_FREE);
            serviceAutoFlight.changeStatusDriver(idDriver, DRIVER_STATUS_FREE);
            setDispatcherAttributes(request);
            request.setAttribute(REQUEST_ATTRIBUTE_SUCCESSFULLY_CANCEL, "bids.successfullyCancel");
            session.setAttribute(FLAG_PROTECTION_F5_CANCELED_AUTO_FLIGHT, "canceledAutoFlight");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.bids");
    }

    /**
     * The method sets attributes dispatcher
     *
     * @param request is the request
     * @throws ServiceException
     */
    private void setDispatcherAttributes(HttpServletRequest request) throws ServiceException {
        ServiceAutoFlight serviceAutoFlight = new ServiceAutoFlight();
        ServiceBid serviceBid = new ServiceBid();
        List<Bid> listAcceptedBids = serviceBid.findListBidsByStatus(STATUS_ACCEPTED_BID);
        if (listAcceptedBids.isEmpty()) {
            request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_ACCEPTED_BIDS, "bids.emptyListAcceptedBids");
        } else {
            List<User> listDrivers = serviceAutoFlight.findListDriversByStatus(DRIVER_STATUS_BUSY);
            List<Auto> listAuto = serviceAutoFlight.findListAutoFromAutoFlights();
            request.setAttribute(REQUEST_ATTRIBUTE_LIST_ACCEPTED_BIDS, listAcceptedBids);
            request.setAttribute(REQUEST_ATTRIBUTE_LIST_DRIVERS, listDrivers);
            request.setAttribute(REQUEST_ATTRIBUTE_LIST_AUTO, listAuto);
        }
    }
}
