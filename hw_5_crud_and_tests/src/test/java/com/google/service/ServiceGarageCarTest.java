package com.google.service;


import com.google.entity.Car;
import com.google.entity.Garage;
import org.junit.jupiter.api.*;

import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceGarageCarTest {
    private static final ServiceGarageCar service = new ServiceGarageCar();
    private static final int TOTAL_CAR_NUMBER = 30;
    private static final int TOTAL_GARAGE_NUMBER = 6;
    private static final String COLOR = "test color";
    private static final String FUEL = "test fuel";
    private static final String COUNTRY = "test country";
    private static final String BRAND = "test brand";
    private static final String NAME = "test name";
    private static final String ADDRESS = "test address";
    private static final int CAPACITY = 5;

    /**
     * About {@code setUp()} method:
     * <p>
     * First cycle fills service with cars,
     * second cycle fills service with garages,
     * third cycle attaches cars to garages
     * <p>
     *Now each garage has 5 car within, except last one garage.
     * <p>
     *cars with number 0, 5, 10, 15, 20 belong to garage number 0,
     * <p>
     *cars with number 1, 6, 11, 16, 21 belong to garage number 1,
     * <p>
     *cars with number 2, 7, 12, 17, 22 belong to garage number 2,
     * <p>
     *cars with number 3, 8, 13, 18, 23 belong to garage number 3,
     * <p>
     *cars with number 4, 9, 14, 19, 24 belong to garage number 4,
     * <p>
     *cars with number 25, 26, 27, 28, 29 have not garage,
     * <p>
     *garage with number 5 is empty.
     */
    @BeforeAll
    public static void setUp() {
        // fill the service with cars
        for (int i = 0; i < TOTAL_CAR_NUMBER; i++)
            service.createCar(newCar(i));

        // fill the service with garages
        for (int i = 0; i < TOTAL_GARAGE_NUMBER; i++)
            service.createGarage(newGarage(i));

        //fill garages with cars
        for (int i = 0; i < TOTAL_CAR_NUMBER - 5; i++)
            service.attachCarToGarage(service.findAllGarages().get(i % 5).getId(), service.findAllCars().get(i).getId());
    }

    @Test
    @Order(1)
    public void checkCarsAndGaragesSizesAfterUpgrading() {
        Assertions.assertEquals(service.findAllCars().size(), TOTAL_CAR_NUMBER);
    }

    @Test
    @Order(2)
    public void checkGaragesSizesAfterUpgrading() {
        Assertions.assertEquals(service.findAllGarages().size(), TOTAL_GARAGE_NUMBER);
    }

    @Test
    @Order(3)
    public void checkCarsSizesAfterDeleting() {
        service.removeCar(service.findAllCars().get(TOTAL_CAR_NUMBER - 1).getId());
        Assertions.assertEquals(service.findAllCars().size(), TOTAL_CAR_NUMBER - 1);
    }

    @Test
    @Order(4)
    public void checkGaragesSizesAfterDeleting() {
        service.removeGarage(service.findAllGarages().get(TOTAL_GARAGE_NUMBER - 1).getId());
        Assertions.assertEquals(service.findAllGarages().size(), TOTAL_GARAGE_NUMBER - 1);
    }

    @Test
    @Order(5)
    public void checkFindCarById() {
        String id = service.findAllCars().get(0).getId();
        Assertions.assertTrue(service.findCarById(id).isPresent());
    }

    @Test
    @Order(6)
    public void checkFindGarageById() {
        String id = service.findAllGarages().get(0).getId();
        Assertions.assertTrue(service.findGarageById(id).isPresent());
    }

    @Test
    @Order(7)
    public void checkFindAllGarages() {
        Assertions.assertTrue(service.findAllGarages().size() != 0);
    }

    @Test
    @Order(8)
    public void checkFindAllCars() {
        Assertions.assertTrue(service.findAllCars().size() != 0);
    }

    @Test
    @Order(9)
    public void checkFindCarByIdWithInvalidId() {
        Assertions.assertFalse(service.findCarById("fff").isPresent());
    }

    @Test
    @Order(10)
    public void checkFindGarageByIdWithInvalidId() {
        Assertions.assertFalse(service.findGarageById("fff").isPresent());
    }

    @Test
    @Order(11)
    public void checkUpgradeCar() {
        Car car = service.findAllCars().get(0);
        car.setBrand("BMW");
        service.upgradeCar(car);
        Assertions.assertEquals(service.findAllCars().get(0).getBrand(), "BMW");
    }

    @Test
    @Order(12)
    public void checkUpgradeGarage() {
        Garage garage = service.findAllGarages().get(0);
        garage.setName("VIP cars only");
        service.upgradeGarage(garage);
        Assertions.assertEquals(service.findAllGarages().get(0).getName(), "VIP cars only");
    }

    @Test
    @Order(13)
    public void checkUpgradeGarageWithInvalidData() {
        Garage garage = service.findAllGarages().get(0);
        garage.setName("-@-!\\ffd03*8");
        service.upgradeGarage(garage);
        Assertions.assertNotEquals(service.findAllGarages().get(0).getName(), "-@-!\\ffd03*8");
    }

    @Test
    @Order(14)
    public void checkUpgradeCarWithInvalidData() {
        Car car = service.findAllCars().get(0);
        car.setBrand("!@#$%^&*(files");
        service.upgradeCar(car);
        Assertions.assertNotEquals(service.findAllCars().get(0).getBrand(), "!@#$%^&*(files");
    }

    @Test
    @Order(15)
    public void readCarsInsideGarageAfterDeletingCar() {
        int size = service.findAllGarages().get(0).getCarsInside().size();
        String id = service.findAllGarages().get(0).getCarsInside().get(0).getId();
        service.removeCar(id);
        Assertions.assertEquals(service.findAllGarages().get(0).getCarsInside().size(), size - 1);
    }

    @Test
    @Order(16)
    public void checkReadAllCarsInsideGarage() {
        Assertions.assertNotNull(service.findAllGarages().get(0).getCarsInside());
    }

    @Test
    @Order(17)
    public void checkCleanGarage() {
        int size = service.findAllGarages().get(3).getCarsInside().size();
        service.cleanGarage(service.findAllGarages().get(3).getId());
        Assertions.assertNotEquals(service.findAllGarages().get(3).getCarsInside().size(), size);
    }

    @Test
    @Order(18)
    public void checkAttachCarToGarage() {
        Optional<Car> car = service.findAllCars().stream().filter(e -> e.getGarage() == null).findAny();
        if (car.isPresent()) {
            int size = service.findAllGarages().get(3).getCarsInside().size();
            service.attachCarToGarage(service.findAllGarages().get(3).getId(), car.get().getId());
            Assertions.assertEquals(service.findAllGarages().get(3).getCarsInside().size(), size + 1);
        }
    }

    private static Garage newGarage(int i) {
        Garage garage = new Garage();
        garage.setName(NAME + " " + i);
        garage.setAddress(ADDRESS + " " + i);
        garage.setCapacity(CAPACITY);
        return garage;
    }

    private static Car newCar(int i) {
        Car car = new Car();
        car.setBrand(BRAND + " " + i);
        car.setColor(COLOR + " " + i);
        car.setCountry(COUNTRY + " " + i);
        car.setFuel(FUEL + " " + i);
        return car;
    }
}