package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.CityService;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
=======
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
import java.util.List;

@RestController
@RequestMapping(value = "/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;
    @Autowired
    private CityService cityService;

    @PostMapping(value = "/add")
<<<<<<< HEAD
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

=======
    public void add(String name_air,String iataCode,String iso,float latitud,float longitud){
        City value=this.cityService.getByAttributeType(iso);
        if(value!=null){
            Airport airport=new Airport(name_air,iataCode,value,latitud,longitud);
            this.airportService.newObject(airport);
        }
    }

    @PutMapping(value = "/update")
    public void update(Airport value) {
        Airport ar = this.airportService.getById(value.getId());
        City city = this.cityService.getById(value.getCity().getId());
        if(ar!=null && city != null){
            //seteo los daatos
        }
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
<<<<<<< HEAD
        try{
            this.airportService.removeObject(id);
        }catch(PersistenceException e)
        {
            e.printStackTrace();
        }

=======
        this.airportService.removeObject(id);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @GetMapping(value = "/")
    public List<Airport> getAll() {
<<<<<<< HEAD
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
=======
        List<Airport> airports=this.airportService.getAll();
        return airports;
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }
}
