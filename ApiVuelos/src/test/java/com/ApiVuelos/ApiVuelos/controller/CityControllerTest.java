package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CityService;
import com.ApiVuelos.ApiVuelos.service.StateService;
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

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CityControllerTest extends TestCase {
    @Mock
    private CityService cityService;
    @Mock
    private StateService stateService;
    @InjectMocks
    private CityController controller;


    Country ct =new Country(1,"Argentina","AR");
    State st=new State(1,"state","AR-B",ct);
    City city=new City(1,"Buenos Aires","AR-B-7600",st);

    List<City> listCity = new ArrayList<City>();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.listCity.add(city);
        this.listCity.add(city);
        this.listCity.add(city);
    }


    @Test
    public void addTest(){
        ResponseEntity status=new ResponseEntity(HttpStatus.OK);

        try {


            String iata=this.city.getIataCode().replaceAll("[^a-zA-Z0-9]","-");
            String [] code=iata.split("-");
            assertEquals(3,code.length);
            assertNotNull(code[0]);
            assertNotNull(code[1]);
            assertNotNull(code[2]);

            assertFalse(code[0].trim().equals(""));
            assertFalse(code[1].trim().equals(""));
            assertFalse(code[2].trim().equals(""));


            when(this.stateService.getByAttributeType(code[0]+"-"+code[1])).thenReturn(this.st);
            State ct=this.stateService.getByAttributeType(code[0]+"-"+code[1]);


            assertEquals(ct,this.city.getState());
            assertEquals(code[0] + "-" + code[1] + "-" + code[2],this.city.getIataCode());

            assertFalse(this.city.validateNullEmpty());

            when(this.cityService.newObject(this.city)).thenReturn(this.city);
            City Bend=this.cityService.newObject(this.city);
            assertEquals(Bend,this.city);

            when(this.controller.add(this.listCity)).thenReturn(status);
            ResponseEntity statusOk=this.controller.add(this.listCity);
            assertEquals(statusOk,status);


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void addTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.cityService.newObject(this.city)).thenThrow(Exception.class);
            status1 = this.controller.add(this.listCity);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void addTestExceptionCase2(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.stateService.getByAttributeType(this.city.getIataCode())).thenThrow(Exception.class);
            status1 = this.controller.add(this.listCity);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void updateTest(){

        ResponseEntity status=new ResponseEntity(HttpStatus.OK);

        try {
            when(this.cityService.getById(this.city.getId())).thenReturn(this.city);

            City ste=this.cityService.getById(this.city.getId());
            assertNotNull(ste);

            String iata=this.city.getIataCode().replaceAll("[^a-zA-Z0-9]","-");
            String [] code=iata.split("-");
            assertEquals(3,code.length);
            assertNotNull(code[0]);
            assertNotNull(code[1]);
            assertNotNull(code[2]);

            assertFalse(code[0].trim().equals(""));
            assertFalse(code[1].trim().equals(""));
            assertFalse(code[2].trim().equals(""));


            when(this.stateService.getByAttributeType(code[0]+"-"+code[1])).thenReturn(this.st);
            State ct=this.stateService.getByAttributeType(code[0]+"-"+code[1]);


            assertEquals(ct,this.city.getState());
            assertEquals(code[0] + "-" + code[1] + "-" + code[2],this.city.getIataCode());

            assertFalse(this.city.validateNullEmpty());

            when(this.cityService.newObject(this.city)).thenReturn(this.city);
            City Bend=this.cityService.newObject(this.city);
            assertEquals(Bend,this.city);

            when(this.controller.update(this.city)).thenReturn(status);
            ResponseEntity statusOk=this.controller.update(this.city);
            assertEquals(statusOk,status);


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void updateTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.cityService.newObject(this.city)).thenThrow(Exception.class);
            status1 = this.controller.update(this.city);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void updateTestExeptionCase2() {
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.stateService.getByAttributeType(this.city.getIataCode())).thenThrow(Exception.class);
            status1 = this.controller.update(this.city);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR), status1);
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
            when(this.cityService.getAll()).thenReturn(this.listCity);
            List<City>list=this.cityService.getAll();
            assertEquals(this.listCity.size(),list.size());
            ResponseEntity status=this.controller.getAll();
            assertEquals(new ResponseEntity(list,HttpStatus.OK),status);
            assertEquals(3,list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getAllTestException() {
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.cityService.getAll()).thenThrow(Exception.class);
            status1 = this.controller.getAll();
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR), status1);
        }
    }
    @Test
    public void getByOneCityTest(){
        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        City tk=null;
        assertNull(tk);
        String iata=this.city.getIataCode();
        try {
            assertFalse(iata.trim().equals(""));
            assertNotNull(iata);
            when(this.cityService.getByAttributeType(iata)).thenReturn(this.city);
            tk=this.cityService.getByAttributeType(iata);
            assertEquals(this.city,tk);
            assertNotNull(this.city);
            when(this.controller.getByOneCity(this.city.getIataCode())).thenReturn(status);
            status=this.controller.getByOneCity(iata);
            assertEquals(new ResponseEntity(this.city,HttpStatus.OK),status);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void getByOneCityTestException() {
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.cityService.getByAttributeType(this.city.getIataCode())).thenThrow(Exception.class);
            status1 = this.controller.getByOneCity(this.city.getIataCode());
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR), status1);
        }
    }

}
