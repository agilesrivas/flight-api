package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>{
}
