package com.raine.springboot.demo.service;

import com.raine.springboot.demo.DemoApplicationTests;
import com.raine.springboot.demo.domain.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class IPermissionServiceTest extends DemoApplicationTests {

    @Autowired
    IPermissionService permissionService;

    @Test
    void getPermissionById() {
        Permission permission = permissionService.getPermissionById(1);
        System.out.println(permission);
    }
}