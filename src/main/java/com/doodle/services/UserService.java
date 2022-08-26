package com.doodle.services;

import com.doodle.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface UserService {

    Set<UserDTO.Read> getAllUsers();
    Page<UserDTO.Read> getAllUsersPageable(Pageable pageable);
    UserDTO.Read createUser(UserDTO.Create userDTO);
    UserDTO.Read findUserById(UUID id);
    void deleteUser(UUID id);

    UserDTO.Read findUserByNickname(String nickname);
    
}
