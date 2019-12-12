package by.bsac.beautysalon.builder;

import by.bsac.beautysalon.entity.Employee;
import by.bsac.beautysalon.entity.enumeration.Gender;
import by.bsac.beautysalon.entity.enumeration.Role;
import by.bsac.beautysalon.entity.enumeration.Specialty;
import by.bsac.beautysalon.utill.ImageUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeBuilder implements Builder<Employee> {

    private static final String USER_ID = "user_id";
    private static final String CABINET_NUMBER = "cabinet_number";
    private static final String SALARY = "salary";
    private static final String EMPLOYMENT_DATE = "employment_date";
    private static final String SPECIALTY = "specialty";
    private static final String LOGIN = "login";
    private static final String ROLE = "role";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PATRONYMIC = "patronymic";
    private static final String GENDER = "gender";
    private static final String PHONE = "phone";
    private static final String BIRTH_DATE = "birth_date";
    private static final String AVATAR = "avatar";

    @Override
    public Employee build(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt(USER_ID));
        employee.setCabinetNumber(resultSet.getInt(CABINET_NUMBER));
        employee.setSalary(resultSet.getDouble(SALARY));
        employee.setEmploymentDate(resultSet.getDate(EMPLOYMENT_DATE));
        employee.setSpecialty(Specialty.getById(resultSet.getInt(SPECIALTY)));
        employee.setLogin(resultSet.getString(LOGIN));
        employee.setRole(Role.getById(resultSet.getInt(ROLE)));
        employee.setName(resultSet.getString(NAME));
        employee.setSurname(resultSet.getString(SURNAME));
        employee.setPatronymic(resultSet.getString(PATRONYMIC));
        employee.setGender(Gender.getById(resultSet.getInt(GENDER)));
        employee.setPhone(resultSet.getInt(PHONE));
        employee.setBirthDate(resultSet.getDate(BIRTH_DATE));
        employee.setAvatar(ImageUtil.encoder(resultSet.getBlob(AVATAR)));

        return employee;
    }
}
