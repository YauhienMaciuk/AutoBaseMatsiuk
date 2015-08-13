package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.entity.City;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.ServiceException;
import com.epam.autobasematsiuk.service.ServiceBid;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The class back to the page /jsp/client/addBid.jsp
 */
public class MoveToAddBidCommand implements ActionCommand {

    public static final String SESSION_ATTRIBUTE_LIST_CITIES = "listCities";

    /**
     * The method execute
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceBid serviceBid = new ServiceBid();
        try {
            List<City> listCities = serviceBid.receiveListCities();
            request.getSession().setAttribute(SESSION_ATTRIBUTE_LIST_CITIES, listCities);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return configurationManager.getProperty("path.page.addBid");
    }
}
