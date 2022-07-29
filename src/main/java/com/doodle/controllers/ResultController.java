package com.doodle.controllers;

import com.doodle.dto.ResultDTO;
import com.doodle.services.ResultService;
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
@RequestMapping("api/result")
@AllArgsConstructor
public class ResultController {

    private ResultService resultService;

    @PostMapping("/add")
    public ResponseEntity<ResultDTO.Read> createResult(@RequestBody ResultDTO.Create resultDTO) {
        ResultDTO.Read result = resultService.createResult(resultDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteResult(@PathVariable UUID id) {
        resultService.deleteResult(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<ResultDTO.Read>> findAllResults() {
        Set<ResultDTO.Read> results = resultService.getAllResults();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResultDTO.Read> updateResult(@RequestBody ResultDTO.Read resultDTO) {
        ResultDTO.Read result = resultService.updateResult(resultDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/allPageable")
    public ResponseEntity<Page<ResultDTO.Read>> getAllResultsPaging(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<ResultDTO.Read> resultPage = resultService.getAllResultsPageable(paging);
        return new ResponseEntity<>(resultPage, HttpStatus.OK);
    }

}
