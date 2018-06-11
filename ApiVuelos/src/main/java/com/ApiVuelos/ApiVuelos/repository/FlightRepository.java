package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query( value="SELECT * FROM flights fl INNER JOIN routes r ON fl.id_Route = r.id WHERE fl.date_flight = :date_flight AND r.id = :id_Route",
            nativeQuery = true,
            name = "getAttribute")
    public Optional<Flight> getAttribute(@Param("date_flight")String date, @Param("id_Route")long id_Route);

    @Query( value="SELECT * FROM flights fl WHERE STR_TO_DATE(fl.date_flight, '%d/%m/%Y') BETWEEN STR_TO_DATE(:from_date_flight, '%d/%m/%Y') AND STR_TO_DATE(:to_date_flight, '%d/%m/%Y')",
            nativeQuery = true,
            name = "getAttributeDate")
    public List<Flight> getAttributeDate(@Param("from_date_flight")String from_Date, @Param("to_date_flight")String to_Date);
}
