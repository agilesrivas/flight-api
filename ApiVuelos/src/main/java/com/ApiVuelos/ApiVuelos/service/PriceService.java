package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Price;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService implements MethodsRepository<Price>{

    @Override
    public List<Price> getAll() {
        return null;
    }

    @Override
    public Price getByAttributeType(String id) {
        return null;
    }

    @Override
    public Price getById(Long id) {
        return null;
    }

    @Override
    public void newObject(Price value) {

    }

    @Override
    public void updateObject(Price value2) {

    }

    @Override
    public void removeObject(Long id) {

    }
}
