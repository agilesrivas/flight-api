package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CabinService;
import com.ApiVuelos.ApiVuelos.service.PriceService;
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
public class PriceControllerTest extends TestCase {

    @Mock
    private PriceService service;
    @Mock
    private CabinService cabinService;
    @InjectMocks
    private PriceController controller;


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
    List<Price>prices=new ArrayList<Price>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.prices.add(this.money);
    }

    @Test
    public void addTestException2(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getByAttributeType(this.cabin.getName())).thenThrow(Exception.class);
            status1 = this.controller.add(this.prices);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void addTestException3(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.newObject(this.money)).thenThrow(Exception.class);
            status1 = this.controller.add(this.prices);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }

    @Test
    public void addTest(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            assertNotNull(this.money.getCabin());
            assertFalse(this.money.validateNullEmptyIdentifier());

            when(this.cabinService.getByAttributeType(this.money.getCabin().getName())).thenReturn(this.cabin);
            Cabin cab=this.cabinService.getByAttributeType(this.money.getCabin().getName());


            when(this.service.newObject(this.money)).thenThrow(Exception.class);
            status1 = this.controller.add(this.prices);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void updateTest(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getById(1l)).thenReturn(this.money);
            Price pr=this.service.getById(1l);

            assertNotNull(this.money.getCabin());
            assertFalse(this.money.validateNullEmptyIdentifier());

            when(this.cabinService.getByAttributeType(this.money.getCabin().getName())).thenReturn(this.cabin);
            Cabin cab=this.cabinService.getByAttributeType(this.money.getCabin().getName());


            when(this.service.newObject(this.money)).thenThrow(Exception.class);
            status1 = this.controller.update(this.money);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void updateTestExceptionCase1(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.newObject(this.money)).thenThrow(Exception.class);
            status1 = this.controller.update(this.money);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void updateTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.newObject(this.money)).thenThrow(Exception.class);
            status1 = this.controller.update(this.money);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void updateTestException2(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getById(1l)).thenThrow(Exception.class);
            status1 = this.controller.update(this.money);
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
            when(this.service.getAll()).thenReturn(this.prices);
            List<Price> list=this.service.getAll();
            assertEquals(this.prices.size(),list.size());
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
    public void getPriceOfCabinAndDateTest(){
        ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
                assertNotNull(this.money.getCabin().getName());
                assertFalse(this.money.getCabin().getName().trim().equals(""));

                when(this.service.getPriceOfCabinAndDate(this.cabin.getName(), "10/12/18")).thenReturn(this.money);
                this.money = this.service.getPriceOfCabinAndDate(this.cabin.getName(), "10/12/18");

                when(this.controller.getPriceOfCabinAndDate(this.cabin.getName(), "10/12/18")).thenReturn(status);
                status=this.controller.getPriceOfCabinAndDate(this.cabin.getName(), "10/12/18");
                assertEquals(new ResponseEntity(HttpStatus.OK),status);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void getByOnePricesOfCabinTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getPriceOfCabinAndDate(this.cabin.getName(), "10/12/18")).thenThrow(Exception.class);
            status1 = this.controller.getPriceOfCabinAndDate(this.cabin.getName(), "10/12/18");
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
}
