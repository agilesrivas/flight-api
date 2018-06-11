package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.ApiVuelos.ApiVuelos.service.TicketService;
import com.utn.tssi.tp5.Models.model.Cabin;
import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CountryControllerTest extends TestCase {

    @Mock
    private CountryService service;
    @InjectMocks
    private CountryController controller;
    Country ct =new Country(1,"Argentina","AR");
    List<Country> listCount = new ArrayList<Country>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this. listCount.add(this.ct);
    }






    @Test
    public void addTest(){

        ResponseEntity status=new ResponseEntity(HttpStatus.OK);

        try {



            assertFalse(this.ct.validateNullEmpty());

            when(this.service.newObject(this.ct)).thenReturn(this.ct);
            Country Bend=this.service.newObject(this.ct);
            assertEquals(Bend,this.ct);

            when(this.controller.add(this.listCount)).thenReturn(status);
            ResponseEntity statusOk=this.controller.add(this.listCount);
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




            when(this.service.getById(this.ct.getId())).thenReturn(this.ct);

            Country ste=this.service.getById(this.ct.getId());
            assertNotNull(ste);

            assertFalse(this.ct.validateNullEmpty());
            when(this.service.newObject(this.ct)).thenReturn(this.ct);
            Country airt=this.service.newObject(this.ct);
            assertEquals(this.ct,airt);


            when(this.controller.update(this.ct)).thenReturn(status);
            ResponseEntity statusOk=this.controller.update(this.ct);
            assertEquals(statusOk,status);


        }
        catch(Exception e){

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
            when(this.service.getAll()).thenReturn(this.listCount);
            List<Country>list=this.service.getAll();
            assertEquals(this.listCount.size(),list.size());
            ResponseEntity status=this.controller.getAll();
            assertEquals(new ResponseEntity(list,HttpStatus.OK),status);
            assertEquals(1,list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getByOneCityTest(){
        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        Country tk=null;
        assertNull(tk);
        String iata=this.ct.getIsoCode();
        try {
            assertFalse(iata.trim().equals(""));
            assertNotNull(iata);
            when(this.service.getByAttributeType(iata)).thenReturn(this.ct);
            tk=this.service.getByAttributeType(iata);
            assertEquals(this.ct,tk);
            assertNotNull(this.ct);
            when(this.controller.getByOneCountry(this.ct.getIsoCode())).thenReturn(status);
            status=this.controller.getByOneCountry(iata);
            assertEquals(new ResponseEntity(this.ct,HttpStatus.OK),status);
        }
        catch(Exception e){

        }
    }
}
