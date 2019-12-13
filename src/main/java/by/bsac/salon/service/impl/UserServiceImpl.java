package by.bsac.salon.service.impl;

import by.bsac.salon.dao.UserDao;
import by.bsac.salon.entity.User;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final UserDao dao;

    public UserServiceImpl(UserDao userDao) {
        this.dao = userDao;
    }

    @Override
    public int countRows() throws DataBaseException {
        return dao.countRows();
    }

    @Override
    public List<User> find() throws DataBaseException {
        return dao.read();
    }

    public List<User> find(int currentPage, int recordsPerPage)
            throws DataBaseException {
        return dao.read(currentPage, recordsPerPage);
    }

    @Override
    public List<User> find(String login) throws DataBaseException {
        return dao.read(login);
    }

    @Override
    public User find(Integer id) throws DataBaseException {
        return dao.read(id);
    }

    @Override
    public User find(String login, String password) throws DataBaseException {
        return dao.read(login, password);
    }

    @Override
    public Integer save(User user) throws DataBaseException {
        if (user.getId() != null) {
            if (user.getPassword() != null) {
                user.setPassword(user.getPassword());
                dao.updatePassword(user);
                LOGGER.debug("Update password for user");
            } else {
                dao.update(user);
                LOGGER.debug("Update info about user");
            }
        } else {
            user.setPassword(user.getPassword());
            user.setId(dao.create(user));
            LOGGER.debug("Create user info");
        }
        return user.getId();

    }

    @Override
    public boolean delete(Integer id) throws DataBaseException {
        return dao.delete(id);

    }

}
