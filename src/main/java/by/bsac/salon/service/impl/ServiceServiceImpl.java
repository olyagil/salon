package by.bsac.salon.service.impl;

import by.bsac.salon.dao.ServiceDao;
import by.bsac.salon.entity.Service;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.ServiceService;

import java.util.List;

public class ServiceServiceImpl implements ServiceService {

    private final ServiceDao dao;

    public ServiceServiceImpl(ServiceDao serviceDao) {
        this.dao = serviceDao;
    }

    @Override
    public int countRows() throws DataBaseException {
        return dao.countRows();
    }

    @Override
    public List<Service> find() throws DataBaseException {
        return dao.read();
    }

    @Override
    public Service find(Integer id) throws DataBaseException {
        return dao.read(id);
    }


    @Override
    public List<Service> find(String name) throws DataBaseException {
        return dao.read(name);
    }

    @Override
    public Integer save(Service service) throws DataBaseException {
        if (service.getId() != null) {
            dao.update(service);
        } else {
            service.setId(dao.create(service));
        }
        return service.getId();
    }

    @Override
    public boolean delete(Integer id) throws DataBaseException {
        return dao.delete(id);
    }
}
