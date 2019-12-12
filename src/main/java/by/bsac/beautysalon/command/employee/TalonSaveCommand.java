package by.bsac.beautysalon.command.employee;

import by.bsac.beautysalon.entity.Employee;
import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;
import by.bsac.beautysalon.entity.Service;
import by.bsac.beautysalon.entity.Talon;
import by.bsac.beautysalon.entity.User;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.TalonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TalonSaveCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String SERVICE_ID = "serviceId";
    private static final String CLIENT_ID = "clientId";
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String RECEPTION_DATE_COL = "receptionDateCol";
    private static final String RECEPTION_DATE = "receptionDate";
    private static final String RECEPTION_DATE_COL1 = "receptionDateCol";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        HttpSession session = request.getSession();
        TalonService service = serviceFactory.getTalonService();
        Talon talon = new Talon();
        Service serv = new Service();
        User client = new User();
        Employee employee = new Employee();

        String id = request.getParameter(ID);
        String serviceId = request.getParameter(SERVICE_ID);
        String clientId = request.getParameter(CLIENT_ID);
        String employeeId = request.getParameter(EMPLOYEE_ID);
        if (id != null) {
            talon.setId(Integer.parseInt(id));
        }
        if (employeeId != null) {
            employee.setId(Integer.parseInt(employeeId));
        }
        if (serviceId != null) {
            serv.setId(Integer.parseInt(serviceId));
        }
        if (clientId != null) {
            client.setId(Integer.parseInt(clientId));
        }

        talon.setService(serv);
        talon.setClient(client);
        talon.setEmployee(employee);
        Date date;
        try {
            if (request.getParameter(RECEPTION_DATE_COL) != null) {
                date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
                        .parse(request.getParameter(RECEPTION_DATE_COL1));
            } else {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse(request.getParameter(RECEPTION_DATE));
            }
            LOGGER.debug(date);
        } catch (ParseException e) {
            LOGGER.debug("Can't parse the date");
            request.getSession().setAttribute("alert", "Please, enter correct" +
                    " data.");
            return new Forward("/talon/list.html");
        }

        talon.setReceptionDate(new Timestamp(date.getTime()));
        talon.setStatus(Boolean.valueOf(request.getParameter("status")));
        service.save(talon);
        session.setAttribute("success_save_talon", "Talon is successfully" +
                " saved!");
        return new Forward("/talon/list.html");
    }
}
