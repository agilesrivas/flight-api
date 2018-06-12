package com.utn.WebService.controller;

import com.utn.WebService.WebServiceApplication;
import com.utn.WebService.wrapper.AirportWrapper;
import com.utn.tssi.tp5.Models.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/airport")
public class AirportController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "")
    public ResponseEntity<List<AirportWrapper>> getAll() {
        ResponseEntity status = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        if(WebServiceApplication.TOCKEN != null) {
            status = restTemplate.getForEntity("http://localhost:25100/airport", List.class);

            List<Airport> airports = (List<Airport>) status.getBody();
            List<AirportWrapper> airportWrappers = new ArrayList<>();

            if(!airports.isEmpty()) {
                for(Airport airport : airports) {
                    airportWrappers.add(new AirportWrapper(airport));
                }

                status = new ResponseEntity<List<AirportWrapper>>(airportWrappers, HttpStatus.OK);
            }
        }

        return status;
    }

}
