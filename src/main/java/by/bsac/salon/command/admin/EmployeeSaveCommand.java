package by.bsac.salon.command.admin;

import by.bsac.salon.entity.enumeration.Gender;
import by.bsac.salon.entity.enumeration.Role;
import by.bsac.salon.entity.enumeration.Specialty;
import by.bsac.salon.service.UserService;
import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.entity.Employee;
import by.bsac.salon.entity.User;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.EmployeeService;
import by.bsac.salon.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * The class {@code EmployeeSaveCommand} is used for saving info about employee.
 */
public class EmployeeSaveCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SPECIALIST_ID = "specialistId";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SURNAME = "surname";
    private static final String NAME = "name";
    private static final String PATRONYMIC = "patronymic";
    private static final String GENDER = "gender";
    private static final String PHONE = "phone";
    private static final String IMG = "img";
    private static final String CABINET_NUMBER = "cabinet_number";
    private static final String SALARY = "salary";
    private static final String EMPLOYMENT_DATE = "employment_date";
    private static final String SPECIALTY = "specialty";
    private static final String BIRTH_DATE = "birth_date";
    private static final String ALERT_MESSAGE = "alert_message";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        EmployeeService service = serviceFactory.getEmployeeService();
        UserService userService = serviceFactory.getUserService();
        String id = request.getParameter(SPECIALIST_ID);
        String login = request.getParameter(LOGIN);
        Employee employee = new Employee();
        User user = null;
        try {
            if (id != null) {
                employee.setId(Integer.parseInt(id));
                user = userService.find(Integer.parseInt(id));
            }
            if (user == null) {
                LOGGER.debug(userService.find(login));

                if (UserValidator.checkLogin(login)
                        && userService.find(login).isEmpty()) {
                    LOGGER.debug("Adding new employee.");
                    employee.setLogin(login);
                    employee.setPassword(request.getParameter(PASSWORD));
                    employee.setRole(Role.EMPLOYEE);
                    employee.setSurname(request.getParameter(SURNAME));
                    employee.setName(request.getParameter(NAME));
                    employee.setPatronymic(request.getParameter(PATRONYMIC));
                    employee.setGender(Gender.valueOf(request
                            .getParameter(GENDER).toUpperCase()));
                    employee.setPhone(Integer.valueOf(request.getParameter(PHONE)));
                    employee.setBirthDate(Date.valueOf(request.getParameter(BIRTH_DATE)));
                    try {
                        employee.setAvatar(UserValidator.getAvatar(request.getPart(IMG),
                                employee.getGender()));
                    } catch (IOException | ServletException e) {
                        LOGGER.error("Can't read the image from file", e);
                        throw new DataBaseException(e);
                    }
                    employee.setId(userService.save(employee));
                    LOGGER.debug("Employee is saved.");

                } else {
                    request.getSession().setAttribute(ALERT_MESSAGE,
                            "Such user is exist or enter the correct date.");
                    return new Forward("/employee/add.html");
                }
            }
            LOGGER.debug("Employee id: " + employee.getId());
            employee.setCabinetNumber(Integer.parseInt(request.getParameter(CABINET_NUMBER)));
            employee.setSalary(Double.parseDouble(request.getParameter(SALARY)));
            employee.setEmploymentDate(Date.valueOf(request.getParameter(EMPLOYMENT_DATE)));
            employee.setSpecialty(Specialty.getById(Integer
                    .parseInt(request.getParameter(SPECIALTY))));


            service.save(employee);
            LOGGER.debug("Employee is changed");

        } catch (IllegalArgumentException e) {
            LOGGER.error("Can't parse the data of the user");
            request.setAttribute(ALERT_MESSAGE, "Please, enter the correct " +
                    "date.");
            if (id != null) {
                return new Forward("/employee/edit.html?specialistId=" + id);
            } else {
                request.getSession().setAttribute(ALERT_MESSAGE, "Please, " +
                        "enter the correct date.");
                return new Forward("/employee/add.html");

            }
        }
        return new Forward("/employee/list.html");

    }
}

