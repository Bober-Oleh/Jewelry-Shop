package com.epam.jewelry_shop.dao;

import com.epam.jewelry_shop.database.DBHelper;

public class UserDAOFactory {
	public static UserDAO getUserDAO(String type, DBHelper dbHelper) {
		if (type.equalsIgnoreCase("mysql")) {
			return new UserDAOImpl(dbHelper);
		} else {
			return null;
		}
	}
}
