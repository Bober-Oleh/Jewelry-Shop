package com.epam.jewelry_shop.service;

import com.epam.jewelry_shop.model.User;

public interface UserService extends CRUDService<User, Long> {

	User isRegisteredUser(String email);

	User authenticate(String userLogin, String userPassword);
}
