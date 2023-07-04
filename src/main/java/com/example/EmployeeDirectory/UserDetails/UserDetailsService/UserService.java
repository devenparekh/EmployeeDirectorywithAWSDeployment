package com.example.EmployeeDirectory.UserDetails.UserDetailsService;

import com.example.EmployeeDirectory.UserDetails.UserDetailsEntity.UserInfo;
import com.example.EmployeeDirectory.UserDetails.UserDetailsRepository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserInfoRepository repository;

    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added to System";
    }
}
