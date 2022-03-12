package com.doodle.controllers;

import com.doodle.models.User;
import com.doodle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"", "/"})
    public Iterable<User> findAll(){
        System.out.println("It's working");
        return this.userService.findAll();
    }


}
