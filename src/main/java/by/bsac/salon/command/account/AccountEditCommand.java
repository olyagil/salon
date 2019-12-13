package by.bsac.salon.command.account;

import by.bsac.salon.entity.User;
import by.bsac.salon.service.UserService;
import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class {@code AccountEditCommand} is used for editing user account.
 */
public class AccountEditCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String USER = "user";

    @Override
    public Forward execute(HttpServletRequest request,
                           HttpServletResponse response) throws DataBaseException {
        HttpSession session = request.getSession(false);

        try {

            Integer id = (Integer) session.getAttribute(ID);

            LOGGER.debug("The id of the user: " + id);

            UserService service = serviceFactory.getUserService();
            User user = service.find(id);
            if (user != null) {
                request.setAttribute(USER, user);

            }
        } catch (NumberFormatException e) {
            LOGGER.error("Can't parse the id", e);
            throw new DataBaseException(e);
        }
        return null;

    }
}
