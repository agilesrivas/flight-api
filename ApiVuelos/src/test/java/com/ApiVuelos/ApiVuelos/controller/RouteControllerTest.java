package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.RouteService;
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
public class RouteControllerTest extends TestCase {

    @Mock
    private RouteService service;
    @Mock
    private AirportService airportService;
    @InjectMocks
    private RouteController controller;


    Country ct =new Country(1,"Argentina","ARG");
    State st=new State(1,"state","ARG",ct);
    City city=new City(1,"Buenos Aires","BS",st);
    City city2=new City(1,"Rosario","R",st);
    Airport airportBegin=new Airport(1,"AreolineasArgentinas","ARG",city,-222,222);
    Airport airportEnd=new Airport(2,"MDP","MDP",city2,-222,222);
    Route rt=new Route(1,airportBegin,airportEnd,100,1);
    List<Route> rutas=new ArrayList<Route>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.rutas.add(this.rt);
    }

    @Test
    public void addTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.newObject(this.rt)).thenThrow(Exception.class);
            status1 = this.controller.add(this.rutas);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void addTestExceptionCase2(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.airportService.getByAttributeType(this.rt.getAirportBegin().getIataCode())).thenThrow(Exception.class);
            status1 = this.controller.add(this.rutas);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void addTest(){
        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            assertFalse(this.rt.validateNullEmptyIdentifier());
            assertEquals(this.airportBegin,this.rt.getAirportBegin());
            assertEquals(this.airportEnd,this.rt.getAirportEnd());
            String iata1=this.rt.getAirportBegin().getIataCode().replaceAll("[^a-zA-Z0-9]","-");
            String iata2=this.rt.getAirportEnd().getIataCode().replaceAll("[^a-zA-Z0-9]","-");

            when(this.airportService.getByAttributeType(iata1)).thenReturn(this.airportBegin);
            when(this.airportService.getByAttributeType(iata2)).thenReturn(this.airportEnd);

            Airport end=this.airportService.getByAttributeType(iata1);
            Airport begin=this.airportService.getByAttributeType(iata2);


            assertFalse(this.rt.validateNullEmpty());

            when(this.service.newObject(this.rt)).thenReturn(this.rt);
            Route ruta=this.service.newObject(this.rt);
            assertEquals(this.rt,ruta);

            when(this.controller.add(this.rutas)).thenReturn(status);
            status=this.controller.add(this.rutas);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void updateTest(){
        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try{
            assertNotNull(this.rt);
            when(this.service.getById(1l)).thenReturn(this.rt);
            Route rutaPorId=this.service.getById(1l);
            assertEquals(this.rt,rutaPorId);

            assertNotNull(rutaPorId);

            assertFalse(this.rt.validateNullEmptyIdentifier());
            assertEquals(this.airportBegin,this.rt.getAirportBegin());
            assertEquals(this.airportEnd,this.rt.getAirportEnd());
            String iata1=this.rt.getAirportBegin().getIataCode().replaceAll("[^a-zA-Z0-9]","-");
            String iata2=this.rt.getAirportEnd().getIataCode().replaceAll("[^a-zA-Z0-9]","-");

            when(this.airportService.getByAttributeType(iata1)).thenReturn(this.airportBegin);

            when(this.airportService.getByAttributeType(iata2)).thenReturn(this.airportEnd);
            Airport end=this.airportService.getByAttributeType(iata1);
            Airport begin=this.airportService.getByAttributeType(iata2);


            assertFalse(this.rt.validateNullEmpty());

            when(this.service.newObject(this.rt)).thenReturn(this.rt);
            Route ruta=this.service.newObject(this.rt);
            assertEquals(this.rt,ruta);

            when(this.controller.update(ruta)).thenReturn(status);
            status=this.controller.update(ruta);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void updateTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.newObject(this.rt)).thenThrow(Exception.class);
            status1 = this.controller.update(this.rt);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void updateTestExceptionCase2(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.airportService.getByAttributeType(this.rt.getAirportBegin().getIataCode())).thenThrow(Exception.class);
            status1 = this.controller.update(this.rt);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }

    @Test
    public void updateTestExceptionCase3(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getById(1l)).thenThrow(Exception.class);
            status1 = this.controller.update(this.rt);
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
            when(this.service.getAll()).thenReturn(this.rutas);
            List<Route>list=this.service.getAll();
            assertEquals(this.rutas.size(),list.size());
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
    public void getByOneRouteTest(){
        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            assertNotNull(this.airportBegin.getIataCode());
            assertNotNull(this.airportEnd.getIataCode());
            assertFalse(this.airportBegin.getIataCode().trim().equals(""));
            assertFalse(this.airportEnd.getIataCode().trim().equals(""));
            when(this.service.getByAttributeTypeRoute(this.airportBegin.getIataCode(),this.airportEnd.getIataCode())).thenReturn(this.rt);
            Route rout=this.service.getByAttributeTypeRoute(this.airportBegin.getIataCode(),this.airportEnd.getIataCode());
            assertEquals(this.rt,rout);
            when(this.controller.getByOneRoute(this.airportBegin.getIataCode(),this.airportEnd.getIataCode())).thenReturn(status);
            status=this.controller.getByOneRoute(this.airportBegin.getIataCode(),this.airportEnd.getIataCode());
            assertEquals(new ResponseEntity(HttpStatus.OK),status);
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
    @Test
    public void getByOneRouteTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getByAttributeTypeRoute(this.airportBegin.getIataCode(),this.airportEnd.getIataCode())).thenThrow(Exception.class);
            status1 = this.controller.getByOneRoute(this.airportBegin.getIataCode(),this.airportEnd.getIataCode());
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }

    @Test
    public void getByInitAirportTest(){
        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        String iataBegin = this.airportBegin.getIataCode();
        try {
            assertNotNull(iataBegin);
            assertFalse(iataBegin.trim().equals(""));
            when(this.service.getByInitAirport(iataBegin)).thenReturn(this.rutas);
            List<Route> routes=this.service.getByInitAirport(iataBegin);
            assertEquals(this.rutas,routes);
            when(this.controller.getByInitAirport(iataBegin)).thenReturn(status);
            status=this.controller.getByInitAirport(iataBegin);
            assertEquals(new ResponseEntity(HttpStatus.OK),status);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getByInitAirportTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getByInitAirport(this.airportBegin.getIataCode())).thenThrow(Exception.class);
            status1 = this.controller.getByInitAirport(this.airportBegin.getIataCode());
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
}
