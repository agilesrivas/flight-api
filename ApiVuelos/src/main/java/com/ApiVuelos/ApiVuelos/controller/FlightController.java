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

<<<<<<< HEAD
    @PostMapping(value = "/add")
    public void add(Route route, String date_flight){
<<<<<<< HEAD
=======
    @PostMapping(value = "/")
    public ResponseEntity add(Route route, String date_flight){
>>>>>>> alekano
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
<<<<<<< HEAD

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
=======
    }

    @PutMapping(value = "/")
    public ResponseEntity update(Flight flight) {
>>>>>>> alekano
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
=======
        Route ruta=this.routeService.getById(flight.getRoute().getId());
        Flight vuelo=this.flightService.getById(flight.getId());
        if(vuelo!=null && ruta!=null)
        {
            //seteo de datos
        }
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

<<<<<<< HEAD
    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id) {
<<<<<<< HEAD
=======
    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id) {
>>>>>>> alekano
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
<<<<<<< HEAD

=======
        this.flightService.removeObject(id);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @GetMapping(value = "/")
    public List<Flight> getAll() {
<<<<<<< HEAD
=======
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Flight>> getAll() {
>>>>>>> alekano
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
<<<<<<< HEAD
        return listFlight;
=======
        return this.flightService.getAll();
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
=======
>>>>>>> alekano
    }
}
