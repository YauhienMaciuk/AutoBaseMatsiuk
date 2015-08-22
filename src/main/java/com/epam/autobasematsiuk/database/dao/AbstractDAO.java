package com.epam.autobasematsiuk.database.dao;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * The class AbstractDAO. Base abstract class for concrete DAO objects.
 */
public class AbstractDAO {

    public static final Logger LOGGER = Logger.getLogger(AbstractDAO.class);

    /**
     * The method closes the statement
     *
     * @param statement is the statement
     */
    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOGGER.fatal("Exception in method AbstractDAO.close()", e);
        }
    }
}
