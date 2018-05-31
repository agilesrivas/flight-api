package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    @Query(value="SELECT * FROM state WHERE iata=iataCode",nativeQuery = true)
    public State getAtributte(@Param("iataCode")String iataCode);
}
