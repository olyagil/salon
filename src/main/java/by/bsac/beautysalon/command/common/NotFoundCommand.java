package by.bsac.beautysalon.command.common;

import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;
import by.bsac.beautysalon.exception.DataBaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotFoundCommand extends Command {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        return null;
    }
}
