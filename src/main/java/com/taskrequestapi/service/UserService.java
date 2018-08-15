package com.taskrequestapi.service;

import java.util.List;

import com.taskrequestapi.models.User;

public interface UserService {

	List<User> listUserActive();

	User saveUser(User user);

	User findById(Integer id);

	User desable(Integer id);

}