package com.google.db;

import com.google.entity.BaseEntity;
import com.google.entity.Garage;

import java.util.ArrayList;
import java.util.List;

public class DbGarage {
    private final List<Garage> garages = new ArrayList<>();
    private static DbGarage instance;

    private DbGarage() {}

    public static DbGarage getInstance() {
        if (instance == null) {
            instance = new DbGarage();
        }
        return instance;
    }

    public void create(Garage garage) {
        garage.setId(BaseEntity.generateId());
        garages.add(garage);
    }

    public List<Garage> findAll() {
        return garages;
    }

    public void delete(Garage garage) {
        garages.remove(garage);
    }
}
