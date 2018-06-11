package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.FlightRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Flight;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements MethodsRepository<Flight> {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> getAll() throws Exception{
        return this.flightRepository.findAll();
    }

    @Override
    public Flight getByAttributeType(String value)throws Exception {
        return null;
    }

    @Override
    public Flight getById(Long id) throws Exception{
        Flight flight=null;
        Optional<Flight> flightOptional=this.flightRepository.findById(id);
        if(flightOptional.isPresent()){
            flight=flightOptional.get();
        }
        return flight;
    }

    @Override
    public Flight newObject(Flight value)throws Exception {
        if(value!=null){
            this.flightRepository.save(value);
        }
        return value;
    }

    @Override
    public void removeObject(Long id) throws Exception{
        this.flightRepository.deleteById(id);
    }

    public Flight getByAttributeTypeDateRoute(String date, Route route){
        Flight flight = null;
        Optional<Flight> flightOptional = this.flightRepository.getAttribute(date, route.getId());

        if(flightOptional.isPresent()) {
            flight = flightOptional.get();
        }

        return flight;
    }

    public List<Flight> getByAttributeDate(String fromDate, String toDate){
        List<Flight> flights = this.flightRepository.getAttributeDate(fromDate, toDate);
        return flights;
    }
}
