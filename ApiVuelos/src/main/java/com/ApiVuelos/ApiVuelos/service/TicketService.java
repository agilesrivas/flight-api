package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.TicketRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Flight;
import com.utn.tssi.tp5.Models.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements MethodsRepository<Ticket> {

    @Autowired
    private TicketRepository ticketRepo;
    @Override
    public List<Ticket> getAll()throws Exception {
        return this.ticketRepo.findAll();
    }

    @Override
    public Ticket getByAttributeType(String value)throws Exception {
        return null;

    }

    @Override
    public Ticket getById(Long id) throws Exception{
        Ticket tick=null;
        Optional<Ticket> ticketOptional=this.ticketRepo.findById(id);
        if(ticketOptional.isPresent()){
            tick=ticketOptional.get();
        }
        return tick;
    }

    @Override
    public Ticket newObject(Ticket value)throws Exception {
        if(value!=null){
            this.ticketRepo.save(value);
        }
        return value;
    }

    @Override
    public void removeObject(Long id)throws Exception {
        this.ticketRepo.deleteById(id);
    }

    public Ticket getByUser(Long id) {
        Optional<Ticket> ticketOptional= this.ticketRepo.getAttribute(id);
        Ticket ticket= null;

        if(ticketOptional.isPresent()) {
            ticket = ticketOptional.get();
        }

        return ticket;
    }
}
