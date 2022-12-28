package com.google.entity;

public class Car extends BaseEntity {
    private String model;
    private String country;
    private String fuel;
    private Garage garage;

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", country='" + country + '\'' +
                ", fuel='" + fuel + '\'' +
                ", id='" + getId() + '\'' +
                ", garage=" + garage.getName() +
                '}';
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}
