package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query( value = "SELECT a FROM airports a WHERE a.iata = :iata",
            nativeQuery = true,
            name = "getAttribute")
    public Airport getAttribute(@Param("iata")String iata);
}
