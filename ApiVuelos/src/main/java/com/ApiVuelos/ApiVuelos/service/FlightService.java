package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.FlightRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements MethodsRepository<Flight> {

    @Autowired
    private FlightRepository flight;
    @Override
    public List<Flight> getAll() {
        return this.flight.findAll();
    }

    @Override
    public Flight getByAttributeType(String value) {
        return this.flight.getAtributte(value);
    }

    @Override
    public Flight getById(Long id) {
        Flight flight=null;
        Optional<Flight> flightOptional=this.flight.findById(id);
        if(flightOptional.isPresent()){
            flight=flightOptional.get();
        }
        return flight;
    }

    @Override
    public void newObject(Flight value) {
        if(value!=null){
            this.flight.save(value);
        }
    }

    @Override
    public void updateObject(Flight value2) {
<<<<<<< HEAD

=======
        EntityManager enty=null;
        Flight flight=enty.find(Flight.class,value2.getId());
        flight.setRoute(value2.getRoute());
        flight.setDate(value2.getDate());
        enty.getTransaction().commit();
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    }

    @Override
    public void removeObject(Long id) {
        this.flight.deleteById(id);
    }
}
