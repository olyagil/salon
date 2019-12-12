package by.bsac.beautysalon.command.employee;

import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.TalonService;
import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TalonDeleteCommand extends Command {

    private static final String ID = "id";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        TalonService service = serviceFactory.getTalonService();
        Integer id = Integer.parseInt(request.getParameter(ID));
        service.delete(id);
        return new Forward("/talon/list.html");
    }
}
