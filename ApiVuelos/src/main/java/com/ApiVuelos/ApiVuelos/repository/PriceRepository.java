package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>{

    @Query( value = "SELECT * FROM prices p INNER JOIN cabins c ON p.id_Cabin = c.id " +
                    "WHERE c.type_Cabin = :type_Cabin AND " +
                    "STR_TO_DATE(:date, '%d/%m/%Y') BETWEEN STR_TO_DATE(p.from_Date, '%d/%m/%Y') AND STR_TO_DATE(p.to_Date, '%d/%m/%Y')",
            nativeQuery = true,
            name = "getAllPricesOffCabinAndDate")
    Optional<Price> getPriceOfCabinAndDate(@Param("type_Cabin") String type_Cabin, @Param("date") String date);
}
