package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/index.html")
    public ModelAndView indexView() {
        return null;
    }

    @PostMapping(value = "/add")
    public void add(String name, String iataCode,String iso) {
        try{
            Country country = this.countryService.getByAttributeType(iso);

            if(country != null) {
                State state = new State(name, iataCode, country);
                this.stateService.newObject(state);

            }
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }

    }
    @PutMapping(value = "/update")
    public void update(State st) {
<<<<<<< HEAD
      try{
            this.stateService.updateObject(st);
      }
      catch(PersistenceException e ){
          e.printStackTrace();
      }
=======
        Country country=this.countryService.getById(st.getId());
        State value=this.stateService.getById(st.getId());
        if(value!=null && country != null){
            //seteo los daatos
        }
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
<<<<<<< HEAD
        try {
            this.stateService.removeObject(id);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }

=======
        this.stateService.removeObject(id);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @GetMapping(value ="/")
    public List<State> getAll() {
<<<<<<< HEAD
        List<State>st=new ArrayList<State>();
                try{
                    st= this.stateService.getAll();
                }
                catch(PersistenceException e){
                    e.printStackTrace();
                }

=======
        List<State>st=this.stateService.getAll();
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
        return st;
    }
}
