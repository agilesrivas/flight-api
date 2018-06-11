package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CountryService;
import com.ApiVuelos.ApiVuelos.service.StateService;
import com.ApiVuelos.ApiVuelos.service.TicketService;
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
public class StateControllerTest extends TestCase {

    @Mock
    private StateService service;
    @Mock
    private CountryService countryService;
    @InjectMocks
    private StateController controller;

    Country ct =new Country(1,"Argentina","AR");
    State st=new State(1,"state","AR-B",ct);
    List<State> listSt = new ArrayList<State>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this. listSt.add(this.st);
    }

    @Test
    public void addTest(){

        ResponseEntity status=new ResponseEntity(HttpStatus.OK);

        try {


            assertFalse(this.st.validateNullEmptyIdentifier());

            String [] code=this.st.getIataCode().split("-");
            assertEquals(2,code.length);
            assertNotNull(code[0]);
            assertNotNull(code[1]);


            assertFalse(code[0].trim().equals(""));
            assertFalse(code[1].trim().equals(""));



            when(this.countryService.getByAttributeType(code[0])).thenReturn(this.ct);
            Country cte=this.countryService.getByAttributeType(code[0]);

            assertEquals(cte,this.ct);
            verify(this.st,times(1)).setCountry(cte);
            verify(this.st,times(1)).setIataCode(code[0] + "-" + code[1]);
            assertEquals(cte,this.ct);
            assertEquals(code[0] + "-" + code[1] ,this.st.getIataCode());
            assertFalse(this.st.validateNullEmpty());
            when(this.service.newObject(this.st)).thenReturn(this.st);
            State Bend=this.service.newObject(this.st);
            assertEquals(Bend,this.st);

            when(this.controller.add(this.listSt)).thenReturn(status);
            ResponseEntity statusOk=this.controller.add(this.listSt);
            assertEquals(statusOk,status);
        }
        catch(Exception e){

        }
    }
    @Test
    public void updateTest(){

        ResponseEntity status=new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            assertFalse(this.st.validateNullEmptyIdentifier());

            String [] code=this.st.getIataCode().split("-");
            assertEquals(2,code.length);
            assertNotNull(code[0]);
            assertNotNull(code[1]);


            assertFalse(code[0].trim().equals(""));
            assertFalse(code[1].trim().equals(""));



            when(this.countryService.getByAttributeType(code[0])).thenReturn(this.ct);
            Country cte=this.countryService.getByAttributeType(code[0]);

            assertEquals(cte,this.ct);
            verify(this.st,times(1)).setCountry(cte);


            assertEquals(code[0] + "-" + code[1] ,this.st.getIataCode());
            assertFalse(this.st.validateNullEmpty());
            when(this.service.newObject(this.st)).thenReturn(this.st);
            State Bend=this.service.newObject(this.st);
            assertEquals(Bend,this.st);

            when(this.controller.add(this.listSt)).thenReturn(status);
            ResponseEntity statusOk=this.controller.add(this.listSt);
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
            when(this.service.getAll()).thenReturn(this.listSt);
            List<State>list=this.service.getAll();
            assertEquals(this.listSt.size(),list.size());
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
        State tk=null;
        assertNull(tk);
        String iata=this.st.getIataCode();
        try {
            assertFalse(iata.trim().equals(""));
            assertNotNull(iata);
            when(this.service.getByAttributeType(iata)).thenReturn(this.st);
            tk=this.service.getByAttributeType(iata);
            assertEquals(this.st,tk);
            assertNotNull(this.st);
            when(this.controller.getByOneState(this.ct.getIsoCode())).thenReturn(status);
            status=this.controller.getByOneState(iata);
            assertEquals(new ResponseEntity(this.st,HttpStatus.OK),status);
        }
        catch(Exception e){

        }
    }

}
