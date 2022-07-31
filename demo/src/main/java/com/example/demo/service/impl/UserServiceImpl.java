package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper usermapper;

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public String getInformationByUsername(String username){
        return(""+username+"'s information:"+usermapper.getInformationByUsername(username));
    }

    @Override
    public User findUserByUsername(String username){
        return(usermapper.findUserByUsername(username));
    }

    @Override
    public Result register(User user){
        Result result = new Result();
        result.setCode(null);
        result.setMsg("Register Failed");
        result.setData(null);
        try{
            User existUser = usermapper.findUserByUsername(user.getUsername());
            if(existUser != null){
                // If this user is already exist
                result.setCode("2");
                result.setMsg("This username already exist");
            }else{
                System.out.println("Registering "+user.getUsername()+"...");
                usermapper.registerWithUsernameAndPassword(user.getUsername(), user.getPassword());
                result.setCode("1");
                result.setMsg("Registration Success!");
            }
        }catch ( Exception e){
            result.setCode("0");
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return(result);
    }

    @Override
    public Result login(User user){
        Result result = new Result();
        result.setCode(null);
        result.setMsg("Login failed");
        result.setData(null);
        try {
            User loginUser = usermapper.loginWithUsernameAndPassword(user.getUsername(), user.getPassword());
            if(loginUser == null){
                result.setMsg("Username and password incorrect!");
                result.setCode("2");
                result.setData(null);

            }else {
                result.setCode("1");
                result.setMsg("Login Success");
                System.out.println(user.getUsername()+" login!");
                // Insert token string into data part
                result.setData(tokenUtils.createToken(user));
            }
        }catch ( Exception e){
            result.setCode("0");
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return (result);
    }

}
