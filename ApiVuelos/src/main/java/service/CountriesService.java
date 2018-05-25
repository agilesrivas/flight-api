package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RepositoryCabin;
import repository.RepositoryCountries;

@Service
public class CountriesService {

    @Autowired
    private RepositoryCountries country;


    public void newCountry(Country value){
        this.country.save(value);
    }
    public List<Country>getAll(){
       return this.country.findAll();
    }

    public Country getCountryById(int id){

        return this.country.findById(id);
    }
    public void removeCountry(int id){

        this.country.deleteById(id);
    }


}
