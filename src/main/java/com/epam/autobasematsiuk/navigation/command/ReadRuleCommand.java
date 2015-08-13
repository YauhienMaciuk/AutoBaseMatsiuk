package com.epam.autobasematsiuk.navigation.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The class designed for read rule of creation of a new bid
 */
public class ReadRuleCommand implements ActionCommand {

    /**
     * The method execute
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        return configurationManager.getProperty("path.page.rule");
    }
}
