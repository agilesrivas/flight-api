package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import org.springframework.stereotype.Service;
import com.utn.tssi.tp5.Models.model.City;

import java.util.List;

@Service
public class CityService implements MethodsRepository<City>{

    @Override
    public List<City> getAll() {
        return null;
    }

    @Override
    public City getByAttributeType(String id) {
        return null;
    }

    @Override
    public City getById(Long id) {
        return null;
    }

    @Override
    public void newObject(City value) {

    }

    @Override
    public void updateObject(Object value2) {

    }

    @Override
    public void removeObject(Long id) {

    }
}
