package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.utn.tssi.tp5.Models.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping(value = "/add")
    public void add(String name, String isoCode) {
        try{
            Country country = new Country(name, isoCode);
            this.countryService.newObject(country);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }

    }

    @PutMapping(value = "/update")
    public void update(Country country){
        try{
            this.countryService.updateObject(country);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id") Long id){
        try{
            this.countryService.removeObject(id);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/")
    public List<Country> getAll() {
        List<Country> countryList=new ArrayList<Country>();
        try{
           countryList = this.countryService.getAll();
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
        return countryList;
    }
    @GetMapping
    public Country getByOneCountry(String iso){
        Country count=null;
        try{
           count= this.countryService.getByAttributeType(iso);
        }
        catch(PersistenceException e)
        {
            e.printStackTrace();
        }
        return count;
    }
}
