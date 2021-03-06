package com.springexample.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
@EnableConfigurationProperties()
public class Application {

	public static Datalayer dl = new Datalayer();  //initialize new datalayer instance
	public static User currentUser;
	public static ArrayList<Course> courseList = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);//build new spring application using this class
		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
		//SpringApplication.run(Application.class, args);
	}
}