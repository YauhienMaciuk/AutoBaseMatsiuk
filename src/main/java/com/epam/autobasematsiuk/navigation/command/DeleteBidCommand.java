package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class deletes a bid.
 */
public class DeleteBidCommand implements ActionCommand {

    public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";
    public static final String REQUEST_ATTRIBUTE_PERFORMED_BIDS = "performedBids";
    public static final int STATUS_PERFORMED_BID = 1;
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST = "emptyListPerformedBids";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_SPENT_BIDS_CLIENT = "emptyListSpentBidsClient";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_PERFORMED_BID_CLIENT = "emptyListPerformedBidsClient";
    public static final String SESSION_ATTRIBUTE_ID_BID = "idBid";
    public static final String REQUEST_ATTRIBUTE_CAN_NOT_DELETE = "canNotDelete";
    public static final String REQUEST_PARAMETER_ACTION_CLIENT = "actionClient";
    public static final String REQUEST_ATTRIBUTE_NEW_BIDS = "newBids";
    public static final String CANCEL_BID = "cancelBid";
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
        HttpSession session = request.getSession();
        int idBid = (int) session.getAttribute(SESSION_ATTRIBUTE_ID_BID);
        ServiceBid serviceBid = new ServiceBid();
        String page = null;
        try {
            boolean flag = serviceBid.deleteBid(idBid);
            if (!flag) {
                request.setAttribute(REQUEST_ATTRIBUTE_CAN_NOT_DELETE, "addBid.canNotDelete");
            }
            int idUser = (int) session.getAttribute(SESSION_ATTRIBUTE_ID_USER);
            if (idUser == ID_DISPATCHER) {
                page = setDispatcherAttribute(request);
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
     * @param request  is the request
     * @param idClient is the id of client
     * @return the String. It's the path to the page.
     * @throws ServiceException
     */
    private String setClientAttributes(HttpServletRequest request, int idClient) throws ServiceException {
        String actionClient = request.getParameter(REQUEST_PARAMETER_ACTION_CLIENT);
        ServiceBid serviceBid = new ServiceBid();
        if (actionClient.equals(CANCEL_BID)) {
            List<Bid> listSentBidsClient = serviceBid.findListBidsByIdAndStatusClient(idClient, STATUS_NEW_BID);
            if (listSentBidsClient.isEmpty()) {
                request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_SPENT_BIDS_CLIENT, "listBids.emptySpentBids");
            } else {
                request.setAttribute(REQUEST_ATTRIBUTE_NEW_BIDS, listSentBidsClient);
            }
        } else {
            List<Bid> listPerformedBidClient = serviceBid.findListBidsByIdAndStatusClient(idClient, STATUS_PERFORMED_BID);
            if (listPerformedBidClient.isEmpty()) {
                request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_PERFORMED_BID_CLIENT, "listBids.emptyPerformedBids");
            } else {
                request.setAttribute(REQUEST_ATTRIBUTE_PERFORMED_BIDS, listPerformedBidClient);
            }
        }
        return configurationManager.getProperty("path.page.listBids");
    }

    /**
     * The method sets attribute for dispatcher.
     *
     * @param request is the request
     * @return is the String. It's the path to the page.
     * @throws ServiceException
     */
    private String setDispatcherAttribute(HttpServletRequest request) throws ServiceException {
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
}
