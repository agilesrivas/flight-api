package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Cabin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CabinService implements MethodsRepository<Cabin> {

    @Override
    public List<Cabin> getAll() {
        return null;
    }

    @Override
    public Cabin getByAttributeType(String id) {
        return null;
    }

    @Override
    public Cabin getById(Long id) {
        return null;
    }

    @Override
    public void newObject(Cabin value) {

    }

    @Override
    public void updateObject(Object value2) {

    }

    @Override
    public void removeObject(Long id) {

    }
}
