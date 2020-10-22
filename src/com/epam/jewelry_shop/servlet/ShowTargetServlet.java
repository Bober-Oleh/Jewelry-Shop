package com.epam.jewelry_shop.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.dao.ProductDAOImpl;
import com.epam.jewelry_shop.service.BasketService;

/**
 * Servlet implementation class ShowTargetServlet
 */
@WebServlet("/ShowTarget")
public class ShowTargetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowTargetServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProductDAOImpl productDAO = (ProductDAOImpl) getServletContext().getAttribute("productDAO");
		if (session.getAttribute("target") != null) {
			BasketService target = (BasketService) session.getAttribute("target");
			request.setAttribute("listTarget", target.getBasketMap());
			request.setAttribute("totalPrice", target.getTotalPrice());

		}
		RequestDispatcher dis = request.getRequestDispatcher("target.jsp");
		dis.forward(request, response);

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