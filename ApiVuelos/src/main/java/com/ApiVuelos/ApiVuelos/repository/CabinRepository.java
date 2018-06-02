package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Long> {

    @Query( value="SELECT * FROM cabins c WHERE c.type = :typeCabin",
            nativeQuery = true,
            name = "getAttribute")
    public Cabin getAttribute(@Param("typeCabin")String value);

    @Query( value = "SELECT c.id, c.type_Cabin, p.price FROM cabins c INNER JOIN prices p ON c.id = p.id_cabin",
            nativeQuery = true,
            name = "getAll")
    public Cabin getAll();
}
