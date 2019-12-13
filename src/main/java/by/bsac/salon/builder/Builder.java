package by.bsac.salon.builder;

import by.bsac.salon.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Builder<Type extends Entity> {

    Type build(ResultSet resultSet) throws SQLException;

}
