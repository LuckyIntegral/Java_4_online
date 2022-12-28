package com.google.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class BaseEntity {
    private static final Set<String> ids = new HashSet<>();
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String generateId() {
        String id;
        do {
            id = UUID.randomUUID().toString().substring(0,8);
        } while (ids.contains(id));
        ids.add(id);
        return id;
    }
}