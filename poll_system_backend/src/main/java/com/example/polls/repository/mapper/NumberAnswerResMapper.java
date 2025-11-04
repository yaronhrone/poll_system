package com.example.polls.repository.mapper;

import com.example.polls.model.NumberAnswerResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NumberAnswerResMapper implements RowMapper<NumberAnswerResponse> {
    @Override
    public NumberAnswerResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        NumberAnswerResponse numberAnswerResponse = new NumberAnswerResponse();
        numberAnswerResponse.setNumUsers( rs.getInt("num_users"));
        numberAnswerResponse.setAnswerNumber(rs.getInt("answer_number"));
        numberAnswerResponse.setQuestion(rs.getString("question"));


        return numberAnswerResponse;
    }


}
