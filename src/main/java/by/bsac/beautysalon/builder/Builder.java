package by.bsac.beautysalon.builder;

import by.bsac.beautysalon.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Builder<Type extends Entity> {

    Type build(ResultSet resultSet) throws SQLException;

}
