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
        try{
            for(City city : cities) {
                if (city.getName() != null && !(city.getName().trim().equals("")) && city.getIataCode() != null && !(city.getIataCode().trim().equals(""))) {
                    State state = this.stateService.getByAttributeType(city.getIataCode());

                    if (state != null) {
                        this.cityService.newObject(city);
                        return new ResponseEntity(HttpStatus.OK);
                    } else {
                        return new ResponseEntity(HttpStatus.NO_CONTENT);
                    }
                } else {
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/update")
    public ResponseEntity update(City value) {
        try{
            if(value!=null){
                this.cityService.updateObject(value);
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return  new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity remove(@RequestParam("id")Long id){
        try {
            if (id != null) {
                this.cityService.removeObject(id);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<City>> getAll() {
        List<City>cities=new ArrayList<City>();
        try{
            cities=this.cityService.getAll();
            if(cities!=null){
                return new ResponseEntity<List<City>>(cities,HttpStatus.OK);
            }else
            {
                return new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity<List<City>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
