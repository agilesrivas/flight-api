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

        try{
            for(Route route : routes) {
                if(!route.validateNullEmptyIdentifier()) {
                    Airport airportBegin = this.airportService.getByAttributeType(route.getAirportBegin().getIataCode());
                    Airport airportEnd = this.airportService.getByAttributeType(route.getAirportEnd().getIataCode());
                    route.setAirportBegin(airportBegin);
                    route.setAirportEnd(airportEnd);

                    if (!route.validateNullEmpty()) {
                        this.routeService.newObject(route);
                        status = new ResponseEntity(HttpStatus.OK);

                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                        break;
                    }
                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    break;
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity update(@RequestBody Route value){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(value != null){
                Route routeDB = this.routeService.getById(value.getId());

                if(routeDB != null && !(value.validateNullEmptyIdentifier())) {
                    Airport airportBegin = this.airportService.getByAttributeType(value.getAirportBegin().getIataCode());
                    Airport airportEnd = this.airportService.getByAttributeType(value.getAirportEnd().getIataCode());
                    value.setAirportBegin(airportBegin);
                    value.setAirportEnd(airportEnd);

                    if (!value.validateNullEmpty()) {
                        this.routeService.newObject(value);
                        status = new ResponseEntity(HttpStatus.OK);
                    }
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(id != null && id > 0){
                this.routeService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);

            }
        }catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        List<Route>rtList=new ArrayList<Route>();

        try{
            rtList = this.routeService.getAll();
            if(!rtList.isEmpty()){
                status = new ResponseEntity<List<Route>>(rtList,HttpStatus.OK);

            }
        }catch(Exception e){
            status = new ResponseEntity<List<Route>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value= "/")
    public ResponseEntity getByOneRoute(@RequestParam("iataAirportBegin") String iataAirportBegin, @RequestParam("iataAirportEnd") String iataAirportEnd){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(iataAirportBegin != null && !(iataAirportBegin.trim().equals("")) && iataAirportEnd != null && !(iataAirportEnd.trim().equals(""))){
                Route route = this.routeService.getByAttributeTypeRoute(iataAirportBegin, iataAirportEnd);

                if(route != null){
                    status = new ResponseEntity<Route>(route, HttpStatus.OK);
                }
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
