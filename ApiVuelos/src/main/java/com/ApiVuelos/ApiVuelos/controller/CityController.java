package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CityService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.State;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private StateService stateService;

    @PostMapping(value = "/add")
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
        }

    }

    @PutMapping(value = "/update")
    public void update(City value) {
        try{
            this.cityService.updateObject(value);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }

    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
        try{
            this.cityService.removeObject(id);
        }
        catch(PersistenceException e)
        {
            e.printStackTrace();
        }

    }
    @GetMapping(value = "/")
    @ResponseBody
    public List<City> getAll() {
        List<City>cities=new ArrayList<City>();
        try{
            cities=this.cityService.getAll();
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
        return cities;
    }
}
