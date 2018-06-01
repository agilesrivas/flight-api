package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Airport;
<<<<<<< HEAD
import com.utn.tssi.tp5.Models.model.Flight;
=======
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
import com.utn.tssi.tp5.Models.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value="SELECT * FROM tickets WHERE date_flight=date_flight",nativeQuery = true)
    public Ticket getAtributte(@Param("date_flight")String  date);
}
