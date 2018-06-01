package com.ApiVuelos.ApiVuelos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MethodsRepository<T>{
        List<T> getAll();
        T getByAttributeType(String value);
        T getById(Long id);
        void newObject(T value);
        void updateObject(T value2);
        void removeObject(Long id);
}
