package by.bsac.beautysalon.dao.connection;

import by.bsac.beautysalon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String DRIVER = "jdbc.driver";
    private static final String URL = "jdbc.url";
    private static final String USER = "jdbc.user";
    private static final String PASSWORD = "jdbc.password";
    private static final String POOL_SIZE_MAX = "jdbc.pool.size.max";
    private static final String POOL_CONNECTION_TIMEOUT = "jdbc.pool.connection.timeout";
    private static final String POOL_SIZE_START = "jdbc.pool.size.start";
    private String url;
    private String user;
    private String password;
    private int maxSize;
    private int checkConnectionTimeout;

    private final BlockingQueue<PooledConnection> connectionPool
            = new LinkedBlockingQueue<>();
    private final Set<PooledConnection> usedConnections
            = new ConcurrentSkipListSet<>();
    private final Lock getLock = new ReentrantLock();
    private final Lock releaseLock = new ReentrantLock();

    private static final  ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {

    }


    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() throws DataBaseException {
        LOGGER.debug("Getting connection from pool.");
        PooledConnection connection = null;
        while (connection == null) {
            try {
                getLock.lock();
                if (!connectionPool.isEmpty()) {
                    connection = connectionPool.take();
                    if (!connection.isValid(checkConnectionTimeout)) {
                        try {
                            connection.getConnection().close();
                        } catch (SQLException e) {
                            LOGGER.error("Can't close connection", e);
                        }
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = createConnection();
                } else {
                    LOGGER.error("Maximum pool size reached, " +
                            "no available connections!");
                    throw new DataBaseException();
                }
            } catch (InterruptedException | SQLException e) {
                LOGGER.error("It is impossible to connect to a database", e);
                throw new DataBaseException(e);
            } finally {
                getLock.unlock();
            }
        }
        usedConnections.add(connection);
        LOGGER.debug(String.format("Connection was received from pool. " +
                        "Current pool size: %d used connections; %d free connection",
                usedConnections.size(), connectionPool.size()));
        return connection;
    }


    void releaseConnection(PooledConnection connection) {
        try {
            releaseLock.lock();
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                usedConnections.remove(connection);
                connectionPool.put(connection);
                LOGGER.debug(String.format("Connection was released into pool. "
                                + "Current pool size: %d used connections;"
                                + " %d free connection", usedConnections.size(),
                        connectionPool.size()));
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Can't return database connection into pool", e);
            try {
                connection.getConnection().close();
            } catch (SQLException e2) {
                LOGGER.error("Can't close connection", e2);
            }
        } finally {
            releaseLock.unlock();
        }
    }

    public void init() throws DataBaseException {
        try {
            DBConfigurationManager confManager =
                    DBConfigurationManager.getInstance();
            destroy();
            Class.forName(confManager.getValue(DRIVER));
            this.url = confManager.getValue(URL);
            this.user = confManager.getValue(USER);
            this.password = confManager.getValue(PASSWORD);
            this.maxSize = Integer.parseInt(confManager.getValue(POOL_SIZE_MAX));
            this.checkConnectionTimeout = Integer.parseInt(confManager
                    .getValue(POOL_CONNECTION_TIMEOUT));
            int startSize = Integer.parseInt(confManager.getValue(POOL_SIZE_START));

            for (int counter = 0; counter < startSize; counter++) {
                connectionPool.put(createConnection());
            }
        } catch (ClassNotFoundException | InterruptedException | SQLException e) {
            LOGGER.fatal("It is impossible to initialize connection", e);
            throw new DataBaseException(e);
        }
    }

    private void destroy() {
        usedConnections.addAll(connectionPool);
        connectionPool.clear();
        for (PooledConnection connection : usedConnections) {
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                LOGGER.error("Can't close the connection", e);
            }
        }
        usedConnections.clear();
    }

    private PooledConnection createConnection() throws SQLException {
        LOGGER.debug("Creating connection");
        return new PooledConnection(DriverManager.getConnection(url, user,
                password));
    }


}
