package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RepositoryCabin;

@Service
public class AircraftsService {

    @Autowired
    private RepositoryAircrafts aircrafts;


    public void newAircrafts(Aircrafts value){
        this.aircrafts.save(value);
    }
    public List<Aircrafts>getAll(){
       return this.aircrafts.findAll();
    }

    public Aircrafts getAircraftsById(int id){

        return this.aircrafts.findById(id);
    }
    public Aircrafts getAircraftsByName(String name){

        return null;
    }
    public void removeAircrafts(int id){

        this.aircrafts.deleteById(id);
    }


}
