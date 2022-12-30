package com.google.controller;

import com.google.entity.Car;
import com.google.entity.Garage;
import com.google.service.ServiceGarageCar;
import com.google.service.SyntaxRules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ServiceController {
    private final ServiceGarageCar service = new ServiceGarageCar();

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose option");
        String line;
        menu();
        while ((line = reader.readLine()) != null) {
            crud(reader, line);
        }
    }

    private void crud(BufferedReader reader, String option) throws IOException {
        switch (option.trim()) {
            case "1" -> createCar(reader);
            case "2" -> upgradeCar(reader);
            case "3" -> findCar(reader);
            case "4" -> findAllCars();
            case "5" -> removeCar(reader);
            case "6" -> createGarage(reader);
            case "7" -> upgradeGarage(reader);
            case "8" -> findGarage(reader);
            case "9" -> findAllGarage();
            case "10" -> removeGarage(reader);
            case "11" -> attachCarToGarage(reader);
            case "12" -> readCarsInGarage(reader);
            case "13" -> removeCarInGarage(reader);
            case "14" -> cleanAllCarInGarage(reader);
            case "15" -> exit();
        }
        menu();
    }

    private void cleanAllCarInGarage(BufferedReader reader) throws IOException {
        System.out.println("Please enter the garage id");
        service.cleanGarage(reader.readLine().trim());
    }

    private void removeCarInGarage(BufferedReader reader) throws IOException {
        System.out.println("Please enter the garage id");
        String garageId = reader.readLine().trim();
        System.out.println("PLease enter the car id");
        String carId = reader.readLine().trim();
        service.deleteCarFromGarage(garageId, carId);
    }

    private void readCarsInGarage(BufferedReader reader) throws IOException {
        System.out.println("Please enter the garage id");
        String id = reader.readLine().trim();
        System.out.println(Car.CAR_UTIL);
        for (Car car : service.readCarsInGarage(id)) {
            System.out.println(car);
        }
    }

    private void attachCarToGarage(BufferedReader reader) throws IOException {
        System.out.println("Please enter the car id");
        String carId = reader.readLine().trim();
        System.out.println("Please enter the garage id");
        String garageId = reader.readLine().trim();
        service.attachCarToGarage(garageId, carId);
    }

    private void removeGarage(BufferedReader reader) throws IOException {
        System.out.println("PLease enter the garage id");
        service.removeGarage(reader.readLine().trim());
    }

    private  void findAllGarage() {
        System.out.println(Garage.GARAGE_UTIL);
        for (Garage garage : service.findAllGarages()) {
            System.out.println(garage);
        }
    }

    private void findGarage(BufferedReader reader) throws IOException {
        System.out.println("Please enter the garage id");
        Optional<Garage> garage = service.findGarageById(reader.readLine());
        if (garage.isPresent()) {
            System.out.println(Garage.GARAGE_UTIL);
            System.out.println(garage.get());
        } else {
            System.out.println("This garage id is not correct");
        }
    }

    private void upgradeGarage(BufferedReader reader) throws IOException {
        System.out.println("PLease enter the garage id");
        String id = reader.readLine();
        if (id == null) return;
        Optional<Garage> optional = service.findGarageById(id);
        if (optional.isEmpty()) {
            System.out.println("This id is incorrect");
        } else {
            upgradeGarageMenu();
            String option = reader.readLine().trim();
            System.out.println("PLease enter the new value");
            switch (option) {
                case "1" -> optional.get().setName(reader.readLine().trim());
                case "2" -> optional.get().setAddress(reader.readLine().trim());
                case "3" -> {
                    int number;
                    try {
                        number = Integer.parseInt(reader.readLine().trim());
                    } catch (Exception e) {
                        number = SyntaxRules.DEFAULT_GARAGE_CAPACITY;
                    }
                    optional.get().setCapacity(number);
                }
            }
            service.upgradeGarage(optional.get());
        }
    }

    private void upgradeGarageMenu() {
        System.out.println("To upgrade the name, please enter 1");
        System.out.println("To upgrade the address, please enter 2");
        System.out.println("To upgrade the capacity, please enter 3");
    }

    private void createGarage(BufferedReader reader) throws IOException {
        Garage garage = new Garage();
        System.out.println("Please enter the name");
        garage.setName(reader.readLine().trim());
        System.out.println("PLease enter the address");
        garage.setAddress(reader.readLine().trim());
        System.out.println("Please enter the capacity");
        try {
            garage.setCapacity(Integer.parseInt(reader.readLine().trim()));
        } catch (Exception e) {garage.setCapacity(-1);}
        service.createGarage(garage);
    }

    private void removeCar(BufferedReader reader) throws IOException {
        System.out.println("Please enter the car id");
        service.removeCar(reader.readLine().trim());
    }

    private void findAllCars() {
        System.out.println(Car.CAR_UTIL);
        for (Car car : service.findAllCars()) {
            System.out.println(car);
        }
    }

    private void findCar(BufferedReader reader) throws IOException {
        System.out.println("Please enter the car id");
        Optional<Car> car = service.findCarById(reader.readLine().trim());
        if (car.isEmpty()) {
            System.out.println("This id is incorrect");
        } else {
            System.out.println(Car.CAR_UTIL);
            System.out.println(car.get());
        }
    }

    private void upgradeCar(BufferedReader reader) throws IOException {
        System.out.println("Please enter the car id");
        String id = reader.readLine();
        if (id == null) return;
        Optional<Car> optional = service.findCarById(id);
        if (optional.isEmpty()) {
            System.out.println("This id is incorrect");
        } else {
            upgradeCarMenu();
            String option = reader.readLine().trim();
            System.out.println("PLease enter the new value");
            switch (option) {
                case "1" -> optional.get().setBrand(reader.readLine().trim());
                case "2" -> optional.get().setFuel(reader.readLine());
                case "3" -> optional.get().setCountry(reader.readLine());
                case "4" -> optional.get().setColor(reader.readLine());
            }
            service.upgradeCar(optional.get());
        }
    }

    private void upgradeCarMenu() {
        System.out.println("To upgrade brand, please enter 1");
        System.out.println("To upgrade fuel, please enter 2");
        System.out.println("To upgrade country, please enter 3");
        System.out.println("To upgrade color, please enter 4");
    }

    private void createCar(BufferedReader reader) throws IOException {
        Car car = new Car();
        System.out.println("Please enter the brand");
        car.setBrand(reader.readLine().trim());
        System.out.println("Please enter the country");
        car.setCountry(reader.readLine().trim());
        System.out.println("Please enter the fuel");
        car.setFuel(reader.readLine().trim());
        System.out.println("Please enter the color");
        car.setColor(reader.readLine().trim());
        service.createCar(car);
    }

    private void exit() {
        service.exit();
    }

    private void menu() {
        System.out.println();
        System.out.println("--------------------MENU--------------------");
        System.out.println("To create a car, please enter ------------- 1");
        System.out.println("To upgrade the car, please enter ---------- 2");
        System.out.println("To find the car, please enter ------------- 3");
        System.out.println("To find all cars, please enter ------------ 4");
        System.out.println("To remove the car, please enter ----------- 5");
        System.out.println("----------||---------||---------||----------");
        System.out.println("To create a garage, please enter ---------- 6");
        System.out.println("To upgrade the garage, please enter ------- 7");
        System.out.println("To find the garage, please enter ---------- 8");
        System.out.println("To find all garage, please enter ---------- 9");
        System.out.println("To remove the garage, please enter ------- 10");
        System.out.println("----------||---------||---------||----------");
        System.out.println("To attach a car to garage/ please enter -- 11");
        System.out.println("To read cars in garage, please enter ----- 12");
        System.out.println("To remove the car in garage, enter ------- 13");
        System.out.println("To remove all car in garage, enter ------- 14");
        System.out.println("To close the application, please enter --- 15");
        System.out.println("---------------------------------------------");
        System.out.println();
    }
}
