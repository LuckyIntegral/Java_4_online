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
        return "Garage{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", id='" + getId() + '\'' +
                ", carInside=" + carInside +
                '}';
    }

    public void addCar(Car car) {
        carInside.add(car);
    }

    public void deleteCar(Car car) {
        carInside.remove(car);
    }

    public List<Car> getCarInside() {
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
