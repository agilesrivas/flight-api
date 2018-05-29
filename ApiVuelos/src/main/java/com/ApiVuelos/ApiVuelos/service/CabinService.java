package com.ApiVuelos.ApiVuelos.service;

import org.springframework.beans.factory.annotation.Autowired;
import repository.GenericsRepository;
import org.springframework.stereotype.Service;
import repository.MethodsRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class CabinService implements MethodsRepository {

    @Autowired
    private GenericsRepository cabinBd;


    @Override
    public List<Optional> getAll() {
        return this.cabinBd.findAll();
    }

    @Override
    public Optional getByAttributeType(String id) {
        return null;
    }

    @Override
    public Optional getById(Long id) {
        return this.cabinBd.findById(id);
    }

    @Override
    public void newObject(Optional value) {
        this.cabinBd.save(value);
        }

    @Override
    public void updateObject( Object value2) {
        EntityManager value=null;
        value.getTransaction().begin();
        //Object miAiport=value.find(Aiport,value2.getId());
        ///SETEO LOS DATOS EN OBJECT MI AIPORT
        value.getTransaction().commit();
        value.close();
    }

    @Override
    public void removeObject(Long id) {
        this.cabinBd.deleteById(id);
    }
}
