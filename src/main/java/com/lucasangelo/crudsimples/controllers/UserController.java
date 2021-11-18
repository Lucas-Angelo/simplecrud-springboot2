package com.lucasangelo.crudsimples.controllers;

import java.util.List;

import com.lucasangelo.crudsimples.models.User;
import com.lucasangelo.crudsimples.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        List<User> users = this.userService.findAll();
        return users;
    }
}
