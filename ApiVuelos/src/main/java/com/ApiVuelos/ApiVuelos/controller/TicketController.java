package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.*;
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
    private PriceService priceService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity add(@RequestBody List<Ticket> tickets){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            for (Ticket ticket : tickets) {
                Flight flight = ticket.getFlight();
                Price price = ticket.getPrice();
                User user = ticket.getUser();

                if(flight != null && price != null && user != null) {
                    flight = this.flightService.getById(flight.getId());
                    price = this.priceService.getById(price.getId());
                    user = this.userService.getById(user.getId());

                    ticket.setFlight(flight);
                    ticket.setPrice(price);
                    ticket.setUser(user);
                    ticket.calculateTotalPrice();
                    ticket.setDate(flight.getDate());

                    if(!ticket.validateNullEmpty()) {
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

    @PutMapping(value = "/")
    public ResponseEntity update(Ticket tk2){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(tk2 != null){
                Ticket ticketDB = this.ticketService.getById(tk2.getId());

                if(ticketDB != null) {
                    Flight flight = tk2.getFlight();
                    Price price = tk2.getPrice();
                    User user = tk2.getUser();

                    if (flight != null && price != null && user != null) {
                        flight = this.flightService.getById(flight.getId());
                        price = this.priceService.getById(price.getId());
                        user = this.userService.getById(user.getId());

                        tk2.setFlight(flight);
                        tk2.setPrice(price);
                        tk2.setUser(user);
                        tk2.calculateTotalPrice();
                        tk2.setDate(flight.getDate());

                        if (!tk2.validateNullEmpty()) {
                            this.ticketService.newObject(tk2);
                            status = new ResponseEntity(HttpStatus.OK);

                        }
                    }
                }
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
        } catch( Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAll() {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        List<Ticket>tkList = new ArrayList<Ticket>();

        try{
            tkList = this.ticketService.getAll();
            if(!tkList.isEmpty()){
                status = new ResponseEntity<List<Ticket>>(tkList,HttpStatus.OK);
            }
        } catch(Exception e){
            status = new ResponseEntity<List<Ticket>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @GetMapping(value = "/")
    public ResponseEntity getByOneTicket(@RequestParam("idUser") Long idUser){

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try{
            if(idUser > 0){
                Ticket ticket = this.ticketService.getByUser(idUser);

                if(ticket != null){
                    status = new ResponseEntity(ticket, HttpStatus.OK);
                }
            }
        } catch(Exception e){
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
