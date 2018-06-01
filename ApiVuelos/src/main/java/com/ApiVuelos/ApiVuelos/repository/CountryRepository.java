package com.ApiVuelos.ApiVuelos.repository;

<<<<<<< HEAD
import com.utn.tssi.tp5.Models.model.City;
=======
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
import com.utn.tssi.tp5.Models.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query(value="SELECT c FROM countries c WHERE c.iso=isoCode",nativeQuery = true)
    public Country getAtributte(@Param("isoCode")String isoCode);
}
