package by.bsac.salon.service.impl;

import by.bsac.salon.dao.EmployeeDao;
import by.bsac.salon.entity.Employee;
import by.bsac.salon.exception.DataBaseException;
import by.bsac.salon.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao dao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.dao = employeeDao;
    }

    @Override
    public int countRows() throws DataBaseException {
        return dao.countRows();
    }

    @Override
    public List<Employee> find() throws DataBaseException {
        return dao.read();
    }

    @Override
    public List<Employee> find(String login) throws DataBaseException {
        return dao.read(login);
    }
    @Override
    public Employee find(Integer id) throws DataBaseException {
        return dao.read(id);
    }

    @Override
    public Integer save(Employee employee) throws DataBaseException {
        if (employee.getSurname() != null) {
            dao.create(employee);
        } else {
            dao.update(employee);
        }
        return employee.getId();
    }

    @Override
    public boolean delete(Integer id) throws DataBaseException {
        return dao.delete(id);
    }
}
