package com.doodle.services;

import com.doodle.models.Test;
import com.doodle.models.User;
import com.doodle.repostitories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).get();
    }

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserByNickname(String username){
        return userRepository.findByNickname(username);
    }

    public User getUserById(UUID id){ return userRepository.findById(id).get(); }

    public User save(User user){
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
