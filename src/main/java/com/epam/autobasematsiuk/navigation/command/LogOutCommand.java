package com.epam.autobasematsiuk.navigation.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The class LogOutCommand removes user from the session.
 */
public class LogOutCommand implements ActionCommand {

    /**
     * The method execute.
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return configurationManager.getProperty("path.page.index");
    }
}
