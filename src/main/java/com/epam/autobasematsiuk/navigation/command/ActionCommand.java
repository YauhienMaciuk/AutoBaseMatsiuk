package com.epam.autobasematsiuk.navigation.command;

import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.recource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The Interface ActionCommand. Base interface for all Commands.
 */
public interface ActionCommand {

    static ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    /**
     * The method execute.
     *
     * @param request is the request
     * @return the String. It's the path to the page.
     * @throws CommandException
     */
    String execute(HttpServletRequest request) throws CommandException;
}
