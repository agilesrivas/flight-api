package com.ApiVuelos.ApiVuelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericsRepository<T> extends JpaRepository<T,Long> {

}
