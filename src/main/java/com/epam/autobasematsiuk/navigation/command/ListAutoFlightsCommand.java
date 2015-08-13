package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Auto;
import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.entity.User;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceAutoFlight;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class searches list of auto flights
 */
public class ListAutoFlightsCommand implements ActionCommand {

    public static final int STATUS_ACCEPTED_BID = 2;
    public static final String REQUEST_ATTRIBUTE_LIST_ACCEPTED_BIDS = "listAcceptedBids";
    public static final String REQUEST_ATTRIBUTE_LIST_DRIVERS = "listDrivers";
    public static final String REQUEST_ATTRIBUTE_LIST_AUTO = "listAuto";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_ACCEPTED_BIDS = "emptyListAcceptedBids";
    public static final boolean DRIVER_STATUS_BUSY = true;

    /**
     * The method execute
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceAutoFlight serviceAutoFlight = new ServiceAutoFlight();
        ServiceBid serviceBid = new ServiceBid();
        try {
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
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.bids");
    }
}
