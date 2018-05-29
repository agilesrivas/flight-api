package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface MethodsRepository{

        public List<Optional>getAll();
        public Optional getByAttributeType(@Param("")String id);
        public Optional getById(Long id);
        public void newObject(Optional value);
        public void updateObject(Object value2);
        public void removeObject(Long id);

}
