package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CabinService;
import com.ApiVuelos.ApiVuelos.service.FlightService;
import com.ApiVuelos.ApiVuelos.service.TicketService;
import com.utn.tssi.tp5.Models.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.applet.AudioClip;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private CabinService cabinService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity add(@RequestBody List<Ticket> tickets){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        Flight flight = null;
        Cabin cabin = null;

        try{
            for (Ticket ticket : tickets) {
                if(!ticket.validateNullEmpty()) {
                    flight = this.flightService.getById(ticket.getFlight().getId());
                    cabin = this.cabinService.getByAttributeType(ticket.getCabin().getName());

                    if(flight != null && cabin != null) {
                        ticket.setFlight(flight);
                        ticket.setCabin(cabin);

                        this.ticketService.newObject(ticket);
                        status = new ResponseEntity(HttpStatus.OK);
                    } else {
                        status = new ResponseEntity(HttpStatus.NO_CONTENT);
                    }
                } else {
                    status = new ResponseEntity(HttpStatus.NO_CONTENT);
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value = "/") //Este metodo buscara por el ID o EMAIL del usuario
    public ResponseEntity getByOneTicket(String date){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            Ticket tk=null;
            if(date!=null){
                tk=this.ticketService.getByAttributeType(date);

                if(tk!=null){
                    status = new ResponseEntity(tk,HttpStatus.OK);
                }
            } else {
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PutMapping(value = "/")
    public ResponseEntity update(Ticket tk2){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(tk2 != null && !(tk2.validateNullEmpty())){
                this.ticketService.updateObject(tk2);
                status = new ResponseEntity(HttpStatus.OK);
            }else{
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @DeleteMapping(value = "/")
    public ResponseEntity remove(@RequestParam("id")Long id){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(id != null && id > 0){
                this.ticketService.removeObject(id);
                status = new ResponseEntity(HttpStatus.OK);
            }
            else{
                status = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        catch(PersistenceException e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Ticket>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        List<Ticket>tkList = new ArrayList<Ticket>();

        try{
            tkList = this.ticketService.getAll();
            if(!tkList.isEmpty()){
                status = new ResponseEntity<List<Ticket>>(tkList,HttpStatus.OK);

            }else {
                status = new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
            }
        } catch(Exception e){
            status = new ResponseEntity<List<Ticket>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
