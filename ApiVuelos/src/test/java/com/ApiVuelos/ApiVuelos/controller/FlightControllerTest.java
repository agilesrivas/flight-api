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
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.newObject(this.fl)).thenThrow(Exception.class);
            status1 = this.controller.add(this.list);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void addTest(){

        ResponseEntity status=new ResponseEntity(HttpStatus.OK);

        try {



            assertFalse(this.ct.validateNullEmpty());

            when(this.service.newObject(this.fl)).thenReturn(this.fl);
            Flight Bend=this.service.newObject(this.fl);
            assertEquals(Bend,this.fl);

            when(this.controller.add(this.list)).thenReturn(status);
            ResponseEntity statusOk=this.controller.add(this.list);
            assertEquals(statusOk,status);
        }
        catch(Exception e){

        }
    }
    @Test
    public void updateTest(){

        ResponseEntity status=new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            assertNotNull(this.ct);

            when(this.service.getById(this.ct.getId())).thenReturn(this.fl);

            Flight ste=this.service.getById(this.fl.getId());
            assertNotNull(ste);

            assertFalse(this.ct.validateNullEmpty());
            when(this.service.newObject(this.fl)).thenReturn(this.fl);
            Flight flight=this.service.newObject(this.fl);
            assertEquals(this.fl,flight);


            when(this.controller.update(this.fl)).thenReturn(status);
            ResponseEntity statusOk=this.controller.update(this.fl);
            assertEquals(statusOk,status);


        }
        catch(Exception e){

        }
    }
    @Test
    public void updateTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.newObject(this.fl)).thenThrow(Exception.class);
            status1 = this.controller.update(this.fl);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
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
    public void getByOneFlightOnDateTest(){

        ResponseEntity status = new ResponseEntity(HttpStatus.OK);
        Flight tk=null;
        assertNull(tk);
        String iataBegin=this.fl.getRoute().getAirportBegin().getIataCode();
        String iataEnd=this.fl.getRoute().getAirportEnd().getIataCode();
        String date=this.fl.getDate();
        try {
            assertFalse(iataBegin.trim().equals(""));
            assertNotNull(iataBegin);
            assertFalse(iataEnd.trim().equals(""));
            assertNotNull(iataEnd);
            when(this.service.getByAttributeTypeDateRoute(date, fl.getRoute())).thenReturn(this.fl);
            tk=this.service.getByAttributeTypeDateRoute(date, fl.getRoute());
            assertEquals(this.fl,tk);
            assertNotNull(this.fl);
            when(this.controller.getByOneFlightOnDate(date, iataBegin, iataEnd)).thenReturn(status);
            status=this.controller.getByOneFlightOnDate(date, iataBegin, iataEnd);
            assertEquals(new ResponseEntity(this.fl,HttpStatus.OK),status);
        }
        catch(Exception e){

        }
    }

    @Test
    public void getByOneFlightOnDateTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        String iataBegin=this.fl.getRoute().getAirportBegin().getIataCode();
        String iataEnd=this.fl.getRoute().getAirportEnd().getIataCode();
        String date=this.fl.getDate();
        try {
            when(this.service.getByAttributeType(this.ct.getIsoCode())).thenThrow(Exception.class);
            status1 = this.controller.getByOneFlightOnDate(date, iataBegin, iataEnd);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }

    @Test
    public void getBetweenDatesTest(){

        ResponseEntity status = new ResponseEntity(HttpStatus.OK);
        List<Flight> tk=null;
        assertNull(tk);
        try {
            when(this.service.getByAttributeDate("25/06/2018", "30/06/2018")).thenReturn(this.list);
            tk=this.service.getByAttributeDate("25/06/2018", "30/06/2018");
            assertEquals(this.list,tk);
            assertNotNull(this.list);
            when(this.controller.getBetweenDates("25/06/2018", "30/06/2018")).thenReturn(status);
            status=this.controller.getBetweenDates("25/06/2018", "30/06/2018");
            assertEquals(new ResponseEntity(this.fl,HttpStatus.OK),status);
        }
        catch(Exception e){

        }
    }

    @Test
    public void getBetweenDatesTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        List<Flight> tk=null;
        assertNull(tk);
        try {
            when(this.service.getByAttributeDate("25/06/2018", "30/06/2018")).thenThrow(Exception.class);
            status1=this.controller.getBetweenDates("25/06/2018", "30/06/2018");
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
}
