package by.bsac.salon.command.admin;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.entity.enumeration.Specialty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class {@code EmployeeAddCommand} is used for adding employee, setting
 * specialties.
 */
public class EmployeeAddCommand extends Command {

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("specialties", Specialty.values());
        return null;
    }
}
