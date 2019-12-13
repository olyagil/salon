package by.bsac.salon.command.admin;

import by.bsac.salon.entity.Service;
import by.bsac.salon.utill.PaginationUtil;
import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.ServiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * The class {@code ServiceListCommand} is used for viewing all services from
 * DB.
 */
public class ServiceListCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SEARCH_NAME = "searchName";
    private static final String SERVICES = "services";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        ServiceService service = serviceFactory.getServiceService();
        if (request.getParameter(ServiceListCommand.SEARCH_NAME) != null) {
            String name = request.getParameter(SEARCH_NAME);
            List<Service> serviceList = service.find(name);
            request.setAttribute(SERVICES, serviceList);
        } else {
            request.setAttribute(SERVICES, service.find());
            LOGGER.debug("Get list if services");
        }
        return null;
    }
}
