package com.utn.WebService.controller;

import com.utn.WebService.util.SessionData;
import com.utn.WebService.wrapper.AirportWrapper;
import com.utn.WebService.wrapper.UserWrapper;
import com.utn.tssi.tp5.Models.model.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class AirportControllerTest extends TestCase {


    @Mock
    private RestTemplate restTemplate;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession session;
    @InjectMocks
    private AirportController controller;

    public static SessionData sessionData = new SessionData();


    Country ct =new Country(1,"Argentina","AR");
    State st=new State(1,"state","AR-B",ct);
    City city=new City(1,"Buenos Aires","AR-B-7600",st);
    Airport air=new Airport(1,"AreolineasArgentinas","AR-B-7600-MDQ",city,-222,222);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);


    }

    @Test
    public void verifyGetAll() {
        try
        {
             String sessionId=this.sessionData.addSession(new UserWrapper(new User("alekano","12345")));
            this.session.setAttribute("tocken",sessionId);


           when(request.getSession(true)).thenReturn(this.session);
           when(this.session.getAttribute("tocken")).thenReturn(sessionId);

            String url="http://localhost:25100/airport";
            Airport[] airports={this.air,this.air};
            ResponseEntity state=new ResponseEntity(airports,HttpStatus.OK);

            when(this.restTemplate.getForEntity(url,Airport[].class)).thenReturn(state);
            ResponseEntity a=this.restTemplate.getForEntity(url,Airport[].class);
            assertEquals(a,state);

            List<AirportWrapper> list=new ArrayList<AirportWrapper>();
            list.add(new AirportWrapper(this.air));
            ResponseEntity<List<AirportWrapper>> aw=new ResponseEntity<List<AirportWrapper>>(list,HttpStatus.OK);

            when(this.controller.getAll(this.request)).thenReturn(aw);
            ResponseEntity state2=this.controller.getAll(this.request);
            assertEquals(list,state.getBody());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Test
    public void getAllException()
    {
       try
       {
           String url="http://localhost:25100/airport";
           Airport[] airports={this.air,this.air};
           ResponseEntity state=new ResponseEntity(airports,HttpStatus.OK);

           when(this.restTemplate.getForEntity(url,Airport[].class)).thenThrow(NullPointerException.class);
           ResponseEntity a=this.restTemplate.getForEntity(url,Airport[].class);
           ResponseEntity state2=this.controller.getAll(this.request);
       } catch (NullPointerException ne){
           ne.getMessage();
       }
    }






}
