package service;

import org.springframework.beans.factory.annotation.Autowired;
import repository.GenericsRepository;
import org.springframework.stereotype.Service;
import repository.MethodsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CabinService implements MethodsRepository {

    @Autowired
    private GenericsRepository cabinBd;


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
