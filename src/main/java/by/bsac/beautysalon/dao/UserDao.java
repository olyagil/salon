package by.bsac.beautysalon.dao;

import by.bsac.beautysalon.builder.Builder;
import by.bsac.beautysalon.builder.UserBuilder;
import by.bsac.beautysalon.entity.User;
import by.bsac.beautysalon.exception.DataBaseException;

import java.util.List;

public interface UserDao extends Dao<User> {


    List<User> read(String login) throws DataBaseException;

    User read(String login, String password) throws DataBaseException;

    void updatePassword(User user) throws DataBaseException;

    void updateAvatar(User user) throws DataBaseException;

    default Builder<User> getBuilder() {
        return new UserBuilder();
    }
}
