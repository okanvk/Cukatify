package com.okanciftci.cukatify.services.abstr;

import com.okanciftci.cukatify.entities.mongo.Role;

public interface RoleService {

    Role getRoleByName(String name);
}
