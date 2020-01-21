package com.angelo.eshop.web.controller;

import com.angelo.eshop.model.User;
import com.angelo.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/findAllUsers")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }
}

