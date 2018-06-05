package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.AirportRepository;
import com.ApiVuelos.ApiVuelos.repository.CityRepository;
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
public class CityServiceTest extends TestCase {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService service;



    Country ct =new Country(1,"Argentina","ARG");
    State st=new State(1,"state","ARG",ct);
    City city=new City(1,"Buenos Aires","BS",st);


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getALlTest() throws Exception{
        List<City> listAirport = new ArrayList<City>();
        listAirport.add(this.city);
        listAirport.add(this.city);
        listAirport.add(this.city);
        when(this.cityRepository.findAll()).thenReturn(listAirport);
        List<City>dao=this.service.getAll();
        assertEquals(3, listAirport.size());
    }
    @Test
    public void addTest()throws Exception{
        when(this.cityRepository.save(this.city)).thenReturn(this.city);
        City cit=this.service.newObject(this.city);
        assertEquals(1,cit.getId());
        assertEquals("Buenos Aires",cit.getName());
        assertEquals("BS",cit.getIataCode());
        assertEquals(this.st,cit.getState());

    }

    @Test
    public void removeTest()throws Exception{
        service.removeObject(this.city.getId());
        verify(this.cityRepository,times(1)).deleteById(this.city.getId());
    }
    @Test
    public void getByIdTest()throws Exception{
        when(this.cityRepository.findById(this.city.getId())).thenReturn(java.util.Optional.ofNullable(this.city));
        City cit=this.service.getById(this.city.getId());
        assertEquals(1,cit.getId());
        assertEquals("Buenos Aires",cit.getName());
        assertEquals("BS",cit.getIataCode());
        assertEquals(this.st,cit.getState());
    }
    @Test
    public void getByAttributeTypeTest()throws Exception{
        when(this.cityRepository.getAttribute(this.city.getIataCode())).thenReturn(this.city);
        City cit=this.service.getByAttributeType(this.city.getIataCode());
        assertEquals(1,cit.getId());
        assertEquals("Buenos Aires",cit.getName());
        assertEquals("BS",cit.getIataCode());
        assertEquals(this.st,cit.getState());
    }
}
