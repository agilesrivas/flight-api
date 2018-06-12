package com.utn.WebService.controller;

import com.utn.WebService.WebServiceApplication;
import com.utn.WebService.util.SessionData;
import com.utn.WebService.wrapper.UserWrapper;
import com.utn.tssi.tp5.Models.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private SessionData sessionData;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public UserController(SessionData sessionData) {
        this.sessionData = sessionData;
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        ResponseEntity status = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        User user = new User(userName, password);
        status = this.restTemplate.postForEntity("http://localhost:25100/user/", user, ResponseEntity.class);

        return status;
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        ResponseEntity status = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        User user = new User(userName, password);
        ResponseEntity<User> response = this.restTemplate.postForEntity("http://localhost:25100/user", user, User.class);
        user = response.getBody();

        if (user != null) {
            UserWrapper userWrapper = new UserWrapper(user);
            String sessionId = this.sessionData.addSession(userWrapper);
            WebServiceApplication.TOCKEN = sessionId;
            status = new ResponseEntity<String>(sessionId, HttpStatus.OK);

        } else {
            status = response;
        }

        return status;
    }

    @PostMapping(value = "/logout")
    public ResponseEntity logout(@RequestHeader("sessionId") String sessionId) {
        this.sessionData.removeSession(sessionId);
        WebServiceApplication.TOCKEN = null;
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
