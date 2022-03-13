package com.doodle.controllers;

import com.doodle.models.User;
import com.doodle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/save")
    public User createUser(@RequestBody User user){
        System.out.println(user.toString());
        return userService.save(user);
    }


    @GetMapping("/get")
    public Iterable<User> get(){
        Iterable<User> users = userService.getUsers();
        System.out.println(users);
        return users;
    }
}
