package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RepositoryCabin;

@Service
public class AiportsService {

    @Autowired
    private RepositoryAiport aiports;


    public void newAirport(Airport value){
        this.aiports.save(value);
    }
    public List<Airport>getAll(){
       return this.aiports.findAll();
    }

    public Airport getAiportById(int id){

        return this.aiports.findById(id);
    }
    public void removeAiport(int id){

        this.aiports.deleteById(id);
    }


}
