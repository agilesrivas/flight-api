package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.TicketRepository;
import com.utn.tssi.tp5.Models.model.*;
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
public class TicketServiceTest extends TestCase {
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService service;


    Country ct =new Country(1,"Argentina","ARG");
    State st=new State(1,"state","ARG",ct);
    City city=new City(1,"Buenos Aires","BS",st);
    City city2=new City(1,"Rosario","R",st);
    Airport airportBegin=new Airport(1,"AreolineasArgentinas","ARG",city,-222,222);
    Airport airportEnd=new Airport(2,"MDP","MDP",city2,-222,222);
    Route rt=new Route(1,airportBegin,airportEnd,100,1);
    Flight fl =new Flight(1,rt,"10/12/18");
    Cabin cabin=new Cabin(1,"Economica");
            Price money=new Price(1,1023,"10/12/18","25/01/2018",cabin);
            User us=new User(1,"Alekano","12345");
    Ticket tk=new Ticket(1,fl,money, us);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getALlTest()throws Exception {
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(this.tk);
        tickets.add(this.tk);
        tickets.add(this.tk);
        when(this.ticketRepository.findAll()).thenReturn(tickets);
        List<Ticket>dao=this.service.getAll();
        assertEquals(3, tickets.size());
    }
    @Test
    public void addTest()throws Exception {
        when(this.ticketRepository.save(this.tk)).thenReturn(this.tk);
        Ticket tkt=this.service.newObject(this.tk);
        assertEquals(1,tkt.getId());
        assertEquals(this.fl,tkt.getFlight());
        assertEquals(this.us,tkt.getUser());
        assertEquals(this.money,tkt.getPrice());

    }
   @Test
    public void removeTest()throws Exception{
        service.removeObject(this.ct.getId());
        verify(this.ticketRepository,times(1)).deleteById(this.tk.getId());
    }
    @Test
    public void getByIdTest()throws Exception {
        when(this.ticketRepository.findById(this.tk.getId())).thenReturn(java.util.Optional.ofNullable(this.tk));
        Ticket tkt = this.service.getById(this.tk.getId());
        assertEquals(1, tkt.getId());
        assertEquals(this.fl, tkt.getFlight());
        assertEquals(this.us,tkt.getUser());
        assertEquals(this.money,tkt.getPrice());
    }
        @Test
      public void getByAttributeTypeTest()throws Exception{
        when(this.ticketRepository.getAttribute(this.tk.getId())).thenReturn(java.util.Optional.ofNullable(this.tk));
        Ticket tkt=this.service.getByAttributeType(this.fl.getDate());
        assertNull(tkt);

    }
    @Test
    public void getByUserTest() {
        when(this.ticketRepository.getAttribute(this.tk.getId())).thenReturn(java.util.Optional.ofNullable(this.tk));
        Ticket tkt = this.service.getByUser(this.tk.getId());
        assertEquals(1, tkt.getId());
        assertEquals(this.fl, tkt.getFlight());
        assertEquals(this.us,tkt.getUser());
        assertEquals(this.money,tkt.getPrice());
    }

}
