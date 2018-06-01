package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.CabinRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Cabin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Service
public class CabinService implements MethodsRepository<Cabin> {
    @Autowired
    private CabinRepository cabin;
    @Override
    public List<Cabin> getAll() {
        return this.cabin.findAll();
    }

    @Override
    public Cabin getByAttributeType(String value) {
        return this.cabin.getAttribute(value);
    }

    @Override
    public Cabin getById(Long id) {
        Cabin cabin=null;
        Optional<Cabin> cabinOptional = this.cabin.findById(id);
        if(cabinOptional.isPresent()){
            cabin=cabinOptional.get();
        }
        return cabin;
    }

    @Override
    public void newObject(Cabin value) {
        if(value!=null){
            this.cabin.save(value);
        }
    }

    @Transactional
    @Override
    public void updateObject(Cabin value2) {
        EntityManager enty = null;
        enty.getTransaction().begin();
        Cabin cabin=enty.find(Cabin.class,value2.getId());
        cabin.setName(value2.getName());
        cabin.setPriceKm(value2.getPriceKm());
        enty.getTransaction().commit();
    }

    @Override
    public void removeObject(Long id) {
        this.cabin.deleteById(id);
    }
}
