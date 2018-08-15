package com.taskrequestapi.service;

import java.util.Date;
import java.util.List;

import com.taskrequestapi.models.Task;
import com.taskrequestapi.models.TaskExecuted;
import com.taskrequestapi.models.User;

public interface TaskExecutedService {

	List<TaskExecuted> listTaskExecuted(User user, Task task, Date dateBeginning, Date dateEnd);

	TaskExecuted save(TaskExecuted taskExecuted);

}