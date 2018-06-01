package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.CityService;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
<<<<<<< HEAD
=======
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
=======
import java.util.ArrayList;
>>>>>>> alekano
import java.util.List;

@RestController
@RequestMapping(value = "/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;
    @Autowired
    private CityService cityService;

<<<<<<< HEAD
    @PostMapping(value = "/add")
<<<<<<< HEAD
    public void add(String name_air,String iataCode,float latitud,float longitud){
=======
    @PostMapping(value = "/")
    public ResponseEntity add(@RequestParam("name_air") String name_air,@RequestParam("iata")String iataCode, @RequestParam("latitud") float latitud, @RequestParam("longitud") float longitud){
>>>>>>> alekano
        try{
            if(name_air!=null && iataCode!=null && latitud!=0 && longitud!=0){
                City value=this.cityService.getByAttributeType(iataCode);
                if(value!=null){
                    Airport airport=new Airport(name_air,iataCode,value,latitud,longitud);
                    this.airportService.newObject(airport);
                    return new ResponseEntity(HttpStatus.OK);
                }else
                {
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }
            else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/")
    public ResponseEntity update(@RequestBody Airport value){
        try
        {
            if(value!=null){
                this.airportService.updateObject(value);
                return new ResponseEntity(HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        }catch(Exception e ){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
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

<<<<<<< HEAD
    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
<<<<<<< HEAD
=======
    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){
>>>>>>> alekano
        try{
            if(id!=null){
                this.airportService.removeObject(id);
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        }catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

=======
        this.airportService.removeObject(id);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

<<<<<<< HEAD
    @GetMapping(value = "/")
    public List<Airport> getAll() {
<<<<<<< HEAD
        List<Airport> airports=null;
=======
    @GetMapping
    public ResponseEntity<List<Airport>> getAll() {
>>>>>>> alekano
        try
        {
            List<Airport> airports=new ArrayList<Airport>();
           airports=this.airportService.getAll();
           return new ResponseEntity<List<Airport>>(airports,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(value="/")
    public ResponseEntity getByOne(@RequestParam("iataCode")String iataCode){

        try
        {
            Airport ar=null;
            if(iataCode!=null){
                ar=this.airportService.getByAttributeType(iataCode);
                return new ResponseEntity(ar,HttpStatus.OK);
            }else
            {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
<<<<<<< HEAD
        return ar;
=======
        List<Airport> airports=this.airportService.getAll();
        return airports;
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
=======

>>>>>>> alekano
    }
}
