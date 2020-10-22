package com.epam.jewelry_shop.util;

import javax.servlet.http.HttpServletRequest;
import com.epam.jewelry_shop.model.User;

public class UserData {
	
	public User createUser(HttpServletRequest request, String fileName) {
		User user = new User(1, request.getParameter("first_name"), request.getParameter("last_name"),
				request.getParameter("email"), request.getParameter("password"), fileName);
		return user;
	}
	public void requestSetUserAttributes(HttpServletRequest request) {
		request.setAttribute("email", request.getParameter("email"));
		request.setAttribute("first_name", request.getParameter("first_name"));
		request.setAttribute("last_name", request.getParameter("last_name"));
		request.setAttribute("password", request.getParameter("password"));
		
	}
}
