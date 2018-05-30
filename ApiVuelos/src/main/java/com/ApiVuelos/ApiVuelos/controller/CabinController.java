package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.CabinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cabin")
public class CabinController {

    @Autowired
    private CabinService cabinService;

    @PostMapping(value = "/add")
    public void add(String name_cabin) {
        Cabin cab=new Cabin(name_cabin);
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
}
