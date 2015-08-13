package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class wants to perform an auto flight
 */
public class WantPerformAutoFlightCommand implements ActionCommand {

    public static final String FLAG_PROTECTION_F5_PERFORMED_AUTO_FLIGHT = "performedAutoFlight";
    public static final String FLAG_PROTECTION_F5_AUTO_FOR_REPAIR = "autoForRepair";

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
        if (session.getAttribute(FLAG_PROTECTION_F5_PERFORMED_AUTO_FLIGHT) != null) {
            session.removeAttribute(FLAG_PROTECTION_F5_PERFORMED_AUTO_FLIGHT);
        }
        if (session.getAttribute(FLAG_PROTECTION_F5_AUTO_FOR_REPAIR) != null) {
            session.removeAttribute(FLAG_PROTECTION_F5_AUTO_FOR_REPAIR);
        }
        return configurationManager.getProperty("path.page.performAutoFlight");
    }
}
