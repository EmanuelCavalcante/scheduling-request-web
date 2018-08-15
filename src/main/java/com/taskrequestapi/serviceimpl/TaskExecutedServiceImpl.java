package com.taskrequestapi.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskrequestapi.models.Task;
import com.taskrequestapi.models.TaskExecuted;
import com.taskrequestapi.models.User;
import com.taskrequestapi.repository.TaskExecutedRepository;
import com.taskrequestapi.service.TaskExecutedService;

@Service
public class TaskExecutedServiceImpl implements TaskExecutedService {

	@Autowired
	private TaskExecutedRepository taskExecutedRepository;

	/* (non-Javadoc)
	 * @see com.taskrequestapi.serviceimpl.TaskExecutedService#listTaskExecuted(com.taskrequestapi.models.User, com.taskrequestapi.models.Task, java.util.Date, java.util.Date)
	 */
	@Override
	public List<TaskExecuted> listTaskExecuted(User user, Task task, Date dateBeginning, Date dateEnd) {
		 List<TaskExecuted>  tasksExecuted = taskExecutedRepository.tasksExecuted(user.getId(), task.getId(), dateBeginning, dateEnd);
		 return tasksExecuted;
	}
	
	/* (non-Javadoc)
	 * @see com.taskrequestapi.serviceimpl.TaskExecutedService#save(com.taskrequestapi.models.TaskExecuted)
	 */
	@Override
	public TaskExecuted save (TaskExecuted taskExecuted) {
		TaskExecuted taskExecutedSaved =	taskExecutedRepository.save(taskExecuted);
		return taskExecutedSaved;
	}

}
