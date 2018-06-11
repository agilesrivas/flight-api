package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.FlightService;
import com.ApiVuelos.ApiVuelos.service.TicketService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class FlightControllerTest extends TestCase {

    @Mock
    private FlightService service;
    @InjectMocks
    private FlightController controller;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
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
    public void removeTestException(){

    }
    @Test
    public void removeTest(){

    }
    @Test
    public void getAllTest(){

    }
    @Test
    public void getAllTestException(){

    }

    @Test
    public void getByOneTicketTest(){

    }
    @Test
    public void getByOneTicketTestException(){

    }
}
