package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CityService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.State;
<<<<<<< HEAD
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private StateService stateService;

    @PostMapping(value = "/add")
<<<<<<< HEAD
    public void add(String name_city,String iataCode){
        try{
            State state=this.stateService.getByAttributeType(iataCode);
            if(state!=null)
            {
                City city=new City(name_city,iataCode,state);
            }

        }
        catch(PersistenceException e){
            e.printStackTrace();
=======
    public void add(String name_city,String iataCode,Long id_state){

        State state=this.stateService.getById(id_state);
        if(state!=null)
        {
            City city=new City(name_city,iataCode,state);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
        }

    }

    @PutMapping(value = "/update")
    public void update(City value) {
<<<<<<< HEAD
        try{
            this.cityService.updateObject(value);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }

=======
        State st=this.stateService.getById(value.getState().getId());
        City city=this.cityService.getById(value.getId());
        if(st !=null && city !=null){
            //Seteo los datos
        }
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
<<<<<<< HEAD
        try{
            this.cityService.removeObject(id);
        }
        catch(PersistenceException e)
        {
            e.printStackTrace();
        }

=======
        this.cityService.removeObject(id);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }
    @GetMapping(value = "/")
    @ResponseBody
    public List<City> getAll() {
<<<<<<< HEAD
        List<City>cities=new ArrayList<City>();
        try{
            cities=this.cityService.getAll();
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
=======
        List<City>cities=this.cityService.getAll();
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
        return cities;
    }
}
