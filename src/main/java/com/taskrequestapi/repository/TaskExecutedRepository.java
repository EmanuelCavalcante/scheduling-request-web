package com.taskrequestapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taskrequestapi.models.TaskExecuted;

@Repository
public interface TaskExecutedRepository extends JpaRepository<TaskExecuted, Integer> {
	@Query("SELECT taskExecuted FROM TaskExecuted taskExecuted where taskExecuted.user.id = :userId "
			+ "AND taskExecuted.task.id =:taskId AND taskExecuted.date>=:dateBeginning "
			+ "AND taskExecuted.date<=:dateEnd ")
	List<TaskExecuted> tasksExecuted(@Param("userId") Integer userId, @Param("taskId") Integer taskId,
			@Param("dateBeginning") Date dateBeginning, @Param("dateEnd") Date dateEnd);
}
