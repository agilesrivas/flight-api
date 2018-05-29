package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.State;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StateService implements MethodsRepository<State> {

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

    }

    @Override
    public void updateObject(Object value2) {

    }

    @Override
    public void removeObject(Long id) {

    }
}
