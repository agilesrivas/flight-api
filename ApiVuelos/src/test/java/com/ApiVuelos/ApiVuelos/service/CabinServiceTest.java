package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.AirportRepository;
import com.ApiVuelos.ApiVuelos.repository.CabinRepository;
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
public class CabinServiceTest extends TestCase {


    @Mock
    private CabinRepository cabinRepository;

    @InjectMocks
    private CabinService service;




    Cabin cabin=new Cabin(1,"Economica");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getALlTest()throws Exception {
        List<Cabin> listCabin = new ArrayList<Cabin>();
        listCabin.add(this.cabin);
        listCabin.add(this.cabin);
        listCabin.add(this.cabin);
        when(this.cabinRepository.findAll()).thenReturn(listCabin);
        List<Cabin>dao=this.service.getAll();
        assertEquals(3, listCabin.size());
    }
    @Test
    public void addTest()throws Exception{
        when(this.cabinRepository.save(this.cabin)).thenReturn(this.cabin);
        Cabin cab=this.service.newObject(this.cabin);
        assertEquals(1,cab.getId());
        assertEquals("Economica",cab.getName());

    }

    @Test
    public void removeTest()throws Exception{
        service.removeObject(this.cabin.getId());
        verify(this.cabinRepository,times(1)).deleteById(this.cabin.getId());
    }
    @Test
    public void getByIdTest()throws Exception{
        when(this.cabinRepository.findById(this.cabin.getId())).thenReturn(java.util.Optional.ofNullable(this.cabin));
        Cabin cab=this.service.getById(this.cabin.getId());
        assertEquals(1,cab.getId());
        assertEquals("Economica",cab.getName());

    }
    @Test
    public void getByAttributeTypeTest()throws Exception{
        when(this.cabinRepository.getAttribute(this.cabin.getName())).thenReturn(this.cabin);
        Cabin cab=this.service.getByAttributeType(this.cabin.getName());
        assertEquals(1,cab.getId());
        assertEquals("Economica",cab.getName());

    }
}
