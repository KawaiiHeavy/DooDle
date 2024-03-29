package com.doodle.repostitories;

import com.doodle.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE Answers q set q.question.id = :questionId where q.id = :answerId")
    void saveAnswerToQuestion(@Param("answerId") UUID answerId, @Param("questionId") UUID questionId);

}
