package com.epam.jewelry_shop.dao;

import com.epam.jewelry_shop.model.User;
import java.util.List;

public interface UserDAO {

	boolean createUser(User user);

	User getUserById(int id);

	User getUserByEmail(String email);

	List<User> getAllUsers();
}
