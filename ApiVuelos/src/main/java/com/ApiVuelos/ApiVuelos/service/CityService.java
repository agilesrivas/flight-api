package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.CityRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.tssi.tp5.Models.model.City;

import java.util.List;
import java.util.Optional;

@Service
public class CityService implements MethodsRepository<City>{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        return this.cityRepository.findAll();
    }

    @Override
    public City getByAttributeType(String iataCode) {
       return  this.cityRepository.getAtributte(iataCode);
    }

    @Override
    public City getById(Long id){

        City city=null;
        Optional<City> cityOptional=this.cityRepository.findById(id);
        if(cityOptional.isPresent()){
            city=cityOptional.get();
        }
        return city;
    }

    @Override
    public void newObject(City value) {
        if(value!=null){
            this.cityRepository.save(value);
        }
    }

    @Override
    public void updateObject(City value2) {

    }

    @Override
    public void removeObject(Long id) {
        this.cityRepository.deleteById(id);
    }
}
