package com.example.EmployeeDirectory.UserDetails.UserDetailsService;

import com.example.EmployeeDirectory.UserDetails.UserDetailsEntity.UserInfo;
import com.example.EmployeeDirectory.UserDetails.UserDetailsRepository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(userName);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found " + userName));
    }
}

