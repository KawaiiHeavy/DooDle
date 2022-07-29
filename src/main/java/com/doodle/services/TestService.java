package com.doodle.services;

import com.doodle.dto.TestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface TestService {

    Set<TestDTO.Read> getAllTests();
    Page<TestDTO.Read> getAllTestsPageable(Pageable pageable);
    TestDTO.Read createTest(TestDTO.Create testDTO);
    TestDTO.Read findTestById(UUID id);
    TestDTO.Read updateTest(TestDTO.Read testDTO);
    void deleteTest(UUID id);
    
}
