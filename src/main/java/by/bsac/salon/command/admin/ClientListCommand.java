package by.bsac.salon.command.admin;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.entity.User;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.UserService;
import by.bsac.salon.utill.PaginationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class {@code ClientListCommand} is used for viewing all clients.
 */
public class ClientListCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SEARCH_LOGIN = "searchLogin";
    private static final String CLIENTS = "clients";

    @Override
    public Forward execute(HttpServletRequest request,
                           HttpServletResponse response) throws DataBaseException {
        UserService service = serviceFactory.getUserService();

        if (request.getParameter(SEARCH_LOGIN) != null) {
            String login = request.getParameter(SEARCH_LOGIN);
            List<User> userList = service.find(login);
            request.setAttribute(CLIENTS, userList);
            request.setAttribute(SEARCH_LOGIN, login);

        } else {
            request.setAttribute(CLIENTS, service.find());
        }
        LOGGER.debug("Get list of clients.");
        return null;
    }
}
