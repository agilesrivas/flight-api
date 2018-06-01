package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/route")
public class RouteController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity add(@RequestBody List<Route> routes) {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        Airport airportBegin = null, airportEnd = null;

        try{
            for(Route route : routes) {
                if (!route.validateNullEmpty()) {
                    airportBegin = this.airportService.getByAttributeType(route.getAirportBegin().getIataCode());
                    airportEnd = this.airportService.getByAttributeType(route.getAirportEnd().getIataCode());

                    if(airportBegin != null && airportEnd != null) {
                        route.setAirportBegin(airportBegin);
                        route.setAirportEnd(airportEnd);

                        this.routeService.newObject(route);
                        status = new ResponseEntity(HttpStatus.OK);

                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    }
                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }
        }
        catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/")
    public ResponseEntity update(Route value){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        Airport airportBegin = null, airportEnd = null;

        try{
            if(value != null && !(value.validateNullEmpty())){

                airportBegin = this.airportService.getByAttributeType(value.getAirportBegin().getIataCode());
                airportEnd = this.airportService.getByAttributeType(value.getAirportEnd().getIataCode());

                if(airportBegin != null && airportEnd != null) {
                    value.setAirportBegin(airportBegin);
                    value.setAirportEnd(airportEnd);

                    this.routeService.updateObject(value);
                    status = new ResponseEntity(HttpStatus.OK);

                } else{
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            } else{
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            if(id != null && id > 0){
                this.routeService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);

            }else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        }catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        List<Route>rtList=new ArrayList<Route>();

        try{
            rtList = this.routeService.getAll();
            if(!rtList.isEmpty()){
                status = new ResponseEntity<List<Route>>(rtList,HttpStatus.OK);

            }else{
                status = new ResponseEntity<List<Route>>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            status = new ResponseEntity<List<Route>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value= "/")
    public ResponseEntity getByOneRoute(@RequestParam("iataAirportBegin") String iataAirportBegin, @RequestParam("iataAirportEnd") String iataAirportEnd){

        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        Route route= null;

        try{
            if(iataAirportBegin != null && !(iataAirportBegin.trim().equals("")) && iataAirportEnd != null && !(iataAirportEnd.trim().equals(""))){
                route = this.routeService.getByAttributeTypeRoute(iataAirportBegin, iataAirportEnd);

                if(route != null){
                    status = new ResponseEntity<Route>(route, HttpStatus.OK);

                }   else {
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
