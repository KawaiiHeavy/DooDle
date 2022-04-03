package com.doodle.repostitories;

import com.doodle.models.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupRepository extends CrudRepository<Group, UUID> {

}
