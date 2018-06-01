package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.RouteRepository;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService implements MethodsRepository<Route> {

    @Autowired
    private RouteRepository routeRepository;
    @Override
    public List<Route> getAll() {
        return this.routeRepository.findAll();
    }

    @Override
    public Route getByAttributeType(String id) {
        return null;
    }

    @Override
    public Route getById(Long id) {
        Route route=null;
        Optional<Route> routeOptional=this.routeRepository.findById(id);
        if(routeOptional.isPresent()){
            route=routeOptional.get();
        }
        return route;
    }

    @Override
    public void newObject(Route value) {
        if(value!=null){
            this.routeRepository.save(value);
        }
    }

    @Override
    public void updateObject(Route value2) {

    }

    @Override
    public void removeObject(Long id) {
        this.routeRepository.deleteById(id);
    }
}
