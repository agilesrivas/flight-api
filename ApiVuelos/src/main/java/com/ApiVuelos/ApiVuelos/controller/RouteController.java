package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/add")
    public void add(Airport begin ,Airport end,int distance,int time_estimed) {
        try{
            Route rt=new Route(begin,end,distance,time_estimed);
            this.routeService.newObject(rt);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
    }

    @PutMapping(value = "/update")
    public void update(Route value){
        try{
            this.routeService.updateObject(value);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }

    }

    @DeleteMapping(value = "/remove")
    public void remove(Long id){
        try{
            this.routeService.removeObject(id);
        }catch(PersistenceException e){
            e.printStackTrace();
        }

    }

    @GetMapping(value = "/")
    public List<Route> getAll() {
        List<Route>rtList=new ArrayList<Route>();
        try{
            rtList=this.routeService.getAll();
        }catch(PersistenceException e){
            e.printStackTrace();
        }
        return  rtList;
    }
}
