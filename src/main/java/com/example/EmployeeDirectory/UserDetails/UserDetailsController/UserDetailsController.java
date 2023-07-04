package com.example.EmployeeDirectory.UserDetails.UserDetailsController;

import com.example.EmployeeDirectory.UserDetails.UserDetailsEntity.UserInfo;
import com.example.EmployeeDirectory.UserDetails.UserDetailsService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController {

    @Autowired
    UserService service;

    @PostMapping("/newUser")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }
}
