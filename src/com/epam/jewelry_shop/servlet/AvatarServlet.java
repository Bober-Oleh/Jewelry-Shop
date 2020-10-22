package com.epam.jewelry_shop.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.model.User;

/**
 * Servlet implementation class AvatarServlet
 */
@WebServlet("/avatar")
public class AvatarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("registeredUser");
		String filename = "default.jpg";
		if (user != null) {
			if (user.getAvatar() != null) {
				filename = user.getAvatar();
			}
		}
		File file = new File(getServletContext().getInitParameter("file-upload") + "\\" + filename);
		byte[] array = Files.readAllBytes(file.toPath());
		response.setContentLength(array.length);
		response.getOutputStream().write(array);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}