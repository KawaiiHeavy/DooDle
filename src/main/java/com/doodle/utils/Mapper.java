package com.doodle.utils;

import com.doodle.dto.*;
import com.doodle.models.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {

    public TestDTO.Create mapToCreatedTestDTO(Test test) {
        return new TestDTO.Create(
                test.getTitle(),
                mapToReadUserDTO(test.getCreator()),
                test.getMaxBall(),
                test.getSeconds()
        );
    }

    public TestDTO.Read mapToReadTestDTO(Test test) {
        return new TestDTO.Read(
                test.getId(),
                test.getTitle(),
                mapToReadUserDTO(test.getCreator()),
                test.getMaxBall(),
                test.getSeconds()
        );
    }

    public UserDTO.Create mapToCreatedUserDTO(User user){
        return new UserDTO.Create(
                user.getNickname(),
                user.getPassword(),
                user.getEmail(),
                user.getRoles().stream().map(this::mapToReadRoleDTO).collect(Collectors.toSet())
        );
    }

    public UserDTO.Read mapToReadUserDTO(User user){
        return new UserDTO.Read(
                user.getId(),
                user.getNickname(),
                user.getPassword(),
                user.getRoles().stream().map(this::mapToReadRoleDTO).collect(Collectors.toSet())
        );
    }

    public RoleDTO mapToReadRoleDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getRole_id());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    public Test mapToTest(TestDTO.Create testDTO){
        Test test = new Test();
        test.setTitle(testDTO.getTitle());
        test.setCreator(mapToUser(testDTO.getCreator()));
        test.setSeconds(testDTO.getSeconds());
        test.setMaxBall(testDTO.getMaxBall());
        return test;
    }

    public Test mapToTest(TestDTO.Read testDTO){
        Test test = new Test();
        test.setId(testDTO.getId());
        test.setTitle(testDTO.getTitle());
        test.setCreator(mapToUser(testDTO.getCreator()));
        test.setSeconds(testDTO.getSeconds());
        test.setMaxBall(testDTO.getMaxBall());
        return test;
    }

    public User mapToUser(UserDTO.Read userDTO){
        User user = new User();
        System.out.println(userDTO);
        user.setId(userDTO.getId());
        user.setNickname(userDTO.getNickname());
        user.setPassword(userDTO.getPassword());
        user.setPhone(user.getPhone());
        user.setRoles(userDTO.getRoles().stream().map(this::mapToRole).collect(Collectors.toSet()));
        return user;
    }

    public User mapToUser(UserDTO.Create userDTO){
        User user = new User();
        user.setNickname(userDTO.getNickname());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhone(user.getPhone());
        user.setRoles(userDTO.getRoles().stream().map(this::mapToRole).collect(Collectors.toSet()));
        return user;
    }

    public Role mapToRole(RoleDTO roleDTO){
        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setRole_id(role.getRole_id());
        return role;
    }

    public QuestionDTO.Create mapToCreatedQuestionDTO (Question question) {
        return new QuestionDTO.Create(
                question.getQuestionText(),
                question.getScoreWeight(),
                question.getImageUrl()
        );
    }

    public QuestionDTO.Read mapToReadQuestionDTO (Question question) {
        return new QuestionDTO.Read(
                question.getId(),
                question.getQuestionText(),
                question.getScoreWeight(),
                question.getImageUrl()
        );
    }

    public Question mapToQuestion(QuestionDTO.Create questionDTO) {
        Question question = new Question();
        question.setQuestionText(questionDTO.getQuestionText());
        question.setImageUrl(questionDTO.getImageUrl());
        return question;
    }

    public Question mapToQuestion(QuestionDTO.Read questionDTO) {
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setQuestionText(questionDTO.getQuestionText());
        question.setImageUrl(questionDTO.getImageUrl());
        return question;
    }

    public AnswerDTO.Create mapToCreatedAnswerDTO(Answer answer) {
        return new AnswerDTO.Create(
                answer.getAnswerText(),
                answer.getCorrect()
        );
    }

    public AnswerDTO.Read mapToReadAnswerDTO(Answer answer){
        return new AnswerDTO.Read(
                answer.getId(),
                answer.getAnswerText(),
                answer.getCorrect()
        );
    }

    public Answer mapToAnswer(AnswerDTO.Create answerDTO){
        Answer answer = new Answer();
        answer.setAnswerText(answerDTO.getAnswerText());
        answer.setCorrect(answerDTO.getCorrect());
        return answer;
    }

    public Answer mapToAnswer(AnswerDTO.Read answerDTO){
        Answer answer = new Answer();
        answer.setId(answerDTO.getId());
        answer.setAnswerText(answerDTO.getAnswerText());
        answer.setCorrect(answerDTO.getCorrect());
        return answer;
    }

    public GroupDTO.Create mapToCreatedGroupDTO(Group group) {
        return new GroupDTO.Create(
                group.getTitle(),
                mapToReadUserDTO(group.getGroupLeader())
        );
    }

    public GroupDTO.Read mapToReadGroupDTO(Group group) {
        return new GroupDTO.Read(
                group.getId(),
                group.getTitle(),
                mapToReadUserDTO(group.getGroupLeader())
        );
    }

    public Group mapToGroup(GroupDTO.Create groupDTO) {
        Group group = new Group();
        group.setTitle(group.getTitle());
        group.setGroupLeader(mapToUser(groupDTO.getGroupLeader()));
        return group;
    }

    public Group mapToGroup(GroupDTO.Read groupDTO) {
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setTitle(group.getTitle());
        group.setGroupLeader(mapToUser(groupDTO.getGroupLeader()));
        return group;
    }

    public ResultDTO.Create mapToCreatedResultDTO(Result result) {
        return new ResultDTO.Create(
                mapToReadTestDTO(result.getTest()),
                mapToReadUserDTO(result.getParticipant()),
                result.getScore()
        );
    }

    public ResultDTO.Read mapToReadResultDTO(Result result) {
        return new ResultDTO.Read(
                result.getId(),
                mapToReadTestDTO(result.getTest()),
                mapToReadUserDTO(result.getParticipant()),
                result.getScore()
        );
    }

    public Result mapToResult(ResultDTO.Create resultDTO) {
        Result result = new Result();
        result.setTest(mapToTest(resultDTO.getTest()));
        result.setParticipant(mapToUser(resultDTO.getParticipant()));
        result.setScore(resultDTO.getScore());
        return result;
    }

    public Result mapToResult(ResultDTO.Read resultDTO) {
        Result result = new Result();
        result.setId(resultDTO.getId());
        result.setTest(mapToTest(resultDTO.getTest()));
        result.setParticipant(mapToUser(resultDTO.getParticipant()));
        result.setScore(resultDTO.getScore());
        return result;
    }
}
