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
public String createQuestion(Question question){
try {
    String sql = String.format("INSERT INTO %s (question,first_option,second_option,third_option,fourth_option) VALUES (?,?,?,?,?)", QUESTION_TABLE);
    jdbcTemplate.update(sql, question.getQuestion(), question.getFirstOption(), question.getSecondOption(), question.getThirdOption(), question.getFourthOption());
    return "The question is created ";
} catch (Exception e) {
    System.out.println(e.getMessage());
    return "The question Illegal ";
}
}
public String update(Question question){

        String sql = String.format("UPDATE %s  SET question = ? , first_option = ? ,second_option = ? , third_option = ? , fourth_option = ? WHERE id = ?",QUESTION_TABLE);
        jdbcTemplate.update(sql , question.getQuestion(),question.getFirstOption(),question.getSecondOption(),question.getThirdOption(),question.getFourthOption(),question.getId());
return "The question is update";

}
public String deleteQuestion(int id){
    String sql = String.format("DELETE FROM %s WHERE id = ?",QUESTION_TABLE);
    jdbcTemplate.update(sql,id);

    return "The question is deleted";
}
public Question getQuestionHelper(String question){
try {
    String sql = String.format("SELECT * FROM %s WHERE question = ?", QUESTION_TABLE);

        return jdbcTemplate.queryForObject(sql, new QuestionMapper(),question);
} catch (Exception e) {
    System.out.println(e.getMessage());
    return null;
}

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

    public Answer getResultQuestion(int id){
            String sql = String.format("SELECT * FROM %s WHERE id = ?",ANSWER_QUESTION_TABLE);
            return  jdbcTemplate.queryForObject(sql, new AnswerMapper(),id);
    }
    public String answerQuestion(Answer answer)  {

                String sql = String.format("INSERT INTO %s (id_question,answer_number,user_id) VALUES (?,?,?)",ANSWER_QUESTION_TABLE);
            jdbcTemplate.update(sql , answer.getIdQuestion(),answer.getAnswerNumber(),answer.getUserId());
    return "The answer is sending";


    }
    public List<Answer> getAllAnswer(){
        String sql = "SELECT * FROM answer_question";
        return   jdbcTemplate.query(sql, new AnswerMapper());
    }
    public List<AnswerResponse> getAllAnswerByUserId(int id){
    try {

    String sql = String.format("SELECT q.question,a_q.answer_number " +
            "FROM %s a_q  " +
            "JOIN %s q ON a_q.id_question = q.id " +
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
                "   %s aq ON aq.id_question = q.id " +
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
            "WHERE id_question = ?;",ANSWER_QUESTION_TABLE);
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
            "%s a_q  ON  q.id = a_q.id_question  " +
            "GROUP BY q.question ,q.id  ",QUESTION_TABLE,ANSWER_QUESTION_TABLE);


    return jdbcTemplate.query(sql,new QuestionAndNumberUserMapper());
    }
    public void deleteAnswerByUser(int userId){
    String sql = String.format("DELETE FROM %s WHERE user_id = ?",ANSWER_QUESTION_TABLE);
        jdbcTemplate.update(sql, userId);

    }
    public Answer getAnswerByIdHelper(int id){
    try {

    String sql = String.format("SELECT * FROM %s WHERE id = ?",ANSWER_QUESTION_TABLE);

   return jdbcTemplate.queryForObject(sql,new AnswerMapper(),id);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }

    }
    public String getAnswer(int questionId, int answerNumber, int userId) {
        try {

            String sql = String.format("SELECT " +
                    " CASE a.answer_number " +
                    " WHEN 1 THEN q.first_option" +
                    " WHEN 2 THEN q.second_option" +
                    " WHEN 3 THEN q.third_option" +
                    " WHEN 4 THEN q.fourth_option" +
                    " END AS selected_answer," +
                    " a.user_id FROM %s a " +
                    "JOIN %s q ON a.id_question = q.id " +
                    "WHERE a.id_question = ? AND a.answer_number = ? AND a.user_id = ?", ANSWER_QUESTION_TABLE, QUESTION_TABLE);

            return jdbcTemplate.queryForObject(sql, String.class, questionId, answerNumber, userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
        public int getIdQuestion (String question ) {
            try {
                String sql = String.format("SELECT id FROM %s WHERE question = ?", QUESTION_TABLE);
                return jdbcTemplate.queryForObject(sql, Integer.class, question);
            } catch (DataAccessException e) {
                return 0;
            }
        }


}
