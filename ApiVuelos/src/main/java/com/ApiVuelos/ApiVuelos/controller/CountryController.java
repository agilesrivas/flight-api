package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.utn.tssi.tp5.Models.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/index.html")
    public ModelAndView indexView() {
        return null;
    }

    @PostMapping(value = "/add")
    public void add(String name, String isoCode) {
        Country country = new Country(name, isoCode);
        this.countryService.newObject(country);
    }

    @PutMapping(value = "/update")
    public void update() {

    }

    @DeleteMapping(value = "/remove")
    public void remove() {

    }

    @GetMapping(value = "/")
    public List<Country> getAll() {
        List<Country> countryList = this.countryService.getAll();
        return countryList;
    }
}
