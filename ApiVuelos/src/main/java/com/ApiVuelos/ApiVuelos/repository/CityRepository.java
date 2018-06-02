package com.ApiVuelos.ApiVuelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query( value="SELECT * FROM cities cs WHERE cs.iata = :iataCode",
            nativeQuery = true,
            name = "getAttribute")
    public City getAttribute(@Param("iataCode")String iataCode);
}
