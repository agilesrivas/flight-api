package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GenericsRepository;
import repository.MethodsRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class StateService implements MethodsRepository {

    @Autowired
    private GenericsRepository state;


    @Override
    public List<Optional> getAll() {
        return this.state.findAll();
    }

    @Override
    public Optional getByAttributeType(String id) {
        return null;
    }

    @Override
    public Optional getById(Long id) {
        return this.state.findById(id);
    }

    @Override
    public void newObject(Optional value) {
        this.state.save(value);
    }

    @Override
    public void updateObject(EntityManager value, Object value2) {
        value.getTransaction().begin();
        //Object state=value.find(State,value2.getId());
        ///SETEO LOS DATOS EN OBJECT MI state
        value.getTransaction().commit();
        value.close();
    }

    @Override
    public void removeObject(Long id) {
        this.state.deleteById(id);
    }
}
