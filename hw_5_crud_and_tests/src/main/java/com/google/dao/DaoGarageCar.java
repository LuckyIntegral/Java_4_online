package com.google.dao;

import com.google.db.DbCar;
import com.google.db.DbGarage;
import com.google.entity.Car;
import com.google.entity.Garage;

import java.util.List;

public class DaoGarageCar {
    DbCar dbCar = DbCar.getInstance();
    DbGarage dbGarage = DbGarage.getInstance();

    public void createCar(Car car) {
        dbCar.create(car);
    }

    public List<Car> findAllCars() {
        return dbCar.findAll();
    }

    public void removeCar(Car car) {
        findAllGarages()
                .stream()
                .filter(e -> e.getCarsInside().contains(car))
                .forEach(e -> e.deleteCar(car));
        dbCar.delete(car);
    }

    public void createGarage(Garage garage) {
        dbGarage.create(garage);
    }

    public List<Garage> findAllGarages() {
        return dbGarage.findAll();
    }

    public void removeGarage(Garage garage) {
        findAllCars()
                .stream()
                .filter(e -> e.getGarage() != null)
                .filter(e -> garage.getId().equals(e.getGarage().getId()))
                .findAny()
                .ifPresent(Car::removeGarage);
        dbGarage.delete(garage);
    }
}
