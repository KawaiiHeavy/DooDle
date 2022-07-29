package com.doodle.services.impl;

import com.doodle.dto.TestDTO;
import com.doodle.exceptions.TestNotFoundException;
import com.doodle.models.Test;
import com.doodle.repostitories.TestRepository;
import com.doodle.services.TestService;
import com.doodle.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private TestRepository testRepository;
    private Mapper mapper;

    @Override
    public Set<TestDTO.Read> getAllTests() {
        return testRepository.findAll().stream()
                .map(mapper::mapToReadTestDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Page<TestDTO.Read> getAllTestsPageable(Pageable pageable) {
        Page<Test> test = testRepository.findAll(pageable);
        return test.map(mapper::mapToReadTestDTO);
    }

    public TestDTO.Read createTest(TestDTO.Create testDTO) {
        return mapper.mapToReadTestDTO(testRepository.save(mapper.mapToTest(testDTO)));
    }

    @Override
    public TestDTO.Read findTestById(UUID id) {
        return mapper.mapToReadTestDTO(testRepository.findById(id)
                .orElseThrow(() -> new TestNotFoundException("Test by id " + id + " was not found")));
    }

    public TestDTO.Read updateTest(TestDTO.Read testDTO) {
        return mapper.mapToReadTestDTO(testRepository.save(mapper.mapToTest(testDTO)));
    }

    public TestDTO.Read updateTest(TestDTO.Create testDTO) {
        return mapper.mapToReadTestDTO(testRepository.save(mapper.mapToTest(testDTO)));
    }

    @Transactional
    public void deleteTest(UUID id) {
        Test test = testRepository.getById(id);
        testRepository.delete(test);
    }

}
