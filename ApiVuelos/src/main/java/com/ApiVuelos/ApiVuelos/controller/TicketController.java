package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.TicketService;
import com.utn.tssi.tp5.Models.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "/")
    public ResponseEntity add(Flight fl,Cabin cn,Price price,Date date){
        try{
            if(fl!=null && cn!=null && price!=null && date!=null){
                Ticket tk=new Ticket(fl,cn);
                this.ticketService.newObject(tk);
                return new ResponseEntity(HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
           return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping
    public ResponseEntity getOneTicket(String date){

        try{
            Ticket tk=null;
            if(date!=null){
                tk=this.ticketService.getByAttributeType(date);
                if(tk!=null){
                    return new ResponseEntity(tk,HttpStatus.OK);
                }
            }
            else
            {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/")
    public ResponseEntity update(Ticket tk2){
        try{
            if(tk2!=null){
                this.ticketService.updateObject(tk2);
                return new ResponseEntity(HttpStatus.OK);
            }else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){
        try{
            if(id!=null){
                this.ticketService.removeObject(id);
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(PersistenceException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Ticket>> getAll() {
        List<Ticket>tkList=new ArrayList<Ticket>();
        try{
            tkList=this.ticketService.getAll();
            if(tkList.isEmpty()){
                return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
            }else
            {
                return new ResponseEntity<List<Ticket>>(tkList,HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<List<Ticket>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
