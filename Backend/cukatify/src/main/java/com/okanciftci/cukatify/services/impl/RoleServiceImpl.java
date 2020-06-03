package com.okanciftci.cukatify.services.impl;

import com.okanciftci.cukatify.entities.mongo.Role;
import com.okanciftci.cukatify.persistence.mongo.RoleRepository;
import com.okanciftci.cukatify.services.abstr.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
