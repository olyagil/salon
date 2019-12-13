package by.bsac.salon.command.admin;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.entity.Employee;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.EmployeeService;
import by.bsac.salon.utill.PaginationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class {@code EmployeeListCommand} is used for viewing all employees.
 */
public class EmployeeListCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SEARCH_LOGIN = "searchLogin";
    private static final String EMPLOYEES = "employees";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        EmployeeService service = serviceFactory.getEmployeeService();

        if (request.getParameter(SEARCH_LOGIN) != null) {
            String login = request.getParameter(SEARCH_LOGIN);
            request.setAttribute(EMPLOYEES, service.find(login));
            request.setAttribute(SEARCH_LOGIN, login);
        } else {
            request.setAttribute(EMPLOYEES, service.find());
            LOGGER.debug("Get list of employees");
        }
        return null;
    }
}
