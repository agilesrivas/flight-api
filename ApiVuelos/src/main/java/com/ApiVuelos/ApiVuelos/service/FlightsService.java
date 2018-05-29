package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GenericsRepository;
import repository.MethodsRepository;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;

@Service
public class FlightsService implements MethodsRepository {

    @Autowired
    private GenericsRepository vuelos;


    @Override
    public List<Optional> getAll() {
        return this.vuelos.findAll();
    }

    @Override
    public Optional getByAttributeType(String id) {
        return null;
    }

    @Override
    public Optional getById(Long id) {
        return this.vuelos.findById(id);
    }

    @Override
    public void newObject(Optional value) {
        this.vuelos.save(value);
    }

    @Override
    public void updateObject( Object value2) {
        EntityManager value=null;
        value.getTransaction().begin();
        //Object miAiport=value.find(Aiport,value2.getId());
        ///SETEO LOS DATOS EN OBJECT MI AIPORT
        value.getTransaction().commit();
        value.close();
    }

    @Override
    public void removeObject(Long id) {
        this.vuelos.deleteById(id);
    }


}
