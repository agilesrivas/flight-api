package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.CityService;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.List;

@RestController
@RequestMapping(value = "/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;
    @Autowired
    private CityService cityService;

    @PostMapping(value = "/add")
    public void add(String name_air,String iataCode,float latitud,float longitud){
        try{
            City value=this.cityService.getByAttributeType(iataCode);

            if(value!=null){
                Airport airport=new Airport(name_air,iataCode,value,latitud,longitud);
                this.airportService.newObject(airport);
            }
        }catch(PersistenceException e){
                e.printStackTrace();
        }

    }

    @PutMapping(value = "/update")
    public void update(Airport value){
        try
        {
            this.airportService.updateObject(value);
        }catch(PersistenceException e ){
            e.printStackTrace();
        }

    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
        try{
            this.airportService.removeObject(id);
        }catch(PersistenceException e)
        {
            e.printStackTrace();
        }

    }

    @GetMapping(value = "/")
    public List<Airport> getAll() {
        List<Airport> airports=null;
        try
        {
           airports=this.airportService.getAll();
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
        return airports;
    }
    @GetMapping(value="/airport/air")
    public Airport getByOne(@RequestParam("iataCode")String iataCode){
        Airport ar=null;
        try
        {
          ar=this.airportService.getByAttributeType(iataCode);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
        return ar;
    }
}
