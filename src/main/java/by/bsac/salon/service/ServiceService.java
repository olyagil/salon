package by.bsac.salon.service;

import by.bsac.salon.entity.Service;
import by.bsac.salon.exception.DataBaseException;

import java.util.List;

public interface ServiceService extends by.bsac.salon.service.Service<Service> {

    List<Service> find(String name) throws DataBaseException;

}
