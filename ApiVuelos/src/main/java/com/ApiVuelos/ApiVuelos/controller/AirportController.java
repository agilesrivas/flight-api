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

import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;
    @Autowired
    private CityService cityService;

    @PostMapping(value = "/")
    public ResponseEntity add(@RequestParam("name_air") String name_air,@RequestParam("iata")String iataCode, @RequestParam("latitud") float latitud, @RequestParam("longitud") float longitud){
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

    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){
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

    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAll() {
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

    }
}
