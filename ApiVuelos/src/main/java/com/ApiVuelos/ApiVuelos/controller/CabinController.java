package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.CabinService;
import com.utn.tssi.tp5.Models.model.Cabin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cabin")
public class CabinController {

    @Autowired
    private CabinService cabinService;

    @PostMapping(value = "/add")
    public void add(String name_cabin) {
        try{
            Cabin cab = new Cabin(name_cabin, 0);
            this.cabinService.newObject(cab);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
    }

    @PutMapping(value = "/update")
    public void update(Cabin value){
        try{
            ///aca va metodo que esta haciendo Gian
            Cabin cab=this.cabinService.getById(value.getId());
            if(cab!=null){
                cab.setName(value.getName());
                cab.setPriceKm(value.getPriceKm());
            }
        }
        catch(PersistenceException e)
        {
            e.printStackTrace();
        }


    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
        try{
            this.cabinService.removeObject(id);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/")
    public List<Cabin> getAll(){
        List<Cabin>cabins=new ArrayList<Cabin>();
        try{
           cabins=this.cabinService.getAll();
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
        return cabins;
    }
    @GetMapping(value="/Cabin")
    public Cabin getByCabinOne(@RequestParam("typeCabin")String typeCabin){
        Cabin cabin=null;
        try{
            cabin=this.cabinService.getByAttributeType(typeCabin);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
        return cabin;
    }
}
