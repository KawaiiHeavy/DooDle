package com.doodle.controllers;

import com.doodle.dto.AnswerDTO;
import com.doodle.services.AnswerService;
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
@RequestMapping("api/answer")
@AllArgsConstructor
public class AnswerController {

    private AnswerService answerService;

    @PostMapping("/add")
    public ResponseEntity<AnswerDTO.Read> createAnswer(@RequestBody AnswerDTO.Create answerDTO){
        AnswerDTO.Read answer = answerService.createAnswer(answerDTO);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable UUID id){
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<AnswerDTO.Read>> findAllAnswers(){
        Set<AnswerDTO.Read> answers = answerService.getAllAnswers();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<AnswerDTO.Read> updateAnswer(@RequestBody AnswerDTO.Read answerDTO){
        AnswerDTO.Read answer = answerService.updateAnswer(answerDTO);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @GetMapping("/allPageable")
    public ResponseEntity<Page<AnswerDTO.Read>> getAllAnswersPaging(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<AnswerDTO.Read> answerPage = answerService.getAllAnswersPageable(paging);
        return new ResponseEntity<>(answerPage, HttpStatus.OK);
    }

}
