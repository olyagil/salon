package by.bsac.salon.dao;

import by.bsac.salon.builder.Builder;
import by.bsac.salon.builder.EmployeeBuilder;
import by.bsac.salon.entity.Employee;
import by.bsac.salon.exception.DataBaseException;

import java.util.List;

public interface EmployeeDao extends Dao<Employee> {

    List<Employee> read(String login) throws DataBaseException;

    default Builder<Employee> getBuilder() {
        return new EmployeeBuilder();
    }
}
