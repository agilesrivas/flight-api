package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RepositoryCabin;

@Service
public class CitiesService {

    @Autowired
    private RepositoryCities city;


    public void newCity(Cities value){
        this.city.save(value);
    }
    public List<Cities>getAll(){
       return this.city.findAll();
    }

    public Cities getCityById(int id){

        return this.city.findById(id);
    }
    public void removeCity(int id){

        this.city.deleteById(id);
    }


}
