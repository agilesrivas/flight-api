package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.PriceService;
import com.utn.tssi.tp5.Models.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/price")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping(value = "/add")
    public void add(){
    }

    @PutMapping(value = "/update")
    public void update() {
    }

    @DeleteMapping(value = "/remove")
    public void remove() {
    }

    @GetMapping(value = "/")
    public List<Price> getAll() {
        return this.priceService.getAll();
    }
}
