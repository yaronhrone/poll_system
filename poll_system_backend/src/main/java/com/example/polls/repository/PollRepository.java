package com.example.polls.repository;


import com.example.polls.model.*;
import com.example.polls.repository.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PollRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String ANSWER_QUESTION_TABLE = "answer_question";
    private final String QUESTION_TABLE = "question";

    public String createQuestion(Question question) {
        try {
            String sql = String.format("INSERT INTO %s (question,first_option,second_option,third_option,fourth_option) VALUES (?,?,?,?,?)", QUESTION_TABLE);
            jdbcTemplate.update(sql, question.getQuestion(), question.getFirstOption(), question.getSecondOption(), question.getThirdOption(), question.getFourthOption());
            return "The question is created ";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "The question Illegal ";
        }
    }

    public Question getQuestionByName(String questionName) {
        try {
            String sql = String.format("SELECT * FROM %s WHERE question = ?", QUESTION_TABLE);
            return jdbcTemplate.queryForObject(sql, new QuestionMapper(), questionName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return  null ;
        }
}

public String update(Question question){
try {
        String sql = String.format("UPDATE %s  SET question = ? , first_option = ? ,second_option = ? , third_option = ? , fourth_option = ? WHERE id = ?",QUESTION_TABLE);
        jdbcTemplate.update(sql , question.getQuestion(),question.getFirstOption(),question.getSecondOption(),question.getThirdOption(),question.getFourthOption(),question.getId());
return "The question is update";

} catch (Exception e) {
    return "The question is not update";
}

}
public String deleteQuestion(int id){
    String sql = String.format("DELETE FROM %s WHERE id = ?",QUESTION_TABLE);
    jdbcTemplate.update(sql,id);

    return "The question is deleted";
}



public List<Question> getAllQuestions(){

        String sql = String.format("SELECT * FROM %s ",QUESTION_TABLE);
        return jdbcTemplate.query(sql,new QuestionMapper());

}
    public Question getQuestionById(int id){
    try {
                String sql = String.format("SELECT * FROM %s WHERE id = ?",QUESTION_TABLE);

return jdbcTemplate.queryForObject(sql, new QuestionMapper(),id);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }
}



    public String answerQuestion(Answer answer)  {

                String sql = String.format("INSERT INTO %s (question_id,answer_number,user_id) VALUES (?,?,?)",ANSWER_QUESTION_TABLE);
            jdbcTemplate.update(sql , answer.getQuestionId(),answer.getAnswerNumber(),answer.getUserId());
    return "The answer is sending";


    }

    public List<Answer> getAllAnswer(){
        String sql = String.format("SELECT * FROM %s",ANSWER_QUESTION_TABLE);
        return   jdbcTemplate.query(sql, new AnswerMapper());
    }
    public List<AnswerResponse> getAllAnswerByUserId(int id){
    try {

    String sql = String.format("SELECT q.question,a_q.answer_number " +
            "FROM %s a_q  " +
            "JOIN %s q ON a_q.question_id = q.id " +
            "WHERE user_id = ? " +
            "GROUP BY q.question, a_q.answer_number",ANSWER_QUESTION_TABLE,QUESTION_TABLE);
    return  jdbcTemplate.query(sql, new AnswerResponseMapper(),id);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }
    }
    public List<NumberAnswerResponse> howManyUsersChooseEachOfQuestionOption(int id){

        String sql = String.format("SELECT  " +
                "    q.question,  " +
                "    aq.answer_number,  " +
                "    COUNT(aq.user_id) AS num_users " +
                "FROM  " +
                "    %s q " +
                "LEFT JOIN " +
                "   %s aq ON aq.question_id = q.id " +
                "WHERE " +
                "    q.id = ?  " +
                "GROUP BY  " +
                "    q.id, aq.answer_number " +
                "ORDER BY  " +
                "    aq.answer_number; ",QUESTION_TABLE,ANSWER_QUESTION_TABLE);

         return jdbcTemplate.query(sql,new NumberAnswerResMapper() ,id);

    }

public Integer howManyAnswerTheQuestion(int id){
    String sql = String.format("SELECT COUNT(id) FROM %s " +
            "WHERE question_id = ?;",ANSWER_QUESTION_TABLE);
    return jdbcTemplate.queryForObject(sql,Integer.class,id);
}
    public Integer howManyQuestionUserAnswerById(int id){
    String sql = String.format("SELECT COUNT(*) FROM %s WHERE user_id =?", ANSWER_QUESTION_TABLE);
    return jdbcTemplate.queryForObject(sql, Integer.class,id);
    }
    public List<QuestionAndNumberUser> allQuestionAndNumberOfUsersAnswer(){
    String sql = String.format(
            "SELECT q.id , q.question, COUNT(a_q.user_id) AS number_users_answers " +
            "FROM  %s q " +
            "LEFT JOIN " +
            "%s a_q  ON  q.id = a_q.question_id  " +
            "GROUP BY q.question ,q.id  ",QUESTION_TABLE,ANSWER_QUESTION_TABLE);


    return jdbcTemplate.query(sql,new QuestionAndNumberUserMapper());
    }
    public void deleteAnswerByUser(int userId){
    String sql = String.format("DELETE FROM %s WHERE user_id = ?",ANSWER_QUESTION_TABLE);
        jdbcTemplate.update(sql, userId);

    }
    public void deleteAnswerByQuestion(int questionId){
        String sql = String.format("DELETE FROM %s WHERE question_id = ?",ANSWER_QUESTION_TABLE);
        jdbcTemplate.update(sql, questionId);
    }
    public boolean hasUserAnswered(int questionId, int userId) {
        String sql = String.format("SELECT COUNT(*) FROM %s WHERE question_id = ? AND user_id = ?", ANSWER_QUESTION_TABLE);
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, questionId, userId);
        System.out.println(count != null && count > 0);
        return count != null && count > 0;
    }



        public List<QuestionResultDTO> getResult() {
            try {
                String sql = String.format("SELECT " +
                        "    q.id AS question_id," +
                        "    q.question," +
                        "    q.first_option," +
                        "    q.second_option," +
                        "    q.third_option," +
                        "    q.fourth_option," +
                        "    SUM(CASE WHEN a.answer_number = 1 THEN 1 ELSE 0 END) AS first_option_count," +
                        "    SUM(CASE WHEN a.answer_number = 2 THEN 1 ELSE 0 END) AS second_option_count," +
                        "    SUM(CASE WHEN a.answer_number = 3 THEN 1 ELSE 0 END) AS third_option_count," +
                        "    SUM(CASE WHEN a.answer_number = 4 THEN 1 ELSE 0 END) AS fourth_option_count" +
                        " FROM " +
                        "    %s q" +
                        " LEFT JOIN " +
                        "    %s a" +
                        " ON " +
                        "    q.id = a.question_id" +
                        " GROUP BY " +
                        "    q.id, q.question, q.first_option, q.second_option, q.third_option, q.fourth_option" +
                        " ORDER BY " +
                        "    q.id; ", QUESTION_TABLE, ANSWER_QUESTION_TABLE);
                return jdbcTemplate.query(sql, new QuestionResultMapper());
            } catch (DataAccessException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }


}
