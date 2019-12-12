package by.bsac.beautysalon.dao;

import by.bsac.beautysalon.builder.Builder;
import by.bsac.beautysalon.builder.EmployeeBuilder;
import by.bsac.beautysalon.entity.Employee;
import by.bsac.beautysalon.exception.DataBaseException;

import java.util.List;

public interface EmployeeDao extends Dao<Employee> {

    List<Employee> read(String login) throws DataBaseException;

    default Builder<Employee> getBuilder() {
        return new EmployeeBuilder();
    }
}
