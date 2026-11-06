package com.palle.service;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class GenericService<T> {

    protected Map<Integer, T> records;

    public GenericService() {
        records = new LinkedHashMap<>();
    }

    // Add record
    public void save(int id, T object) {
        records.put(id, object);
    }

    // Get record
    public T findById(int id) {
        return records.get(id);
    }

    // Get all records
    public Map<Integer, T> findAll() {
        return records;
    }

    // Delete record
    public void remove(int id) {
        records.remove(id);
    }

    // Check record exists
    public boolean exists(int id) {
        return records.containsKey(id);
    }
}
