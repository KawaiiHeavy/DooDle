package com.doodle.controllers;

import com.doodle.dto.UserDTO;
import com.doodle.models.Test;
import com.doodle.models.User;
import com.doodle.services.UserService;
import com.doodle.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDTO.Read> createUser(@RequestBody UserDTO.Create userDTO) {
        UserDTO.Read user = userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<UserDTO.Read>> getAllUsers() {
        Set<UserDTO.Read> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO.Read> getById(@PathVariable UUID id) {
        UserDTO.Read user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/allPageable")
    public ResponseEntity<Page<UserDTO.Read>> getAllAnswersPaging(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<UserDTO.Read> userPage = userService.getAllUsersPageable(paging);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findByNickname/{nickname}")
    public ResponseEntity<UserDTO.Read> findUserByNickname(@PathVariable String nickname) {
        System.out.println(nickname);
        UserDTO.Read user = userService.findUserByNickname(nickname);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
