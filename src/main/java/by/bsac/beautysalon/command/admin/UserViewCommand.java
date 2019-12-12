package by.bsac.beautysalon.command.admin;

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

/**
 * The class {@code UserViewCommand} is used for viewing user.
 */
public class UserViewCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String USER = "user";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {

        UserService userService = serviceFactory.getUserService();

        try {
            int id = Integer.parseInt(request.getParameter(ID));
            Role role =
                    Role.getById(Integer.parseInt(request.getParameter(ROLE)));
            LOGGER.debug("User id: " + id + " role: " + role);

            if (role == Role.EMPLOYEE) {
                EmployeeService service = serviceFactory.getEmployeeService();
                request.setAttribute(USER, service.find(id));
            } else {
                request.setAttribute(USER, userService.find(id));
            }
        } catch (NumberFormatException e) {
            LOGGER.error("Can't determinate the id of user");
            return new Forward("/client/list.html");
        }
        return null;
    }
}
