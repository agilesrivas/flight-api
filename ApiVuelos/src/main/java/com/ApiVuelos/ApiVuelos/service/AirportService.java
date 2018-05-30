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
    public List<Airport> getAll() {
        return this.airportRepository.findAll();
    }

    @Override
    public Airport getByAttributeType(String id) {
        return null;
    }

    @Override
    public Airport getById(Long id) {
        return this.airportRepository.findById(id);
        Airport airport=null;
        Optional<Airport>airportaOptional=this.airportRepository.findById(id);
        if(airportaOptional.isPresent()){
            airport=airportaOptional.get();
        }
        return airport;
    }

    @Override
    public void newObject(Airport value) {
        if(value!=null){
            this.airportRepository.save(value);
        }

    }
    @Transactional
    @Override
    public void updateObject(Object value2) {
        /*EntityManager enty=null;
        Airport airport=enty.find(Airport,value2.getId());
        //SETEAR DATOS*/
    }

    @Override
    public void removeObject(Long id) {
        this.airportRepository.deleteById(id);
    }
}
