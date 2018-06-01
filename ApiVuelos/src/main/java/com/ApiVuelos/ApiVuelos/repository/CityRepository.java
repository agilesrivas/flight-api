package com.ApiVuelos.ApiVuelos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utn.tssi.tp5.Models.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
<<<<<<< HEAD

    @Query( value = "SELECT a FROM airports a WHERE a.iata = :iata",
            nativeQuery = true,
            name = "getAttribute")
    public City getAtributte(@Param("iata")String iataCode);
=======
    @Query(value="SELECT cs FROM cities cs WHERE cs.iata=iataCode",nativeQuery = true)
    public City getAtributte(@Param("iataCode")String iataCode);
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
}
