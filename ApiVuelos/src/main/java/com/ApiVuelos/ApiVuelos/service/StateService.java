package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.CountryRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.StateRepository;
import com.utn.tssi.tp5.Models.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StateService implements MethodsRepository<State> {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<State> getAll() {
        return null;
    }

    @Override
    public State getByAttributeType(String id) {
        return null;
    }

    @Override
    public State getById(Long id) {
        return null;
    }

    @Override
    public void newObject(State value) {
        this.stateRepository.save(value);
    }

    @Override
    public void updateObject(Object value2) {

    }

    @Override
    public void removeObject(Long id) {

    }
}
