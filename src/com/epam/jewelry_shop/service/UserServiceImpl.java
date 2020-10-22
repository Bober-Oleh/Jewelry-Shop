package com.epam.jewelry_shop.service;

import com.epam.jewelry_shop.model.User;
import com.epam.jewelry_shop.util.HashPasswordAuthentication;
import com.epam.jewelry_shop.dao.UserDAOImpl;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
	private UserDAOImpl userDao;

	public UserServiceImpl(UserDAOImpl userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean save(User entity) {
		return userDao.createUser(entity);

	}

	public User isRegisteredUser(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User authenticate(String email, String password) {
		User registeredUser = null;

		List<User> allUsersList = new ArrayList<>();
		allUsersList = this.userDao.getAllUsers();

		for (User user : allUsersList) {
			if (user.getEmail().equals(email)
					&& (new HashPasswordAuthentication().authenticate(password.toCharArray(), user.getPassword())) // user.getPassword().equals(password)
			) {
				registeredUser = user;
				break;
			}
		}
		return registeredUser;
	}
}
