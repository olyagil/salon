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
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int RECORDS_PER_PAGE = 5;
    private static final String CURRENT_PAGE = "currentPage";
    private static final String SEARCH_LOGIN = "searchLogin";
    private static final String NO_OF_PAGES = "noOfPages";
    private static final String EMPLOYEES = "employees";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        EmployeeService service = serviceFactory.getEmployeeService();
        int currentPage;
        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.valueOf(request.getParameter(CURRENT_PAGE));
        } else {
            currentPage = DEFAULT_CURRENT_PAGE;
        }
        int rows;
        if (request.getParameter(SEARCH_LOGIN) != null) {
            String login = request.getParameter(SEARCH_LOGIN);
            List<Employee> employeeList = service.find(login);
            rows = employeeList.size();
            request.setAttribute(EMPLOYEES, service.find(login));
            request.setAttribute(SEARCH_LOGIN, login);
        } else {
            rows = service.countRows();
            request.setAttribute(EMPLOYEES, service.find(currentPage, RECORDS_PER_PAGE));
            LOGGER.debug("Get list of employees");
        }

        request.setAttribute(NO_OF_PAGES, PaginationUtil.getNumOfPages(rows));
        request.setAttribute(CURRENT_PAGE, currentPage);

        return null;
    }
}
