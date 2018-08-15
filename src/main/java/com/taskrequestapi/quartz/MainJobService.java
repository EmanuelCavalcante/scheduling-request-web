package com.taskrequestapi.quartz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskrequestapi.models.Task;
import com.taskrequestapi.models.User;
import com.taskrequestapi.service.UserService;

@Service
public class MainJobService {

	@Autowired
	UserService userService;

	public void execute() {
		List<User> userActives = userService.listUserActive();
		for (User user : userActives) {
			List<Task> taskOfUser = new ArrayList<>();
			for (Task task : taskOfUser) {
				
			}
		}
	}

}
