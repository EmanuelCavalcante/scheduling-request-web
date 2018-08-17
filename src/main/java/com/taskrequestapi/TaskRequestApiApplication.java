package com.taskrequestapi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.taskrequestapi.models.Header;
import com.taskrequestapi.models.Task;
import com.taskrequestapi.models.User;
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

	@Bean
	public String teste() {
		User user = new User("email@parateste.com", "123456", true);
		this.userService.saveUser(user);
		User userDb = this.userService.findById(1);
		System.out.println(userDb.toString());
		List<Header> header = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2015);
		Timestamp startTask = new Timestamp(c.getTimeInMillis());
		Timestamp endTask = new Timestamp(new Date().getTime());
		Task task = new Task("https://api.meuatendimento.com.br/", "GET", true, header, 2, userDb, startTask, endTask);
		taskService.saveTask(task);
		Task taskDb = taskService.findById(1);
		List<Task> tasks = this.taskService.listTaskForUserAndTime(userDb, Calendar.getInstance());
		return "dsadas";
	}
}
