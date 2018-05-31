package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.RouteRepository;
import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    @Transactional
    @Override
    public void updateObject(Route value2) {
        EntityManager enty=null;
        Route route=enty.find(Route.class,value2.getId());
        route.setAirportBegin(value2.getAirportBegin());
        route.setAirportEnd(value2.getAirportEnd());
        route.setDistance(value2.getDistance());
        route.setEstimatedTime(value2.getEstimatedTime());
        enty.getTransaction().commit();
    }

    @Override
    public void removeObject(Long id) {
        this.routeRepository.deleteById(id);
    }
}
