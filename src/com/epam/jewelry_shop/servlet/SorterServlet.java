package com.epam.jewelry_shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.dao.ProductDAOImpl;
import com.epam.jewelry_shop.util.WebConstants;

/**
 * Servlet implementation class SorterServlet
 */
@WebServlet("/SorterServlet")
public class SorterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SorterServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProductDAOImpl productDAO = (ProductDAOImpl) getServletContext().getAttribute("productDAO");
		String sortSQL = productDAO.createSortForQuery(request.getParameter("fieldSort"),
				Integer.parseInt(request.getParameter("typeSort")));
		session.setAttribute("sortString", sortSQL);
		session.setAttribute("numberPage", 1);
		response.sendRedirect(WebConstants.URL);
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