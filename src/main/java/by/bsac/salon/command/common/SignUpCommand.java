package by.bsac.salon.command.common;

import by.bsac.salon.service.UserService;
import by.bsac.salon.validator.UserValidator;
import by.bsac.salon.command.Command;
import by.bsac.salon.command.Forward;
import by.bsac.salon.entity.User;
import by.bsac.salon.entity.enumeration.Gender;
import by.bsac.salon.entity.enumeration.Role;
import by.bsac.salon.exception.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;


public class SignUpCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PATRONYMIC = "patronymic";
    private static final String GENDER = "gender";
    private static final String PHONE = "phone";
    private static final String BIRTH_DATE = "birth_date";
    private static final String IMG = "img";
    private static final String MESSAGE_SIGNUP = "message_signup";

    @Override
    public Forward execute(HttpServletRequest request,
                           HttpServletResponse response)
            throws DataBaseException {
        Forward forward = new Forward("/login.html");
        UserService service = serviceFactory.getUserService();
        HttpSession session = request.getSession();
        User user = new User();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String patronymic = request.getParameter(PATRONYMIC);
        if (UserValidator.checkLogin(login)) {
            if (service.find(login).isEmpty()) {

                if (UserValidator.checkPassword(password)) {
                    user.setPassword(password);
                    try {
                        Gender gender = Gender.valueOf(request
                                .getParameter(GENDER).toUpperCase());
                        String phone = request.getParameter(PHONE);
                        Date birthDate = Date.valueOf(request.getParameter(BIRTH_DATE));
                        user.setPhone(Integer.parseInt(UserValidator.getPhone(phone)));

                        user.setLogin(login);
                        user.setRole(Role.CLIENT);
                        user.setName(name);
                        user.setSurname(surname);
                        user.setPatronymic(patronymic);
                        user.setGender(gender);
                        user.setBirthDate(birthDate);
                    } catch (IllegalArgumentException e) {
                        LOGGER.error("Can't insert user in DB.", e);
                        session.setAttribute(MESSAGE_SIGNUP,
                                "Incorrect data. Please, try again!");
                        return null;
                    }

                    service.save(user);
                    session.setAttribute("message_success_signup", "Вы " +
                            "успешно зарегестрированы! Для продолжения введите логин и " +
                            "пароль.");
                    return forward;
                } else {
                    session.setAttribute(MESSAGE_SIGNUP, "Password must be " +
                            " at list 6 digits and in pattern: a-zA-Z0-9-_");
                }
            } else {
                session.setAttribute(MESSAGE_SIGNUP, "Such user is " +
                        "already exist.");

            }
        }
        return null;

    }
}
