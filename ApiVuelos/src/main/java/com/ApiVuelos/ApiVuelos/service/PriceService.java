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
<<<<<<< HEAD
    public void updateObject(Price value2) {
=======
    public void updateObject(Object value2) {
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2

    }

    @Override
    public void removeObject(Long id) {

    }
}
