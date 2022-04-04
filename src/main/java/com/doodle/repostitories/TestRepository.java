package com.doodle.repostitories;

import com.doodle.models.Question;
import com.doodle.models.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TestRepository extends CrudRepository<Test, UUID> {

    List<Test> findByTitle(String title);

}
