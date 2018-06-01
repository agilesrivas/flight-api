package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity add(@RequestBody List<State> states) {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            for (State state : states) {
                if (state.getName() != null  && !(state.getName().trim().equals("")) && state.getIataCode() != null  && !(state.getIataCode().trim().equals("")) && state.getCountry() != null) {
                    Country country = this.countryService.getByAttributeType(state.getCountry().getIsoCode());

                    if(country != null) {
                        state.setCountry(country);
                        this.stateService.newObject(state);

                        status = new ResponseEntity(HttpStatus.OK);

                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    }
                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }
        }
        catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }

    @PutMapping(value = "/")
    public ResponseEntity update(State st) {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            if(st!=null){
                this.stateService.updateObject(st);
                status = new ResponseEntity(HttpStatus.OK);

            }else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e ){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try {
            if(id!=null){
                this.stateService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);

            } else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }

    @GetMapping(value ="/")
    public ResponseEntity<List<State>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        List<State>st = new ArrayList<State>();

        try{
            st= this.stateService.getAll();
            if(!st.isEmpty()){
                status = new ResponseEntity<List<State>>(HttpStatus.NO_CONTENT);

            }else {
                status = new ResponseEntity<List<State>>(st,HttpStatus.OK);
            }
        }
        catch(Exception e){
            status = new ResponseEntity<List<State>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
