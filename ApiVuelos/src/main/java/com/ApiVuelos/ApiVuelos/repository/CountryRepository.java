package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
