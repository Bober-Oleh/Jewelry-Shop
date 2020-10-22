package com.epam.jewelry_shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.service.BasketService;
import com.epam.jewelry_shop.util.WebConstants;

/**
 * Servlet implementation class ServiceTargetServlet
 */
@WebServlet("/ServiceTargetServlet")
public class ServiceTargetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceTargetServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		BasketService target = (BasketService) session.getAttribute("target");
		int idProduct = Integer.parseInt(request.getParameter("id"));
		if (request.getParameter("delete") != null) {
			target.deleteProduct(idProduct);
		} else {
			int number = Integer.parseInt(request.getParameter("number"));
			target.setNumberProduct(idProduct, number);

		}
		session.setAttribute("target", target);
		response.sendRedirect(WebConstants.URL + "/ShowTarget");
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