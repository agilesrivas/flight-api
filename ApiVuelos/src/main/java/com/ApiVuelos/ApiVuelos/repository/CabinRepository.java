package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Long> {

    @Query( value = "SELECT c.id, c.type_Cabin, p.price FROM cabins c INNER JOIN prices p ON c.id = p.id_Cabin",
            nativeQuery = true,
            name = "getAll")
    List<Cabin> getAll();

    @Query( value = "SELECT c.id, c.type_Cabin, p.price FROM cabins c INNER JOIN prices p ON c.id = p.id_Cabin WHERE c.id = :id",
            nativeQuery = true,
            name = "getById")
    Optional<Cabin> getById(@Param("id") long id);
}
