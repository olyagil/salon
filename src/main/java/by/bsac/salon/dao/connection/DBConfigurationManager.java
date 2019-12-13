package by.bsac.salon.dao.connection;

import java.util.ResourceBundle;

class DBConfigurationManager {

    private static final  DBConfigurationManager instance =
            new DBConfigurationManager();
    private final ResourceBundle bundle = ResourceBundle.getBundle("db_config");

    private DBConfigurationManager() {

    }

    static DBConfigurationManager getInstance() {
        return instance;
    }

    String getValue(String key) {
        return bundle.getString(key);
    }
}
