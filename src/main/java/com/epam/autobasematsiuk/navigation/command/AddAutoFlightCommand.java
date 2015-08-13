package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceAutoFlight;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class AddAutoFlightCommand creates a new auto flight.
 */
public class AddAutoFlightCommand implements ActionCommand {

    public static final String REQUEST_PARAMETER_ID_BID = "idBid";
    public static final String REQUEST_ATTRIBUTE_FAILED_CREATE_AUTO_FLIGHT = "failedCreateAutoFlight";
    public static final String REQUEST_ATTRIBUTE_SUCCESSFULLY_CREATED = "successfullyCreated";
    public static final String REQUEST_ATTRIBUTE_NEW_BIDS = "newBids";
    public static final String REQUEST_PARAMETER_ID_AUTO = "idAuto";
    public static final String REQUEST_PARAMETER_ID_DRIVER = "idDriver";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_NEW_BIDS = "emptyListNewBids";
    public static final String FLAG_PROTECTION_F5_ADD_AUTO_FLIGHT = "addAutoFlight";
    public static final int STATUS_BID_ACCEPT = 2;
    public static final int STATUS_NEW_BID = 0;
    public static final boolean DRIVER_STATUS_BUSY = true;
    public static final boolean AUTO_STATUS_BUSY = true;

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
        ServiceBid serviceBid = new ServiceBid();
        try {
            if (session.getAttribute(FLAG_PROTECTION_F5_ADD_AUTO_FLIGHT) == null) {
                int idBid = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_ID_BID));
                int idAuto = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_ID_AUTO));
                int idDriver = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_ID_DRIVER));
                ServiceAutoFlight serviceAutoFlight = new ServiceAutoFlight();
                boolean flag = serviceAutoFlight.addAutoFlight(idBid, idDriver, idAuto);
                if (!flag) {
                    request.setAttribute(REQUEST_ATTRIBUTE_FAILED_CREATE_AUTO_FLIGHT, "autoFlight.failed");
                    return configurationManager.getProperty("path.page.createAutoFlight");
                }
                serviceAutoFlight.changeStatusDriver(idDriver, DRIVER_STATUS_BUSY);
                serviceAutoFlight.changeStatusAuto(idAuto, AUTO_STATUS_BUSY);
                serviceBid.changeStatusBid(STATUS_BID_ACCEPT, idBid);
                request.setAttribute(REQUEST_ATTRIBUTE_SUCCESSFULLY_CREATED, "autoFlight.created");
                session.setAttribute(FLAG_PROTECTION_F5_ADD_AUTO_FLIGHT, "addAutoFlight");
            }
            setDispatcherAttributes(request, serviceBid);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.bids");
    }

    /**
     * The method sets attributes dispatcher
     *
     * @param request    is the request
     * @param serviceBid is the object of ServiceBid
     * @throws ServiceException
     */
    private void setDispatcherAttributes(HttpServletRequest request, ServiceBid serviceBid)
            throws ServiceException {
        List<Bid> listNewBids = serviceBid.findListBidsByStatus(STATUS_NEW_BID);
        if (listNewBids.isEmpty()) {
            request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_NEW_BIDS, "bids.emptyListNewBids");
        } else {
            request.setAttribute(REQUEST_ATTRIBUTE_NEW_BIDS, listNewBids);
        }
    }
}
