package com.example.polls.controller;

import com.example.polls.model.*;
import com.example.polls.service.PollService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin("http://localhost:3000")
public class PollController {
    @Autowired
    private PollService pollService;
    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody Question question){
        try {
            String result = pollService.createQuestion(question);
            if (result.contains("created")) {
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            }else return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Question question){
        try {
            String result = pollService.update(question);
            if (result.contains("update")) {

                return new ResponseEntity<>(result, HttpStatus.OK);
            }return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @DeleteMapping("/")
    public ResponseEntity<String> deleteQuestion(@RequestParam int id){
        try {
            String result = pollService.deleteQuestion(id);
            if (result.contains("deleted")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAll(){
        try {
            return new ResponseEntity<>(pollService.getAllQuestions(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id){
         try {
             Question question = pollService.getQuestionById(id);
             if (question != null) {
                 return new ResponseEntity<>(question, HttpStatus.OK);
             }else return new ResponseEntity("The question is not exists",HttpStatus.NOT_FOUND);
         } catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
    @GetMapping("/answer-user/{id}")
    public ResponseEntity<List<AnswerResponse>> getAllAnswerByUserId(@PathVariable int id){
        try {
            List<AnswerResponse> list = pollService.getAllAnswerByUserId(id);
            if (!list.isEmpty()) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }return new ResponseEntity("The are not have answer",HttpStatus.NOT_FOUND);
            } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all-answers")
    public ResponseEntity<List<Answer>> getAllAnswer(){
        try {
            return new ResponseEntity<>(pollService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("answer")
    public ResponseEntity<String> answerQuestion(@RequestBody String answerQu) throws JsonProcessingException {
       try {

           Answer answer = objectMapper.readValue(answerQu, Answer.class);
          String answer1 =  pollService.answerQuestion(answer);
          if (answer1.contains("sending")){
               return new ResponseEntity(answer1,HttpStatus.OK);
           } else return new ResponseEntity<>( answer1,HttpStatus.BAD_REQUEST);
       } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
       }
    @GetMapping("/count-answer-user/{id}")   // no client
    public ResponseEntity<Integer> howManyQuestionUserAnswerById(@PathVariable int id){
        try {
            if(pollService.getQuestionById(id) != null) {
            return new ResponseEntity<>(pollService.howManyQuestionUserAnswerById(id), HttpStatus.OK);
        }else return new ResponseEntity("The question_id " + id + " is not exists", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/number-answer-question/{id}")
    public ResponseEntity<List<NumberAnswerResponse>> howManyUsersChooseEachOfQuestionOption(@PathVariable int id){
        try {
            if (pollService.getQuestionById(id) != null){
            return new ResponseEntity<>(pollService.howManyUsersChooseEachOfQuestionOption(id),HttpStatus.OK);
            }else return new ResponseEntity("The question_id " + id + " is not exists", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
@GetMapping("/how-many-answer/{id}")
    public ResponseEntity<Integer> howManyAnswerTheQuestion(@PathVariable int id) {
        try {
            if (pollService.getQuestionById(id) != null){
           return new ResponseEntity<>( pollService.howManyAnswerTheQuestion(id),HttpStatus.OK);

        }else return new ResponseEntity("The question_id " + id + " is not exists", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
@GetMapping("/all-question-and-number-answer")
    public ResponseEntity<List<QuestionAndNumberUser>> allQuestionAndNumberOfUsersAnswer(){
        try{
return new ResponseEntity<>(pollService.allQuestionAndNumberOfUsersAnswer(),HttpStatus.OK);
        } catch (Exception e) {

        return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
@DeleteMapping("/delete_answer/")
public  ResponseEntity  deleteAnswerByUser(@RequestParam int id){
        try {
            pollService.deleteAnswerByUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}
