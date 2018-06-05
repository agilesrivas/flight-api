package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.MethodsRepository;
import com.ApiVuelos.ApiVuelos.repository.PriceRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService implements MethodsRepository<Price>{

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<Price> getAll() {
        List<Price> prices = new ArrayList<Price>();
        prices = this.priceRepository.findAll();

        return prices;
    }

    @Override
    public Price getByAttributeType(String id) {
        return null;
    }

    @Override
    public Price getById(Long id) {
        Optional<Price> priceOptional = null;
        Price price = null;

        priceOptional = this.priceRepository.findById(id);

        if(priceOptional.isPresent()) {
            price = priceOptional.get();
        }

        return price;
    }

    @Override
    public Price newObject(Price value) {
        this.priceRepository.save(value);
        return value;
    }


    @Override
    public void removeObject(Long id) {
        this.priceRepository.deleteById(id);
    }

    public List<Price> getByAttributeTypePricesOffCabin(String type_Cabin){
        List<Price> prices = new ArrayList<Price>();
        prices = this.priceRepository.getAllPricesOffCabin(type_Cabin);

        return prices;
    }
}
