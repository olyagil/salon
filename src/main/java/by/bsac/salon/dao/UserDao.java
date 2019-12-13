package by.bsac.salon.dao;

import by.bsac.salon.builder.Builder;
import by.bsac.salon.builder.UserBuilder;
import by.bsac.salon.entity.User;
import by.bsac.salon.exception.DataBaseException;

import java.util.List;

public interface UserDao extends Dao<User> {


    List<User> read(String login) throws DataBaseException;

    User read(String login, String password) throws DataBaseException;

    void updatePassword(User user) throws DataBaseException;


    default Builder<User> getBuilder() {
        return new UserBuilder();
    }
}
