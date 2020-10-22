package com.epam.jewelry_shop.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.util.CapchaFactory;
import com.epam.jewelry_shop.util.CapchaGenerate;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

/**
 * Servlet that passes the captcha image to the registration.jsp page in byte
 * stream form. Captcha storage is provided from the server-side session
 * 
 * @autor Oleh Bober
 */
public class CapchaServlet extends HttpServlet {
	final static Logger LOGGER = Logger.getLogger(CapchaServletWithCookie.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		Integer capchaNumber = CapchaFactory.CreateCapcha(getServletContext().getInitParameter("longNumberCapcha"));
		BufferedImage captcha = CapchaGenerate.drowCapcha(capchaNumber);
		File outputFile = new File("image.png");
		ImageIO.write(captcha, "PNG", outputFile);
		byte[] array = Files.readAllBytes(outputFile.toPath());
		LOGGER.info("---------------Inside RegistrationServlet:doGet-----------");
		response.setContentLength(array.length);
		response.getOutputStream().write(array);
		HttpSession session = request.getSession();
		session.setAttribute("capcha", Integer.toString(capchaNumber));
		session.setMaxInactiveInterval(Integer.parseInt(getServletContext().getInitParameter("longLifeCapcha")));
		capchaNumber = null;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}
