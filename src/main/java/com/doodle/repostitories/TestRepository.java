package com.doodle.repostitories;

import com.doodle.models.Test;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TestRepository extends CrudRepository<Test, UUID> {
}
