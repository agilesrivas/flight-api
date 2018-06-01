package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value="SELECT tk FROM tickets tk  WHERE tk.date_flight=date_flight",nativeQuery = true)
    public Ticket getAttribute(@Param("date_flight")String  date);
}
