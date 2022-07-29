package com.doodle.controllers;

import com.doodle.dto.QuestionDTO;
import com.doodle.services.QuestionService;
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
@RequestMapping("api/question")
@AllArgsConstructor
public class QuestionController {

    private QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<QuestionDTO.Read> createQuestion(@RequestBody QuestionDTO.Create questionDTO){
        QuestionDTO.Read question = questionService.createQuestion(questionDTO);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable UUID id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<QuestionDTO.Read>> findAllQuestions(){
        Set<QuestionDTO.Read> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<QuestionDTO.Read> updateQuestion(@RequestBody QuestionDTO.Read questionDTO){
        QuestionDTO.Read question = questionService.updateQuestion(questionDTO);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/allPageable")
    public ResponseEntity<Page<QuestionDTO.Read>> getAllQuestionsPaging(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<QuestionDTO.Read> questionPage = questionService.getAllQuestionsPageable(paging);
        return new ResponseEntity<>(questionPage, HttpStatus.OK);
    }
    
}
