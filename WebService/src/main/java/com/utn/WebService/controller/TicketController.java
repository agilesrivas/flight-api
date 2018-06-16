package com.utn.WebService.controller;

import com.utn.WebService.util.AuthenticationData;
import com.utn.WebService.wrapper.PriceWrapper;
import com.utn.WebService.wrapper.RouteWrapper;
import com.utn.WebService.wrapper.TicketWrapper;
import com.utn.WebService.wrapper.UserWrapper;
import com.utn.tssi.tp5.Models.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/ticket")
public class TicketController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity requestATicket(@RequestParam("iataAirportBegin")String iataBegin, @RequestParam("iataAirportEnd")String iataEnd, @RequestParam("date")String date, @RequestParam("typeCabin") String typeCabin, HttpServletRequest  request){

        ResponseEntity status = new ResponseEntity("Nothing to show", HttpStatus.UNAUTHORIZED);

        try{
            HttpSession session = request.getSession(true);
            String tocken = (String) session.getAttribute("tocken");
            List<String>iatas=new ArrayList<String>();
            iatas.add(iataBegin);
            iatas.add(iataEnd);

            if(tocken != null && iataBegin != null && !(iataBegin.trim().equals("")) &&  iataEnd != null && !(iataEnd.trim().equals("")))  {

                status = restTemplate.getForEntity("http://localhost:25100/route/?iataAirportBegin="+iataBegin+"&iataAirportEnd="+iataEnd,Route.class);
                Route route= (Route) status.getBody();

                status = this.restTemplate.getForEntity("http://localhost:25100/price/?typeCabin="+typeCabin+"&date="+date,Price.class);
                Price priceCabin= (Price) status.getBody();

                status = new ResponseEntity("Nothing to show", HttpStatus.NO_CONTENT);

                if(route != null && priceCabin != null) {

                    List<Flight> flights = new ArrayList<Flight>();
                    Flight flight = new Flight(route, date);
                    flights.add(flight);

                    status = this.restTemplate.postForEntity("http://localhost:25100/flight/", flights, Flight[].class);

                    AuthenticationData data = UserController.sessionData.getSession(tocken);
                    UserWrapper usuarioOn = data.getUser();

                    User user = new User(usuarioOn.getName(), usuarioOn.getPassword());
                    Ticket ticket = new Ticket(flight, priceCabin, user);
                    TicketWrapper wrapperTick = new TicketWrapper(ticket);

                    status = new ResponseEntity(wrapperTick, HttpStatus.OK);
                }
            } else {
                status = new ResponseEntity("Nothing to show", HttpStatus.UNAUTHORIZED);
            }
        } catch(Exception e){
            e.printStackTrace();
            status = new ResponseEntity("Nothing to show", HttpStatus.BAD_REQUEST);
        }

        return status;

    }
}
