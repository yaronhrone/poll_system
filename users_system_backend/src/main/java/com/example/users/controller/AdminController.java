package com.example.users.controller;


import com.example.users.model.*;
import com.example.users.service.PollClientService;
import com.example.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000")
public class AdminController {
    @Autowired
    private PollClientService pollClientService;
    @Autowired
    private UserService userService;


    // Question
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-question")
    public ResponseEntity<String> createQuestion(@RequestBody Question question) {
        System.out.println("create question from admin");
        try {
            String result = pollClientService.createQuestion(question);
            System.out.println(result + " from admin create question");
            if (result.contains("created")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

              return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-question/")
    public ResponseEntity<String> deleteQuestion(@RequestParam int id) {
        try {
            String result = pollClientService.deleteQuestion(id);
            if (result.contains("deleted")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-question")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question) {

        try {
            String result = pollClientService.updateQuestion(question);
            if (result.contains("update")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/result-question/{id}")
    public ResponseEntity<List<NumberAnswerResponse>>  howManyUsersChooseEachOfQuestionOption(@PathVariable int id) {
        try {
            return new ResponseEntity<>(pollClientService.howManyUsersChooseEachOfQuestionOption(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(pollClientService.getAllQuestions(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-answers")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        try {
            return new ResponseEntity<>(pollClientService.getAllAnswers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-questions-and-answers")
    public ResponseEntity<List<QuestionAndNumberAnswer>> getAllQuestionsAndAnswers() {
        try {
            return new ResponseEntity<>(pollClientService.getAllQuestionsAndAnswers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Users
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-user/")
    public ResponseEntity<String> deleteUser(@RequestParam int id) {
        try {
            System.out.println(id + " id user deleted");
            String result = userService.deleteById(id);
            if (result.contains("deleted")) {

                return new ResponseEntity<>(result, HttpStatus.OK);
            } else return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all-users")
    public ResponseEntity<List<UserCustomer>> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/answer-user/{username}")
    public ResponseEntity<List<AnswerResponse>> getAllAnswerByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(pollClientService.allAnswerByUsername(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/answer-user/{id}")
    public ResponseEntity<List<AnswerResponse>> getAllAnswerById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(pollClientService.allAnswerByUserId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
