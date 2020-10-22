package com.epam.jewelry_shop.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.beans.FilterFormBean;
import com.epam.jewelry_shop.dao.CategoryDAOImpl;
import com.epam.jewelry_shop.dao.ProducerDAOImpl;
import com.epam.jewelry_shop.dao.ProductDAOImpl;

/**
 * Servlet implementation class ProductServlet
 */
//@WebServlet("/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAOImpl productDAO = (ProductDAOImpl) getServletContext().getAttribute("productDAO");
		ProducerDAOImpl producerDAO = (ProducerDAOImpl) getServletContext().getAttribute("producerDAO");
		CategoryDAOImpl categoryDAO = (CategoryDAOImpl) getServletContext().getAttribute("categoryDAO");
		int numberPage = 1;

		String sortForQuery = "order by name";
		HttpSession session = request.getSession();
		if (session.getAttribute("sortString") != null) {
			sortForQuery = (String) session.getAttribute("sortString");
		}

		int numberProducts = 1;
		if (session.getAttribute("numberProducts") != null) {
			numberProducts = (int) session.getAttribute("numberProducts");
		}

		int countProduct = 0;
		String filtersQuery = "";
		if (session.getAttribute("numberPage") != null) {
			numberPage = (int) session.getAttribute("numberPage");

		}

		if (session.getAttribute("filters") != null) {
			FilterFormBean filterBean = (FilterFormBean) session.getAttribute("filters");
			filtersQuery = productDAO.createFiltersForQuery(filterBean);

		}
		countProduct = productDAO.countProducts(filtersQuery);
		request.setAttribute("products",
				productDAO.findProducts(countProduct, numberProducts, numberPage, filtersQuery, sortForQuery));
		request.setAttribute("categories", categoryDAO.findAllCategory());
		request.setAttribute("pages", (int) (Math.ceil(countProduct / (double) numberProducts)));
		request.setAttribute("producers", producerDAO.findAllProducer());
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}