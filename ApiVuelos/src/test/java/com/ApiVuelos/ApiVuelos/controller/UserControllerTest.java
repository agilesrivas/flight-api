package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.UserService;
import com.utn.tssi.tp5.Models.model.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends TestCase {

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    User user=new User(1,"Alekano","123");

    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addUserTest(){

        ResponseEntity status = new ResponseEntity(HttpStatus.OK);
        try
        {

            when(this.service.newObject(this.user)).thenReturn(this.user);
            status=this.controller.add(this.user);
            assertEquals(new ResponseEntity(HttpStatus.OK),status);
            assertNotNull(this.user);
            assertFalse(this.user.validateNullEmpty());

        }
        catch(Exception e){

        }
    }
    @Test
    public void addUserTestException() {

        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.newObject(this.user)).thenThrow(Exception.class);
            status1 = this.controller.add(this.user);

        } catch (Exception e) {
            ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            assertEquals(status,status1);

        }
    }
    @Test
    public void getUserTestException(){
        ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        try {
            when(this.service.getByAttributeTypeUser(this.user.getName(),this.user.getPassword())).thenThrow(Exception.class);
            status1 = this.controller.getUser(this.user);
        } catch (Exception e) {
            assertEquals(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR),status1);

        }

    }
    @Test
    public void getUserTestFeliz(){
        ResponseEntity status = new ResponseEntity(HttpStatus.OK);
        try
        {

            when(this.service.getByAttributeTypeUser(this.user.getName(),this.user.getPassword())).thenReturn(this.user);
            status=this.controller.getUser(this.user);
            assertEquals(new ResponseEntity(this.user,HttpStatus.OK),status);
            assertNotNull(this.user);
            assertFalse(this.user.validateNullEmpty());
        }
        catch(Exception e){

        }
    }
}
