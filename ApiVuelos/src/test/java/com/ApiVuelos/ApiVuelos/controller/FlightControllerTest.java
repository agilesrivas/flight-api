package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.FlightService;
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
public class FlightControllerTest extends TestCase {

    @Mock
    private FlightService service;
    @InjectMocks
    private FlightController controller;



    Country ct =new Country(1,"Argentina","ARG");
    State st=new State(1,"state","ARG",ct);
    City city=new City(1,"Buenos Aires","BS",st);
    City city2=new City(1,"Rosario","R",st);
    Airport airportBegin=new Airport(1,"AreolineasArgentinas","ARG",city,-222,222);
    Airport airportEnd=new Airport(2,"MDP","MDP",city2,-222,222);
    Route rt=new Route(1,airportBegin,airportEnd,100,1);
    Flight fl =new Flight(1,rt,"10/12/18");

    List<Flight>list=new ArrayList<Flight>();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.list.add(this.fl);
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
    public void removeTest(){
        Long id= Long.valueOf(1);
        ResponseEntity ent=new ResponseEntity(HttpStatus.OK);
        try {

            assertNotNull(id);
            ResponseEntity status=this.controller.remove(id);
            assertEquals(ent,status);
        }
        catch (Exception e){

        }
    }
    @Test
    public void removeTestException(){
        Long id= Long.valueOf(1);
        ResponseEntity ent=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try {

            ResponseEntity status=this.controller.remove(id);


        }
        catch (Exception e){
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),ent);
        }
    }
    @Test
    public void getAllTest(){

        try {
            when(this.service.getAll()).thenReturn(this.list);
            List<Flight> list=this.service.getAll();
            assertEquals(this.list.size(),list.size());
            ResponseEntity status=this.controller.getAll();
            assertEquals(new ResponseEntity(list,HttpStatus.OK),status);
            assertEquals(1,list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void getAllTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getAll()).thenThrow(Exception.class);
            status1 = this.controller.getAll();
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void getByOneTicketTest(){

    }
    @Test
    public void getByOneTicketTestException(){

    }
}
