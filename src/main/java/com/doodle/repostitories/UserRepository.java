package com.doodle.repostitories;

import com.doodle.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String username);
    Optional<User> findById(UUID id);
    Boolean existsByNickname(String nickname);
    Boolean existsByEmail(String email);
}
