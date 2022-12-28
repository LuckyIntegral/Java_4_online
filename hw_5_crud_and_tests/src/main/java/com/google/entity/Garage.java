package com.google.entity;

import java.util.ArrayList;
import java.util.List;

public class Garage extends BaseEntity {
    private String name;
    private String address;
    private int capacity;
    private final List<Car> carInside = new ArrayList<>();

    @Override
    public String toString() {
        return name + " ".repeat(14 - name.length()) +
                address + " ".repeat(14 - address.length()) +
                capacity + " cars  " + " ".repeat(3 - String.valueOf(capacity).length()) +
                carInside.size() + " cars inside  " + " ".repeat(3 - String.valueOf(carInside.size()).length()) +
                getId();
    }

    public void addCar(Car car) {
        carInside.add(car);
    }

    public void deleteCar(Car car) {
        carInside.remove(car);
    }

    public List<Car> getCarsInside() {
        return carInside;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}