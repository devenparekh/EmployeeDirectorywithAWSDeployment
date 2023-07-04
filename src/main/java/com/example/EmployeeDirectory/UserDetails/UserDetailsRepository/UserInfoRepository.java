package com.example.EmployeeDirectory.UserDetails.UserDetailsRepository;

import com.example.EmployeeDirectory.UserDetails.UserDetailsEntity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByName(String name);
}
