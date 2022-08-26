package com.doodle.services.impl;

import com.doodle.dto.UserDTO;
import com.doodle.exceptions.QuestionNotFoundException;
import com.doodle.exceptions.UserNotFoundException;
import com.doodle.models.User;
import com.doodle.repostitories.RoleRepository;
import com.doodle.repostitories.UserRepository;
import com.doodle.services.UserService;
import com.doodle.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private Mapper mapper;

    public UserDTO.Read createUser(UserDTO.Create userDTO) {
        userDTO.setRoles(userDTO.getRoles()
                .stream()
                .map(roleDTO -> mapper.mapToReadRoleDTO(roleRepository.findByName(roleDTO.getName()).get()))
                .collect(Collectors.toSet()));
        return mapper.mapToReadUserDTO(
                userRepository.save(
                        mapper.mapToUser(userDTO)
                )
        );
    }

    @Override
    public UserDTO.Read findUserById(UUID id) {
        return mapper.mapToReadUserDTO(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found")));
    }

    @Override
    public void deleteUser(UUID id) {
        User user = userRepository.getById(id);
        userRepository.delete(user);
    }

    @Override
    public UserDTO.Read findUserByNickname(String nickname) {
        return mapper.mapToReadUserDTO(userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UserNotFoundException("User by nickname " + nickname + " was not found")));
    }

    public Set<UserDTO.Read> getAllUsers() {
        return userRepository.findAll().stream()
                .map(mapper::mapToReadUserDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Page<UserDTO.Read> getAllUsersPageable(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page.map(mapper::mapToReadUserDTO);
    }

}