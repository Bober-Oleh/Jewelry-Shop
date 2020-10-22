package com.epam.jewelry_shop.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.model.Order;
import com.epam.jewelry_shop.model.User;
import com.epam.jewelry_shop.service.BasketService;
import com.epam.jewelry_shop.service.OrderService;
import com.epam.jewelry_shop.util.WebConstants;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (request.getParameter("recvisits") != null) {
			List<String> recvisits = new ArrayList();
			recvisits.add(request.getParameter("delivery"));
			recvisits.add(request.getParameter("payment"));
			recvisits.add(request.getParameter("name"));
			recvisits.add(request.getParameter("adress"));
			session.setAttribute("recvisits", recvisits);
		}
		BasketService target;
		if (session.getAttribute("target") != null && request.getParameter("recvisits") != null) {
			target = (BasketService) session.getAttribute("target");
			OrderService orderService = new OrderService();
			Order order = orderService.getOrderFromBasket(target.getBasketMap(),
					(User) session.getAttribute("registeredUser"));
			orderService.saveOrder(order);
			target.cleanTarget();
			session.setAttribute("target", target);

		}
		if (session.getAttribute("recvisits") != null) {
			response.sendRedirect(WebConstants.URL + "/ShowTarget");
		} else {

			RequestDispatcher dis = request.getRequestDispatcher("orderInform.jsp");
			dis.forward(request, response);
		}
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