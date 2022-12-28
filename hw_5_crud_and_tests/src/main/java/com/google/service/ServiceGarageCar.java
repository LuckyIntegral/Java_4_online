package com.google.service;

import com.google.dao.DaoGarageCar;
import com.google.entity.Car;
import com.google.entity.Garage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceGarageCar {
    private final SyntaxRules rules = new SyntaxRules();
    private final DaoGarageCar dao = new DaoGarageCar();

    public void exit() {
        System.exit(0);
    }

    public void attachCarToGarage(String garageId, String carId) {
        Optional<Car> car = findCarById(carId);
        if (car.isEmpty()) {
            System.out.println("This car id is incorrect");
            return;
        }
        Optional<Garage> garage = findGarageById(garageId);
        if (garage.isEmpty()) {
            System.out.println("This garage id is incorrect");
            return;
        }
        car.get().setGarage(garage.get());
        garage.get().addCar(car.get());
    }

    public void cleanGarage(String id) {
        Optional<Garage> garage = findGarageById(id);
        if (garage.isEmpty()) {
            System.out.println("This id is incorrect");
        } else {
            garage.get().getCarsInside().forEach(Car::removeGarage);
            garage.get().getCarsInside().forEach(e -> garage.get().deleteCar(e));
        }
    }

    public void deleteCarFromGarage(String garageId, String carId) {
        Optional<Garage> garage = findGarageById(garageId);
        if (garage.isEmpty()) {
            System.out.println("This garage id is incorrect");
            return;
        }
        Optional<Car> car = findCarById(carId);
        if (car.isEmpty()) {
            System.out.println("This car id is incorrect");
            return;
        }
        garage.get()
                .getCarsInside()
                .removeIf(e -> e.getId().equals(car.get().getId()));
        car.get().removeGarage();
    }

    public List<Car> readCarsInGarage(String id) {
        Optional<Garage> garage = findGarageById(id);
        if (garage.isPresent()) {
            return garage.get().getCarsInside();
        } else {
            System.out.println("This id is incorrect");
            return new ArrayList<>();
        }
    }

    public void removeGarage(String id) {
        Optional<Garage> optional = findGarageById(id);
        optional.ifPresent(dao::removeGarage);
    }

    public void upgradeGarageName(String id, String name) {
        if (name == null || rules.garageNameIsInvalid(name)) {
            System.out.println("This name is incorrect");
            return;
        }
        Optional<Garage> optional = findGarageById(id);
        if (optional.isPresent()) {
            optional.get().setName(name);
        } else {
            System.out.println("This id is incorrect");
        }
    }

    public void upgradeGarageAddress(String id, String address) {
        if (address == null || rules.garageAddressIsInvalid(address)) {
            System.out.println("This address is incorrect");
            return;
        }
        Optional<Garage> optional = findGarageById(id);
        if (optional.isPresent()) {
            optional.get().setAddress(address);
        } else {
            System.out.println("This address is incorrect");
        }
    }

    public void upgradeGarageCapacity(String id, int capacity) {
        if (rules.garageCapacityIsInvalid(capacity)) {
            System.out.println("This capacity is incorrect");
            return;
        }
        Optional<Garage> optional = findGarageById(id);
        if (optional.isPresent()) {
            optional.get().setCapacity(capacity);
        } else {
            System.out.println("This capacity is incorrect");
        }
    }

    public List<Garage> findAllGarages() {
        return dao.findAllGarages();
    }

    public Optional<Garage> findGarageById(String id) {
        return dao.findAllGarages()
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public void createGarage(Garage garage) {
        if (garage == null) {
            System.out.println("Something went wrong, please try again");
            return;
        }
        if (rules.garageNameIsInvalid(garage.getName())) {
            System.out.println("This name is incorrect");
            garage.setName(SyntaxRules.DEFAULT_GARAGE_NAME);
        }
        if (rules.garageAddressIsInvalid(garage.getAddress())) {
            System.out.println("This address is incorrect");
            garage.setAddress(SyntaxRules.DEFAULT_GARAGE_ADDRESS);
        }
        if (rules.garageCapacityIsInvalid(garage.getCapacity())) {
            System.out.println("This capacity is incorrect");
            garage.setCapacity(SyntaxRules.DEFAULT_GARAGE_CAPACITY);
        }
        dao.createGarage(garage);
    }

    public void removeCar(String id) {
        Optional<Car> optional = findCarById(id);
        optional.ifPresent(dao::removeCar);
    }

    public void upgradeCarBrand(String id, String brand) {
        if (brand == null || rules.carBrandIsInvalid(brand)) {
            System.out.println("This brand name is incorrect");
            return;
        }
        Optional<Car> optional = findCarById(id);
        if (optional.isPresent()) {
            optional.get().setBrand(brand);
        } else {System.out.println("This id is incorrect");}
    }

    public void upgradeCarCountry(String id, String country) {
        if (country == null || rules.carCountryIsInvalid(country)) {
            System.out.println("This country name is incorrect");
            return;
        }
        Optional<Car> optional = findCarById(id);
        if (optional.isPresent()) {
            optional.get().setCountry(country);
        } else {System.out.println("This id is incorrect");}
    }

    public void upgradeCarFuel(String id, String fuel) {
        if (fuel == null || rules.carFuelIsInvalid(fuel)) {
            System.out.println("This fuel is incorrect");
            return;
        }
        Optional<Car> optional = findCarById(id);
        if (optional.isPresent()) {
            optional.get().setFuel(fuel);
        } else {System.out.println("This id is incorrect");}
    }

    public void upgradeCarColor(String id, String color) {
        if (color == null || rules.carColorIsInvalid(color)) {
            System.out.println("This color is incorrect");
            return;
        }
        Optional<Car> optional = findCarById(id);
        if (optional.isPresent()) {
            optional.get().setColor(color);
        } else {System.out.println("This id is incorrect");}
    }

    public List<Car> findAllCars() {
        return dao.findAllCars();
    }

    public Optional<Car> findCarById(String id) {
        return dao.findAllCars()
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public void createCar(Car car) {
        if (car == null) {
            System.out.println("Something went wrong, please try again!");
            return;
        }
        if (rules.carBrandIsInvalid(car.getBrand())) {
            System.out.println("This brand is incorrect");
            car.setBrand(SyntaxRules.DEFAULT_CAR_BRAND);
        }
        if (rules.carCountryIsInvalid(car.getCountry())) {
            System.out.println("This country is incorrect");
            car.setCountry(SyntaxRules.DEFAULT_CAR_COUNTRY);
        }
        if (rules.carFuelIsInvalid(car.getFuel())) {
            System.out.println("This fuel is incorrect");
            car.setFuel(SyntaxRules.DEFAULT_CAR_FUEL);
        }
        if (rules.carColorIsInvalid(car.getColor())) {
            System.out.println("This color is incorrect");
            car.setColor(SyntaxRules.DEFAULT_CAR_COLOR);
        }
        dao.createCar(car);
    }
}