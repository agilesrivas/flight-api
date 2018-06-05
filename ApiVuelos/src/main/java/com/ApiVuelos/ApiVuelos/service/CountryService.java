package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.CountryRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements MethodsRepository<Country> {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getAll() throws Exception{
        return this.countryRepository.findAll();
    }

    @Override
    public Country getByAttributeType(String value)throws Exception {
        return this.countryRepository.getAttribute(value);
    }

    @Override
    public Country getById(Long id) throws Exception{
        Country country = null;
        Optional<Country> countryOptional = this.countryRepository.findById(id);

        if(countryOptional.isPresent()) {
            country = countryOptional.get();
        }

        return country;
    }

    @Override
    public Country newObject(Country value)throws Exception {
        this.countryRepository.save(value);
        return value;
    }


    @Override
    public void removeObject(Long id)throws Exception {
        this.countryRepository.deleteById(id);
    }
}
