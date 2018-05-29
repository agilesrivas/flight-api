package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportService implements MethodsRepository<Airport>{

    @Override
    public List<Airport> getAll() {
        return null;
    }

    @Override
    public Airport getByAttributeType(String id) {
        return null;
    }

    @Override
    public Airport getById(Long id) {
        return null;
    }

    @Override
    public void newObject(Airport value) {

    }

    @Override
    public void updateObject(Object value2) {

    }

    @Override
    public void removeObject(Long id) {

    }
}
