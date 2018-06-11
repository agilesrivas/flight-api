package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.TicketService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TicketControllerTest extends TestCase {

    @Mock
    private TicketService service;
    @InjectMocks
    private TicketController controller;



    Country ct =new Country(1,"Argentina","ARG");
    State st=new State(1,"state","ARG",ct);
    City city=new City(1,"Buenos Aires","BS",st);
    City city2=new City(1,"Rosario","R",st);
    Airport airportBegin=new Airport(1,"AreolineasArgentinas","ARG",city,-222,222);
    Airport airportEnd=new Airport(2,"MDP","MDP",city2,-222,222);
    Route rt=new Route(1,airportBegin,airportEnd,100,1);
    Flight fl =new Flight(1,rt,"10/12/18");
    Cabin cabin=new Cabin(1,"Economica");
    Price money=new Price(1,1023,"10/12/18",null,true,cabin);
    User us=new User(1,"Alekano","12345");
    Ticket tk=new Ticket(1,fl,money, us);

    List<Ticket>dao=new ArrayList();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        
    }

    @Test
    public void addTestException(){

    }
    @Test
    public void addTest(){

    }
    @Test
    public void updateTest(){

    }
    @Test
    public void updateTestException(){

    }
    @Test
    public void removeTestException(){

    }
    @Test
    public void removeTest(){

    }
    @Test
    public void getAllTest(){

    }
    @Test
    public void getAllTestException(){

        try {
            when(this.service.getAll()).thenReturn(this.tk);
            List<Airport> list=this.service.getAll();
            assertEquals(this.airports.size(),list.size());
            ResponseEntity status=this.controller.getAll();
            assertEquals(new ResponseEntity(list,HttpStatus.OK),status);
            assertEquals(3,list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getByOneTicketTest(){

    }
    @Test
    public void getByOneTicketTestException(){

    }

}
