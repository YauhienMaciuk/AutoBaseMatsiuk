package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.Auto;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceAutoFlight;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class RepairAutoCommand. Depending on the parameter
 * sends auto to the repair or takes auto from repair.
 */
public class RepairAutoCommand implements ActionCommand {

    public static final String REQUEST_PARAMETER_ID_AUTO = "idAuto";
    public static final String REQUEST_ATTRIBUTE_LIST_AUTO = "listAuto";
    public static final String REQUEST_ATTRIBUTE_LIST_AUTO_FOR_REPAIR = "listAutoForRepair";
    public static final String REQUEST_PARAMETER_CONDITION = "condition";
    public static final boolean AUTO_CONDITION_GOOD = false;
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
        int idAuto = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_ID_AUTO));
        String conditionAuto = request.getParameter(REQUEST_PARAMETER_CONDITION);
        try {
            ServiceAutoFlight serviceAutoFlight = new ServiceAutoFlight();
            if (conditionAuto.equals("toRepair")) {
                serviceAutoFlight.changeWorkingConditionAuto(AUTO_CONDITION_REPAIR, idAuto);
            } else {
                serviceAutoFlight.changeWorkingConditionAuto(AUTO_CONDITION_GOOD, idAuto);
            }
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
