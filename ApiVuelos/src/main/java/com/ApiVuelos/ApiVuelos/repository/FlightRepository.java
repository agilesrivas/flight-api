package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Airport;
<<<<<<< HEAD
import com.utn.tssi.tp5.Models.model.Country;
=======
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
import com.utn.tssi.tp5.Models.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query(value="SELECT * FROM countries WHERE date_flight=date_flight",nativeQuery = true)
    public Flight getAtributte(@Param("date_flight")String date);
}
