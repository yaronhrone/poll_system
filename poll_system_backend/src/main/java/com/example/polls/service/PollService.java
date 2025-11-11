package com.example.polls.service;

import com.example.polls.model.*;
import com.example.polls.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {
@Autowired
    private PollRepository pollRepository;

    public String createQuestion(Question question) {
        Question question1 = pollRepository.getQuestionByName(question.getQuestion());
        if (question1 != null) {
        return "The question is exists";
        }
            return pollRepository.createQuestion(question);
    }
    public String update(Question question){

        if (pollRepository.getQuestionById(question.getId()) != null){
                pollRepository.update(question);
            pollRepository.deleteAnswerByQuestion(question.getId());
        return "The question is updated";
        }else return "The question is not exists";
    }
    public String deleteQuestion (int id ){
       if (getQuestionById(id) != null){
           return pollRepository.deleteQuestion(id);
       } return "The question withe id: " + id + "is not exists";
    }
    public List<Question> getAllQuestions(){
        return pollRepository.getAllQuestions();
    }
    public Question getQuestionById(int id){
        return pollRepository.getQuestionById(id);
    }
    public List<AnswerResponse> getAllAnswerByUserId(int id){
        List<AnswerResponse> answerResponses = pollRepository.getAllAnswerByUserId(id);


        return answerResponses;

    }
    public List<Answer> getAll(){
        return pollRepository.getAllAnswer();
    }
    public String  answerQuestion(Answer answer) {
if(pollRepository.hasUserAnswered(answer.getQuestionId(),answer.getUserId())) {
    return "You have already answered this question";
}return pollRepository.answerQuestion(answer) ;

    }
    public Integer  howManyQuestionUserAnswerById(int id){
        return pollRepository.howManyQuestionUserAnswerById(id);
    }
    public List<NumberAnswerResponse> howManyUsersChooseEachOfQuestionOption(int id){


        return pollRepository.howManyUsersChooseEachOfQuestionOption(id) ;
    }
    public Integer howManyAnswerTheQuestion(int id){
      return   pollRepository.howManyAnswerTheQuestion(id);
    }
    public List<QuestionAndNumberUser> allQuestionAndNumberOfUsersAnswer(){

        return  pollRepository.allQuestionAndNumberOfUsersAnswer();
    }
    public void deleteAnswerByUser(int userId){
        pollRepository.deleteAnswerByUser(userId);
    }

    public List<QuestionResultDTO> getResult(){

    return pollRepository.getResult();
    }


    }

