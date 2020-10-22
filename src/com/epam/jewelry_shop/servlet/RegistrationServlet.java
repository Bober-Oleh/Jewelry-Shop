package com.epam.jewelry_shop.servlet;

import com.epam.jewelry_shop.model.User;
import com.epam.jewelry_shop.service.UserService;
import com.epam.jewelry_shop.util.UserData;
import com.epam.jewelry_shop.util.WebConstants;
import com.epam.jewelry_shop.util.WriteFileOnServer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

/**
 * Servlet that performs server-side validation of the data passed user.
 * Validation is carried out by getting a string with the number of captchas
 * from the session
 * 
 * @autor Oleh Bober
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class RegistrationServlet extends HttpServlet {
	final static Logger LOGGER = Logger.getLogger(CapchaServletWithCookie.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		LOGGER.info("---------------Inside RegistrationServlet:doGet-----------");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("registration.jsp");
		requestDispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userEmail = request.getParameter("email");
		UserService userService = (UserService) getServletContext().getAttribute("userService");
		User registeredUser = userService.isRegisteredUser(userEmail);
		HttpSession session = request.getSession();
		session.setAttribute("registeredUser", registeredUser);
		String capcha = request.getParameter("capcha");
		boolean cap = false;

		if (capcha != null) {
			if (session.getAttribute("capcha") == null) {
				request.setAttribute("validCapcha", "Время действия капчи истекло, введите цифры еще раз!");
			} else if (capcha.equals(session.getAttribute("capcha"))) {
				cap = true;
			} else {
				request.setAttribute("validCapcha", "Вы неправильно ввели цифры!");

			}
		}

		new UserData().requestSetUserAttributes(request);

		String jsp = "registration.jsp";
		if (cap) {
			String path = getServletContext().getInitParameter("file-upload");
			String fileName = new WriteFileOnServer().writeFile(path, request.getParts());
			User user = new UserData().createUser(request, fileName);
			session.setAttribute("registeredUser", user);
			if (userService.isRegisteredUser(userEmail) != null) {
				request.setAttribute("unvalidEmail", userEmail);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(jsp);
				requestDispatcher.forward(request, response);
			} else
				try {
					if (!userService.save(user)) {
						request.setAttribute("errorMessage", "Error saving in Database");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
						requestDispatcher.forward(request, response);

					} else {
						response.sendRedirect(WebConstants.URL);
					}
				} catch (NoSuchAlgorithmException | InvalidKeySpecException | ServletException | IOException e) {
					e.printStackTrace();
				}
		}
	}
}
