package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/")
    public ResponseEntity add(Airport begin , Airport end, int distance, int time_estimed) {
        try{
            if(begin!=null && end!=null && distance!=0 && time_estimed!=0){
                Route rt=new Route(begin,end,distance,time_estimed);
                this.routeService.newObject(rt);
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity update(Route value){
        try{
            if(value!=null){
                this.routeService.updateObject(value);
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(Long id){
        try{
            if(id!=null){
                this.routeService.removeObject(id);
                return new ResponseEntity(HttpStatus.OK);
            }else
            {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Route>> getAll() {
        List<Route>rtList=new ArrayList<Route>();
        try{
            rtList=this.routeService.getAll();
            if(rtList.isEmpty()){
                return new ResponseEntity<List<Route>>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<List<Route>>(rtList,HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<List<Route>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
