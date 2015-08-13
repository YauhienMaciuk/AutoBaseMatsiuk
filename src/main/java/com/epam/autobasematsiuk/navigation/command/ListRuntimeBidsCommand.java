package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class searches list of runtime bids
 */
public class ListRuntimeBidsCommand implements ActionCommand {

    public static final int STATUS_RUNTIME_BID = 2;
    public static final String REQUEST_ATTRIBUTE_RUNTIME_BIDS = "runtimeBids";
    public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";
    public static final String REQUEST_ATTRIBUTE_EMPTY_LIST_RUNTIME_BIDS_CLIENT = "emptyListRuntimeBidsClient";

    /**
     * The method execute
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int idClient = (int) request.getSession().getAttribute(SESSION_ATTRIBUTE_ID_USER);
        ServiceBid serviceBid = new ServiceBid();
        try {
            List<Bid> listRuntimeBidsClient = serviceBid.findListBidsByIdAndStatusClient(idClient, STATUS_RUNTIME_BID);
            if (listRuntimeBidsClient.isEmpty()) {
                request.setAttribute(REQUEST_ATTRIBUTE_EMPTY_LIST_RUNTIME_BIDS_CLIENT, "listBids.emptyListRuntime");
            }
            request.setAttribute(REQUEST_ATTRIBUTE_RUNTIME_BIDS, listRuntimeBidsClient);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.listBids");
    }
}
