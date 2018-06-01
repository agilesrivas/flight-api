package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CityService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private StateService stateService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity add(@RequestBody List<City> cities){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            for(City city : cities) {
                if(!city.validateNullEmpty()) {
                    State state = this.stateService.getByAttributeType(city.getState().getIataCode());

                    if (state != null) {
                        city.setState(state);
                        this.cityService.newObject(city);
                        status = new ResponseEntity(HttpStatus.OK);

                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    }
                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/")
    public ResponseEntity update(City value) {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            if(value != null && value.validateNullEmpty()) {
                State state = null;
                state = this.stateService.getByAttributeType(value.getState().getIataCode());

                if(state != null) {
                    value.setState(state);
                    this.cityService.updateObject(value);
                    status = new ResponseEntity(HttpStatus.OK);

                }else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            if (id != null && id > 0) {
                this.cityService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);
            } else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        List<City>cities = new ArrayList<City>();

        try{
            cities=this.cityService.getAll();
            if(!cities.isEmpty()){
                status = new ResponseEntity<List<City>>(cities,HttpStatus.OK);

            }else {
                status = new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e) {
            status = new ResponseEntity<List<City>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value= "/")
    public ResponseEntity getByOneCity(@RequestParam("iata") String iata){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        City city= null;

        try{
            if(iata != null && !(iata.trim().equals(""))){
                city = this.cityService.getByAttributeType(iata);

                if(city != null){
                    status = new ResponseEntity<City>(city, HttpStatus.OK);

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
