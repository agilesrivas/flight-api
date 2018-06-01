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
<<<<<<< HEAD
    @PutMapping(value = "/update")
    public void update(State st) {
<<<<<<< HEAD
=======
    @PutMapping(value = "/")
    public ResponseEntity update(State st) {
>>>>>>> alekano
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
=======
        Country country=this.countryService.getById(st.getId());
        State value=this.stateService.getById(st.getId());
        if(value!=null && country != null){
            //seteo los daatos
        }
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

<<<<<<< HEAD
    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
<<<<<<< HEAD
=======
    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){
>>>>>>> alekano
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
<<<<<<< HEAD

=======
        this.stateService.removeObject(id);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @GetMapping(value ="/")
    public List<State> getAll() {
<<<<<<< HEAD
=======
    }

    @GetMapping(value ="/")
    public ResponseEntity<List<State>> getAll() {
>>>>>>> alekano
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
<<<<<<< HEAD

=======
        List<State>st=this.stateService.getAll();
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
        return st;
=======
>>>>>>> alekano
    }
}
