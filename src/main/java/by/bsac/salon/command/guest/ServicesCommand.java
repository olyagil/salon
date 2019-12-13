package by.bsac.salon.command.guest;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.ServiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServicesCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SERVICES = "services";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {

        ServiceService service = serviceFactory.getServiceService();
        request.setAttribute(SERVICES, service.find());
        LOGGER.debug("Get list of services.");
        return null;
    }
}
