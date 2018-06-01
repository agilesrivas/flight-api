package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.FlightService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
import com.utn.tssi.tp5.Models.model.Flight;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import javax.persistence.PersistenceException;
import java.util.ArrayList;
=======
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
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
<<<<<<< HEAD
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

=======
        Route rt=this.routeService.getById(route.getId());
        if(rt!=null){
            Flight flight=new Flight(rt,date_flight);
            this.flightService.newObject(flight);
        }
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @PutMapping(value = "/update")
    public void update(Flight flight) {
<<<<<<< HEAD
       try{
           this.flightService.updateObject(flight);
       }
       catch (PersistenceException e){
           e.printStackTrace();
       }
=======
        Route ruta=this.routeService.getById(flight.getRoute().getId());
        Flight vuelo=this.flightService.getById(flight.getId());
        if(vuelo!=null && ruta!=null)
        {
            //seteo de datos
        }
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id) {
<<<<<<< HEAD
        try{
            this.flightService.removeObject(id);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }

=======
        this.flightService.removeObject(id);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @GetMapping(value = "/")
    public List<Flight> getAll() {
<<<<<<< HEAD
        List<Flight>listFlight=new ArrayList<Flight>();
        try{
            listFlight= this.flightService.getAll();
        }catch(PersistenceException e){
            e.printStackTrace();
        }
        return listFlight;
=======
        return this.flightService.getAll();
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }
}
