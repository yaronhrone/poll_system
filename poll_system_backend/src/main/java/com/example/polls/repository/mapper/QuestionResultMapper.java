package com.example.polls.repository.mapper;

import com.example.polls.model.QuestionResultDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionResultMapper  implements RowMapper<QuestionResultDTO> {
    @Override
    public QuestionResultDTO mapRow(ResultSet rs, int rowNum) throws SQLException {


        int id = rs.getInt("question_id");
        String question = rs.getString("question");

        List<QuestionResultDTO.OptionResult> options = List.of(
                new QuestionResultDTO.OptionResult(rs.getString("first_option"), rs.getInt("first_option_count")),
                new QuestionResultDTO.OptionResult(rs.getString("second_option"), rs.getInt("second_option_count")),
                new QuestionResultDTO.OptionResult(rs.getString("third_option"), rs.getInt("third_option_count")),
                new QuestionResultDTO.OptionResult(rs.getString("fourth_option"), rs.getInt("fourth_option_count"))
        );

        return new QuestionResultDTO(id, question, options);
    }
}