package com.ApiVuelos.ApiVuelos.repository;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MethodsRepository<T>{
        List<T> getAll();
        T getByAttributeType(@Param("")String id);
        T getById(Long id);
        void newObject(T value);
        void updateObject(Object value2);
        void removeObject(Long id);
}
