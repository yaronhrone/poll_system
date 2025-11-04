package com.example.users.controller;



import com.example.users.model.AuthenticationRequest;
import com.example.users.model.AuthenticationResponse;
import com.example.users.model.UserCustomer;
import com.example.users.service.AuthenticationService;
import com.example.users.service.PollClientService;
import com.example.users.service.UserService;
import com.example.users.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PollClientService pollClientService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserCustomer user) {
        try {
            String password = user.getPassword();
            String result = userService.registerUser(user);
if (result.contains(" successfully")) {
    AuthenticationResponse auth = authenticationService.createAuthenticationToken(new AuthenticationRequest(user.getUsername(), password));
    System.out.println(auth.getJwt() + "auth response");
    return new ResponseEntity<>(auth, HttpStatus.OK);
}return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize(" hasRole('USER') or hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<String> update(@RequestHeader (value = "Authorization") String token, @RequestBody UserCustomer userNew){
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            UserCustomer user = userService.getUserByUsername(username);
            if (user == null) {
                return new ResponseEntity("The user is not exists",HttpStatus.NOT_FOUND);
            }

            return  new ResponseEntity<>(userService.update(userNew), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<UserCustomer> getUserByUsername(@RequestHeader (value = "Authorization") String token){
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
        UserCustomer user = userService.getUserByUsername(username);
        if (user != null) {
        return new ResponseEntity<>(user, HttpStatus.OK);
        }else return new ResponseEntity("The username: " + username + " is not exists",HttpStatus.NOT_FOUND );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize(" hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestHeader (value = "Authorization") String token){
        try {
            String jwtToken = token.substring(7);
            String username = jwtUtil.extractUsername(jwtToken);
            int id = userService.getUserByUsername(username).getId();
            String result = userService.deleteById(id);
            if (result.contains("deleted")){
            pollClientService.deleteAnswer(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
