package by.bsac.salon.command.account;

import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.entity.User;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.UserService;
import by.bsac.salon.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class {@code AccountSaveCommand} is used for saving password.
 */
public class AccountSavePasswordCommand extends Command {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String OLD_PASSWORD = "old-password";
    private static final String PASSWORD = "password";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response)
            throws DataBaseException {
        HttpSession session = request.getSession();
        UserService service = serviceFactory.getUserService();

        Integer id = (Integer) session.getAttribute(ID);
        User user = service.find(id);
        if (user != null) {
            String oldPassword = request.getParameter(OLD_PASSWORD);
            String password = request.getParameter(PASSWORD);
            if (oldPassword != null && password != null) {
                if (service.find(user.getLogin(), oldPassword) != null) {
                    if (UserValidator.checkPassword(password)) {
                        user.setPassword(password);
                        service.save(user);
                        session.setAttribute("success_save_password",
                                "The password is successful changed!");
                        LOGGER.debug(String.format("Password for user with id %s is" +
                                " changed", id));
                    } else {
                        LOGGER.debug(String.format("Can't change password for" +
                                " user with id %s", id));
                        session.setAttribute("failure_save_password",
                                "Enter correct data, please.");
                    }
                } else {
                    session.setAttribute("failure_save_password",
                            "The password is not recognized! Repeat password, please.");
                }
            }
        }
        return new Forward("/account/edit/password.html");
    }
}
