package com.example.users.repository.mapper;



import com.example.users.model.Role;
import com.example.users.model.UserCustomer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserCustomer> {
    @Override
    public UserCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
       UserCustomer user = new UserCustomer();
       user.setId(rs.getInt("id"));
       user.setFirstName(rs.getString("first_name"));
       user.setLastName(rs.getString("last_name"));
       user.setEmail(rs.getString("email"));
       user.setAddress(rs.getString("address"));
       user.setAge(rs.getInt("age"));
       user.setJoiningDate(rs.getDate("joining_date"));
       user.setRole(Role.valueOf(rs.getString("role")));
       user.setPassword(rs.getString("password"));
       user.setUsername(rs.getString("username"));

        return user;
    }
}
