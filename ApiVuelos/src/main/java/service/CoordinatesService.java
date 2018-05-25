package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RepositoryCabin;
import repository.RepositoryCoordinates;

@Service
public class CoordinatesService {

    @Autowired
    private RepositoryCoordinates coordi;


    public void newCoordenadas(Coordinates value){
        this.coordi.save(value);
    }
    public List<Coordinates>getAll(){
       return this.coordi.findAll();
    }

    public Coordinates getCoordinatesById(int id){

        return this.coordi.findById(id);
    }
    public void removeCoordinates(int id){

        this.coordi.deleteById(id);
    }


}
