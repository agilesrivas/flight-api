package com.ApiVuelos.ApiVuelos.repository;

import com.utn.tssi.tp5.Models.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query( value="SELECT * FROM tickets tk INNER JOIN users u ON tk.id_User = u.id WHERE u.id = :id_User",
            nativeQuery = true,
            name = "getAttribute")
    public Optional<Ticket> getAttribute(@Param("id_User")Long idUser);
}
