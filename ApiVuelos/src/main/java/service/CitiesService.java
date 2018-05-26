package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GenericsRepository;
import repository.MethodsRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class CitiesService implements MethodsRepository {

    @Autowired
    private GenericsRepository city;


    @Override
    public List<Optional> getAll() {
        return this.city.findAll();
    }

    @Override
    public Optional getByAttributeType(String id) {
        return Optional.empty();
    }

    @Override
    public Optional getById(Long id) {
        return this.city.findById(id);
    }

    @Override
    public void newObject(Optional value) {
        this.city.save(value);
    }

    @Override
    public void updateObject(EntityManager value, Object value2) {
        value.getTransaction().begin();
        //Object miAiport=value.find(Aiport,value2.getId());
        ///SETEO LOS DATOS EN OBJECT MI AIPORT
        value.getTransaction().commit();
        value.close();
    }

    @Override
    public void removeObject(Long id) {
        this.city.deleteById(id);
    }
}
