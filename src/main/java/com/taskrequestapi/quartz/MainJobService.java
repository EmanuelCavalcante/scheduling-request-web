package com.taskrequestapi.quartz;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskrequestapi.http.RequestHttpUtil;
import com.taskrequestapi.models.Task;
import com.taskrequestapi.models.TaskExecuted;
import com.taskrequestapi.models.User;
import com.taskrequestapi.service.TaskExecutedService;
import com.taskrequestapi.service.TaskService;
import com.taskrequestapi.service.UserService;
import com.taskrequestapi.utils.email.SendEmail;

@Service
public class MainJobService {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private TaskExecutedService taskExecutedService;

	public void execute() {
		Calendar calendar = Calendar.getInstance();
		RequestHttpUtil request = new RequestHttpUtil();
		List<User> userActives = userService.listUserActive();
		for (User user : userActives) {
			List<Task> taskOfUser = taskService.listTaskForUserAndTime(user, calendar);
			for (Task task : taskOfUser) {
				TaskExecuted taskExecuted = request.request(task);
				taskExecuted.setUser(user);
				taskExecuted.setTask(task);
				TaskExecuted taskExecutedDb = taskExecutedService.save(taskExecuted);
				if (taskExecutedDb.getErro()) {
					if (user.isUserIsVip()) {
						try {
							SendEmail.sendEmail(taskExecutedDb.getResult(), user.getEmail());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				System.out.println(new Date() + " :" + taskExecuted);
			}
		}
	}

}
