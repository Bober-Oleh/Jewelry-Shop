package com.epam.jewelry_shop.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.epam.jewelry_shop.model.User;

public class UserResultSetExtracter {
	public User createUserFromResultSet(ResultSet rs) throws SQLException {
		int idUser = rs.getInt("idUser");
		String firstName = rs.getString("firstName");
		String lastName = rs.getString("lastName");
		String email = rs.getString("email");
		String password = rs.getString("password");
		String avatar = rs.getString("avatar");
		String role = rs.getString("role");
		User user = new User(idUser, firstName, lastName, email, password, avatar,role);
		return user;
	}
}
