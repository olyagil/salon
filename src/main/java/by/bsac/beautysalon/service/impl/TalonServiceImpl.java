package by.bsac.beautysalon.service.impl;

import by.bsac.beautysalon.dao.TalonDao;
import by.bsac.beautysalon.entity.Talon;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.TalonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class TalonServiceImpl implements TalonService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final TalonDao dao;

    public TalonServiceImpl(TalonDao talonDao) {
        this.dao = talonDao;
    }


    @Override
    public int countRows() throws DataBaseException {
        return dao.countRows();
    }

    @Override
    public List<Talon> find(int currentPage, int recordsPerPage) throws DataBaseException {
        return dao.read(currentPage, recordsPerPage);
    }

    @Override
    public List<Talon> findByClient(Integer clientId) throws DataBaseException {
        return dao.readByClient(clientId);
    }

    @Override
    public List<Talon> findByEmployee(Integer specialistId) throws DataBaseException {
        return dao.readByEmployee(specialistId);
    }

    @Override
    public List<Talon> find(Date date) throws DataBaseException {
        return dao.read(date);
    }

    @Override
    public List<Talon> find(Boolean status) throws DataBaseException {
        return dao.read(status);
    }

    @Override
    public List<Talon> find() throws DataBaseException {
        return dao.read();
    }


    @Override
    public Talon find(Integer id) throws DataBaseException {
        return dao.read(id);
    }

    @Override
    public Integer save(Talon talon) throws DataBaseException {
        LOGGER.debug("TALON ID: " + talon.getId());
        if (talon.getId() != null) {
            dao.update(talon);
        } else {
            talon.setId(dao.create(talon));
        }
        return talon.getId();
    }

    @Override
    public boolean delete(Integer id) throws DataBaseException {
        return dao.delete(id);
    }
}
