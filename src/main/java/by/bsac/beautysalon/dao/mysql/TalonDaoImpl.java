package by.bsac.beautysalon.dao.mysql;

import by.bsac.beautysalon.dao.TalonDao;
import by.bsac.beautysalon.entity.Talon;
import by.bsac.beautysalon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TalonDaoImpl extends BaseDaoImpl implements TalonDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SELECT_ALL = "select talons.`id`, "
            + "`client_id`, `employee_id`, client.`surname`, client.`name`,"
            + "client.`phone`, employee.`surname`, employee.`name`, "
            + "employee.`phone`, s.`id`, s.`name`,`reception_date`,"
            + " `status` from talons join services s on service_id = s.id "
            + "join user_info client on talons.client_id = client.user_id "
            + "join user_info employee on talons.employee_id = employee.user_id ";
    private static final String SELECT_ALL_BY_PARTS = SELECT_ALL
            + "order by `reception_date` DESC limit ?,?";
    private static final String SELECT_BY_EMPLOYEE = SELECT_ALL
            + "where employee_id = ? order by `reception_date` DESC ;";
    private static final String SELECT_BY_CLIENT = SELECT_ALL
            + "where client_id = ? order by `reception_date` DESC ;";
    private static final String SELECT_BY_ID = SELECT_ALL
            + "where talons.`id` = ? order by `reception_date` DESC ;";
    private static final String SELECT_BY_STATUS = SELECT_ALL
            + "where status = ? order by `reception_date` DESC ;";
    private static final String SELECT_BY_DATE = SELECT_ALL
            + "where reception_date between ? and ? order by `reception_date` DESC ;";
    private static final String INSERT_TALON = "insert into `talons`"
            + "(client_id, service_id, employee_id, reception_date, status) "
            + "values (?, ?, ?, ?, ?);";
    private static final String UPDATE_TALON = "update `talons` set service_id=?,"
            + " `reception_date`=?, `status`=? where id=?";
    private static final String DELETE_BY_ID = "delete from `talons` "
            + "where `id`=?;";
    private static final String COUNT_TALONS = "select count(`id`) "
            + "from `talons`";

   TalonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Talon> readByClient(Integer clientId) throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_BY_CLIENT)) {
            statement.setInt(1, clientId);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Talon> talonList = new ArrayList<>();
                while (resultSet.next()) {
                    Talon talon = getBuilder().build(resultSet);
                    talonList.add(talon);
                }
                return talonList;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read clients by client id: " + clientId, e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<Talon> readByEmployee(Integer specialistId) throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_BY_EMPLOYEE)) {
            statement.setInt(1, specialistId);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Talon> talonList = new ArrayList<>();
                while (resultSet.next()) {
                    talonList.add(getBuilder().build(resultSet));
                }
                return talonList;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read clients by employee id: " + specialistId, e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<Talon> read(Boolean status) throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_BY_STATUS)) {
            statement.setBoolean(1, status);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Talon> talonList = new ArrayList<>();
                while (resultSet.next()) {
                    talonList.add(getBuilder().build(resultSet));
                }
                return talonList;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read talons with status: " + status, e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<Talon> read(Date date) throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_BY_DATE)) {
            Timestamp data = new Timestamp(date.getTime());
            LOGGER.debug("DATE:" + data);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DAY_OF_WEEK, 1);
            statement.setTimestamp(1, data);
            statement.setTimestamp(2, new Timestamp(cal.getTime().getTime()));
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Talon> talonList = new ArrayList<>();
                while (resultSet.next()) {
                    talonList.add(getBuilder().build(resultSet));
                }
                LOGGER.debug("List of talons: " + talonList);
                return talonList;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read talons by date id: " + date, e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public int countRows() throws DataBaseException {
        int count = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(COUNT_TALONS);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't count the number of talons", e);
            throw new DataBaseException(e);
        }
        return count;
    }

    @Override
    public List<Talon> read(int currentPage, int recordsPerPage) throws DataBaseException {
        int start = currentPage * recordsPerPage - recordsPerPage;

        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_ALL_BY_PARTS)) {
            statement.setInt(1, start);
            statement.setInt(2, recordsPerPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Talon> talonList = new ArrayList<>();
                while (resultSet.next()) {
                    talonList.add(getBuilder().build(resultSet));
                }
                return talonList;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read by page the talons", e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<Talon> read() throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Talon> talonList = new ArrayList<>();
            while (resultSet.next()) {
                Talon talon = getBuilder().build(resultSet);
                talonList.add(talon);
            }
            return talonList;

        } catch (SQLException e) {
            LOGGER.error("Can't read all talons", e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public Integer create(Talon talon) throws DataBaseException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_TALON,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, talon.getClient().getId());
            statement.setInt(2, talon.getService().getId());
            statement.setInt(3, talon.getEmployee().getId());
            statement.setTimestamp(4, talon.getReceptionDate());
            statement.setBoolean(5, talon.isStatus());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    LOGGER.error("There is no autoincrement index after trying " +
                            "to add record into `talons` ");
                    throw new DataBaseException();
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Can't insert talon in DB ", e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public Talon read(Integer id) throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                Talon talon = null;
                while (resultSet.next()) {
                    talon = getBuilder().build(resultSet);
                }
                return talon;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read talon by id: " + id, e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public boolean update(Talon talon) throws DataBaseException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TALON)) {
            statement.setInt(1, talon.getService().getId());
            statement.setTimestamp(2, talon.getReceptionDate());
            statement.setBoolean(3, talon.isStatus());
            statement.setInt(4, talon.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Can't make the update of the talon to DB ", e);
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
            LOGGER.error("Can't delete the talon from DB ", e);
            throw new DataBaseException(e);
        }
    }
}
