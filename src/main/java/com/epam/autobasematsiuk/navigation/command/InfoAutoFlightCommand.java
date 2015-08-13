package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;

/**
 * The class provide information about the auto flight
 */
public class InfoAutoFlightCommand implements ActionCommand {

    public static final String REQUEST_ATTRIBUTE_ID_DRIVER = "idDriver";
    public static final String REQUEST_ATTRIBUTE_BID = "bid";

    /**
     * The method execute
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int idDriver = Integer.parseInt(request.getParameter(REQUEST_ATTRIBUTE_ID_DRIVER));
        ServiceBid serviceBid = new ServiceBid();
        try {
            Bid bid = serviceBid.findBidByIdDriver(idDriver);
            request.setAttribute(REQUEST_ATTRIBUTE_BID, bid);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.infoAboutAutoFlight");
    }
}
