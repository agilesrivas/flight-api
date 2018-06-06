package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Airport;

import java.util.List;

public interface MethodsRepository<T>{
        List<T> getAll() throws Exception;
        T getByAttributeType(String value) throws Exception;
        T getById(Long id) throws Exception;
        T newObject(T value) throws Exception;

        void removeObject(Long id) throws Exception;
}
