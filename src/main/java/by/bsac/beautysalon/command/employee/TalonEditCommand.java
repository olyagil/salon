package by.bsac.beautysalon.command.employee;

import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;
import by.bsac.beautysalon.entity.Talon;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.ServiceService;
import by.bsac.beautysalon.service.TalonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TalonEditCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String TALON = "talon";
    private static final String SERVICES = "services";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        try {
            int id = Integer.parseInt(request.getParameter(ID));
            TalonService talonService = serviceFactory.getTalonService();
            ServiceService service = serviceFactory.getServiceService();
            LOGGER.debug("ID: " + id);
            Talon talon = talonService.find(id);
            request.setAttribute(TALON, talon);
            request.setAttribute(SERVICES, service.find());
        } catch (NumberFormatException e) {
            LOGGER.error("Can't parse the id", e);
            return new Forward("/talon/list.html");
        }
        return null;
    }
}
