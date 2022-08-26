package com.doodle.repostitories;

import com.doodle.dto.ResultDTO;
import com.doodle.models.Result;
import com.doodle.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ResultRepository extends JpaRepository<Result, UUID> {

    @Query("SELECT r from Result r where test.id =:testId")
    Set<ResultDTO.Read> findResultsByTest(UUID testId);

}
