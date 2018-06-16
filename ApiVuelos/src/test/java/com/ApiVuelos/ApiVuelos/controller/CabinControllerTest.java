package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.CabinService;
import com.utn.tssi.tp5.Models.model.Cabin;
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
public class CabinControllerTest extends TestCase {

    @Mock
    private CabinService cabinService;
    @InjectMocks
    private CabinController controller;
    Cabin cabin=new Cabin(1,"Economica");
    List<Cabin> listCabin = new ArrayList<Cabin>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.listCabin.add(this.cabin);
    }
    @Test
    public void addTest(){
        ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try{
            assertFalse(this.cabin.validateNullEmpty());
            when(this.cabinService.newObject(this.cabin)).thenReturn(this.cabin);
            Cabin cb=this.cabinService.newObject(this.cabin);
            when(this.controller.add(this.listCabin)).thenReturn(status);
            status=this.controller.add(this.listCabin);
            assertEquals(new ResponseEntity(HttpStatus.OK),status);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void addtestVerifyException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.cabinService.newObject(this.cabin)).thenThrow(Exception.class);
            status1 = this.controller.add(this.listCabin);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }

    @Test
    public void updateTest(){
            ResponseEntity status=new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            Cabin getCab=new Cabin(1,"Economica");
            try
            {   assertNotNull(this.cabin);
                when(this.cabinService.getById(this.cabin.getId())).thenReturn(this.cabin);
                Cabin cab=this.cabinService.getById(this.cabin.getId());
                assertNotNull(cab);
                assertEquals(this.cabin,cab);
                assertFalse(this.cabin.validateNullEmpty());
                when(this.cabinService.newObject(this.cabin)).thenReturn(this.cabin);
                Cabin cb=this.cabinService.newObject(this.cabin);
                assertEquals(this.cabin,cb);
                when(this.controller.update(this.cabin)).thenReturn(status);
                status=this.controller.update(this.cabin);
                assertEquals(new ResponseEntity(HttpStatus.OK),status);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }
    @Test
    public void updateTestExceptionCase1(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.cabinService.newObject(this.cabin)).thenThrow(Exception.class);
            status1 = this.controller.update(this.cabin);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
    @Test
    public void updateTestverifyException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.cabinService.getById(1l)).thenThrow(Exception.class);
            status1 = this.controller.update(this.cabin);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

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
            when(this.cabinService.getAll()).thenReturn(this.listCabin);
            List<Cabin>list=this.cabinService.getAll();
            assertEquals(this.listCabin.size(),list.size());
            ResponseEntity status=this.controller.getAll();
            assertEquals(new ResponseEntity(list,HttpStatus.OK),status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getAllTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.cabinService.getAll()).thenThrow(Exception.class);
            status1 = this.controller.getAll();
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }


    @Test
    public void getByOneCabinTest(){
        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
        Cabin tk=null;
        assertNull(tk);
        String name=this.cabin.getName();
        try {
            assertFalse(name.trim().equals(""));
            assertNotNull(name);
            when(this.cabinService.getByAttributeType(name)).thenReturn(this.cabin);
            tk=this.cabinService.getByAttributeType(name);
            assertEquals(this.cabin,tk);
            assertNotNull(tk);
            status=this.controller.getByOneCabin(name);
            assertEquals(new ResponseEntity(this.cabin,HttpStatus.OK),status);

        }
        catch(Exception e){

        }
    }
    @Test
    public void getByOneCabinTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.cabinService.getByAttributeType(this.cabin.getName())).thenThrow(Exception.class);
            status1 = this.controller.getByOneCabin(this.cabin.getName());
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }
    }
}
