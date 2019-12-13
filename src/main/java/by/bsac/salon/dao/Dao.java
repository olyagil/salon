package by.bsac.salon.dao;

import by.bsac.salon.builder.Builder;
import by.bsac.salon.entity.Entity;
import by.bsac.salon.exception.DataBaseException;

import java.util.List;

public interface Dao<Type extends Entity> {
    int countRows() throws DataBaseException;

    List<Type> read() throws DataBaseException;

    Integer create(Type entity) throws DataBaseException;

    Type read(Integer id) throws DataBaseException;

    boolean update(Type entity) throws DataBaseException;

    boolean delete(Integer id) throws DataBaseException;

    Builder<Type> getBuilder();
}
