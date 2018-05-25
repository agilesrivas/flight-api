package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepositoryCabin extends JpaRepository<Cabin,Long> {

    @Query(value = "Select * from cabins where type_Cabin=:type",nativeQuery = true);
    public Cabin getCabinByType(@Param("type_Cabin") String type);

    @Query(value="Update cabins set type_Cabin=? where id=:id",nativeQuery = true);
    public void update(@Param("id")int id);
}
