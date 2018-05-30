package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.TicketRepository;
import com.utn.tssi.tp5.Models.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements MethodsRepository<Ticket> {

    @Autowired
    private TicketRepository ticketRepo;
    @Override
    public List<Ticket> getAll() {
        return this.ticketRepo.findAll();
    }

    @Override
    public Ticket getByAttributeType(String id) {
        return null;
    }

    @Override
    public Ticket getById(Long id) {
        Ticket tick=null;
        Optional<Ticket> ticketOptional=this.ticketRepo.findById(id);
        if(ticketOptional.isPresent()){
            tick=ticketOptional.get();
        }
        return tick;
    }

    @Override
    public void newObject(Ticket value) {
        if(value!=null){
            this.ticketRepo.save(value);
        }
    }

    @Override
    public void updateObject(Object value2) {

    }

    @Override
    public void removeObject(Long id) {
        this.ticketRepo.deleteById(id);
    }
}
