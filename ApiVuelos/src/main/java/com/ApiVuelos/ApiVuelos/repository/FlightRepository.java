package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query( value="SELECT * FROM flights fl INNER JOIN routes r ON fl.id_Route = r.id WHERE fl.date_flight = :date_flight AND r.id = :id_Route",
            nativeQuery = true,
            name = "getAttribute")
    public Optional<Flight> getAttribute(@Param("date_flight")String date, @Param("id_Route")long id_Route);
}
