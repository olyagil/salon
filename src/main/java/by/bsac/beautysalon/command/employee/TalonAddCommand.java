package by.bsac.beautysalon.command.employee;

import by.bsac.beautysalon.entity.enumeration.Role;
import by.bsac.beautysalon.service.UserService;
import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.EmployeeService;
import by.bsac.beautysalon.service.ServiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TalonAddCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String EMPLOYEES = "employees";
    private static final String SERVICES = "services";
    private static final String CLIENTS = "clients";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        UserService userService = serviceFactory.getUserService();
        ServiceService service = serviceFactory.getServiceService();
        EmployeeService employeeService = serviceFactory.getEmployeeService();
        HttpSession session = request.getSession();
        try {
            Integer id = (Integer) session.getAttribute(ID);

            Role role = Role.getById((Integer) session.getAttribute(ROLE));

            switch (role) {
                case EMPLOYEE:
                    request.setAttribute(EMPLOYEES, userService.find(id));
                    break;
                case ADMINISTRATOR:
                    request.setAttribute(EMPLOYEES, employeeService.find());
            }
            request.setAttribute(SERVICES, service.find());
            request.setAttribute(CLIENTS, userService.find());
        } catch (NumberFormatException e) {
            LOGGER.error("Can't parse the id" + e);
            return new Forward("/talon.list.html");
        }
        return null;
    }
}
