package com.utn.WebService.controller;

import com.utn.WebService.wrapper.AirportWrapper;
import com.utn.WebService.wrapper.CabinWrapper;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Cabin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/index")
public class MainController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "")
    public Object getAll_Index(HttpServletRequest request, ModelAndView modelAndView) {
        ResponseEntity status = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        HttpSession session = request.getSession(true);
        String tocken = (String) session.getAttribute("tocken");

        List<Cabin>listCabin=new ArrayList<Cabin>();
        List<Airport>listAirport=new ArrayList<Airport>();

        modelAndView.setViewName("redirect:/user");

        try {
            if(tocken != null) {

                status = restTemplate.getForEntity("http://localhost:25100/cabin", Cabin[].class);

                Cabin[] cabins = (Cabin[]) status.getBody();
                List<CabinWrapper> cabinWrappers = new ArrayList<>();

                for(Cabin cabin : cabins) {
                    cabinWrappers.add(new CabinWrapper(cabin));
                }

                status = restTemplate.getForEntity("http://localhost:25100/airport", Airport[].class);

                Airport[] airports = (Airport[]) status.getBody();
                List<AirportWrapper> airportWrappers = new ArrayList<>();

                for(Airport airport : airports) {
                    airportWrappers.add(new AirportWrapper(airport));
                }

                modelAndView.setViewName("/index");
                modelAndView.addObject("cabinList", cabinWrappers);
                modelAndView.addObject("airportList", airportWrappers);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return modelAndView;
    }
}

