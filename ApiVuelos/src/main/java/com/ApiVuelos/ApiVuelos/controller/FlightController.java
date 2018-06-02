package com.ApiVuelos.ApiVuelos.controller;


import com.ApiVuelos.ApiVuelos.service.FlightService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
import com.utn.tssi.tp5.Models.model.Flight;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity add(@RequestBody List<Flight> flights){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        Route route = null;

        try{
            for(Flight flight : flights) {
                if (!(flight.validateNullEmpty())) {
                    route = this.routeService.getByAttributeTypeRoute(flight.getRoute().getAirportBegin().getIataCode(), flight.getRoute().getAirportEnd().getIataCode());

                    if (route != null) {
                        flight.setRoute(route);
                        this.flightService.newObject(flight);
                        status = new ResponseEntity(HttpStatus.OK);

                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    }
                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/")
    public ResponseEntity update(Flight flight) {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        Route route = null;

        try{
            if(flight != null && !(flight.validateNullEmpty())){
                route = this.routeService.getByAttributeTypeRoute(flight.getRoute().getAirportBegin().getIataCode(), flight.getRoute().getAirportEnd().getIataCode());

                this.flightService.updateObject(flight);
                status = new ResponseEntity(HttpStatus.OK);

            }else{
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id) {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            if(id != null && id > 0){
                this.flightService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);

            }else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        List<Flight>listFlight = new ArrayList<Flight>();

        try{
            listFlight= this.flightService.getAll();

            if(!listFlight.isEmpty()){
                status = new ResponseEntity<List<Flight>>(listFlight,HttpStatus.OK);

            }else {
                status = new ResponseEntity<List<Flight>>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            status = new ResponseEntity<List<Flight>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value= "/")
    public ResponseEntity getByOneFlightOnDate(@RequestParam("date") String date, @RequestParam("iataAirportBegin") String iataAirportBegin, @RequestParam("iataAirportEnd") String iataAirportEnd){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        Flight flight = null;
        Route route = null;

        try{
            if(date != null && !(date.trim().equals("")) && iataAirportBegin != null && !(iataAirportBegin.trim().equals("")) && iataAirportEnd != null && !(iataAirportEnd.trim().equals(""))){
                route = this.routeService.getByAttributeTypeRoute(iataAirportBegin, iataAirportEnd);

                if(route != null) {
                    flight = this.flightService.getByAttributeTypeDateRoute(date, route);

                    if (flight != null) {
                        status = new ResponseEntity<Flight>(flight, HttpStatus.OK);

                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    }
                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return status;
    }
}
