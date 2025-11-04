package com.example.polls.repository.mapper;

import com.example.polls.model.AnswerResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerResponseMapper implements RowMapper<AnswerResponse> {
    @Override
    public AnswerResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setQuestion(rs.getString("question"));
        answerResponse.setAnswerNumber(rs.getInt("answer_number"));

        return answerResponse;
    }
}
