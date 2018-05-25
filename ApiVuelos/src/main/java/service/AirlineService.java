package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RepositoryCabin;

@Service
public class AirlineService {

    @Autowired
    private RepositoryAirline airline;


    public void newAirline(Airline value){
        this.airline.save(value);
    }
    public List<Airline>getAll(){
       return this.airline.findAll();
    }

    public Airline getAirlineById(int id){

        return this.airline.findById(id);
    }
    public Airline getAirlineByName(String name){

        return this.airline.getAirlineByName(name);
    }
    public void removeAirline(int id){

        this.airline.deleteById(id);
    }


}
