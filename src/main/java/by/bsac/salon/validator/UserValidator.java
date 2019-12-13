package by.bsac.salon.validator;

import by.bsac.salon.entity.enumeration.Gender;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.regex.Pattern;

public class UserValidator {

    private static final String REGEX_LOGIN = "^[a-zA-Z0-9_-]{3,16}$";
    private static final String REGEX_PASSWORD = "^[a-zA-Z0-9_-]{6,18}$";

    public static boolean checkLogin(String login) {
        return login != null && Pattern.matches(REGEX_LOGIN, login);
    }

    public static boolean checkPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }


    public static String getPhone(String phone) {
        return phone.replaceAll("\\+375\\(", "")
                .replaceAll("\\)", "")
                .replaceAll("-", "");
    }

}
