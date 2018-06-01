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
<<<<<<< HEAD
    public void updateObject(Price value2) {
=======
    public void updateObject(Object value2) {
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b

    }

    @Override
    public void removeObject(Long id) {

    }
}
