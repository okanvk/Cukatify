package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.entities.mongo.User;
import com.okanciftci.cukatify.services.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/secureusers")
@CrossOrigin
@Slf4j
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

    @RequestMapping(value = "/toggle/{username}",method = RequestMethod.PATCH)
    public ResponseEntity<?> toggleUser(@PathVariable String username){

        try{
            boolean state = userService.toggleUser(username);
            return new ResponseEntity<Boolean>(state,HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/admin/{username}",method = RequestMethod.PATCH)
    public ResponseEntity<?> makeUserAdmin(@PathVariable String username){
        try{
            boolean state = userService.makeAdmin(username);
            return new ResponseEntity<Boolean>(state,HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }



}
