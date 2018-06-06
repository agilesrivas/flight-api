package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.AirportRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.UserRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements MethodsRepository<User>{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll()throws Exception {
        return this.userRepository.findAll();
    }

    @Override
    public User getByAttributeType(String value)throws Exception {

        return null;
    }

    public User getByAttributeTypeUser(String value, String pass)throws Exception {
        return this.userRepository.getAttribute(value,pass);
    }

    @Override
    public User getById(Long id)throws Exception {
        User user=null;
        Optional<User>useOptional=this.userRepository.findById(id);
        if(useOptional.isPresent()){
            user=useOptional.get();
        }
        return user;
    }

    @Override
    public User newObject(User value)throws Exception {
        if(value!=null){
            this.userRepository.save(value);
        }

        return value;
    }
    @Override
    public void removeObject(Long id) throws Exception{
        this.userRepository.deleteById(id);
    }
}
