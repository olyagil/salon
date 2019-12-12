package by.bsac.beautysalon.service.impl;

import by.bsac.beautysalon.dao.connection.ConnectionPool;
import by.bsac.beautysalon.dao.mysql.DaoFactory;
import by.bsac.beautysalon.entity.Service;
import by.bsac.beautysalon.service.ServiceFactory;
import by.bsac.beautysalon.exception.DataBaseException;
import by.bsac.beautysalon.service.ServiceService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ServiceServiceImplTest {

    private ServiceFactory factory;
    private ServiceService service;

    @BeforeClass
    public void setUp() throws DataBaseException {
        ConnectionPool.getInstance().init();
        factory = new ServiceFactory(new DaoFactory());
        service = factory.getServiceService();

    }

    @AfterClass
    public void tearDown() {
        factory.close();
    }

    @DataProvider(name = "dataForFindServiceById")
    public static Object[][] dataForFindServiceById() {
        return new Object[][]{
                {new Service(1, "Консультация", "Консультация",
                        10.00, 30)},
                {new Service(2, "Пилинг", "Пилинг лица",
                        20.00, 30)},
                {new Service(3, "Лазерная шлифовка", "Лазерная " +
                        "шлифовка лица и тела", 250.00, 60)},
                {new Service(4, "Солярий", "Солярий всего тела",
                        1.20, 1)},
                {new Service(5, "Эпиляция", "Лазерная эпиляция всего " +
                        "тела", 100.00, 15)},
                {new Service(6, "Маникюр", "Маникюр рук",
                        30.00, 120)},
                {new Service(7, "Визаж", "Макияж лица",
                        45.00, 60)},
                {new Service(8, "Плазмотерапия",
                        "Терапия плазмой крови", 150.00, 50)},
        };
    }

    @Test(dataProvider = "dataForFindServiceById", description = "test for checking " +
            "finding service by id")
    public void testFindAllServices(Service expected) throws DataBaseException {
        Service actual = service.find(expected.getId());
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForFindAllServices")
    public static Object[][] dataForFindAllServices() {
        return new Object[][]{
                {Arrays.asList(new Service(1, "Консультация", "Консультация",
                                10.00, 30),
                        new Service(2, "Пилинг", "Пилинг лица",
                                20.00, 30),
                        new Service(3, "Лазерная шлифовка", "Лазерная " +
                                "шлифовка лица и тела", 250.00, 60),
                        new Service(4, "Солярий", "Солярий всего тела",
                                1.20, 1),
                        new Service(5, "Эпиляция", "Лазерная эпиляция всего " +
                                "тела", 100.00, 15),
                        new Service(6, "Маникюр", "Маникюр рук",
                                30.00, 120),
                        new Service(7, "Визаж", "Макияж лица",
                                45.00, 60),
                        new Service(8, "Плазмотерапия",
                                "Терапия плазмой крови", 150.00, 50))},
        };
    }

    @Test(dataProvider = "dataForFindAllServices", description = "test for " +
            "checking finding all services.")
    public void testFind(List<Service> expected) throws DataBaseException {
        List<Service> actual = service.find();
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForSaveService")
    public static Object[][] dataForSaveService() {
        return new Object[][]{
                {new Service("Консультация", "Консультация",
                        10.00, 30)},
                {new Service("Пилинг", "Пилинг лица",
                        20.00, 30)},
                {new Service("Лазерная шлифовка", "Лазерная " +
                        "шлифовка лица и тела", 250.00, 60)},
                {new Service("Солярий", "Солярий всего тела",
                        1.20, 1)},
                {new Service("Эпиляция", "Лазерная эпиляция всего " +
                        "тела", 100.00, 15)},
                {new Service("Маникюр", "Маникюр рук",
                        30.00, 120)},
                {new Service("Визаж", "Макияж лица",
                        45.00, 60)},
                {new Service("Плазмотерапия", "Терапия плазмой крови",
                        150.00, 50)},
        };
    }

    @Test(dataProvider = "dataForSaveService", description = "test for adding" +
            " services to db.")
    public void testSave(Service expected) throws DataBaseException {
        Integer id = service.save(expected);
        Service actual = service.find(id);
        Assert.assertEquals(actual, expected);
    }


    @DataProvider(name = "dataForDeleteService")
    public static Object[][] dataForDeleteService() {
        return new Object[][]{
                {9},
                {10},
                {11},
                {12},
                {13},
                {14},
                {15},
                {16},
        };
    }

    @Test(dataProvider = "dataForDeleteService", description = "test for " +
            "checking if deleting from BD correctly.", dependsOnMethods = "testSave")
    public void testDelete(Integer id) throws DataBaseException {
        Assert.assertTrue(service.delete(id));
    }
}
