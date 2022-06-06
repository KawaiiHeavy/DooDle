package com.doodle.controllers;

import com.doodle.models.Test;
import com.doodle.models.User;
import com.doodle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"", "/"})
    public Iterable<User> findAll(){
        return this.userService.findAll();
    }

    @PostMapping("/save")
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/getAllUsers")
    public Iterable<User> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/getTestsByUser/{userId}")
    public Set<Test> getTestsByUser(@PathVariable UUID userId){
        return userService.getTestsByUser(userId);
    }

    @GetMapping("/getUserByNickname/{nickname}")
    public User getUserByNickname(@PathVariable String nickname){
        return userService.getUserByNickname(nickname);
    }
}
