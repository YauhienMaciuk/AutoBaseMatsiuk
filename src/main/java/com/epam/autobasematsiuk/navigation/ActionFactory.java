package com.epam.autobasematsiuk.navigation;

import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.navigation.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * The class ActionFactory for creating Command objects.
 */
public class ActionFactory {

    /**
     * The method defines the required command
     *
     * @param request is the request
     * @return current command
     * @throws CommandException
     */
    public ActionCommand defineCommand(HttpServletRequest request) throws CommandException {
        ActionCommand currentCommand = null;
        String page = request.getParameter("page");
        Optional<String> optional = Optional.ofNullable(page);
        String result = optional.orElse("startPage");
        try {
            CommandEnum commandEnum = CommandEnum.valueOf(result.toUpperCase());
            currentCommand = commandEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            throw new CommandException("Command name parameter is wrong", e);
        }
        return currentCommand;
    }
}
