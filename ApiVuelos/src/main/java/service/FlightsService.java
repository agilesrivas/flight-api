package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.GenericsRepository;
import repository.MethodsRepository;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Optional;

@Service
public class FlightsService implements MethodsRepository {

    @Autowired
    private GenericsRepository vuelos;


    @Override
    public List<Optional> getAll() {
        return null;
    }

    @Override
    public Optional getByAttributeType() {
        return Optional.empty();
    }

    @Override
    public Optional getById() {
        return Optional.empty();
    }

    @Override
    public void newObject() {

    }

    @Override
    public void updateObject() {

    }

    @Override
    public void removeObject() {

    }
}
