package com.epam.autobasematsiuk.controller;


import com.epam.autobasematsiuk.database.pool.ConnectionPool;
import com.epam.autobasematsiuk.exception.CommandException;
import com.epam.autobasematsiuk.exception.DAOException;
import com.epam.autobasematsiuk.navigation.ActionFactory;
import com.epam.autobasematsiuk.navigation.command.ActionCommand;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The Class Controller. Receives requests, causes the needed action and
 * forwards to the result page.
 */
@WebServlet(name = "Controller", initParams = {@WebInitParam(name = "log4j", value = "/WEB-INF/log4j.properties")},
        urlPatterns = "/controller")

public class Controller extends HttpServlet {

    private static final long serialVersionUID = -2756969082549965198L;
    public static final Logger LOGGER = Logger.getLogger(Controller.class);

    /**
     * The method initializes log4j
     *
     * @param config is the configuration of the Servlet
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String log4j = config.getInitParameter("log4j");
        String path = getServletContext().getRealPath(log4j);
        PropertyConfigurator.configure(path);
    }

    /**
     * The method receives POST-request and calls the method processRequest
     *
     * @param request  is the request
     * @param response is the response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * The method receives GET-request and calls the method processRequest
     *
     * @param request  is the request
     * @param response is the response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * The method performs process over the request
     *
     * @param request  is the request
     * @param response is the response
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        try {
            ActionFactory actionFactory = new ActionFactory();
            ActionCommand command = actionFactory.defineCommand(request);
            page = command.execute(request);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException | CommandException e) {
            LOGGER.error("Exception in RequestDispatcher", e);
            try {
                response.sendError(500);
            } catch (IOException e1) {
                LOGGER.error("IOException in method response.sendError.");
            }
        }
    }

    /**
     * The method performs clean up the connectionPool when the servlet stop
     */
    @Override
    public void destroy() {
        super.destroy();
        try {
            ConnectionPool.getInstance().cleanUp();
        } catch (DAOException e) {
            LOGGER.error(e);
        }
    }
}
