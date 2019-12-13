package by.bsac.salon.command.account;

import by.bsac.salon.entity.enumeration.Gender;
import by.bsac.salon.service.UserService;
import by.bsac.salon.validator.UserValidator;
import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.entity.User;
import by.bsac.salon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class {@code AccountSaveAvatarCommand} is used for saving avatar of user.
 */
public class AccountSaveAvatarCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String IMG = "img";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws DataBaseException {

        Integer id = (Integer) request.getSession().getAttribute(ID);
        if (id != null) {
            User user = new User();
            user.setId(id);
            UserService service = serviceFactory.getUserService();
            try {
                user.setAvatar(UserValidator.getAvatar(request.getPart(IMG),
                        Gender.FEMALE));
                service.save(user);
            } catch (IOException | ServletException e) {
                LOGGER.error("Can't read the image from file", e);
                throw new DataBaseException(e);
            }
        }
        return new Forward("/account/edit/info.html");
    }
}
