package com.utn.WebService.controller;

import com.utn.WebService.wrapper.AirportWrapper;
import com.utn.WebService.wrapper.CabinWrapper;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.Cabin;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/index")
public class MainController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/")
    public ResponseEntity<JSONObject>getAll_Index(HttpServletRequest request) {
        ResponseEntity status = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        HttpSession session = request.getSession(true);
        String tocken = (String) session.getAttribute("tocken");
        List<Cabin>listCabin=new ArrayList<Cabin>();
        List<Airport>listAirport=new ArrayList<Airport>();
        JSONObject entities = new JSONObject();

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

                entities.put("Cabins",cabinWrappers);
                entities.put("Airports",airportWrappers);


            status = new ResponseEntity<JSONObject>(entities, HttpStatus.OK);

          }
        }
        catch(Exception e) {
            e.printStackTrace();
        }


        return status;
    }
}

