package com.raine.springboot.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenjun
 * @date 2020/4/29
 * @since V1.0.0
 */
@Controller
@RequestMapping("/")
public class IndexController {
    private static final Logger logger = LoggerFactory
            .getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index() {
        logger.debug("-------------index------------");
        return "index";
    }

    @RequestMapping("/login")
    public String toLogin() {
        logger.debug("-------------login------------");
        return "login";
    }

    @RequestMapping("/home")
    public String toHome() {
        Subject subject = SecurityUtils.getSubject();
        logger.debug("-------------home------------");
        logger.debug("-------------home------------superman="+subject.hasRole("superman"));
        logger.debug("-------------home------------admin="+subject.hasRole("admin"));
        return "home";
    }
}
