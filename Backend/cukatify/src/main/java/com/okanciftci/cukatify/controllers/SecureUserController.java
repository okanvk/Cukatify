package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.entities.mongo.User;
import com.okanciftci.cukatify.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/secureusers")
@CrossOrigin
public class SecureUserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUser(Principal principal){

        User user = userService.getUser(principal.getName());

        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){

        User newUser = userService.updateUser(user);

        return new ResponseEntity<User>(newUser,HttpStatus.OK);

    }




}
