package by.bsac.beautysalon.command.admin;

import by.bsac.beautysalon.entity.Service;
import by.bsac.beautysalon.utill.PaginationUtil;
import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.ServiceService;
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
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int RECORDS_PER_PAGE = 5;
    private static final String CURRENT_PAGE = "currentPage";
    private static final String SEARCH_NAME = "searchName";
    private static final String SERVICES = "services";
    private static final String NO_OF_PAGES = "noOfPages";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        ServiceService service = serviceFactory.getServiceService();

        int currentPage;
        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.valueOf(request.getParameter(CURRENT_PAGE));
        } else {
            currentPage = DEFAULT_CURRENT_PAGE;
        }

        int rows;
        if (request.getParameter(ServiceListCommand.SEARCH_NAME) != null) {
            String name = request.getParameter(SEARCH_NAME);
            List<Service> serviceList = service.find(name);
            request.setAttribute(SERVICES, serviceList);
            rows = serviceList.size();
        } else {
            request.setAttribute(SERVICES, service.find(currentPage, RECORDS_PER_PAGE));
            rows = service.countRows();
            LOGGER.debug("Get list if services");
        }
        request.setAttribute(NO_OF_PAGES, PaginationUtil.getNumOfPages(rows));
        request.setAttribute(CURRENT_PAGE, currentPage);
        return null;
    }
}
