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

                String iataCode = city.getIataCode();
                String[] code = iataCode.split("-");

                if(code.length == 3 && code[0] != null && !(code[0].trim().equals("")) && code[1] != null && !(code[1].trim().equals("")) && code[2] != null && !(code[2].trim().equals(""))) {
                    State state = this.stateService.getByAttributeType(code[0] + "-" + code[1]);
                    city.setIataCode(code[0] + "-" + code[1] + "-" + code[2]);
                    city.setState(state);

                    if (!city.validateNullEmpty()) {
                        this.cityService.newObject(city);
                        status = new ResponseEntity(HttpStatus.OK);

                    }else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                        break;
                    }
                }else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    break;
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity update(@RequestBody City value) {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(value != null) {
                City cityDB = this.cityService.getById(value.getId());

                if(cityDB != null) {
                    String iataCode = value.getIataCode();
                    String[] code = iataCode.split("-");

                    if (code.length == 3 && code[0] != null && !(code[0].trim().equals("")) && code[1] != null && !(code[1].trim().equals("")) && code[2] != null && !(code[2].trim().equals(""))) {
                        State state = this.stateService.getByAttributeType(code[0] + "-" + code[1]);
                        value.setState(state);

                        if (!value.validateNullEmpty()) {
                            this.cityService.newObject(value);
                            status = new ResponseEntity(HttpStatus.OK);
                        }
                    }
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            if (id != null && id > 0) {
                this.cityService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        List<City>cities = new ArrayList<City>();

        try{
            cities=this.cityService.getAll();
            if(!cities.isEmpty()){
                status = new ResponseEntity<List<City>>(cities,HttpStatus.OK);
            }
        } catch(Exception e) {
            status = new ResponseEntity<List<City>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value= "/")
    public ResponseEntity getByOneCity(@RequestParam("iata") String iata){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        City city= null;

        try{
            if(iata != null && !(iata.trim().equals(""))){
                city = this.cityService.getByAttributeType(iata);

                if(city != null){
                    status = new ResponseEntity<City>(city, HttpStatus.OK);

                }
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }
}
