package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/airport")
public class AirportController {


    @PostMapping(value = "/add")
    public void add() {

    }

    @PutMapping(value = "/update")
    public void update() {

    }

    @DeleteMapping(value = "/remove")
    public void remove() {

    }

    @GetMapping(value = "/")
    public void getAll() {

    }
}
