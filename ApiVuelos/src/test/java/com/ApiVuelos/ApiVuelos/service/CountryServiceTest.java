package com.ApiVuelos.ApiVuelos.service;

import com.ApiVuelos.ApiVuelos.repository.CountryRepository;
import com.utn.tssi.tp5.Models.model.Country;
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
public class CountryServiceTest extends TestCase {


    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService service;




    Country ct =new Country(1,"Argentina","ARG");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getALlTest() throws Exception{
        List<Country> listCountry = new ArrayList<Country>();
        listCountry.add(this.ct);
        listCountry.add(this.ct);
        listCountry.add(this.ct);
        when(this.countryRepository.findAll()).thenReturn(listCountry);
        List<Country>dao=this.service.getAll();
        assertEquals(3, listCountry.size());
    }
    @Test
    public void addTest()throws Exception{
        when(this.countryRepository.save(this.ct)).thenReturn(this.ct);
        Country country=this.service.newObject(this.ct);
        assertEquals(1,country.getId());
        assertEquals("Argentina",country.getName());
        assertEquals("ARG",country.getIsoCode());

    }

    @Test
    public void removeTest()throws Exception{
        service.removeObject(this.ct.getId());
        verify(this.countryRepository,times(1)).deleteById(this.ct.getId());
    }
    @Test
    public void getByIdTest()throws Exception{
        when(this.countryRepository.findById(this.ct.getId())).thenReturn(java.util.Optional.ofNullable(this.ct));
        Country country=this.service.getById(this.ct.getId());
        assertEquals(1,country.getId());
        assertEquals("Argentina",country.getName());
        assertEquals("ARG",country.getIsoCode());
    }
    @Test
    public void getByAttributeTypeTest()throws Exception{
        when(this.countryRepository.getAttribute(this.ct.getName())).thenReturn(this.ct);
        Country country=this.service.getByAttributeType(this.ct.getName());
        assertEquals(1,country.getId());
        assertEquals("Argentina",country.getName());
        assertEquals("ARG",country.getIsoCode());
    }
}
