package com.example.users.service;


import com.example.users.Client.PollClient;
import com.example.users.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollClientService {

    @Autowired
    private PollClient pollClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    public Question getQuestion(int id )  {
   return pollClient.getQuestion(id);


    }
    public String answerQuestion(Answer answer){
       if (userService.getUserById(answer.getUserId()) != null) {
              String answer1 = pollClient.answer(answer);

              return answer1 + " ,The answer is save in the system";
       }else return   " The answer is not save , the user withe id: " + answer.getUserId()+ " is not exists";
    }
    public List<AnswerResponse> allAnswerByUsername(String username){
        UserCustomer user = userService.getUserByUsername(username);
        if (user != null){

       return pollClient.allAnswerByUserId(user.getId());
        }return null;
    }
    public  List<AnswerResponse> allAnswerByUserId(int id){
        return pollClient.allAnswerByUserId(id);
    }
    public Integer howManyQuestionUserAnswerById(String username){
       return pollClient.howManyQuestionUserAnswerById(userService.getUserByUsername(username).getId());
    }
    public void deleteAnswer(int userId){
        pollClient.deleteAnswer(userId);
    }
    public String deleteQuestion(int id){
        return pollClient.deleteQuestion(id);
    }

    public String updateQuestion(Question question){
        return pollClient.updateQuestion(question);
    }
    public String createQuestion(Question question){
        return pollClient.createQuestion(question);
    }
    public List<Answer> getAllAnswers() {
        return pollClient.getAllAnswers();
    }
    public List<Question> getAllQuestions() {
        return pollClient.getAllQuestions();
    }
    public List<QuestionAndNumberAnswer> getAllQuestionsAndAnswers() {
        return pollClient.getAllQuestionsAndAnswers();
    }


    public List<NumberAnswerResponse> howManyUsersChooseEachOfQuestionOption(int id) {
        return pollClient.howManyUsersChooseEachOfQuestionOption(id);
    }
}