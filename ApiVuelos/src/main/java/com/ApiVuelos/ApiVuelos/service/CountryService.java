package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.CountryRepository;
import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.utn.tssi.tp5.Models.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements MethodsRepository<Country> {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country getByAttributeType(String id) {
        return null;
    }

    @Override
    public Country getById(Long id) {
        Country country = null;
        Optional<Country> countryOptional = this.countryRepository.findById(id);

        if(countryOptional.isPresent()) {
            country = countryOptional.get();
        }

        return country;
    }

    @Override
    public void newObject(Country value) {
        this.countryRepository.save(value);
    }

    @Override
    public void updateObject(Object value2) {

    }

    @Override
    public void removeObject(Long id) {
        this.countryRepository.deleteById(id);
    }
}
