package by.bsac.salon.service;

import by.bsac.salon.entity.Talon;
import by.bsac.salon.exception.DataBaseException;

import java.sql.Date;
import java.util.List;

public interface TalonService extends Service<Talon> {

    List<Talon> findByClient(Integer clientId) throws DataBaseException;

    List<Talon> findByEmployee(Integer specialistId) throws DataBaseException;

    List<Talon> find(Date date) throws DataBaseException;

    List<Talon> find(Boolean status) throws DataBaseException;

}
