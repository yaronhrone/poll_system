package com.example.polls.repository.mapper;

import com.example.polls.model.Answer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {

    @Override
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {

        Answer answer =  new Answer();
        answer.setId((rs.getInt("id")));
        answer.setIdQuestion((rs.getInt("id_question")));
        answer.setAnswerNumber(rs.getInt("answer_number"));
        answer.setUserId(rs.getInt("user_id"));
        return answer;
    }
}
