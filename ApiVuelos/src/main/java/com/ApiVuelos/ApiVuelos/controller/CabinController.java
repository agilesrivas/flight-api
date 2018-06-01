package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.CabinService;
import com.utn.tssi.tp5.Models.model.Cabin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.ArrayList;
=======
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
import java.util.List;

@RestController
@RequestMapping(value = "/cabin")
public class CabinController {

    @Autowired
    private CabinService cabinService;

<<<<<<< HEAD
    @PostMapping(value = "/add")
    public void add(String name_cabin) {
<<<<<<< HEAD
=======
    @PostMapping(value = "/")
    public ResponseEntity add(String name_cabin) {
>>>>>>> alekano
        try{
            if(name_cabin!=null){
                Cabin cab = new Cabin(name_cabin, 0);
                this.cabinService.newObject(cab);
                return new ResponseEntity(HttpStatus.OK);
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
    public ResponseEntity update(Cabin value){
        try{
            ///aca va metodo que esta haciendo Gian
            Cabin cab=this.cabinService.getById(value.getId());
            if(cab!=null && value!=null){
                cab.setName(value.getName());
                cab.setPriceKm(value.getPriceKm());
                return new ResponseEntity(HttpStatus.OK);
            }else
            {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){
        try{
            if(id!=null){
                this.cabinService.removeObject(id);
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Cabin>> getAll() {

        try
        {
            List<Cabin> cabins =this.cabinService.getAll();
            if (cabins != null) {
                return new ResponseEntity<List<Cabin>>(cabins, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity getByCabinOne(@RequestParam("typeCabin")String typeCabin){
        try{
            Cabin cabin=null;
            if(typeCabin!=null){
                cabin=this.cabinService.getByAttributeType(typeCabin);
                if(cabin!=null){
                    return new ResponseEntity(cabin,HttpStatus.OK);
                }
            }
            else
            {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
=======
        Cabin cab = new Cabin(name_cabin, 0);
        this.cabinService.newObject(cab);
    }

    @PutMapping(value = "/update")
    public void update(Cabin value) {
        Cabin cab=this.cabinService.getById(value.getId());
        if(cab!=null){
            //seteo los datos
        }

    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id) {
        this.cabinService.removeObject(id);
    }

    @GetMapping(value = "/")
    public List<Cabin> getAll() {
        List<Cabin>cabins=this.cabinService.getAll();
        return cabins;
    }
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
}
