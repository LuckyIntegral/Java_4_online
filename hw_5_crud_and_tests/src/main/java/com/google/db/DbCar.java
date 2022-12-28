package com.google.db;

import com.google.entity.BaseEntity;
import com.google.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class DbCar {
    private final List<Car> cars = new ArrayList<>();
    private static DbCar instance;

    private DbCar() {}

    public static DbCar getInstance() {
        if (instance == null) {
            instance = new DbCar();
        }
        return instance;
    }

    public void create(Car car) {
        car.setId(BaseEntity.generateId());
        cars.add(car);
    }

    public List<Car> findAll() {
        return cars;
    }

    public void delete(Car car) {
        cars.remove(car);
    }
}
