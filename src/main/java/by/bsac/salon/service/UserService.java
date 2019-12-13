package by.bsac.salon.service;

import by.bsac.salon.entity.User;
import by.bsac.salon.exception.DataBaseException;

import java.util.List;

public interface UserService extends Service<User> {

    List<User> find(String login) throws DataBaseException;

    User find(String login, String password)
            throws DataBaseException;

}
