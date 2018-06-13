package com.utn.WebService.controller;

import com.utn.WebService.util.AuthenticationData;
import com.utn.WebService.wrapper.PriceWrapper;
import com.utn.WebService.wrapper.RouteWrapper;
import com.utn.WebService.wrapper.TicketWrapper;
import com.utn.WebService.wrapper.UserWrapper;
import com.utn.tssi.tp5.Models.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/")
public class TicketController {

    @Autowired
     RestTemplate restTemplate;

    @GetMapping("/")
    public ResponseEntity solicitudTicket(@PathVariable("iataAirportBegin")String iataBegin,@PathVariable("iataAirportEnd")String iataEnd,@PathVariable("fecha")String fecha,@PathVariable("typeCabin") String typeCabin, HttpServletRequest  request){

           ResponseEntity status = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        try{

            HttpSession session = request.getSession(true);
            String tocken = (String) session.getAttribute("tocken");
            List<String>iatas=new ArrayList<String>();
            iatas.add(iataBegin);
            iatas.add(iataEnd);

            if(tocken!=null && iataBegin!=null && iataBegin.trim().equals("") &&  iataEnd!=null && iataEnd.trim().equals(""))

            {

                status=restTemplate.getForEntity("http://localhost:25100/route/?iataAirportBegin="+iataBegin+"&iataAirportEnd="+iataEnd,Route.class);
                Route ruta= (Route) status.getBody();

                status=this.restTemplate.getForEntity("http://localhost:25100/price/?typeCabin="+typeCabin,Price.class);
                Price priceCabin= (Price) status.getBody();

                List<Flight>flights=new ArrayList<Flight>();
                Flight vuelo=new Flight(ruta,fecha);
                flights.add(vuelo);

                status=this.restTemplate.postForEntity("http://localhost:25100/flight",flights,Flight[].class);

                AuthenticationData data=UserController.sessionData.getSession(tocken);
                UserWrapper usuarioOn =data.getUser();


                User user=new User(usuarioOn.getName(),usuarioOn.getPassword());
                Ticket tickete=new Ticket(vuelo,priceCabin,user);
                TicketWrapper wrapperTick=new TicketWrapper(tickete);
                status=new ResponseEntity(wrapperTick,HttpStatus.OK);


            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;

    }
}
