package com.ApiVuelos.ApiVuelos.controller;

import com.ApiVuelos.ApiVuelos.service.UserService;
import com.utn.tssi.tp5.Models.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity add(@RequestBody User user) {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            if(user != null && !(user.validateNullEmpty())) {
                this.userService.newObject(user);
                status = new ResponseEntity(HttpStatus.OK);
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }

    @PostMapping(value = "")
    public ResponseEntity<User> getUser(@RequestBody User user) {

        ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            if(user != null && !(user.validateNullEmpty())) {
                User userDB = this.userService.getByAttributeTypeUser(user.getName(), user.getPassword());
                status = new ResponseEntity<User>(userDB, HttpStatus.OK);
            }
        } catch(Exception e) {
            status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return status;
    }
}
