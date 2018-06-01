package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.utn.tssi.tp5.Models.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
=======
import javax.servlet.http.HttpServletRequest;
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
import java.util.List;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping(value = "/" ,consumes="application/json")
    public ResponseEntity add(@RequestParam("name")String name,@RequestParam("iso")String isoCode) {
        try{
            if(name!=null && isoCode!=null){
                Country country = new Country(name, isoCode);
                this.countryService.newObject(country);
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

<<<<<<< HEAD
    @PutMapping(value = "/update")
    public void update(Country country){
<<<<<<< HEAD
=======
    @PutMapping(value = "/")
    public ResponseEntity update(Country country){
>>>>>>> alekano
        try{
            if(country!=null){
                this.countryService.updateObject(country);
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
<<<<<<< HEAD
        catch(PersistenceException e){
            e.printStackTrace();
=======
        Country value=this.countryService.getById(country.getId());
        if(value !=null){
            //Seteo los datos
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
        }
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id") Long id){
<<<<<<< HEAD
=======
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id") Long id){
>>>>>>> alekano
        try{
            if(id!=null){
                this.countryService.removeObject(id);
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
=======
        this.countryService.removeObject(id);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAll() {
        List<Country> countryList=new ArrayList<Country>();
        try{
           countryList = this.countryService.getAll();
           if(countryList!=null){
               return new ResponseEntity<List<Country>>(countryList,HttpStatus.OK);
           }else
           {
               return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
           }
        }
        catch(Exception e){
            return new ResponseEntity<List<Country>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/")
    public ResponseEntity getByOneCountry(@RequestParam("iso") String iso){
        Country count=null;
        try{
            if(iso!=null){
                count= this.countryService.getByAttributeType(iso);
                if(count!=null){
                    return new ResponseEntity(count,HttpStatus.OK);
                }
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e)
        {
           return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
