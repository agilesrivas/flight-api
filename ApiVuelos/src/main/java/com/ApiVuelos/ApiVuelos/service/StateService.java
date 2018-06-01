package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.CountryRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.StateRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class StateService implements MethodsRepository<State> {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public List<State> getAll() {
        return this.stateRepository.findAll();
    }

    @Override
    public State getByAttributeType(String iataCode) {
        return this.stateRepository.getAtributte(iataCode);
    }

    @Override
    public State getById(Long id) {
        State state=null;
        Optional<State> stateOptional=this.stateRepository.findById(id);
        if(stateOptional.isPresent()){
            state=stateOptional.get();
        }
        return state;
    }

    @Override
    public void newObject(State value) {
        this.stateRepository.save(value);
    }

    @Transactional
    @Override
    public void updateObject(State value2) {
        EntityManager enty=null;
        State st=enty.find(State.class,value2.getId());
        st.setIataCode(value2.getIataCode());
        st.setName(value2.getName());
        st.setCountry(value2.getCountry());
        enty.getTransaction().commit();
    }

    @Override
    public void removeObject(Long id) {
        this.stateRepository.deleteById(id);
    }
}
