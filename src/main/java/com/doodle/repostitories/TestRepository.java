package com.doodle.repostitories;

import com.doodle.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TestRepository extends JpaRepository<Test, UUID> {

    Set<Test> findByTitle(String title);

}
