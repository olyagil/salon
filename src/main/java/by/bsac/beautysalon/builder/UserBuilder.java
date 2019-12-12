package by.bsac.beautysalon.builder;

import by.bsac.beautysalon.utill.ImageUtil;
import by.bsac.beautysalon.entity.User;
import by.bsac.beautysalon.entity.enumeration.Gender;
import by.bsac.beautysalon.entity.enumeration.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String ROLE = "role";
    private static final String SURNAME = "surname";
    private static final String NAME = "name";
    private static final String PATRONYMIC = "patronymic";
    private static final String GENDER = "gender";
    private static final String PHONE = "phone";
    private static final String BIRTH_DATE = "birth_date";
    private static final String AVATAR = "avatar";

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(USER_ID));
        user.setLogin(resultSet.getString(LOGIN));
        user.setRole(Role.getById(resultSet.getInt(ROLE)));
        user.setSurname(resultSet.getString(SURNAME));
        user.setName(resultSet.getString(NAME));
        user.setPatronymic(resultSet.getString(PATRONYMIC));
        user.setGender(Gender.getById(resultSet.getInt(GENDER)));
        user.setPhone(resultSet.getInt(PHONE));
        user.setBirthDate(resultSet.getDate(BIRTH_DATE));
        user.setAvatar(ImageUtil.encoder(resultSet.getBlob(AVATAR)));

        return user;
    }
}
