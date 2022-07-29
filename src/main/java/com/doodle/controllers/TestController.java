package com.doodle.controllers;

import com.doodle.dto.TestDTO;
import com.doodle.services.TestService;
import com.doodle.services.impl.TestServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/test")
@AllArgsConstructor
public class TestController {

    private TestService testService;

    @PostMapping("/add")
    public ResponseEntity<TestDTO.Read> createTest(@RequestBody TestDTO.Create testDTO){
        TestDTO.Read test = testService.createTest(testDTO);
        return new ResponseEntity<>(test, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTest(@PathVariable UUID id){
        testService.deleteTest(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<TestDTO.Read>> findAllTests(){
        Set<TestDTO.Read> tests = testService.getAllTests();
        return new ResponseEntity<>(tests, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<TestDTO.Read> updateTest(@RequestBody TestDTO.Read testDTO){
        TestDTO.Read test = testService.updateTest(testDTO);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @GetMapping("/allPageable")
    public ResponseEntity<Page<TestDTO.Read>> getAllTestsPaging(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<TestDTO.Read> testPage = testService.getAllTestsPageable(paging);
        return new ResponseEntity<>(testPage, HttpStatus.OK);
    }
}
