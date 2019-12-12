package by.bsac.beautysalon.builder;

import by.bsac.beautysalon.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceBuilder implements Builder<Service> {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String DURATION = "duration";

    @Override
    public Service build(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getInt(ID));
        service.setName(resultSet.getString(NAME));
        service.setDescription(resultSet.getString(DESCRIPTION));
        service.setPrice(resultSet.getDouble(PRICE));
        service.setDuration(resultSet.getDouble(DURATION));

        return service;

    }
}
