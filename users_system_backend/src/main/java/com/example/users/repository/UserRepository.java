package com.example.users.repository;


import com.example.users.model.UserCustomer;

import com.example.users.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
@Autowired
    private JdbcTemplate jdbcTemplate;

public String registerUser(UserCustomer user){
try {
    String sql = "INSERT INTO users (first_name,last_name,email,age,address,joining_date,role,password,username)  VALUES (?,?,?,?,?,?,?,?,?)";
    jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getAge(), user.getAddress(), user.getJoiningDate(), user.getRole().name(), user.getPassword(), user.getUsername());
    return "The user: " + user.getFirstName() + " is register successfully";
} catch (Exception e) {
    System.out.println(e.getMessage());
    return "The user: " + user.getFirstName() + " is not register";
}


}
public String update(UserCustomer user){
        String sql = "UPDATE users SET first_name = ? ,last_name = ? ,email = ?,age = ? ,address = ? ,joining_date = ?)  WHERE id =?  ";
        jdbcTemplate.update(sql,user.getFirstName(),user.getLastName(),user.getEmail(),user.getAge(),user.getAddress(),user.getJoiningDate(),user.getId());
        return "The user: " + user.getFirstName() + " is update";

}


public UserCustomer getUserById(int id){
    try {
        String sql = "SELECT * FROM users WHERE id = ?";
        return  jdbcTemplate.queryForObject(sql,new UserMapper(),id);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }
}
public UserCustomer getUserByEmailHelper(String email){
    try {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql,new UserMapper(),email);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }
}

    public List<UserCustomer> allUsers(){

        String sql ="SELECT * FROM users";
      return jdbcTemplate.query(sql,new UserMapper());


    }
    public String deleteById(int id){
        String sql = "DELETE FROM users WHERE id= ?";
        jdbcTemplate.update(sql,id);
        return "You user is deleted";

    }
    public UserCustomer getUserByUsername(String username){
        System.out.println(username + "username");
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            return jdbcTemplate.queryForObject(sql,new UserMapper(),username);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
