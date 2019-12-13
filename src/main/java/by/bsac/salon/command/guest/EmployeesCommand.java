package by.bsac.salon.command.guest;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeesCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String EMPLOYEES = "employees";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        EmployeeService service = serviceFactory.getEmployeeService();
        request.setAttribute(EMPLOYEES, service.find());
        LOGGER.debug("Get list of employees.");
        return null;
    }
}
