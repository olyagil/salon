package by.bsac.salon.command.employee;

import by.bsac.salon.entity.Talon;
import by.bsac.salon.entity.enumeration.Role;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.TalonService;
import by.bsac.salon.utill.PaginationUtil;
import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

public class TalonListCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int RECORDS_PER_PAGE = 5;
    private static final String CURRENT_PAGE = "currentPage";
    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String STATUS = "status";
    private static final String TALONS = "talons";
    private static final String SEARCH_DATE = "searchDate";
    private static final String NO_OF_PAGES = "noOfPages";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        TalonService service = serviceFactory.getTalonService();
        HttpSession session = request.getSession();

        int currentPage;
        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.valueOf(request.getParameter(CURRENT_PAGE));
        } else {
            currentPage = DEFAULT_CURRENT_PAGE;
        }

        int rows = 0;
        List<Talon> talonList;
        try {
            Integer id = (Integer) session.getAttribute(ID);
            Role role = Role.getById((Integer) session.getAttribute(ROLE));
            if (request.getParameter(STATUS) != null) {
                Boolean status = Boolean.valueOf(request.getParameter(STATUS));
                talonList = service.find(status);
                rows = 0;
                request.setAttribute(TALONS, talonList);
            } else if (request.getParameter(SEARCH_DATE) != null) {
                Date date = Date.valueOf(request.getParameter(SEARCH_DATE));
                talonList = service.find(date);
                rows = 0;
                request.setAttribute(TALONS, talonList);
                request.setAttribute(SEARCH_DATE, date);
            } else {
                switch (role) {
                    case EMPLOYEE:
                        talonList = service.findByEmployee(id);
                        request.setAttribute(TALONS, talonList);
                        rows = 0;
                        break;
                    case CLIENT:
                        talonList = service.findByClient(id);
                        request.setAttribute(TALONS, talonList);
                        rows = 0;
                        break;
                    case ADMINISTRATOR:
                        request.setAttribute(TALONS,
                                service.find(currentPage, RECORDS_PER_PAGE));
                        rows = service.countRows();
                }
            }
        } catch (NumberFormatException e) {
            LOGGER.error("Can't parse the id" + e);

        }
        int nOfPages = PaginationUtil.getNumOfPages(rows);
        request.setAttribute(NO_OF_PAGES, nOfPages);
        request.setAttribute(CURRENT_PAGE, currentPage);

        return null;
    }
}
