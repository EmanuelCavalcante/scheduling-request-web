package com.taskrequestapi.serviceimpl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskrequestapi.models.Task;
import com.taskrequestapi.models.User;
import com.taskrequestapi.repository.TaskRepository;
import com.taskrequestapi.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	private static final int NUMBER_BEGINNING = 1;
	private static final int NUMBER_END = 60;

	@Autowired
	private TaskRepository taskRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taskrequestapi.serviceimpl.TaskService#listTaskForUserAndTime(com.
	 * taskrequestapi.models.User, com.taskrequestapi.models.Task,
	 * java.util.Calendar)
	 */
	@Override
	public List<Task> listTaskForUserAndTime(User user, Calendar calendar) {
		Set<Integer> minutes = getMultiples(calendar.get(Calendar.MINUTE));
		List<Task> tasks = taskRepository.listTaskForMinutes(user.getId(), minutes);
		return tasks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taskrequestapi.serviceimpl.TaskService#saveTask(com.taskrequestapi.models
	 * .Task)
	 */
	@Override
	public Task saveTask(Task task) {
		Task taskSaved = taskRepository.save(task);
		return taskSaved;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taskrequestapi.serviceimpl.TaskService#desableTask(java.lang.Integer)
	 */
	@Override
	public void desableTask(Integer id) {
		Task task = findById(id);
		task.setActive(false);
		saveTask(task);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taskrequestapi.serviceimpl.TaskService#findById(java.lang.Integer)
	 */
	@Override
	public Task findById(Integer id) {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			return task.get();
		} else {
			return null;
		}
	}

	private Set<Integer> getMultiples(Integer number) {
		Set<Integer> numbers = new HashSet<>();
		for (int i = NUMBER_BEGINNING; i <= NUMBER_END; i++) {
			if (number % i == 0) {
				numbers.add(i);
			}
			if (i % number == 0) {
				numbers.add(i);
			}

		}
		return numbers;
	}
}
