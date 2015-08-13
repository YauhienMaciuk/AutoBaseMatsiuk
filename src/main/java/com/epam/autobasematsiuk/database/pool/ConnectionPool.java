package com.epam.autobasematsiuk.database.pool;

import com.epam.autobasematsiuk.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class ConnectionPool. Contains a initialize connections to the database.
 */
public class ConnectionPool {

    public static ResourceBundle dbConfig = ResourceBundle.getBundle("database");
    public static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private int poolSize = Integer.parseInt(dbConfig.getString("poolSize"));
    private long waitTime = Integer.parseInt(dbConfig.getString("waitTime"));
    public static ConnectionPool instance;
    public static AtomicBoolean isNull = new AtomicBoolean(true);
    public static AtomicBoolean receiveConnection = new AtomicBoolean(true);
    private BlockingQueue<Connection> pool;
    public static Lock lock = new ReentrantLock();

    /**
     * Causes the method init()
     *
     * @throws DAOException
     */
    private ConnectionPool() throws DAOException {
        init();
    }

    /**
     * The method registers jdbc driver and creates connection to the database.
     *
     * @throws DAOException
     */
    private void init() throws DAOException {
        pool = new ArrayBlockingQueue<>(poolSize);
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Properties properties = new Properties();
            properties.setProperty("user", dbConfig.getString("user"));
            properties.setProperty("password", dbConfig.getString("password"));
            properties.setProperty("unicode", dbConfig.getString("unicode"));
            properties.setProperty("encoding", dbConfig.getString("encoding"));
            for (int i = 0; i < poolSize; i++) {
                Connection connection = null;
                connection = DriverManager.getConnection(dbConfig.getString("url"), properties);
                pool.offer(connection);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception in method ConnectionPool.init", e);
        }
    }

    /**
     * Gets the single instance of the ConnectionPool.
     *
     * @return instance that is object of the ConnectionPool
     * @throws DAOException
     */
    public static ConnectionPool getInstance() throws DAOException {
        if (isNull.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isNull.set(false);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Gets the connection. If there is no free connections
     * user will be wait 10000 milliseconds.
     *
     * @return connection
     * @throws DAOException
     */
    public Connection getConnection() throws DAOException {
        Connection connection = null;
        if (receiveConnection.get()) {
            try {
                connection = pool.poll(waitTime, TimeUnit.MILLISECONDS);
                if (connection == null) {
                    throw new DAOException(
                            "No available connections in pool.");
                }
                return connection;
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted exception while getting connection", e);
            }
        }
        return connection;
    }

    /**
     * The method puts connection to the pool.
     *
     * @param connection is the connection
     */
    public void putConnection(Connection connection) {
        if (connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error("Can't put connection to database", e);
            }
        }
    }

    /**
     * The method closes all connections and cleans up pool.
     */
    public void cleanUp() {
        receiveConnection.set(false);
        if (instance != null) {
            try {
                Thread.sleep(100);
                Iterator<Connection> iterator = pool.iterator();
                while (iterator.hasNext()) {
                    iterator.next().close();
                }
                pool.clear();
            } catch (SQLException e) {
                LOGGER.error("Error while pool cleaning up.", e);
            } catch (InterruptedException e) {
                LOGGER.error("InterruptedException", e);
            }
        }
    }

}
