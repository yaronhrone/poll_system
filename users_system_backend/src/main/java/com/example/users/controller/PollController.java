package com.example.users.controller;


import com.example.users.model.Answer;
import com.example.users.model.AnswerResponse;
import com.example.users.model.Question;
import com.example.users.model.UserCustomer;
import com.example.users.service.PollClientService;
import com.example.users.service.UserService;
import com.example.users.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poll")
@CrossOrigin("http://localhost:3000")
public class PollController {
    @Autowired
    private PollClientService pollClientService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id){
            try{
              Question question =   pollClientService.getQuestion(id);
              if (question != null) {
                  return new ResponseEntity<>(question, HttpStatus.OK);
              }else return new ResponseEntity("The question withe id: " +id + " is not exists",HttpStatus.NOT_FOUND);
            } catch (Exception e) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)    ;    }
    }
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/answer")
    public ResponseEntity<String> answerQuestion(@RequestHeader(value = "Authorization") String token, @RequestBody Answer answer) {
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            UserCustomer user =  userService.getUserByUsername(username);
            answer.setUserId(user.getId());
            String result = pollClientService.answerQuestion(answer);
        System.out.println(answer + "answer");
            if (result.contains("sending")){
                return new ResponseEntity<>(result, HttpStatus.OK);
        }else return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
        @GetMapping("/answer-user-by-username")
                public ResponseEntity<List<AnswerResponse>> getAllAnswerByUsername(@RequestHeader(value = "Authorization") String token)  {
            try { String jwtToken = token.substring(7);
                String username = jwtUtil.extractUsername(jwtToken);
                List<AnswerResponse> result =pollClientService.allAnswerByUsername(username);
                if (result !=null) {
                    if (!result.isEmpty()) {
                        return new ResponseEntity<>(result, HttpStatus.OK);
                    }
                    return new ResponseEntity(" The user not have answer", HttpStatus.NOT_FOUND);
                }else return new ResponseEntity(" The user is not exists ",HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
        @GetMapping("/number-user-answer")
                public ResponseEntity<Integer> howManyQuestionUserAnswerById(@RequestHeader(value = "Authorization") String token){
                try {
                    String jwtToken = token.substring(7);
                    String username = jwtUtil.extractUsername(jwtToken);
                    return new ResponseEntity<>(pollClientService.howManyQuestionUserAnswerById(username),HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }


}
