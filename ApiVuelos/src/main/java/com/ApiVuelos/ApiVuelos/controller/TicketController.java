package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.TicketService;
import com.utn.tssi.tp5.Models.model.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/add")
    public void add(Flight fl,Cabin cn,Price price,Date date){
        try{
            Ticket tk=new Ticket(fl,cn);
            this.ticketService.newObject(tk);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }

    }
    @GetMapping(value="/ticket")
    public Ticket getOneTicket(String date){
        Ticket tk=null;
        try{
            tk=this.ticketService.getByAttributeType(date);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
        return tk;
    }

    @PutMapping(value = "/update")
    public void update(Ticket tk2){
        try{
            this.ticketService.updateObject(tk2);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
    }

    @DeleteMapping(value = "/remove")
    public void remove(@RequestParam("id")Long id){
        try{
            this.ticketService.removeObject(id);
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }

    }

    @GetMapping(value = "/")
    public List<Ticket> getAll() {
        List<Ticket>tkList=new ArrayList<Ticket>();
        try{
            tkList=this.ticketService.getAll();
        }
        catch(PersistenceException e){
            e.printStackTrace();
        }
        return tkList;
    }
}
