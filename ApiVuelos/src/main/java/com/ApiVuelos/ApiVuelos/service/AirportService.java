package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.AirportRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService implements MethodsRepository<Airport>{

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Airport> getAll() throws Exception{
        return this.airportRepository.findAll();
    }

    @Override
    public Airport getByAttributeType(String value) throws Exception{
        return this.airportRepository.getAttribute(value);
    }

    @Override
    public Airport getById(Long id) throws Exception{
        Airport airport=null;
        Optional<Airport>airportaOptional=this.airportRepository.findById(id);
        if(airportaOptional.isPresent()){
            airport=airportaOptional.get();
        }
        return airport;
    }

    @Override
    public Airport newObject(Airport value) throws Exception{
        if(value!=null){
            this.airportRepository.save(value);
        }

        return value;
    }
    @Override
    public void removeObject(Long id) throws Exception{
        this.airportRepository.deleteById(id);
    }
}
