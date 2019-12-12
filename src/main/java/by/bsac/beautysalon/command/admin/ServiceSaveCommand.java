package by.bsac.beautysalon.command.admin;

import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;
import by.bsac.beautysalon.entity.Service;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The class {@code ServiceSaveCommand} is used for saving service.
 */
public class ServiceSaveCommand extends Command {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String DURATION = "duration";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        ServiceService service = serviceFactory.getServiceService();
        Service serv = new Service();
        String id = request.getParameter(ID);
        if (id != null) {
            serv.setId(Integer.parseInt(id));
        }
        serv.setName(request.getParameter(NAME));
        serv.setDescription(request.getParameter(DESCRIPTION));
        serv.setPrice(Double.parseDouble(request.getParameter(PRICE)));
        serv.setDuration(Double.parseDouble(request.getParameter(DURATION)));

        service.save(serv);
        return new Forward("/service/list.html");
    }
}
