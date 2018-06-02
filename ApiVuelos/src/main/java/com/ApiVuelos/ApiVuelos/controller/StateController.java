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
                Country country = state.getCountry();

                if (country != null && !(country.validateNullEmptyIdentifier())) {
                    country = this.countryService.getByAttributeType(country.getIsoCode());
                    state.setCountry(country);

                    if(!state.validateNullEmpty()) {
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
            if(st != null && !(st.validateNullEmpty())) {
                Country country = null;
                country = this.countryService.getByAttributeType(st.getCountry().getIsoCode());

                if(country != null) {
                    st.setCountry(country);
                    this.stateService.updateObject(st);
                    status = new ResponseEntity(HttpStatus.OK);

                }else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
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
            if(id != null && id > 0){
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

    @GetMapping
    public ResponseEntity<List<State>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        List<State>st = new ArrayList<State>();

        try{
            st= this.stateService.getAll();
            if(!st.isEmpty()){
                status = new ResponseEntity<List<State>>(st,HttpStatus.OK);

            }else {
                status = new ResponseEntity<List<State>>(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            status = new ResponseEntity<List<State>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value= "/")
    public ResponseEntity getByOneState(@RequestParam("iata") String iata){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        State state= null;

        try{
            if(iata != null && !(iata.trim().equals(""))){
                state= this.stateService.getByAttributeType(iata);

                if(state != null){
                    status = new ResponseEntity<State>(state,HttpStatus.OK);

                }   else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }else{
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }
}
