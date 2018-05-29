package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService implements MethodsRepository<Route> {

    @Override
    public List<Route> getAll() {
        return null;
    }

    @Override
    public Route getByAttributeType(String id) {
        return null;
    }

    @Override
    public Route getById(Long id) {
        return null;
    }

    @Override
    public void newObject(Route value) {

    }

    @Override
    public void updateObject(Object value2) {

    }

    @Override
    public void removeObject(Long id) {

    }
}
