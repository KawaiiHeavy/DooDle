package com.doodle.repostitories;

import com.doodle.dto.ResultDTO;
import com.doodle.models.ImageModel;
import com.doodle.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    @Query("SELECT q.image from Question q where q.id =:questionId")
    Optional<ImageModel> getImageFromQuestion(UUID questionId);

    @Modifying
    @Query("update Question q set q.image = :img where q.id = :questionId")
    void addImageToQuestion(UUID questionId, ImageModel img);
}
