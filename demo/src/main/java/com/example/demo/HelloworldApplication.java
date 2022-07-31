package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Main enterance
//To identify this is a spring boot application
@SpringBootApplication
//Scan mapper
@MapperScan("com.example.demo.mapper")
public class HelloworldApplication {

	public static void main(String[] args) {
		//start springboot application
		SpringApplication.run(HelloworldApplication.class, args);
	}

}
