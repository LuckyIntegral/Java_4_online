package com.google.service;


import com.google.entity.Car;
import com.google.entity.Garage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

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
            service.createCar(carConstructor(i));

        // fill the service with garages
        for (int i = 0; i < TOTAL_GARAGE_NUMBER; i++)
            service.createGarage(garageConstructor(i));

        //fill garages with cars
        for (int i = 0; i < TOTAL_CAR_NUMBER - 5; i++)
            service.attachCarToGarage(service.findAllGarages().get(i % 5).getId(), service.findAllCars().get(i).getId());
    }

    private static Garage garageConstructor(int i) {
        Garage garage = new Garage();
        garage.setName(NAME + " " + i);
        garage.setAddress(ADDRESS + " " + i);
        garage.setCapacity(CAPACITY);
        return garage;
    }

    private static Car carConstructor(int i) {
        Car car = new Car();
        car.setBrand(BRAND + " " + i);
        car.setColor(COLOR + " " + i);
        car.setCountry(COUNTRY + " " + i);
        car.setFuel(FUEL + " " + i);
        return car;
    }
}