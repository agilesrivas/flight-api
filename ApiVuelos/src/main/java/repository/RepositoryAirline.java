package repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAirline extends JpaRepository<Airline,Long> {

    @Query(value = "Select * from airlines where name_Airline=:name",nativeQuery = true);
    public Airline getAirlineByName(@Param("name_Airline") String name);
}
