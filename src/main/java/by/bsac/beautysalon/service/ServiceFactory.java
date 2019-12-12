package by.bsac.beautysalon.service;

import by.bsac.beautysalon.dao.mysql.DaoFactory;
import by.bsac.beautysalon.service.impl.EmployeeServiceImpl;
import by.bsac.beautysalon.service.impl.ServiceServiceImpl;
import by.bsac.beautysalon.service.impl.TalonServiceImpl;
import by.bsac.beautysalon.service.impl.UserServiceImpl;

public class ServiceFactory {

    private final DaoFactory factory;

    public ServiceFactory(DaoFactory daoFactory) {
        this.factory = daoFactory;

    }

    public UserServiceImpl getUserService() {
        return new UserServiceImpl(factory.getUserDao());
    }

    public EmployeeServiceImpl getEmployeeService() {
        return new EmployeeServiceImpl(factory.getEmployeeDao());
    }

    public ServiceServiceImpl getServiceService() {
        return new ServiceServiceImpl(factory.getServiceDao());
    }

    public TalonServiceImpl getTalonService() {
        return new TalonServiceImpl(factory.getTalonDao());
    }

    public void close() {
        factory.close();
    }
}
