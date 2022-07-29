package com.doodle.services.impl;

import com.doodle.dto.ResultDTO;
import com.doodle.models.Answer;
import com.doodle.models.Result;
import com.doodle.repostitories.ResultRepository;
import com.doodle.services.ResultService;
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
public class ResultServiceImpl implements ResultService {

    private ResultRepository resultRepository;
    private Mapper mapper;

//    public double calculateScoreForTest(TestBlank testBlank){
//        double score = 0;
//
//        for (QuestionBlank questionBlank: testBlank.getQuestionBlanks()){
//            List<Answer> answers = questionBlank.getAnswers();
//            Integer countOfRightAnswers = getCountOfRightAnswers(answers);
//            List<Answer> userAnswers = questionBlank.getUserAnswers();
//
//            System.out.println(questionBlank.getScoreWeight());
//            System.out.println(countOfRightAnswers);
//
//            score += calculateScoreForQuestion(answers, userAnswers, questionBlank.getScoreWeight());
//        }
//
//        return score;
//    }
//
//    public double calculateScoreForQuestion(List<Answer> answers, List<Answer> userAnswers, double scoreWeight){
//        double score = 0;
//        int correctAns = 0;
//        int totalTrueAns = 0;
//        int incorrectAns = 0;
//
//        System.out.println(answers);
//        System.out.println(userAnswers);
//
//        for (int i = 0; i < answers.size(); i++){
//            if (answers.get(i).getCorrect() && userAnswers.get(i).getCorrect()){
//               // score += (scoreWeight / answers.size());
//                correctAns += 1;
//                totalTrueAns += 1;
//            }
//            else if (answers.get(i).getCorrect() && !userAnswers.get(i).getCorrect()){
//                totalTrueAns += 1;
//                incorrectAns += 1;
//            }
//            else if (!answers.get(i).getCorrect() && userAnswers.get(i).getCorrect()){
//                incorrectAns += 1;
//            }
//        }
//        score = correctAns * (scoreWeight / totalTrueAns) - incorrectAns * (scoreWeight / (answers.size() - totalTrueAns));
//        if (score > 0) {
//            return score;
//        }
//        else return 0;
//    }
//
//    public Integer getCountOfRightAnswers(List<Answer> answers){
//        int count = 0;
//        for (Answer answer: answers){
//            if (answer.getCorrect()){
//                count += 1;
//            }
//        }
//        return count;
//    }

    public ResultDTO.Read createResult(ResultDTO.Create resultDTO) {
        return mapper.mapToReadResultDTO(
                resultRepository.save(
                        mapper.mapToResult(resultDTO)
                )
        );
    }

    @Override
    public ResultDTO.Read findResultById(UUID id) {
        return mapper.mapToReadResultDTO(resultRepository.getById(id));
    }

    @Override
    public ResultDTO.Read updateResult(ResultDTO.Read resultDTO) {
        return mapper.mapToReadResultDTO(
                resultRepository.save(
                        mapper.mapToResult(resultDTO)
                )
        );
    }

    public Set<ResultDTO.Read> getAllResults() {
        return resultRepository.findAll().stream()
                .map(mapper::mapToReadResultDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Page<ResultDTO.Read> getAllResultsPageable(Pageable pageable) {
        Page<Result> page = resultRepository.findAll(pageable);
        return page.map(mapper::mapToReadResultDTO);
    }

    @Transactional
    public void deleteResult(UUID id) {
        Result result = resultRepository.getById(id);
        resultRepository.delete(result);
    }
}
