package com.taskrequestapi.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taskrequestapi.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Query("SELECT task FROM Task task WHERE task.active = true AND task.user.id = :id AND task.executeIn in (:minutes) ")
	public List<Task> listTaskForMinutes(@Param("id") Integer idUser, @Param("minutes") Set<Integer> minutes);
}
