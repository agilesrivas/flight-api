package com.ApiVuelos.ApiVuelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query( value = "SELECT a FROM airports a WHERE a.iata = :iata",
            nativeQuery = true,
            name = "getAttribute")
    public City getAtributte(@Param("iata")String iataCode);
}
