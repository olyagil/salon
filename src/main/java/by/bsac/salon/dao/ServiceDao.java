package by.bsac.salon.dao;

import by.bsac.salon.builder.Builder;
import by.bsac.salon.builder.ServiceBuilder;
import by.bsac.salon.entity.Service;
import by.bsac.salon.exception.DataBaseException;

import java.util.List;

public interface ServiceDao extends Dao<Service> {

    List<Service> read(String name) throws DataBaseException;


    List<Service> read() throws DataBaseException;

    default Builder<Service> getBuilder() {
        return new ServiceBuilder();
    }
}
