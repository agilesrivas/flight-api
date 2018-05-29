package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements MethodsRepository<Ticket> {

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public Ticket getByAttributeType(String id) {
        return null;
    }

    @Override
    public Ticket getById(Long id) {
        return null;
    }

    @Override
    public void newObject(Ticket value) {

    }

    @Override
    public void updateObject(Object value2) {

    }

    @Override
    public void removeObject(Long id) {

    }
}
