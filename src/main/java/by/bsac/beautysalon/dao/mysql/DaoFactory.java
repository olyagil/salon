package by.bsac.beautysalon.dao.mysql;

import by.bsac.beautysalon.dao.connection.ConnectionPool;
import by.bsac.beautysalon.dao.EmployeeDao;
import by.bsac.beautysalon.dao.ServiceDao;
import by.bsac.beautysalon.dao.TalonDao;
import by.bsac.beautysalon.dao.UserDao;
import by.bsac.beautysalon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    private static final Logger LOGGER = LogManager.getLogger();
    private Connection connection;

    public DaoFactory() {
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (DataBaseException e) {
            LOGGER.error("Can't get connection");
        }
    }

    public UserDao getUserDao() {
        return new UserDaoImpl(connection);
    }

    public EmployeeDao getEmployeeDao() {
        return new EmployeeDaoImpl(connection);
    }

    public ServiceDao getServiceDao() {
        return new ServiceDaoImpl(connection);
    }

    public TalonDao getTalonDao() {
        return new TalonDaoImpl(connection);
    }


    public void commit() throws DataBaseException {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("It is impossible to commit transaction", e);
            throw new DataBaseException(e);
        }
    }

    public void rollback() throws DataBaseException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("It is impossible to rollback transaction", e);
            throw new DataBaseException(e);
        }
    }

    public void close() {
        try {
            LOGGER.debug("Closing connection to DB");

            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Can't close connection to DB", e);
        }
    }
}
