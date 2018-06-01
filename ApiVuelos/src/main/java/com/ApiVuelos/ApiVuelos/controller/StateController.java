package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CountryService countryService;

    @PostMapping(value = "/")
    public ResponseEntity add(String name, String iataCode, String iso) {
        try{
            if(name!=null && iataCode!=null && iso!=null){
                Country country = this.countryService.getByAttributeType(iso);

                if(country != null) {
                    State state = new State(name, iataCode, country);
                    this.stateService.newObject(state);
                    return  new ResponseEntity(HttpStatus.OK);
                }
                else
                {
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }else
            {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/")
    public ResponseEntity update(State st) {
      try{
          if(st!=null){
              this.stateService.updateObject(st);
              return new ResponseEntity(HttpStatus.OK);
          }else
          {
              return new ResponseEntity(HttpStatus.NO_CONTENT);
          }
      }
      catch(Exception e ){
          return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){
        try {
            if(id!=null){
                this.stateService.removeObject(id);
                return new ResponseEntity(HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value ="/")
    public ResponseEntity<List<State>> getAll() {
        List<State>st=new ArrayList<State>();
                try{
                    st= this.stateService.getAll();
                    if(st.isEmpty()){
                        return new ResponseEntity<List<State>>(HttpStatus.NO_CONTENT);
                    }else
                    {
                        return new ResponseEntity<List<State>>(st,HttpStatus.OK);
                    }
                }
                catch(Exception e){
                    return new ResponseEntity<List<State>>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
    }
}
