package com.utn.WebService.controller;

import com.utn.WebService.util.SessionData;
import com.utn.WebService.wrapper.UserWrapper;
import com.utn.tssi.tp5.Models.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    public static SessionData sessionData = new SessionData();

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/user");
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView register(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        ResponseEntity status = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        User user = new User(userName, password);
        status = this.restTemplate.postForEntity("http://localhost:25100/user/", user, ResponseEntity.class);

        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        modelAndView.setStatus(status.getStatusCode());

        return modelAndView;
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request){
        ResponseEntity status = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        User user = new User(userName, password);
        ResponseEntity<User> responseRest = this.restTemplate.postForEntity("http://localhost:25100/user", user, User.class);
        user = responseRest.getBody();

        ModelAndView modelAndView = new ModelAndView("redirect:/index");

        if (user != null) {
            UserWrapper userWrapper = new UserWrapper(user);
            String sessionId = this.sessionData.addSession(userWrapper);

            HttpSession session = request.getSession(true);
            session.setAttribute("tocken", sessionId);

            modelAndView.setStatus(HttpStatus.OK);

        } else {
            status = responseRest;
            modelAndView.setViewName("redirect:/user");
            modelAndView.setStatus(responseRest.getStatusCode());
        }

        return modelAndView;
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String tocken = (String) session.getAttribute("tocken");

        this.sessionData.removeSession(tocken);
        session.removeAttribute("tocken");

        ModelAndView modelAndView = new ModelAndView("redirect:/index");
        modelAndView.setStatus(HttpStatus.OK);

        return modelAndView;
    }

}
