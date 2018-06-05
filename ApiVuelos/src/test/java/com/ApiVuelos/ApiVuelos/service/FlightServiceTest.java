package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.CountryRepository;
import com.ApiVuelos.ApiVuelos.repository.FlightRepository;
import com.utn.tssi.tp5.Models.model.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringJUnit4ClassRunner.class)
public class FlightServiceTest extends TestCase {


    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService service;


    Country ct =new Country(1,"Argentina","ARG");
    State st=new State(1,"state","ARG",ct);
    City city=new City(1,"Buenos Aires","BS",st);
    City city2=new City(1,"Rosario","R",st);
    Airport airportBegin=new Airport(1,"AreolineasArgentinas","ARG",city,-222,222);
    Airport airportEnd=new Airport(2,"MDP","MDP",city2,-222,222);
    Route rt=new Route(1,airportBegin,airportEnd,100,1);
    Flight fl =new Flight(1,rt,"10/12/18");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getALlTest() {
        List<Flight> listFlights = new ArrayList<Flight>();
        listFlights.add(this.fl);
        listFlights.add(this.fl);
        listFlights.add(this.fl);
        when(this.flightRepository.findAll()).thenReturn(listFlights);
        List<Flight>dao=this.service.getAll();
        assertEquals(3, listFlights.size());
    }
    @Test
    public void addTest(){
        when(this.flightRepository.save(this.fl)).thenReturn(this.fl);
        Flight fly=this.service.newObject(this.fl);
        assertEquals(1,fly.getId());
        assertEquals(this.rt,fly.getRoute());
        assertEquals("10/12/18",fly.getDate());

    }
    @Test
    public void getByAttributeTypeTest(){
        Flight fl=this.service.getByAttributeType("hola");
        assertNull(fl);
    }
    @Test
    public void removeTest(){
        service.removeObject(this.ct.getId());
        verify(this.flightRepository,times(1)).deleteById(this.fl.getId());
    }
    @Test
    public void getByIdTest(){
        when(this.flightRepository.findById(this.fl.getId())).thenReturn(java.util.Optional.ofNullable(this.fl));
        Flight fly=this.service.getById(this.fl.getId());
        assertEquals(1,fly.getId());
        assertEquals(this.rt,fly.getRoute());
        assertEquals("10/12/18",fly.getDate());
    }
    @Test
    public void getByAttributeTypeDateRouteTest(){
        when(this.flightRepository.getAttribute(this.fl.getDate(),this.rt.getId())).thenReturn(java.util.Optional.ofNullable(this.fl));
        Flight fly=this.service.getByAttributeTypeDateRoute(this.fl.getDate(),this.rt);
        assertEquals(1,fly.getId());
        assertEquals(this.rt,fly.getRoute());
        assertEquals("10/12/18",fly.getDate());

    }

}
