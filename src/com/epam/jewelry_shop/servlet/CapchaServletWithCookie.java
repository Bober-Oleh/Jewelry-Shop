package com.epam.jewelry_shop.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.jewelry_shop.util.CapchaFactory;
import com.epam.jewelry_shop.util.CapchaGenerate;

/**
 * Servlet that passes the captcha image to the registration.jsp page in byte
 * stream form. Captcha is stored using a cookie on client side
 * 
 * @autor Oleh Bober
 */

public class CapchaServletWithCookie extends HttpServlet {
	final static Logger LOGGER = Logger.getLogger(CapchaServletWithCookie.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");
		Integer capchaNumber = CapchaFactory.CreateCapcha(getServletContext().getInitParameter("longNumberCapcha"));
		BufferedImage capcha = CapchaGenerate.drowCapcha(capchaNumber);
		File outputFile = new File("image.png");
		ImageIO.write(capcha, "PNG", outputFile);
		byte[] array = Files.readAllBytes(outputFile.toPath());

		LOGGER.info("---------------Inside RegistrationServlet:doGet-----------");

		Cookie cookie = new Cookie("user", Integer.toBinaryString(capchaNumber));
		cookie.setMaxAge(Integer.parseInt(getServletContext().getInitParameter("longLifeCapcha")));
		response.addCookie(cookie);
		response.setContentLength(array.length);
		response.getOutputStream().write(array);
		capchaNumber = null;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
}
