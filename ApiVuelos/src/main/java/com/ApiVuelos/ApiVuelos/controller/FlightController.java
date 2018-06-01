package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.FlightService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
import com.utn.tssi.tp5.Models.model.Flight;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/")
    public ResponseEntity add(Route route, String date_flight){
        try{
            if(route!=null && date_flight!=null){
                Route rt=this.routeService.getById(route.getId());
                if(rt!=null){
                    Flight flight=new Flight(rt,date_flight);
                    this.flightService.newObject(flight);
                    return new ResponseEntity(HttpStatus.OK);
                }else
                {
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                }
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

    @PutMapping(value = "/")
    public ResponseEntity update(Flight flight) {
       try{
           if(flight!=null){
               this.flightService.updateObject(flight);
               return new ResponseEntity(HttpStatus.OK);
           }else{
               return new ResponseEntity(HttpStatus.NO_CONTENT);
           }
       }
       catch (Exception e){
           return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id) {
        try{
            if(id!=null){
                this.flightService.removeObject(id);
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

    @GetMapping(value = "/")
    public ResponseEntity<List<Flight>> getAll() {
        List<Flight>listFlight=new ArrayList<Flight>();
        try{
            listFlight= this.flightService.getAll();
            if(listFlight.isEmpty()){
                return new ResponseEntity<List<Flight>>(HttpStatus.NO_CONTENT);
            }else
            {
                return new ResponseEntity<List<Flight>>(listFlight,HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<List<Flight>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
