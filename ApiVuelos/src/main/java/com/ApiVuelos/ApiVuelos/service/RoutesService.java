package com.ApiVuelos.ApiVuelos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GenericsRepository;
import repository.MethodsRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class RoutesService implements MethodsRepository {

    @Autowired
    private GenericsRepository route;


    @Override
    public List<Optional> getAll() {
        return this.route.findAll();
    }

    @Override
    public Optional getByAttributeType(String id) {
        return null;
    }

    @Override
    public Optional getById(Long id) {
        return this.route.findById(id);
    }

    @Override
    public void newObject(Optional value) {
            this.route.save(value);
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
        this.route.deleteById(id);
    }
}
