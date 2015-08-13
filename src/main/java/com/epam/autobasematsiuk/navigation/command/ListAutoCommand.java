package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Auto;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceAutoFlight;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class searches lists of autos  by condition
 */
public class ListAutoCommand implements ActionCommand {

    public static final String REQUEST_ATTRIBUTE_LIST_AUTO = "listAuto";
    public static final String REQUEST_ATTRIBUTE_LIST_AUTO_FOR_REPAIR = "listAutoForRepair";
    public static final boolean AUTO_CONDITION_REPAIR = true;
    public static final boolean AUTO_CONDITION_GOOD = false;

    /**
     * The method execute.
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceAutoFlight serviceAutoFlight = new ServiceAutoFlight();
        try {
            List<Auto> listAuto = serviceAutoFlight.findListAutoByCondition(AUTO_CONDITION_GOOD);
            request.setAttribute(REQUEST_ATTRIBUTE_LIST_AUTO, listAuto);
            List<Auto> listAutoForRepair = serviceAutoFlight.findListAutoByCondition(AUTO_CONDITION_REPAIR);
            request.setAttribute(REQUEST_ATTRIBUTE_LIST_AUTO_FOR_REPAIR, listAutoForRepair);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.listAuto");
    }
}
