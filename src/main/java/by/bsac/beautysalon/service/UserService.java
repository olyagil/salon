package by.bsac.beautysalon.service;

import by.bsac.beautysalon.entity.User;
import by.bsac.beautysalon.exception.DataBaseException;

import java.util.List;

public interface UserService extends Service<User> {

    List<User> find(String login) throws DataBaseException;

    User find(String login, String password)
            throws DataBaseException;

}
