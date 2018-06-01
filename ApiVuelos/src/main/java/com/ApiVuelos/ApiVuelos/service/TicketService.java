package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.TicketRepository;
import com.utn.tssi.tp5.Models.model.State;
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
    public List<Ticket> getAll() {
        return this.ticketRepo.findAll();
    }

    @Override
    public Ticket getByAttributeType(String value) {
        return this.ticketRepo.getAtributte(value);
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
    @Transactional
    @Override
    public void updateObject(Ticket value2) {
<<<<<<< HEAD

=======
        EntityManager enty=null;
        Ticket tk=enty.find(Ticket.class,value2.getId());
        tk.setCabin(value2.getCabin());
        tk.setDate(value2.getDate());
        tk.setFlight(value2.getFlight());
        tk.setTotalPrice(value2.getTotalPrice());
        enty.getTransaction().commit();
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    }

    @Override
    public void removeObject(Long id) {
        this.ticketRepo.deleteById(id);
    }
}
