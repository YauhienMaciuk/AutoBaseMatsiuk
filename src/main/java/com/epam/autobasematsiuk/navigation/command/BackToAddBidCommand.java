package com.epam.autobasematsiuk.navigation.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The class BackToAddBidCommand back to /jsp/client/addBid.jsp
 */
public class BackToAddBidCommand implements ActionCommand {

    /**
     * The method execute
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        return configurationManager.getProperty("path.page.addBid");
    }
}
