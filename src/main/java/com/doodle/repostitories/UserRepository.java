package com.doodle.repostitories;

import com.doodle.dto.UserDTO;
import com.doodle.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByNickname(String username);

    Boolean existsByNickname(String nickname);

    Boolean existsByEmail(String email);

    UserDTO.Read getByNickname(String nickname);
}
