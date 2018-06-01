package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.CityService;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;
    @Autowired
    private CityService cityService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity add(@RequestBody List<Airport> airports){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            for(Airport airport : airports) {
                if (!airport.validateNullEmpty()) {
                    City city = null;
                    city = this.cityService.getByAttributeType(airport.getCity().getIataCode());

                    if(city != null) {
                        airport.setCity(city);
                        this.airportService.newObject(airport);
                        status = new ResponseEntity(HttpStatus.OK);

                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    }

                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }
        }catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/")
    public ResponseEntity update(@RequestBody Airport value){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            if(value != null && !(value.validateNullEmpty())){
                City city = null;
                city = this.cityService.getByAttributeType(value.getCity().getIataCode());

                if(city != null) {
                    value.setCity(city);
                    this.airportService.updateObject(value);
                    status = new ResponseEntity(HttpStatus.OK);

                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            } else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        }catch(Exception e ){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            if(id != null && id > 0){
                this.airportService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);

            }else{
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        }catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<Airport>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            List<Airport> airports = new ArrayList<Airport>();
            airports = this.airportService.getAll();

            if (airports.isEmpty()) {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);

            } else {
                status = new ResponseEntity<List<Airport>>(airports, HttpStatus.OK);
            }
        } catch (Exception e) {
            status = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value= "/")
    public ResponseEntity getByOneAirport(@RequestParam("iata") String iata){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        Airport airport= null;

        try{
            if(iata != null && !(iata.trim().equals(""))){
                airport = this.airportService.getByAttributeType(iata);

                if(airport != null){
                    status = new ResponseEntity<Airport>(airport, HttpStatus.OK);

                }   else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }
}
