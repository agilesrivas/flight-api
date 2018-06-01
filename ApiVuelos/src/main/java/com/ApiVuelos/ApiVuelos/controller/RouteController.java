package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
<<<<<<< HEAD
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

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
@RestController
@RequestMapping(value = "/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

<<<<<<< HEAD
    @PostMapping(value = "/add")
<<<<<<< HEAD
    public void add(Airport begin ,Airport end,int distance,int time_estimed) {
=======
    @PostMapping(value = "/")
    public ResponseEntity add(Airport begin , Airport end, int distance, int time_estimed) {
>>>>>>> alekano
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
<<<<<<< HEAD
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
=======
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
>>>>>>> alekano

        }catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/")
<<<<<<< HEAD
<<<<<<< HEAD
    public List<Route> getAll() {
=======
    public ResponseEntity<List<Route>> getAll() {
>>>>>>> alekano
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
<<<<<<< HEAD
        return  rtList;
=======
    public void getAll() {

>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
=======
>>>>>>> alekano
    }
}
