package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RepositoryCabin;
import repository.RepositoryFlights;

@Service
public class FlightsService {

    @Autowired
    private RepositoryFlights vuelos;


    public void newFlight(Flight value){

        this.vuelos.save(value);
    }
    public List<Flight>getAll(){

        return this.vuelos.findAll();
    }

    public Flight getFlightById(int id){

        return this.vuelos.findById(id);
    }

    public void removeFlight(int id){

        this.vuelos.deleteById(id);
    }


}
