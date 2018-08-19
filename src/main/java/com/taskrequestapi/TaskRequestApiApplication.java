package com.taskrequestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.taskrequestapi.service.TaskService;
import com.taskrequestapi.service.UserService;

@SpringBootApplication
public class TaskRequestApiApplication {

	@Autowired
	UserService userService;
	@Autowired
	TaskService taskService;

	public static void main(String[] args) {
		SpringApplication.run(TaskRequestApiApplication.class, args);
	}
}
