package com.epam.autobasematsiuk.navigation.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The class LangCommand selects locale for the session.
 */
public class LangCommand implements ActionCommand {

    public static final String REQUEST_PARAM_LANGUAGE = "language";
    public static final String SESSION_ATTRIBUTE_LANG = "lang";
    public static final String RUSSIAN = "ru";
    public static final String ENGLISH = "en";

    /**
     * The method execute.
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(REQUEST_PARAM_LANGUAGE);
        switch (language) {
            case RUSSIAN:
                request.getSession().setAttribute(SESSION_ATTRIBUTE_LANG, RUSSIAN);
                break;
            case ENGLISH:
                request.getSession().setAttribute(SESSION_ATTRIBUTE_LANG, ENGLISH);
                break;
        }
        return configurationManager.getProperty("path.page.login");
    }
}
