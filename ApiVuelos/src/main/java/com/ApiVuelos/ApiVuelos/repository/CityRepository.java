package com.ApiVuelos.ApiVuelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
