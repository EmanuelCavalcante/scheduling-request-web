package com.taskrequestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskrequestapi.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT user FROM User user where user.active = true")
	public List<User> listUserActive();
}
