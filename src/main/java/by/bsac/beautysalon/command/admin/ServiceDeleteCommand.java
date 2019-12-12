package by.bsac.beautysalon.command.admin;

import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;
import by.bsac.beautysalon.entity.Service;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.ServiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class {@code ServiceDeleteCommand} is used for deleting service from DB.
 */
public class ServiceDeleteCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {

        ServiceService service = serviceFactory.getServiceService();
        String id = request.getParameter(ID);
        try {
            if (id != null) {
                Service serv = service.find(Integer.parseInt(id));
                LOGGER.debug(String.format("Trying to delete the id: %s which is " +
                        "service: %s", id, serv));
                if (serv != null) {
                    service.delete(Integer.parseInt(id));
                }else{
                    request.getSession().setAttribute("alert", "Can't delete service");

                }
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("alert", "Can't delete service");
        }
        return new Forward("/service/list.html");
    }
}
