package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Long> {

    @Query( value="SELECT * FROM cabins c WHERE c.type_Cabin = :typeCabin",
            nativeQuery = true,
            name = "getAttribute")
    public Cabin getAttribute(@Param("typeCabin")String value);

}
