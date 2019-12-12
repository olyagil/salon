package by.bsac.beautysalon.service;

import by.bsac.beautysalon.entity.Service;
import by.bsac.beautysalon.exception.DataBaseException;

import java.util.List;

public interface ServiceService extends by.bsac.beautysalon.service.Service<Service> {

    List<Service> find(String name) throws DataBaseException;

}
