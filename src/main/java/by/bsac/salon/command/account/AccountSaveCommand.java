package by.bsac.salon.command.account;

import by.bsac.salon.entity.enumeration.Gender;
import by.bsac.salon.entity.enumeration.Role;
import by.bsac.salon.service.UserService;
import by.bsac.salon.validator.UserValidator;
import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.entity.User;
import by.bsac.salon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;

/**
 * The class {@code AccountSaveCommand} is used for saving info about user.
 */
public class AccountSaveCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String PATRONYMIC = "patronymic";
    private static final String SURNAME = "surname";
    private static final String PHONE = "phone";
    private static final String BIRTH_DATE = "birth_date";
    private static final String GENDER = "gender";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response)
            throws DataBaseException {
        HttpSession session = request.getSession();

        Integer id = (Integer) session.getAttribute(ID);
        Integer roleId = (Integer) session.getAttribute(ROLE);
        try {
            if (roleId != null) {
                Role role = Role.getById((roleId));
                User user = new User();
                UserService service = serviceFactory.getUserService();
                user.setId(id);

                String login = request.getParameter(LOGIN);
                if (UserValidator.checkLogin(login)
                        && service.find(login).isEmpty()) {
                    String name = request.getParameter(NAME);
                    String surname = request.getParameter(SURNAME);
                    String patronymic = request.getParameter(PATRONYMIC);

                    Integer phone = Integer.parseInt(request.getParameter(PHONE));
                    Date birthDate = Date.valueOf(request.getParameter(BIRTH_DATE));
                    user.setGender(Gender.valueOf(request.getParameter(GENDER).toUpperCase()));
                    user.setLogin(login);
                    user.setRole(role);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setPatronymic(patronymic);
                    user.setPhone(phone);
                    user.setBirthDate(birthDate);
                    LOGGER.debug("User is successfully updated.");
                    service.save(user);
                    session.setAttribute("success_save_info", "The information is " +
                            "successful updated");
                } else {
                    session.setAttribute("failure_save_info", "The information " +
                            "can't be updated");
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.debug("Can't parse date.", e);
            session.setAttribute("failure_save_info", "Such user is " +
                    "exist or  enter correct data. ");

        }
        return new Forward("/account/edit/info.html");
    }
}
