package by.bsac.beautysalon.dao;

import by.bsac.beautysalon.builder.Builder;
import by.bsac.beautysalon.builder.ServiceBuilder;
import by.bsac.beautysalon.entity.Service;
import by.bsac.beautysalon.exception.DataBaseException;

import java.util.List;

public interface ServiceDao extends Dao<Service> {

    List<Service> read(String name) throws DataBaseException;


    List<Service> read() throws DataBaseException;

    default Builder<Service> getBuilder() {
        return new ServiceBuilder();
    }
}
