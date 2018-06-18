package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.FlightService;
import com.ApiVuelos.ApiVuelos.service.PriceService;
import com.ApiVuelos.ApiVuelos.service.TicketService;
import com.ApiVuelos.ApiVuelos.service.UserService;
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
    @Mock
    private FlightService flightService;
    @Mock
    private UserService userService;
    @Mock
    private PriceService priceService;
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
    Price money=new Price(1,1023,"10/12/18","25/01/2019",cabin);

    User us=new User(1,"Alekano","12345");

    Ticket tk=new Ticket(1,fl,money, us);

    List<Ticket>dao=new ArrayList();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.dao.add(this.tk);


    }

    @Test
    public void addTestExceptionGetById(){

        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getById(1l)).thenThrow(Exception.class);
            status1 = this.controller.add(this.dao);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }

    }

    @Test
    public void addTest(){
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            Flight vuelo=this.tk.getFlight();
            User usuario=this.tk.getUser();
            Price price=this.tk.getPrice();

            assertNotNull(vuelo);
            assertNotNull(usuario);
            assertNotNull(price);


            when(this.flightService.getById(vuelo.getId())).thenReturn(this.fl);
            vuelo=this.flightService.getById(vuelo.getId());
            assertEquals(this.fl,vuelo);

            when(this.priceService.getPriceOfCabinAndDate(this.cabin.getName(),this.fl.getDate())).thenReturn(this.money);
            price=this.priceService.getPriceOfCabinAndDate(price.getCabin().getName(),vuelo.getDate());
            assertEquals(this.money,price);

            when(this.userService.getById(this.us.getId())).thenReturn(this.us);
            usuario=this.userService.getById(usuario.getId());
            assertEquals(this.us,usuario);
            assertEquals(usuario,this.tk.getUser());
            assertEquals(vuelo,this.tk.getFlight());
            assertEquals(price,this.tk.getPrice());
            assertEquals(102,300,this.tk.getTotalPrice());


            assertFalse(this.tk.validateNullEmpty());

            when(this.service.newObject(this.tk)).thenReturn(this.tk);
            Ticket tkt=this.service.newObject(this.tk);
            assertEquals(this.tk,tkt);

            when(this.controller.add(this.dao)).thenReturn(status);
            status=this.controller.add(this.dao);
            assertEquals(new ResponseEntity(HttpStatus.OK),status);


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void updateTest(){
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            when(this.service.getById(this.tk.getId())).thenReturn(this.tk);
            Ticket tick=this.service.getById(this.tk.getId());

            assertEquals(this.tk,tick);
            Flight vuelo=this.tk.getFlight();
            User usuario=this.tk.getUser();
            Price price=this.tk.getPrice();

            assertNotNull(vuelo);
            assertNotNull(usuario);
            assertNotNull(price);

            when(this.flightService.getById(vuelo.getId())).thenReturn(this.fl);
            vuelo=this.flightService.getById(vuelo.getId());
            assertEquals(this.fl,vuelo);

            when(this.priceService.getPriceOfCabinAndDate(this.cabin.getName(),this.fl.getDate())).thenReturn(this.money);
            price=this.priceService.getPriceOfCabinAndDate(price.getCabin().getName(),vuelo.getDate());
            assertEquals(this.money,price);

            when(this.userService.getById(this.us.getId())).thenReturn(this.us);
            usuario=this.userService.getById(usuario.getId());
            assertEquals(this.us,usuario);
            assertEquals(usuario,this.tk.getUser());
            assertEquals(vuelo,this.tk.getFlight());
            assertEquals(price,this.tk.getPrice());
            assertEquals(102,300,this.tk.getTotalPrice());

            assertFalse(this.tk.validateNullEmpty());

            when(this.service.newObject(this.tk)).thenReturn(this.tk);
            Ticket tkt=this.service.newObject(this.tk);
            assertEquals(this.tk,tkt);

            when(this.controller.update(tkt)).thenReturn(status);
            status=this.controller.update(tkt);
            assertEquals(new ResponseEntity(HttpStatus.OK),status);


        }
        catch(Exception e){
         e.printStackTrace();
        }
    }
    @Test
    public void updateTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getById(1l)).thenThrow(Exception.class);
            status1 = this.controller.update(this.tk);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void removeTestException(){
        Long id= Long.valueOf(1);
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {

            status1 = this.controller.remove(1l);
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
    public void getAllTest(){
        try {
            when(this.service.getAll()).thenReturn(this.dao);
            List<Ticket> list=this.service.getAll();
            assertEquals(this.dao.size(),list.size());
            ResponseEntity status=this.controller.getAll();
            assertEquals(new ResponseEntity(list,HttpStatus.OK),status);
            assertEquals(1,list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getAllTestException(){

        Long id= Long.valueOf(1);
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
        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try {

            when(this.service.getByUser(1l)).thenReturn(this.tk);
            Ticket tkt=this.service.getByUser(1l);
            assertEquals(this.tk,tkt);
            assertNotNull(this.tk);
            status=this.controller.getByOneTicket(tkt.getId());
            assertEquals(new ResponseEntity(tkt,HttpStatus.OK),status);

        }
        catch(Exception e){

        }
    }
    @Test
    public void getByOneTicketTestException(){
        Long id= Long.valueOf(1);
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getByUser(1l)).thenThrow(Exception.class);
            status1 = this.controller.getByOneTicket(1l);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }

}
