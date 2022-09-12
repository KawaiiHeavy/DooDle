package com.doodle.repostitories;

import com.doodle.models.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageModelRepository extends JpaRepository<ImageModel, UUID> {

}
