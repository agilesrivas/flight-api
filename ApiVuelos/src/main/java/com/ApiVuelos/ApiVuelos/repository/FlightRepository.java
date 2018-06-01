package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query(value="SELECT fl FROM flights fl WHERE fl.date_flight=date_flight",nativeQuery = true)
    public Flight getAttribute(@Param("date_flight")String date);
}
