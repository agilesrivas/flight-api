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

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            for (State state : states) {
                if (!state.validateNullEmptyIdentifier()) {
                    String iataCode = state.getIataCode();
                    String[] code = iataCode.split("-");

                    if(code.length == 2 && code[0] != null && !(code[0].trim().equals("")) && code[1] != null && !(code[1].trim().equals(""))) {
                        Country country = this.countryService.getByAttributeType(code[0]);
                        state.setCountry(country);

                        if (!state.validateNullEmpty()) {
                            this.stateService.newObject(state);
                            status = new ResponseEntity(HttpStatus.OK);

                        } else {
                            status = new ResponseEntity(HttpStatus.NO_CONTENT);
                            break;
                        }
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

    @PutMapping(value = "/")
    public ResponseEntity update(State st) {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(st != null) {
                State stateDB = this.stateService.getById(st.getId());

                if(stateDB != null) {
                    String iataCode = st.getIataCode();
                    String[] code = iataCode.split("-");

                    if(code.length == 2 && code[0] != null && !(code[0].trim().equals("")) && code[1] != null && !(code[1].trim().equals(""))) {
                        Country country = this.countryService.getByAttributeType(code[0]);
                        st.setCountry(country);

                        if (!st.validateNullEmpty()) {
                            this.stateService.newObject(st);
                            status = new ResponseEntity(HttpStatus.OK);
                        }
                    }
                }
            }
        } catch(Exception e ){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            if(id != null && id > 0){
                this.stateService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<State>> getAll() {

        ResponseEntity status = new ResponseEntity<List<State>>(HttpStatus.NO_CONTENT);
        List<State>st = new ArrayList<State>();

        try{
            st= this.stateService.getAll();
            if(!st.isEmpty()){
                status = new ResponseEntity<List<State>>(st,HttpStatus.OK);
            }
        } catch(Exception e){
            status = new ResponseEntity<List<State>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value= "/")
    public ResponseEntity getByOneState(@RequestParam("iata") String iata){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(iata != null && !(iata.trim().equals(""))){
                State state = null;
                state = this.stateService.getByAttributeType(iata);

                if(state != null){
                    status = new ResponseEntity<State>(state,HttpStatus.OK);
                }
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
