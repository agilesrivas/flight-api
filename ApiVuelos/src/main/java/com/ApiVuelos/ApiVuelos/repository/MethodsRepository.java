package com.ApiVuelos.ApiVuelos.repository;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MethodsRepository<T>{

        public List<T>getAll();
        public T getByAttributeType(@Param("")String id);
        public T getById(Long id);
        public void newObject(T value);
        public void updateObject(Object value2);
        public void removeObject(Long id);

}
