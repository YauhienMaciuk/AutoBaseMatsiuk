package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class searches list of the new bids.
 */
public class ListNewBidsCommand implements ActionCommand {

    public static final String REQUEST_ATTRIBUTE_NEW_BIDS = "newBids";
    public static final int STATUS_NEW_BID = 0;
    public static final int ID_DISPATCHER = 1;
    public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_SPENT_BIDS_CLIENT = "emptyListSpentBidsClient";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_NEW_BIDS = "emptyListNewBids";

    /**
     * The method execute.
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int idUser = (int) request.getSession().getAttribute(SESSION_ATTRIBUTE_ID_USER);
        try {
            if (idUser == ID_DISPATCHER) {
                setDispatcherAttributes(request);
                return configurationManager.getProperty("path.page.bids");
            } else {
                setClientAttributes(request, idUser);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.listBids");
    }

    /**
     * The method sets attributes for client
     *
     * @param request is the request
     * @param idUser  is the id of user
     * @throws ServiceException
     */
    private void setClientAttributes(HttpServletRequest request, int idUser) throws ServiceException {
        ServiceBid serviceBid = new ServiceBid();
        List<Bid> listSentBidsClient = serviceBid.findListBidsByIdAndStatusClient(idUser, STATUS_NEW_BID);
        if (listSentBidsClient.isEmpty()) {
            request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_SPENT_BIDS_CLIENT, "listBids.emptySpentBids");
        } else {
            request.setAttribute(REQUEST_ATTRIBUTE_NEW_BIDS, listSentBidsClient);
        }
    }

    /**
     * The method sets attributes for dispatcher
     *
     * @param request is the request
     * @throws ServiceException
     */
    private void setDispatcherAttributes(HttpServletRequest request) throws ServiceException {
        ServiceBid serviceBid = new ServiceBid();
        List<Bid> listNewBids = serviceBid.findListBidsByStatus(STATUS_NEW_BID);
        if (listNewBids.isEmpty()) {
            request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_NEW_BIDS, "bids.emptyListNewBids");
        }
        request.setAttribute(REQUEST_ATTRIBUTE_NEW_BIDS, listNewBids);
    }
}
