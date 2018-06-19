package com.utn.WebService.controller;

import com.utn.WebService.wrapper.UserWrapper;
import com.utn.tssi.tp5.Models.model.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends TestCase {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    UserController userController;

    ModelAndView modelAndView;
    HttpServletRequest request;
    HttpSession session;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.modelAndView = new ModelAndView();
        this.request = mock(HttpServletRequest.class);
        this.session = new MockHttpSession();
    }

    @Test
    public void indexTest() {
        this.modelAndView.setViewName("/user");
        ModelAndView modelAndView = this.userController.index();
        //assertEquals(modelAndView, this.modelAndView);
    }

    @Test
    public void registerOKTest() {
        User user = new User("pepe", "pompin");

        when(this.restTemplate.postForEntity("http://localhost:25100/user/", user, ResponseEntity.class)).thenReturn(new ResponseEntity(HttpStatus.OK));
        this.modelAndView.setViewName("redirect:/user");
        this.modelAndView.setStatus(HttpStatus.OK);
        ModelAndView modelAndView = this.userController.register("pepe", "pompin");
        //assertEquals(modelAndView, this.modelAndView);
    }

    @Test
    public void registerEmptyTest() {
        User user = new User("", "");

        when(this.restTemplate.postForEntity("http://localhost:25100/user/", user, ResponseEntity.class)).thenReturn(new ResponseEntity(HttpStatus.NO_CONTENT));
        this.modelAndView.setViewName("redirect:/user");
        this.modelAndView.setStatus(HttpStatus.NO_CONTENT);

        ModelAndView modelAndView = this.userController.register("", "");
        //assertEquals(modelAndView, this.modelAndView);
    }

    @Test
    public void registerUserRegisteredTest() {
        User user = new User("pepe", "langa");

        when(this.restTemplate.postForEntity("http://localhost:25100/user/", user, ResponseEntity.class)).thenReturn(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR));
        this.modelAndView.setViewName("redirect:/user");
        this.modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        ModelAndView modelAndView = this.userController.register("pepe", "langa");
        //assertEquals(modelAndView, this.modelAndView);
    }

    @Test
    public void loginOKTest() {
        User user = new User("pepe", "pompin");
        UserWrapper userWrapper = new UserWrapper(user);
        ResponseEntity<User> status = new ResponseEntity<User>(user, HttpStatus.OK);

        when(this.restTemplate.postForEntity("http://localhost:25100/user", user, User.class)).thenReturn(status);
        when(this.request.getSession(true)).thenReturn(this.session);

        this.modelAndView.setViewName("redirect:/index");
        this.modelAndView.setStatus(HttpStatus.OK);
        ModelAndView modelAndView = this.userController.login("pepe", "pompin", this.request);
        //assertEquals(modelAndView, this.modelAndView);
    }

    @Test
    public void loginBadTest() {
        User user = new User("pepe", "");
        ResponseEntity<User> status = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

        when(this.restTemplate.postForEntity("http://localhost:25100/user", user, User.class)).thenReturn(status);

        this.modelAndView.setViewName("redirect:/user");
        this.modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        ModelAndView modelAndView = this.userController.login("pepe", "", this.request);
        //assertEquals(modelAndView, this.modelAndView);
    }

    @Test
    public void logoutTest() {
        this.session.setAttribute("tocken", "dasdsd555");
        when(this.request.getSession(true)).thenReturn(this.session);

        this.modelAndView.setViewName("redirect:/index");
        this.modelAndView.setStatus(HttpStatus.OK);
        ModelAndView modelAndView = this.userController.logout(this.request);
        //assertEquals(modelAndView, this.modelAndView);
    }
}
