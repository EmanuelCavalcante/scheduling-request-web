package com.taskrequestapi.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskrequestapi.models.User;
import com.taskrequestapi.repository.UserRepository;
import com.taskrequestapi.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taskrequestapi.serviceimpl.UserService#listUserActive()
	 */
	@Override
	public List<User> listUserActive() {
		List<User> users = userRepository.listUserActive();
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taskrequestapi.serviceimpl.UserService#saveUser(com.taskrequestapi.models
	 * .User)
	 */
	@Override
	public User saveUser(User user) {
		User userSave = userRepository.save(user);
		return userSave;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taskrequestapi.serviceimpl.UserService#findById(java.lang.Integer)
	 */
	@Override
	public User findById(Integer id) {
		Optional<User> userSave = userRepository.findById(id);
		return userSave.get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taskrequestapi.serviceimpl.UserService#desable(java.lang.Integer)
	 */
	@Override
	public User desable(Integer id) {
		User userSave = findById(id);
		userSave.setActive(false);
		User userUpdate = saveUser(userSave);
		return userUpdate;
	}
}
