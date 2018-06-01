package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query( value = "SELECT * " +
                    "FROM routes r " +
                    "INNER JOIN airports air_Begin ON r.id_Airport_Begin = air_Begin.id " +
                    "INNER JOIN airports air_End ON r.id_Airport_End = air_End.id " +
                    "WHERE air_Begin.iata = :iataAirportBegin AND air_End.iata = :iataAirportEnd",
            nativeQuery = true,
            name = "getAttributeByAirports")
    Optional<Route> getAttributeByAirports(@Param("iataAirportBegin") String iataAirporBegin, @Param("iataAirportEnd") String iataAirportEnd);
}
