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
                String iataCode = airport.getIataCode().replaceAll("[^a-zA-Z0-9]","-");
                String[] code = iataCode.split("-");

                if(code.length == 4 && code[0] != null && !(code[0].trim().equals("")) && code[1] != null && !(code[1].trim().equals("")) && code[2] != null && !(code[2].trim().equals("")) && code[3] != null && !(code[3].trim().equals(""))) {
                    City city = this.cityService.getByAttributeType(code[0] + "-" + code[1] + "-" + code[2]);
                    airport.setCity(city);
                    airport.setIataCode(code[3]);

                    if (!airport.validateNullEmpty()) {
                        this.airportService.newObject(airport);
                        status = new ResponseEntity(HttpStatus.OK);

                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                        break;
                    }
                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    break;
                }
            }
        }catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity update(@RequestBody Airport value){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            if(value != null){
                Airport airportDB = this.airportService.getById(value.getId());

                if (airportDB != null) {
                    String iataCode = value.getIataCode().replaceAll("[^a-zA-Z0-9]","-");
                    String[] code = iataCode.split("-");

                    if (code.length == 4 && code[0] != null && !(code[0].trim().equals("")) && code[1] != null && !(code[1].trim().equals("")) && code[2] != null && !(code[2].trim().equals("")) && code[3] != null && !(code[3].trim().equals(""))) {
                        City city = this.cityService.getByAttributeType(code[0] + "-" + code[1] + "-" + code[2]);
                        value.setCity(city);
                        value.setIataCode(code[3]);

                        if (!value.validateNullEmpty()) {
                            this.airportService.newObject(value);
                            status = new ResponseEntity(HttpStatus.OK);
                        }
                    }
                }
            }
        }catch(Exception e ){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(id != null && id > 0){
                this.airportService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);

            }
        }catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<Airport>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            List<Airport> airports = new ArrayList<Airport>();
            airports = this.airportService.getAll();

            if (!airports.isEmpty()) {
                status = new ResponseEntity<List<Airport>>(airports, HttpStatus.OK);
            }
        } catch (Exception e) {
            status = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value= "/")
    public ResponseEntity getByOneAirport(@RequestParam("iata") String iata){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        Airport airport= null;

        try{
            if(iata != null && !(iata.trim().equals(""))){
                airport = this.airportService.getByAttributeType(iata);

                if(airport != null){
                    status = new ResponseEntity<Airport>(airport, HttpStatus.OK);
                }
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
