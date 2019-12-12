package by.bsac.beautysalon.builder;

import by.bsac.beautysalon.entity.Employee;
import by.bsac.beautysalon.entity.Service;
import by.bsac.beautysalon.entity.Talon;
import by.bsac.beautysalon.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TalonBuilder implements Builder<Talon> {

    private static final String TALONS_ID = "talons.id";
    private static final String RECEPTION_DATE = "reception_date";
    private static final String STATUS = "status";
    private static final String EMPLOYEE_ID = "employee_id";
    private static final String CLIENT_ID = "client_id";
    private static final String SERVICE_ID = "s.id";
    private static final String SERVICE_NAME = "s.name";
    private static final String EMPLOYEE_NAME = "employee.name";
    private static final String EMPLOYEE_SURNAME = "employee.surname";
    private static final String EMPLOYEE_PHONE = "employee.phone";
    private static final String CLIENT_NAME = "client.name";
    private static final String CLIENT_SURNAME = "client.surname";
    private static final String CLIENT_PHONE = "client.phone";

    @Override
    public Talon build(ResultSet resultSet) throws SQLException {
        Talon talon = new Talon();
        User client = new User();
        Service service = new Service();
        Employee employee = new Employee();
        talon.setClient(client);
        talon.setService(service);
        talon.setEmployee(employee);

        talon.setId(resultSet.getInt(TALONS_ID));
        talon.setReceptionDate(resultSet.getTimestamp(RECEPTION_DATE));
        talon.setStatus(resultSet.getBoolean(STATUS));
        employee.setId(resultSet.getInt(EMPLOYEE_ID));
        client.setId(resultSet.getInt(CLIENT_ID));

        service.setId(resultSet.getInt(SERVICE_ID));
        service.setName(resultSet.getString(SERVICE_NAME));
        employee.setName(resultSet.getString(EMPLOYEE_NAME));
        employee.setSurname(resultSet.getString(EMPLOYEE_SURNAME));
        employee.setPhone(resultSet.getInt(EMPLOYEE_PHONE));

        client.setName(resultSet.getString(CLIENT_NAME));
        client.setSurname(resultSet.getString(CLIENT_SURNAME));
        client.setPhone(resultSet.getInt(CLIENT_PHONE));

        return talon;
    }
}
