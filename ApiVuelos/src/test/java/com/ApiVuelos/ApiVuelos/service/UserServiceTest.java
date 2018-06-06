package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.AirportRepository;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest extends TestCase {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
     private AirportService service;



    Country ct =new Country(1,"Argentina","ARG");
    State st=new State(1,"state","ARG",ct);
    City city=new City(1,"Buenos Aires","BS",st);
    Airport airport=new Airport(1,"AreolineasArgentinas","ARG",city,-222,222);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getALlTest() throws Exception{
        List<Airport> listAirport = new ArrayList<Airport>();
        listAirport.add(this.airport);
        listAirport.add(this.airport);
        listAirport.add(this.airport);
        when(this.airportRepository.findAll()).thenReturn(listAirport);
        List<Airport>dao=this.service.getAll();
        assertEquals(3,dao.size());
    }
    @Test
    public void addTest()throws Exception{
        when(this.airportRepository.save(this.airport)).thenReturn(this.airport);
        Airport air=this.service.newObject(this.airport);
        assertEquals(1,air.getId());
        assertEquals("AreolineasArgentinas",air.getName());
        assertEquals("ARG",air.getIataCode());
        assertEquals(city,air.getCity());
        assertEquals(-222,air.getLatitude(),0);
        assertEquals(222,air.getLongitude(),0);
    }
    @Test
    public void removeTest()throws Exception{
        service.removeObject(this.airport.getId());
        verify(this.airportRepository,times(1)).deleteById(this.airport.getId());
    }
    @Test
    public void getByIdTest()throws Exception{
        when(this.airportRepository.findById(this.airport.getId())).thenReturn(java.util.Optional.ofNullable(this.airport));
        Airport air=this.service.getById(this.airport.getId());
        assertEquals(1,air.getId());
        assertEquals("AreolineasArgentinas",air.getName());
        assertEquals("ARG",air.getIataCode());
        assertEquals(city,air.getCity());
        assertEquals(-222,air.getLatitude(),0);
        assertEquals(222,air.getLongitude(),0);
    }
    @Test
    public void getByAttributeTypeTest()throws Exception{
        when(this.airportRepository.getAttribute(this.airport.getIataCode())).thenReturn(this.airport);
        Airport air=this.service.getByAttributeType(this.airport.getIataCode());
        assertEquals(1,air.getId());
        assertEquals("AreolineasArgentinas",air.getName());
        assertEquals("ARG",air.getIataCode());
        assertEquals(city,air.getCity());
        assertEquals(-222,air.getLatitude(),0);
        assertEquals(222,air.getLongitude(),0);
    }

}
