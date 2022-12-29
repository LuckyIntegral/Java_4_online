package com.google.entity;

public class Car extends BaseEntity {
    public static final String CAR_UTIL = "Brand       Country             Fuel      Color       Garage name   Car id";
    private String brand;
    private String country;
    private String fuel;
    private String color;
    private Garage garage;

    @Override
    public String toString() {
        return brand + " ".repeat(12 - brand.length()) +
                country + " ".repeat(20 - country.length()) +
                fuel + " ".repeat(10 - fuel.length()) +
                color + " ".repeat(12 - color.length()) +
                (garage == null ? "No info       " : garage.getName() + " ".repeat(14 - garage.getName().length())) +
                getId();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public void removeGarage() {
        this.garage = null;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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