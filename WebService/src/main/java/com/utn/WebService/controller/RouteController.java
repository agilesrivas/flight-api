package com.utn.WebService.controller;

import com.utn.WebService.WebServiceApplication;
import com.utn.WebService.wrapper.RouteWrapper;
import com.utn.tssi.tp5.Models.model.Route;
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
@RequestMapping(value = "/route")
public class RouteController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/{iata}")
    public ResponseEntity<List<RouteWrapper>> getAirportsEnd(@PathVariable("iata") String iataAirportBegin) {
        ResponseEntity status = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        if(WebServiceApplication.TOCKEN != null && iataAirportBegin != null && !(iataAirportBegin.trim().equals(""))) {
            status = restTemplate.getForEntity("http://localhost:25100/route/" + iataAirportBegin, List.class);

            List<Route> routes = (List<Route>) status.getBody();
            List<RouteWrapper> routeWrappers = new ArrayList<>();

            if(!routes.isEmpty()) {
                for(Route route : routes) {
                    routeWrappers.add(new RouteWrapper(route));
                }

                status = new ResponseEntity<List<RouteWrapper>>(routeWrappers, HttpStatus.OK);
            }
        }

        return status;
    }
}
