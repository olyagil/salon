package by.bsac.salon.service;

import by.bsac.salon.entity.Employee;
import by.bsac.salon.exception.DataBaseException;

import java.util.List;

public interface EmployeeService extends Service<Employee> {

    List<Employee> find(String login) throws DataBaseException;


}
