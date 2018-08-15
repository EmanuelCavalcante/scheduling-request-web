package com.taskrequestapi.service;

import java.util.Calendar;
import java.util.List;

import com.taskrequestapi.models.Task;
import com.taskrequestapi.models.User;

public interface TaskService {

	List<Task> listTaskForUserAndTime(User user, Calendar calendar);

	Task saveTask(Task task);

	void desableTask(Integer id);

	Task findById(Integer id);

}