package by.bsac.beautysalon.service;

import by.bsac.beautysalon.entity.Employee;
import by.bsac.beautysalon.exception.DataBaseException;

import java.util.List;

public interface EmployeeService extends Service<Employee> {

    List<Employee> find(String login) throws DataBaseException;


}
