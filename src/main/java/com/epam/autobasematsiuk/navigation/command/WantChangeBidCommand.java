package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class wants to change a bid
 */
public class WantChangeBidCommand implements ActionCommand {

    public static final String REQUEST_PARAMETER_ID_BID = "idBid";
    public static final String REQUEST_ATTRIBUTE_IMPOSSIBLE_CHANGE = "impossibleChange";
    public static final String REQUEST_ATTRIBUTE_BID = "bid";
    public static final String FLAG_PROTECTION_F5_CANCELED_BID = "canceledBid";

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
        if (session.getAttribute(FLAG_PROTECTION_F5_CANCELED_BID) != null) {
            session.removeAttribute(FLAG_PROTECTION_F5_CANCELED_BID);
        }
        int idBid = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_ID_BID));
        ServiceBid serviceBid = new ServiceBid();
        try {
            Bid bid = serviceBid.findBidById(idBid);
            if (bid.getId() == 0) {
                request.setAttribute(REQUEST_ATTRIBUTE_IMPOSSIBLE_CHANGE, "change.impossible");
                return configurationManager.getProperty("path.page.addBid");
            }
            request.setAttribute(REQUEST_ATTRIBUTE_BID, bid);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.changeBid");
    }
}
