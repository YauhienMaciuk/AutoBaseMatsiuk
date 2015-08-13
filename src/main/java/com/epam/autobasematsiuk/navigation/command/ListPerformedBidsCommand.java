package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class searches list of performed bids
 */
public class ListPerformedBidsCommand implements ActionCommand {

    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_PERFORMED_BID_CLIENT = "emptyListPerformedBidsClient";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST = "emptyListPerformedBids";
    public static final String REQUEST_ATTRIBUTE_PERFORMED_BIDS = "performedBids";
    public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";
    public static final int STATUS_PERFORMED_BID = 1;
    public static final int ID_DISPATCHER = 1;

    /**
     * The method execute
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int idUser = (int) request.getSession().getAttribute(SESSION_ATTRIBUTE_ID_USER);
        String page = null;
        try {
            if (idUser == ID_DISPATCHER) {
                page = setDispatcherAttributes(request);
            } else {
                page = setClientAttributes(request, idUser);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }

    /**
     * The method sets attributes for client
     *
     * @param request is the request
     * @param idUser  is the id of user
     * @return the String. It's the path to the page.
     * @throws ServiceException
     */
    private String setClientAttributes(HttpServletRequest request, int idUser) throws ServiceException {
        ServiceBid serviceBid = new ServiceBid();
        List<Bid> listPerformedBidClient = serviceBid.findListBidsByIdAndStatusClient(idUser, STATUS_PERFORMED_BID);
        if (listPerformedBidClient.isEmpty()) {
            request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_PERFORMED_BID_CLIENT, "listBids.emptyPerformedBids");
        } else {
            request.setAttribute(REQUEST_ATTRIBUTE_PERFORMED_BIDS, listPerformedBidClient);
        }
        return configurationManager.getProperty("path.page.listBids");
    }

    /**
     * The method sets attributes for dispatcher
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws ServiceException
     */
    private String setDispatcherAttributes(HttpServletRequest request) throws ServiceException {
        ServiceBid serviceBid = new ServiceBid();
        String page = null;
        List<Bid> listPerformedBids = serviceBid.findListBidsByStatus(STATUS_PERFORMED_BID);
        if (listPerformedBids.isEmpty()) {
            request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST, "bids.emptyListPerformedBids");
            page = configurationManager.getProperty("path.page.bids");
        } else {
            request.setAttribute(REQUEST_ATTRIBUTE_PERFORMED_BIDS, listPerformedBids);
            page = configurationManager.getProperty("path.page.bids");
        }
        return page;
    }

}
