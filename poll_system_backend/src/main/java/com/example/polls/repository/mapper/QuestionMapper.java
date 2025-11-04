package com.example.polls.repository.mapper;


import com.example.polls.model.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setQuestion(rs.getString("question"));
        question.setId(rs.getInt("id"));
        question.setFirstOption(rs.getString("first_option"));
        question.setSecondOption(rs.getString("second_option"));
        question.setThirdOption(rs.getString("third_option"));
        question.setFourthOption(rs.getString("fourth_option"));

        return question;
    }
}
