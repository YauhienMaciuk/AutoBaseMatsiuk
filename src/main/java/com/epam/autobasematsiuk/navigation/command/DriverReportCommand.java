package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Bid;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceAutoFlight;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class changes the condition of the auto.
 */
public class DriverReportCommand implements ActionCommand {

    public static final String REQUEST_PARAM_CONDITION = "condition";
    public static final String SESSION_ATTRIBUTE_ID_AUTO = "idAuto";
    public static final String SESSION_ATTRIBUTE_ID_USER = "idUser";
    public static final String REQUEST_ATTRIBUTE_CHANGED_CONDITION_AUTO = "changedConditionAuto";
    public static final String REQUEST_ATTRIBUTE_NOT_AUTO_FLIGHT = "notAutoFlight";
    public static final String SESSION_ATTRIBUTE_BID_FOR_DRIVER = "bidForDriver";
    public static final String FLAG_PROTECTION_F5_AUTO_FOR_REPAIR = "autoForRepair";
    public static final boolean AUTO_CONDITION_REPAIR = true;

    /**
     * The method execute.
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String condition = request.getParameter(REQUEST_PARAM_CONDITION);
        ServiceAutoFlight serviceAutoFlight = new ServiceAutoFlight();
        ServiceBid serviceBid = new ServiceBid();
        HttpSession session = request.getSession();
        int idDriver = (int) session.getAttribute(SESSION_ATTRIBUTE_ID_USER);
        String page = configurationManager.getProperty("path.page.bidsDriver");
        try {
            if (session.getAttribute(FLAG_PROTECTION_F5_AUTO_FOR_REPAIR) != null) {
                Bid bidForDriver = serviceBid.findBidByIdDriver(idDriver);
                if (bidForDriver.getId() > 0) {
                    session.setAttribute(SESSION_ATTRIBUTE_BID_FOR_DRIVER, bidForDriver);
                }
                request.setAttribute(REQUEST_ATTRIBUTE_NOT_AUTO_FLIGHT, "bidsDriver.notAutoFlight");
                return page;
            }
            if (condition.equals("repair")) {
                int idAuto = (int) session.getAttribute(SESSION_ATTRIBUTE_ID_AUTO);
                serviceAutoFlight.changeWorkingConditionAuto(AUTO_CONDITION_REPAIR, idAuto);
                request.setAttribute(REQUEST_ATTRIBUTE_CHANGED_CONDITION_AUTO, "report.changed");
                session.setAttribute(FLAG_PROTECTION_F5_AUTO_FOR_REPAIR, "autoForRepair");
            }
            Bid bidForDriver = serviceBid.findBidByIdDriver(idDriver);
            if (bidForDriver.getId() > 0) {
                session.setAttribute(SESSION_ATTRIBUTE_BID_FOR_DRIVER, bidForDriver);
                return page;
            }
            session.removeAttribute(SESSION_ATTRIBUTE_ID_AUTO);
            session.removeAttribute(SESSION_ATTRIBUTE_BID_FOR_DRIVER);
            request.setAttribute(REQUEST_ATTRIBUTE_NOT_AUTO_FLIGHT, "bidsDriver.notAutoFlight");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
