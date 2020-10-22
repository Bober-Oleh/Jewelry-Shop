package com.epam.jewelry_shop.repository;

import com.epam.jewelry_shop.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that acts as a repository with a property <b>users</b>.
 * 
 * @autor Oleh Bober
 */
public class UserRepositoryImpl implements UserRepository {

	private List<User> users = new ArrayList<>();

	/**
	 * method for getting users {@link User # id}
	 * 
	 * @return returns all users
	 */
	@Override
	public List<User> findAll() {
		return users;
	}

}
