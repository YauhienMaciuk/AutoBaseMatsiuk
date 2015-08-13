package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceAutoFlight;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class performs the auto flight.
 */
public class PerformAutoFlightCommand implements ActionCommand {

    public static final String REQUEST_ATTRIBUTE_IMPOSSIBLE_PERFORM_AUTO_FLIGHT = "impossiblePerformAutoFlight";
    public static final String SESSION_ATTRIBUTE_ID_DRIVER = "idUser";
    public static final String REQUEST_PARAMETER_ID_AUTO_FLIGHT = "idBid";
    public static final String SESSION_ATTRIBUTE_ID_AUTO = "idAuto";
    public static final int STATUS_PERFORMED_BID = 1;
    public static final String FLAG_PROTECTION_F5_PERFORMED_AUTO_FLIGHT = "performedAutoFlight";
    public static final boolean AUTO_STATUS_FREE = false;

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
        if (session.getAttribute(FLAG_PROTECTION_F5_PERFORMED_AUTO_FLIGHT) != null) {
            return configurationManager.getProperty("path.page.report");
        }
        int idAutoFlight = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_ID_AUTO_FLIGHT));
        ServiceAutoFlight serviceAutoFlight = new ServiceAutoFlight();
        ServiceBid serviceBid = new ServiceBid();
        try {
            boolean performAutoFlight = serviceAutoFlight.deleteAutoFlight(idAutoFlight);
            if (!performAutoFlight) {
                request.setAttribute(REQUEST_ATTRIBUTE_IMPOSSIBLE_PERFORM_AUTO_FLIGHT, "bidsDriver.impossible");
                return configurationManager.getProperty("path.page.bidsDriver");
            }
            int idDriver = (int) session.getAttribute(SESSION_ATTRIBUTE_ID_DRIVER);
            int idAuto = (int) session.getAttribute(SESSION_ATTRIBUTE_ID_AUTO);
            serviceBid.changeStatusBid(STATUS_PERFORMED_BID, idAutoFlight);
            serviceAutoFlight.changeStatusAuto(idAuto, AUTO_STATUS_FREE);
            serviceAutoFlight.changeStatusDriver(idDriver, AUTO_STATUS_FREE);
            session.setAttribute(FLAG_PROTECTION_F5_PERFORMED_AUTO_FLIGHT, "performedAutoFlight");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.report");
    }
}
