package com.example.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class User {
    private String username;
    private String password;
    private String information;
    private Integer priority;
    private Integer uid;

    public String getSession() {
        return session;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", information='" + information + '\'' +
                ", priority=" + priority +
                ", uid=" + uid +
                ", session='" + session + '\'' +
                '}';
    }

    public void setSession(String session) {
        this.session = session;
    }

    private String session;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
        this.username = "default_user";
        this.password = "111111";
        this.priority = 2;
        this.information = "user information undefined";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
