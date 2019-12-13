package by.bsac.salon.dao.mysql;

import by.bsac.salon.dao.EmployeeDao;
import by.bsac.salon.entity.Employee;
import by.bsac.salon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl extends BaseDaoImpl implements EmployeeDao {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String UPDATE_EMPLOYEE_BY_ID = "update employees "
            + "set cabinet_number=?, salary=?, employment_date=?, "
            + "specialty=? where user_info_id=?";
    private static final String INSERT_EMPLOYEE = "insert into employees "
            + "(user_info_id, cabinet_number, salary, employment_date, "
            + "specialty) values (?,?,?,?,?)";
    private static final String SELECT_ALL = "select user_id, login,"
            + "role, name,  surname,  patronymic, gender, "
            + "phone, birth_date, cabinet_number, salary, "
            + "employment_date, specialty "
            + "from employees "
            + "join user_info ui on employees.user_info_id = ui.user_id "
            + "join users users on employees.user_info_id = users.id "
            + "order by users.id";
    private static final String SELECT_ALL_BY_PARTS = "select user_id, login,"
            + "role, name,  surname,  patronymic, gender, "
            + "phone, birth_date, cabinet_number, salary, "
            + "employment_date, specialty "
            + "from employees "
            + "join user_info ui on employees.user_info_id = ui.user_id "
            + "join users users on employees.user_info_id = users.id "
            + "order by users.id limit ?,?";
    private static final String SELECT_BY_LOGIN = "select user_id, login, "
            + "role, name,  surname,  patronymic, gender, phone, "
            + "birth_date, cabinet_number, salary, "
            + "employment_date, specialty "
            + "from employees "
            + "join user_info ui on employees.user_info_id = ui.user_id\n"
            + "join users users on employees.user_info_id = users.id "
            + "where login=?";

    private static final String SELECT_BY_ID = "select user_id, login, "
            + "role, name,  surname,  patronymic, gender, phone, "
            + "birth_date, cabinet_number, salary, "
            + "employment_date, specialty "
            + "from employees "
            + "join user_info ui on employees.user_info_id = ui.user_id "
            + "join users users on employees.user_info_id = users.id "
            + "where id=?";
    private static final String COUNT_EMPLOYEES = "select count" +
            "(user_info_id) from employees";

    EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int countRows() throws DataBaseException {
        int count = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement(COUNT_EMPLOYEES);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't count the number of employees", e);
            throw new DataBaseException(e);
        }
        return count;
    }

    @Override
    public List<Employee> read() throws DataBaseException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Employee> employeeList = new ArrayList<>();
            while (resultSet.next()) {
                employeeList.add(getBuilder().build(resultSet));
            }
            return employeeList;

        } catch (SQLException e) {
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<Employee> read(int currentPage, int recordsPerPage) throws DataBaseException {
        int start = currentPage * recordsPerPage - recordsPerPage;

        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_ALL_BY_PARTS)) {
            statement.setInt(1, start);
            statement.setInt(2, recordsPerPage);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Employee> employeeList = new ArrayList<>();
                while (resultSet.next()) {
                    employeeList.add(getBuilder().build(resultSet));
                }
                return employeeList;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read by page the employees", e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public List<Employee> read(String login) throws DataBaseException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Employee> employeeList = new ArrayList<>();
                while (resultSet.next()) {
                    employeeList.add(getBuilder().build(resultSet));
                }
                return employeeList;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read the employee by the login: " + login, e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public Integer create(Employee employee) throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_EMPLOYEE)) {

            statement.setInt(1, employee.getId());
            statement.setInt(2, employee.getCabinetNumber());
            statement.setDouble(3, employee.getSalary());
            statement.setDate(4, employee.getEmploymentDate());
            statement.setInt(5, employee.getSpecialty().getId());
            statement.executeUpdate();
            return employee.getId();

        } catch (SQLException e) {
            LOGGER.error("Can't insert the employee into the DB", e);
            throw new DataBaseException(e);
        }

    }

    @Override
    public Employee read(Integer id) throws DataBaseException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Employee employee = null;
                if (resultSet.next()) {
                    employee = getBuilder().build(resultSet);
                }
                return employee;
            }
        } catch (SQLException e) {
            LOGGER.error("Can't read the employee from DB with id: " + id, e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public boolean update(Employee employee) throws DataBaseException {
        try (PreparedStatement statement =
                     connection.prepareStatement(UPDATE_EMPLOYEE_BY_ID)) {
            statement.setInt(1, employee.getCabinetNumber());
            statement.setDouble(2, employee.getSalary());
            statement.setDate(3, employee.getEmploymentDate());
            statement.setInt(4, employee.getSpecialty().getId());
            statement.setInt(5, employee.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Can't update the info about employee with id: "
                    + employee.getId(), e);
            throw new DataBaseException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
