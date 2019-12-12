package by.bsac.beautysalon.dao.mysql;

import by.bsac.beautysalon.dao.ServiceDao;
import by.bsac.beautysalon.entity.Service;
import by.bsac.beautysalon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl extends BaseDaoImpl implements ServiceDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SELECT_ALL = "select `id`, `name`, "
            + "`description`, `price`, `duration` from `services`";

    private static final String DELETE_BY_ID = "delete from `services` where `id`=?";

    private static final String UPDATE_SERVICE = "update `services` set `name`=?,"
            + " `description`=?, `price`=?, `duration`=? where `id`=?";

    private static final String SELECT_BY_ID = "select `id`, `name`, " +
            "`description`, "
            + "`price`, `duration` from `services` where `id`=?";

    private static final String INSERT_SERVICE = "insert into `services`"
            + "(`name`, `description`, `price`, `duration`) values (?,?,?,?)";

    private static final String READ_SERVICE_BY_NAME = "select `id`, `name`, "
            + "`description`, `price`, `duration` " +
            "from `services` where `name` like ? order by name";

    private static final String COUNT_SERVICES = "select count(`id`) "
            + "from `services`";
    private static final String SELECT_ALL_BY_PARTS = "select `id`, `name`, "
            + "`description`, `price`,  `duration` from `services` " +
            "order by `id` limit ?,?";

    ServiceDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int countRows() throws DataBaseException {
        int count = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(COUNT_SERVICES);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't count the number of services", e);
            throw new DataBaseException(e);
        }
        return count;
    }

    @Override
    public List<Service> read(String name) throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(READ_SERVICE_BY_NAME)) {
            statement.setString(1, "%" + name + "%");
            List<Service> serviceList;
            try (ResultSet resultSet = statement.executeQuery()) {
                serviceList = new ArrayList<>();
                while (resultSet.next()) {
                    serviceList.add(getBuilder().build(resultSet));
                }
                return serviceList;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read the services from DB", e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<Service> read(int currentPage, int recordsPerPage) throws DataBaseException {
        int start = currentPage * recordsPerPage - recordsPerPage;

        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_ALL_BY_PARTS)) {
            statement.setInt(1, start);
            statement.setInt(2, recordsPerPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Service> serviceList = new ArrayList<>();
                while (resultSet.next()) {
                    serviceList.add(getBuilder().build(resultSet));
                }
                return serviceList;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read by page the services", e);
            throw new DataBaseException(e);
        }

    }

    @Override
    public List<Service> read() throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Service> serviceList = new ArrayList<>();
            Service service;
            while (resultSet.next()) {
                service = getBuilder().build(resultSet);
                serviceList.add(service);
            }
            return serviceList;
        } catch (SQLException e) {
            LOGGER.error("Can't read all services from DB", e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public Integer create(Service service) throws DataBaseException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SERVICE,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());
            statement.setDouble(3, service.getPrice());
            statement.setDouble(4, service.getDuration());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    LOGGER.error("There is no autoincrement index after trying " +
                            "to add record into `users` ");
                    throw new DataBaseException();
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Can't insert the service in DB.", e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public Service read(Integer id) throws DataBaseException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Service service = null;
                if (resultSet.next()) {
                    service = getBuilder().build(resultSet);
                }
                return service;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find the service with id: " + id, e);
            throw new DataBaseException(e);
        }

    }

    @Override
    public boolean update(Service service) throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(UPDATE_SERVICE)) {
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());
            statement.setDouble(3, service.getPrice());
            statement.setDouble(4, service.getDuration());
            statement.setInt(5, service.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Can't update service", e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DataBaseException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Can't delete service with id: " + id, e);
            throw new DataBaseException(e);
        }
    }
}
