package com.doodle.repostitories;

import com.doodle.models.ERole;
import com.doodle.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(ERole name);
    Boolean existsByName(ERole name);

}
