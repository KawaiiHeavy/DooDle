package com.doodle.controllers;

import com.doodle.authorization.jwt.JwtUtils;
import com.doodle.authorization.pojo.JwtResponse;
import com.doodle.authorization.pojo.LoginRequest;
import com.doodle.authorization.pojo.MessageResponse;
import com.doodle.authorization.pojo.SignUpRequest;
import com.doodle.models.*;
import com.doodle.repostitories.RoleRepository;
import com.doodle.repostitories.UserRepository;
import com.doodle.services.UserDetailsImpl;
import com.doodle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getNickname(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        JwtResponse response = new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){

        if (userRepository.existsByNickname(signUpRequest.getNickname())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is busy"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is busy"));
        }

        User user = new User(signUpRequest.getNickname(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> reqRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "ADMIN":
                        Role adminRole = roleRepository
                                .findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roles.add(adminRole);

                        break;

                    case "TRAINER":
                        Role trainerRole = roleRepository
                                .findByName(ERole.TRAINER)
                                .orElseThrow(() -> new RuntimeException("Error, Role TRAINER is not found"));
                        roles.add(trainerRole);

                        break;

                    case "STUDENT":
                        Role studentRole = roleRepository
                                .findByName(ERole.STUDENT)
                                .orElseThrow(() -> new RuntimeException("Error, Role STUDENT is not found"));
                        roles.add(studentRole);

                        break;

                    default:
                        Role userRole = roleRepository
                                .findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userService.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }

}
