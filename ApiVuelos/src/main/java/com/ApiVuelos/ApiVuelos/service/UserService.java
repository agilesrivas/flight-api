package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.AirportRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.UserRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements MethodsRepository<User>{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getByAttributeType(String value,String pass) {
        return this.userRepository.getAttribute(value,pass);
    }

    @Override
    public User getById(Long id) {
        User user=null;
        Optional<User>useOptional=this.userRepository.findById(id);
        if(useOptional.isPresent()){
            user=useOptional.get();
        }
        return user;
    }

    @Override
    public User newObject(User value) {
        if(value!=null){
            this.userRepository.save(value);
        }

        return value;
    }
    @Override
    public void removeObject(Long id) {
        this.userRepository.deleteById(id);
    }
}
