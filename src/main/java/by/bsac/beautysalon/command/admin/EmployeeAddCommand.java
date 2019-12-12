package by.bsac.beautysalon.command.admin;

import by.bsac.beautysalon.command.Command;
import by.bsac.beautysalon.command.Forward;
import by.bsac.beautysalon.entity.enumeration.Specialty;

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
