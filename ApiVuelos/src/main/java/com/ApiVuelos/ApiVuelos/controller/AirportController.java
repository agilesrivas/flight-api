package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;
    @Autowired
    private CityService cityService;

    @PostMapping(value = "/add")
    public void add(String name_air,String iataCode,String iso,float latitud,float longitud){
        City value=this.cityService.getByAttributeType(iso);
        if(value!=null){
            Airport airport=new Airport(name_air,iataCode,value,latitud,longitud);
            this.airportService.newObject(airport);
        }
    }

    @PutMapping(value = "/update")
    public void update(Airport value) {
        Airport ar=this.airportService.getById(value.getId());
        City value=this.cityService.getById(value.getCity().getId());
        if(ar!=null && value != null){
            //seteo los daatos
        }
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
        this.airportService.removeObject(id);
    }

    @GetMapping(value = "/")
    public List<Airport> getAll() {
        List<Airport> airports=this.airportService.getAll();
        return airports;
    }
}
