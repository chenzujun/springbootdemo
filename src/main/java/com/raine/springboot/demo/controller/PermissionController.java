package com.raine.springboot.demo.controller;

import com.alibaba.fastjson.JSON;
import com.raine.springboot.demo.domain.Permission;
import com.raine.springboot.demo.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenjun
 * @date 2020/4/29
 * @since V1.0.0
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        Permission permission = permissionService.getPermissionById(1);
        return JSON.toJSONString(permission);
    }
}
