package com.utn.WebService.controller;

import com.utn.WebService.util.SessionData;
import com.utn.WebService.wrapper.UserWrapper;
import com.utn.tssi.tp5.Models.model.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TicketControllerTest extends TestCase {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;
    @InjectMocks
    private TicketController ticketController;




    Country ct =new Country(1,"Argentina","ARG");
    State st=new State(1,"state","ARG",ct);
    City city=new City(1,"Buenos Aires","BS",st);
    City city2=new City(1,"Rosario","R",st);
    Airport airportBegin=new Airport(1,"AreolineasArgentinas","ARG",city,-222,222);
    Airport airportEnd=new Airport(2,"MDP","MDP",city2,-222,222);
    Route rt=new Route(1,airportBegin,airportEnd,100,1);
    Flight fl =new Flight(1,rt,"10/12/18");
    Cabin cabin=new Cabin(1,"Economica");
    Price money=new Price(1,1023,"10/12/18","25/01/2019",false,cabin);
    User us=new User(1,"Alekano","12345");
    Ticket tk=new Ticket(1,fl,money, us);

    public static SessionData sessionData = new SessionData();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void requestATicket(){
        try
        {
            String sessionId=this.sessionData.addSession(new UserWrapper(new User("alekano","12345")));
            this.session.setAttribute("tocken",sessionId);
            when(request.getSession(true)).thenReturn(this.session);
            when(this.session.getAttribute("tocken")).thenReturn(sessionId);

            assertNotNull(this.airportBegin.getIataCode());
            assertFalse(this.airportBegin.getIataCode().trim().equals(""));
            assertNotNull(this.airportEnd.getIataCode());
            assertFalse(this.airportEnd.getIataCode().trim().equals(""));

            ResponseEntity state=new ResponseEntity(this.rt,HttpStatus.OK);
            when(this.restTemplate.getForEntity("http://localhost:25100/route/?iataAirportBegin="+this.airportBegin.getIataCode()+"&iataAirportEnd="+this.airportEnd.getIataCode(),Route.class)).thenReturn(state);
            ResponseEntity entity=this.restTemplate.getForEntity("http://localhost:25100/route/?iataAirportBegin="+this.airportBegin.getIataCode()+"&iataAirportEnd="+this.airportEnd.getIataCode(),Route.class);
            assertEquals(this.rt,(Route) entity.getBody());


            ResponseEntity statePrice=new ResponseEntity(this.money,HttpStatus.OK);
            when(this.restTemplate.getForEntity("http://localhost:25100/price/?typeCabin="+this.cabin.getName()+"&date="+"10/11/12",Price.class)).thenReturn(state);
            ResponseEntity entityPrice=this.restTemplate.getForEntity("http://localhost:25100/price/?typeCabin="+this.cabin.getName()+"&date="+"10/11/12",Price.class);
            assertEquals(statePrice.getStatusCode(), entityPrice.getStatusCode());


            assertNotNull(entity.getBody());
            assertNotNull(entityPrice.getBody());

            List<Flight> flights = new ArrayList<Flight>();


            flights.add(new Flight((Route) entity.getBody(),"10/11/12"));

            when(this.restTemplate.postForEntity("http://localhost:25100/flight/", flights, Flight[].class)).thenReturn(new ResponseEntity(HttpStatus.OK));


            when(this.ticketController.requestATicket(this.airportBegin.getIataCode(),this.airportEnd.getIataCode(),"10/11/12",this.cabin.getName(),mock(HttpServletRequest.class)));
            ResponseEntity a=this.ticketController.requestATicket(this.airportBegin.getIataCode(),this.airportEnd.getIataCode(),"10/11/12",this.cabin.getName(),this.request);
            assertEquals(new ResponseEntity(this.tk,HttpStatus.OK),a);



        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
