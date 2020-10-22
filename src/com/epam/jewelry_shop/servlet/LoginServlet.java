package com.epam.jewelry_shop.servlet;

import com.epam.jewelry_shop.model.User;
import com.epam.jewelry_shop.service.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Authentication.jsp");
		requestDispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");

		UserService userService = (UserService) getServletContext().getAttribute("userService");
		User registeredUser = userService.authenticate(userEmail, userPassword);

		if (registeredUser != null) {
			session.setAttribute("registeredUser", registeredUser);
			if (session.getAttribute("DesiredURL") != null) {
				String URL = (String) session.getAttribute("DesiredURL");
				session.removeAttribute("DesiredURL");
				response.sendRedirect(URL);
			} else {
				response.sendRedirect(request.getHeader("Referer"));
			}

		} else {
			response.sendRedirect(request.getHeader("Referer"));
		}
	}
}