package com.okanciftci.cukatify.services.impl;


import com.okanciftci.cukatify.common.enums.RoleNames;
import com.okanciftci.cukatify.entities.mongo.Role;
import com.okanciftci.cukatify.entities.mongo.User;
import com.okanciftci.cukatify.exceptions.UsernameAlreadyExistsException;
import com.okanciftci.cukatify.persistence.mongo.RoleRepository;
import com.okanciftci.cukatify.persistence.mongo.UserRepository;
import com.okanciftci.cukatify.services.abstr.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User saveSpotifyUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            return userRepository.save(user);
        }catch(DuplicateKeyException e){
            throw new UsernameAlreadyExistsException("This email is already exists.");
        }
    }


    public User saveUser(User newUser){
        try{
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            Role role = roleService.getRoleByName(RoleNames.USER.name());
            newUser.addRole(role);
            newUser.setActive(true);
            return userRepository.save(newUser);
        }catch(DuplicateKeyException e){
            throw new UsernameAlreadyExistsException("This email is already exists.");
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

    public boolean toggleUser(String username) {

        User user = userRepository.findByUsername(username);

        if(user != null){
            user.setActive(!user.isActive());
            userRepository.save(user);
        }else{
            throw new UsernameNotFoundException("Email not found.");
        }
        return true;
    }

    public boolean makeAdmin(String username) {

        User user = userRepository.findByUsername(username);

        if(user != null){
            Role role = roleService.getRoleByName(RoleNames.ADMIN.name());
            user.addRole(role);
            userRepository.save(user);
        }else{
            throw new UsernameNotFoundException("Email not found.");
        }
        return true;
    }

    public boolean checkActive(String username){
        User user = userRepository.findByUsername(username);

        if(user != null){
            return user.isActive();
        }else{
            throw new UsernameNotFoundException("Email not found.");
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
