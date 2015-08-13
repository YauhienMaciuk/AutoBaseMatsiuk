package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class cancels deletion of the bid
 */
public class CancelDeletionBidCommand implements ActionCommand {

    public static final String SESSION_ATTRIBUTE_ID_CLIENT = "idUser";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_SPENT_BIDS_CLIENT = "emptyListSpentBidsClient";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_PERFORMED_BID_CLIENT = "emptyListPerformedBidClient";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST = "emptyListPerformedBids";
    public static final String REQUEST_ATTRIBUTE_PERFORMED_BIDS = "performedBids";
    public static final String REQUEST_PARAMETER_ACTION_CLIENT = "actionClient";
    public static final String CANCEL_BID = "cancelBid";
    public static final String REQUEST_ATTRIBUTE_NEW_BIDS = "newBids";
    public static final int STATUS_PERFORMED_BID = 1;
    public static final int STATUS_NEW_BID = 0;
    public static final int ID_DISPATCHER = 1;

    /**
     * The method execute.
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        int idUser = (int) request.getSession().getAttribute(SESSION_ATTRIBUTE_ID_CLIENT);
        try {
            if (idUser == ID_DISPATCHER) {
                page = setDispatcherAttributes(request);
            } else {
                setClientAttributes(request, idUser);
                page = configurationManager.getProperty("path.page.listBids");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }

    /**
     * The method sets attributes dispatcher
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws ServiceException
     */
    private String setDispatcherAttributes(HttpServletRequest request) throws ServiceException {
        String page = null;
        ServiceBid serviceBid = new ServiceBid();
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

    /**
     * The method sets attributes client
     *
     * @param request is the request
     * @param idUser  is the id of user
     * @throws ServiceException
     */
    private void setClientAttributes(HttpServletRequest request, int idUser) throws ServiceException {
        String actionClient = request.getParameter(REQUEST_PARAMETER_ACTION_CLIENT);
        ServiceBid serviceBid = new ServiceBid();
        if (actionClient.equals(CANCEL_BID)) {
            List<Bid> listSentBidsClient = serviceBid.findListBidsByIdAndStatusClient(idUser, STATUS_NEW_BID);
            if (listSentBidsClient.isEmpty()) {
                request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_SPENT_BIDS_CLIENT, "listBids.emptySpentBids");
            } else {
                request.setAttribute(REQUEST_ATTRIBUTE_NEW_BIDS, listSentBidsClient);
            }
        } else {
            List<Bid> listPerformedBidClient = serviceBid.findListBidsByIdAndStatusClient(idUser, STATUS_PERFORMED_BID);
            if (listPerformedBidClient.isEmpty()) {
                request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_PERFORMED_BID_CLIENT, "listBids.emptyPerformedBids");
            } else {
                request.setAttribute(REQUEST_ATTRIBUTE_PERFORMED_BIDS, listPerformedBidClient);
            }
        }
    }
}
