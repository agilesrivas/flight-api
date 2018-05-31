package com.ApiVuelos.ApiVuelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Query(value="SELECT * FROM airports WHERE iata=iataCode",nativeQuery = true)
    public City getAtributte(@Param("iataCode")String iataCode);
}
