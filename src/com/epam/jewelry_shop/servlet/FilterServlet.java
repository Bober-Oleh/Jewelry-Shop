package com.epam.jewelry_shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.beans.FilterFormBean;
import com.epam.jewelry_shop.beans.FilterFormBeanValidator;
import com.epam.jewelry_shop.dao.ProductDAOImpl;
import com.epam.jewelry_shop.util.WebConstants;

/**
 * Servlet implementation class FilterServlet
 */
@WebServlet("/FilterServlet")
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilterServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAOImpl productDAO = (ProductDAOImpl)getServletContext().getAttribute("productDAO");
		HttpSession session = request.getSession();
		session.setAttribute("numberPage", 1);
		FilterFormBean filterBean=new FilterFormBeanValidator().filterFormBeanValidate(request);
		session.setAttribute("filters", filterBean);
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