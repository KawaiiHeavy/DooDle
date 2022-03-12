package com.doodle.repostitories;

import com.doodle.models.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GroupRepository extends CrudRepository<Group, UUID> {

}
