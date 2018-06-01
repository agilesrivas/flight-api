package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
<<<<<<< HEAD
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
@RestController
@RequestMapping(value = "/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/add")
<<<<<<< HEAD
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
=======
    public void add() {

    }

    @PutMapping(value = "/update")
    public void update() {
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b

    }

    @DeleteMapping(value = "/remove")
<<<<<<< HEAD
    public void remove(Long id){
        try{
            this.routeService.removeObject(id);
        }catch(PersistenceException e){
            e.printStackTrace();
        }
=======
    public void remove() {
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b

    }

    @GetMapping(value = "/")
<<<<<<< HEAD
    public List<Route> getAll() {
        List<Route>rtList=new ArrayList<Route>();
        try{
            rtList=this.routeService.getAll();
        }catch(PersistenceException e){
            e.printStackTrace();
        }
        return  rtList;
=======
    public void getAll() {

>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }
}
