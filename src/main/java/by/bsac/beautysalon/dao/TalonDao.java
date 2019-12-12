package by.bsac.beautysalon.dao;

import by.bsac.beautysalon.builder.Builder;
import by.bsac.beautysalon.builder.TalonBuilder;
import by.bsac.beautysalon.entity.Talon;
import by.bsac.beautysalon.exception.DataBaseException;

import java.sql.Date;
import java.util.List;

public interface TalonDao extends Dao<Talon> {
    List<Talon> readByClient(Integer clientId) throws DataBaseException;

    List<Talon> readByEmployee(Integer specialistId) throws DataBaseException;

    List<Talon> read(Boolean status) throws DataBaseException;

    List<Talon> read(Date date) throws DataBaseException;

    default Builder<Talon> getBuilder() {
        return new TalonBuilder();
    }

}
