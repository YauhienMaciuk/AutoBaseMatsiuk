package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class wants to delete a bid
 */
public class WantDeleteBidCommand implements ActionCommand {

    public static final String ID_BID = "idBid";
    public static final String CANCEL_BID = "cancelBid";
    public static final String DELETE_PERFORMED_BID = "deletePerformedBid";
    public static final String DELETE_PERFORMED_AUTO_FLIGHT = "deletePerformedAutoFlight";
    public static final String REQUEST_PARAMETER_ACTION_CLIENT = "actionClient";
    public static final String CANCEL_AUTO_FLIGHT = "cancelAutoFlight";
    public static final String FLAG_PROTECTION_F5_CANCELED_AUTO_FLIGHT = "canceledAutoFlight";

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
        int idBid = Integer.parseInt(request.getParameter(ID_BID));
        String actionClient = request.getParameter(REQUEST_PARAMETER_ACTION_CLIENT);
        switch (actionClient) {
            case CANCEL_BID:
                request.setAttribute(CANCEL_BID, actionClient);
                break;
            case DELETE_PERFORMED_BID:
                request.setAttribute(DELETE_PERFORMED_BID, actionClient);
                break;
            case DELETE_PERFORMED_AUTO_FLIGHT:
                request.setAttribute(DELETE_PERFORMED_AUTO_FLIGHT, actionClient);
                break;
            case CANCEL_AUTO_FLIGHT:
                request.setAttribute(CANCEL_AUTO_FLIGHT, actionClient);
                if (session.getAttribute(FLAG_PROTECTION_F5_CANCELED_AUTO_FLIGHT) != null) {
                    session.removeAttribute(FLAG_PROTECTION_F5_CANCELED_AUTO_FLIGHT);
                }
                break;
            default:
                throw new CommandException("Exception in the method WantDeleteBid.execute()." +
                        " Default value in the switch.");
        }
        session.setAttribute(ID_BID, idBid);
        return configurationManager.getProperty("path.page.wantDeleteBid");
    }
}
