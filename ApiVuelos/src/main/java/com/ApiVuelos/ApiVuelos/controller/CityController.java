package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CityService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.State;
<<<<<<< HEAD
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
<<<<<<< HEAD
    public void add(String name_city,String iataCode){
=======
    public ResponseEntity add(String name_city, String iataCode){
>>>>>>> alekano
        try{
            if(name_city!=null && iataCode!=null){
                State state=this.stateService.getByAttributeType(iataCode);
                if(state!=null)
                {
                    City city=new City(name_city,iataCode,state);
                    return new ResponseEntity(HttpStatus.OK);
                }else
                {
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
<<<<<<< HEAD
        catch(PersistenceException e){
            e.printStackTrace();
=======
    public void add(String name_city,String iataCode,Long id_state){

        State state=this.stateService.getById(id_state);
        if(state!=null)
        {
            City city=new City(name_city,iataCode,state);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
=======
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
>>>>>>> alekano
        }

    }

    @PutMapping(value = "/update")
<<<<<<< HEAD
    public void update(City value) {
<<<<<<< HEAD
=======
    public ResponseEntity update(City value) {
>>>>>>> alekano
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
<<<<<<< HEAD

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
=======
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
>>>>>>> alekano
        }
        catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
<<<<<<< HEAD

=======
        this.cityService.removeObject(id);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }
    @GetMapping(value = "/")
    @ResponseBody
    public List<City> getAll() {
<<<<<<< HEAD
=======
    }
    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<City>> getAll() {
>>>>>>> alekano
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
<<<<<<< HEAD
=======
        List<City>cities=this.cityService.getAll();
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
        return cities;
=======
>>>>>>> alekano
    }
}
