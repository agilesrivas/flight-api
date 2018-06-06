package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.utn.tssi.tp5.Models.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping(value = "/" , consumes = "application/json")
    public ResponseEntity add(@RequestBody List<Country> countries) {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            for(Country country : countries) {
                if (!country.validateNullEmpty()) {
                    this.countryService.newObject(country);
                    status = new ResponseEntity(HttpStatus.OK);

                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    break;
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity update(@RequestBody Country country){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(country != null && !(country.validateNullEmpty())) {
                Country countryDB = this.countryService.getById(country.getId());

                if(countryDB != null) {
                    this.countryService.newObject(country);
                    status = new ResponseEntity(HttpStatus.OK);
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id") Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(id != null && id > 0){
                this.countryService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        List<Country> countryList = new ArrayList<Country>();

        try{
           countryList = this.countryService.getAll();
           if(!countryList.isEmpty()){
               status = new ResponseEntity<List<Country>>(countryList,HttpStatus.OK);
           }
        } catch(Exception e){
            status = new ResponseEntity<List<Country>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value= "/")
    public ResponseEntity getByOneCountry(@RequestParam("iso") String iso){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        Country country = null;

        try{
            if(iso != null && !(iso.trim().equals(""))){
                country= this.countryService.getByAttributeType(iso);

                if(country != null){
                    status = new ResponseEntity<Country>(country,HttpStatus.OK);

                }
            }
        } catch(Exception e) {
           status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
