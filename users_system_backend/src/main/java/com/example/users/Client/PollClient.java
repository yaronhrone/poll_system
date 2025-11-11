package com.example.users.Client;

import com.example.users.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${external-api.poll.name}" , url = "${external-api.poll.url}")
public interface PollClient {
    @PostMapping ("/question/answer")
    String answer (@RequestBody Answer answer);
    @GetMapping("/question/{id}")
    Question getQuestion (@PathVariable int id);
    @GetMapping("/question/answer-user/{id}")
    List<AnswerResponse> allAnswerByUserId (@PathVariable int id);
    @GetMapping("question/count-answer-user/{id}")
    Integer howManyQuestionUserAnswerById(@PathVariable int id);
    @DeleteMapping("/question/delete-answer/")
    void deleteAnswer(@RequestParam int id);
    @PostMapping("/question")
    String createQuestion(@RequestBody Question question);
    @GetMapping("/question/number-answer-question/{id}")
    List<NumberAnswerResponse>  howManyUsersChooseEachOfQuestionOption(@PathVariable int id);
    @DeleteMapping("/question/")
    String deleteQuestion(@RequestParam int id);
    @PutMapping("/question")
    String updateQuestion(@RequestBody Question question);
    @GetMapping("/question/all-answers")
    List<Answer> getAllAnswers();
    @GetMapping("/question/all")
    List<Question> getAllQuestions();
    @GetMapping("/question/all-question-and-number-answer")
    List<QuestionAndNumberAnswer> getAllQuestionsAndAnswers();



}
