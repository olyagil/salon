package by.bsac.salon.command.admin;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.entity.Employee;
import by.bsac.salon.entity.enumeration.Specialty;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class {@code EmployeeEditCommand} is used for editing employee.
 */
public class EmployeeEditCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SPECIALIST_ID = "specialistId";
    private static final String USER = "user";
    private static final String SPECIALTIES = "specialties";
    private static final String ALERT_MESSAGE = "alert_message";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        EmployeeService service = serviceFactory.getEmployeeService();

        try {

            String id = request.getParameter(SPECIALIST_ID);
            if (id != null) {
                Employee employee = service.find(Integer.parseInt(id));
                if (employee != null) {
                    request.setAttribute(USER, service.find(employee.getId()));
                    request.setAttribute(SPECIALTIES, Specialty.values());
                } else {
                    LOGGER.debug(String.format("The user with id %s is not found"
                            , id));
                    request.setAttribute(ALERT_MESSAGE, "Such user doesn't " +
                            "exist!");
                }
            } else {
                LOGGER.debug("Can't determine the id of user.");
                return new Forward("/employee/list.html");
            }
        } catch (NumberFormatException e) {
            LOGGER.error("Can't parse the id of the user", e);
            request.setAttribute(ALERT_MESSAGE, "Please, enter correct data.");
        }

        return null;

    }
}
