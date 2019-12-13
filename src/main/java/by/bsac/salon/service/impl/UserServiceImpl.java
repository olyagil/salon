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
        return dao.read(login, md5(password));
    }

    @Override
    public Integer save(User user) throws DataBaseException {
        if (user.getId() != null) {
            if (user.getPassword() != null) {
                user.setPassword(md5(user.getPassword()));
                dao.updatePassword(user);
                LOGGER.debug("Update password for user");
            } else if (user.getAvatar() != null) {
                dao.updateAvatar(user);
            } else {
                dao.update(user);
                LOGGER.debug("Update info about user");
            }
        } else {
            user.setPassword(md5(user.getPassword()));
            user.setId(dao.create(user));
            LOGGER.debug("Create user info");
        }
        return user.getId();

    }

    @Override
    public boolean delete(Integer id) throws DataBaseException {
        return dao.delete(id);

    }

    private String md5(String password) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("md5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            byte[] hash = messageDigest.digest();
            Formatter formatter = new Formatter();

            for (int i = 0; i < hash.length; i++) {
                formatter.format("%02X", hash[i]);
            }
            String m5sum = formatter.toString();
            formatter.close();
            return m5sum;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
