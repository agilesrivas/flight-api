package com.utn.WebService.controller;

import com.utn.WebService.util.SessionData;
import com.utn.WebService.wrapper.UserWrapper;
import com.utn.tssi.tp5.Models.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    public  static SessionData sessionData;

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
    public ResponseEntity login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request){
        ResponseEntity status = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        User user = new User(userName, password);
        ResponseEntity<User> responseRest = this.restTemplate.postForEntity("http://localhost:25100/user", user, User.class);
        user = responseRest.getBody();

        if (user != null) {
            UserWrapper userWrapper = new UserWrapper(user);
            String sessionId = this.sessionData.addSession(userWrapper);

            HttpSession session = request.getSession(true);
            session.setAttribute("tocken", sessionId);
            status = new ResponseEntity<String>(sessionId, HttpStatus.OK);


        } else {
            status = responseRest;
        }

        return status;
    }

    @RequestMapping(value = "/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String tocken = (String) session.getAttribute("tocken");

        this.sessionData.removeSession(tocken);
        session.removeAttribute("tocken");
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
