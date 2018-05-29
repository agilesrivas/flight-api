package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements MethodsRepository<Flight> {

    @Override
    public List<Flight> getAll() {
        return null;
    }

    @Override
    public Flight getByAttributeType(String id) {
        return null;
    }

    @Override
    public Flight getById(Long id) {
        return null;
    }

    @Override
    public void newObject(Flight value) {

    }

    @Override
    public void updateObject(Object value2) {

    }

    @Override
    public void removeObject(Long id) {

    }
}
