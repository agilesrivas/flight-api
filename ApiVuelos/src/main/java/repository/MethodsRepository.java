package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MethodsRepository{

        public List<Optional>getAll();
        public Optional getByAttributeType();
        public Optional getById();
        public void newObject();
        public void updateObject();
        public void removeObject();

}
