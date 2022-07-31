package com.example.demo.service;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;

public interface UserService {
    String getInformationByUsername(String username);

    User findUserByUsername(String username);

    Result register(User user);

    Result login(User user);


}
