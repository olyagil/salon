package by.bsac.salon.service;

import by.bsac.salon.entity.Entity;
import by.bsac.salon.exception.DataBaseException;

import java.util.List;

public interface Service<Type extends Entity> {
    int countRows() throws DataBaseException;

    List<Type> find(int currentPage, int recordsPerPage) throws DataBaseException;

    List<Type> find() throws DataBaseException;

    Type find(Integer id) throws DataBaseException;

    Integer save(Type entity) throws DataBaseException;

    boolean delete(Integer id) throws DataBaseException;


}
