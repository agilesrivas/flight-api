package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.AirportService;
import com.ApiVuelos.ApiVuelos.service.CityService;
import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

import static jdk.internal.org.objectweb.asm.TypeReference.INSTANCEOF;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class AirportControllerTest extends TestCase {

    @Mock
    private CityService cityService;
    @Mock
    private AirportService airportService;
    @InjectMocks
    protected AirportController controller;

    List<Airport> airports=new ArrayList<Airport>();

    Country ct =new Country(1,"Argentina","AR");
    State st=new State(1,"state","AR-B",ct);
    City city=new City(1,"Buenos Aires","AR-B-7600",st);
    Airport air=new Airport(1,"AreolineasArgentinas","AR-B-7600-MDQ",city,-222,222);
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        this.airports.add(air);
        this.airports.add(air);
        this.airports.add(air);
    }


  @Test
  public void testAddVerifyThrows(){
      ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
      try {
          when(this.airportService.newObject(this.air)).thenThrow(Exception.class);
          status1 = this.controller.add(this.airports);
      } catch (Exception e) {
          assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

      }
  }
  @Test
  public void addTestException(){
      ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
      try {
          when(this.cityService.getByAttributeType(this.air.getIataCode())).thenThrow(Exception.class);
          status1 = this.controller.add(this.airports);
      } catch (Exception e) {
          assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

      }
  }

  @Test
    public void addTest(){

        ResponseEntity status=new ResponseEntity(HttpStatus.OK);

        try {
            String iata=this.air.getIataCode().replaceAll("[^a-zA-Z0-9]","-");
            String [] code=iata.split("-");
            assertEquals(4,code.length);
            assertNotNull(code[0]);
            assertNotNull(code[1]);
            assertNotNull(code[2]);
            assertNotNull(code[3]);
            assertFalse(code[0].trim().equals(""));
            assertFalse(code[1].trim().equals(""));
            assertFalse(code[2].trim().equals(""));
            assertFalse(code[3].trim().equals(""));

            when(this.cityService.getByAttributeType(code[0]+"-"+code[1]+"-"+code[2])).thenReturn(this.city);
            City ct=this.cityService.getByAttributeType(code[0]+"-"+code[1]+"-"+code[2]);
            assertEquals(ct,this.air.getCity());
           assertEquals(ct.getIataCode()+"-"+code[3],this.air.getIataCode());


            assertFalse(this.air.validateNullEmpty());

            when(this.airportService.newObject(this.air)).thenReturn(this.air);
            Airport Bend=this.airportService.newObject(this.air);
            assertEquals(Bend,this.air);

            when(this.controller.add(this.airports)).thenReturn(status);
            ResponseEntity statusOk=this.controller.add(this.airports);
            assertEquals(statusOk,status);


        }
        catch(Exception e){
            e.printStackTrace();
        }
  }


    @Test
    public void updateTest(){
        ResponseEntity status=new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            assertNotNull(this.air);
            when(this.airportService.getById(this.air.getId())).thenReturn(this.air);
            Airport airportdb=this.airportService.getById(this.air.getId());
            assertNotNull(airportdb);

            String iata=this.air.getIataCode().replaceAll("[^a-zA-Z0-9]","-");
            String [] code=iata.split("-");

            assertEquals(4,code.length);
            assertFalse(code[0].trim().equals(""));
            assertFalse(code[1].trim().equals(""));
            assertFalse(code[2].trim().equals(""));
            assertFalse(code[3].trim().equals(""));


            when(this.cityService.getByAttributeType(code[0]+"-"+code[1]+"-"+code[2])).thenReturn(this.city);
            City ct=this.cityService.getByAttributeType(code[0]+"-"+code[1]+"-"+code[2]);


            assertEquals(ct,this.air.getCity());
            assertEquals(this.air.getIataCode(),airportdb.getIataCode());
            assertFalse(this.air.validateNullEmpty());

            when(this.airportService.newObject(this.air)).thenReturn(this.air);
            Airport airt=this.airportService.newObject(this.air);
            assertEquals(this.air,airt);


            when(this.controller.update(this.air)).thenReturn(status);
            ResponseEntity statusOk=this.controller.update(this.air);
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
            when(this.airportService.newObject(this.air)).thenThrow(Exception.class);
            status1 = this.controller.update(this.air);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void updateTstException2(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.airportService.getById(1l)).thenThrow(Exception.class);
            status1 = this.controller.update(this.air);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }

    @Test
    public void updateTestExceptionCase(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.cityService.getByAttributeType(this.air.getIataCode())).thenThrow(Exception.class);
            status1 = this.controller.update(this.air);
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
    public void removeTestException(){
        Long id= Long.valueOf(1);
        ResponseEntity ent=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        try {

            ResponseEntity status=this.controller.remove(id);


        }
        catch (Exception e){
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),ent);
        }
    }



    @Test
    public void getAllTest(){

        try {
            when(this.airportService.getAll()).thenReturn(this.airports);
            List<Airport>list=this.airportService.getAll();
            assertEquals(this.airports.size(),list.size());
            ResponseEntity status=this.controller.getAll();
            assertEquals(new ResponseEntity(list,HttpStatus.OK),status);
            assertEquals(3,list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void getAllTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.airportService.getAll()).thenThrow(Exception.class);
            status1 = this.controller.getAll();
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }

    @Test
    public void getByOneAirportTest(){
        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        Airport tk=null;
        assertNull(tk);
        String iata=this.air.getIataCode();
        try {
            assertFalse(iata.trim().equals(""));
            assertNotNull(iata);
            when(this.airportService.getByAttributeType(iata)).thenReturn(this.air);
            tk=this.airportService.getByAttributeType(iata);
            assertEquals(this.air,tk);
            assertNotNull(this.air);
            status=this.controller.getByOneAirport(iata);
            assertEquals(new ResponseEntity(this.air,HttpStatus.OK),status);

        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
    @Test
    public void getByOneAirportTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.airportService.getByAttributeType(this.air.getIataCode())).thenThrow(Exception.class);
            status1 = this.controller.getByOneAirport(this.air.getIataCode());
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }


}
