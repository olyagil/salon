package by.bsac.beautysalon.command.admin;

import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;
import by.bsac.beautysalon.entity.enumeration.Role;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class {@code UserDeleteCommand} is used for deleting user.
 */
public class UserDeleteCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        try {
            UserService service = serviceFactory.getUserService();
            Integer id = Integer.parseInt(request.getParameter(USER_ID));
            Role role = Role.getById(Integer.parseInt(request.getParameter(
                    USER_ROLE)));

            service.delete(id);

            switch (role) {
                case EMPLOYEE:
                    return new Forward("/employee/list.html");
                case CLIENT:
                    return new Forward("/client/list.html");
            }
        } catch (NumberFormatException e) {
            LOGGER.error("Can't delete user with id ");
            request.getSession().setAttribute("alert", "Can't determine id.");
        }
        return new Forward("/client/list.html");

    }
}
