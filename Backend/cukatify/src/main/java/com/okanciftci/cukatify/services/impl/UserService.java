package com.okanciftci.cukatify.services.impl;


import com.okanciftci.cukatify.entities.mongo.User;
import com.okanciftci.cukatify.exceptions.UsernameAlreadyExistsException;
import com.okanciftci.cukatify.persistence.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;




    public User saveUser(User newUser){
        try{
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            return userRepository.save(newUser);
        }catch(DuplicateKeyException e){
            throw new UsernameAlreadyExistsException("This username is already exists.");
        }
    }



    public User getUser(String username){

        try{
            User user = userRepository.findByUsername(username);
            user.setPassword("");
            return user;
        }catch (Exception e){
            return null;
        }

    }
    public User updateUser(User newUser){

        try{

            User updatedUser = userRepository.getById(newUser.getId().toString());

            updatedUser.setFullName(newUser.getFullName());
            updatedUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

            return userRepository.save(updatedUser);

        }catch(Exception e){
            throw new UsernameAlreadyExistsException("Exception");
        }
    }

}
