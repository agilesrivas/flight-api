package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.utn.tssi.tp5.Models.model.Country;
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
    public void addTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.newObject(this.ct)).thenThrow(Exception.class);
            status1 = this.controller.add(this.listCount);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

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
    public void updateTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.newObject(this.ct)).thenThrow(Exception.class);
            status1 = this.controller.update(this.ct);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void updateTestExceptionCase2(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getById(1l)).thenThrow(Exception.class);
            status1 = this.controller.update(this.ct);
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
    public void getByOneCountryTest(){
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
    @Test
    public void getByOneCountryTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getByAttributeType(this.ct.getIsoCode())).thenThrow(Exception.class);
            status1 = this.controller.getByOneCountry(this.ct.getIsoCode());
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
}
