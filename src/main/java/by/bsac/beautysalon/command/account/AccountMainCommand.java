package by.bsac.beautysalon.command.account;

import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;
import by.bsac.beautysalon.entity.enumeration.Role;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.EmployeeService;
import by.bsac.beautysalon.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class {@code AccountMainCommand} is used for displaying info about user.
 */
public class AccountMainCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String LOGGED_USER = "loggedUser";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response)
            throws DataBaseException {
        HttpSession session = request.getSession(false);
        Integer id = (Integer) session.getAttribute(ID);
        Integer roleId = (Integer) session.getAttribute(ROLE);
        if (roleId != null) {
            Role role = Role.getById(roleId);
            LOGGER.debug("User id: " + id + " with role: " + role);
            if (role.equals(Role.EMPLOYEE)) {
                EmployeeService service = serviceFactory.getEmployeeService();
                request.setAttribute(LOGGED_USER, service.find(id));
                LOGGER.debug("Employee: " + service.find(id));
            } else {
                UserService service = serviceFactory.getUserService();
                request.setAttribute(LOGGED_USER, service.find(id));
                LOGGER.debug("User: " + service.find(id));
            }
        }
        return null;
    }
}
