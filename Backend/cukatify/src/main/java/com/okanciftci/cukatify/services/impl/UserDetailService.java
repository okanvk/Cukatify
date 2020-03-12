package com.okanciftci.cukatify.services.impl;

import com.okanciftci.cukatify.entities.mongo.User;
import com.okanciftci.cukatify.persistence.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null) new UsernameNotFoundException("User not found");
        return  user;

    }

    @Transactional
    public User loadUserById(String id) {

        User user = userRepository.getById(id);

        if(user == null) new UsernameNotFoundException("User not found");
        return  user;

    }

}
