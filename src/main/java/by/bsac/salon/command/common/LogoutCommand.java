package by.bsac.salon.command.common;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class ${@code LogoutCommand} is used to logout user.
 */
public class LogoutCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LANGUAGE = "lang";

    @Override
    public Forward execute(HttpServletRequest request,
                           HttpServletResponse response)
            throws DataBaseException {
        HttpSession session = request.getSession(false);
        String lang = (String) session.getAttribute(LANGUAGE);
        session.invalidate();
        LOGGER.debug("User is logged out.");
        request.getSession().setAttribute(LANGUAGE, lang);
        return new Forward("/main.html");
    }
}
