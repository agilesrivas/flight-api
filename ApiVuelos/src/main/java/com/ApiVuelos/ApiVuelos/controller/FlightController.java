package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.FlightService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
import com.utn.tssi.tp5.Models.model.Flight;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Route rt=this.routeService.getById(route.getId());
        if(rt!=null){
            Flight flight=new Flight(rt,date_flight);
            this.flightService.newObject(flight);
        }
    }

    @PutMapping(value = "/update")
    public void update(Flight flight) {
        Route ruta=this.routeService.getById(flight.getRoute().getId());
        Flight vuelo=this.flightService.getById(flight.getId());
        if(vuelo!=null && ruta!=null)
        {
            //seteo de datos
        }
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id) {
        this.flightService.removeObject(id);
    }

    @GetMapping(value = "/")
    public List<Flight> getAll() {
        return this.flightService.getAll();
    }
}
