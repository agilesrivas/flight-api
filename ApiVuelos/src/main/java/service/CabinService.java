package service;

import org.springframework.beans.factory.annotation.Autowired;
import repository.RepositoryCabin;
import org.springframework.stereotype.Service;

@Service
public class CabinService {

    @Autowired
    private RepositoryCabin cabinBd;


    public void newCabin(Cabin value){
        this.cabinBd.save(value);
    }
    public List<Cabin>getAll(){
       return this.cabinBd.findAll();
    }

    public Cabin getCabinById(int id){

        return this.cabinBd.findById(id);
    }
    public Cabin getCabinByType(String type){

        return this.cabinBd.getCabinByType(type);
    }
    public void removeCabin(int id){

        this.cabinBd.deleteById(id);
    }


}
