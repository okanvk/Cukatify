package com.okanciftci.cukatify.unit_integration;

import com.okanciftci.cukatify.common.enums.RoleNames;
import com.okanciftci.cukatify.entities.mongo.Role;
import com.okanciftci.cukatify.services.abstr.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    void findRole(){

        // given
        String name = RoleNames.ADMIN.name();

        // when
        Role role = roleService.getRoleByName(name);

        // then
        Assertions.assertEquals(role.getName(),RoleNames.ADMIN.name());


    }
}
