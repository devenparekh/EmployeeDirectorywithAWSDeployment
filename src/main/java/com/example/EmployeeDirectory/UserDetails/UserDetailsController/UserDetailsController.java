package com.example.EmployeeDirectory.UserDetails.UserDetailsController;

import com.example.EmployeeDirectory.UserDetails.UserDetailsEntity.UserInfo;
import com.example.EmployeeDirectory.UserDetails.UserDetailsService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
public class UserDetailsController {

    @Autowired
    UserService service;

    @PostMapping("/newUser")
    public ResponseEntity<String> addNewUser(@RequestBody UserInfo userInfo){
        if(userInfo.equals(null)){
            return ResponseEntity.notFound().build();
        }else {
            String addedUser = service.addUser(userInfo);
            return ResponseEntity.ok("New User Added with Details: " + userInfo);
        }
    }
}
