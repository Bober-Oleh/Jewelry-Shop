package com.epam.jewelry_shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.dao.ProductDAOImpl;
import com.epam.jewelry_shop.service.BasketService;

/**
 * Servlet implementation class AddTargetServlet
 */
@WebServlet("/AddTargetServlet")
public class AddTargetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTargetServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProductDAOImpl productDAO = (ProductDAOImpl)getServletContext().getAttribute("productDAO");
		BasketService target;
		if (session.getAttribute("target") != null) {
			target = (BasketService) session.getAttribute("target");
		} else {
			target = new BasketService();
		}
		target.addProduct(productDAO.getProductById(Integer.parseInt(request.getParameter("targetId"))));
		session.setAttribute("target", target);
		PrintWriter writer = response.getWriter();
		writer.println("Product added");

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
