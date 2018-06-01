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
    public List<Country> getAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country getByAttributeType(String value) {
        return this.countryRepository.getAtributte(value);
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
    @Transactional
    @Override
    public void updateObject(Country value2) {
<<<<<<< HEAD

=======
        EntityManager enty=null;
        Country country=enty.find(Country.class,value2.getId());
        country.setName(value2.getName());
        country.setIsoCode(value2.getIsoCode());
        enty.getTransaction().commit();
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    }

    @Override
    public void removeObject(Long id) {
        this.countryRepository.deleteById(id);
    }
}
