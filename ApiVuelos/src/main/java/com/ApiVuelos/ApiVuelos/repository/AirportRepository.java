package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
<<<<<<< HEAD

    @Query( value = "SELECT a FROM airports a WHERE a.iata = :iata",
            nativeQuery = true,
            name = "getAttribute")
    public Airport getAttribute(@Param("iata")String iata);
=======
    @Query(value="SELECT a FROM airports a WHERE a.iata=iataCode",nativeQuery = true)
    public Airport getAtributte(@Param("iataCode")String iata);
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
}
