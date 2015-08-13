package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;

/**
 * The class BackToDriverBidCommand. Back to the page /jsp/driver/bidsDriver.jsp.
 */
public class BackToDriverBidCommand implements ActionCommand {

    public static final String REQUEST_ATTRIBUTE_BID_FOR_DRIVER = "bidForDriver";
    public static final String REQUEST_ATTRIBUTE_NOT_AUTO_FLIGHT = "notAutoFlight";
    public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";

    /**
     * The method execute.
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceBid serviceBid = new ServiceBid();
        int idDriver = (int) request.getSession().getAttribute(SESSION_ATTRIBUTE_ID_USER);
        try {
            Bid bidForDriver = serviceBid.findBidByIdDriver(idDriver);
            if (bidForDriver.getId() > 0) {
                request.setAttribute(REQUEST_ATTRIBUTE_BID_FOR_DRIVER, bidForDriver);
            } else {
                request.setAttribute(REQUEST_ATTRIBUTE_NOT_AUTO_FLIGHT, "bidsDriver.notAutoFlight");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.bidsDriver");
    }
}
