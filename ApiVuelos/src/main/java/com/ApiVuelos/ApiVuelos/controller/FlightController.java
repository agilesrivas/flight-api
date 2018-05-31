package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.FlightService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
import com.utn.tssi.tp5.Models.model.Flight;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/add")
    public void add(Route route, String date_flight){
        try{
            Route rt=this.routeService.getById(route.getId());
            if(rt!=null){
                Flight flight=new Flight(rt,date_flight);
                this.flightService.newObject(flight);
            }
        }
        catch(PersistenceException e)
        {
            e.printStackTrace();
        }

    }

    @PutMapping(value = "/update")
    public void update(Flight flight) {
       try{
           this.flightService.updateObject(flight);
       }
       catch (PersistenceException e){
           e.printStackTrace();
       }
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id) {
        try{
            this.flightService.removeObject(id);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }

    }

    @GetMapping(value = "/")
    public List<Flight> getAll() {
        List<Flight>listFlight=new ArrayList<Flight>();
        try{
            listFlight= this.flightService.getAll();
        }catch(PersistenceException e){
            e.printStackTrace();
        }
        return listFlight;
    }
}
