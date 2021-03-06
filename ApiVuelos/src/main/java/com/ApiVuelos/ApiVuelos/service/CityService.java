package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.CityRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class CityService implements MethodsRepository<City>{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAll() throws Exception{
        return this.cityRepository.findAll();
    }

    @Override
    public City getByAttributeType(String iataCode) throws Exception{
       return  this.cityRepository.getAttribute(iataCode);
    }

    @Override
    public City getById(Long id)throws Exception{

        City city=null;
        Optional<City> cityOptional=this.cityRepository.findById(id);
        if(cityOptional.isPresent()){
            city=cityOptional.get();
        }
        return city;
    }

    @Override
    public City newObject(City value)throws Exception {
        if(value!=null){
            this.cityRepository.save(value);
        }
        return value;
    }


    @Override
    public void removeObject(Long id) throws Exception{
        this.cityRepository.deleteById(id);
    }
}
