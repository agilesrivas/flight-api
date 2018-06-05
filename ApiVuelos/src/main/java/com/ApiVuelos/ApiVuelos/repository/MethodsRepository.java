package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Airport;

import java.util.List;

public interface MethodsRepository<T>{
        List<T> getAll();
        T getByAttributeType(String value);
        T getById(Long id);
        T newObject(T value);

        void removeObject(Long id);
}
