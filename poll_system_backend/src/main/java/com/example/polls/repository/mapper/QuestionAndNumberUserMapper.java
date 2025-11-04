package com.example.polls.repository.mapper;

import com.example.polls.model.QuestionAndNumberUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionAndNumberUserMapper implements RowMapper<QuestionAndNumberUser> {

    @Override
    public QuestionAndNumberUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuestionAndNumberUser questionAndNumberUser = new QuestionAndNumberUser();
        questionAndNumberUser.setQuestion(rs.getString("question"));
        questionAndNumberUser.setNumUsersAnswers(rs.getInt("number_users_answers"));
        return questionAndNumberUser;
    }
}
