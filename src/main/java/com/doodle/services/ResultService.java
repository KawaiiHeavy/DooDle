package com.doodle.services;

import com.doodle.dto.ResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface ResultService {

    Set<ResultDTO.Read> getAllResults();
    Page<ResultDTO.Read> getAllResultsPageable(Pageable pageable);
    ResultDTO.Read createResult(ResultDTO.Create resultDTO);
    ResultDTO.Read findResultById(UUID id);
    ResultDTO.Read updateResult(ResultDTO.Read resultDTO);
    void deleteResult(UUID id);

}
