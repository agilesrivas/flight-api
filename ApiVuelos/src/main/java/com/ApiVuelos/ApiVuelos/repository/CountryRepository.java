package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query(value="SELECT * FROM countries WHERE iso=isoCode",nativeQuery = true)
    public Country getAtributte(@Param("isoCode")String isoCode);
}
