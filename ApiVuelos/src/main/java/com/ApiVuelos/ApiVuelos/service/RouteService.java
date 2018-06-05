package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.RouteRepository;
import com.utn.tssi.tp5.Models.model.Airport;
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
    public Route newObject(Route value) {
        if(value!=null){
            this.routeRepository.save(value);
        }
        return value;
    }


    @Override
    public void removeObject(Long id) {
        this.routeRepository.deleteById(id);
    }

    public Route getByAttributeTypeRoute(String iataAirportBegin, String iataAirportEnd) {
        Route route = null;
        Optional<Route> routeOptional = this.routeRepository.getAttributeByAirports(iataAirportBegin, iataAirportEnd);

        if(routeOptional.isPresent()) {
            route = routeOptional.get();
        }

        return route;
    }
}
