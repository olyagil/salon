package by.bsac.beautysalon.service.impl;

import by.bsac.beautysalon.dao.ServiceDao;
import by.bsac.beautysalon.entity.Service;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.ServiceService;

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
    public List<Service> find(int currentPage, int recordsPerPage) throws DataBaseException {
        return dao.read(currentPage, recordsPerPage);
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
