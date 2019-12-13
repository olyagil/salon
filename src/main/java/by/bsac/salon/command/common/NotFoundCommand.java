package by.bsac.salon.command.common;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.exception.DataBaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NotFoundCommand extends Command {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {
        return null;
    }
}
