package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.RouteService;
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
public class RouteControllerTest extends TestCase {

    @Mock
    private RouteService service;
    @InjectMocks
    private RouteController controller;

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
