package com.doodle.services;

import com.doodle.models.Test;
import com.doodle.models.User;
import com.doodle.repostitories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private TestService testService;

    private final UserRepository userRepository;

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).get();
    }

    public Iterable<User> getUsers(){
        Iterable<User> users = userRepository.findAll();
        for (User user: users){
            user.setOwnedTests(null);
        }
        return users;
    }

    public User getUserByNickname(String username){
        return userRepository.findByNickname(username).get();
    }

    public User getUserById(UUID id){ return userRepository.findById(id).get(); }

    public User save(User user){
        if (user.getNickname().equals("user")){
            List<Test> result = StreamSupport.stream(testService.getTests().spliterator(), false)
                            .collect(Collectors.toList());
            user.setOwnedTests(result);
            System.out.println(user.getOwnedTests());
        }
        return userRepository.save(user);
    }

    public void delete(User user){
        this.userRepository.delete(user);
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> getRandomUser(){
        return userRepository.getRandomUser();
    }

    public Set<Test> getTestsByUser(UUID userId){
        return userRepository.findOwnedTestsById(userId);
    }
}
