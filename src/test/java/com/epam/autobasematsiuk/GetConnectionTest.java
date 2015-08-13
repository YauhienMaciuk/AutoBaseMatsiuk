package com.epam.autobasematsiuk;

import com.epam.autobasematsiuk.database.pool.ConnectionPool;
import com.epam.autobasematsiuk.exception.DAOException;
import org.junit.Test;

public class GetConnectionTest {

    private static ConnectionPool connectionPool;

    @Test
    public void ConnectionPoolCreationTest() throws DAOException {
            connectionPool = ConnectionPool.getInstance();
            connectionPool.getConnection();
    }
}