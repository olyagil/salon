package by.bsac.salon.command.common;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Class ${@code LanguageCommand} is used for changing language.
 */
public class LanguageCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LANGUAGE = "lang";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {

        String lang = request.getParameter(LANGUAGE);
        request.getSession().setAttribute(LANGUAGE, lang);
        URI uri = null;
        try {
            uri = new URI(request.getHeader("referer"));
        } catch (URISyntaxException e) {
            LOGGER.error("Can't get the previous page");
        }
        LOGGER.debug("The language is changed to: " + lang);

        return new Forward(uri.getPath().replace("/salon", ""));
    }
}
