package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    String getInformationByUsername(String username);

    User findUserByUsername(String username);

    void registerWithUsernameAndPassword(String username, String password);

    User loginWithUsernameAndPassword(String username, String password);

}
